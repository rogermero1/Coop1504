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

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_TIPOS_BLOQUEOS")
@XmlRootElement
public class TwebTiposBloqueo extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private List<TwebUsuario> usuarios;

	private Long id;
	private String descripcion;

	@Id
	@SequenceGenerator(name = "objGenIDTipoBloqueo", sequenceName = "SWEB_ID_TIPOS_BLOQUEOS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDTipoBloqueo", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(mappedBy = "tiposBloqueo")
	public List<TwebUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<TwebUsuario> usuario) {
		this.usuarios = usuario;
	}

}
