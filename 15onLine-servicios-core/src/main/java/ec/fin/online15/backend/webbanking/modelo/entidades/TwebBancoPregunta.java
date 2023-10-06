package ec.fin.online15.backend.webbanking.modelo.entidades;

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
@Table(name = "TWEB_BANCO_PREGUNTAS")
@XmlRootElement
public class TwebBancoPregunta extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private List<TwebRespuestasUsuarios> twebRespuestasUsuarios;

	private Long id;
	private String descripcion;

	public TwebBancoPregunta() {
	}

	public TwebBancoPregunta(Long idBancoPregunta, String descripcion) {
		super();
		this.id = idBancoPregunta;
		this.descripcion = descripcion;
	}

	public TwebBancoPregunta(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	@Id
	@SequenceGenerator(name = "BANCOPREGUNTASTAB_ID_GENERATOR", sequenceName = "SWEB_ID_BANCO_PREGUNTAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANCOPREGUNTASTAB_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlTransient
	@OneToMany(mappedBy = "bancoPregunta")
	public List<TwebRespuestasUsuarios> getRespuestasUsuarios() {
		return twebRespuestasUsuarios;
	}

	public void setRespuestasUsuarios(
			List<TwebRespuestasUsuarios> twebRespuestasUsuarios) {
		this.twebRespuestasUsuarios = twebRespuestasUsuarios;
	}

}
