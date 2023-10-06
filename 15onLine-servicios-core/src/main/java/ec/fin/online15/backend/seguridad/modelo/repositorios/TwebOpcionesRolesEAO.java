package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcionRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Stateless
@LocalBean
public class TwebOpcionesRolesEAO extends EAOSeed<TwebOpcionRol, Long> {

	public static final String OPCIONES_PADRE = "TWEB_OPCIONES_ROLES.OPCIONES_PADRE";

	@SuppressWarnings("unchecked")
	public List<TwebOpcion> obtenerListaOpcionesRol(TwebUsuario usuario,
			String orientacion) {
		Query query = getEntityManager().createNamedQuery(OPCIONES_PADRE);
		query.setParameter(1, usuario.getId());
		query.setParameter(2, orientacion);
		return query.getResultList();
	}

	public void inactivarOpcionesRol(TwebRol rol) {
		String lsQuery = " update TWEB_opciones_roles b "
				+ " set b.estado = 'I', b.fecha_actualizacion = sysdate "
				+ " where b.id_rol = ?";
		Query query = getEntityManager().createNativeQuery(lsQuery);
		query.setParameter(1, rol.getId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TwebOpcionRol> listaOpcionesRolesVigentes() {
		Query query = getEntityManager().createNativeQuery(
				"select * from tweb_opciones_roles where estado = 'A'",
				TwebOpcionRol.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebOpcionRol> listaOpcionesRolesPorRol(Long idRol) {
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from tweb_opciones_roles where estado = 'A' and id_rol = ?",
						TwebOpcionRol.class);
		query.setParameter(1, idRol);
		return query.getResultList();
	}
}
