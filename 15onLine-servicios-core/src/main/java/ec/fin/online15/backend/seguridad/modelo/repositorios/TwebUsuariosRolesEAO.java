package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;

@Stateless
@LocalBean
public class TwebUsuariosRolesEAO extends EAOSeed<TwebUsuarioRol, Long> {

	public void inactivarRolesUsuario(TwebUsuario usuario) {
		String lsQuery = "update tweb_usuarios_roles a" + " set a.estado = 'I', a.fecha_actualizacion = sysdate "
				+ " where a.id_usuario = ?";
		Query query = getEntityManager().createNativeQuery(lsQuery);
		query.setParameter(1, usuario.getId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TwebUsuarioRol> rolesUsuario(Long idUsuario) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * " + "FROM   Tweb_Usuarios_Roles " + "WHERE  Id_Usuario = ? " + "AND    Estado = 'A'",
				TwebUsuarioRol.class);
		query.setParameter(1, idUsuario);
		return query.getResultList();
	}

	public TwebUsuarioRol obtenerRolUsuarioTest(Long idUsuario) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM As_Seed.Tweb_Usuarios_Roles WHERE Id_Usuario = ? AND Id_Rol = 5 AND Estado = 'A' ",
				TwebUsuarioRol.class);
		query.setParameter(1, idUsuario);
		try {
			return (TwebUsuarioRol) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}
}
