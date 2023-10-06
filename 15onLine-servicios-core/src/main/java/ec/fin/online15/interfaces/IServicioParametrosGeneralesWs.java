package ec.fin.online15.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioParametrosGeneralesWs {

	@WebMethod(operationName = "listaParametrosGenerales")
	@WebResult(name = "resultadolistaParametrosGenerales")
	public List<TwebParametrosGenerales> listaParametrosGenerales(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "actualizarParametro")
	@WebResult(name = "resultadoactualizarParametro")
	public void actualizarParametro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "parametroGeneral") TwebParametrosGenerales parametroGeneral);

	@WebMethod(operationName = "consultaFechaValida")
	@WebResult(name = "resultadoConsultaFechaValida")
	public Date consultaFechaValida(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "modulo") String modulo);

}
