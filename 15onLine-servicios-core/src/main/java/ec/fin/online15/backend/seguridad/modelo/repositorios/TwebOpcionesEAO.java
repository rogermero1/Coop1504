package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;

@Stateless
@LocalBean
public class TwebOpcionesEAO extends EAOSeed<TwebOpcion, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebOpcion> listaOpcionesPadreUsuario(String usuario,
			String orientacion) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT *  FROM TWEB_OPCIONES D WHERE EXISTS (SELECT 'X' FROM TWEB_OPCIONES_ROLES S        "
								+ "WHERE EXISTS (SELECT 'X' FROM TWEB_USUARIOS_ROLES T WHERE T.ID_USUARIO = (select r.id "
								+ "from tweb_usuarios r "
								+ "where upper(r.usuario) ="
								+ "upper(?)) "
								+ "AND T.ESTADO = 'A' AND T.ID_ROL = S.ID_ROL) AND S.ESTADO = 'A' AND S.ID_OPCION = D.ID) AND D.MODULO_PADRE IS NULL AND D.ESTADO = 'A' "
								+ "AND upper(D.ORIENTACION) = upper(?) ORDER BY D.ORDEN ASC",
						TwebOpcion.class);
		query.setParameter(1, usuario);
		query.setParameter(2, orientacion);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebOpcion> listaOpcionesHijas(Long idOpcion, String usuario,
			String orientacion) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT *  FROM TWEB_OPCIONES D WHERE EXISTS (SELECT 'X' FROM TWEB_OPCIONES_ROLES S        "
								+ "WHERE EXISTS (SELECT 'X' FROM TWEB_USUARIOS_ROLES T WHERE T.ID_USUARIO = (select r.id "
								+ "from tweb_usuarios r "
								+ "where upper(r.usuario) ="
								+ "upper(?)) "
								+ "AND T.ESTADO = 'A' AND T.ID_ROL = S.ID_ROL) AND S.ESTADO = 'A' AND S.ID_OPCION = D.ID) "
								+ "AND D.MODULO_PADRE = ? AND D.ESTADO = 'A' "
								+ "AND upper(D.ORIENTACION) = upper(?) ORDER BY D.ORDEN ASC",
						TwebOpcion.class);
		query.setParameter(1, usuario);
		query.setParameter(2, idOpcion);
		query.setParameter(3, orientacion);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebOpcion> listaOpcionesVigentes() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.*  FROM TWEB_OPCIONES a where a.estado = 'A' order by orden, modulo_padre",
						TwebOpcion.class);
		return query.getResultList();
	}

	/*
	 * public void inactivarOpcion(TwebOpcion opcion) { String lsQuery =
	 * " update tweb_opciones b " +
	 * " set b.estado = 'I', b.fecha_actualizacion = sysdate " +
	 * " where b.id = ?"; Query query =
	 * getEntityManager().createNativeQuery(lsQuery); query.setParameter(1,
	 * opcion.getId()); query.executeUpdate(); }
	 */

}
