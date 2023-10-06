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

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_ESTADOS")
@XmlRootElement
public class TwebEstados extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigoEstado;
	private String descripcion;

	@Id
	@SequenceGenerator(name = "objGenIDEstado", sequenceName = "SWEB_ID_ESTADO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDEstado", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODIGO_ESTADO")
	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
