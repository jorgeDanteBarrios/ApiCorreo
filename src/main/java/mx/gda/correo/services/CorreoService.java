package mx.gda.correo.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.gda.correo.entities.Bitacora;
import mx.gda.correo.entities.Sender;
import mx.gda.correo.interfaces.SenderRepository;
import mx.gda.correo.objects.Email;
import mx.gda.correo.objects.EmailFile;
import mx.gda.correo.objects.Webhook;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.SendersApi;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.GetSendersList;
import sibModel.GetSendersListSenders;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailAttachment;
import sibModel.SendSmtpEmailBcc;
import sibModel.SendSmtpEmailCc;
import sibModel.SendSmtpEmailReplyTo;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

@Service
public class CorreoService {
	
	Logger logger= LoggerFactory.getLogger(CorreoService.class);
	
	@Autowired
	Environment env;
	@Value("${api.sendinBlue.key}")
	private String API_KEY;
	@Value("${api.sendinBlue.timeout.miliseconds}")
	private Integer TIME_OUT;
	
	@Autowired
	private BitacoraService bitacoraService;	
	@Autowired
	private SenderRepository senderRepository;

	
	/* Método para obtener la lista de correos origen */
	public List<GetSendersListSenders> getSenders() {
		List<GetSendersListSenders> salida=null;
		logger.info(" Se consume método:  getSenders ");
		//Set API KEY
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setConnectTimeout(TIME_OUT); //timeOUt - int miliseconds
		defaultClient.setWriteTimeout(TIME_OUT);
		defaultClient.setReadTimeout(TIME_OUT);
		ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
	    apiKey.setApiKey(API_KEY);
	    // Sender
	    SendersApi apiInstanceSender = new SendersApi();
   	 	try {
			GetSendersList result = apiInstanceSender.getSenders(null, null);
			salida=result.getSenders();			
		} catch (ApiException e) {			
			logger.error("Error en método getSenders: ApiSendinBlue error - {}", (e.getResponseBody()!=null)?e.getResponseBody():e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ApiSendinBlue error -"+ ((e.getResponseBody()!=null)?e.getResponseBody():e.getMessage()));
		}catch (Exception e) {
			logger.error("Error en método getSenders: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
   	 	return salida;
	}
	
	/* Método para enviar correo electrónico */
	public Boolean sendEmail(Email email) {
		Boolean salida = false;
		Boolean fFile = false;
		List<SendSmtpEmailTo> emails = new ArrayList<SendSmtpEmailTo>();
		List<SendSmtpEmailCc> emailsCC = new ArrayList<SendSmtpEmailCc>();
		List<SendSmtpEmailBcc> emailsBCC = new ArrayList<SendSmtpEmailBcc>();
		List<SendSmtpEmailAttachment> attachs = new ArrayList<SendSmtpEmailAttachment>();
		Sender tmpSender=null;
		Long inicio, fin;
		Double tiempo;
		inicio = System.currentTimeMillis();
		//validaciones
		tmpSender=senderRepository.findByKsenderAndNestado(email.getSenderId(),1);
		if(tmpSender==null) {
			logger.error("Error en método sendEmail: SenderId no valido)");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"SenderId no valido");
		}
		//set ApiClient values
		TransactionalEmailsApi apiInstance;
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setConnectTimeout(TIME_OUT);
		defaultClient.setWriteTimeout(TIME_OUT);
		defaultClient.setReadTimeout(TIME_OUT);
		ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
		apiKey.setApiKey(API_KEY);
		SendSmtpEmailSender sender = new SendSmtpEmailSender();
		sender.setId(email.getSenderId());
		// Create SendSmtpEmail Object
		apiInstance = new TransactionalEmailsApi(defaultClient);
		SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
		sendSmtpEmail.sender(sender);
		//replyTo
		if (email.getReplyTo() != null) {
			SendSmtpEmailReplyTo r = new SendSmtpEmailReplyTo();
			r.setEmail(email.getReplyTo());
			sendSmtpEmail.setReplyTo(r);
		}
		// Add Emails
		if (email.getTo() != null) {
			for (String tmpTo : email.getTo()) {
				SendSmtpEmailTo w = new SendSmtpEmailTo();
				w.setEmail(tmpTo);
				emails.add(w);
			}
			sendSmtpEmail.setTo(emails);
		}
		// Add Cc
		if(email.getCc()!=null) {
			for (String tmpCC : email.getCc()) {
				SendSmtpEmailCc x = new SendSmtpEmailCc();
				x.setEmail(tmpCC);
				emailsCC.add(x);
			}
			sendSmtpEmail.setCc(emailsCC);
		}
		// Add Bcc
		if(email.getBcc()!=null) {
			for (String tmpBCC : email.getBcc()) {
				SendSmtpEmailBcc y = new SendSmtpEmailBcc();
				y.setEmail(tmpBCC);
				emailsBCC.add(y);
			}
			sendSmtpEmail.setBcc(emailsBCC);
		}
		// Add Attach's
		if (email.getFiles() != null) {
			for (EmailFile file : email.getFiles()) {
				SendSmtpEmailAttachment at = new SendSmtpEmailAttachment();
				at.setName(file.getName());
				if (file.getContentBase64() != null) {
					at.setContent(Base64.getDecoder().decode(file.getContentBase64()));
					fFile = true;
				}
				if (fFile) {
					attachs.add(at);
				}
			}
			sendSmtpEmail.setAttachment(attachs);
		}		
		sendSmtpEmail.setSubject(email.getSubject());
		sendSmtpEmail.setHtmlContent(email.getHtmlBody());
		// Add Tags ( 0 - Origen, 1- Motivo, 2- Identificador / TAG, 3-user )
		if(env.getActiveProfiles()[0].equals("dev")){
			sendSmtpEmail.addTagsItem("TEST");
		}else{
			sendSmtpEmail.addTagsItem(email.getSclave().toUpperCase().trim());
		}				
		sendSmtpEmail.addTagsItem(email.getMotivo().toString());
		sendSmtpEmail.addTagsItem(email.getTag());
		sendSmtpEmail.addTagsItem(email.getUser_reg());
		try {
			CreateSmtpEmail result=apiInstance.sendTransacEmail(sendSmtpEmail);
			logger.debug("Respuesta del Servicio (Sendinblue):  {}", result);
			salida = true;
			fin = System.currentTimeMillis();
			tiempo = (double) ((fin - inicio)/1000);
			logger.debug("Tiempo de Proceso :  {} segundos", tiempo);
		} catch (ApiException e) {   //Excepciones del API de Sendinblue
			fin = System.currentTimeMillis();
			tiempo = (double) ((fin - inicio)/1000);
			logger.debug("Tiempo de Proceso :  {} segundos", tiempo);
			logger.error("Error en método sendEmail: ApiSendinBlue error - {}", (e.getResponseBody()!=null)?e.getResponseBody():e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ApiSendinBlue error -"+ ((e.getResponseBody()!=null)?e.getResponseBody():e.getMessage()));
		}catch (Exception e2) {
			fin = System.currentTimeMillis();
			tiempo = (double) ((fin - inicio)/1000);
			logger.debug("Tiempo de Proceso :  {} segundos", tiempo);
			logger.error("Error en método sendEmail: {}", e2.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
		}finally {
			emails.clear();
			attachs.clear();
		}
		return salida;
	}
	
	/* Método para registrar la trazabilidad de un correo electrónico */
	public Boolean registerTraceability(Webhook webhook){
		Bitacora bitacora = new Bitacora();
		Date tmpDate;
		if(webhook.getTags() != null && webhook.getTags().size()== 4){
			if(webhook.getTags().get(0).equals("TEST")){
				return true;
			}
			bitacora.setSclave(webhook.getTags().get(0));
			bitacora.setKmotivo(Long.valueOf(webhook.getTags().get(1)));
			bitacora.setStag(webhook.getTags().get(2));
			bitacora.setUser_reg(webhook.getTags().get(3));
		}else{
			bitacora.setSclave("UNKNOWN");
			bitacora.setKmotivo(Long.valueOf(20));
			if (webhook.getTags()!= null && webhook.getTags().size()>=1){				
				bitacora.setStag(webhook.getTags().get(0));
			}else{
				bitacora.setStag("");
			}
			bitacora.setUser_reg("desconocido");
		}
		try {							
			bitacora.setScorreoelectronico(webhook.getEmail());
			bitacora.setSasunto(webhook.getSubject());
			bitacora.setSwebhookid(webhook.getWebhookid());
			bitacora.setSevento(webhook.getEvent());
			tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(webhook.getDate());
			bitacora.setDevento(tmpDate);
			bitacora.setSlink(webhook.getLink());
			bitacora.setSreason(webhook.getReason());			
			bitacora.setSsendingip((webhook.getSending_ip()!=null)?webhook.getSending_ip():"");
			bitacora.setSmessageid(webhook.getMessageId());
			bitacoraService.saveBitacora(bitacora);
		} catch (Exception e) {
			logger.error("Error en método registerTraceability: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return true;
	}
	
				
}
