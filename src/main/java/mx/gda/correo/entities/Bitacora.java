package mx.gda.correo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_bitacora",schema = "email")
public class Bitacora {
	
	@Id
	@GenericGenerator(name="custom_emp", strategy = "mx.gda.correo.sequences.CustomIDGenerator")
	@GeneratedValue(generator = "custom_emp")
	private String kemail;
	private String sclave;
	private Long kmotivo ;
	private String stag;
	private String scorreoelectronico;
	private String sasunto;
	private String swebhookid ;
	private String sevento;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss",timezone = "America/Mexico_City")
	@Temporal(TemporalType.TIMESTAMP)
	private Date devento;
	private String user_reg;
	private String slink;
	private String sreason;
	private String ssendingip;
	private String smessageid;
	
	public Bitacora() {
		super();
	}

	public Bitacora(String kemail, String sclave, Long kmotivo, String stag, String scorreoelectronico, String sasunto,
			String swebhookid, String sevento, Date devento, String user_reg, String slink, String sreason,
			String ssendingip, String smessageid) {
		super();
		this.kemail = kemail;
		this.sclave = sclave;
		this.kmotivo = kmotivo;
		this.stag = stag;
		this.scorreoelectronico = scorreoelectronico;
		this.sasunto = sasunto;
		this.swebhookid = swebhookid;
		this.sevento = sevento;
		this.devento = devento;
		this.user_reg = user_reg;
		this.slink = slink;
		this.sreason = sreason;
		this.ssendingip = ssendingip;
		this.smessageid = smessageid;
	}

	public String getKemail() {
		return kemail;
	}

	public void setKemail(String kemail) {
		this.kemail = kemail;
	}

	public String getSclave() {
		return sclave;
	}

	public void setSclave(String sclave) {
		this.sclave = sclave;
	}

	public Long getKmotivo() {
		return kmotivo;
	}

	public void setKmotivo(Long kmotivo) {
		this.kmotivo = kmotivo;
	}

	public String getStag() {
		return stag;
	}

	public void setStag(String stag) {
		this.stag = stag;
	}

	public String getScorreoelectronico() {
		return scorreoelectronico;
	}

	public void setScorreoelectronico(String scorreoelectronico) {
		this.scorreoelectronico = scorreoelectronico;
	}

	public String getSasunto() {
		return sasunto;
	}

	public void setSasunto(String sasunto) {
		this.sasunto = sasunto;
	}

	public String getSwebhookid() {
		return swebhookid;
	}

	public void setSwebhookid(String swebhookid) {
		this.swebhookid = swebhookid;
	}

	public String getSevento() {
		return sevento;
	}

	public void setSevento(String sevento) {
		this.sevento = sevento;
	}

	public Date getDevento() {
		return devento;
	}

	public void setDevento(Date devento) {
		this.devento = devento;
	}

	public String getUser_reg() {
		return user_reg;
	}

	public void setUser_reg(String user_reg) {
		this.user_reg = user_reg;
	}

	public String getSlink() {
		return slink;
	}

	public void setSlink(String slink) {
		this.slink = slink;
	}

	public String getSreason() {
		return sreason;
	}

	public void setSreason(String sreason) {
		this.sreason = sreason;
	}

	public String getSsendingip() {
		return ssendingip;
	}

	public void setSsendingip(String ssendingip) {
		this.ssendingip = ssendingip;
	}

	public String getSmessageid() {
		return smessageid;
	}

	public void setSmessageid(String smessageid) {
		this.smessageid = smessageid;
	}

	
	

}
