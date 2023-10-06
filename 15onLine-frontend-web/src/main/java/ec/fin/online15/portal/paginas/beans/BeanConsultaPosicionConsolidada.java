package ec.fin.online15.portal.paginas.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
//import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
//import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.CuotasPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaAhorro;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaInversion;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaSeguro;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ProductosPreAprobados;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.SaldosBloqueados;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.SaldosReserva;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.UltimosMovimientosAhorro;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosPosicionConsolidada;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("posicionConsolidadaMB")
@SessionScoped
public class BeanConsultaPosicionConsolidada extends BaseManagedBean {

	private static final long serialVersionUID = 1L;
	private Integer codigoCliente;
	private List<PosicionConsolidadaAhorro> posicionConsolidadaAhorro;
	private List<PosicionConsolidadaPrestamo> posicionConsolidadaPrestamo;
	private List<PosicionConsolidadaInversion> posicionConsolidadaInversion;
	private List<PosicionConsolidadaSeguro> posicionConsolidadaSeguro;
	private List<UltimosMovimientosAhorro> listadoUltimosMovimientos;
	private List<CuotasPrestamo> listadoCuotasPrestamo;
	private List<CuotasPrestamo> listadoCuotasGracia;
	private List<SaldosBloqueados> listaSaldosBloqueados;
	private List<SaldosReserva> listaSaldosReserva;
	private List<TwebMovimientos> listadoMovimientosWeb;

	private String detalleMovimientoAhorro;

	private PosicionConsolidadaAhorro cuentaSeleccionada;
	private PosicionConsolidadaPrestamo prestamoSeleccionado;

	private ProductosPreAprobados productosPreAprobados;
	private boolean preaprobado;

	private boolean muestraCuentas;
	private boolean muestraPrestamos;
	private boolean muestraSeguros;
	private boolean muestraInversiones;

	private boolean mostrarMensajeSeguridad;

	private Date fechaDesde;
	private Date fechaHasta;
	private Integer codigoTipoTransaccion;
	private SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

	private boolean transferenciaLocal = false;
	private boolean transferenciaOtroBanco = false;
	private boolean pagoTarjeta = false;
	private boolean pagoPrestamo = false;
	private boolean pagoServicio = false;
	private String sesion = "";
	private Long idUsuario;

	private TwebParametrosGenerales parametroGeneral;
	private Integer mesesHistoricoAhorros;
	private Date fechaMinHistorico;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanServiciosPosicionConsolidada servicioPosicionConsolidada;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales servicioMantenimientoParametroGeneral;

	@PostConstruct
	private void init() {
		this.mostrarMensajeSeguridad = true;
		this.inicializar();
	}

