package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosSolicitud;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioParametrosSolicitudWs {

	@WebMethod(operationName = "listaParametrosVigentes")
	@WebResult(name = "resultadolistaParametrosVigentes")
	public List<TwebParametrosSolicitud> listaParametrosVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "crearParametro")
	@WebResult(name = "resultadocrearParametro")
	public void crearParametro(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "parametro") TwebParametrosSolicitud parametro);

	@WebMethod(operationName = "actualizarParametro")
	@WebResult(name = "resultadoactualizarParametro")
	public void actualizarParametro(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "parametro") TwebParametrosSolicitud parametro);

	@WebMethod(operationName = "buscaParametro")
	@WebResult(name = "resultadobuscaParametro")
	public TwebParametrosSolicitud buscaParametro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "relacionDependencia") String relacionDependencia,
			@WebParam(name = "preaprobado") String preaprobado,
			@WebParam(name = "monto") Double monto);

}
