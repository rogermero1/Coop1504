package ec.fin.online15.aplicacion.paginas;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;
import ec.fin.online15.integracion.beans.BeanServiciosAdministracionUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosBloqueosPorValidacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("bloqueoValidacionMB")
@SessionScoped
public class BeanBloqueoValidacion extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosBloqueosPorValidacion serviciosBloqueosPorValidacion;

	@Inject
	private BeanServiciosAdministracionUsuario servicioAdministracionUsuario;

	private String tituloMensajes;
	private String identificacion;
	private Empleado cliente;
	private String nombreCliente;
	private boolean accionEliminar;
	private TwebBloqueoPorValidacion bloqueo;

	// private List<TwebUsuario> usuariosInternos;
	// private List<TwebUsuario> usuariosWeb;
	// private List<TwebUsuario> usuariosFiltro;
	// private TwebUsuario usuario;
	// private TwebUsuario usuarioSeleccionado;
	// private String numeroIdentificacion;
	// private String nombreEmpleado;
	// private List<TwebEstados> listaEstados;
	// private List<TwebRol> listaRoles;
	// private Long rol;
	// private String claveTemp;
	// private String claveTemp2;
	// private boolean accionEliminar;

	@PostConstruct
	public void init() {
		inicializacion();
	}

	public void inicializacion() {
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);

		this.identificacion = "";
		this.nombreCliente = "";

		this.cliente = new Empleado();
		this.accionEliminar = true;

	}

	// public String nombreCliente(Integer codigoCliente) {
	// Empleado empleadoTemp = this.servicioAdministracionUsuario
	// .getIServicioAdministracionUsuarioWs()
	// .consultaClientePorCodigoCliente(this.getHttpSession().getId(),
	// codigoCliente);
	// if (empleadoTemp == null) {
	// return "S/N";
	// } else {
	// return empleadoTemp.getNombres();
	// }
	// }

	public void consultaUsuarioBloqueado(ActionEvent evento) {
		this.cliente = this.servicioAdministracionUsuario
				.getIServicioAdministracionUsuarioWs()
				.consultaClientePorNumeroIdentificacion(
						this.getHttpSession().getId(), this.identificacion);

		if (this.cliente == null) {
			this.aniadirMensajeError(this.tituloMensajes, "Cliente no existe");
			this.accionEliminar = true;
		} else {
			this.bloqueo = this.serviciosBloqueosPorValidacion
					.getIServicioBloqueoPorValidacionWs().bloqueoActual(
							this.getHttpSession().getId(),
							this.cliente.getCodigoCliente());
			if (bloqueo == null) {
				this.aniadirMensajeError(this.tituloMensajes,
						"Cliente no registra restricción");
				this.accionEliminar = true;
			} else {
				this.nombreCliente = this.cliente.getNombres();
				this.accionEliminar = false;
			}
		}
	}

	public void eliminarBloqueo(ActionEvent event) {
		this.bloqueo.setEstado("I");
		this.bloqueo.setFechaActualizacion(new Date());
		this.bloqueo.setActualizadoPor(this.getUsuarioAutenticado().toString());

		Integer resultado = this.serviciosBloqueosPorValidacion
				.getIServicioBloqueoPorValidacionWs().actualizacionBloqueo(
						this.getHttpSession().getId(), bloqueo);
		if (resultado == 1) {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} else {
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public String getControl() {
		this.inicializacion();
		return "";
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
