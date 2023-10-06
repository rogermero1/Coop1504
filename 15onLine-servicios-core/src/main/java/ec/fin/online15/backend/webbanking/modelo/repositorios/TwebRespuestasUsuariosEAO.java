package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;

@Stateless
@LocalBean
public class TwebRespuestasUsuariosEAO extends
		EAOSeed<TwebRespuestasUsuarios, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebRespuestasUsuarios> listaRespuestasUsuarios(
			TwebUsuario usuario) {
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from tweb_respuesta_usuarios where id_usuario = ? and estado = 'A' order by 1",
						TwebRespuestasUsuarios.class);
		query.setParameter(1, usuario.getId());
		return query.getResultList();
	}
}
