package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.EstadoSituacionCliente;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamo;
import ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones.SolicitudesPrestamosEAO;

@Stateless
public class ServicioSolicitudPrestamo  {

	@EJB
	SolicitudesPrestamosEAO solicitudesPrestamosEAO;

	
	public DatosCliente consultaDatosCliente(Integer codigoCliente) {
		return solicitudesPrestamosEAO.consultaDatosCliente(codigoCliente);
	}

	
	public List<EstadoSituacionCliente> listaEstadoFinancieroCliente(
			Integer codigoEstado, Integer codigoCliente) {
		return solicitudesPrestamosEAO.listaEstadoFinancieroCliente(
				codigoEstado, codigoCliente);
	}

	
	public SolicitudPrestamo registrarSolicitud(SolicitudPrestamo solicitud) {
		return solicitudesPrestamosEAO.registrarSolicitud(solicitud);
	}

}
