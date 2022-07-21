package mx.gda.correo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "c_origen", schema = "email")
public class Origen {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secOrigen")
	@SequenceGenerator(name = "secOrigen", sequenceName = "origen_sequence", schema = "email", allocationSize = 1)
	private Long korigen;
	@NotEmpty(message = "Favor de ingresar la clave de origen")
	private String sclave;
	@NotEmpty(message = "Favor de ingresar la descripción del origen")
	private String sdescripcion;

	public Origen() {
		super();
	}

	public Origen(Long korigen, @NotEmpty(message = "Favor de ingresar la clave de origen") String sclave,
			@NotEmpty(message = "Favor de ingresar la descripción del origen") String sdescripcion) {
		super();
		this.korigen = korigen;
		this.sclave = sclave;
		this.sdescripcion = sdescripcion;
	}

	public Long getKorigen() {
		return korigen;
	}

	public void setKorigen(Long korigen) {
		this.korigen = korigen;
	}

	public String getSclave() {
		return sclave;
	}

	public void setSclave(String sclave) {
		this.sclave = sclave;
	}

	public String getSdescripcion() {
		return sdescripcion;
	}

	public void setSdescripcion(String sdescripcion) {
		this.sdescripcion = sdescripcion;
	}

	@Override
	public String toString() {
		return "Origen [korigen=" + korigen + ", sclave=" + sclave + ", sdescripcion=" + sdescripcion + "]";
	}

}
