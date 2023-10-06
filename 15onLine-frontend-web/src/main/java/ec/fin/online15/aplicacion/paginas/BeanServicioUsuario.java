package ec.fin.online15.aplicacion.paginas;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutenticacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutenticacion;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.PreguntaSecreta;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcionRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.beans.BeanServiciosOpcionRol;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("servicioUsuarioMB")
@SessionScoped
public class BeanServicioUsuario extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private @QualificadorGuardianAutenticacionSimple IBeanGuardiaAutenticacion beanGuardiaAutenticacion;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosUsuario servicioUsuario;

	@Inject
	private BeanServiciosRegistroUsuario servicioMantenimientoUsuario;

	@Inject
	private BeanServiciosOpcionRol servicioOpcionRol;

	private TwebUsuario twebUsuario;
	private List<TwebUsuarioRol> rolesUsuario;
	private List<TwebOpcion> opcionesUsuario;
	private PreguntaSecreta preguntaSecreta;
	private String respuestaPregunta;
	private boolean ipConocida;
	private String ip;
	private boolean autenticacionCompleta;
	private String otpConfirmacionConeccion;
	private String tipoAutorizacionIp;
	private String descripcionIp;
	private boolean usuarioAdministrador;

	private boolean otpCorreo;

	private String tituloMensajes;

	private boolean ipNoAutorizada;

	private boolean loginAdmin;

	private String roles;

	private String controlBatch;

	private String nombreCliente;

	@PostConstruct
	private void init() {
		ipNoAutorizada = false;
		loginAdmin = false;
		roles = "";
		controlBatch = "";
		this.otpCorreo = false;
		inicilizacion();
	}

	private void inicilizacion() {
		System.out.println("INICIALIZA BEAN");
		tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		this.opcionesUsuario = new ArrayList<TwebOpcion>();
		this.ipConocida = false;
		this.ip = this.getIpLocal2();
		this.tipoAutorizacionIp = "TEMPORAL";
		this.otpConfirmacionConeccion = "";
		this.twebUsuario = new TwebUsuario();
		this.consultaUsuario(this.getUsuarioAutenticado().toString());
		//System.out.println("ENVIAR OTP MAIL"+this.twebUsuario.getEnvia_otp_mail());
		this.otpCorreo = "S".equals(this.twebUsuario.getEnvia_otp_mail()) ? true : false;
		System.out.println("Consulta usuario......" + this.getUsuarioAutenticado().toString());

		this.ipConocida = this.servicioUsuario.getIServicioUsuarioWs().consultaIpConocida(this.getHttpSession().getId(),
				twebUsuario, this.ip);

		System.out.println("USUARIO: " + this.getUsuarioAutenticado().toString() + " CONECTADO DESDE IP: " + this.ip
				+ " autorizado " + (this.ipConocida ? "SI" : "NO"));

		// validacion, si tiene rol 1 0 2 entra directo a la aplicacion
		this.rolesUsuario = this.servicioUsuario.getIServicioUsuarioWs().rolesUsuario(this.getHttpSession().getId(),
				this.twebUsuario.getId());

		roles = "";

		controlBatch = this.servicioUsuario.getIServicioUsuarioWs().consultaEstadoBatch(this.getHttpSession().getId());
		System.out.println("Estado del cierre " + controlBatch);

		this.nombreCliente = this.servicioUsuario.getIServicioUsuarioWs()
				.consultaNombreCliente(this.getHttpSession().getId(), this.twebUsuario.getCodigoCliente());

		Integer conteoRol = 0;
		if (rolesUsuario != null)
			for (TwebUsuarioRol usuarioRol : rolesUsuario) {
				// if (usuarioRol.getRol().getId() == 1
				// || usuarioRol.getRol().getId() == 2) {
				if (usuarioRol.getRol().getId() != 3 && usuarioRol.getRol().getId() != 5) {
					conteoRol += 1;
				}
				List<TwebOpcionRol> opcionRolTemp = this.servicioOpcionRol.getIServicioOpcionRolWs()
						.listaOpcionesRolesPorRol(this.getHttpSession().getId(), usuarioRol.getRol().getId());
				if (opcionRolTemp != null) {
					for (TwebOpcionRol opcionRol : opcionRolTemp) {
						this.opcionesUsuario.add(opcionRol.getOpciones());
					}
				}

				roles = roles + " - " + usuarioRol.getRol().getRol();
			}
		System.out.println("usuario: " + this.getUsuarioAutenticado().toString() + " conectado con roles: " + roles);
		if (conteoRol == 0) {
			this.usuarioAdministrador = false;
			this.autenticacionCompleta = false;
			this.buscaPreguntaSecreta(null);
			System.out.println("Consulta pregunta secreta...");
			System.out.println(this.preguntaSecreta.getPregunta());
			if (this.preguntaSecreta.getIdPregunta() == 0) {
				this.aniadirMensajeError(tituloMensajes, "Problemas con pregunta secreta.");
			} else {
				// si no es una ip frecuente, envia otp al correo
				if (!this.ipConocida) {
					String errorEnvioOtp = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().envioOtp(
							this.getHttpSession().getId(), this.twebUsuario.getCodigoCliente(),
							this.twebUsuario.getCelular(), this.twebUsuario.getCorreo(), "S", "Autorización de PC");
					System.out.println("Resultado de envioOtp por validacion de IP: " + errorEnvioOtp);
				}
			}
		} else {
			// CONTROL para que administrador y operativo solo se conecten desde
			// la coop.
			System.out.println("Usuario administrador/operativo conectado");
			// System.out.println(twebUsuario.getUsuario() + " ipPublica: "
			// + this.getIpPublica() + " ipLocal: " + this.getIpLocal());
			System.out.println(twebUsuario.getUsuario() + " ipLocal: " + this.ip);// this.getIpLocal());
//			if (this.ip.equals("186.3.64.203") || this.ip.equals("186.3.64.204") || this.ip.equals("192.168.60.1")
//					|| this.ip.equals("200.125.222.42") || this.ip.equals("181.198.63.58")
//					|| this.ip.equals("127.0.0.1") || this.ip.contains("200.100.1.") || this.ip.contains("200.100.2.")
//					|| this.ip.contains("200.100.4.") || this.ip.contains("200.100.6.")) {
			// se crea tabla para el registro de estas IPS
			if (this.servicioUsuario.getIServicioUsuarioWs().validaIpAdministrativa(this.getHttpSession().getId(),
					this.ip)) {
				ipNoAutorizada = false;
				this.usuarioAdministrador = true;
				this.servicioUsuario.getIServicioUsuarioWs().registrarConexion(this.getHttpSession().getId(),
						twebUsuario, this.ip, this.descripcionIp, false, this.getUsuarioAutenticado().toString(),
						this.usuarioAdministrador, Canal.WEB);
				this.autenticacionCompleta = true;
				// this.getSession();
				loginAdmin = true;
//				this.configuracionesGenerales
//						.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION, "");
			} else {
				HttpSession lHttpSession = getHttpServletRequest().getSession();
				if (lHttpSession != null)
					lHttpSession.invalidate();
				// this.configuracionesGenerales.redireccionarUrlConstante("/login.jsf",
				// "?faces-redirect=true");
				this.aniadirMensajeError(tituloMensajes, "No conectado desde la Cooperativa");
				ipNoAutorizada = true;
			}
		}
	}

	public void controlNavegacion() throws IOException {
		if (loginAdmin) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					getContextPath() + "/paginas/principales/pag_principal_aplicacion.jsf?faces-redirect=true");
		} else {
			System.out.println("NO ADMIN");
		}
	}

	public TwebUsuario getTwebUsuario() {
		return twebUsuario;
	}

	public void setTwebUsuario(TwebUsuario twebUsuario) {
		this.twebUsuario = twebUsuario;
	}

	public List<TwebUsuarioRol> getRolesUsuario() {
		return rolesUsuario;
	}

	public void setRolesUsuario(List<TwebUsuarioRol> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	public List<TwebOpcion> getOpcionesUsuario() {
		return opcionesUsuario;
	}

	public void setOpcionesUsuario(List<TwebOpcion> opcionesUsuario) {
		this.opcionesUsuario = opcionesUsuario;
	}

	public PreguntaSecreta getPreguntaSecreta() {
		return preguntaSecreta;
	}

	public void setPreguntaSecreta(PreguntaSecreta preguntaSecreta) {
		this.preguntaSecreta = preguntaSecreta;
	}

	public String getRespuestaPregunta() {
		return respuestaPregunta;
	}

	public void setRespuestaPregunta(String respuestaPregunta) {
		this.respuestaPregunta = respuestaPregunta;
	}

	public boolean isIpConocida() {
		return ipConocida;
	}

	public void setIpConocida(boolean ipConocida) {
		this.ipConocida = ipConocida;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean getAutenticacionCompleta() {
		return autenticacionCompleta;
	}

	public void setAutenticacionCompleta(boolean autenticacionCompleta) {
		this.autenticacionCompleta = autenticacionCompleta;
	}

	public void consultaUsuario(String usuario) {
		this.twebUsuario = this.servicioUsuario.getIServicioUsuarioWs()
				.obtenerUsuarioBase(this.getHttpSession().getId(), usuario);
	}

	public String getOtpConfirmacionConeccion() {
		return otpConfirmacionConeccion;
	}

	public void setOtpConfirmacionConeccion(String otpConfirmacionConeccion) {
		this.otpConfirmacionConeccion = otpConfirmacionConeccion;
	}

	public String getTipoAutorizacionIp() {
		return tipoAutorizacionIp;
	}

	public void setTipoAutorizacionIp(String tipoAutorizacionIp) {
		this.tipoAutorizacionIp = tipoAutorizacionIp;
	}

	public boolean isUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public String getDescripcionIp() {
		return descripcionIp;
	}

	public void setDescripcionIp(String descripcionIp) {
		this.descripcionIp = descripcionIp;
	}

	public void buscaPreguntaSecreta(ActionEvent evento) {
		System.out.println("Envia a buscar pregunta secreta : " + this.twebUsuario.getUsuario());
		this.preguntaSecreta = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
				.consultaPreguntaSecreta(this.getHttpSession().getId(), this.twebUsuario.getUsuario());

		System.out.println(this.preguntaSecreta.getPregunta());

		if (this.preguntaSecreta.getIdPregunta() == 0) {
			this.aniadirMensajeError(tituloMensajes, "Pregunta secreta no encontrada.");
		}
	}

	public String validaRespuesta() {
		String resultado = "";
		// 1ero validar respuesta
		if (this.respuestaPregunta.equals(this.preguntaSecreta.getRespuesta())) {
			System.out.println("Respuesta validada OK");
			// 2do revisar otp en caso de ip desconocida
			if (!this.ipConocida) {
				if (this.otpConfirmacionConeccion.equals("")
						|| (this.tipoAutorizacionIp.equals("PERMANENTE") && this.descripcionIp.equals(""))) {
					this.aniadirMensajeError(tituloMensajes,
							"Debe ingresar el código de seguridad "
									+ ((this.tipoAutorizacionIp.equals("PERMANENTE") && this.descripcionIp.equals(""))
											? "y la descripción del computador"
											: ""));
					// return "";
				} else {
					Integer validacionOtp = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
							.confirmacionOtp(this.getHttpSession().getId(), this.twebUsuario.getCodigoCliente(),
									this.otpConfirmacionConeccion, this.otpConfirmacionConeccion);
					if (validacionOtp == 0) {
						this.aniadirMensajeError(tituloMensajes,
								"Los códigos ingresados no corresponden con los enviados");
						// return "";
					} else if (validacionOtp == 2) {
						this.aniadirMensajeError(tituloMensajes,
								"Los códigos ingresados han superado su tiempo de vigencia");
						// return "";
					} else {
						this.servicioUsuario.getIServicioUsuarioWs().registrarConexion(this.getHttpSession().getId(),
								twebUsuario, this.ip, this.descripcionIp,
								(this.tipoAutorizacionIp.equals("TEMPORAL") ? false : true),
								this.getUsuarioAutenticado().toString(), this.usuarioAdministrador, Canal.WEB);
						this.autenticacionCompleta = true;
						// return
						resultado = ConstantesMemoria.getValorParametro(
								NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB, String.class);
					}
				}
			} else {
				this.servicioUsuario.getIServicioUsuarioWs().registrarConexion(this.getHttpSession().getId(),
						twebUsuario, this.ip, this.descripcionIp, false, this.getUsuarioAutenticado().toString(),
						this.usuarioAdministrador, Canal.WEB);
				this.autenticacionCompleta = true;
				// return
				resultado = ConstantesMemoria.getValorParametro(
						NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB, String.class);
			}
		} else {
			String errorRespuesta = this.servicioUsuario.getIServicioUsuarioWs().errorRespuestaSecreta(
					this.getHttpSession().getId(), this.twebUsuario.getUsuario(),
					this.getUsuarioAutenticado().toString());
			this.aniadirMensajeError(tituloMensajes, "Respuesta es INCORRECTA " + errorRespuesta);
			// enviar notificacion
			List<String> parametros = new ArrayList<String>();
			parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((new Date())));
			System.out.println(
					"Envio de notificacion validacion fallida para el usuario : " + this.twebUsuario.getUsuario() + " "
							+ this.servicioUsuario.getIServicioUsuarioWs().envioNotificacion(
									this.getHttpSession().getId(), this.twebUsuario.getCodigoCliente(),
									this.twebUsuario.getCelular(), this.twebUsuario.getCorreo(),
									ValoresConstantes.PLANTILLANOTIFICACIONVALIDACIONFALLIDA, parametros));
			if (!errorRespuesta.equals("")) {// el servicio contestará error cuando se bloquee el usuario por intentos
				beanGuardiaAutenticacion.logout();// agregado nov-2022 SOLUCION la sesion quedaba inciada con usuario
													// bloqueado HREAL
				HttpSession lHttpSession = getHttpServletRequest().getSession();
				if (lHttpSession != null)
					lHttpSession.invalidate();
				PrimeFaces.current().executeScript("PF('dlgBloqueo').show();");
				// this.configuracionesGenerales.regresaLogin();
				// resultado =
				// ConstantesMemoria.getValorParametro(NombresConstantesMemoria.RUTA_LOGIN_APLICACION,String.class);
			}
			// return "";
		}
		if (resultado.equals("")) {
			// PrimeFaces.current().executeScript("window.open('myUrl');");
			PrimeFaces.current().executeScript("PF('dlgCargando').hide();");

		}

		// if (autenticacionCompleta) {
		// this.configuracionesGenerales
		// .redireccionarUrlConstante(
		// NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB,
		// "");
		// }

		return resultado;

		// para la redireccion se cambio este metodo a void, y se modifico el
		// xhtm para cambiar a actionlistener
	}

	public String urlInicio() {
		if (this.usuarioAdministrador)
			return ConstantesMemoria.getValorParametro(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION,
					String.class);
		else
			return ConstantesMemoria.getValorParametro(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB,
					String.class);
	}

	public boolean isIpNoAutorizada() {
		return ipNoAutorizada;
	}

	public void setIpNoAutorizada(boolean ipNoAutorizada) {
		this.ipNoAutorizada = ipNoAutorizada;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getControlBatch() {
		return controlBatch;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTextoOtpMail() {
		return otpCorreo ? "correo electrónico y " : "";
	}

}
