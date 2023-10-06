package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebEstados;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioAdministracionUsuarioWs {

	@WebMethod(operationName = "listaUsuariosInternos")
	@WebResult(name = "resultadolistaUsuariosInternos")
	public List<TwebUsuario> listaUsuariosInternos(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "consultaEmpleadoPorCedula")
	@WebResult(name = "resultadoconsultaEmpleadoPorCedula")
	public Empleado consultaEmpleadoPorCedula(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroIdentificacion") String numeroIdentificacion);

	@WebMethod(operationName = "consultaEmpleadoPorCodigoCliente")
	@WebResult(name = "resultadoconsultaEmpleadoPorCodigoCliente")
	public Empleado consultaEmpleadoPorCodigoCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaClientePorCodigoCliente")
	@WebResult(name = "resultadoconsultaClientePorCodigoCliente")
	public Empleado consultaClientePorCodigoCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaClientePorNumeroIdentificacion")
	@WebResult(name = "resultadoconsultaClientePorNumeroIdentificacion")
	public Empleado consultaClientePorNumeroIdentificacion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "identificacion") String identificacion);

	@WebMethod(operationName = "listaEstadosVigentes")
	@WebResult(name = "resultadolistaEstadosVigentes")
	public List<TwebEstados> listaEstadosVigentes(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listaRolesUsuarioInternos")
	@WebResult(name = "resultadolistaRolesUsuarioInternos")
	public List<TwebRol> listaRolesUsuarioInternos(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listaUsuariosWeb")
	@WebResult(name = "resultadolistaUsuariosWeb")
	public List<TwebUsuario> listaUsuariosWeb(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "registraUsuarioTester")
	@WebResult(name = "resultadoregistraUsuarioTester")
	public Integer registraUsuarioTester(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario,
			@WebParam(name = "usuarioTransaccion") String usuarioTransaccion);

	@WebMethod(operationName = "anularUsuarioTester")
	@WebResult(name = "resultadoanularUsuarioTester")
	public Integer anularUsuarioTester(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "usuario") TwebUsuario usuario,
			@WebParam(name = "usuarioTransaccion") String usuarioTransaccion);

}
