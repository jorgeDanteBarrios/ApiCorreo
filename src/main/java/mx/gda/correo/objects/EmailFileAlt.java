package mx.gda.correo.objects;

import javax.validation.constraints.NotEmpty;

public class EmailFileAlt {
	
	@NotEmpty(message = "El campo de name no puede ser nulo")
	private String name;
	private String contentBase64;
	private String type;
	
	public EmailFileAlt() {
		super();
	}

	public EmailFileAlt(@NotEmpty(message = "El campo de name no puede ser nulo") String name, String contentBase64,
			String type) {
		super();
		this.name = name;
		this.contentBase64 = contentBase64;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
}
