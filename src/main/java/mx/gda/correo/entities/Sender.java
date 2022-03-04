package mx.gda.correo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "c_sender", schema = "email")
public class Sender {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secSender")
	@SequenceGenerator(name = "secSender", sequenceName = "c_sender_seq", schema = "email", allocationSize = 1)
	private Long ksender;
	@NotNull(message = "El campo ssender no puede ser nulo o vacio")
	private String ssender;
	private Long ncmarca;
	@NotNull(message = "El campo semail no puede ser nulo o vacio")
	private String semail;
	private String sreplyto;
	private String sobservaciones;
	private Integer nestado;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dreg;
	@NotNull(message = "El campo user_reg no puede ser nulo o vacio")
	private String user_reg;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dmod;
	private String user_mod;

	public Sender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sender(Long ksender, @NotEmpty(message = "El campo ssender no puede ser nulo o vacio") String ssender,
			Long ncmarca, @NotEmpty(message = "El campo semail no puede ser nulo o vacio") String semail,
			String sreplyto, String sobservaciones, Integer nestado, Date dreg, String user_reg, Date dmod,
			String user_mod) {
		super();
		this.ksender = ksender;
		this.ssender = ssender;
		this.ncmarca = ncmarca;
		this.semail = semail;
		this.sreplyto = sreplyto;
		this.sobservaciones = sobservaciones;
		this.nestado = nestado;
		this.dreg = dreg;
		this.user_reg = user_reg;
		this.dmod = dmod;
		this.user_mod = user_mod;
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

	public Integer getNestado() {
		return nestado;
	}

	public void setNestado(Integer nestado) {
		this.nestado = nestado;
	}

	public Date getDreg() {
		return dreg;
	}

	public void setDreg(Date dreg) {
		this.dreg = dreg;
	}

	public String getUser_reg() {
		return user_reg;
	}

	public void setUser_reg(String user_reg) {
		this.user_reg = user_reg;
	}

	public Date getDmod() {
		return dmod;
	}

	public void setDmod(Date dmod) {
		this.dmod = dmod;
	}

	public String getUser_mod() {
		return user_mod;
	}

	public void setUser_mod(String user_mod) {
		this.user_mod = user_mod;
	}

	@Override
	public String toString() {
		return "Sender [ksender=" + ksender + ", ssender=" + ssender + ", ncmarca=" + ncmarca + ", semail=" + semail
				+ ", sreplyto=" + sreplyto + ", sobservaciones=" + sobservaciones + ", nestado=" + nestado + ", dreg="
				+ dreg + ", user_reg=" + user_reg + ", dmod=" + dmod + ", user_mod=" + user_mod + "]";
	}

}
