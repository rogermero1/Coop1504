package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioBloqueoPorValidacionWs {

	@WebMethod(operationName = "listaBloqueosVigentes")
	@WebResult(name = "resultadolistaBloqueosVigentes")
	public List<TwebBloqueoPorValidacion> listaBloqueosVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "bloqueoActual")
	@WebResult(name = "resultadobloqueoActual")
	public TwebBloqueoPorValidacion bloqueoActual(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "actualizacionBloqueo")
	@WebResult(name = "resultadoactualizacionBloqueo")
	public Integer actualizacionBloqueo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "bloqueo") TwebBloqueoPorValidacion bloqueo);

}
