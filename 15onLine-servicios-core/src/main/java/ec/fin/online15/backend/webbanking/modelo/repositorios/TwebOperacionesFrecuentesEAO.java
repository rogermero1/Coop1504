package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;

@Stateless
@LocalBean
public class TwebOperacionesFrecuentesEAO extends
		EAOSeed<TwebOperacionesFrecuentes, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebOperacionesFrecuentes> operacionesFrecuentesPorUsuario(
			TwebUsuario usuario, Integer tipoTransaccion) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.* "
								+ "FROM   Tweb_Operacion_Frecuente a, Tweb_Usuarios b "
								+ "WHERE  a.Id_Usuario = b.Id "
								+ "AND    a.Estado = 'A' "
								+ "AND    b.Estado = 'A' "
								+ "AND    b.Id = ? "
								+ "AND    a.Id_Tipo_Transaccion = decode(?,0,a.Id_Tipo_Transaccion,?)",
						TwebOperacionesFrecuentes.class);

		query.setParameter(1, usuario.getId());
		query.setParameter(2, tipoTransaccion == null ? 0 : tipoTransaccion);
		query.setParameter(3, tipoTransaccion == null ? 0 : tipoTransaccion);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
