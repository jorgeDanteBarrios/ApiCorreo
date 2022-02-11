package mx.gda.correo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "c_sender", schema = "email")
public class Sender {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secSender")
	@SequenceGenerator(name = "secSender",sequenceName = "c_sender_seq",schema = "email",allocationSize=1)
	private Long ksender;
	@NotEmpty(message = "El campo ssender no puede ser nulo o vacio")
	private String ssender;
	private Long ncmarca;
	@NotEmpty(message = "El campo semail no puede ser nulo o vacio")
	private String semail;
	private String sreplyto;
	private String sobservaciones;

	public Sender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sender(Long ksender, String ssender, Long ncmarca, String semail, String sreplyto, String sobservaciones) {
		super();
		this.ksender = ksender;
		this.ssender = ssender;
		this.ncmarca = ncmarca;
		this.semail = semail;
		this.sreplyto = sreplyto;
		this.sobservaciones = sobservaciones;
	}

	public Long getKsender() {
		return ksender;
	}

	public void setKsender(Long ksender) {
		this.ksender = ksender;
	}

	public String getSsender() {
		return ssender;
	}

	public void setSsender(String ssender) {
		this.ssender = ssender;
	}

	public Long getNcmarca() {
		return ncmarca;
	}

	public void setNcmarca(Long ncmarca) {
		this.ncmarca = ncmarca;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSreplyto() {
		return sreplyto;
	}

	public void setSreplyto(String sreplyto) {
		this.sreplyto = sreplyto;
	}

	public String getSobservaciones() {
		return sobservaciones;
	}

	public void setSobservaciones(String sobservaciones) {
		this.sobservaciones = sobservaciones;
	}

}
