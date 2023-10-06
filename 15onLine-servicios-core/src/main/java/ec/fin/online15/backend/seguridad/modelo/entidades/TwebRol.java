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

@Entity
@Table(name = "TWEB_ROLES")
@XmlRootElement
public class TwebRol extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TwebUsuarioRol> usuariosRoles;
	private List<TwebOpcionRol> opcionesRoles;

	private String rol;

	private Long id;

	@Id
	@SequenceGenerator(name = "objGenIDRol", sequenceName = "SWEB_ID_ROL", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDRol", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ROL")
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@XmlTransient
	@OneToMany(mappedBy = "rol")
	public List<TwebUsuarioRol> getUsuariosRoles() {
		return usuariosRoles;
	}

	public void setUsuariosRoles(List<TwebUsuarioRol> usuariosRoles) {
		this.usuariosRoles = usuariosRoles;
	}

	@XmlTransient
	@OneToMany(mappedBy = "rol")
	public List<TwebOpcionRol> getOpcionesRoles() {
		return opcionesRoles;
	}

	public void setOpcionesRoles(List<TwebOpcionRol> opcionesRoles) {
		this.opcionesRoles = opcionesRoles;
	}

	public String toString() {
		return this.rol;
	}

}
