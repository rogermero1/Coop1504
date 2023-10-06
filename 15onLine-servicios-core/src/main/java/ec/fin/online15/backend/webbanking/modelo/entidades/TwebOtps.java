package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_OTPS")
@XmlRootElement
public class TwebOtps extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codigoCliente;
	private Integer idCanalElectronico;
	private String otpCorreo;
	private String otpCelular;
	private Date horaEnvio;
	private Date horaVigencia;

	@Id
	@SequenceGenerator(name = "TWEBOTPSTAB_ID_GENERATOR", sequenceName = "SWEB_ID_OTPS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBOTPSTAB_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODIGO_CLIENTE", length = 10)
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Column(name = "ID_CANAL_ELECTRONICO", length = 10)
	public Integer getIdCanalElectronico() {
		return idCanalElectronico;
	}

	public void setIdCanalElectronico(Integer idCanalElectronico) {
		this.idCanalElectronico = idCanalElectronico;
	}

	@Column(name = "OTP_CORREO", length = 6)
	public String getOtpCorreo() {
		return otpCorreo;
	}

	public void setOtpCorreo(String otpCorreo) {
		this.otpCorreo = otpCorreo;
	}

	@Column(name = "OTP_CELULAR", length = 6)
	public String getOtpCelular() {
		return otpCelular;
	}

	public void setOtpCelular(String otpCelular) {
		this.otpCelular = otpCelular;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_ENVIO")
	public Date getHoraEnvio() {
		return horaEnvio;
	}

	public void setHoraEnvio(Date horaEnvio) {
		this.horaEnvio = horaEnvio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_VIGENCIA")
	public Date getHoraVigencia() {
		return horaVigencia;
	}

	public void setHoraVigencia(Date horaVigencia) {
		this.horaVigencia = horaVigencia;
	}

}
