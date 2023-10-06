package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebRespuestasUsuariosEAO;

@Stateless
public class ServicioRespuestasUsuarios extends
		ServicioMantenimientoEntidad<TwebRespuestasUsuarios, Long>  {

	@EJB
	private TwebRespuestasUsuariosEAO respuestasUsuariosEAO;

	public List<TwebRespuestasUsuarios> listaRespuestasUsuarios(
			TwebUsuario usuario) {
		return respuestasUsuariosEAO.listaRespuestasUsuarios(usuario);
	}

	protected EAOGenerico<TwebRespuestasUsuarios, Long> getEAO() {
		return respuestasUsuariosEAO;
	}

	public Integer actualizarRespuestasUsuario(
			List<TwebRespuestasUsuarios> respuestasUsuario) {
		try {
			this.actualizarColeccion(respuestasUsuario);
			return 1;
		} catch (ExcepcionAplicacion e) {
			System.out
					.println("ServicioRespuestasUsuarios - actualizarRespuestasUsuario");
			e.printStackTrace();
			return 0;
		}

	}

}
