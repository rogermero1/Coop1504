package ec.fin.online15.portal.paginas.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ParametroMontoPermitido;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AutorizaTransaccion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaLocal;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaOtroBanco;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosOperacionesFrecuentes;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosTransaccionCliente;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("transferenciaMB")
@SessionScoped
public class BeanTransferencia extends BaseManagedBean {

	private static final long serialVersionUID = 1L;
	private TransferenciaLocal transferenciaLocal;
	private TransferenciaLocal confirmacion;
	private TransferenciaOtroBanco transferenciaOtroBanco;

	private List<ListadoOpciones> listaCuentas;
	private List<ListadoOpciones> listaTipoCuenta;
	private List<ListadoOpciones> listaFinancieras;

	private ParametroMontoPermitido montosPermitidos;
	private TwebParametrosGenerales parametrosGenerales;
	private AutorizaTransaccion autorizaTransaccion;

	private Long idUsuario;
	private String codigoSeguridad;
	private Integer codigoCliente;
	private String correo;
	private String telefono;
	private Integer idTipoCanal;
	private String financiera;
	private String descripcionFinanciera;
	private String descripcripcionTipoCuenta;
	private Double comisionOtrosBancos;
	private Double comisionAguaPotable;
	private String controlSesion;

	private List<TwebOperacionesFrecuentes> listaTransferenciasLocales;
	private List<TwebOperacionesFrecuentes> listaTransferenciasOtrosBancos;
	private List<TwebOperacionesFrecuentes> listaPagoTarjetas;
	private Long idOperacionTransferenciaLocal;
	private Long idOperacionTransferenciaOtroBanco;
	private Long idOperacionPagoTarjeta;

	public String getDescripcionFinanciera() {
		return descripcionFinanciera;
	}

	public void setDescripcionFinanciera(String descripcionFinanciera) {
		this.descripcionFinanciera = descripcionFinanciera;
	}

	private String nombreCuenta = null;
	private String etiquetaNombreCuenta = null;
	private Date fechaHora;
	private Date fechaValida;
	private String tituloMensajes;
	SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Inject
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosTransaccionCliente servicioTransaccion;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales serviciosMantenimientoParametrosGenerales;

	@Inject
	private BeanServiciosRegistroUsuario servicioRegistroUsuario;

	@Inject
	private BeanServiciosUsuario serviciosUsuario;

	@Inject
	private BeanServiciosOperacionesFrecuentes servicioOperacionFrecuente;

	@PostConstruct
	private void init() {
		this.inicializar();
	}

	private void inicializar() {
		this.codigoSeguridad = "";
		this.codigoCliente = this.servicioUsuario.getTwebUsuario().getCodigoCliente();
		this.correo = this.servicioUsuario.getTwebUsuario().getCorreo();
		this.telefono = this.servicioUsuario.getTwebUsuario().getCelular();
		this.idUsuario = this.servicioUsuario.getTwebUsuario().getId();
		this.idTipoCanal = 1;
		this.nombreCuenta = "";
		this.financiera = "";
		this.descripcionFinanciera = "";
		this.tituloMensajes = this.configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);

		transferenciaLocal = new TransferenciaLocal();
		transferenciaOtroBanco = new TransferenciaOtroBanco();

		listaCuentas = new ArrayList<ListadoOpciones>();
		listaTipoCuenta = new ArrayList<ListadoOpciones>();
		listaFinancieras = new ArrayList<ListadoOpciones>();

		montosPermitidos = new ParametroMontoPermitido();
		parametrosGenerales = new TwebParametrosGenerales();
		confirmacion = new TransferenciaLocal();

		/* Fecha valida del modulo BCA */
		fechaValida = serviciosMantenimientoParametrosGenerales.getIServicioParametrosGeneralesWs()
				.consultaFechaValida(this.getHttpSession().getId(), "BCA");
		System.out.println("Fecha del modulo BCA : " + formatoDeFecha.format(fechaValida));

		/* Consulta cuentas del cliente */
		listaCuentas = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaCuentasCliente(this.getHttpSession().getId(), this.codigoCliente);

		/* Consulta tipos de cuentas */
		listaTipoCuenta = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.listadoTipoCuenta(this.getHttpSession().getId());

		/* Consulta entidades financieras */
		listaFinancieras = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.listadoEntidadesFinancieras(this.getHttpSession().getId());

		/* Consulta monto maximo y minimo del cliente */
		montosPermitidos = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.parametroMontosTransaccionCliente(this.getHttpSession().getId(), this.codigoCliente);
		System.out.println("Monto maximo : " + montosPermitidos.getMontoMaximoDiario());
		System.out.println("Monto minimo : " + montosPermitidos.getMontoMinimoDiario());
		/* Parametros generales */
		for (TwebParametrosGenerales resultado : serviciosMantenimientoParametrosGenerales
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(this.getHttpSession().getId())) {
			parametrosGenerales = resultado;
		}
		System.out.println("Minutos vigencia OTP : " + parametrosGenerales.getMinutosVigenciaOtp());
		/*
		 * Comision para transferencias otros bancos 1 = Comision Transferencia otros
		 * bancos 2 = Comison planilla de agua potable
		 */
		this.comisionOtrosBancos = servicioTransaccion.getIServicioTransaccionClienteWs()
				.consultaComision(this.getHttpSession().getId(), 1);
		this.comisionAguaPotable = servicioTransaccion.getIServicioTransaccionClienteWs()
				.consultaComision(this.getHttpSession().getId(), 2);
		System.out.println("Comision otros bancos : " + this.comisionOtrosBancos);
		System.out.println("Comision planilla agua potable : " + this.comisionAguaPotable);

