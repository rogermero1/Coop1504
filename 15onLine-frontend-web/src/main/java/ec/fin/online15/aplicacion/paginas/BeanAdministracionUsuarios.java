package ec.fin.online15.aplicacion.paginas;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionEncriptacion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebEstados;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.integracion.beans.BeanServiciosAdministracionUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;
import ec.fin.online15.librerias.utilerias.UtilCryptography;

@Named("administracionUsuarioMB")
@SessionScoped
public class BeanAdministracionUsuarios extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanServiciosAdministracionUsuario servicioAdministracionUsuario;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosRegistroUsuario servicioMantenimientoUsuario;

	@Inject
	private BeanServiciosUsuario servicioUsuario;

	private String tituloMensajes;
	private List<TwebUsuario> usuariosInternos;
	private List<TwebUsuario> usuariosWeb;
	private List<TwebUsuario> usuariosFiltro;
	private TwebUsuario usuario;
	private TwebUsuario usuarioSeleccionado;
	private String numeroIdentificacion;
	private String nombreEmpleado;
	private List<TwebEstados> listaEstados;
	private List<TwebRol> listaRoles;
	private Long rol;
	private String claveTemp;
	private String claveTemp2;
	private boolean accionEliminar;

	private String control;

	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

	public TwebUsuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(TwebUsuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<TwebUsuario> getUsuariosInternos() {
		return usuariosInternos;
	}

	public void setUsuariosInternos(List<TwebUsuario> usuariosInternos) {
		this.usuariosInternos = usuariosInternos;
	}

	public List<TwebUsuario> getUsuariosWeb() {
		return usuariosWeb;
	}

	public void setUsuariosWeb(List<TwebUsuario> usuariosWeb) {
		this.usuariosWeb = usuariosWeb;
	}

	public List<TwebUsuario> getUsuariosFiltro() {
		return usuariosFiltro;
	}

	public void setUsuariosFiltro(List<TwebUsuario> usuariosFiltro) {
		this.usuariosFiltro = usuariosFiltro;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public List<TwebEstados> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<TwebEstados> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<TwebRol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<TwebRol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

	public String getClaveTemp() {
		return claveTemp;
	}

	public void setClaveTemp(String claveTemp) {
		this.claveTemp = claveTemp;
	}

	public String getClaveTemp2() {
		return claveTemp2;
	}

	public void setClaveTemp2(String claveTemp2) {
		this.claveTemp2 = claveTemp2;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	@PostConstruct
	public void init() {
		inicializacion();
	}

	public void inicializacion() {
		control = "";
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		this.numeroIdentificacion = null;
		this.nombreEmpleado = null;
		this.claveTemp = null;
		this.claveTemp2 = null;
		this.usuario = new TwebUsuario();
		this.usuarioSeleccionado = new TwebUsuario();
		this.rol = null;
		this.accionEliminar = true;
		this.usuariosInternos = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.listaUsuariosInternos(this.getHttpSession().getId());
		this.usuariosWeb = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.listaUsuariosWeb(this.getHttpSession().getId());
		this.listaEstados = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.listaEstadosVigentes(this.getHttpSession().getId());
		this.listaRoles = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.listaRolesUsuarioInternos(this.getHttpSession().getId());
	}

	public void buscaEmpleado() {
		Empleado empleadoTemp = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.consultaEmpleadoPorCedula(this.getHttpSession().getId(), this.numeroIdentificacion);
		if (empleadoTemp == null) {
			this.aniadirMensajeError(tituloMensajes, "No se encuentra empleado");

		} else {
			this.usuario = new TwebUsuario();
			this.usuario.setCodigoCliente(empleadoTemp.getCodigoCliente());
			this.numeroIdentificacion = empleadoTemp.getNumeroIdentificacion();
			this.nombreEmpleado = empleadoTemp.getNombres();
		}
	}

	public String nombreEmpleado(Integer codigoCliente) {
		Empleado empleadoTemp = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.consultaEmpleadoPorCodigoCliente(this.getHttpSession().getId(), codigoCliente);
		if (empleadoTemp == null) {
			return "S/N";
		} else {
			return empleadoTemp.getNombres();
		}
	}

	public String nombreCliente(Integer codigoCliente) {
		Empleado empleadoTemp = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.consultaClientePorCodigoCliente(this.getHttpSession().getId(), codigoCliente);
		if (empleadoTemp == null) {
			return "S/N";
		} else {
			return empleadoTemp.getNombres();
		}
	}

	public void cargarEmpleado(Integer codigoCliente) {
		Empleado empleadoTemp = this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.consultaEmpleadoPorCodigoCliente(this.getHttpSession().getId(), codigoCliente);
		if (empleadoTemp != null) {
			this.claveTemp = this.claveTemp2 = null;
			this.numeroIdentificacion = empleadoTemp.getNumeroIdentificacion();
			this.nombreEmpleado = empleadoTemp.getNombres();
		}
	}

	public List<TwebUsuarioRol> rolesUsuario(Long idUsuario) {
		List<TwebUsuarioRol> listaRolesTemp = this.servicioUsuario.getIServicioUsuarioWs()
				.rolesUsuario(this.getHttpSession().getId(), idUsuario);
		return listaRolesTemp;
	}

	public void guardarUsuario(ActionEvent evento) {
		if (this.usuario.getId() != null) {
			this.usuario.setFechaActualizacion(new Date());
			this.usuario.setActualizadoPor(this.getUsuarioAutenticado().toString());
			int resultado = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
					.actualizacionUsuario(this.getHttpSession().getId(), this.usuario);
			if (resultado == 1) {
				this.configuracionesGenerales.mensajeTransaccionExitosa();
				inicializacion();
			} else {
				this.configuracionesGenerales.mensajeTransaccionError();
			}
		} else {
			if (this.usuario.getCodigoCliente() == null || this.nombreEmpleado == null) {
				this.aniadirMensajeError(tituloMensajes, "No se ha seleccionado el empleado");
			} else if (this.usuario.getUsuario().isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el usuario");
			} else if (this.usuario.getCorreo().isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el correo");
			} else if (this.usuario.getCelular().isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el celular");
			} else if (this.rol == null) {
				this.aniadirMensajeError(tituloMensajes, "No se ha seleccionado rol");
			} else if (this.usuario.getEstado().isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el celular");
			} else if (this.claveTemp.isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la contraseña");
			} else if (this.claveTemp2.isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la confirmación de contraseña");
			} else if (!this.claveTemp.equals(this.claveTemp2)) {
				this.aniadirMensajeError(tituloMensajes, "Las contraseñas no coinciden");
			} else if (this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
					.compruebaExistenciaUsuario(this.getHttpSession().getId(), this.usuario.getUsuario()) > 0) {
				this.aniadirMensajeError(tituloMensajes, "Ya existe un usuario con ese nombre");
			} else {
				this.usuario.setCambiaClave("S");
				this.usuario.setBloqueado("N");
				this.usuario.setIntentoFallidoAutenticacion(0);
				this.usuario.setIntentoFallidoRespuestas(0);
				this.usuario.setObservacion("REGISTRO DE USUARIO");
				this.usuario.setFechaRegistro(new Date());
				this.usuario.setFechaUltimoCambioClave(new Date());
				this.usuario.setRegistradoPor(this.getUsuarioAutenticado().toString());
				String claveMd5 = null;
				try {
					claveMd5 = UtilCryptography.encriptarMd5(this.claveTemp);
				} catch (ExcepcionEncriptacion e1) {
					e1.printStackTrace();
				}
				int resultado = servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().registrarUsuario(
						this.getHttpSession().getId(), this.usuario, null, this.nombreEmpleado, claveMd5, this.rol,
						this.getIpLocal());
				if (resultado == 1) {
					this.configuracionesGenerales.mensajeTransaccionExitosa();
					inicializacion();
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
				}
			}
		}
	}

	public void anularUsuario(ActionEvent event) {
		this.usuarioSeleccionado.setEstado("I");
		this.usuarioSeleccionado.setFechaActualizacion(new Date());
		this.usuarioSeleccionado.setActualizadoPor(this.getUsuarioAutenticado().toString());
		int resultado = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
				.actualizacionUsuario(this.getHttpSession().getId(), this.usuarioSeleccionado);
		if (resultado == 1) {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} else {
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void actualizar(ActionEvent event) {
		this.usuarioSeleccionado.setFechaActualizacion(new Date());
		this.usuarioSeleccionado.setActualizadoPor(this.getUsuarioAutenticado().toString());
		int resultado = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
				.actualizacionUsuario(this.getHttpSession().getId(), this.usuarioSeleccionado);
		if (resultado == 1) {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} else {
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public void consultaUsuarioPorNumeroIdentificacion(ActionEvent evento) {
		this.usuarioSeleccionado = this.servicioUsuario.getIServicioUsuarioWs()
				.consultaUsuarioPorNumeroIdentificacion(this.getHttpSession().getId(), this.numeroIdentificacion);
		if (this.usuarioSeleccionado == null) {
			this.aniadirMensajeError(this.tituloMensajes, "Cliente no existe, o no tiene usario activo");
			this.accionEliminar = true;
		} else {
			this.accionEliminar = false;
		}
	}

	public void consultaUsuarioTester(ActionEvent evento) {
		this.usuarioSeleccionado = this.servicioUsuario.getIServicioUsuarioWs()
				.consultaUsuarioPorNumeroIdentificacion(this.getHttpSession().getId(), this.numeroIdentificacion);
		if (this.usuarioSeleccionado == null) {
			this.aniadirMensajeError(this.tituloMensajes, "Cliente no existe, o no tiene usario activo");
			this.accionEliminar = true;
		} else {
			List<TwebUsuarioRol> rolesUsuario = this.servicioUsuario.getIServicioUsuarioWs()
					.rolesUsuario(this.getHttpSession().getId(), this.usuarioSeleccionado.getId());
			Integer conteoRol = 0;
			if (rolesUsuario != null) {
				for (TwebUsuarioRol usuarioRol : rolesUsuario) {
					if (usuarioRol.getRol().getId() == 5) {
						conteoRol += 1;
					}
				}
			}
			this.accionEliminar = conteoRol > 0 ? true : false;
		}
	}

	public void registrarUsuarioTester(ActionEvent event) {
		if (this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs().registraUsuarioTester(
				this.getHttpSession().getId(), this.usuarioSeleccionado,
				this.getUsuarioAutenticado().toString()) == 1) {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} else {
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void anularUsuarioTester(ActionEvent event) {
		if (this.servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs().anularUsuarioTester(
				this.getHttpSession().getId(), this.usuarioSeleccionado,
				this.getUsuarioAutenticado().toString()) == 1) {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} else {
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

}
