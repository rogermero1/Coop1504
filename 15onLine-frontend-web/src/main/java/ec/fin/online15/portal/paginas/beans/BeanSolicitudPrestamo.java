package ec.fin.online15.portal.paginas.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.EstadoSituacionCliente;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamo;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosSolicitud;
import ec.fin.online15.integracion.beans.BeanServiciosParametrosSolicitud;
import ec.fin.online15.integracion.beans.BeanServiciosSolicitudPrestamo;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("solicitudPrestamoMB")
@SessionScoped
public class BeanSolicitudPrestamo extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanConsultaPosicionConsolidada posicionConsolidada;

	@Inject
	private BeanServiciosSolicitudPrestamo servicioSolicitud;

	@Inject
	private BeanServiciosParametrosSolicitud servicioParametros;

	private DatosCliente datosCliente;
	private SolicitudPrestamo solicitud;
	private List<EstadoSituacionCliente> listaIngresos;
	private List<EstadoSituacionCliente> listaGastos;
	private Boolean aceptaAutorizacion;

	private String nombreArchivo;

	private String cargaCedula;
	private String cargaVotacion;
	private String cargaIngresos;

	@PostConstruct
	private void init() {
		this.inicializar();
	}

	public void inicializar() {
		this.solicitud = new SolicitudPrestamo();
		this.solicitud.setCodigoCliente(posicionConsolidada.getCodigoCliente());
		this.solicitud.setMontoSolicitado(0D);
		this.solicitud.setPlazo(0);
		this.solicitud.setTasa(0D);
		this.solicitud.setCuota(0D);
		// 3-alemana(variable)4-frances (fija)
		this.solicitud.setTipoAmortizacion(4);
		this.solicitud
				.setPreAprobado(posicionConsolidada.isPreaprobado() == true ? "S"
						: "N");

		this.aceptaAutorizacion = false;

		this.cargaCedula = this.cargaVotacion = this.cargaIngresos = "N";

		this.datosCliente = servicioSolicitud.getIServicioSolicitudPrestamoWs()
				.consultaDatosCliente(this.getHttpSession().getId(),
						posicionConsolidada.getCodigoCliente());
		this.listaIngresos = servicioSolicitud
				.getIServicioSolicitudPrestamoWs()
				.listaEstadoFinancieroCliente(this.getHttpSession().getId(), 1,
						posicionConsolidada.getCodigoCliente());
		this.listaGastos = servicioSolicitud.getIServicioSolicitudPrestamoWs()
				.listaEstadoFinancieroCliente(this.getHttpSession().getId(), 2,
						posicionConsolidada.getCodigoCliente());

	}

	public void simular(ActionEvent evento) {
		System.out.println("Consultando parámetros:\nMonto: "
				+ this.solicitud.getMontoSolicitado() + "\nPlazo: "
				+ this.solicitud.getPlazo() + "\nAmortizacion:= "
				+ this.solicitud.getTipoAmortizacion() + "\nDependencia: "
				+ this.datosCliente.getRelacionDependencia()
				+ "\nPreaprobado: " + this.solicitud.getPreAprobado());

		TwebParametrosSolicitud parametro = this.servicioParametros
				.getIServicioParametrosSolicitudWs()
				.buscaParametro(
						this.getHttpSession().getId(),
						this.datosCliente.getRelacionDependencia().equals("SI") ? "S"
								: "N", this.solicitud.getPreAprobado(),
						this.solicitud.getMontoSolicitado());

		// calculo default $10000 a 12 meses
		if (this.solicitud.getMontoSolicitado() == 0D) {
			this.solicitud.setMontoSolicitado(1000D);
		}
		if (this.solicitud.getPlazo() == 0D) {
			this.solicitud.setPlazo(12);
		}

		if (parametro != null) {
			this.solicitud.setTasa(parametro.getTasa());
			Double tasa = (this.solicitud.getTasa() / 36000) * 30; // mensual
			if (this.solicitud.getTipoAmortizacion() == 4) {
				this.solicitud.setCuota((this.solicitud.getMontoSolicitado()
						* tasa * Math.pow(1 + tasa, this.solicitud.getPlazo()))
						/ (Math.pow(1 + tasa, this.solicitud.getPlazo()) - 1)

				);
			} else {
				this.solicitud
						.setCuota((this.solicitud.getMontoSolicitado() / this.solicitud
								.getPlazo())
								+ (this.solicitud.getMontoSolicitado() * tasa));
			}
			this.solicitud
					.setCuota(Math.round(this.solicitud.getCuota() * 100d) / 100d);
		} else {
			this.solicitud.setTasa(0D);
			this.solicitud.setCuota(0D);
		}

	}

	public void asignarNombreArchivo(String nombre) {
		// System.out.println("recibe" + nombre);
		this.nombreArchivo = this.datosCliente.getIdentificacion() + "-"
				+ nombre;
		System.out.println("Asigando nombre al archivo... "
				+ this.nombreArchivo);
	}

	public void guardar(ActionEvent evento) {
		SolicitudPrestamo resultado = new SolicitudPrestamo();

		if (this.solicitud.getMontoSolicitado() == 0D
				|| this.solicitud.getPlazo() == 0D
				|| this.solicitud.getCuota() == 0D
				|| this.solicitud.getProposito().length() == 0) {
			this.aniadirMensajeError("Para continuar debe ingresar monto/plazo/propósito");
		} else if (this.cargaCedula.equals("N")
				|| this.cargaVotacion.equals("N")
				|| this.cargaIngresos.equals("N")) {
			this.aniadirMensajeError("Para continuar debe cargar los documentos solicitados");
		} else {
			String estado = "";
			for (EstadoSituacionCliente e : this.listaIngresos) {
				if (e.getValor() != e.getValorNuevo()) {
					estado += e.getCodigoEstado() + ";"
							+ e.getCodigoTipoEstado() + ";" + e.getValorNuevo()
							+ "|";
				}
			}
			for (EstadoSituacionCliente e : this.listaGastos) {
				if (e.getValor() != e.getValorNuevo()) {
					estado += e.getCodigoEstado() + ";"
							+ e.getCodigoTipoEstado() + ";" + e.getValorNuevo()
							+ "|";
				}
			}
			if (estado.length() > 0) {
				estado = estado.substring(0, estado.length() - 1);
			}
			this.solicitud.setEstadosSituacion(estado);

			resultado = this.servicioSolicitud
					.getIServicioSolicitudPrestamoWs().registrarSolicitud(
							this.getHttpSession().getId(), this.solicitud);
			if (resultado.getCodigoError() == null
					|| resultado.getCodigoError().equals("")) {
				this.configuracionesGenerales.mensajeTransaccionExitosa();
				this.inicializar();
			} else {
				System.out.println("Error al registrar solicitud: "
						+ resultado.getCodigoError());
				if (resultado.getCodigoError().equals("59")) {
					this.aniadirMensajeError("Ya tiene una solicitud en trámite, un asesor se comunicará con ud");
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
				}
			}
		}
	}

	public void aceptarCondiciones() {
		System.out.println("Acepta Condiciones  " + this.aceptaAutorizacion);
	}

	public DatosCliente getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(DatosCliente datosCliente) {
		this.datosCliente = datosCliente;
	}

	public List<EstadoSituacionCliente> getListaIngresos() {
		return listaIngresos;
	}

	public void setListaIngresos(List<EstadoSituacionCliente> listaIngresos) {
		this.listaIngresos = listaIngresos;
	}

	public List<EstadoSituacionCliente> getListaGastos() {
		return listaGastos;
	}

	public void setListaGastos(List<EstadoSituacionCliente> listaGastos) {
		this.listaGastos = listaGastos;
	}

	public SolicitudPrestamo getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudPrestamo solicitud) {
		this.solicitud = solicitud;
	}

	public Boolean getAceptaAutorizacion() {
		return aceptaAutorizacion;
	}

	public void setAceptaAutorizacion(Boolean aceptaAutorizacion) {
		this.aceptaAutorizacion = aceptaAutorizacion;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getCargaCedula() {
		return cargaCedula;
	}

	public void setCargaCedula(String cargaCedula) {
		this.cargaCedula = cargaCedula;
	}

	public String getCargaVotacion() {
		return cargaVotacion;
	}

	public void setCargaVotacion(String cargaVotacion) {
		this.cargaVotacion = cargaVotacion;
	}

	public String getCargaIngresos() {
		return cargaIngresos;
	}

	public void setCargaIngresos(String cargaIngresos) {
		this.cargaIngresos = cargaIngresos;
	}

}
