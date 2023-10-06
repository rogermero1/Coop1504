package ec.fin.online15.portal.paginas.beans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilderFactory;

import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTResplyConsulta;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTResplyConsultaINTDataConsulta;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.cooperativa15abril.facilito.controladorsoap.RespuestaPago;
import com.cooperativa15abril.facilito.controladorsoap.RespuestaRecarga;
import com.cooperativa15abril.facilito.controladorsoap.ResultadoProcesoPago;
import com.cooperativa15abril.facilito.controladorsoap.ServicoIntegracionFacilito_Service;
import com.cooperativa15abril.facilito.controladorsoap.SolicitudConsulta;
import com.cooperativa15abril.facilito.controladorsoap.SolicitudDataPago;
import com.cooperativa15abril.facilito.controladorsoap.SolicitudPago;
import com.cooperativa15abril.facilito.controladorsoap.SolicitudRecarga;

import ec.fin.coop15abril.servicios.municipio.Canal;
import ec.fin.coop15abril.servicios.municipio.Estado;
import ec.fin.coop15abril.servicios.municipio.PagoIn;
import ec.fin.coop15abril.servicios.municipio.PagoIn.Titulos;
import ec.fin.coop15abril.servicios.municipio.PagoOut;
import ec.fin.coop15abril.servicios.municipio.ResultadoConsulta;
import ec.fin.coop15abril.servicios.municipio.Rubro;
import ec.fin.coop15abril.servicios.municipio.ServiciosMunicipioClienteService;
import ec.fin.coop15abril.servicios.municipio.Titulo;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ConsolidadoPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.RubroFacilito;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AbonoPagoPrestamo;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.PagoServicio;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.beans.BeanServiciosAdministracionUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosTransaccionCliente;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("pagoMB")
@SessionScoped
public class BeanPagos extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	private AbonoPagoPrestamo abonoPagoPrestamo;
	private TwebParametrosGenerales parametrosGenerales;
	private DatosPrestamo datoPrestamo;
	private PagoServicio pagoServicio;
	private ConsolidadoPagoServicio consolidadoPagoServicio;

	private List<DatosPrestamo> listaDatosPrestamo;
	private List<ListadoOpciones> listadoPrestamosCliente;
	private List<ListadoOpciones> listadoPrestamosTercero;
	private List<ListadoOpciones> listadoCuentas;
	private List<ListadoOpciones> listadoEmpresas;
	private List<ListadoOpciones> listadoServicios;
	private List<DatosPagoServicio> datosPagoServicio;
	private DatosPagoServicio datoSeleccionado;

	private Long idUsuario;
	private Integer codigoCliente;
	private String telefono;
	private String correo;
	private String codigoSeguridad;
	private Date fechaHora;
	private Date fechaValida;
	private Integer codigoEmpresa;
	private String opcionBusqueda;
	private String etiquetaBusqueda = "";
	private String numeroIdenticacionServicio;
	private String descripcionEnpresa;
	private String descripcionServicio;
	private String numeroIdentificacion = null;
	private String numeroServicio = null;
	private boolean seleccionarTodo;
	private Double valorAdeudado;
	private Double valorTotalCancelado;
	private String controlSesion = "";
	private String etiquetaNombreBeneficiario;
	private String nombreBeneficiario;

	private String urlClienteMunicipio;
	private ResultadoConsulta deudaGad;
	private List<Rubro> rubrosAdeudadosGad;
	private String nombreDeudorGad;
	private Double comisionGad;
	private Double ivaGad;
	private Double totalComisionGad;
	private List<Titulo> titulosFiltrados;
	private Titulo tituloSeleccionado;
	private Double totalPagoGAD;
	private Long cuentaDebito;

	private String urlClienteFacilito;
	private String idRubroFacilito;
	private RubroFacilito rubroFacilito;
	private Double valorRecarga;
	private List<RubroFacilito> rubrosRecargaFacilito;
	private List<ListadoOpciones> listadoValoresRecargas;
	private boolean confirmaRecarga;
	private String reciboFacilito;
	private boolean bloqueaAbono;
	private boolean pagoEspecial;
	private String tituloReferenciaRecarga;
	private String tituloReferenciaPago;

	private List<ListadoOpciones> listadoGruposFacilito;
	private List<ListadoOpciones> listadoGruposRecaudacionFacilito;
	private List<RubroFacilito> rubrosFacilito;
	private String grupoFacilito;
	private INTResplyConsulta consultaDeudaFacilito;
	private List<INTResplyConsultaINTDataConsulta> rubrosAdeudadosFacilito;
	private INTResplyConsultaINTDataConsulta rubroSeleccionado;

	SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Inject
	private BeanServiciosTransaccionCliente servicioTransaccion;

	@Inject
	private BeanServiciosRegistroUsuario servicioRegistroUsuario;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales serviciosMantenimientoParametrosGenerales;

	@Inject
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanServiciosAdministracionUsuario servicioAdministracionUsuario;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosUsuario serviciosUsuario;

	@PostConstruct
	private void init() {
		this.inicializar();
	}

	private void inicializar() {
		this.codigoCliente = this.servicioUsuario.getTwebUsuario().getCodigoCliente();
		this.telefono = this.servicioUsuario.getTwebUsuario().getCelular();
		this.correo = this.servicioUsuario.getTwebUsuario().getCorreo();
		this.idUsuario = this.servicioUsuario.getTwebUsuario().getId();
		this.datoPrestamo = new DatosPrestamo();
		this.parametrosGenerales = new TwebParametrosGenerales();
		this.listadoPrestamosCliente = new ArrayList<ListadoOpciones>();
		this.listadoPrestamosTercero = new ArrayList<ListadoOpciones>();
		this.listadoCuentas = new ArrayList<ListadoOpciones>();
		this.listadoEmpresas = new ArrayList<ListadoOpciones>();
		this.listadoServicios = new ArrayList<ListadoOpciones>();
		this.datosPagoServicio = new ArrayList<DatosPagoServicio>();
		this.numeroIdenticacionServicio = "";
		this.numeroIdentificacion = "";
		this.numeroServicio = "";
		this.codigoSeguridad = "";
		this.codigoEmpresa = 0;
		this.descripcionEnpresa = "";
		this.descripcionServicio = "";
		this.etiquetaNombreBeneficiario = "";
		this.nombreBeneficiario = "";

		this.urlClienteMunicipio = System.getProperty("urlClienteMunicipio");
		System.out.println("URL cliente MUNICIPIO: " + this.urlClienteMunicipio);
		this.deudaGad = null;
		this.rubrosAdeudadosGad = new ArrayList<Rubro>();
		this.nombreDeudorGad = "";
		this.comisionGad = 0D;
		this.ivaGad = 0D;
		this.totalComisionGad = 0D;
		this.titulosFiltrados = new ArrayList<Titulo>();
		this.tituloSeleccionado = null;
		this.totalPagoGAD = 0D;
		this.cuentaDebito = null;

		this.urlClienteFacilito = System.getProperty("urlClienteFacilito");
		System.out.println("URL cliente FACILITO: " + this.urlClienteFacilito);
		this.idRubroFacilito = null;
		this.rubroFacilito = null;
		this.rubrosRecargaFacilito = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listadoRecargasFacilito(this.getHttpSession().getId());
		this.listadoValoresRecargas = null;
		this.valorRecarga = 0D;
		this.confirmaRecarga = false;
		this.reciboFacilito = "";
		this.bloqueaAbono = true;
		this.pagoEspecial = false;
		this.tituloReferenciaRecarga = "Teléfono";
		this.tituloReferenciaPago = "Referencia";

		this.listadoGruposFacilito = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listadoGruposFacilito(this.getHttpSession().getId());
		this.listadoGruposRecaudacionFacilito = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listadoGruposRecaudacionFacilito(this.getHttpSession().getId());
		this.grupoFacilito = "";
		this.rubrosFacilito = null;
		this.rubrosAdeudadosFacilito = new ArrayList<INTResplyConsultaINTDataConsulta>();
		this.rubroSeleccionado = null;
		this.consultaDeudaFacilito = new INTResplyConsulta();

		this.abonoPagoPrestamo = new AbonoPagoPrestamo();
		this.consolidadoPagoServicio = new ConsolidadoPagoServicio();

		this.pagoServicio = new PagoServicio();
		this.opcionBusqueda = "servicio";
		this.etiquetaBusqueda = "servicio :";
		this.seleccionarTodo = true;
		this.valorTotalCancelado = 0.00;
		/* Lista los prestamos del cliente */
		this.listadoPrestamosCliente = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaPrestamoCLiente(this.getHttpSession().getId(), this.codigoCliente);
		/* Lista las cuentas del cliente */
		this.listadoCuentas = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaCuentasCliente(this.getHttpSession().getId(), codigoCliente);
		/* Fecha valida del modulo BPR */
		fechaValida = serviciosMantenimientoParametrosGenerales.getIServicioParametrosGeneralesWs()
				.consultaFechaValida(this.getHttpSession().getId(), "BPR");
		System.out.println("Fecha del modulo BPR : " + formatoDeFecha.format(fechaValida));
		/* Parametros generales */
		for (TwebParametrosGenerales resultado : serviciosMantenimientoParametrosGenerales
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(this.getHttpSession().getId())) {
			parametrosGenerales = resultado;
		}
		/* Lista empresas para pago de servicios */
		this.listadoEmpresas = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaEmpresasPagoServicio(this.getHttpSession().getId());

		System.out.println("Minutos vigencia OTP : " + parametrosGenerales.getMinutosVigenciaOtp());
	}

	public void consultaDatoprestamo() {
		listaDatosPrestamo = new ArrayList<DatosPrestamo>();
		datoPrestamo = new DatosPrestamo();
		listaDatosPrestamo = servicioTransaccion.getIServicioTransaccionClienteWs()
				.consultaDatosPrestamo(this.getHttpSession().getId(), this.abonoPagoPrestamo.getNumeroPrestamo());
		for (DatosPrestamo datos : listaDatosPrestamo) {
			this.datoPrestamo.setCuotasAtrasadas(datos.getCuotasAtrasadas());
			this.datoPrestamo.setCuotasPedientes(datos.getCuotasPedientes());
			this.datoPrestamo.setSaldoCapital(datos.getSaldoCapital());
			this.datoPrestamo.setSaldoTotal(datos.getSaldoTotal());
			this.datoPrestamo.setValorAdeudado(datos.getValorAdeudado());
		}
		System.out.println("Objeto datos prestamo : " + datoPrestamo.toString());
	}

	public void consultaPrestamosTercero() {
		System.out.println("Consulta prestamos para:" + this.numeroIdentificacion);

		Empleado empleadoTemp = servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
				.consultaClientePorNumeroIdentificacion(this.getHttpSession().getId(), this.numeroIdentificacion);
		if (empleadoTemp != null) {
			this.etiquetaNombreBeneficiario = "Nombre Beneficiario : ";
			this.nombreBeneficiario = empleadoTemp.getNombres();
			/* Lista los prestamos del cliente */
			this.listadoPrestamosTercero = servicioTransaccion.getIServicioTransaccionClienteWs()
					.listaPrestamoCLiente(this.getHttpSession().getId(), empleadoTemp.getCodigoCliente());

		} else {
			this.aniadirMensajeError("Aplicación : ",
					"No se encuentran datos del cliente " + this.numeroIdentificacion);
			this.etiquetaNombreBeneficiario = "";
			this.nombreBeneficiario = "";
		}

	}

	public void validaValorPago() {
		if (this.abonoPagoPrestamo.getValorPago() > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.abonoPagoPrestamo.getNumeroCuenta())) {
			this.aniadirMensajeError("Aplicación : ",
					"Saldo insuficiene en su cuenta, el valor a pagar supera el valor disponible");
		}
	}

	public void validaDatosPagoPrestamo() {
		boolean control = true;
		if (this.abonoPagoPrestamo.getNumeroCuenta() == null || this.abonoPagoPrestamo.getNumeroCuenta().equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Número de cuenta es requerido.");
			control = false;
		}
		if (this.abonoPagoPrestamo.getNumeroPrestamo() == null
				|| this.abonoPagoPrestamo.getNumeroPrestamo().equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Número de préstamo es requerido.");
			control = false;
		}
		if (this.abonoPagoPrestamo.getValorPago() == null || this.abonoPagoPrestamo.getValorPago().equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Monto a pagar es requerido.");
			control = false;
		}

		if (this.abonoPagoPrestamo.getValorPago() <= 0) {
			this.aniadirMensajeError("Aplicación : ", "Monto a pagar no permitido.");
			control = false;
		}

		if (this.abonoPagoPrestamo.getValorPago() > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.abonoPagoPrestamo.getNumeroCuenta())) {
			this.aniadirMensajeError("Aplicación : ",
					"Saldo insuficiene en su cuenta, el valor a pagar supera el valor disponible");
			control = false;
		}
		if (control) {
			this.abonoPagoPrestamo.setCodigoCliente(this.codigoCliente);
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
					"Pago préstamo");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				System.out.println("Antes de redireccionar...");
				System.out.println(this.abonoPagoPrestamo.toString());
				configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
						"pag_cliente_confirma_pago_prestamo.jsf");
			} else {
				this.aniadirMensajeError("No se pudo correo : ", error);
			}
		}
	}

	public void trxPagoPrestamo() {
		System.out.println("Antes de confirmar => codigo de seguridad");
		Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
				this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);
		System.out.println("Depues de confirmar en codigo de seguridad");
		System.out.println("Resultado de confirmacion : " + respuesta);
		if (respuesta == 1) {
			abonoPagoPrestamo.setCanal(ec.fin.online15.backend.webbanking.modelo.entidades.Canal.WEB);
			abonoPagoPrestamo = servicioTransaccion.getIServicioTransaccionClienteWs()
					.abonoPagoPrestamoCliente(this.getHttpSession().getId(), abonoPagoPrestamo);

			System.out.println("Secuencia comprobacion : " + abonoPagoPrestamo.getSecuenciaPago());

			System.out.println("Codigo Error : " + abonoPagoPrestamo.getCodigoError());

			if (abonoPagoPrestamo.getCodigoError() == null || abonoPagoPrestamo.getCodigoError().equals("")) {
				// Fecha de proceso
				this.fechaHora = new Date();
				TwebMovimientos movimiento = new TwebMovimientos();

				movimiento.setIdUsuario(this.idUsuario);
				movimiento.setCodigoVerificador(this.codigoSeguridad);
				/* 4 = pago de prestamos */
				movimiento.setIdTipoTransaccion(4);
				movimiento.setFechaValida(this.fechaValida);
				movimiento.setRegistradoPor("HBANKING");
				movimiento.setEsLocal("S");
				movimiento.setEstado("A");
				movimiento.setCanal(ec.fin.online15.backend.webbanking.modelo.entidades.Canal.WEB);
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
				movimiento.setNumeroCuentaOrigen(abonoPagoPrestamo.getNumeroCuenta().toString());

				movimiento.setObservacion("Pago de prestamo");
				movimiento.setReferencia(abonoPagoPrestamo.getReferencia());
				movimiento.setTipoCuenta("AHORRO");
				movimiento.setUsuario(this.getUsuarioAutenticado().toString());
				movimiento.setValor(abonoPagoPrestamo.getValorPago());
				movimiento.setNumeroPrestamo(abonoPagoPrestamo.getNumeroPrestamo());

				servicioTransaccion.getIServicioTransaccionClienteWs().registraMovimiento(this.getHttpSession().getId(),
						movimiento);

				this.consultaDatoprestamo();

				List<String> parametros = new ArrayList<String>();
				parametros.add(this.abonoPagoPrestamo.getSecuenciaPago().toString());
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add(this.abonoPagoPrestamo.getNumeroCuenta().toString());
				parametros.add(this.abonoPagoPrestamo.getNumeroPrestamo().toString());
				parametros.add(this.datoPrestamo.getCuotasAtrasadas().toString());
				parametros.add(this.datoPrestamo.getCuotasPedientes().toString());
				parametros.add(this.datoPrestamo.getValorAdeudado().toString());
				parametros.add(this.datoPrestamo.getSaldoCapital().toString());
				parametros.add(this.datoPrestamo.getSaldoTotal().toString());
				parametros.add(this.abonoPagoPrestamo.getValorPago().toString());
				parametros.add(this.abonoPagoPrestamo.getReferencia());

				System.out.println("Envio de comprobante pago prestamo: " + this.serviciosUsuario
						.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(), this.codigoCliente,
								null, this.correo, ValoresConstantes.PLANTILLACOMPROBANTEPAGOPRESTAMO, parametros));

				parametros = new ArrayList<String>();
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add("Pago prestamo");
				parametros.add(this.abonoPagoPrestamo.getValorPago().toString());
				System.out.println("Envio de notificacion pago prestamo: " + this.serviciosUsuario
						.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(), this.codigoCliente,
								this.telefono, null, ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_comprobante_pago_prestamo.jsf");
			} else {
				if (this.abonoPagoPrestamo.getCodigoError().contains("52104")) {
					this.configuracionesGenerales.mensajeServicioNoDisponible();
				} else if (this.abonoPagoPrestamo.getCodigoError().contains("4928")) {
					this.configuracionesGenerales.aniadirMensajeError("Aplicacion:",
							this.abonoPagoPrestamo.getCodigoError()
									.substring(this.abonoPagoPrestamo.getCodigoError().indexOf("Valor"))
									.replace("_", "").replace("max", "máximo"));
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
				}
				System.out.println("Error pago de prestamo: " + abonoPagoPrestamo.getCodigoError());
				// this.aniadirMensajeError("Detalle : ",abonoPagoPrestamo.getCodigoError());
			}
		} else if (respuesta == 0) {
			this.aniadirMensajeError("Aplicación : ", "Código ingresado no existe.");
		} else if (respuesta == 2) {
			this.aniadirMensajeError("Aplicación : ", "Tiempo máximo de vigencia de su código de seguridad expiro.");
		}
	}

	/* PAGO DE SERVICIOS */
	public void consultaServicios() {
		/* Lista servicios por empresa para pagos de servicios */
		this.listadoServicios = servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaServiciosEmpresa(this.getHttpSession().getId(), this.codigoEmpresa);
	}

	public void filtraRubrosGad() {
		System.out.println("Filtrando rubros GAD para id " + this.codigoEmpresa);
		this.titulosFiltrados = new ArrayList<Titulo>();
		this.tituloSeleccionado = null;
		this.totalPagoGAD = 0D;
		if (this.codigoEmpresa != null)
			for (Titulo titulo : this.deudaGad.getDetalle().getTitulo()) {
				if (titulo.getCodigoRubro().equals(this.codigoEmpresa.toString()))
					this.titulosFiltrados.add(titulo);
			}
	}

	public void buscarDatosPagoServicio() {
		if (opcionBusqueda.equals("servicio")) {
			System.out.println("Voy a buscar con criterio servicio...");
			numeroServicio = this.numeroIdenticacionServicio;
			numeroIdentificacion = null;
			this.pagoServicio.setNumeroServicio(numeroServicio);
		} else if (opcionBusqueda.equals("identificacion")) {
			System.out.println("Voy a buscar con criterio identificacion...");
			numeroIdentificacion = this.numeroIdenticacionServicio;
			numeroServicio = null;
		}
		System.out.println("Nuemero identificacion : " + numeroIdentificacion);
		System.out.println("Codigo servicio : " + this.pagoServicio.getCodigoTipoServicio());

		System.out.println("Numero servicio : " + numeroServicio);
		datosPagoServicio = this.servicioTransaccion.getIServicioTransaccionClienteWs().consultaPagoServicio(
				this.getHttpSession().getId(), numeroIdentificacion, this.pagoServicio.getCodigoTipoServicio(),
				numeroServicio);

		consolidadoPagoServicio = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.consultaConsolidadoServicio(this.getHttpSession().getId(), numeroIdentificacion,
						this.pagoServicio.getCodigoTipoServicio(), numeroServicio);

		System.out.println(consolidadoPagoServicio.toString());
		this.valorAdeudado = consolidadoPagoServicio.getValorAdeudado();
	}

	public void consultaDeudaGAD() {
		try {
			System.out.println("consultaDeudaGAD");
			System.out.println("Usando clienteWS: " + this.urlClienteMunicipio);

			this.rubrosAdeudadosGad = new ArrayList<Rubro>();
			this.titulosFiltrados = new ArrayList<Titulo>();
			this.deudaGad = null;
			this.tituloSeleccionado = null;
			this.comisionGad = 0D;
			this.ivaGad = 0D;
			this.totalComisionGad = 0D;
			this.totalPagoGAD = 0D;
			Empleado clientePago = servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
					.consultaClientePorCodigoCliente(this.getHttpSession().getId(),
							this.servicioUsuario.getTwebUsuario().getCodigoCliente());
			if (clientePago != null) {
				System.out.println("Cliente Pago: " + clientePago.getNumeroIdentificacion());
				ServiciosMunicipioClienteService interfaz = new ServiciosMunicipioClienteService(
						new URL(this.urlClienteMunicipio));
				this.deudaGad = interfaz.getServiciosMunicipioClientePort()
						.consulta(clientePago.getNumeroIdentificacion(), this.numeroIdenticacionServicio, Canal.WEB);
				System.out.println("Resultado consulta GAD: \nRespuesta: " + this.deudaGad.getCodigoRespuesta()
						+ "\nMensaje: " + this.deudaGad.getMensaje() + "\nDeudor: " + this.deudaGad.getContribuyente());
				if (this.deudaGad.getCodigoRespuesta().equals("01"))// consulta OK
				{
					if (this.deudaGad.getTotalDeuda() == 0) {
						this.aniadirMensajeInformacion("Aplicación : ", "Identificación no registra deuda");
					} else {
						this.nombreDeudorGad = this.deudaGad.getContribuyente();
						this.comisionGad = this.deudaGad.getComision();// + this.deudaGad.getIva();
						this.ivaGad = this.deudaGad.getIva();
						this.totalComisionGad = this.comisionGad + this.ivaGad;
						// Consolidar los rubros
						for (Titulo titulo : this.deudaGad.getDetalle().getTitulo()) {
							// consultar si el rubro ya esta en el resumen
							if (this.rubrosAdeudadosGad.stream().filter(o -> o.getNombre().equals(titulo.getRubro()))
									.count() == 0L) {
								Rubro rubro = new Rubro();
								rubro.setId(titulo.getCodigoRubro());
								rubro.setNombre(titulo.getRubro());
								this.rubrosAdeudadosGad.add(rubro);
							}
						}
					}
				} else if (this.deudaGad.getCodigoRespuesta().equals("02")) {
					this.aniadirMensajeInformacion("Aplicación : ", "No se encontraron registros en el GAD");
				} else {
					this.aniadirMensajeError("Aplicación : ", "Error al consultar valores GAD");
				}
			} else {
				this.nombreBeneficiario = "";
				this.aniadirMensajeError("Aplicación : ", "No se encontraron datos del usuario.");
			}

//		} catch (SOAPFaultException e) {
//			e.printStackTrace();
		} catch (Exception e) {
			this.aniadirMensajeError("Aplicación : ", "Error al consultar servicio GAD");
			e.printStackTrace();
		}
	}

	public void validaDatosPagoGAD() {
		boolean control = true;
		DecimalFormat formateador = new DecimalFormat("#########.##");

		if (this.tituloSeleccionado == null) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger un rubro a pagar");
			System.out.println("Objeto pago de GAD : NULL");
			control = false;
		} else {
			System.out.println("Objeto pago de GAD : " + this.tituloSeleccionado.getContrapartida());
		}
		System.out.println("Numero cuenta : " + this.cuentaDebito);
		System.out.println("Total Pago : " + this.totalPagoGAD);

		if (this.cuentaDebito == null || this.cuentaDebito.equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger una cuenta");
			control = false;
		}

		if (control)
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.totalPagoGAD) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
				control = false;
			}

		if (control) {
			System.out.println("Valor adeudado : " + formateador.format(this.tituloSeleccionado.getMonto()));
			System.out.println("Valor a pagar : " + formateador.format(this.totalPagoGAD));

			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S", "Pago GAD");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				System.out.println("Antes de redireccionar...");

				this.descripcionEnpresa = "GAD Portoviejo";
				this.descripcionServicio = this.tituloSeleccionado.getRubro();
				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_confirma_pago_gad.jsf");
			} else {
				this.aniadirMensajeError("No se pudo enviar código de seguridad : ", error);
			}
		}
	}

	public void trxPagoGAD() {
		try {
			System.out.println("trxPagoGAD");
			/* Volver a validar el saldo de la cuenta de ahorro */
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.totalPagoGAD) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
			} else {
				System.out.println("Antes de confirmar => codigo de seguridad");

				Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);

				System.out.println("Depues de confirmar en codigo de seguridad");
				System.out.println("Resultado de confirmacion : " + respuesta);
				if (respuesta == 1) {
					// armar pago
					PagoIn pago = new PagoIn();
					pago.setCodigoAgencia("101");
					pago.setNumeroCuenta(this.cuentaDebito.toString());
					pago.setTotal(this.totalPagoGAD);
					pago.setComision(this.comisionGad);
					pago.setIva(this.ivaGad);
					pago.setEmail(this.correo);
					pago.setUsuario("HBANKING");
					pago.setCanal(Canal.WEB);
					Titulos titulos = new Titulos();
					titulos.getTitulo().add(this.tituloSeleccionado);
					pago.setTitulos(titulos);

					// aplicar pago al clte ws del Municipio
					System.out.println("Usando clienteWS: " + this.urlClienteMunicipio);
					ServiciosMunicipioClienteService interfaz = new ServiciosMunicipioClienteService(
							new URL(this.urlClienteMunicipio));

					PagoOut resultadoPago = interfaz.getServiciosMunicipioClientePort().pago(pago);

					if (resultadoPago.getCodigoRespuesta().equals(Estado.OK)) {
						this.fechaHora = new Date();
						List<String> parametros = new ArrayList<String>();
						parametros.add(this.descripcionEnpresa);
						parametros.add(this.descripcionServicio);
						parametros.add(this.tituloSeleccionado.getClave());
						parametros.add(this.tituloSeleccionado.getCedulaRuc());
						parametros.add(this.tituloSeleccionado.getNombre());
						parametros.add(this.tituloSeleccionado.getMonto().toString());
						parametros.add(this.totalComisionGad.toString());
						parametros.add(this.totalPagoGAD.toString());
						parametros.add(this.totalPagoGAD.toString());
						parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
						parametros.add(this.tituloSeleccionado.getContrapartida());
						System.out.println("Envio de comprobante pago gad: "
								+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
										this.getHttpSession().getId(), this.codigoCliente, null, this.correo,
										ValoresConstantes.PLANTILLACOMPROBANTEPAGOGAD, parametros));

						parametros = new ArrayList<String>();
						parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
						parametros.add("Pago GAD");
						parametros.add(this.totalPagoGAD.toString());
						System.out.println("Envio de notificacion pago gad: "
								+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
										this.getHttpSession().getId(), this.codigoCliente, this.telefono, null,
										ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

						this.configuracionesGenerales.redireccionarUrlConstante(
								NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
								"pag_cliente_comprobante_pago_gad.jsf");
					} else {
						System.out.println(resultadoPago.getObservacion());
						this.aniadirMensajeError("Aplicación : ", "Error al pagar servicio GAD");
					}

				} else if (respuesta == 0) {
					this.aniadirMensajeError("Aplicación : ", "Código ingresado no existe.");
				} else if (respuesta == 2) {
					this.aniadirMensajeError("Aplicación : ",
							"Tiempo máximo de vigencia de su código de seguridad expiro.");
				}
			}
		} catch (Exception e) {
			this.aniadirMensajeError("Aplicación : ", "No se pudo completar la transacción");
			e.printStackTrace();
		}
	}

	public void filtraValoresRecarga() {
		System.out.println("Filtrando valores de recarga para " + this.idRubroFacilito);
		this.listadoValoresRecargas = null;
		this.valorRecarga = 0D;
		if (this.idRubroFacilito != "") {
			this.rubroFacilito = this.rubrosRecargaFacilito.stream()
					.filter(o -> o.getIdentidad().equals(this.idRubroFacilito)).findFirst().orElse(null);
			if (this.rubroFacilito != null) {
				System.out.println("Valores para recarga " + this.rubroFacilito.getValores());
				String valores = this.rubroFacilito.getValores().replace("</VALOR><VALOR>", "|").replace("<VALOR>", "")
						.replace("</VALOR>", "");
				// System.out.println("Valores para recarga " + valores);

				this.tituloReferenciaRecarga = this.rubroFacilito.getTituloReferencia();

				this.listadoValoresRecargas = new ArrayList<ListadoOpciones>();

				String[] valoresSplit = valores.split("\\|");
				List<String> array = Arrays.asList(valoresSplit);
				for (String a : array) {
					this.listadoValoresRecargas.add(new ListadoOpciones(a, a));
					// System.out.println(a);
				}
				System.out.println("Se pobla la lista con " + this.listadoValoresRecargas.size() + " valores");
			}
		}
	}

	public void validaDatosRecarga(ActionEvent evento) {
		boolean control = true;
		this.confirmaRecarga = false;
		// DecimalFormat formateador = new DecimalFormat("#########.##");

		if (this.rubroFacilito == null) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger una recarga");
			System.out.println("idRubroFacilito : NULL");
			control = false;
		} else {
			System.out.println(
					"Recarga seleccionada " + this.rubroFacilito.getIdentidad() + " " + this.rubroFacilito.getNombre());
		}
		System.out.println("Numero cuenta : " + this.cuentaDebito);
		System.out.println("Valor recarga : " + this.valorRecarga);

		if (this.valorRecarga == 0D) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger un valor");
			control = false;
		}

		if (this.cuentaDebito == null || this.cuentaDebito.equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger una cuenta");
			control = false;
		}

		if (this.numeroIdenticacionServicio == null || this.numeroIdenticacionServicio.equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Debe ingresar un número de telefono/contrato/tarjeta");
			control = false;
		}

		if (control) {
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.valorRecarga) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
				control = false;
			}
		}

		if (control) {
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S", "Recarga");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				this.confirmaRecarga = true;
				System.out.println("Antes de redireccionar...");
			} else {
				this.aniadirMensajeError("No se pudo enviar código de seguridad : ", error);
			}
		}
	}

	public void trxRecargaFacilito() {
		try {
			System.out.println("trxRecargaFacilito");
			/* Volver a validar el saldo de la cuenta de ahorro */
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.valorRecarga) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
			} else {
				System.out.println("Antes de confirmar => codigo de seguridad");

				Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);

				System.out.println("Depues de confirmar en codigo de seguridad");
				System.out.println("Resultado de confirmacion : " + respuesta);
				if (respuesta == 1) {
					// armar pago
					Empleado clientePago = servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
							.consultaClientePorCodigoCliente(this.getHttpSession().getId(),
									this.servicioUsuario.getTwebUsuario().getCodigoCliente());
					if (clientePago != null) {
						SolicitudRecarga recarga = new SolicitudRecarga();
						recarga.setPnAgencia(101L);
						recarga.setPnCuenta(this.cuentaDebito.toString());
						recarga.setPvCanal("WEB");
						recarga.setPvEmail(this.servicioUsuario.getTwebUsuario().getCorreo());
						recarga.setPvIdProductoFacilito(this.rubroFacilito.getIdentidad());
						recarga.setPvIdentificacion(clientePago.getNumeroIdentificacion());
						recarga.setPvNombres(clientePago.getNombres());
						recarga.setPvUsuario("HBANKING");
						recarga.setReferencia(this.numeroIdenticacionServicio);
						recarga.setValorConComision(this.valorRecarga);

						// aplicar en el clientews de facilito
						System.out.println("Usando clienteWS: " + this.urlClienteFacilito);
						ServicoIntegracionFacilito_Service interfaz = new ServicoIntegracionFacilito_Service(
								new URL(this.urlClienteFacilito));
						RespuestaRecarga resultadoRecarga = interfaz.getServicoIntegracionFacilitoPort()
								.recargas(recarga);
						if (resultadoRecarga.getCodigoFlujo().equals("0000")) {
							this.fechaHora = new Date();

							System.out.println(
									"Recarga afectada en el core con ID: " + resultadoRecarga.getSecuenciaCore());

							System.out.println("RECIBO RECIBIDO");
							System.out.println(resultadoRecarga.getRespuestaFacilito().getXMLRecibo());

							this.reciboFacilito = printXML(
									resultadoRecarga.getRespuestaFacilito().getXMLRecibo().replace(";", ":"));

							System.out.println("RECIBO");
							System.out.println(this.reciboFacilito);
							// InputStream is = new
							// ByteArrayInputStream(this.reciboFacilito.getBytes("UTF-8"));
							// Document doc =
							// DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
							// doc.getDocumentElement().normalize();
							// printXML(doc.getDocumentElement());

							// enviar comprobante y envia a la pagina de impresion
//							this.fechaHora = new Date();
							List<String> parametros = new ArrayList<String>();
							parametros.add(this.reciboFacilito);
//							parametros.add(this.descripcionServicio);
//							parametros.add(this.tituloSeleccionado.getClave());
//							parametros.add(this.tituloSeleccionado.getCedulaRuc());
//							parametros.add(this.tituloSeleccionado.getNombre());
//							parametros.add(this.tituloSeleccionado.getMonto().toString());
//							parametros.add(this.totalComisionGad.toString());
//							parametros.add(this.totalPagoGAD.toString());
//							parametros.add(this.totalPagoGAD.toString());
//							parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
//							parametros.add(this.tituloSeleccionado.getContrapartida());

							System.out.println("Envio de comprobante recarga: "
									+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
											this.getHttpSession().getId(), this.codigoCliente, null, this.correo,
											ValoresConstantes.PLANTILLARECIBOFACILITO, parametros));

							parametros = new ArrayList<String>();
							parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
							parametros.add("Recarga");
							parametros.add(this.valorRecarga.toString());
							System.out.println("Envio de notificacion recarga: "
									+ this.serviciosUsuario.getIServicioUsuarioWs().envioNotificacion(
											this.getHttpSession().getId(), this.codigoCliente, this.telefono, null,
											ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

							this.configuracionesGenerales.redireccionarUrlConstante(
									NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
									"pag_cliente_comprobante_facilito.jsf");
						} else {
							System.out.println("Resultado recarga: " + resultadoRecarga.getMensajeTransaccional());
							this.aniadirMensajeError("Aplicación : ",
									"Error al realizar recarga " + resultadoRecarga.getMensajeTransaccional());
						}
					} else {
						this.aniadirMensajeError("Aplicación : ", "No se encontraron datos del usuario.");
					}
				} else if (respuesta == 0) {
					this.aniadirMensajeError("Aplicación : ", "Código ingresado no existe.");
				} else if (respuesta == 2) {
					this.aniadirMensajeError("Aplicación : ",
							"Tiempo máximo de vigencia de su código de seguridad expiro.");
				}
			}
		} catch (Exception e) {
			this.aniadirMensajeError("Aplicación : ", "No se pudo completar la transacción");
			e.printStackTrace();
		}
	}

	public void buscaProductosPorGrupoFacilito() {
		System.out.println("Buscando productos facilito para grupo " + this.grupoFacilito);
		this.numeroIdenticacionServicio = "";
		this.nombreBeneficiario = "";
		this.numeroServicio = "";
		this.totalPagoGAD = 0D;
		this.rubrosFacilito = null;
		this.rubrosAdeudadosFacilito = new ArrayList<INTResplyConsultaINTDataConsulta>();
		this.rubroSeleccionado = null;
		if (this.grupoFacilito != "") {
			this.rubrosFacilito = this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.listadoRubrosPorGrupoFacilito(this.getHttpSession().getId(), this.grupoFacilito);
		}
	}

	public void determinaTipoPagoFacilito() {
		System.out.println("Determina tipo de pago para: " + this.idRubroFacilito);
		this.totalPagoGAD = 0D;
		this.rubrosAdeudadosFacilito = new ArrayList<INTResplyConsultaINTDataConsulta>();
		this.rubroSeleccionado = null;
		this.rubroFacilito = null;
		if (this.idRubroFacilito != "") {
			this.rubroFacilito = this.rubrosFacilito.stream().filter(o -> o.getIdentidad().equals(this.idRubroFacilito))
					.findFirst().orElse(null);
			if (this.rubroFacilito != null) {
				System.out.println("Tipo de pago: " + this.rubroFacilito.getTipoPago());
				this.tituloReferenciaPago = this.rubroFacilito.getTituloReferencia();
				this.bloqueaAbono = this.rubroFacilito.getTipoPago().equals("A") ? false : true;
				this.pagoEspecial = this.rubroFacilito.getPagoEspecial().equals("S") ? true : false;
			}
		}
		// Se considera solamente tipo de pago A como abono, podrá ingresar un valor <=
		// al total de pago
	}

	public void consultaDeudaFacilito() {
		try {
			System.out.println("Consultando deuda para: " + this.idRubroFacilito + " " + this.numeroServicio);

			// Validacion para codigo regional CNEL
			if (this.grupoFacilito.equals("LUZ")) {
				System.out.println("Validando codigo Regional LUZ para: " + this.numeroServicio + " "
						+ this.rubroFacilito.getNombre());
				if (!this.rubroFacilito.getNombre().contains("GUAYAQUIL")) {
					// Eliminar el codigo regional
					if (this.numeroServicio.length() > 2) {
						this.numeroServicio = this.numeroServicio.substring(2, this.numeroServicio.length());
					}
					// eliminar 0's a la izquierda
					while (this.numeroServicio.substring(0, 1).equals("0")) {
						this.numeroServicio = this.numeroServicio.substring(1, this.numeroServicio.length());
					}
				}
			}

			boolean consultar = true;

			if (this.idRubroFacilito == null || this.idRubroFacilito.equals("")) {
				consultar = false;
				this.aniadirMensajeError("Aplicación : ", "Debe escoger una producto");
			}

			if (this.numeroServicio == null || this.numeroServicio.equals("")) {
				consultar = false;
				this.aniadirMensajeError("Aplicación : ", "Debe ingresar una referencia");
			}

			if (consultar) {
				this.numeroIdenticacionServicio = "";
				this.nombreBeneficiario = "";
				this.totalPagoGAD = 0D;

				System.out.println("Usando clienteWS: " + this.urlClienteFacilito);
				ServicoIntegracionFacilito_Service interfaz = new ServicoIntegracionFacilito_Service(
						new URL(this.urlClienteFacilito));
				SolicitudConsulta solicitud = new SolicitudConsulta();
				solicitud.setCanal("WEB");
				solicitud.setIdProducto(this.idRubroFacilito);
				solicitud.setReferencia(this.numeroServicio);

				this.consultaDeudaFacilito = new INTResplyConsulta();
				this.consultaDeudaFacilito = interfaz.getServicoIntegracionFacilitoPort()
						.consultaRedFacilito(solicitud);
				this.rubrosAdeudadosFacilito = new ArrayList<INTResplyConsultaINTDataConsulta>();
				this.rubroSeleccionado = null;

				if (this.consultaDeudaFacilito.getCodigoResultado().equals("000")) {
					this.numeroIdenticacionServicio = this.consultaDeudaFacilito.getIdentificacion();
					this.nombreBeneficiario = this.consultaDeudaFacilito.getNombre();
					this.rubrosAdeudadosFacilito = this.consultaDeudaFacilito.getDataConsulta()
							.getINTResplyConsultaINTDataConsulta();
				} else {
					this.numeroIdenticacionServicio = "";
					this.nombreBeneficiario = "";
					System.out.println("Resultado consulta: " + this.consultaDeudaFacilito.getCodigoResultado()
							+ this.consultaDeudaFacilito.getMensaje());
					this.aniadirMensajeError("Aplicación : ", this.consultaDeudaFacilito.getMensaje());// "No se pudo
																										// procesar la
																										// consulta");
				}
			}
		} catch (Exception e) {
			this.aniadirMensajeError("Aplicación : ", "Error al procesar la consulta");
			e.printStackTrace();
		}
	}

	public void validaDatosPagoFacilito(boolean validaValorPago) {
		boolean control = true;
		DecimalFormat formateador = new DecimalFormat("#########.##");

		if (this.rubroSeleccionado == null) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger un rubro a pagar");
			System.out.println("Objeto pago de Facilito : NULL");
			control = false;
		} else {
			System.out.println("Objeto pago de Facilito : " + this.rubroSeleccionado.getIDRubro() + " - "
					+ this.rubroSeleccionado.getDescripcion() + " Prioridad " + this.rubroSeleccionado.getPrioridad());
			// validar prioridad de pago (solo 1) para tipos de pago != T
			if (!this.rubroFacilito.getTipoPago().equals("T") && this.rubroSeleccionado.getPrioridad() != 1) {
				this.aniadirMensajeError("Aplicación : ", "Debe realizar su pago por orden de prioridad");
				control = false;
			}
		}
		System.out.println("Numero cuenta : " + this.cuentaDebito);
		System.out.println("Total Pago : " + this.totalPagoGAD);

		if (this.cuentaDebito == null || this.cuentaDebito.equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Debe escoger una cuenta");
			control = false;
		}

		if (this.totalPagoGAD == 0D) {
			this.aniadirMensajeError("Aplicación : ", "Debe ingresar un valor para el pago");
			control = false;
		}

		if (control)
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.totalPagoGAD) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
				control = false;
			}

		if (control && validaValorPago) {
			System.out.println("Valor adeudado : "
					+ formateador.format(this.rubroSeleccionado.getValorConComision().doubleValue()));
			System.out.println("Valor a pagar : " + formateador.format(this.totalPagoGAD));

			// consideracion "PAGO ESPECIAL" no valida monto a pagar contra el adeudado
			if (!this.pagoEspecial) {
				if (this.totalPagoGAD > this.rubroSeleccionado.getValorConComision().doubleValue()) {
					this.aniadirMensajeError("Aplicación : ", "El valor a pagar no puede ser superior al adeudado");
					control = false;
				}
			}
		}

		if (control) {
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
					"Pago servicio");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				System.out.println("Antes de redireccionar...");

				this.descripcionEnpresa = "Red Facilito";
				this.descripcionServicio = this.grupoFacilito + " " + this.rubroFacilito.getNombre();
				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_confirma_pago_facilito.jsf");
			} else {
				this.aniadirMensajeError("No se pudo enviar código de seguridad : ", error);
			}
		}
	}

	public void trxPagoFacilito() {
		try {
			System.out.println("trxPagoFacilito");
			/* Volver a validar el saldo de la cuenta de ahorro */
			if (this.servicioTransaccion.getIServicioTransaccionClienteWs()
					.saldoCuentaAhorro(this.getHttpSession().getId(), this.cuentaDebito) < this.totalPagoGAD) {
				this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
			} else {
				System.out.println("Antes de confirmar => codigo de seguridad");

				Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
						this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);

				System.out.println("Depues de confirmar en codigo de seguridad");
				System.out.println("Resultado de confirmacion : " + respuesta);
				if (respuesta == 1) {
					// armar pago
					Empleado clientePago = servicioAdministracionUsuario.getIServicioAdministracionUsuarioWs()
							.consultaClientePorCodigoCliente(this.getHttpSession().getId(),
									this.servicioUsuario.getTwebUsuario().getCodigoCliente());
					if (clientePago != null) {
						SolicitudPago pago = new SolicitudPago();
						pago.setPnAgencia(101L);
						pago.setPnCuenta(this.cuentaDebito.toString());
						pago.setPvCanal(this.consultaDeudaFacilito.getObjRecuest().getCanal());
						pago.setPvEmail(this.servicioUsuario.getTwebUsuario().getCorreo());
						pago.setPvIdProductoFacilito(this.consultaDeudaFacilito.getObjRecuest().getIDProducto());
						pago.setPvIdentificacion(this.consultaDeudaFacilito.getIdentificacion());
						pago.setPvNombres(this.consultaDeudaFacilito.getNombre());
						pago.setPvNumeroServicio(this.consultaDeudaFacilito.getObjRecuest().getReferencia());
						pago.setPvUsuario("HBANKING");
						pago.setPnIva(0D);
						pago.setPnSubtotal(this.rubroSeleccionado.getComision().doubleValue());
						pago.setPnMonto(this.totalPagoGAD - this.rubroSeleccionado.getComision().doubleValue());// pago.setPnMonto(this.rubroSeleccionado.getValor().doubleValue());
						pago.setIDTransaccion(this.consultaDeudaFacilito.getIDTransaccion());

						SolicitudDataPago datoPago = new SolicitudDataPago();
						datoPago.setIdRubro(this.rubroSeleccionado.getIDRubro());
						datoPago.setValorConComision(this.totalPagoGAD);// (this.rubroSeleccionado.getValorConComision().doubleValue());

						pago.getListaPagos().add(datoPago);

						// aplicar en el clientews de facilito
						System.out.println("Usando clienteWS: " + this.urlClienteFacilito);
						ServicoIntegracionFacilito_Service interfaz = new ServicoIntegracionFacilito_Service(
								new URL(this.urlClienteFacilito));

						ResultadoProcesoPago resultadoPago = interfaz.getServicoIntegracionFacilitoPort().pago(pago);
						if (resultadoPago != null) {
							RespuestaPago respuestaPago = resultadoPago.getListaRespuestaPago().get(0);
							if (respuestaPago != null) {
								if (respuestaPago.getCodigoFlujo().equals("0000")) {
									this.fechaHora = new Date();
									System.out.println(
											"Pago afectado en el core con ID: " + respuestaPago.getSecuenciaCore());

									System.out.println("RECIBO RECIBIDO");
									System.out.println(respuestaPago.getRespuestaFacilito().getXMLRecibo());

									this.reciboFacilito = printXML(
											respuestaPago.getRespuestaFacilito().getXMLRecibo().replace(";", ":"));

									System.out.println("RECIBO FORMATEADO");
									System.out.println(this.reciboFacilito);

									List<String> parametros = new ArrayList<String>();
									parametros.add(this.reciboFacilito);

									System.out.println("Envio de comprobante pago facilito: " + this.serviciosUsuario
											.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(),
													this.codigoCliente, null, this.correo,
													ValoresConstantes.PLANTILLARECIBOFACILITO, parametros));

									parametros = new ArrayList<String>();
									parametros
											.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
									parametros.add("Pago servicio");
									parametros.add(this.totalPagoGAD.toString());
									System.out.println("Envio de notificacion pago facilito: " + this.serviciosUsuario
											.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(),
													this.codigoCliente, this.telefono, null,
													ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

									this.configuracionesGenerales.redireccionarUrlConstante(
											NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB,
											"pag_cliente_comprobante_facilito.jsf");
								} else {
									System.out.println("Resultado pago: " + respuestaPago.getMensajeTransaccional());
									this.aniadirMensajeError("Aplicación : ",
											"Error al procesar pago " + respuestaPago.getMensajeTransaccional());
								}
							} else {
								this.aniadirMensajeError("Aplicación : ", "Error al procesar respuesta pago");
							}
						} else {
							this.aniadirMensajeError("Aplicación : ", "Error al procesar resultado pago");
						}
					} else {
						this.aniadirMensajeError("Aplicación : ", "No se encontraron datos del usuario.");
					}
				} else if (respuesta == 0) {
					this.aniadirMensajeError("Aplicación : ", "Código ingresado no existe.");
				} else if (respuesta == 2) {
					this.aniadirMensajeError("Aplicación : ",
							"Tiempo máximo de vigencia de su código de seguridad expiro.");
				}
			}
		} catch (Exception e) {
			this.aniadirMensajeError("Aplicación : ", "No se pudo completar la transacción");
			e.printStackTrace();
		}
	}

	private String printXML(String xml) {
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			doc.getDocumentElement().normalize();

			String resultado = printXMLnodesHTML((Node) doc.getDocumentElement(), 0);
			return resultado + "</table></center>";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String printXMLnodesHTML(Node node, int indent) {

		String linea = "";

		// System.out.println(); // salto de linea donde empezamos a escribir el nuevo
		// nodo
		// for (int i = 0; i < indent; i++)
		// System.out.print("\t"); // aplicamos sangría correspondiente

		// System.out.print(node.getNodeName()); // imprimimos nombre del nodo

		// si el nodo tiene atributos, los imprimimos
		if (node.hasAttributes()) {
			NamedNodeMap nnm = node.getAttributes();
			for (int i = 0; i < nnm.getLength(); i++) {
				System.out.print(" (" + nnm.item(i).getNodeName() + ": " + nnm.item(i).getNodeValue() + ")+");
			}
		}

		// System.out.print(": "); // parar separar el nombre del nodo de su contenido

		// por cada hijo de este nodo...
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {

			// aqui filtramos solo los hijos directos, los hijos de hijos seran considerados
			// por recursividad
			if (node.getChildNodes().item(i).getParentNode().isSameNode(node)) {

				// si es de tipo elemento...
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

					// llamada recursiva con dicho hijo, y ampliamos sangria
					linea += printXMLnodesHTML(node.getChildNodes().item(i), indent + 1);
					// o si es de tipo texto...
				} else if (node.getChildNodes().item(i).getNodeType() == Node.TEXT_NODE) {

					// almacenamos su valor en un string por comodidad
					String text = node.getChildNodes().item(i).getNodeValue();

					// El parser considera como TEXT_NODE los saltos de linea y tabulaciones
					// internas
					// que pueda haber en el XML original, asi que aqui filtramos para solo tener en
					// cuenta
					// los strings que realmente tengan caracteres que no sean ni espacios ni '\t',
					// '\n', '\r'
					if (text.trim().length() > 0) {
						if (!text.equals(".")) {
							// para linea 2,3,4,5 se centra
							if (node.getNodeName().equals("LINEA_2") || node.getNodeName().equals("LINEA_3")
									|| node.getNodeName().equals("LINEA_5")) {
								linea += "<center>" + text + "</center>\n";
							} else if (node.getNodeName().equals("LINEA_4")) {
								linea += "<center><b>" + text + "</b></center>\n";
							} else if (node.getNodeName().equals("LINEA_7")) {
								// linea += "<center><table><tr><td>" + text + "</td></tr>\n";
								linea += "<center><table style=\"font-size: 12px\"><tr><td>" + text + "</td></tr>\n";
							} else if (node.getNodeName().equals("LINEA_41")) {
								// formato para recibo con adjunto en el pie para descarga de comprobante en
								// empresa emisora
								linea += "<tr><td>" + text.replace("ingrese a", "<br>ingrese a").replace("y obtenga",
										"<br>y obtenga") + "</td></tr>\n";
							} else if (text.contains("USUARIO")) {
								// se agrega la agencia antes del usuario (valor agregado por nosotros)
								linea += "<tr><td>AGENCIA: WEB</td></tr>\n";
								linea += "<tr><td>" + text + "</td></tr>\n";
							} else if (text.contains("CIUDAD")) {
								// se reemplaza el N/D del recibo por "PORTOVIEJO"
								linea += "<tr><td>" + text.replace("N/D", "Portoviejo") + "</td></tr>\n";
							} else {
								linea += "<tr><td>" + text + "</td></tr>\n";
							}

						}
						// System.out.print(text); // imprimimos el contenido
					}
				}
			}
		}
		return linea;
	}

	private void printXML(Element root) {
		printXMLnodes((Node) root, 0);
		System.out.println();
	}

	private void printXMLnodes(Node node, int indent) {

		System.out.println(); // salto de linea donde empezamos a escribir el nuevo nodo
		for (int i = 0; i < indent; i++)
			System.out.print("\t"); // aplicamos sangría correspondiente

		System.out.print(node.getNodeName()); // imprimimos nombre del nodo

		// si el nodo tiene atributos, los imprimimos
		if (node.hasAttributes()) {
			NamedNodeMap nnm = node.getAttributes();
			for (int i = 0; i < nnm.getLength(); i++) {
				System.out.print(" (" + nnm.item(i).getNodeName() + ": " + nnm.item(i).getNodeValue() + ")");
			}
		}

		System.out.print(": "); // parar separar el nombre del nodo de su contenido

		// por cada hijo de este nodo...
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {

			// aqui filtramos solo los hijos directos, los hijos de hijos seran considerados
			// por recursividad
			if (node.getChildNodes().item(i).getParentNode().isSameNode(node)) {

				// si es de tipo elemento...
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

					// llamada recursiva con dicho hijo, y ampliamos sangria
					printXMLnodes(node.getChildNodes().item(i), indent + 1);

					// o si es de tipo texto...
				} else if (node.getChildNodes().item(i).getNodeType() == Node.TEXT_NODE) {

					// almacenamos su valor en un string por comodidad
					String text = node.getChildNodes().item(i).getNodeValue();

					// El parser considera como TEXT_NODE los saltos de linea y tabulaciones
					// internas
					// que pueda haber en el XML original, asi que aqui filtramos para solo tener en
					// cuenta
					// los strings que realmente tengan caracteres que no sean ni espacios ni '\t',
					// '\n', '\r'
					if (text.trim().length() > 0) {
						System.out.print(text); // imprimimos el contenido
					}
				}
			}
		}

	}

	public void etiquetaBusqueda() {
		if (opcionBusqueda.equals("servicio")) {
			this.etiquetaBusqueda = "servicio :";
		} else if (opcionBusqueda.equals("identificacion")) {
			this.etiquetaBusqueda = "Cédula/RUC/Pasaporte :";
		}
	}

	public void actualizaValorPago(Integer id) {
		System.out.println(id.toString());
		for (DatosPagoServicio resultado : this.datosPagoServicio) {
			if (resultado.getId() == id && resultado.getEstadoPago() == false) {
				this.consolidadoPagoServicio.setValorAdeudado(
						this.consolidadoPagoServicio.getValorAdeudado() - resultado.getValorAdeudado());
				this.consolidadoPagoServicio
						.setValorComision(this.consolidadoPagoServicio.getValorComision() - resultado.getCargoMinimo());
				this.consolidadoPagoServicio.setValorMinimoPago(
						this.consolidadoPagoServicio.getValorMinimoPago() - resultado.getValorAdeudado());
				System.out.println("Resta : " + resultado.toString());
			} else if (resultado.getId() == id && resultado.getEstadoPago() == true) {
				this.consolidadoPagoServicio.setValorAdeudado(
						this.consolidadoPagoServicio.getValorAdeudado() + resultado.getValorAdeudado());
				this.consolidadoPagoServicio
						.setValorComision(this.consolidadoPagoServicio.getValorComision() + resultado.getCargoMinimo());
				this.consolidadoPagoServicio.setValorMinimoPago(
						this.consolidadoPagoServicio.getValorMinimoPago() + resultado.getValorAdeudado());
				System.out.println("Suma : " + resultado.toString());
			}
		}
	}

	public void seleccionarTodos() {
		List<DatosPagoServicio> listado = new ArrayList<DatosPagoServicio>();
		for (DatosPagoServicio resultado : this.datosPagoServicio) {
			if (!this.seleccionarTodo) {
				resultado.setEstadoPago(false);
			} else {
				resultado.setEstadoPago(true);
			}
			listado.add(resultado);
		}
		this.datosPagoServicio = listado;

		if (!this.seleccionarTodo) {
			this.consolidadoPagoServicio.setValorAdeudado(0.00);
			this.consolidadoPagoServicio.setValorCancelado(0.00);
			this.consolidadoPagoServicio.setValorComision(0.00);
			this.consolidadoPagoServicio.setValorMinimoPago(0.00);
		} else {
			this.consolidadoPagoServicio = servicioTransaccion.getIServicioTransaccionClienteWs()
					.consultaConsolidadoServicio(this.getHttpSession().getId(), numeroIdentificacion,
							this.pagoServicio.getCodigoTipoServicio(), numeroServicio);
		}
	}

	/* Confirmacion */
	public void validaDatosPagoServicio() {
		boolean control = true;
		DecimalFormat formateador = new DecimalFormat("#########.##");

		System.out.println("Obejto pago de servicio : " + this.pagoServicio.toString());
		System.out.println("Numero cuenta : " + this.pagoServicio.getNumeroCuenta().toString());

		if (this.numeroIdenticacionServicio == null || this.numeroIdenticacionServicio.equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Número de servicio/identificación no puede ser nulo.");
			control = false;
		}

		if (this.pagoServicio.getNumeroCuenta() == null || this.pagoServicio.getNumeroCuenta().equals("")) {
			this.aniadirMensajeError("Aplicación : ", "Número de cuenta no puede ser nulo.");
			control = false;
		}

		if (this.servicioTransaccion.getIServicioTransaccionClienteWs().saldoCuentaAhorro(this.getHttpSession().getId(),
				this.pagoServicio.getNumeroCuenta()) < this.pagoServicio.getValor()) {
			this.aniadirMensajeError("Aplicación : ", "Saldo disponible menor a valor de pago.");
			control = false;
		}

		System.out.println("Valor adeudado : " + formateador.format(
				this.consolidadoPagoServicio.getValorAdeudado() + this.consolidadoPagoServicio.getValorComision()));

		System.out.println("Valor a pagar : " + formateador.format(this.pagoServicio.getValor()));

		if (Double.valueOf(formateador.format(this.pagoServicio.getValor())) > Double.valueOf(formateador.format(
				this.consolidadoPagoServicio.getValorAdeudado() + this.consolidadoPagoServicio.getValorComision()))) {
			this.aniadirMensajeError("Aplicación : ", "Valor a pagar es mayor al valor adeudado.");
			control = false;
		}

		if (this.pagoServicio.getValor() < this.consolidadoPagoServicio.getValorMinimoPago()
				+ this.consolidadoPagoServicio.getValorComision()) {
			this.aniadirMensajeError("Aplicación : ", "El monto a pagar no puede ser menor que el pago mínimo.");
			control = false;
		}
		if (control) {
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.telefono, this.correo, "S",
					"Pago servicio");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				System.out.println("Antes de redireccionar...");

				this.descripcionEnpresa = this.descripcionLista(listadoEmpresas, this.codigoEmpresa.toString());
				this.descripcionServicio = this.descripcionLista(listadoServicios,
						this.pagoServicio.getCodigoTipoServicio().toString());

				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_confirma_pago_servicio.jsf");
			} else {
				this.aniadirMensajeError("No se pudo enviar código de seguridad : ", error);
			}
		}
	}

	public void trxPagoServicio() {
		/* Volver a validar el saldo de la cuenta de ahorro */
		if (this.pagoServicio.getValor() > this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.pagoServicio.getNumeroCuenta())) {
			this.aniadirMensajeError("Aplicación : ", "Saldo disponible insuficiente.");
		} else {
			System.out.println("Antes de confirmar => codigo de seguridad");

			Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);

			System.out.println("Depues de confirmar en codigo de seguridad");
			System.out.println("Resultado de confirmacion : " + respuesta);
			if (respuesta == 1) {
				boolean controlPago = true;

				for (DatosPagoServicio resultado : this.datosPagoServicio) {
					System.out.println(resultado.toString());
					if (resultado.getEstadoPago()) {
						if (resultado.getMaestroDetalle().equals("S")) {
							this.pagoServicio.setValor(resultado.getValorAdeudado() + resultado.getCargoMinimo());
						}
						this.pagoServicio.setCodigoTipoServicio(resultado.getCodigoTipoServicio());
						this.pagoServicio.setNumeroServicio(resultado.getNumeroServicio());
						this.pagoServicio.setSecuenciaEmpresa(resultado.getSecuenciaEmpresa());
						this.pagoServicio.setCobraComision("S");
						this.pagoServicio.setNumeroIdentificacion(this.numeroIdentificacion);

						System.out.println(pagoServicio.toString());

						this.pagoServicio = this.servicioTransaccion.getIServicioTransaccionClienteWs()
								.pagoServicioWeb(this.getHttpSession().getId(), pagoServicio);

						System.out.println("Despues de la transaccion : " + this.pagoServicio.getError());

						if (this.pagoServicio.getError() == null || this.pagoServicio.getError().equals("")) {
							this.fechaHora = new Date();
							controlPago = true;
							this.valorTotalCancelado = this.valorTotalCancelado + pagoServicio.getValor();

							TwebMovimientos movimiento = new TwebMovimientos();

							movimiento.setIdUsuario(this.idUsuario);
							movimiento.setCodigoVerificador(this.codigoSeguridad);
							/* 5 = pago de Servicios */
							movimiento.setIdTipoTransaccion(5);
							movimiento.setFechaValida(this.fechaValida);
							movimiento.setRegistradoPor("HBANKING");
							movimiento.setEsLocal("S");
							movimiento.setEstado("A");
							movimiento.setCanal(ec.fin.online15.backend.webbanking.modelo.entidades.Canal.WEB);
							try {
								movimiento
										.setFechaHora(formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
							} catch (ParseException e) {
								movimiento.setFechaHora(new Date());
							}
							try {
								movimiento.setFechaRegistro(
										formatoFechaHora.parse(formatoFechaHora.format(this.fechaHora)));
							} catch (ParseException e) {
								movimiento.setFechaHora(new Date());
							}
							movimiento.setIpTransaccion(this.getIpLocal());
							movimiento.setNumeroCuentaOrigen(pagoServicio.getNumeroCuenta().toString());

							movimiento.setObservacion("Pago servicio");
							movimiento.setReferencia(null);
							movimiento.setTipoCuenta("AHORRO");
							movimiento.setUsuario(this.getUsuarioAutenticado().toString());
							movimiento.setValor(pagoServicio.getValor());
							movimiento.setNumeroServicio(pagoServicio.getNumeroServicio().toString());
							movimiento.setIdTipoServicio(pagoServicio.getCodigoTipoServicio());
							movimiento.setIdentificacionBeneficiario(pagoServicio.getNumeroIdentificacion());
							movimiento.setNumeroCuentaDestino(pagoServicio.getSecuenciaEmpresa());

							servicioTransaccion.getIServicioTransaccionClienteWs()
									.registraMovimiento(this.getHttpSession().getId(), movimiento);

						} else {
							controlPago = false;
							this.configuracionesGenerales.mensajeTransaccionError();
							// this.aniadirMensajeError("Información : ",this.pagoServicio.getError());
							// Salir
							System.out.println("Error pago servicio : " + this.pagoServicio.getError());
							break;
						}
					}
				}
				if (!controlPago) {
					this.aniadirMensajeError("Aplicación : ", "No se realizaron todos los pagos.");
				}
				// this.fechaHora = new Date();

				List<String> parametros = new ArrayList<String>();
				parametros.add(this.descripcionEnpresa);
				parametros.add(this.descripcionServicio);
				parametros.add(this.pagoServicio.getNumeroServicio());
				parametros.add(this.numeroIdentificacion == null ? "0000000000" : this.numeroIdentificacion);
				parametros.add(this.consolidadoPagoServicio.getNombreDeudor());
				parametros.add(this.consolidadoPagoServicio.getValorAdeudado().toString());
				parametros.add(this.consolidadoPagoServicio.getValorComision().toString());
				parametros.add(String.valueOf((this.consolidadoPagoServicio.getValorAdeudado()
						+ this.consolidadoPagoServicio.getValorComision())));
				parametros.add(this.valorTotalCancelado.toString());
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add("PAGO DE SERVICIO");
				System.out.println("Envio de comprobante pago de servicio: " + this.serviciosUsuario
						.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(), this.codigoCliente,
								null, this.correo, ValoresConstantes.PLANTILLACOMPROBANTEPAGOSERVICIO, parametros));

				parametros = new ArrayList<String>();
				parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
				parametros.add("Pago servicio");
				parametros.add(this.valorTotalCancelado.toString());
				System.out.println("Envio de notificacion pago servicio: " + this.serviciosUsuario
						.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(), this.codigoCliente,
								this.telefono, null, ValoresConstantes.PLANTILLANOTIFICACIONTRANSACCION, parametros));

				this.configuracionesGenerales.redireccionarUrlConstante(
						NombresConstantesMemoria.RUTA_PAGINAS_USUARIO_WEB, "pag_cliente_comprobante_pago_servicio.jsf");
			} else if (respuesta == 0) {
				this.aniadirMensajeError("Aplicación : ", "Código ingresado no existe.");
			} else if (respuesta == 2) {
				this.aniadirMensajeError("Aplicación : ",
						"Tiempo máximo de vigencia de su código de seguridad expiro.");
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
		System.out.println("************************** Cancelar pago de servicio *****************************");

		this.configuracionesGenerales
				.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB, "");
	}

	public void funcionLimpiar(ActionEvent evento) {
		System.out.println(
				"************************** Limpiando formulario pago de servicios....*****************************");
		this.inicializar();
	}

	public AbonoPagoPrestamo getAbonoPagoPrestamo() {
		return abonoPagoPrestamo;
	}

	public void setAbonoPagoPrestamo(AbonoPagoPrestamo abonoPagoPrestamo) {
		this.abonoPagoPrestamo = abonoPagoPrestamo;
	}

	public List<ListadoOpciones> getListadoPrestamosCliente() {
		return listadoPrestamosCliente;
	}

	public void setListadoPrestamosCliente(List<ListadoOpciones> listadoPrestamosCliente) {
		this.listadoPrestamosCliente = listadoPrestamosCliente;
	}

	public List<ListadoOpciones> getListadoPrestamosTercero() {
		return listadoPrestamosTercero;
	}

	public List<ListadoOpciones> getListadoCuentas() {
		return listadoCuentas;
	}

	public void setListadoCuentas(List<ListadoOpciones> listadoCuentas) {
		this.listadoCuentas = listadoCuentas;
	}

	public DatosPrestamo getDatoPrestamo() {
		return datoPrestamo;
	}

	public void setDatoPrestamo(DatosPrestamo datoPrestamo) {
		this.datoPrestamo = datoPrestamo;
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

	public PagoServicio getPagoServicio() {
		return pagoServicio;
	}

	public void setPagoServicio(PagoServicio pagoServicio) {
		this.pagoServicio = pagoServicio;
	}

	public List<DatosPrestamo> getListaDatosPrestamo() {
		return listaDatosPrestamo;
	}

	public void setListaDatosPrestamo(List<DatosPrestamo> listaDatosPrestamo) {
		this.listaDatosPrestamo = listaDatosPrestamo;
	}

	public List<ListadoOpciones> getListadoEmpresas() {
		// this.inicializar();
		return listadoEmpresas;
	}

	public void setListadoEmpresas(List<ListadoOpciones> listadoEmpresas) {
		this.listadoEmpresas = listadoEmpresas;
	}

	public List<ListadoOpciones> getListadoServicios() {
		return listadoServicios;
	}

	public void setListadoServicios(List<ListadoOpciones> listadoServicios) {
		this.listadoServicios = listadoServicios;
	}

	public List<DatosPagoServicio> getDatosPagoServicio() {
		return datosPagoServicio;
	}

	public void setDatosPagoServicio(List<DatosPagoServicio> datosPagoServicio) {
		this.datosPagoServicio = datosPagoServicio;
	}

	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getOpcionBusqueda() {
		return opcionBusqueda;
	}

	public void setOpcionBusqueda(String opcionBusqueda) {
		this.opcionBusqueda = opcionBusqueda;
	}

	public String getEtiquetaBusqueda() {
		return etiquetaBusqueda;
	}

	public void setEtiquetaBusqueda(String etiquetaBusqueda) {
		this.etiquetaBusqueda = etiquetaBusqueda;
	}

	public String getNumeroIdenticacionServicio() {
		return numeroIdenticacionServicio;
	}

	public void setNumeroIdenticacionServicio(String numeroIdenticacionServicio) {
		this.numeroIdenticacionServicio = numeroIdenticacionServicio;
	}

	public ConsolidadoPagoServicio getConsolidadoPagoServicio() {
		return consolidadoPagoServicio;
	}

	public void setConsolidadoPagoServicio(ConsolidadoPagoServicio consolidadoPagoServicio) {
		this.consolidadoPagoServicio = consolidadoPagoServicio;
	}

	public DatosPagoServicio getDatoSeleccionado() {
		return datoSeleccionado;
	}

	public void setDatoSeleccionado(DatosPagoServicio datoSeleccionado) {
		this.datoSeleccionado = datoSeleccionado;
	}

	public boolean isSeleccionarTodo() {
		return seleccionarTodo;
	}

	public void setSeleccionarTodo(boolean seleccionarTodo) {
		this.seleccionarTodo = seleccionarTodo;
	}

	public String getDescripcionEnpresa() {
		return descripcionEnpresa;
	}

	public void setDescripcionEnpresa(String descripcionEnpresa) {
		this.descripcionEnpresa = descripcionEnpresa;
	}

	public String getDescripcionServicio() {
		return descripcionServicio;
	}

	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public Double getValorAdeudado() {
		return valorAdeudado;
	}

	public void setValorAdeudado(Double valorAdeudado) {
		this.valorAdeudado = valorAdeudado;
	}

	public Double getValorTotalCancelado() {
		return valorTotalCancelado;
	}

	public void setValorTotalCancelado(Double valorTotalCancelado) {
		this.valorTotalCancelado = valorTotalCancelado;
	}

	public String getEtiquetaNombreBeneficiario() {
		return etiquetaNombreBeneficiario;
	}

	public void setEtiquetaNombreBeneficiario(String etiquetaNombreBeneficiario) {
		this.etiquetaNombreBeneficiario = etiquetaNombreBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getControlSesion() {
		this.inicializar();
		return controlSesion;
	}

	public void setControlSesion(String controlSesion) {
		this.controlSesion = controlSesion;
	}

	public String getNombreDeudorGad() {
		return nombreDeudorGad;
	}

	public void setNombreDeudorGad(String nombreDeudorGad) {
		this.nombreDeudorGad = nombreDeudorGad;
	}

	public List<Rubro> getRubrosAdeudadosGad() {
		return rubrosAdeudadosGad;
	}

	public void setRubrosAdeudadosGad(List<Rubro> rubrosAdeudadosGad) {
		this.rubrosAdeudadosGad = rubrosAdeudadosGad;
	}

	public List<Titulo> getTitulosFiltrados() {
		return titulosFiltrados;
	}

	public void setTitulosFiltrados(List<Titulo> titulosFiltrados) {
		this.titulosFiltrados = titulosFiltrados;
	}

	public Titulo getTituloSeleccionado() {
		return tituloSeleccionado;
	}

	public void setTituloSeleccionado(Titulo tituloSeleccionado) {
		this.tituloSeleccionado = tituloSeleccionado;
	}

	public Double getTotalPagoGAD() {
		return totalPagoGAD;
	}

	public void setTotalPagoGAD(Double totalPagoGAD) {
		this.totalPagoGAD = totalPagoGAD;
	}

	public Double getTotalComisionGad() {
		return totalComisionGad;
	}

	public void setTotalComisionGad(Double totalComisionGad) {
		this.totalComisionGad = totalComisionGad;
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println(this.tituloSeleccionado == null ? "No hay titulo seleccionado"
				: "titulo seleccionado: " + this.tituloSeleccionado.getContrapartida());
		if (this.tituloSeleccionado != null) {
			this.totalPagoGAD = this.totalComisionGad + this.tituloSeleccionado.getMonto();
			System.out.println(totalPagoGAD);
			this.totalPagoGAD = (double) Math.round(this.totalPagoGAD * 100d) / 100d;
			System.out.println(totalPagoGAD);
		} else {
			this.totalPagoGAD = 0D;
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println(this.tituloSeleccionado == null ? "No hay titulo seleccionado"
				: "titulo seleccionado: " + this.tituloSeleccionado.getContrapartida());
		if (this.tituloSeleccionado != null) {
			this.totalPagoGAD = this.totalComisionGad + this.tituloSeleccionado.getMonto();
			System.out.println(totalPagoGAD);
			this.totalPagoGAD = (double) Math.round(this.totalPagoGAD * 100d) / 100d;
			System.out.println(totalPagoGAD);
		} else {
			this.totalPagoGAD = 0D;
		}
	}

	public void onRowSelectFacilito(SelectEvent event) {
		System.out.println(this.rubroSeleccionado == null ? "No hay rubro seleccionado"
				: "rubro seleccionado: " + this.rubroSeleccionado.getIDRubro() + " "
						+ this.rubroSeleccionado.getDescripcion() + " Prioridad "
						+ this.rubroSeleccionado.getPrioridad());
		if (this.rubroSeleccionado != null) {// && this.bloqueaAbono
			this.totalPagoGAD = this.rubroSeleccionado.getValorConComision().doubleValue();
			System.out.println(totalPagoGAD);
		} else {
			this.totalPagoGAD = 0D;
		}
	}

	public void onRowUnselectFacilito(UnselectEvent event) {
		System.out.println(this.rubroSeleccionado == null ? "No hay rubro seleccionado"
				: "rubro seleccionado: " + this.rubroSeleccionado.getIDRubro() + " "
						+ this.rubroSeleccionado.getDescripcion() + " Prioridad "
						+ this.rubroSeleccionado.getPrioridad());
		if (this.rubroSeleccionado != null) {// && this.bloqueaAbono
			this.totalPagoGAD = this.rubroSeleccionado.getValorConComision().doubleValue();
			System.out.println(totalPagoGAD);
		} else {
			this.totalPagoGAD = 0D;
		}
	}

	public Long getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(Long cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public String getIdRubroFacilito() {
		return idRubroFacilito;
	}

	public void setIdRubroFacilito(String idRubroFacilito) {
		this.idRubroFacilito = idRubroFacilito;
	}

	public List<RubroFacilito> getRubrosRecargaFacilito() {
		return rubrosRecargaFacilito;
	}

	public void setRubrosRecargaFacilito(List<RubroFacilito> rubrosRecargaFacilito) {
		this.rubrosRecargaFacilito = rubrosRecargaFacilito;
	}

	public List<ListadoOpciones> getListadoValoresRecargas() {
		return listadoValoresRecargas;
	}

	public void setListadoValoresRecargas(List<ListadoOpciones> listadoValoresRecargas) {
		this.listadoValoresRecargas = listadoValoresRecargas;
	}

	public Double getValorRecarga() {
		return valorRecarga;
	}

	public void setValorRecarga(Double valorRecarga) {
		this.valorRecarga = valorRecarga;
	}

	public boolean isConfirmaRecarga() {
		return confirmaRecarga;
	}

	public void setConfirmaRecarga(boolean confirmaRecarga) {
		this.confirmaRecarga = confirmaRecarga;
	}

	public String getReciboFacilito() {
		return reciboFacilito;
	}

	public void setReciboFacilito(String reciboFacilito) {
		this.reciboFacilito = reciboFacilito;
	}

	public String getGrupoFacilito() {
		return grupoFacilito;
	}

	public void setGrupoFacilito(String grupoFacilito) {
		this.grupoFacilito = grupoFacilito;
	}

	public List<ListadoOpciones> getListadoGruposFacilito() {
		return listadoGruposFacilito;
	}

	public void setListadoGruposFacilito(List<ListadoOpciones> listadoGruposFacilito) {
		this.listadoGruposFacilito = listadoGruposFacilito;
	}

	public List<RubroFacilito> getRubrosFacilito() {
		return rubrosFacilito;
	}

	public void setRubrosFacilito(List<RubroFacilito> rubrosFacilito) {
		this.rubrosFacilito = rubrosFacilito;
	}

	public String getNumeroServicio() {
		return numeroServicio;
	}

	public void setNumeroServicio(String numeroServicio) {
		this.numeroServicio = numeroServicio;
	}

	public boolean isBloqueaAbono() {
		return bloqueaAbono;
	}

	public void setBloqueaAbono(boolean bloqueaAbono) {
		this.bloqueaAbono = bloqueaAbono;
	}

	public String getTituloReferenciaRecarga() {
		return tituloReferenciaRecarga;
	}

	public void setTituloReferenciaRecarga(String tituloReferenciaRecarga) {
		this.tituloReferenciaRecarga = tituloReferenciaRecarga;
	}

	public String getTituloReferenciaPago() {
		return tituloReferenciaPago;
	}

	public void setTituloReferenciaPago(String tituloReferenciaPago) {
		this.tituloReferenciaPago = tituloReferenciaPago;
	}

	public List<INTResplyConsultaINTDataConsulta> getRubrosAdeudadosFacilito() {
		return rubrosAdeudadosFacilito;
	}

	public void setRubrosAdeudadosFacilito(List<INTResplyConsultaINTDataConsulta> rubrosAdeudadosFacilito) {
		this.rubrosAdeudadosFacilito = rubrosAdeudadosFacilito;
	}

	public INTResplyConsultaINTDataConsulta getRubroSeleccionado() {
		return rubroSeleccionado;
	}

	public void setRubroSeleccionado(INTResplyConsultaINTDataConsulta rubroSeleccionado) {
		this.rubroSeleccionado = rubroSeleccionado;
	}

	public List<ListadoOpciones> getListadoGruposRecaudacionFacilito() {
		return listadoGruposRecaudacionFacilito;
	}

	public void setListadoGruposRecaudacionFacilito(List<ListadoOpciones> listadoGruposRecaudacionFacilito) {
		this.listadoGruposRecaudacionFacilito = listadoGruposRecaudacionFacilito;
	}

}
