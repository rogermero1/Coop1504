package ec.fin.online15.aplicacion.paginas;

import java.io.Serializable;
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
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcionRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoOpcion;
import ec.fin.online15.integracion.beans.BeanServiciosOpcionRol;
import ec.fin.online15.integracion.beans.BeanServiciosRol;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("administracionOpcionRolMB")
@SessionScoped
public class BeanOpcionesRoles extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosRol servicioRol;

	@Inject
	private BeanServiciosMantenimientoOpcion servicioOpcion;

	@Inject
	private BeanServiciosOpcionRol servicioOpcionRol;

	private List<TwebRol> listaRoles;
	private List<TwebOpcion> listaOpciones;
	private List<TwebOpcionRol> listaOpcionesRoles;
	private Long idRolSeleccionado;
	private Long idOpcionSeleccionada;
	private TwebOpcionRol opcionRol;
	private String tituloMensajes;
	private boolean accionEliminar;

	@PostConstruct
	private void init() {
		inicializacion();
	}

	public void inicializacion() {
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		listaRoles = servicioRol.getIServicioRolWs().listaRolesVigentes(
				this.getHttpSession().getId());
		listaOpciones = servicioOpcion.getIServicioOpcionesMenuWs()
				.listaOpcionesVigentes(this.getHttpSession().getId());
		listaOpcionesRoles = servicioOpcionRol.getIServicioOpcionRolWs()
				.listaOpcionesRolesVigentes(this.getHttpSession().getId());
		idRolSeleccionado = null;
		idOpcionSeleccionada = null;
		opcionRol = new TwebOpcionRol();
		accionEliminar = true;
	}

	public List<TwebRol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<TwebRol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<TwebOpcion> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<TwebOpcion> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

	public List<TwebOpcionRol> getListaOpcionesRoles() {
		return listaOpcionesRoles;
	}

	public void setListaOpcionesRoles(List<TwebOpcionRol> listaOpcionesRoles) {
		this.listaOpcionesRoles = listaOpcionesRoles;
	}

	public Long getIdRolSeleccionado() {
		return idRolSeleccionado;
	}

	public void setIdRolSeleccionado(Long idRolSeleccionado) {
		this.idRolSeleccionado = idRolSeleccionado;
	}

	public Long getIdOpcionSeleccionada() {
		return idOpcionSeleccionada;
	}

	public void setIdOpcionSeleccionada(Long idOpcionSeleccionada) {
		this.idOpcionSeleccionada = idOpcionSeleccionada;
	}

	public TwebOpcionRol getOpcionRol() {
		return opcionRol;
	}

	public void setOpcionRol(TwebOpcionRol opcionRol) {
		this.opcionRol = opcionRol;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public void guardarOpcionRol(ActionEvent evento) {
		try {
			TwebOpcion opcion = new TwebOpcion();
			TwebRol rol = new TwebRol();
			opcion = servicioOpcion.getIServicioOpcionesMenuWs().buscarPorId(
					this.getHttpSession().getId(), idOpcionSeleccionada);
			rol = servicioRol.getIServicioRolWs().buscarPorId(
					this.getHttpSession().getId(), idRolSeleccionado);

			opcionRol = new TwebOpcionRol();
			opcionRol.setEstado("A");
			opcionRol.setOpciones(opcion);
			opcionRol.setRol(rol);
			opcionRol.setFechaRegistro(new Date());
			opcionRol.setRegistradoPor(this.getUsuarioAutenticado().toString());
			this.servicioOpcionRol.getIServicioOpcionRolWs().crearOpcionRol(
					this.getHttpSession().getId(), opcionRol);
			inicializacion();
			this.configuracionesGenerales.mensajeTransaccionExitosa();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void eliminarOpcionRol(ActionEvent evento) {
		try {
			if (opcionRol != null) {
				if (opcionRol.getId() != null) {
					opcionRol.setEstado("I");
					opcionRol.setFechaActualizacion(new Date());
					opcionRol.setActualizadoPor(this.getUsuarioAutenticado()
							.toString());
					servicioOpcionRol.getIServicioOpcionRolWs()
							.actualizarOpcionRol(this.getHttpSession().getId(),
									opcionRol);
					inicializacion();
					this.configuracionesGenerales.mensajeTransaccionExitosa();
				} else {
					this.configuracionesGenerales.aniadirMensajeError(
							this.tituloMensajes,
							"No hay registro para eliminar");
				}
			} else {
				this.configuracionesGenerales.aniadirMensajeError(
						this.tituloMensajes, "No hay registro para eliminar");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
	}
}
