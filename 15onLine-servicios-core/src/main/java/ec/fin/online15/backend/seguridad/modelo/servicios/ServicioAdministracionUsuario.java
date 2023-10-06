package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebEstados;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebEstadosEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebRolEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioAdministracionUsuario extends
		ServicioMantenimientoEntidad<TwebUsuario, Long>  {

	@EJB
	private TwebUsuariosEAO twebUsuarioEAO;

	@EJB
	private TwebEstadosEAO twebEstadoEAO;

	@EJB
	private TwebRolEAO twebRolEAO;

	 
	protected EAOGenerico<TwebUsuario, Long> getEAO() {
		return this.twebUsuarioEAO;
	}

	
	public List<TwebUsuario> listaUsuariosInternos() {
		return this.twebUsuarioEAO.listaUsuariosInternos();
	}

	public Empleado consultaEmpleadoPorCedula(String numeroIdentificacion) {
		List<Empleado> listaEmpleado = this.twebUsuarioEAO
				.consultaEmpleadoPorCedula(numeroIdentificacion);
		if (!listaEmpleado.isEmpty()) {
			return listaEmpleado.get(0);
		} else {
			return null;
		}
	}

	 
	public Empleado consultaEmpleadoPorCodigoCliente(Integer codigoCliente) {
		List<Empleado> listaEmpleado = this.twebUsuarioEAO
				.consultaEmpleadoPorCodigoCliente(codigoCliente);
		if (!listaEmpleado.isEmpty()) {
			return listaEmpleado.get(0);
		} else {
			return null;
		}
	}

	 
	public Empleado consultaClientePorCodigoCliente(Integer codigoCliente) {
		List<Empleado> listaEmpleado = this.twebUsuarioEAO
				.consultaClientePorCodigoCliente(codigoCliente);
		if (!listaEmpleado.isEmpty()) {
			return listaEmpleado.get(0);
		} else {
			return null;
		}
	}

	 
	public Empleado consultaClientePorNumeroIdentificacion(String identificacion) {
		List<Empleado> listaEmpleado = this.twebUsuarioEAO
				.consultaClientePorNumeroIdentificacion(identificacion);
		if (!listaEmpleado.isEmpty()) {
			return listaEmpleado.get(0);
		} else {
			return null;
		}
	}

	 
	public List<TwebEstados> listaEstadosVigentes() {
		return this.twebEstadoEAO.listaEstadosVigentes();
	}

	 
	public List<TwebRol> listaRolesUsuarioInternos() {
		return this.twebRolEAO.listaRolesUsuarioInternos();
	}

	 
	public List<TwebUsuario> listaUsuariosWeb() {
		return this.twebUsuarioEAO.listaUsuariosWeb();
	}

}
