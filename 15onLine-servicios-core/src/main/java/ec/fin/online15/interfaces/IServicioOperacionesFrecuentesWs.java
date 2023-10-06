package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebTiposTransaccion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioOperacionesFrecuentesWs {

	@WebMethod(operationName = "operacionesFrecuentesPorUsuario")
	@WebResult(name = "resultadoOperacionesFrecuentesPorUsuario")
	public List<TwebOperacionesFrecuentes> operacionesFrecuentesPorUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario,
			@WebParam(name = "tipoTransaccion") Integer tipoTransaccion);

	@WebMethod(operationName = "tiposTransaccion")
	@WebResult(name = "resultadoTiposTransaccion")
	public List<TwebTiposTransaccion> tiposTransaccion(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "guardarOperacion")
	@WebResult(name = "resultadoGuardarOperacion")
	public Integer guardarOperacion(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "operacionFrecuente") TwebOperacionesFrecuentes operacionFrecuente);

	@WebMethod(operationName = "actualizarOperacion")
	@WebResult(name = "resultadoActualizarOperacion")
	public Integer actualizarOperacion(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "operacionFrecuente") TwebOperacionesFrecuentes operacionFrecuente);

}
