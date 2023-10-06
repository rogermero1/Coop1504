package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;

@Stateless
@LocalBean
public class TwebBloqueosPorValiacionEAO extends
		EAOSeed<TwebBloqueoPorValidacion, Long> {

	public TwebBloqueoPorValidacion obtenerBloqueoActual(Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Bloqueos_x_Validacion WHERE Estado = 'A' AND Codigo_Cliente = ?",
						TwebBloqueoPorValidacion.class);
		query.setParameter(1, codigoCliente);
		try {
			return (TwebBloqueoPorValidacion) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebBloqueoPorValidacion> bloqueosVigentes() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Bloqueos_x_Validacion a WHERE Estado = 'A'",
						TwebBloqueoPorValidacion.class);
		return query.getResultList();
	}

}
