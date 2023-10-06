package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosSolicitud;

@Stateless
@LocalBean
public class TwebParametrosSolicitudEAO extends
		EAOSeed<TwebParametrosSolicitud, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebParametrosSolicitud> parametrosVigentes() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Parametros_Solicitudes a WHERE Estado = 'A'",
						TwebParametrosSolicitud.class);
		return query.getResultList();
	}

	public TwebParametrosSolicitud buscaParametro(String relacionDependencia,
			String preaprobado, Double monto) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * " + "FROM   Tweb_Parametros_Solicitudes "
						+ "WHERE  Estado = 'A' "
						+ "AND    Relacion_Dependencia = ? "
						+ "AND    Preaprobado = ? "
						+ "AND    ? BETWEEN Monto_Desde AND Monto_Hasta",
				TwebParametrosSolicitud.class);
		query.setParameter(1, relacionDependencia);
		query.setParameter(2, preaprobado);
		query.setParameter(3, monto);
		try {
			return (TwebParametrosSolicitud) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}
}
