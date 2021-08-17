package mx.gda.correo.objects;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class Email {
	
	@NotEmpty(message = "Se debe utilizar un identificador para poder tener trazabilidad del correo")
	@ApiModelProperty(value = "Identificador único para poder tener trazabilidad del correo (#Orden,#Px,#Médico,etc.)",example = "t_123456")
	private String tag;
	@Min(value = 1)
	@ApiModelProperty(value = "Identificador de la cuenta origen (sender)")
	private Long senderId;
	@NotEmpty(message = "La lista de destinatarios no puede ser nula")
	private List<String> to;
	@NotEmpty(message = "El campo de subjet no puede ser nulo")
	private String subject;
	@NotEmpty(message = "El campo de htmlBody no puede ser nulo")
	private String htmlBody;
	@ApiModelProperty(value = "Dirección a la cual se indicará se debe responder")
	private String replyTo;
	@ApiModelProperty(value = "Dirección a la cual se realizará una copia oculta del correo")
	private String bcc;
	private List<EmailFile> files;
	@NotEmpty(message = "Favor de ingresar la clave del origen del correo")
	@ApiModelProperty(value = "Identificador del origen del correo")
	private String sclave;
	@Min(value = 1,message = "Favor de ingresar un motivo del catálogo de motivos")
	private Long motivo;
	@ApiModelProperty(value = "Usuario que solicita la petición de envio del correo")
	private String user_reg;
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(
			@NotEmpty(message = "Se debe utilizar un identificador para poder tener trazabilidad del correo") String tag,
			@Min(1) Long senderId, @NotEmpty(message = "La lista de destinatarios no puede ser nula") List<String> to,
			@NotEmpty(message = "El campo de subjet no puede ser nulo") String subject,
			@NotEmpty(message = "El campo de htmlBody no puede ser nulo") String htmlBody, String replyTo, String bcc,
			List<EmailFile> files,
			@NotEmpty(message = "Favor de ingresar la clave del origen del correo") String sclave,
			@NotEmpty(message = "Favor de ingresar un valor del catálogo de motivos") Long motivo, String user_reg) {
		super();
		this.tag = tag;
		this.senderId = senderId;
		this.to = to;
		this.subject = subject;
		this.htmlBody = htmlBody;
		this.replyTo = replyTo;
		this.bcc = bcc;
		this.files = files;
		this.sclave = sclave;
		this.motivo = motivo;
		this.user_reg = user_reg;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public List<EmailFile> getFiles() {
		return files;
	}

	public void setFiles(List<EmailFile> files) {
		this.files = files;
	}

	public String getSclave() {
		return sclave;
	}

	public void setSclave(String sclave) {
		this.sclave = sclave;
	}

	public Long getMotivo() {
		return motivo;
	}

	public void setMotivo(Long motivo) {
		this.motivo = motivo;
	}

	public String getUser_reg() {
		return user_reg;
	}

	public void setUser_reg(String user_reg) {
		this.user_reg = user_reg;
	}


	
	
	
}
