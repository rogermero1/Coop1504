package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioCambioClavePreguntas;
import ec.fin.online15.interfaces.IServicioCambioClavePreguntasWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioCambioClavePreguntasWs implements IServicioCambioClavePreguntasWs {

	@EJB
	private ServicioCambioClavePreguntas servicioCambioClavePreguntas;

	public List<TwebRespuestasUsuarios> respuestasVigentesUsuarios(String sesion, TwebUsuario usuario) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-respuestasVigentesUsuarios");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.respuestasVigentesUsuarios(usuario);
	}

	public Integer actualizarRespuestasUsuario(String sesion, List<TwebRespuestasUsuarios> respuestasUsuario) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-actualizarRespuestasUsuario");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.actualizarRespuestasUsuario(respuestasUsuario);
	}

	public boolean compruebaClaveUsuario(String sesion, String usuario, String clave) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-compruebaClaveUsuario");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.compruebaClaveUsuario(usuario, clave);
	}

	public Integer cambioClave(String sesion, TwebUsuario usuario, String claveNueva) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-cambioClave");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.cambioClave(usuario, claveNueva);
	}

	public Integer cambioTelefonoCorreo(String sesion, TwebUsuario usuario) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-cambioTelefonoCorreo");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.cambioTelefonoCorreo(usuario);
	}

	public List<TwebIpsAutorizadas> ipsVigenteUsuario(String sesion, TwebUsuario usuario) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-ipsVigenteUsuario");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.ipsVigenteUsuario(usuario);
	}

	public Integer actualizarIpsVigentes(String sesion, List<TwebIpsAutorizadas> listaIps) {
		log.info("Invocacion web service : ServicioCambioClavePreguntas-actualizarIpsVigentes");
		log.info("SESION: " + sesion);
		return this.servicioCambioClavePreguntas.actualizarIpsVigentes(listaIps);
	}

}
