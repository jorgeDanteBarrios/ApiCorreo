package mx.gda.correo.objects;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

public class EmailFile {
	
	@NotEmpty(message = "El campo de name no puede ser nulo")
	private String name;
	private String contentBase64;
	
	public EmailFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailFile(@NotEmpty(message = "El campo de name no puede ser nulo") String name, String contentBase64,
			Optional<byte[]> content) {
		super();
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
