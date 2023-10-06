package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioAutorizacionWs {

	@WebMethod(operationName = "listaOpcionesMenuPadre")
	@WebResult(name = "resultadolistaOpcionesMenuPadre")
	public List<TwebOpcion> listaOpcionesMenuPadre(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "orientacion") String orientacion);

	@WebMethod(operationName = "listaOpcionesHijas")
	@WebResult(name = "resultadolistaOpcionesHijas")
	public List<TwebOpcion> listaOpcionesHijas(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "idOpcion") Long idOpcion,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "orientacion") String orientacion);

	@WebMethod(operationName = "buscarUsuarioAplicacionBase")
	@WebResult(name = "resultadobuscarUsuarioAplicacionBase")
	public TwebUsuario buscarUsuarioAplicacionBase(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario);
}
