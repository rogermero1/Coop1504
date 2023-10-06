package ec.fin.online15.aplicacion.paginas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoOpcion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("administracionOpcionMB")
@SessionScoped
public class BeanAdministracionOpciones extends BaseManagedBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosMantenimientoOpcion servicioMantenimientoOpcion;

	private List<TwebOpcion> listaOpcionesVigentes;
	private List<String> listaOpcionesPadre;
	private TwebOpcion opcion;
	private TwebOpcion opcionPadre;
	private String idPadre;
	private String tituloMensajes;
	private boolean accionEliminar;

	@PostConstruct
	private void init() {
		inicializacion();
	}

	public void inicializacion() {
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		opcion = new TwebOpcion();
		opcion.setId(null);
		opcionPadre = new TwebOpcion();
		idPadre = null;
		listaOpcionesVigentes = servicioMantenimientoOpcion
				.getIServicioOpcionesMenuWs().listaOpcionesVigentes(
						this.getHttpSession().getId());
		listaOpcionesPadre = new ArrayList<String>();
		accionEliminar = true;
		cargarOpcionesPadre();
	}

	private void cargarOpcionesPadre() {
		listaOpcionesPadre.add("0 Sin Opc Padre ");
		for (TwebOpcion listao : listaOpcionesVigentes) {
			listaOpcionesPadre.add(listao.getId() + " " + listao.getOpcion());
		}
	}

	public List<TwebOpcion> getListaOpcionesVigentes() {
		return listaOpcionesVigentes;
	}

	public void setListaOpcionesVigentes(List<TwebOpcion> listaOpcionesVigentes) {
		this.listaOpcionesVigentes = listaOpcionesVigentes;
	}

	public List<String> getListaOpcionesPadre() {
		return listaOpcionesPadre;
	}

	public void setListaOpcionesPadre(List<String> listaOpcionesPadre) {
		this.listaOpcionesPadre = listaOpcionesPadre;
	}

	public TwebOpcion getOpcion() {
		return opcion;
	}

	public void setOpcion(TwebOpcion opcion) {
		this.opcion = opcion;
	}

	public String getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public void cargaPadre() {
		Long id = null;
		if (opcion.getOpcionPadre() != null) {
			id = opcion.getOpcionPadre().getId();
		} else {
			id = (long) 0;
		}
		opcionPadre = servicioMantenimientoOpcion.getIServicioOpcionesMenuWs()
				.buscarPorId(this.getHttpSession().getId(), id);
		if (opcionPadre != null)
			idPadre = opcionPadre.getId() + " " + opcionPadre.getOpcion();
		else
			idPadre = "0 Sin Opc Padre ";
	}

	public String buscaPadre(Long id) {
			
		if (id==null)
			return "0 Sin Opc Padre ";
		TwebOpcion padre = servicioMantenimientoOpcion
				.getIServicioOpcionesMenuWs().buscarPorId(
						this.getHttpSession().getId(), id);
		if (padre != null)
			return padre.getId() + " " + padre.getOpcion();
		else
			return "0 Sin Opc Padre ";
	}

	public void guardarOpcion(ActionEvent evento) {
		try {
			StringTokenizer st = new StringTokenizer(idPadre, " ");
			idPadre = st.nextToken();
			if (idPadre != null) {
				opcionPadre = servicioMantenimientoOpcion
						.getIServicioOpcionesMenuWs().buscarPorId(
								this.getHttpSession().getId(),
								Long.parseLong(idPadre));
			}
			opcion.setOpcionPadre(opcionPadre);
			if (opcion.getId() != null) {
				opcion.setFechaActualizacion(new Date());
				opcion.setActualizadoPor(this.getUsuarioAutenticado()
						.toString());
				this.servicioMantenimientoOpcion
						.getIServicioOpcionesMenuWs()
						.actualizarOpcion(this.getHttpSession().getId(), opcion);
			} else {
				opcion.setEstado("A");
				opcion.setAuditoria("USUARIO REGISTRO : "
						+ this.getNombreUsuarioAutenticado());
				opcion.setFechaRegistro(new Date());
				opcion.setRegistradoPor(this.getUsuarioAutenticado().toString());
				servicioMantenimientoOpcion.getIServicioOpcionesMenuWs()
						.crearOpcion(this.getHttpSession().getId(), opcion);
			}
			inicializacion();
			this.configuracionesGenerales.mensajeTransaccionExitosa();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void eliminarOpcion(ActionEvent evento) {
		try {
			if (opcion != null) {
				if (opcion.getId() != null) {
					opcion.setEstado("I");
					opcion.setFechaActualizacion(new Date());
					opcion.setActualizadoPor(this.getUsuarioAutenticado()
							.toString());
					servicioMantenimientoOpcion.getIServicioOpcionesMenuWs()
							.actualizarOpcion(this.getHttpSession().getId(),
									opcion);
					this.configuracionesGenerales.mensajeTransaccionExitosa();
					inicializacion();
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