	public void inicializar() {

		parametroGeneral = new TwebParametrosGenerales();
		List<TwebParametrosGenerales> listaParametrosGenerales = this.servicioMantenimientoParametroGeneral
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(this.getHttpSession().getId());
		if (listaParametrosGenerales != null) {
			parametroGeneral = listaParametrosGenerales.get(0);

			this.mesesHistoricoAhorros = parametroGeneral.getHistoricoAhorros();

			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -this.mesesHistoricoAhorros);
			this.fechaMinHistorico = c.getTime();

		} else {
			this.aniadirMensajeError(
					configuracionesGenerales.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES),
					"Parámetros generales");
		}

		this.muestraCuentas = this.muestraPrestamos = this.muestraInversiones = this.muestraSeguros = false;
		this.codigoCliente = servicioUsuario.getTwebUsuario().getCodigoCliente();
		this.idUsuario = servicioUsuario.getTwebUsuario().getId();

		cuentaSeleccionada = new PosicionConsolidadaAhorro();
		posicionConsolidadaAhorro = new ArrayList<PosicionConsolidadaAhorro>();
		posicionConsolidadaPrestamo = new ArrayList<PosicionConsolidadaPrestamo>();
		posicionConsolidadaInversion = new ArrayList<PosicionConsolidadaInversion>();
		posicionConsolidadaSeguro = new ArrayList<PosicionConsolidadaSeguro>();
		listadoMovimientosWeb = new ArrayList<TwebMovimientos>();

		productosPreAprobados = new ProductosPreAprobados();
		preaprobado = false;

		this.detalleMovimientoAhorro = null;

		posicionConsolidadaAhorro = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaAhorro(this.getHttpSession().getId(), this.codigoCliente);
		posicionConsolidadaPrestamo = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaPrestamo(this.getHttpSession().getId(), this.codigoCliente);
		posicionConsolidadaInversion = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaInversion(this.getHttpSession().getId(), this.codigoCliente);
		posicionConsolidadaSeguro = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaSeguro(this.getHttpSession().getId(), this.codigoCliente);

		productosPreAprobados = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.productosPreaprobados(this.getHttpSession().getId(), this.codigoCliente);

		this.preaprobado = this.revisaPreaprobado();

		this.muestraCuentas = posicionConsolidadaAhorro.isEmpty() ? false : true;
		this.muestraPrestamos = posicionConsolidadaPrestamo.isEmpty() ? false : true;
		this.muestraInversiones = posicionConsolidadaInversion.isEmpty() ? false : true;
		this.muestraSeguros = posicionConsolidadaSeguro.isEmpty() ? false : true;

		// if (this.preaprobado)
		// this.aniadirMensajeInformacion("Usted tiene un crédito preaprobado.");

		if (this.mostrarMensajeSeguridad) {
			this.mostrarMensajeSeguridad = false;
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recomendaciones de seguridad",
//					"Echoes in eternity.");
//			PrimeFaces.current().dialog().showMessageDynamic(message);
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('dlgSeguridad').show();");
		}

		if (this.preaprobado)
			this.aniadirMensajeInformacion("Usted tiene un crédito preaprobado.");

	}

	public void ultimosMovimientosAhorro() {
		listadoUltimosMovimientos = new ArrayList<UltimosMovimientosAhorro>();

		System.out.println(" Numero Cuenta : " + cuentaSeleccionada.getNumeroCuenta());

		listadoUltimosMovimientos = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionUltimosMovimientosAhorro(this.getHttpSession().getId(),
						cuentaSeleccionada.getNumeroCuenta());
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
				"pag_cliente_ultimos_movimientos_ahorro.jsf");
	}

	public void consultaSaldosBloqueados() {
		listaSaldosBloqueados = new ArrayList<SaldosBloqueados>();
		listaSaldosBloqueados = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaSaldosBloqueados(this.getHttpSession().getId(),
						cuentaSeleccionada.getNumeroCuenta());
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
				"pag_cliente_saldos_bloqueados.jsf");
	}

	public void consultaSaldosReserva() {
		listaSaldosReserva = new ArrayList<SaldosReserva>();
		listaSaldosReserva = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaSaldosReserva(this.getHttpSession().getId(),
						cuentaSeleccionada.getNumeroCuenta());
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
				"pag_cliente_saldos_reserva.jsf");
	}

	public void cuotasPrestamo() {
		listadoCuotasPrestamo = new ArrayList<CuotasPrestamo>();
		System.out.println(" Numero prestamo : " + prestamoSeleccionado.getNumeroPrestamo());
		listadoCuotasPrestamo = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaCuotasPrestamo(this.getHttpSession().getId(),
						prestamoSeleccionado.getNumeroPrestamo());
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
				"pag_cliente_cuotas_prestamo.jsf");
	}

	public void periodoGracia() {
		listadoCuotasGracia = new ArrayList<CuotasPrestamo>();
		System.out.println(" Numero prestamo Gracia : " + prestamoSeleccionado.getNumeroPrestamo());
		listadoCuotasGracia = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaCuotasGracia(this.getHttpSession().getId(),
						prestamoSeleccionado.getNumeroPrestamo());
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
				"pag_cliente_cuotas_gracia.jsf");
	}

	public void solicitudPrestamo() {
		// return
		// "/paginas/modulos/usuarioweb/pag_cliente_solicitud_prestamo.jsf";

		this.configuracionesGenerales.redireccionarUrl(
				this.getContextPath() + "/paginas/modulos/usuarioweb/pag_cliente_solicitud_prestamo.jsf");

		// this.configuracionesGenerales.redireccionarUrlConstante(
		// NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
		// "pag_cliente_solicitud_prestamo.jsf");
	}

	public void regresarPaginaPrincipal() {
		this.configuracionesGenerales
				.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB, "");
	}

	public void paginaHistoricoAhorros() {
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_HISTORICO_AHORROS, "");
	}

	/* Historico de transacciones web */
	public void buscaTransaccionesWeb() {
		System.out.println("Codigo tipo transacción : " + this.codigoTipoTransaccion);
		System.out.println("Fecha desde : " + formato.format(this.fechaDesde));
		System.out.println("Fecha hasta : " + formato.format(this.fechaHasta));

		this.transferenciaLocal = false;
		this.transferenciaOtroBanco = false;
		this.pagoTarjeta = false;
		this.pagoPrestamo = false;
		this.pagoServicio = false;

		if (this.codigoTipoTransaccion == 1) {
			this.transferenciaLocal = true;
		} else if (this.codigoTipoTransaccion == 2) {
			this.transferenciaOtroBanco = true;
		} else if (this.codigoTipoTransaccion == 3) {
			this.pagoTarjeta = true;
		} else if (this.codigoTipoTransaccion == 4) {
			this.pagoPrestamo = true;
		} else if (this.codigoTipoTransaccion == 5) {
			this.pagoServicio = true;
		}

		this.listadoMovimientosWeb = this.servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.listadoMovimientos(this.getHttpSession().getId(), this.idUsuario, codigoTipoTransaccion,
						formato.format(this.fechaDesde), formato.format(this.fechaHasta));
	}

	public String nombreFinanciera(Integer idTipoFinanciera, Integer codigoFinanciera) {
		return this.servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.nombreFinanciera(this.getHttpSession().getId(), idTipoFinanciera, codigoFinanciera);
	}

	public String nombreServicio(Integer codigoTipoServicio) {
		return this.servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.nombreServicio(this.getHttpSession().getId(), codigoTipoServicio);
	}

	private boolean revisaPreaprobado() {
		if (productosPreAprobados == null)
			return false;
		else if (this.productosPreAprobados.getAa().equals("S") || this.productosPreAprobados.getAaa().equals("S")
				|| this.productosPreAprobados.getAplus().equals("S")
				|| this.productosPreAprobados.getPreferencial().equals("S")
				|| this.productosPreAprobados.getPreferencial3().equals("S"))
			return true;
		else
			return false;
	}

	public void asignaDetalleMovimientoAhorro(ActionEvent evento) {
		this.detalleMovimientoAhorro = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		System.out.println(params.get("paramDetalle"));

		this.detalleMovimientoAhorro = params.get("paramDetalle");

	}

	public List<PosicionConsolidadaAhorro> getPosicionConsolidadaAhorro() {
		return posicionConsolidadaAhorro;
	}

	public void setPosicionConsolidadaAhorro(List<PosicionConsolidadaAhorro> posicionConsolidadaAhorro) {
		this.posicionConsolidadaAhorro = posicionConsolidadaAhorro;
	}

	public List<PosicionConsolidadaPrestamo> getPosicionConsolidadaPrestamo() {
		return posicionConsolidadaPrestamo;
	}

	public void setPosicionConsolidadaPrestamo(List<PosicionConsolidadaPrestamo> posicionConsolidadaPrestamo) {
		this.posicionConsolidadaPrestamo = posicionConsolidadaPrestamo;
	}

	public List<PosicionConsolidadaInversion> getPosicionConsolidadaInversion() {
		return posicionConsolidadaInversion;
	}

	public void setPosicionConsolidadaInversion(List<PosicionConsolidadaInversion> posicionConsolidadaInversion) {
		this.posicionConsolidadaInversion = posicionConsolidadaInversion;
	}

	public List<PosicionConsolidadaSeguro> getPosicionConsolidadaSeguro() {
		return posicionConsolidadaSeguro;
	}

	public void setPosicionConsolidadaSeguro(List<PosicionConsolidadaSeguro> posicionConsolidadaSeguro) {
		this.posicionConsolidadaSeguro = posicionConsolidadaSeguro;
	}

	public List<UltimosMovimientosAhorro> getListadoUltimosMovimientos() {
		return listadoUltimosMovimientos;
	}

	public void setListadoUltimosMovimientos(List<UltimosMovimientosAhorro> listadoUltimosMovimientos) {
		this.listadoUltimosMovimientos = listadoUltimosMovimientos;
	}

	public PosicionConsolidadaAhorro getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(PosicionConsolidadaAhorro cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public List<CuotasPrestamo> getListadoCuotasPrestamo() {
		return listadoCuotasPrestamo;
	}

	public void setListadoCuotasPrestamo(List<CuotasPrestamo> listadoCuotasPrestamo) {
		this.listadoCuotasPrestamo = listadoCuotasPrestamo;
	}

	public List<CuotasPrestamo> getListadoCuotasGracia() {
		return listadoCuotasGracia;
	}

	public void setListadoCuotasGracia(List<CuotasPrestamo> listadoCuotasGracia) {
		this.listadoCuotasGracia = listadoCuotasGracia;
	}

	public PosicionConsolidadaPrestamo getPrestamoSeleccionado() {
		return prestamoSeleccionado;
	}

	public void setPrestamoSeleccionado(PosicionConsolidadaPrestamo prestamoSeleccionado) {
		this.prestamoSeleccionado = prestamoSeleccionado;
	}

	public boolean isMuestraCuentas() {
		this.inicializar();
		return muestraCuentas;
	}

	public void setMuestraCuentas(boolean muestraCuentas) {
		this.muestraCuentas = muestraCuentas;
	}

	public boolean isMuestraPrestamos() {
		return muestraPrestamos;
	}

	public void setMuestraPrestamos(boolean muestraPrestamos) {
		this.muestraPrestamos = muestraPrestamos;
	}

	public boolean isMuestraSeguros() {
		return muestraSeguros;
	}

	public void setMuestraSeguros(boolean muestraSeguros) {
		this.muestraSeguros = muestraSeguros;
	}

	public boolean isMuestraInversiones() {
		return muestraInversiones;
	}

	public void setMuestraInversiones(boolean muestraInversiones) {
		this.muestraInversiones = muestraInversiones;
	}

	public List<SaldosBloqueados> getListaSaldosBloqueados() {
		return listaSaldosBloqueados;
	}

	public void setListaSaldosBloqueados(List<SaldosBloqueados> listaSaldosBloqueados) {
		this.listaSaldosBloqueados = listaSaldosBloqueados;
	}

	public List<SaldosReserva> getListaSaldosReserva() {
		return listaSaldosReserva;
	}

	public void setListaSaldosReserva(List<SaldosReserva> listaSaldosReserva) {
		this.listaSaldosReserva = listaSaldosReserva;
	}

	public List<TwebMovimientos> getListadoMovimientosWeb() {
		return listadoMovimientosWeb;
	}

	public void setListadoMovimientosWeb(List<TwebMovimientos> listadoMovimientos) {
		this.listadoMovimientosWeb = listadoMovimientos;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getCodigoTipoTransaccion() {
		return codigoTipoTransaccion;
	}

	public void setCodigoTipoTransaccion(Integer codigoTipoTransaccion) {
		this.codigoTipoTransaccion = codigoTipoTransaccion;
	}

	public boolean isTransferenciaLocal() {
		return transferenciaLocal;
	}

	public void setTransferenciaLocal(boolean transferenciaLocal) {
		this.transferenciaLocal = transferenciaLocal;
	}

	public boolean isTransferenciaOtroBanco() {
		return transferenciaOtroBanco;
	}

	public void setTransferenciaOtroBanco(boolean transferenciaOtroBanco) {
		this.transferenciaOtroBanco = transferenciaOtroBanco;
	}

	public boolean isPagoTarjeta() {
		return pagoTarjeta;
	}

	public void setPagoTarjeta(boolean pagoTarjeta) {
		this.pagoTarjeta = pagoTarjeta;
	}

	public boolean isPagoPrestamo() {
		return pagoPrestamo;
	}

	public void setPagoPrestamo(boolean pagoPrestamo) {
		this.pagoPrestamo = pagoPrestamo;
	}

	public boolean isPagoServicio() {
		return pagoServicio;
	}

	public void setPagoServicio(boolean pagoServicio) {
		this.pagoServicio = pagoServicio;
	}

	// public ProductosPreAprobados getProductosPreAprobados() {
	// return productosPreAprobados;
	// }
	//
	// public void setProductosPreAprobados(
	// ProductosPreAprobados productosPreAprobados) {
	// this.productosPreAprobados = productosPreAprobados;
	// }

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public boolean isPreaprobado() {
		return preaprobado;
	}

	public void setPreaprobado(boolean preaprobado) {
		this.preaprobado = preaprobado;
	}

	public String getDetalleMovimientoAhorro() {
		return detalleMovimientoAhorro;
	}

	public void setDetalleMovimientoAhorro(String detalleMovimientoAhorro) {
		this.detalleMovimientoAhorro = detalleMovimientoAhorro;
	}

	public Integer getMesesHistoricoAhorros() {
		return mesesHistoricoAhorros;
	}

	public void setMesesHistoricoAhorros(Integer mesesHistoricoAhorros) {
		this.mesesHistoricoAhorros = mesesHistoricoAhorros;
	}

	public Date getFechaMinHistorico() {
		return fechaMinHistorico;
	}

	public void setFechaMinHistorico(Date fechaMinHistorico) {
		this.fechaMinHistorico = fechaMinHistorico;
	}

	public String getSesion() {
		this.fechaDesde = null;
		this.fechaHasta = null;
		this.codigoTipoTransaccion = null;
		listadoMovimientosWeb = new ArrayList<TwebMovimientos>();
		this.transferenciaLocal = false;
		this.transferenciaOtroBanco = false;
		this.pagoTarjeta = false;
		this.pagoPrestamo = false;
		this.pagoServicio = false;
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

}
