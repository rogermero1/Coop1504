package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioBloqueoPorValidacion;
import ec.fin.online15.interfaces.IServicioBloqueoPorValidacionWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioBloqueoPorValidacionWs implements IServicioBloqueoPorValidacionWs {

	@EJB
	private ServicioBloqueoPorValidacion servicioBloqueoPorValidacion;

	public List<TwebBloqueoPorValidacion> listaBloqueosVigentes(String sesion) {
		log.info("Invocacion web service : ServicioBloqueoPorValidacionWs-listaBloqueosVigentes");
		log.info("SESION: " + sesion);
		return servicioBloqueoPorValidacion.listaBloqueosVigentes();
	}

	public TwebBloqueoPorValidacion bloqueoActual(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioBloqueoPorValidacionWs-bloqueoActual");
		log.info("SESION: " + sesion);
		return servicioBloqueoPorValidacion.bloqueoActual(codigoCliente);
	}

	public Integer actualizacionBloqueo(String sesion, TwebBloqueoPorValidacion bloqueo) {
		log.info("Invocacion web service : ServicioBloqueoPorValidacionWs-actualizacionBloqueo");
		log.info("SESION: " + sesion);
		return servicioBloqueoPorValidacion.actualizacionBloqueo(bloqueo);
	}

}