		// Operaciones Frecuentes
		this.listaTransferenciasLocales = new ArrayList<TwebOperacionesFrecuentes>();
		this.listaTransferenciasOtrosBancos = new ArrayList<TwebOperacionesFrecuentes>();
		this.listaPagoTarjetas = new ArrayList<TwebOperacionesFrecuentes>();
		this.listaTransferenciasLocales = this.servicioOperacionFrecuente.getIServicioOperacionesFrecuentesWs()
				.operacionesFrecuentesPorUsuario(this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario(),
						1);
		this.listaTransferenciasOtrosBancos = this.servicioOperacionFrecuente.getIServicioOperacionesFrecuentesWs()
				.operacionesFrecuentesPorUsuario(this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario(),
						2);
		this.listaPagoTarjetas = this.servicioOperacionFrecuente.getIServicioOperacionesFrecuentesWs()
				.operacionesFrecuentesPorUsuario(this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario(),
						3);
		this.idOperacionTransferenciaLocal = 0L;
		this.idOperacionTransferenciaOtroBanco = 0L;
		this.idOperacionPagoTarjeta = 0L;
	}

	public void cargarOperacionFrecuente(Long tipo) {
		// Tipo: 1 trx local, 2 trx otro bco, 3 pago tar
		TwebOperacionesFrecuentes operacionFrecuente = null;
		if (tipo.toString().equals("1")) {
			for (TwebOperacionesFrecuentes operacion : this.listaTransferenciasLocales) {
				if (operacion.getId().toString().equals(this.idOperacionTransferenciaLocal.toString())) {
					operacionFrecuente = operacion;
					break;
				}
			}
			if (operacionFrecuente != null) {
				this.transferenciaLocal.setDescripcion(operacionFrecuente.getObservacion());
				this.transferenciaLocal.setCuentaDebitar(operacionFrecuente.getCuentaDebito());
				this.transferenciaLocal.setCuentaAcreditar(operacionFrecuente.getCuentaCredito());
				this.transferenciaLocal.setValorDebito(operacionFrecuente.getMonto());
				this.nombreCuentaAcreditar();
			} else {
				this.funcionLimpiar(null);
			}
		} else if (tipo.toString().equals("2")) {
			System.out.println("TIPO 1");
			for (TwebOperacionesFrecuentes operacion : this.listaTransferenciasOtrosBancos) {
				if (operacion.getId().toString().equals(this.idOperacionTransferenciaOtroBanco.toString())) {
					operacionFrecuente = operacion;
					break;
				}
			}
			if (operacionFrecuente != null) {
				this.transferenciaOtroBanco.setMotivo(operacionFrecuente.getObservacion());
				this.transferenciaOtroBanco.setCuentaOrdenante(operacionFrecuente.getCuentaDebito());
				this.transferenciaOtroBanco.setCuentaDestino(operacionFrecuente.getCuentaCredito());
				this.transferenciaOtroBanco
						.setIdentificacionBeneficiario(operacionFrecuente.getIdentificacionBeneficiario());
				this.transferenciaOtroBanco.setNombreBeneficiario(operacionFrecuente.getNombreBeneficiario());
				this.transferenciaOtroBanco
						.setTipoCuentaDestino(Integer.valueOf(operacionFrecuente.getTipoCuentaBeneficiaria()));
				this.financiera = operacionFrecuente.getFinanciera();
				this.transferenciaOtroBanco.setValor(operacionFrecuente.getMonto());
			} else {
				this.funcionLimpiar(null);
			}
		} else if (tipo.toString().equals("3")) {
			System.out.println("TIPO 1");
			for (TwebOperacionesFrecuentes operacion : this.listaPagoTarjetas) {
				if (operacion.getId().toString().equals(this.idOperacionPagoTarjeta.toString())) {
					operacionFrecuente = operacion;
					break;
				}
			}
			if (operacionFrecuente != null) {
				this.transferenciaOtroBanco.setMotivo(operacionFrecuente.getObservacion());
				this.transferenciaOtroBanco.setCuentaOrdenante(operacionFrecuente.getCuentaDebito());
				this.transferenciaOtroBanco.setCuentaDestino(operacionFrecuente.getCuentaCredito());
				this.transferenciaOtroBanco
						.setIdentificacionBeneficiario(operacionFrecuente.getIdentificacionBeneficiario());
				this.transferenciaOtroBanco.setNombreBeneficiario(operacionFrecuente.getNombreBeneficiario());
				this.financiera = operacionFrecuente.getFinanciera();
				this.transferenciaOtroBanco.setValor(operacionFrecuente.getMonto());
			} else {
				this.funcionLimpiar(null);
			}
		}
	}

	public void verificacionDatosTransferenciaLocal() {
		Boolean control = true;
		if (this.transferenciaLocal.getCuentaDebitar().equals(null)
				|| this.transferenciaLocal.getCuentaDebitar().equals("")) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes, "Número de cuenta a debitar no puede ser Nulo");
		}
		if (this.transferenciaLocal.getCuentaAcreditar().equals(null)
				|| this.transferenciaLocal.getCuentaAcreditar().equals("")) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes, "Número de cuenta a acreditar no puede ser Nulo");
		}
		if (transferenciaLocal.getValorDebito() < montosPermitidos.getMontoMinimoDiario()) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto no puede ser menor a " + montosPermitidos.getMontoMinimoDiario());
		}
		if (transferenciaLocal.getValorDebito() > montosPermitidos.getMontoMaximoDiario()) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto no puede ser mayor a " + montosPermitidos.getMontoMaximoDiario());
		}
		if (transferenciaLocal.getValorDebito() > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaLocal.getCuentaDebitar())) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes, "Monto no puede ser mayor a saldo disponible.");
		}
		if (this.transferenciaLocal.getCuentaDebitar().equals(this.transferenciaLocal.getCuentaAcreditar())) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a debitar no puede ser igual a cuenta a acreditar.");
		}
		/* Valida cuenta existe */
		nombreCuenta = servicioTransaccion.getIServicioTransaccionClienteWs()
				.nombreCuentaAhorro(this.getHttpSession().getId(), this.transferenciaLocal.getCuentaAcreditar());
		if (nombreCuenta.equals(null) || nombreCuenta.equals("")) {
			control = false;
			nombreCuenta = "NUMERO CUENTA INCORRECTA";
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a acreditar no existe.");
		}

		System.out.println("Verificacion de datos para realizar transferencia local...");
		if (control) {
			/* Verificar los cupos */
			this.autorizaTransaccion = new AutorizaTransaccion(this.codigoCliente, transferenciaLocal.getValorDebito(),
					this.idTipoCanal, "S", "N", null, null);

			this.autorizaTransaccion = servicioTransaccion.getIServicioTransaccionClienteWs()
					.consultaAutorizacionTransaccion(this.getHttpSession().getId(), autorizaTransaccion);

			System.out.println("Estado de la operacion : " + autorizaTransaccion.getEstado());
			System.out.println("Error : " + autorizaTransaccion.getError());

			if (autorizaTransaccion.getEstado().equals("S")) {
				System.out.println("Antes de enviar OTP...");
				String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
						"Transferencia local");
				System.out.println("Despues de enviar OTP...");
				System.out.println("Error : " + error);

				if (error == null || error.equals("")) {
					System.out.println("Antes de redireccionar...");
					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
							"pag_cliente_confirma_transferencia_local.jsf");
				} else {
					this.aniadirMensajeError(this.tituloMensajes, error);
				}
			} else {
				// this.configuracionesGenerales.mensajeTransaccionError();
				this.aniadirMensajeError("Detalle : ", this.autorizaTransaccion.getError());
			}
		}
	}

	public void trxTransferenciaLocal() {
		TwebUsuario usuario = new TwebUsuario();
		usuario.setCodigoCliente(this.codigoCliente);
		System.out.println("Antes de confirmar => codigo de seguridad");
		Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
				this.getHttpSession().getId(), usuario.getCodigoCliente(), this.codigoSeguridad, this.codigoSeguridad);
		System.out.println("Depues de confirmar en codigo de seguridad");
		System.out.println("Resultado de confirmacion : " + respuesta);
		if (respuesta == 1) {
			transferenciaLocal.setCodigoAplicacionDebito("BCA");
			transferenciaLocal.setCodigoAplicacionCredito("BCA");
			transferenciaLocal.setCanal(Canal.WEB);
			confirmacion = servicioTransaccion.getIServicioTransaccionClienteWs()
					.transferenciaLocalCliente(this.getHttpSession().getId(), transferenciaLocal);

			System.out.println("Secuencia comprobacion : " + confirmacion.getSecuencia());

			System.out.println("Codigo Error : " + confirmacion.getCodigoError());

			transferenciaLocal.setSecuencia(confirmacion.getSecuencia());

			if (confirmacion.getCodigoError() == null || confirmacion.getCodigoError().equals("")) {
				// Fecha de proceso
				this.fechaHora = new Date();
				TwebMovimientos movimiento = new TwebMovimientos();

				movimiento.setIdUsuario(this.idUsuario);
				movimiento.setCodigoVerificador(this.codigoSeguridad);
				movimiento.setIdTipoTransaccion(1);
				movimiento.setFechaValida(this.fechaValida);
				movimiento.setRegistradoPor("HBANKING");
				movimiento.setEsLocal("S");
				movimiento.setEstado("A");
				movimiento.setCanal(Canal.WEB);
				try {
					movimiento.setFechaHora(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
				} catch (ParseException e) {
					movimiento.setFechaHora(new Date());
				}
				try {
					movimiento.setFechaRegistro(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
				} catch (ParseException e) {
					movimiento.setFechaHora(new Date());
				}
				movimiento.setIpTransaccion(this.getIpLocal());
				movimiento.setNumeroCuentaDestino(transferenciaLocal.getCuentaAcreditar().toString());
				movimiento.setNumeroCuentaOrigen(transferenciaLocal.getCuentaDebitar().toString());
				movimiento.setObservacion("Transferencia Cuentas Locales");
				movimiento.setReferencia(transferenciaLocal.getDescripcion());
				movimiento.setTipoCuenta("AHORRO");
				movimiento.setUsuario(this.getUsuarioAutenticado().toString());
				movimiento.setValor(transferenciaLocal.getValorDebito());

				servicioTransaccion.getIServicioTransaccionClienteWs().registraMovimiento(this.getHttpSession().getId(),
						movimiento);

				List<String> parametros = new ArrayList<String>();
				parametros.add(this.transferenciaLocal.getSecuencia().toString());
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add(this.enmascararCadena(this.transferenciaLocal.getCuentaDebitar().toString()));
				parametros.add(this.enmascararCadena(this.transferenciaLocal.getCuentaAcreditar().toString()));
				parametros.add(this.nombreCuenta);
				parametros.add(this.transferenciaLocal.getValorDebito().toString());
				parametros.add(this.transferenciaLocal.getDescripcion());
				System.out.println(
						"Envio de comprobante transferencia local: " + this.serviciosUsuario.getIServicioUsuarioWs()
								.envioNotificacion(this.getHttpSession().getId(), this.codigoCliente, null, this.correo,
										ValoresConstantes.PLANTILLACOMPROBANTETRANSFERENCIALOCAL, parametros));

				parametros = new ArrayList<String>();
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add("Transferencia Local");
				parametros.add(this.transferenciaLocal.getValorDebito().toString());
				System.out.println("Envio de notificacion transferencia local: " + this.serviciosUsuario
						.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(), this.codigoCliente,
								this.telefono, null, ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_comprobante_transferencia.jsf");
			} else {
				if (this.confirmacion.getCodigoError().contains("52104")) {
					this.configuracionesGenerales.mensajeServicioNoDisponible();
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
				}
				System.out.println("Error transferencia local: " + confirmacion.getCodigoError());
				// this.aniadirMensajeError("Detalle : ",
				// confirmacion.getCodigoError());
			}
		} else if (respuesta == 0) {
			this.aniadirMensajeError(this.tituloMensajes, "Código ingresado no existe.");
		} else if (respuesta == 2) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Tiempo máximo de vigencia de su código de seguridad expiro.");
		}
	}

	/*
	 * ############### PROCESO PARA TRANSFERENCIA DE OTROS BANCOS LOCALES
	 * ##################################################################
	 */
	public void verificaDatosOtrosBancos() {

		Boolean control = true;
		if (this.transferenciaOtroBanco.getMotivo() == null || this.transferenciaOtroBanco.getMotivo().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Motivo no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getCuentaOrdenante() == null
				|| this.transferenciaOtroBanco.getCuentaOrdenante().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta debito no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() == null || this.transferenciaOtroBanco.getValor().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Monto de la transacción no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getIdentificacionBeneficiario() == null
				|| this.transferenciaOtroBanco.getIdentificacionBeneficiario().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Identificacion del beneficiario no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getNombreBeneficiario() == null
				|| this.transferenciaOtroBanco.getNombreBeneficiario().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Nombre del beneficiario no puede ser vacio");
			control = false;
		}
		if (this.financiera == null || this.financiera.equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Institución finaciera no puede estar vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getTipoCuentaDestino() == null
				|| this.transferenciaOtroBanco.getTipoCuentaDestino().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Tipo cuenta a acreditar no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getCuentaDestino() == null
				|| this.transferenciaOtroBanco.getCuentaDestino().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a acreditar no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaOtroBanco.getCuentaOrdenante())) {
			this.aniadirMensajeError(this.tituloMensajes, "Saldo insuficiente para realizar transacción");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos > servicioTransaccion
				.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaOtroBanco.getCuentaOrdenante())) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto de la transacción más la comisión superan el saldo disponible ("
							+ this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos + ")");
			control = false;
		}
		if (control) {
			/* Verifica cupos transaccionales */
			this.autorizaTransaccion = new AutorizaTransaccion(this.codigoCliente, transferenciaOtroBanco.getValor(),
					this.idTipoCanal, "S", "N", null, null);

			this.autorizaTransaccion = servicioTransaccion.getIServicioTransaccionClienteWs()
					.consultaAutorizacionTransaccion(this.getHttpSession().getId(), autorizaTransaccion);
			if (autorizaTransaccion.getEstado().equals("N")) {
				// this.configuracionesGenerales.mensajeTransaccionError();
				this.aniadirMensajeError("Detalle : ", this.autorizaTransaccion.getError());
			} else {
				/* Datos del objeto transfereciaOtroBanco */
				this.transferenciaOtroBanco.setTipoTransmision(2);
				this.transferenciaOtroBanco.setConceptoOpi(1);
				this.transferenciaOtroBanco.setLocalidadOrdenante("15Online");
				this.transferenciaOtroBanco.setClienteOrdenante(this.codigoCliente);
				this.transferenciaOtroBanco.setTipoCuentaOrdenante(2);
				if (this.transferenciaOtroBanco.getInstruccionEspecial() == null) {
					this.transferenciaOtroBanco.setInstruccionEspecial("15onLine");
				}
				this.transferenciaOtroBanco.setUsuario("HBANKING");

				this.transferenciaOtroBanco.setFinancieraBce(this.financiera);
//				this.transferenciaOtroBanco.setTipoFinanciera(
//						Integer.parseInt(this.financiera.substring(0, this.financiera.indexOf("-"))));
//				this.transferenciaOtroBanco.setCodigoFinanciera(Integer.parseInt(
//						this.financiera.substring(this.financiera.indexOf("-") + 1, this.financiera.length())));

				System.out.println("Objeto otros Bancos : " + this.transferenciaOtroBanco.toString());
				/* Envia codigo de seguridad */
				System.out.println("Antes de enviar OTP...");

				String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
						"Transferencia otros");
				System.out.println("Despues de enviar OTP...");
				System.out.println("Error : " + error);

				if (error == null || error.equals("")) {
					this.descripcionFinanciera = this.descripcionLista(listaFinancieras, this.financiera);
					this.descripcripcionTipoCuenta = this.descripcionLista(listaTipoCuenta,
							this.transferenciaOtroBanco.getTipoCuentaDestino().toString());

					System.out.println("Antes de redireccionar...");
					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
							"pag_cliente_confirma_transferencia_otro_banco.jsf");
				} else {
					this.aniadirMensajeError(this.tituloMensajes, "No se pudo enviar mensaje : ");
				}
			}
		} else {
			this.aniadirMensajeError(this.tituloMensajes, "No se puede realizar la operación");
		}
	}

	public void trxTransferenciaOtroBanco() {
		TwebUsuario usuario = new TwebUsuario();
		usuario.setCodigoCliente(this.codigoCliente);
		System.out.println("Antes de confirmar => codigo de seguridad");
		Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
				this.getHttpSession().getId(), usuario.getCodigoCliente(), this.codigoSeguridad, this.codigoSeguridad);
		System.out.println("Depues de confirmar en codigo de seguridad");
		System.out.println("Resultado de confirmacion : " + respuesta);

		if (respuesta == 1) {
			/* Comprueba saldo disponible */
			if (servicioTransaccion.getIServicioTransaccionClienteWs().saldoCuentaAhorro(this.getHttpSession().getId(),
					this.transferenciaOtroBanco.getCuentaOrdenante()) >= (this.comisionOtrosBancos
							+ this.transferenciaOtroBanco.getValor())) {

				this.transferenciaOtroBanco.setCanal(Canal.WEB);

				this.transferenciaOtroBanco = servicioTransaccion.getIServicioTransaccionClienteWs()
						.transferenciaOtroBancoCliente(this.getHttpSession().getId(), transferenciaOtroBanco);

				System.out.println("Secuencia comprobacion : " + this.transferenciaOtroBanco.getSecuencia());

				System.out.println("Codigo Error : " + this.transferenciaOtroBanco.getError());

				if (this.transferenciaOtroBanco.getError() == null
						|| this.transferenciaOtroBanco.getError().equals("")) {
					// Fecha de proceso
					this.fechaHora = new Date();
					TwebMovimientos movimiento = new TwebMovimientos();

					movimiento.setIdUsuario(this.idUsuario);
					movimiento.setCodigoVerificador(this.codigoSeguridad);
					movimiento.setIdTipoTransaccion(2);// transferencia otros bancos
					movimiento.setFechaValida(this.fechaValida);
					movimiento.setRegistradoPor("HBANKING");
					movimiento.setEsLocal("S");
					movimiento.setEstado("A");
					movimiento.setCanal(Canal.WEB);
					movimiento
							.setIdentificacionBeneficiario(this.transferenciaOtroBanco.getIdentificacionBeneficiario());
					movimiento.setNombreBeneficiario(this.transferenciaOtroBanco.getNombreBeneficiario());
					try {
						movimiento.setFechaHora(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
					} catch (ParseException e) {
						movimiento.setFechaHora(new Date());
					}
					try {
						movimiento.setFechaRegistro(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
					} catch (ParseException e) {
						movimiento.setFechaHora(new Date());
					}
					movimiento.setIpTransaccion(this.getIpLocal());
					movimiento.setNumeroCuentaDestino(transferenciaOtroBanco.getCuentaDestino().toString());
					movimiento.setNumeroCuentaOrigen(transferenciaOtroBanco.getCuentaOrdenante().toString());
					movimiento.setObservacion("Transferencia Otros Bancos");
					movimiento.setReferencia(transferenciaOtroBanco.getMotivo());
					movimiento.setTipoCuenta("AHORRO");
					movimiento.setUsuario(this.getUsuarioAutenticado().toString());
					movimiento.setValor(transferenciaOtroBanco.getValor());
					// movimiento.setIdTipoFinanciera(this.transferenciaOtroBanco.getTipoFinanciera());
					// movimiento.setCodigoEntidadFinanciera(this.transferenciaOtroBanco.getCodigoFinanciera());
					movimiento.setCodigoEntidadFinanciera(
							Integer.valueOf(this.transferenciaOtroBanco.getFinancieraBce()));
					servicioTransaccion.getIServicioTransaccionClienteWs()
							.registraMovimiento(this.getHttpSession().getId(), movimiento);

					List<String> parametros = new ArrayList<String>();
					parametros.add(this.transferenciaOtroBanco.getSecuencia().toString());
					parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
					parametros.add(this.enmascararCadena(this.transferenciaOtroBanco.getCuentaOrdenante().toString()));
					parametros.add(this.enmascararCadena(this.transferenciaOtroBanco.getCuentaDestino().toString()));
					parametros.add(this.transferenciaOtroBanco.getIdentificacionBeneficiario());
					parametros.add(this.transferenciaOtroBanco.getNombreBeneficiario());
					parametros.add(this.transferenciaOtroBanco.getValor().toString());
					parametros.add(this.comisionOtrosBancos.toString());
					parametros.add(String.valueOf((this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos)));
					parametros.add(this.descripcripcionTipoCuenta);
					parametros.add(this.descripcionFinanciera);
					parametros.add(this.transferenciaOtroBanco.getMotivo());
					System.out.println("Envio de comprobante transferencia otros bancos: "
							+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
									this.getHttpSession().getId(), this.codigoCliente, null, this.correo,
									ValoresConstantes.PLANTILLACOMPROBANTETRANSFERENCIAOTROBANCO, parametros));

					parametros = new ArrayList<String>();
					parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
					parametros.add("Transferencia otros");
					parametros.add(this.transferenciaOtroBanco.getValor().toString());
					System.out.println("Envio de notificacion transferencia otros: "
							+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
									this.getHttpSession().getId(), this.codigoCliente, this.telefono, null,
									ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
							"pag_cliente_comprobante_transferencia_otro_banco.jsf");
				} else {
					if (this.transferenciaOtroBanco.getError().contains("52104")) {
						this.configuracionesGenerales.mensajeServicioNoDisponible();
					} else if (this.transferenciaOtroBanco.getError().contains("HORARIO")) {
						this.aniadirMensajeError(this.tituloMensajes,
								"Por su seguridad, en horario de 00:00 a 06:00 solo podrá realizar transacciones menores a $1000");
					} else {
						this.configuracionesGenerales.mensajeTransaccionError();
					}
					System.out.println("Error transferencia otro banco: " + this.transferenciaOtroBanco.getError());
					// this.aniadirMensajeError("Detalle :
					// ",this.transferenciaOtroBanco.getError());
				}
			} else {
				this.aniadirMensajeError(this.tituloMensajes,
						"Saldo disponible de su cuenta insuficiente, no se puede realizar transacción.");
			}
		} else if (respuesta == 0) {
			this.aniadirMensajeError(this.tituloMensajes, "Código ingresado no existe.");
		} else if (respuesta == 2) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Tiempo máximo de vigencia de su código de seguridad expiro.");
		}
	}

	/*
	 * ############### PROCESO PARA TRANSFERENCIA DE OTROS BANCOS LOCALES
	 * ##################
	 */
	public void verificaDatosTarjetaCredito() {
		Boolean control = true;
		if (this.transferenciaOtroBanco.getMotivo() == null || this.transferenciaOtroBanco.getMotivo().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Motivo no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getCuentaOrdenante() == null
				|| this.transferenciaOtroBanco.getCuentaOrdenante().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta debito no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() == null || this.transferenciaOtroBanco.getValor().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Monto de la transacción no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() < montosPermitidos.getMontoMinimoDiario()) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto no puede ser menor a " + montosPermitidos.getMontoMinimoDiario());
		}
		if (this.transferenciaOtroBanco.getValor() > montosPermitidos.getMontoMaximoDiario()) {
			control = false;
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto no puede ser mayor a " + montosPermitidos.getMontoMaximoDiario());
		}
		if (this.transferenciaOtroBanco.getIdentificacionBeneficiario() == null
				|| this.transferenciaOtroBanco.getIdentificacionBeneficiario().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Identificacion del beneficiario no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getNombreBeneficiario() == null
				|| this.transferenciaOtroBanco.getNombreBeneficiario().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Nombre del beneficiario no puede ser vacio");
			control = false;
		}
		if (this.financiera == null || this.financiera.equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Institución finaciera no puede estar vacio");
			control = false;
		}
		/*
		 * if (this.transferenciaOtroBanco.getTipoCuentaDestino() == null ||
		 * this.transferenciaOtroBanco.getTipoCuentaDestino() .equals("")) {
		 * this.aniadirMensajeError("Aplicación : ",
		 * "Tipo cuenta a acreditar no puede ser vacio"); control = false; }
		 */
		if (this.transferenciaOtroBanco.getCuentaDestino() == null
				|| this.transferenciaOtroBanco.getCuentaDestino().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a acreditar no puede ser vacio");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaOtroBanco.getCuentaOrdenante())) {
			this.aniadirMensajeError(this.tituloMensajes, "Saldo insuficiente para realizar transacción");
			control = false;
		}
		if (this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos > servicioTransaccion
				.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaOtroBanco.getCuentaOrdenante())) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Monto de la transacción más la comisión superan el saldo disponible ("
							+ this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos + ")");
			control = false;
		}
		if (control) {
			/* Verifica cupos transaccionales */
			this.autorizaTransaccion = new AutorizaTransaccion(this.codigoCliente, transferenciaOtroBanco.getValor(),
					this.idTipoCanal, "S", "N", null, null);

			this.autorizaTransaccion = servicioTransaccion.getIServicioTransaccionClienteWs()
					.consultaAutorizacionTransaccion(this.getHttpSession().getId(), autorizaTransaccion);
			if (autorizaTransaccion.getEstado().equals("N")) {
				// this.configuracionesGenerales.mensajeTransaccionError();
				// System.out.println("ERROR " + this.autorizaTransaccion.getError());
				this.aniadirMensajeError("Detalle : ", this.autorizaTransaccion.getError());
			} else {
				/* 4 = tarjeta de credito */
				this.transferenciaOtroBanco.setTipoCuentaDestino(4);
				/* 2 = internet */
				this.transferenciaOtroBanco.setTipoTransmision(2);
				/* 15 = Pago consumos tarjetas de credito */
				this.transferenciaOtroBanco.setConceptoOpi(15);
				this.transferenciaOtroBanco.setLocalidadOrdenante("15Online");
				this.transferenciaOtroBanco.setClienteOrdenante(this.codigoCliente);
				this.transferenciaOtroBanco.setTipoCuentaOrdenante(2);
				if (this.transferenciaOtroBanco.getInstruccionEspecial() == null) {
					this.transferenciaOtroBanco.setInstruccionEspecial("15onLine");
				}
				this.transferenciaOtroBanco.setUsuario("HBANKING");

				this.transferenciaOtroBanco.setFinancieraBce(this.financiera);
//				this.transferenciaOtroBanco.setTipoFinanciera(
//						Integer.parseInt(this.financiera.substring(0, this.financiera.indexOf("-"))));
//				this.transferenciaOtroBanco.setCodigoFinanciera(Integer.parseInt(
//						this.financiera.substring(this.financiera.indexOf("-") + 1, this.financiera.length())));

				System.out.println("Objeto otros Bancos : " + this.transferenciaOtroBanco.toString());
				/* Envia codigo de seguridad */
				System.out.println("Antes de enviar OTP...");

				String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
						"Pago tarjeta");
				System.out.println("Despues de enviar OTP...");
				System.out.println("Error : " + error);

				if (error == null || error.equals("")) {
					this.descripcionFinanciera = this.descripcionLista(listaFinancieras, this.financiera);
					this.descripcripcionTipoCuenta = this.descripcionLista(listaTipoCuenta,
							this.transferenciaOtroBanco.getTipoCuentaDestino().toString());

					System.out.println("Antes de redireccionar...");
					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_confirma_pago_tarjeta.jsf");
				} else {
					this.aniadirMensajeError(this.tituloMensajes, "No se pudo enviar mensaje : ");
				}
			}
