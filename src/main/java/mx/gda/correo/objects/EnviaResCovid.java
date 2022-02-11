package mx.gda.correo.objects;

import java.util.List;

import javax.validation.constraints.NotNull;

public class EnviaResCovid {

	@NotNull(message = "El campo kordensucursal no puede ser nulo")
	private String kordensucursal;
	@NotNull(message = "El campo to no puede ser nulo")
	private List<String> to;
	@NotNull(message = "El campo cmarca no puede ser nulo")
	private Long cmarca;
	@NotNull(message = "El campo files no puede ser nulo")
	private List<EmailFile> files;
	@NotNull(message = "El campo paciente no puede ser nulo")
	private String paciente;

	public EnviaResCovid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnviaResCovid(@NotNull(message = "El campo kordensucursal no puede ser nulo") String kordensucursal,
			@NotNull(message = "El campo to no puede ser nulo") List<String> to,
			@NotNull(message = "El campo cmarca no puede ser nulo") Long cmarca,
			@NotNull(message = "El campo files no puede ser nulo") List<EmailFile> files,
			@NotNull(message = "El campo paciente no puede ser nulo") String paciente) {
		super();
		this.kordensucursal = kordensucursal;
		this.to = to;
		this.cmarca = cmarca;
		this.files = files;
		this.paciente = paciente;
	}

	public String getKordensucursal() {
		return kordensucursal;
	}

	public void setKordensucursal(String kordensucursal) {
		this.kordensucursal = kordensucursal;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public Long getCmarca() {
		return cmarca;
	}

	public void setCmarca(Long cmarca) {
		this.cmarca = cmarca;
	}

	public List<EmailFile> getFiles() {
		return files;
	}

	public void setFiles(List<EmailFile> files) {
		this.files = files;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return "EnviaResCovid [kordensucursal=" + kordensucursal + ", to=" + to + ", cmarca=" + cmarca + ", files="
				+ files + ", paciente=" + paciente + "]";
	}

}
