package ec.fin.online15.backend.seguridad.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;

@Entity
@Table(name = "TWEB_TIPOS_TRANSACCION")
@XmlRootElement
public class TwebTiposTransaccion extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private List<TwebOperacionesFrecuentes> operacionesFrecuentes;

	@Id
	@SequenceGenerator(name = "TWEBTIPOSTRANSACCION_ID_GENERATOR", sequenceName = "SWEB_ID_TIPOS_TRANSACCION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBTIPOSTRANSACCION_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlTransient
	@OneToMany(mappedBy = "tipoTransaccion")
	public List<TwebOperacionesFrecuentes> getOperacionesFrecuentes() {
		return operacionesFrecuentes;
	}

	public void setOperacionesFrecuentes(
			List<TwebOperacionesFrecuentes> operacionesFrecuentes) {
		this.operacionesFrecuentes = operacionesFrecuentes;
	}

}
