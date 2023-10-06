package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioRol;
import ec.fin.online15.interfaces.IServicioRolWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioRolWs implements IServicioRolWs {

	@EJB
	private ServicioRol servicioRol;

	public List<TwebRol> listaRolesVigentes(String sesion) {
		log.info("Invocacion web service : ServicioRolWs-listaRolesVigentes");
		log.info("SESION: " + sesion);
		return servicioRol.listaRolesVigentes();
	}

	public TwebRol buscarPorId(String sesion, Long id) {
		log.info("Invocacion web service : ServicioRolWs-buscarPorId");
		log.info("SESION: " + sesion);
		return servicioRol.buscarPorId(id);
	}

}
