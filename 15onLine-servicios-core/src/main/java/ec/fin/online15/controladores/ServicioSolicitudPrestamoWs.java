package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.EstadoSituacionCliente;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamo;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioSolicitudPrestamo;
import ec.fin.online15.interfaces.IServicioSolicitudPrestamoWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioSolicitudPrestamoWs implements IServicioSolicitudPrestamoWs {

	@EJB
	private ServicioSolicitudPrestamo servicioSolicitud;

	public DatosCliente consultaDatosCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioSolicitudPrestamoWs-consultaDatosCliente");
		log.info("SESION: " + sesion);
		return servicioSolicitud.consultaDatosCliente(codigoCliente);
	}

	public List<EstadoSituacionCliente> listaEstadoFinancieroCliente(String sesion, Integer codigoEstado,
			Integer codigoCliente) {
		log.info("Invocacion web service : ServicioSolicitudPrestamoWs-listaIngresosCliente");
		log.info("SESION: " + sesion);
		return servicioSolicitud.listaEstadoFinancieroCliente(codigoEstado, codigoCliente);
	}

	public SolicitudPrestamo registrarSolicitud(String sesion, SolicitudPrestamo solicitud) {
		log.info("Invocacion web service : ServicioSolicitudPrestamoWs-registrarSolicitud");
		log.info("SESION: " + sesion);
		return servicioSolicitud.registrarSolicitud(solicitud);
	}

}
