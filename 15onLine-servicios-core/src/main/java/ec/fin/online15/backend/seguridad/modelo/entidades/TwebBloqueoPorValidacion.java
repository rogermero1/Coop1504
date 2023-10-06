package ec.fin.online15.backend.seguridad.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_BLOQUEOS_X_VALIDACION")
@XmlRootElement
public class TwebBloqueoPorValidacion extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codigoCliente;
	private Integer intentos;
	private String ip;

	@Id
	@SequenceGenerator(name = "TWEBBLOQUEOSXVALIDACION_ID_GENERATOR", sequenceName = "SWEB_ID_BLOQUEOS_X_VALIDACION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBBLOQUEOSXVALIDACION_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODIGO_CLIENTE", length = 10, nullable = false)
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Column(name = "INTENTOS", length = 2, nullable = false)
	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	@Column(name = "IP", length = 15, nullable = false)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
