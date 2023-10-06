package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebTiposTransaccion;

@Stateless
@LocalBean
public class TwebTiposTransaccionEAO extends
		EAOSeed<TwebTiposTransaccion, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebTiposTransaccion> listaTransaccionesVigentes() {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM Tweb_Tipos_Transaccion WHERE Estado = 'A' AND id not in(4,5) order by 1",
				TwebTiposTransaccion.class);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
