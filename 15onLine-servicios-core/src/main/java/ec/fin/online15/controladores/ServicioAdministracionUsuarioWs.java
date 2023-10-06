package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebEstados;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioAdministracionUsuario;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioUsuariosRoles;
import ec.fin.online15.interfaces.IServicioAdministracionUsuarioWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioAdministracionUsuarioWs implements IServicioAdministracionUsuarioWs {

	@EJB
	private ServicioAdministracionUsuario servicioAdministracionUsuario;

	@EJB
	private ServicioUsuariosRoles servicioUsuarioRol;

	public List<TwebUsuario> listaUsuariosInternos(String sesion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-listaUsuariosInternos");
		log.info("Sesion : "+sesion);
		//System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-listaUsuariosInternos");
		//System.out.println("SESION: "+sesion);
		return this.servicioAdministracionUsuario.listaUsuariosInternos();
	}

	public Empleado consultaEmpleadoPorCedula(String sesion, String numeroIdentificacion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-consultaEmpleadoPorCedula");
		log.info("SESION: "+sesion);
		//System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-consultaEmpleadoPorCedula");
		//System.out.println("SESION: "+sesion);
		return this.servicioAdministracionUsuario.consultaEmpleadoPorCedula(numeroIdentificacion);
	}

	public Empleado consultaEmpleadoPorCodigoCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-consultaEmpleadoPorCodigoCliente");
		log.info("Sesion: " +sesion);
		//System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-consultaEmpleadoPorCodigoCliente");
		//System.out.println("SESION: "+sesion);
		return this.servicioAdministracionUsuario.consultaEmpleadoPorCodigoCliente(codigoCliente);
	}

	public Empleado consultaClientePorCodigoCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-consultaClientePorCodigoCliente");
		log.info("Sesion"+sesion);
		//System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-consultaClientePorCodigoCliente");
		//System.out.println("SESION: "+sesion);
		return this.servicioAdministracionUsuario.consultaClientePorCodigoCliente(codigoCliente);
	}

	public Empleado consultaClientePorNumeroIdentificacion(String sesion, String identificacion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-consultaClientePorNumeroIdentificacion");
		log.info("Sesion: "+sesion);
		//System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-consultaClientePorNumeroIdentificacion");
		//System.out.println("SESION: "+sesion);
		return this.servicioAdministracionUsuario.consultaClientePorNumeroIdentificacion(identificacion);
	}

	public List<TwebEstados> listaEstadosVigentes(String sesion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-listaEstadosVigentes");
		//System.out.println("SESION: "+sesion);
		log.info("Sesion: "+sesion);
		return this.servicioAdministracionUsuario.listaEstadosVigentes();
	}

	public List<TwebRol> listaRolesUsuarioInternos(String sesion) {
		System.out.println("Invocacion web service : ServicioAdministracionUsuarioWs-listaRolesUsuarioInternos");
		//System.out.println("SESION: "+sesion);
		log.info("Sesion: "+sesion);
		return this.servicioAdministracionUsuario.listaRolesUsuarioInternos();
	}

	public List<TwebUsuario> listaUsuariosWeb(String sesion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-listaUsuariosWeb");
		//System.out.println("SESION: "+sesion);
		log.info("Sesion: "+sesion);
		return this.servicioAdministracionUsuario.listaUsuariosWeb();
	}

	public Integer registraUsuarioTester(String sesion, TwebUsuario usuario, String usuarioTransaccion) {
		
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-registraUsuarioTester");
		//System.out.println("SESION: "+sesion);
		log.info("Sesion: "+sesion);
		return this.servicioUsuarioRol.registrarUsuarioTester(usuario, usuarioTransaccion);
	}

	@Override
	public Integer anularUsuarioTester(String sesion, TwebUsuario usuario, String usuarioTransaccion) {
		log.info("Invocacion web service : ServicioAdministracionUsuarioWs-anularUsuarioTester");
		//System.out.println("SESION: "+sesion);
		log.info("Sesion: "+sesion);
		return this.servicioUsuarioRol.anularUsuarioTester(usuario, usuarioTransaccion);
	}

}
