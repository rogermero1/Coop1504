package ec.fin.online15.backend.seguridad.modelo.entidades;

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

@Entity
@Table(name = "TWEB_HISTORICO_CLAVES")
@XmlRootElement
public class TwebHistoricoClaves extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String clave;
	private TwebUsuario usuario;

	@Id
	@SequenceGenerator(name = "TWEBHISTORICOCLAVES_ID_GENERATOR", sequenceName = "SWEB_ID_HISTORICO_CLAVES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBHISTORICOCLAVES_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CLAVE", length = 32, nullable = false)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
