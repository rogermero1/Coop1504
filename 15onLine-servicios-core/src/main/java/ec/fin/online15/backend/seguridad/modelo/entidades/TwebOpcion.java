package ec.fin.online15.backend.seguridad.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_OPCIONES")
@XmlRootElement
public class TwebOpcion extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer orden;
	private String tipo;// (P pagina, D poppup);
	private String opcion;
	private String descripcion;
	private String accion;
	private TwebOpcion opcionPadre;
	private List<TwebOpcion> opcionesHijas;
	private List<TwebOpcionRol> opcionesRoles;
	private String rutaImagen;
	private String orientacion;
	private Long id;
	private String validaOnLine;

	@Id
	@SequenceGenerator(name = "objGenIDOpcion", sequenceName = "SWEB_ID_OPCION", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDOpcion", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OPCION")
	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "ACCION")
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@ManyToOne
	@JoinColumn(name = "MODULO_PADRE")
	public TwebOpcion getOpcionPadre() {
		return opcionPadre;
	}

	public void setOpcionPadre(TwebOpcion opcionPadre) {
		this.opcionPadre = opcionPadre;
	}

	@XmlTransient
	@OneToMany(mappedBy = "opciones")
	public List<TwebOpcionRol> getOpcionesRoles() {
		return opcionesRoles;
	}

	public void setOpcionesRoles(List<TwebOpcionRol> opcionesRoles) {
		this.opcionesRoles = opcionesRoles;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	@XmlTransient
	@OneToMany(mappedBy = "opcionPadre")
	public List<TwebOpcion> getOpcionesHijas() {
		return opcionesHijas;
	}

	public void setOpcionesHijas(List<TwebOpcion> opcionesHijas) {
		this.opcionesHijas = opcionesHijas;
	}

	@Column(name = "ORDEN")
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Column(name = "TIPO")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "VALIDA_ON_LINE", length = 1)
	public String getValidaOnLine() {
		return validaOnLine;
	}

	public void setValidaOnLine(String validaOnLine) {
		this.validaOnLine = validaOnLine;
	}

}
