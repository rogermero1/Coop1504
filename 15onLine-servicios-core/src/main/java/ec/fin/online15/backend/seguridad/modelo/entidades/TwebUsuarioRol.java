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
@Table(name = "TWEB_USUARIOS_ROLES")
@XmlRootElement
public class TwebUsuarioRol extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private TwebUsuario usuario;
	private TwebRol rol;
	private String descripcion;

	private Long id;

	@Id
	@SequenceGenerator(name = "objGenIDUsuarioROL", sequenceName = "SWEB_ID_USUARIO_ROL", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDUsuarioROL", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public TwebRol getRol() {
		return rol;
	}

	public void setRol(TwebRol rol) {
		this.rol = rol;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
