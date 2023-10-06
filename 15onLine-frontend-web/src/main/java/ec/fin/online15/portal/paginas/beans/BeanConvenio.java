package ec.fin.online15.portal.paginas.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.InformacionCliente;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.integracion.beans.BeanServiciosConvenio;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("convenioWebMB")
@SessionScoped
public class BeanConvenio extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosConvenio servicioConvenio;

	private String control;
	private String tituloMensajes;
	private String cedula;
	private InformacionCliente informacionCliente;
	private TwebConvenios convenio;
	private List<TwebConvenios> listaConveniosVigentes;
	private List<TwebConvenios> listaConveniosFiltrados;
	private TwebConvenios convenioSeleccionado;
	private String numeroConvenio;
	private boolean accionGuardar;
	private boolean accionImprimir;
	private boolean accionEliminar;

	@PostConstruct
	public void init() {
		this.inicializacion();
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().getSessionMap().remove("nombreDelBeanEnSesion");
		// System.out.println(context.getExternalContext().getSessionMap());
	}

	public void inicializacion() {
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		this.accionGuardar = true;
		this.accionImprimir = true;
		this.accionEliminar = true;
		this.cedula = null;
		this.informacionCliente = new InformacionCliente();
		this.convenio = new TwebConvenios();
		this.numeroConvenio = "";
		this.convenioSeleccionado = new TwebConvenios();
		this.listaConveniosVigentes = this.servicioConvenio
				.getIServicioConvenioWs().listaConveniosVigentes(
						this.getHttpSession().getId());
	}

	public String getControl() {
		this.inicializacion();
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public InformacionCliente getInformacionCliente() {
		return informacionCliente;
	}

	public void setInformacionCliente(InformacionCliente informacionCliente) {
		this.informacionCliente = informacionCliente;
	}

	public boolean isAccionGuardar() {
		return accionGuardar;
	}

	public void setAccionGuardar(boolean accionGuardar) {
		this.accionGuardar = accionGuardar;
	}

	public boolean isAccionImprimir() {
		return accionImprimir;
	}

	public void setAccionImprimir(boolean accionImprimir) {
		this.accionImprimir = accionImprimir;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public String getNumeroConvenio() {
		return numeroConvenio;
	}

	public void setNumeroConvenio(String numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	public TwebConvenios getConvenio() {
		return convenio;
	}

	public void setConvenio(TwebConvenios convenio) {
		this.convenio = convenio;
	}

	public List<TwebConvenios> getListaConveniosVigentes() {
		return listaConveniosVigentes;
	}

	public void setListaConveniosVigentes(
			List<TwebConvenios> listaConveniosVigentes) {
		this.listaConveniosVigentes = listaConveniosVigentes;
	}

	public List<TwebConvenios> getListaConveniosFiltrados() {
		return listaConveniosFiltrados;
	}

	public void setListaConveniosFiltrados(
			List<TwebConvenios> listaConveniosFiltrados) {
		this.listaConveniosFiltrados = listaConveniosFiltrados;
	}

	public TwebConvenios getConvenioSeleccionado() {
		return convenioSeleccionado;
	}

	public void setConvenioSeleccionado(TwebConvenios convenioSeleccionado) {
		this.convenioSeleccionado = convenioSeleccionado;
	}

	public String getFechaConvenio() {
		if (this.convenio != null) {
			if (this.convenioSeleccionado.getFechaRegistro() != null) {
				return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format((this.convenioSeleccionado.getFechaRegistro())));
			} else if (this.convenio.getFechaRegistro() != null) {
				return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format((this.convenio.getFechaRegistro())));
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String mesLetras(String mes) {
		switch (Integer.valueOf(mes)) {
		case 1:
			return "enero";
		case 2:
			return "febrero";
		case 3:
			return "marzo";
		case 4:
			return "abril";
		case 5:
			return "mayo";
		case 6:
			return "junio";
		case 7:
			return "julio";
		case 8:
			return "agosto";
		case 9:
			return "septiembre";
		case 10:
			return "octubre";
		case 11:
			return "noviembre";
		default:
			return "diciembre";
			// break;
		}
	}

	public String getFechaFormateada() {
		String resultado = "";
		if (this.convenio != null) {
			if (this.convenioSeleccionado.getFechaRegistro() != null) {
				resultado += new SimpleDateFormat("dd")
						.format((this.convenioSeleccionado.getFechaRegistro()))
						+ " días del mes de "
						+ this.mesLetras(new SimpleDateFormat("MM")
								.format((this.convenioSeleccionado
										.getFechaRegistro())))
						+ " del año "
						+ new SimpleDateFormat("yyyy")
								.format((this.convenioSeleccionado
										.getFechaRegistro()));
			} else if (this.convenio.getFechaRegistro() != null) {
				resultado += new SimpleDateFormat("dd").format((this.convenio
						.getFechaRegistro()))
						+ " días del mes de "
						+ this.mesLetras(new SimpleDateFormat("MM")
								.format((this.convenio.getFechaRegistro())))
						+ " del año "
						+ new SimpleDateFormat("yyyy").format((this.convenio
								.getFechaRegistro()));
			} else {
				resultado = "";
			}
		} else {
			resultado = "";
		}
		return resultado;
	}

	public void buscarCliente(ActionEvent evento) {
		this.numeroConvenio = "";
		this.informacionCliente = this.servicioConvenio
				.getIServicioConvenioWs().consultaCliente(
						this.getHttpSession().getId(), this.cedula);
		if (this.informacionCliente.getCodigoCliente() == 0) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Cliente no existe, o tiene un contrato vigente");
			this.accionGuardar = true;
		} else {
			this.accionGuardar = false;
		}
	}

	public void registrarConvenio(ActionEvent evento) {
		convenio = new TwebConvenios();
		convenio.setCodigoCliente(informacionCliente.getCodigoCliente());
		convenio.setNumeroIdentificacion(this.cedula);
		convenio.setNombreCliente(this.informacionCliente.getNombreCliente());
		convenio.setEstado("A");
		convenio.setFechaRegistro(new Date());
		convenio.setRegistradoPor(this.getUsuarioAutenticado().toString());
		this.numeroConvenio = this.servicioConvenio.getIServicioConvenioWs()
				.registrarConvenio(this.getHttpSession().getId(), convenio);
		if (this.numeroConvenio == null) {
			this.configuracionesGenerales.mensajeTransaccionError();
			this.accionImprimir = true;
		} else {
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			this.accionImprimir = false;
			// this.aniadirMensajeInformacion("", this.numeroConvenio);
			// this.inicializacion();
		}
	}

	public void anularConvenio(ActionEvent event) {
		try {
			this.convenioSeleccionado.setFechaActualizacion(new Date());
			this.convenioSeleccionado.setActualizadoPor(this
					.getUsuarioAutenticado().toString());
			this.convenioSeleccionado.setEstado("I");
			this.servicioConvenio.getIServicioConvenioWs().actualizarConvenio(
					this.getHttpSession().getId(), this.convenioSeleccionado);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			this.inicializacion();
		} catch (Exception e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
		this.accionImprimir = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
		this.accionImprimir = true;
	}
}
