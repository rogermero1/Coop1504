package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioAutorizacion;
import ec.fin.online15.interfaces.IServicioAutorizacionWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioAutorizacionWs implements IServicioAutorizacionWs {

	@EJB
	private ServicioAutorizacion servicioAutorizacion;

	public List<TwebOpcion> listaOpcionesMenuPadre(String sesion, String usuario, String orientacion) {
		log.info("Invocacion web service : ServicioAutorizacionWs-listaOpcionesMenuPadre");
		log.info("SESION: " + sesion);
		return servicioAutorizacion.listaOpcionesMenuPadre(usuario, orientacion);
	}

	public List<TwebOpcion> listaOpcionesHijas(String sesion, Long idOpcion, String usuario, String orientacion) {
		log.info("Invocacion web service : ServicioAutorizacionWs-listaOpcionesHijas");
		log.info("SESION: " + sesion);
		return servicioAutorizacion.listaOpcionesHijas(idOpcion, usuario, orientacion);
	}

	public TwebUsuario buscarUsuarioAplicacionBase(String sesion, String usuario) {
		log.info("Invocacion web service : ServicioAutorizacionWs-buscarUsuarioAplicacionBase");
		log.info("SESION: " + sesion);
		return servicioAutorizacion.buscarUsuarioAplicacionBase(usuario);
	}

}
