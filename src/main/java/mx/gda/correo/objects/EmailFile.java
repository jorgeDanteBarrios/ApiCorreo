package mx.gda.correo.objects;


import javax.validation.constraints.NotEmpty;

public class EmailFile {
	
	@NotEmpty(message = "El campo de name no puede ser nulo")
	private String name;
	private String contentBase64;
	
	public EmailFile() {
		super();	
	}

	public EmailFile(String name, String contentBase64) {
		this.name = name;
		this.contentBase64 = contentBase64;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentBase64() {
		return contentBase64;
	}

	public void setContentBase64(String contentBase64) {
		this.contentBase64 = contentBase64;
	}


	
	
	
	
}
