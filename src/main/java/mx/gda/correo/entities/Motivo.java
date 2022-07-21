package mx.gda.correo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "c_motivo", schema = "email")
public class Motivo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secMotivo")
	@SequenceGenerator(name = "secMotivo", sequenceName = "motivo_sequence", schema = "email", allocationSize = 1)
	private Long kmotivo;
	@NotEmpty(message = "Favor de ingresar la descripción del motivo")
	private String sdescripcion;
	@NotEmpty(message = "Favor de ingresar la clave de origen")
	private String korigen;

	public Motivo() {
		super();
	}

	public Motivo(Long kmotivo, String sdescripcion, String korigen) {
		super();
		this.kmotivo = kmotivo;
		this.sdescripcion = sdescripcion;
		this.korigen = korigen;
	}

	public Long getKmotivo() {
		return kmotivo;
	}

	public void setKmotivo(Long kmotivo) {
		this.kmotivo = kmotivo;
	}

	public String getSdescripcion() {
		return sdescripcion;
	}

	public void setSdescripcion(String sdescripcion) {
		this.sdescripcion = sdescripcion;
	}

	public String getKorigen() {
		return korigen;
	}

	public void setKorigen(String korigen) {
		this.korigen = korigen;
	}

	@Override
	public String toString() {
		return "Motivo [kmotivo=" + kmotivo + ", sdescripcion=" + sdescripcion + ", korigen=" + korigen + "]";
	}

}
