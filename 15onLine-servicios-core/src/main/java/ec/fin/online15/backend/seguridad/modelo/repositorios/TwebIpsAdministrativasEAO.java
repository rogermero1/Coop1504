package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebIpsAdministrativas;

@Stateless
@LocalBean
public class TwebIpsAdministrativasEAO extends EAOSeed<TwebIpsAdministrativas, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebIpsAdministrativas> ipsVigentes() {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM Tweb_Ips_Administrativas a WHERE Estado = 'A'", TwebIpsAdministrativas.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebIpsAdministrativas> consultaIp(String ip) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM Tweb_Ips_Administrativas a WHERE Estado = 'A' AND ? like Ip", TwebIpsAdministrativas.class);
		query.setParameter(1, ip);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
