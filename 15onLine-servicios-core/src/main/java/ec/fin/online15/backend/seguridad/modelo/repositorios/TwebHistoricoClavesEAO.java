package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebHistoricoClaves;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Stateless
@LocalBean
public class TwebHistoricoClavesEAO extends EAOSeed<TwebHistoricoClaves, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebHistoricoClaves> historicoClaveUsuario(TwebUsuario usuario,
			Integer limite) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT *  " + "FROM   (SELECT * "
						+ "        FROM   Tweb_Historico_Claves "
						+ "        WHERE  Id_Usuario = ? "
						+ "        ORDER  BY Fecha_Registro DESC) "
						+ "WHERE  Rownum <= ?", TwebHistoricoClaves.class);
		query.setParameter(1, usuario.getId());
		query.setParameter(2, limite);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
