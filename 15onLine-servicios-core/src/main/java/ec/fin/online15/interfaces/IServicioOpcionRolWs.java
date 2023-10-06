package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcionRol;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioOpcionRolWs {

	@WebMethod(operationName = "listaOpcionesRolesVigentes")
	@WebResult(name = "resultadolistaOpcionesMenuPadre")
	public List<TwebOpcionRol> listaOpcionesRolesVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "crearOpcionRol")
	@WebResult(name = "resultadocrearOpcionRol")
	public void crearOpcionRol(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "opcionRol") TwebOpcionRol opcionRol);

	@WebMethod(operationName = "actualizarOpcionRol")
	@WebResult(name = "resultadoactualizarOpcionRol")
	public void actualizarOpcionRol(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "opcionRol") TwebOpcionRol opcionRol);

	@WebMethod(operationName = "listaOpcionesRolesPorRol")
	@WebResult(name = "resultadolistaOpcionesPorRol")
	public List<TwebOpcionRol> listaOpcionesRolesPorRol(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "idRol") Long idRol);

}
