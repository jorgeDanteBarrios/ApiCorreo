package mx.gda.correo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.gda.correo.entities.Plantilla;
import mx.gda.correo.entities.Sender;
import mx.gda.correo.interfaces.PlantillaRepository;
import mx.gda.correo.interfaces.SenderRepository;
import mx.gda.correo.objects.Email;
import mx.gda.correo.objects.EnviaResCovid;

@Service
public class CorreoResultadosService {

	Logger logger= LoggerFactory.getLogger(CorreoResultadosService.class);
	
	@Value("${resultados.covid.clave.plantilla}")
	private String CLAVE_PLANTILLA_RESULTADOS_COVID;
	@Value("${resultados.covid.clave}")
	private String CLAVE_RESULTADOS_COVID;
	@Value("${resultados.covid.motivo}")
	private Long MOTIVO_RESULTADOS_COVID;
	
	@Autowired
	private PlantillaRepository plantillaRepository;
	@Autowired
	private SenderRepository senderRepository;
	@Autowired
	private CorreoService correoService;
	
	/* Método para enviar resultados de pruebas rápidas Covid */
	public Boolean sendResultadoCovid(EnviaResCovid enviaResCovid) {
		Boolean salida=false;
		Email tmpEmail=new Email();
		Plantilla tmpPlantilla=null;
		Optional<Sender> tmpSender=null;
		tmpPlantilla=plantillaRepository.findByNcmarcaAndSclave(enviaResCovid.getCmarca(), CLAVE_PLANTILLA_RESULTADOS_COVID);
		//validaciones
		if(tmpPlantilla==null) {
			logger.error("Error en updateMotivo: El kmotivo  no existe {}","No existe una plantilla (resultados covid) para la marca");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No existe una plantilla (resultados covid) para la marca");
		}
		//set values
		tmpSender=senderRepository.findById(tmpPlantilla.getKsender());
		tmpEmail.setSenderId(tmpPlantilla.getKsender());		
		if(tmpSender.get().getSreplyto()!=null) {
			tmpEmail.setReplyTo(tmpSender.get().getSreplyto());
		}
		tmpEmail.setTo(enviaResCovid.getTo());
		if(enviaResCovid.getCc()!=null) {
			tmpEmail.setCc(enviaResCovid.getCc());
		}
		if(enviaResCovid.getBcc()!=null) {
			tmpEmail.setBcc(enviaResCovid.getBcc());
		}
		tmpEmail.setSubject(tmpPlantilla.getSasunto());
		tmpEmail.setHtmlBody(tmpPlantilla.getSplantilla().replace("{px}", enviaResCovid.getPaciente().toUpperCase().trim()));
		tmpEmail.setFiles(enviaResCovid.getFiles());
		tmpEmail.setSclave(CLAVE_RESULTADOS_COVID);
		tmpEmail.setMotivo(MOTIVO_RESULTADOS_COVID);		
		tmpEmail.setTag(enviaResCovid.getKordensucursal());
		tmpEmail.setUser_reg("system");
		salida= correoService.sendEmail(tmpEmail);			
		return salida;
	}
	
	
}
