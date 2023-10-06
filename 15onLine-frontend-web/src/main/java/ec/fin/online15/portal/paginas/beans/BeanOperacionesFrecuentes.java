package ec.fin.online15.portal.paginas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebTiposTransaccion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;
import ec.fin.online15.integracion.beans.BeanServiciosOperacionesFrecuentes;
import ec.fin.online15.integracion.beans.BeanServiciosTransaccionCliente;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("operacionesFrecuentesMB")
@SessionScoped
public class BeanOperacionesFrecuentes extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanServiciosOperacionesFrecuentes servicioOperacionFrecuente;

	@Inject
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanServiciosTransaccionCliente servicioTransaccion;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	private TwebUsuario usuario;
	private List<TwebOperacionesFrecuentes> listaOperacionesVigentes;
	private List<TwebTiposTransaccion> listaTiposTransaccion;
	private TwebOperacionesFrecuentes operacionSeleccionada;
	private Long transaccionSeleccionada;
	private boolean accionEliminar;
	private List<ListadoOpciones> listadoCuentas;
	private List<ListadoOpciones> listaTipoCuenta;
	private List<ListadoOpciones> listaFinancieras;

	@PostConstruct
	private void init() {
		this.inicializar();
	}

	public void inicializar() {
		this.usuario = new TwebUsuario();
		this.listaOperacionesVigentes = new ArrayList<TwebOperacionesFrecuentes>();
		this.listaTiposTransaccion = new ArrayList<TwebTiposTransaccion>();
		this.operacionSeleccionada = new TwebOperacionesFrecuentes();
		this.listadoCuentas = new ArrayList<ListadoOpciones>();
		this.listaTipoCuenta = new ArrayList<ListadoOpciones>();
		this.listaFinancieras = new ArrayList<ListadoOpciones>();

		this.transaccionSeleccionada = 1L;
		this.usuario = this.servicioUsuario.getTwebUsuario();
		this.listaOperacionesVigentes = this.servicioOperacionFrecuente
				.getIServicioOperacionesFrecuentesWs()
				.operacionesFrecuentesPorUsuario(this.getHttpSession().getId(),
						this.usuario, null);
		this.listaTiposTransaccion = this.servicioOperacionFrecuente
				.getIServicioOperacionesFrecuentesWs().tiposTransaccion(
						this.getHttpSession().getId());
		this.accionEliminar = true;
		this.listadoCuentas = this.servicioTransaccion
				.getIServicioTransaccionClienteWs().listaCuentasCliente(
						this.getHttpSession().getId(),
						this.usuario.getCodigoCliente());
		this.listaTipoCuenta = this.servicioTransaccion
				.getIServicioTransaccionClienteWs().listadoTipoCuenta(
						this.getHttpSession().getId());
		this.listaFinancieras = this.servicioTransaccion
				.getIServicioTransaccionClienteWs()
				.listadoEntidadesFinancieras(this.getHttpSession().getId());
	}

	public void cargaActualizacion() {
		this.transaccionSeleccionada = this.operacionSeleccionada
				.getTipoTransaccion().getId();
	}

	public void guardarOperacion(ActionEvent evento) {
		TwebTiposTransaccion transaccion = new TwebTiposTransaccion();
		for (TwebTiposTransaccion trx : this.listaTiposTransaccion) {
			if (trx.getId().toString()
					.equals(this.transaccionSeleccionada.toString())) {
				transaccion = trx;
				break;
			}
		}
		if (this.operacionSeleccionada.getId() == null) {
			this.operacionSeleccionada.setTipoTransaccion(transaccion);
			this.operacionSeleccionada.setUsuario(usuario);
			this.operacionSeleccionada.setEstado("A");
			this.operacionSeleccionada.setFechaRegistro(new Date());
			this.operacionSeleccionada.setRegistradoPor(this
					.getUsuarioAutenticado().toString());
			// System.out.println("TIPO: "
			// + operacionSeleccionada.getTipoTransaccion().getId()
			// + " "
			// + operacionSeleccionada.getTipoTransaccion()
			// .getObservacion());
			if (this.servicioOperacionFrecuente
					.getIServicioOperacionesFrecuentesWs().guardarOperacion(
							this.getHttpSession().getId(),
							this.operacionSeleccionada) == 1) {
				this.configuracionesGenerales.mensajeTransaccionExitosa();
			} else {
				this.configuracionesGenerales.mensajeTransaccionError();
			}
		} else {
			this.operacionSeleccionada.setTipoTransaccion(transaccion);
			this.operacionSeleccionada.setFechaActualizacion(new Date());
			this.operacionSeleccionada.setActualizadoPor(this
					.getUsuarioAutenticado().toString());
			if (this.servicioOperacionFrecuente
					.getIServicioOperacionesFrecuentesWs().actualizarOperacion(
							this.getHttpSession().toString(),
							this.operacionSeleccionada) == 1) {
				this.configuracionesGenerales.mensajeTransaccionExitosa();
			} else {
				this.configuracionesGenerales.mensajeTransaccionError();
			}
		}

		this.inicializar();
	}

	public void eliminarOperacion(ActionEvent evento) {
		if (this.operacionSeleccionada.getId() != null) {
			this.operacionSeleccionada.setEstado("I");
			this.operacionSeleccionada.setFechaActualizacion(new Date());
			this.operacionSeleccionada.setActualizadoPor(this
					.getUsuarioAutenticado().toString());
			if (this.servicioOperacionFrecuente
					.getIServicioOperacionesFrecuentesWs().actualizarOperacion(
							this.getHttpSession().toString(),
							this.operacionSeleccionada) == 1) {
				this.configuracionesGenerales.mensajeTransaccionExitosa();
			} else {
				this.configuracionesGenerales.mensajeTransaccionError();
			}
		}
		this.inicializar();
	}

	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

	public List<TwebOperacionesFrecuentes> getListaOperacionesVigentes() {
		return listaOperacionesVigentes;
	}

	public void setListaOperacionesVigentes(
			List<TwebOperacionesFrecuentes> listaOperacionesVigentes) {
		this.listaOperacionesVigentes = listaOperacionesVigentes;
	}

	public TwebOperacionesFrecuentes getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(
			TwebOperacionesFrecuentes operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public List<TwebTiposTransaccion> getListaTiposTransaccion() {
		return listaTiposTransaccion;
	}

	public void setListaTiposTransaccion(
			List<TwebTiposTransaccion> listaTiposTransaccion) {
		this.listaTiposTransaccion = listaTiposTransaccion;
	}

	public Long getTransaccionSeleccionada() {
		return transaccionSeleccionada;
	}

	public void setTransaccionSeleccionada(Long transaccionSeleccionada) {
		this.transaccionSeleccionada = transaccionSeleccionada;
	}

	public List<ListadoOpciones> getListadoCuentas() {
		return listadoCuentas;
	}

	public void setListadoCuentas(List<ListadoOpciones> listadoCuentas) {
		this.listadoCuentas = listadoCuentas;
	}

	public List<ListadoOpciones> getListaTipoCuenta() {
		return listaTipoCuenta;
	}

	public void setListaTipoCuenta(List<ListadoOpciones> listaTipoCuenta) {
		this.listaTipoCuenta = listaTipoCuenta;
	}

	public List<ListadoOpciones> getListaFinancieras() {
		return listaFinancieras;
	}

	public void setListaFinancieras(List<ListadoOpciones> listaFinancieras) {
		this.listaFinancieras = listaFinancieras;
	}

	/***********/

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
	}

}
