package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;

@Stateless
@LocalBean
public class TwebIpsAutorizadasEAO extends EAOSeed<TwebIpsAutorizadas, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebIpsAutorizadas> consultaIpPorUsuario(String ip,
			TwebUsuario usuario) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT b.* "
						+ "FROM   Tweb_Usuarios a, Tweb_Ips_Autorizadas b "
						+ "WHERE  a.Id = b.Id_Usuario "
						+ "AND    a.Estado = 'A' " + "AND b.Estado = 'A' "
						+ "AND    a.id = ? " + "AND    b.Ip = ?",
				TwebIpsAutorizadas.class);
		query.setParameter(1, usuario.getId());
		query.setParameter(2, ip);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebIpsAutorizadas> consultaIpsVigentesUsuario(
			TwebUsuario usuario) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT b.* "
						+ "FROM   Tweb_Usuarios a, Tweb_Ips_Autorizadas b "
						+ "WHERE  a.Id = b.Id_Usuario "
						+ "AND    a.Estado = 'A' " + "AND b.Estado = 'A' "
						+ "AND    a.id = ?", TwebIpsAutorizadas.class);
		query.setParameter(1, usuario.getId());
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}
}
