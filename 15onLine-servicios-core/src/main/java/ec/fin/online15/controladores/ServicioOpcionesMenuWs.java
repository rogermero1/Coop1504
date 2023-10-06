package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioMantenimientoOpcion;
import ec.fin.online15.interfaces.IServicioOpcionesMenuWs;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioOpcionesMenuWs implements IServicioOpcionesMenuWs {

	@EJB
	private ServicioMantenimientoOpcion servicioMantenimientoOpcion;

	public List<TwebOpcion> listaOpcionesVigentes(String sesion) {
		log.info("Invocacion web service : ServicioOpcionesMenuWs-listaOpcionesViegentes");
		log.info("SESION: " + sesion);
		return servicioMantenimientoOpcion.listaOpcionesViegentes();
	}

	public void crearOpcion(String sesion, TwebOpcion opcion) {
		log.info("Invocacion web service : ServicioOpcionesMenuWs-crearOpcion");
		log.info("SESION: " + sesion);
		try {
			servicioMantenimientoOpcion.crear(opcion);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public void actualizarOpcion(String sesion, TwebOpcion opcion) {
		log.info("Invocacion web service : ServicioOpcionesMenuWs-actualizarOpcion");
		log.info("SESION: " + sesion);
		try {
			servicioMantenimientoOpcion.actualizar(opcion);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public TwebOpcion buscarPorId(String sesion, Long id) {
		log.info("Invocacion web service : ServicioOpcionesMenuWs-buscarPorId");
		log.info("SESION: " + sesion);
		return servicioMantenimientoOpcion.buscarPorId(id);
	}
}
