package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioCambioClavePreguntasWs {

	@WebMethod(operationName = "respuestasVigentesUsuarios")
	@WebResult(name = "resultadorespuestasVigentesUsuarios")
	public List<TwebRespuestasUsuarios> respuestasVigentesUsuarios(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario);

	@WebMethod(operationName = "actualizarRespuestasUsuario")
	@WebResult(name = "resultadoactualizarRespuestasUsuario")
	public Integer actualizarRespuestasUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "respuestas") List<TwebRespuestasUsuarios> respuestasUsuario);

	@WebMethod(operationName = "compruebaClaveUsuario")
	@WebResult(name = "resultadocompruebaClaveUsuario")
	public boolean compruebaClaveUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "clave") String clave);

	@WebMethod(operationName = "cambioClave")
	@WebResult(name = "resultadocambioClave")
	public Integer cambioClave(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario,
			@WebParam(name = "claveNueva") String claveNueva);

	@WebMethod(operationName = "cambioTelefonoCorreo")
	@WebResult(name = "resultadocambioTelefonoCorreo")
	public Integer cambioTelefonoCorreo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario);

	@WebMethod(operationName = "ipsVigenteUsuario")
	@WebResult(name = "resultadoipsVigenteUsuario")
	public List<TwebIpsAutorizadas> ipsVigenteUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario);

	@WebMethod(operationName = "actualizarIpsVigentes")
	@WebResult(name = "resultadoactualizarIpsVigentes")
	public Integer actualizarIpsVigentes(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "listaIps") List<TwebIpsAutorizadas> listaIps);

}
