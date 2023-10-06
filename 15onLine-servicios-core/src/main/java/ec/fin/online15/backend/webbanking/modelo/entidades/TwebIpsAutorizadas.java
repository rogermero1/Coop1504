package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Entity
@Table(name = "TWEB_IPS_AUTORIZADAS")
@XmlRootElement
public class TwebIpsAutorizadas extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String ip;
	private TwebUsuario usuario;

	@Id
	@SequenceGenerator(name = "TWEBIPS_ID_GENERATOR", sequenceName = "SWEB_ID_IPS_AUTORIZADAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBIPS_ID_GENERATOR")
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

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

}