//		} else {
//			this.aniadirMensajeError(this.tituloMensajes, "No se puede realizar la operación");
		}
	}

	public void trxPagoTarjetaCredito() {
		TwebUsuario usuario = new TwebUsuario();
		usuario.setCodigoCliente(this.codigoCliente);
		System.out.println("Antes de confirmar => codigo de seguridad");
		Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
				this.getHttpSession().getId(), usuario.getCodigoCliente(), this.codigoSeguridad, this.codigoSeguridad);
		System.out.println("Depues de confirmar en codigo de seguridad");
		System.out.println("Resultado de confirmacion : " + respuesta);

		if (respuesta == 1) {
			/* Comprueba saldo disponible */
			if (servicioTransaccion.getIServicioTransaccionClienteWs().saldoCuentaAhorro(this.getHttpSession().getId(),
					this.transferenciaOtroBanco.getCuentaOrdenante()) >= (this.comisionOtrosBancos
							+ this.transferenciaOtroBanco.getValor())) {

				this.transferenciaOtroBanco.setCanal(Canal.WEB);
				this.transferenciaOtroBanco = servicioTransaccion.getIServicioTransaccionClienteWs()
						.transferenciaOtroBancoCliente(this.getHttpSession().getId(), transferenciaOtroBanco);

				System.out.println("Secuencia comprobacion : " + this.transferenciaOtroBanco.getSecuencia());

				System.out.println("Codigo Error : " + this.transferenciaOtroBanco.getError());

				if (this.transferenciaOtroBanco.getError() == null
						|| this.transferenciaOtroBanco.getError().equals("")) {
					// Fecha de proceso
					this.fechaHora = new Date();
					TwebMovimientos movimiento = new TwebMovimientos();

					movimiento.setIdUsuario(this.idUsuario);
					movimiento.setCodigoVerificador(this.codigoSeguridad);
					movimiento.setIdTipoTransaccion(3);
					movimiento.setFechaValida(this.fechaValida);
					movimiento.setRegistradoPor("HBANKING");
					movimiento.setEsLocal("S");
					movimiento.setEstado("A");
					movimiento.setCanal(Canal.WEB);
					movimiento
							.setIdentificacionBeneficiario(this.transferenciaOtroBanco.getIdentificacionBeneficiario());
					movimiento.setNombreBeneficiario(this.transferenciaOtroBanco.getNombreBeneficiario());
					try {
						movimiento.setFechaHora(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
					} catch (ParseException e) {
						movimiento.setFechaHora(new Date());
					}
					try {
						movimiento.setFechaRegistro(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
					} catch (ParseException e) {
						movimiento.setFechaHora(new Date());
					}
					movimiento.setIpTransaccion(this.getIpLocal());
					movimiento.setNumeroCuentaDestino(transferenciaOtroBanco.getCuentaDestino().toString());
					movimiento.setNumeroCuentaOrigen(transferenciaOtroBanco.getCuentaOrdenante().toString());
					movimiento.setObservacion("Pago de tarjeta de credito");
					movimiento.setReferencia(transferenciaOtroBanco.getMotivo());
					movimiento.setTipoCuenta("AHORRO");
					movimiento.setUsuario(this.getUsuarioAutenticado().toString());
					movimiento.setValor(transferenciaOtroBanco.getValor());
					// movimiento.setIdTipoFinanciera(this.transferenciaOtroBanco.getTipoFinanciera());
					// movimiento.setCodigoEntidadFinanciera(this.transferenciaOtroBanco.getCodigoFinanciera());
					movimiento.setCodigoEntidadFinanciera(
							Integer.valueOf(this.transferenciaOtroBanco.getFinancieraBce()));
					servicioTransaccion.getIServicioTransaccionClienteWs()
							.registraMovimiento(this.getHttpSession().getId(), movimiento);

					List<String> parametros = new ArrayList<String>();
					parametros.add(this.transferenciaOtroBanco.getSecuencia().toString());
					parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
					parametros.add(this.enmascararCadena(this.transferenciaOtroBanco.getCuentaOrdenante().toString()));
					parametros.add(this.enmascararCadena(this.transferenciaOtroBanco.getCuentaDestino().toString()));
					parametros.add(this.transferenciaOtroBanco.getIdentificacionBeneficiario());
					parametros.add(this.transferenciaOtroBanco.getNombreBeneficiario());
					parametros.add(this.transferenciaOtroBanco.getValor().toString());
					parametros.add(this.comisionOtrosBancos.toString());
					parametros.add(String.valueOf((this.transferenciaOtroBanco.getValor() + this.comisionOtrosBancos)));
					parametros.add(this.descripcionFinanciera);
					parametros.add(this.transferenciaOtroBanco.getMotivo());
					System.out.println("Envio de comprobante pago de tarjeta: "
							+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
									this.getHttpSession().getId(), this.codigoCliente, null, this.correo,
									ValoresConstantes.PLANTILLACOMPROBANTEPAGOTARJETA, parametros));

					parametros = new ArrayList<String>();
					parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
					parametros.add("Pago tarjeta");
					parametros.add(this.transferenciaOtroBanco.getValor().toString());
					System.out.println(
							"Envio de notificacion pago tarjeta: " + this.serviciosUsuario.getIServicioUsuarioWs()
									.envioNotificacion(this.getHttpSession().getId(), this.codigoCliente, this.telefono,
											null, ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
							"pag_cliente_comprobante_pago_tarjeta.jsf");
				} else {
					if (this.transferenciaOtroBanco.getError().contains("52104")) {
						this.configuracionesGenerales.mensajeServicioNoDisponible();
					} else {
						this.configuracionesGenerales.mensajeTransaccionError();
					}
					System.out.println("Error pago de tarjeta: " + this.transferenciaOtroBanco.getError());
					// this.aniadirMensajeError("Detalle : ",
					// this.transferenciaOtroBanco.getError());
				}
			} else {
				this.aniadirMensajeError(this.tituloMensajes,
						"Saldo disponible de su cuenta insuficiente, no se puede realizar transacción.");
			}
		} else if (respuesta == 0) {
			this.aniadirMensajeError(this.tituloMensajes, "Código ingresado no existe.");
		} else if (respuesta == 2) {
			this.aniadirMensajeError(this.tituloMensajes,
					"Tiempo máximo de vigencia de su código de seguridad expiro.");
		}
	}

	public void nombreCuentaAcreditar() {
		System.out.println("Cuenta a acreditar : " + this.transferenciaLocal.getCuentaAcreditar());

		etiquetaNombreCuenta = "Nombre Beneficiario : ";

		nombreCuenta = servicioTransaccion.getIServicioTransaccionClienteWs()
				.nombreCuentaAhorro(this.getHttpSession().getId(), this.transferenciaLocal.getCuentaAcreditar());
		if (nombreCuenta.equals(null) || nombreCuenta.equals("")) {
			nombreCuenta = "NUMERO CUENTA INCORRECTA";
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a acreditar no existe.");
		}
		if (this.transferenciaLocal.getCuentaDebitar().equals(this.transferenciaLocal.getCuentaAcreditar())) {
			this.aniadirMensajeError(this.tituloMensajes, "Cuenta a debitar no puede ser igual a cuenta a acreditar.");
		}
		System.out.println("Persona acreditar : " + nombreCuenta);

	}

	public void validaMontoTransferencia() {
		System.out.println("Cuenta seleccionada : " + this.transferenciaLocal.getCuentaDebitar());
		if (this.transferenciaLocal.getCuentaDebitar().equals(null)
				|| this.transferenciaLocal.getCuentaDebitar().equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Seleccione cuenta para realizar la transacción.");
		} else {
			if (transferenciaLocal.getValorDebito() < montosPermitidos.getMontoMinimoDiario()) {
				this.aniadirMensajeError(this.tituloMensajes,
						"Monto no puede ser menor a " + montosPermitidos.getMontoMinimoDiario());
			}
			if (transferenciaLocal.getValorDebito() > montosPermitidos.getMontoMaximoDiario()) {
				this.aniadirMensajeError(this.tituloMensajes,
						"Monto no puede ser mayor a " + montosPermitidos.getMontoMaximoDiario());
			}
			if (transferenciaLocal.getValorDebito() > servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.transferenciaLocal.getCuentaDebitar())) {
				this.aniadirMensajeError(this.tituloMensajes, "Monto no puede ser mayor a saldo disponible.");
			}
		}
	}

	private String descripcionLista(List<ListadoOpciones> lista, String id) {
		String respuesta = "";
		for (ListadoOpciones resultado : lista) {
			if (resultado.getId().equals(id)) {
				respuesta = resultado.getDescripcion();
			}
		}
		return respuesta;
	}

	public void funcionCancelar() {
		System.out.println("Cancelando operacion de transferencias...");
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_USUARIO_WEB,
				"");
	}

	public void funcionLimpiar(ActionEvent evento) {
		System.out.println("Inicializa los componentes para transferencias...");
		this.inicializar();
	}

	public TransferenciaLocal getTransferenciaLocal() {
		return transferenciaLocal;
	}

	public void setTransferenciaLocal(TransferenciaLocal transferenciaLocal) {
		this.transferenciaLocal = transferenciaLocal;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getEtiquetaNombreCuenta() {
		return etiquetaNombreCuenta;
	}

	public void setEtiquetaNombreCuenta(String etiquetaNombreCuenta) {
		this.etiquetaNombreCuenta = etiquetaNombreCuenta;
	}

	public List<ListadoOpciones> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<ListadoOpciones> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public ParametroMontoPermitido getMontosPermitidos() {
		return montosPermitidos;
	}

	public void setMontosPermitidos(ParametroMontoPermitido montosPermitidos) {
		this.montosPermitidos = montosPermitidos;
	}

	public TwebParametrosGenerales getParametrosGenerales() {
		return parametrosGenerales;
	}

	public void setParametrosGenerales(TwebParametrosGenerales parametrosGenerales) {
		this.parametrosGenerales = parametrosGenerales;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public TransferenciaOtroBanco getTransferenciaOtroBanco() {
		return transferenciaOtroBanco;
	}

	public void setTransferenciaOtroBanco(TransferenciaOtroBanco transferenciaOtroBanco) {
		this.transferenciaOtroBanco = transferenciaOtroBanco;
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

	public String getFinanciera() {
		return financiera;
	}

	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}

	public String getDescripcripcionTipoCuenta() {
		return descripcripcionTipoCuenta;
	}

	public void setDescripcripcionTipoCuenta(String descripcripcionTipoCuenta) {
		this.descripcripcionTipoCuenta = descripcripcionTipoCuenta;
	}

	public Double getComisionOtrosBancos() {
		return comisionOtrosBancos;
	}

	public void setComisionOtrosBancos(Double comisionOtrosBancos) {
		this.comisionOtrosBancos = comisionOtrosBancos;
	}

	public Double getComisionAguaPotable() {
		return comisionAguaPotable;
	}

	public void setComisionAguaPotable(Double comisionAguaPotable) {
		this.comisionAguaPotable = comisionAguaPotable;
	}

	public String getControlSesion() {
		this.inicializar();
		return controlSesion;
	}

	public void setControlSesion(String controlSesion) {
		this.controlSesion = controlSesion;
	}

	public List<TwebOperacionesFrecuentes> getListaTransferenciasLocales() {
		return listaTransferenciasLocales;
	}

	public void setListaTransferenciasLocales(List<TwebOperacionesFrecuentes> listaTransferenciasLocales) {
		this.listaTransferenciasLocales = listaTransferenciasLocales;
	}

	public List<TwebOperacionesFrecuentes> getListaTransferenciasOtrosBancos() {
		return listaTransferenciasOtrosBancos;
	}

	public void setListaTransferenciasOtrosBancos(List<TwebOperacionesFrecuentes> listaTransferenciasOtrosBancos) {
		this.listaTransferenciasOtrosBancos = listaTransferenciasOtrosBancos;
	}

	public List<TwebOperacionesFrecuentes> getListaPagoTarjetas() {
		return listaPagoTarjetas;
	}

	public void setListaPagoTarjetas(List<TwebOperacionesFrecuentes> listaPagoTarjetas) {
		this.listaPagoTarjetas = listaPagoTarjetas;
	}

	public Long getIdOperacionTransferenciaLocal() {
		return idOperacionTransferenciaLocal;
	}

	public void setIdOperacionTransferenciaLocal(Long idOperacionTransferenciaLocal) {
		this.idOperacionTransferenciaLocal = idOperacionTransferenciaLocal;
	}

	public Long getIdOperacionTransferenciaOtroBanco() {
		return idOperacionTransferenciaOtroBanco;
	}

	public void setIdOperacionTransferenciaOtroBanco(Long idOperacionTransferenciaOtroBanco) {
		this.idOperacionTransferenciaOtroBanco = idOperacionTransferenciaOtroBanco;
	}

	public Long getIdOperacionPagoTarjeta() {
		return idOperacionPagoTarjeta;
	}

	public void setIdOperacionPagoTarjeta(Long idOperacionPagoTarjeta) {
		this.idOperacionPagoTarjeta = idOperacionPagoTarjeta;
	}

}
