package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebEstados;

@Stateless
@LocalBean
public class TwebEstadosEAO extends EAOSeed<TwebEstados, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebEstados> listaEstadosVigentes() {
		Query query = getEntityManager().createNativeQuery(
				"select a.* from TWEB_estados a where a.estado = 'A'",
				TwebEstados.class);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
