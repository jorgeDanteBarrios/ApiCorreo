package mx.gda.correo.objects;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class Email {

	@Min(value = 1)
	@Schema(description =  "Identificador de la cuenta origen (sender)")
	private Long senderId;
	@Schema(description =  "Dirección a la cual se indicará se debe responder")
	private String replyTo;
	@NotNull(message = "La lista de destinatarios no puede ser nula")
	@Schema(description =  "Email's a los cuales esta digirigo el correo")
	private List<String> to;
	@Schema(description =  "Email's a los cuales se realizará una copia del correo")
	private List<String> cc;
	@Schema(description =  "Email's a los cuales se realizará una copia oculta del correo")
	private List<String> bcc;
	@NotEmpty(message = "El campo de asunto/subjet no puede ser nulo")
	private String subject;
	@NotEmpty(message = "El campo de htmlBody no puede ser nulo")
	private String htmlBody;
	@Schema(description =  "Archivo(s) en base 64 a adjuntar en el correo, maximo 10MB")
	private List<EmailFile> files;
	@NotEmpty(message = "Favor de ingresar la clave del origen del correo")
	@Schema(description =  "Identificador del origen del correo")
	private String sclave;
	@Min(value = 1, message = "Favor de ingresar un motivo del catálogo de motivos")
	@Schema(description =  "Identificador del motivo del correo")
	private Long motivo;
	@NotEmpty(message = "Se debe utilizar un identificador para poder tener trazabilidad del correo")
	@Schema(description =  "Identificador único para poder tener trazabilidad del correo (#Orden,#Px,#Médico,etc.)", example = "123456")
	private String tag;
	@Schema(description =  "Usuario que solicita la petición de envio del correo")
	private String user_reg;

	public Email() {
		super();
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUser_reg() {
		return user_reg;
	}

	public void setUser_reg(String user_reg) {
		this.user_reg = user_reg;
	}

	@Override
	public String toString() {
		return "Email [senderId=" + senderId + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc
				+ ", subject=" + subject + ", htmlBody=" + htmlBody + ", files=" + files + ", sclave=" + sclave
				+ ", motivo=" + motivo + ", tag=" + tag + ", user_reg=" + user_reg + "]";
	}

}
