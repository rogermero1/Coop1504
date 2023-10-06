package ec.fin.online15.portal.paginas.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.TiposCertificacion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Certificacion;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.beans.BeanServiciosCertificacion;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosTransaccionCliente;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("certificacionMB")
@SessionScoped
public class BeanCertificacion extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosCertificacion servicioCertificacion;

	@Inject
	private BeanServiciosRegistroUsuario servicioRegistroUsuario;

	@Inject
	private BeanServiciosTransaccionCliente servicioTransaccion;

	@Inject
	private BeanServiciosUsuario serviciosUsuario;

	@Inject
	@Default
	private BeanServicioUsuario servicioUsuario;

	private List<TiposCertificacion> tiposCertificados;
	private Double valor;
	private String codigoSeguridad;
	private Boolean enviarOtp;
	private Integer codigoCliente;
	private Certificacion certificacion;
	private List<ListadoOpciones> listaCuentas;
	private String nombreCertificado;
	private Date fechaHora;

	// SimpleDateFormat formatoFechaHora = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");

	@PostConstruct
	private void init() {
		// this.inicializar();
	}

	public String getInicializacion() {
		this.inicializar();
		return "";
	}

	public void inicializar() {
		this.codigoCliente = this.servicioUsuario.getTwebUsuario().getCodigoCliente();

		this.certificacion = new Certificacion();
		this.certificacion.setCodigoCliente(this.codigoCliente);
		this.certificacion.setCorreoPrincipal(this.servicioUsuario.getTwebUsuario().getCorreo());
		this.certificacion.setCanal(Canal.WEB);

		this.valor = 0D;
		this.codigoSeguridad = "";
		this.enviarOtp = true;
		this.nombreCertificado = "";

		this.tiposCertificados = this.servicioCertificacion.getIServicioCertificacionWs()
				.listaCertificaciones(this.getHttpSession().getId());

		this.listaCuentas = new ArrayList<ListadoOpciones>();
		listaCuentas = this.servicioTransaccion.getIServicioTransaccionClienteWs()
				.listaCuentasCliente(this.getHttpSession().getId(), this.codigoCliente);
	}

	public void onTipoCertificadoChange() {
		System.out.println("SELECCION " + this.certificacion.getTipoCertificacion());
		if ((this.certificacion.getTipoCertificacion() == null ? 0 : this.certificacion.getTipoCertificacion()) > 0) {
			for (TiposCertificacion certificado : this.tiposCertificados) {
				if (certificado.getId() == this.certificacion.getTipoCertificacion()) {
					this.valor = certificado.getValor();
					this.nombreCertificado = certificado.getDescripcion();
					break;
				}
			}
		} else {
			this.valor = 0D;
		}
	}

	public void continuar(ActionEvent evento) {
		if (this.valor == 0D) {
			this.aniadirMensajeError("Aplicación", "Para continuar debe seleccionar un tipo de certificado");
		} else if (this.certificacion.getNumeroCuenta().equals("") || this.certificacion.getNumeroCuenta() == null) {
			this.aniadirMensajeError("Aplicación", "Para continuar debe seleccionar una cuenta");
		} else {
			System.out.println("Antes de enviar OTP...");
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.codigoCliente,
					this.servicioUsuario.getTwebUsuario().getCelular(),
					this.servicioUsuario.getTwebUsuario().getCorreo(), "S", "Certificación");
			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);
			if (error == null || error.equals("")) {
				this.enviarOtp = false;
			} else {
				this.aniadirMensajeError("Aplicación", error);
			}
		}
	}

	public void confirmar() {
		if (this.valor > servicioTransaccion.getIServicioTransaccionClienteWs()
				.saldoCuentaAhorro(this.getHttpSession().getId(), this.certificacion.getNumeroCuenta())) {
			this.aniadirMensajeError("Aplicación", "No cuenta con saldo disponible");
			this.inicializar();
		} else {
			System.out.println("Antes de confirmar => codigo de seguridad");
			Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
					this.getHttpSession().getId(), this.codigoCliente, this.codigoSeguridad, this.codigoSeguridad);
			System.out.println("Depues de confirmar en codigo de seguridad");
			System.out.println("Resultado de confirmacion : " + respuesta);
			if (respuesta == 1) {
				Certificacion resultado = new Certificacion();
				resultado = this.servicioCertificacion.getIServicioCertificacionWs()
						.solicitarCertificacion(this.getHttpSession().getId(), this.certificacion);
				if (resultado.getCodigoError() == null || resultado.getCodigoError().equals("")) {

					this.configuracionesGenerales.mensajeTransaccionExitosa();

					this.fechaHora = new Date();

					List<String> parametros = new ArrayList<String>();
					parametros.add(resultado.getSecuencia().toString());
					parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((this.fechaHora)));
					parametros.add(this.nombreCertificado);
					parametros.add(this.enmascararCadena(this.certificacion.getNumeroCuenta().toString()));
					parametros.add(this.valor.toString());
					System.out.println("Envio de comprobante certificacion: " + this.serviciosUsuario
							.getIServicioUsuarioWs().envioNotificacion(this.getHttpSession().getId(),
									this.codigoCliente, null, this.servicioUsuario.getTwebUsuario().getCorreo(),
									ValoresConstantes.PLANTILLACOMPROBANTECERTIFICACION, parametros));
					this.inicializar();
				} else {
					if (resultado.getCodigoError().contains("52104")) {
						this.configuracionesGenerales.mensajeServicioNoDisponible();
					} else if (resultado.getCodigoError().contains("PENDIENTE")) {
						this.configuracionesGenerales.aniadirMensajeError("Aplicación",
								"Existe una solicitud de certificación pendiente");
					} else {
						this.configuracionesGenerales.mensajeTransaccionError();
					}
					System.out.println("Error al registrar certificacion: " + resultado.getCodigoError());
					// this.configuracionesGenerales.mensajeTransaccionError();
					this.inicializar();
				}

			} else if (respuesta == 0) {
				this.aniadirMensajeError("Aplicación", "Código ingresado no existe.");
				this.inicializar();
			} else if (respuesta == 2) {
				this.aniadirMensajeError("Aplicación", "Tiempo máximo de vigencia de su código de seguridad expiro.");
				this.inicializar();
			}
		}
	}

	public void cancelar() {
		System.out.println("Cancelando certificacion...");
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_USUARIO_WEB,
				"");
	}

	public List<TiposCertificacion> getTiposCertificados() {
		return tiposCertificados;
	}

	public void setTiposCertificados(List<TiposCertificacion> tiposCertificados) {
		this.tiposCertificados = tiposCertificados;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public Boolean getEnviarOtp() {
		return enviarOtp;
	}

	public void setEnviarOtp(Boolean enviarOtp) {
		this.enviarOtp = enviarOtp;
	}

	public List<ListadoOpciones> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<ListadoOpciones> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public Certificacion getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(Certificacion certificacion) {
		this.certificacion = certificacion;
	}

}
