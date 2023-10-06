package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioUsuarioWs {

	@WebMethod(operationName = "obtenerUsuarioBase")
	@WebResult(name = "resultadoobtenerUsuarioBase")
	public TwebUsuario obtenerUsuarioBase(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario);

	@WebMethod(operationName = "errorRespuestaSecreta")
	@WebResult(name = "resultadoerrorRespuestaSecreta")
	public String errorRespuestaSecreta(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario, String actualizadoPor);

	@WebMethod(operationName = "consultaIpConocida")
	@WebResult(name = "resultadoconsultaIpConocida")
	public boolean consultaIpConocida(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario, @WebParam(name = "ip") String Ip);

	@WebMethod(operationName = "validaIpAdministrativa")
	@WebResult(name = "resultadovalidaIpAdministrativa")
	public boolean validaIpAdministrativa(@WebParam(name = "sesion") String sesion, @WebParam(name = "ip") String ip);

	@WebMethod(operationName = "rolesUsuario")
	@WebResult(name = "resultadorolesUsuario")
	public List<TwebUsuarioRol> rolesUsuario(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "idUsuario") Long idUsuario);

	@WebMethod(operationName = "registrarConexion")
	@WebResult(name = "resultadoregistrarConexion")
	public void registrarConexion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario, @WebParam(name = "ip") String ip,
			@WebParam(name = "observacion") String observacion, @WebParam(name = "guardarIp") boolean guardarIp,
			@WebParam(name = "actualizadoPor") String actualizadoPor,
			@WebParam(name = "usuarioAdministrador") boolean usuarioAdministrador,
			@WebParam(name = "canal") Canal canal);

	@WebMethod(operationName = "envioNotificacion")
	@WebResult(name = "resultadoenvioNotificacion")
	public String envioNotificacion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente, @WebParam(name = "telefono") String telefono,
			@WebParam(name = "correo") String correo, @WebParam(name = "plantilla") Integer plantilla,
			@WebParam(name = "parametros") List<String> parametros);

	@WebMethod(operationName = "consultaUsuarioPorNumeroIdentificacion")
	@WebResult(name = "resultadoconsultaUsuarioPorNumeroIdentificacion")
	public TwebUsuario consultaUsuarioPorNumeroIdentificacion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "identificacion") String identificacion);

	@WebMethod(operationName = "consultaEstadoBatch")
	@WebResult(name = "resultadoconsultaEstadoBatch")
	public String consultaEstadoBatch(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "consultaNombreCliente")
	@WebResult(name = "consultaNombreCliente")
	public String consultaNombreCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

}
