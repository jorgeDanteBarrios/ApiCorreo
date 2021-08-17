package mx.gda.correo.objects;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class AlternativeEmail {


	@NotEmpty(message = "La lista de destinatarios no puede ser nula")
	private List<String> to;
	@NotEmpty(message = "El campo de subjet no puede ser nulo")
	private String subject;
	@NotEmpty(message = "El campo de htmlBody no puede ser nulo")
	private String htmlBody;
	@ApiModelProperty(value = "Listado de archivos (en base 64)")
	private List<EmailFileAlt> files;
	
	public AlternativeEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlternativeEmail(@NotEmpty(message = "La lista de destinatarios no puede ser nula") List<String> to,
			@NotEmpty(message = "El campo de subjet no puede ser nulo") String subject,
			@NotEmpty(message = "El campo de htmlBody no puede ser nulo") String htmlBody, List<EmailFileAlt> files) {
		super();
		this.to = to;
		this.subject = subject;
		this.htmlBody = htmlBody;
		this.files = files;
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

	public List<EmailFileAlt> getFiles() {
		return files;
	}

	public void setFiles(List<EmailFileAlt> files) {
		this.files = files;
	}
	
	
	
}
