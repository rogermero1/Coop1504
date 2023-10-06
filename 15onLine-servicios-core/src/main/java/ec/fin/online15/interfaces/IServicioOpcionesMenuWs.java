package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioOpcionesMenuWs {

	@WebMethod(operationName = "listaOpcionesVigentes")
	@WebResult(name = "resultadolistaOpcionesViegentes")
	public List<TwebOpcion> listaOpcionesVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "crearOpcion")
	@WebResult(name = "resultadocrearOpcion")
	public void crearOpcion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "opcion") TwebOpcion opcion);

	@WebMethod(operationName = "actualizarOpcion")
	@WebResult(name = "resultadoactualizarOpcion")
	public void actualizarOpcion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "opcion") TwebOpcion opcion);

	@WebMethod(operationName = "buscarPorId")
	@WebResult(name = "resultadobuscarPorId")
	public TwebOpcion buscarPorId(
			@WebParam(name = "sesion") String sesion,
			@WebParam(targetNamespace = "http://servicios.coop15abril.fin.ec", name = "id") Long id);

}
