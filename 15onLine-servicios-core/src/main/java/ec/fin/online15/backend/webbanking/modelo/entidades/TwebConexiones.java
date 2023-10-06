package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

//import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Entity
@Table(name = "TWEB_CONEXIONES")
@XmlRootElement
public class TwebConexiones extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String ip;
	private Date fechaConexion;
	private TwebUsuario usuario;
	private String sesion;
	private Canal canal;

	@Id
	@SequenceGenerator(name = "TWEBCONEXIONES_ID_GENERATOR", sequenceName = "SWEB_ID_CONEXIONES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBCONEXIONES_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "IP", length = 15, nullable = false)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CONEXION")
	public Date getFechaConexion() {
		return fechaConexion;
	}

	public void setFechaConexion(Date fechaConexion) {
		this.fechaConexion = fechaConexion;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "SESION", nullable = false, length = 100)
	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CANAL", nullable = false, length = 3)
	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

}
