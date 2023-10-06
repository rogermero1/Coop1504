package ec.fin.online15.backend.seguridad.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_OPCIONES_ROLES")
@NamedNativeQueries(@NamedNativeQuery(name = "TWEB_OPCIONES_ROLES.OPCIONES_PADRE", query = "SELECT * "
		+ " FROM TWEB_OPCIONES D "
		+ " WHERE EXISTS (SELECT 'X' "
		+ "        FROM TWEB_OPCIONES_ROLES S "
		+ "       WHERE EXISTS (SELECT 'X' "
		+ "                FROM TWEB_USUARIOS_ROLES T "
		+ "               WHERE T.ID_USUARIO = ? "
		+ "                 AND T.ESTADO = 'A' "
		+ "                 AND T.ID_ROL = S.ID_ROL) "
		+ "         AND S.ESTADO = 'A' "
		+ "         AND S.ID_OPCION = D.ID) "
		+ " AND D.MODULO_PADRE IS NULL "
		+ " AND D.ESTADO = 'A'"
		+ " AND D.ORIENTACION = ? ORDER BY D.ORDEN ASC", resultClass = TwebOpcion.class))
@XmlRootElement
public class TwebOpcionRol extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	private TwebRol rol;
	private TwebOpcion opciones;

	private Long id;

	@Id
	@SequenceGenerator(name = "objGenIDOpcionRol", sequenceName = "SWEB_ID_OPCION_ROL", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDOpcionRol", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public TwebRol getRol() {
		return rol;
	}

	public void setRol(TwebRol rol) {
		this.rol = rol;
	}

	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public TwebOpcion getOpciones() {
		return opciones;
	}

	public void setOpciones(TwebOpcion opciones) {
		this.opciones = opciones;
	}

}
