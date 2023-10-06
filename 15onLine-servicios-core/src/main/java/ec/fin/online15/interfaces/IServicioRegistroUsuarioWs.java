package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.PreguntaSecreta;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioRegistroUsuarioWs {

	@WebMethod(operationName = "compruebaExistenciaUsuario")
	@WebResult(name = "resultadocompruebaExistenciaUsuario")
	public Integer compruebaExistenciaUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") String usuario);

	@WebMethod(operationName = "envioOtp")
	@WebResult(name = "resultadoenvioOtp")
	public String envioOtp(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "telefono") String telefono,
			@WebParam(name = "correo") String correo,
			@WebParam(name = "otpsIguales") String otpsIguales,
			@WebParam(name = "observacion") String observacion);

	@WebMethod(operationName = "confirmacionOtp")
	@WebResult(name = "resultaconfirmacionOtp")
	public Integer confirmacionOtp(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "otpCorreo") String otpCorreo,
			@WebParam(name = "otpCelular") String otpCelular);

	@WebMethod(operationName = "consultaPreguntaSecreta")
	@WebResult(name = "resultadoconsultaPreguntaSecreta")
	public PreguntaSecreta consultaPreguntaSecreta(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "nombreUsuario") String nombreUsuario);

	@WebMethod(operationName = "modificaClaveUsuario")
	@WebResult(name = "resultadomodificaClaveUsuario")
	public int modificaClaveUsuario(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "clave") String clave,
			@WebParam(name = "bloqueado") String bloqueado,
			@WebParam(name = "actualizadoPor") String actualizadoPor);

	@WebMethod(operationName = "registrarUsuario")
	@WebResult(name = "resultadoregistrarUsuario")
	public Integer registrarUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario,
			@WebParam(name = "respuestasUsuario") List<TwebRespuestasUsuarios> respuestasUsuario,
			@WebParam(name = "nombreCLiente") String nombreCliente,
			@WebParam(name = "clave") String clave,
			@WebParam(name = "rol") Long rol, @WebParam(name = "ip") String ip);

	@WebMethod(operationName = "actualizacionUsuario")
	@WebResult(name = "resultadoactualizacionUsuario")
	public Integer actualizacionUsuario(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario);

	// MAYER
	@WebMethod(operationName = "compruebaExistenciaCelular")
	@WebResult(name = "resultadocompruebaExistenciaCelular")
	public Integer compruebaExistenciaCelular(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "celular") String celular,
			@WebParam(name = "usuario") String usuario);

	// MAYER
	@WebMethod(operationName = "compruebaExistenciaCorreo")
	@WebResult(name = "resultadocompruebaExistenciaCorreo")
	public Integer compruebaExistenciaCorreo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "correo") String correo,
			@WebParam(name = "usuario") String usuario);

}
