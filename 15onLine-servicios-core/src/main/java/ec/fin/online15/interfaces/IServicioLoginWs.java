package ec.fin.online15.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioLoginWs extends Serializable {

	@WebMethod(operationName = "autenticacion")
	@WebResult(name = "resultadoAutenticacion")
	public String autenticacion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "resultadoLdap") boolean resultadoLdap,
			@WebParam(name = "canal") Canal canal);

}
