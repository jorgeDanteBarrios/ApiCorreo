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
@Table(name = "t_plantilla", schema = "email")
public class Plantilla {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secPlantilla")
	@SequenceGenerator(name = "secPlantilla", sequenceName = "t_plantilla_seq", schema = "email", allocationSize = 1)
	private Long kplantilla;
	@NotEmpty(message = "El campo sclave no puede ser nulo o vacio")
	private String sclave;
	@NotNull(message = "El campo ncmarca no puede ser nulo o vacio")
	private Long ncmarca;
	@NotNull(message = "El campo ncmarca no puede ser nulo o vacio")
	private Long ksender;
	@NotEmpty(message = "El campo sasunto no puede ser nulo o vacio")
	private String sasunto;
	@NotEmpty(message = "El campo splantilla no puede ser nulo o vacio")
	private String splantilla;
	private String sdescripcion;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dreg;
	@NotNull(message = "El campo user_reg no puede ser nulo o vacio")
	private String user_reg;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dmod;
	private String user_mod;

	public Plantilla() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plantilla(Long kplantilla, @NotEmpty(message = "El campo sclave no puede ser nulo o vacio") String sclave,
			@NotNull(message = "El campo ncmarca no puede ser nulo o vacio") Long ncmarca,
			@NotNull(message = "El campo ncmarca no puede ser nulo o vacio") Long ksender,
			@NotEmpty(message = "El campo sasunto no puede ser nulo o vacio") String sasunto,
			@NotEmpty(message = "El campo splantilla no puede ser nulo o vacio") String splantilla, String sdescripcion,
			Date dreg, @NotNull(message = "El campo user_reg no puede ser nulo o vacio") String user_reg, Date dmod,
			String user_mod) {
		super();
		this.kplantilla = kplantilla;
		this.sclave = sclave;
		this.ncmarca = ncmarca;
		this.ksender = ksender;
		this.sasunto = sasunto;
		this.splantilla = splantilla;
		this.sdescripcion = sdescripcion;
		this.dreg = dreg;
		this.user_reg = user_reg;
		this.dmod = dmod;
		this.user_mod = user_mod;
	}

	public Long getKplantilla() {
		return kplantilla;
	}

	public void setKplantilla(Long kplantilla) {
		this.kplantilla = kplantilla;
	}

	public String getSclave() {
		return sclave;
	}

	public void setSclave(String sclave) {
		this.sclave = sclave;
	}

	public Long getNcmarca() {
		return ncmarca;
	}

	public void setNcmarca(Long ncmarca) {
		this.ncmarca = ncmarca;
	}

	public Long getKsender() {
		return ksender;
	}

	public void setKsender(Long ksender) {
		this.ksender = ksender;
	}

	public String getSasunto() {
		return sasunto;
	}

	public void setSasunto(String sasunto) {
		this.sasunto = sasunto;
	}

	public String getSplantilla() {
		return splantilla;
	}

	public void setSplantilla(String splantilla) {
		this.splantilla = splantilla;
	}

	public String getSdescripcion() {
		return sdescripcion;
	}

	public void setSdescripcion(String sdescripcion) {
		this.sdescripcion = sdescripcion;
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
		return "Plantilla [kplantilla=" + kplantilla + ", sclave=" + sclave + ", ncmarca=" + ncmarca + ", ksender="
				+ ksender + ", sasunto=" + sasunto + ", splantilla=" + splantilla + ", sdescripcion=" + sdescripcion
				+ ", dreg=" + dreg + ", user_reg=" + user_reg + ", dmod=" + dmod + ", user_mod=" + user_mod + "]";
	}

}
