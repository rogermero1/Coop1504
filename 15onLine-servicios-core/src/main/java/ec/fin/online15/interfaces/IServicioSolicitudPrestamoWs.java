package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.EstadoSituacionCliente;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamo;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioSolicitudPrestamoWs {

	@WebMethod(operationName = "consultaDatosCliente")
	@WebResult(name = "resultadoconsultaDatosCliente")
	public DatosCliente consultaDatosCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "listaIngresosCliente")
	@WebResult(name = "resultadolistaIngresosCliente")
	public List<EstadoSituacionCliente> listaEstadoFinancieroCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoEstado") Integer codigoEstado,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "registrarSolicitud")
	@WebResult(name = "resultadoregistrarSolicitud")
	public SolicitudPrestamo registrarSolicitud(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "solicitud") SolicitudPrestamo solicitud);

}
