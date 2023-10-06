package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;

@Stateless
@LocalBean
public class TwebRolEAO extends EAOSeed<TwebRol, Long> {

	protected Logger logger = Logger.getLogger(TwebRolEAO.class);

	protected Logger getLogger() {
		return logger;
	}

	@SuppressWarnings("unchecked")
	public List<TwebRol> listaRolesVigentes() {
		Query query = getEntityManager().createNativeQuery(
				"select * from tweb_roles where estado = 'A'", TwebRol.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebRol> listaRolesUsuarioInternos() {
		Query query = getEntityManager().createNativeQuery(
				"select * from tweb_roles where estado = 'A' and id != 3", TwebRol.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebUsuarioRol> listaRolesAsignados(TwebUsuario usuario) {
		Query query = getEntityManager().createNativeQuery(
				"select * from TWEB_usuarios_roles f where f.id_usuario = ? "
						+ " and f.estado = 'A'", TwebUsuarioRol.class);
		query.setParameter(1, usuario.getId());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebRol> listaRolesAsignar(TwebUsuario usuario) {
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from TWEB_roles a where a.id not in  ( select f.id_rol from TWEB_usuarios_roles f "
								+ " where f.id_usuario = ? and f.estado = 'A' )",
						TwebRol.class);
		query.setParameter(1, usuario.getId());
		return query.getResultList();
	}

	public void cambioEstadoRol(String estado, Integer id) {
		String lsQuery = " update TWEB_roles r set r.estado = ?, r.fecha_actualizacion = sysdate where r.id = ? ";
		Query query = getEntityManager().createNativeQuery(lsQuery);
		query.setParameter(1, estado);
		query.setParameter(2, id);
		query.executeUpdate();
	}
}
