package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioRolWs {

	@WebMethod(operationName = "listaRolesVigentes")
	@WebResult(name = "resultadolistaRolesVigentes")
	public List<TwebRol> listaRolesVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "buscarPorId")
	@WebResult(name = "resultadobuscarPorId")
	public TwebRol buscarPorId(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "id") Long id);

}
