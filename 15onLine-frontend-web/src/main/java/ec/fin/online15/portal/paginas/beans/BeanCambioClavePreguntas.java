package ec.fin.online15.portal.paginas.beans;

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
import ec.fin.online15.backend.librerias.excepciones.ExcepcionEncriptacion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.integracion.beans.BeanServiciosCambioClavePreguntas;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;
import ec.fin.online15.librerias.utilerias.UtilCryptography;
import ec.fin.online15.librerias.utilerias.ValidacionClaves;

@Named("cambioClavePreguntasMB")
@SessionScoped
public class BeanCambioClavePreguntas extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosCambioClavePreguntas servicioCambioClavePreguntas;

	@Inject
	@Default
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanServiciosRegistroUsuario servicioRegistroUsuario;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales servicioMantenimientoParametroGeneral;

	private String controlSesion;
	private String opcion;
	private String tituloMensajes;
	private String claveActual;
	private String claveNueva;
	private String confirmacionClaveNueva;
	private List<TwebRespuestasUsuarios> listaRespuestasUsuario;
	private List<TwebIpsAutorizadas> listaIpsVigentesUsuario;
	private boolean confirmacion;
	private String codigoSeguridad;
	private TwebParametrosGenerales parametroGeneral;
	private TwebUsuario usuario;
	private boolean botonCambioClave;
	private boolean botonCambioRespuestas;
	private boolean botonCambioTelefonoCorreo;
	private boolean botonCambioIps;

	private String correo;
	private String celular;

	@PostConstruct
	private void init() {
		this.inicializar();
	}

	public void inicializar() {
		this.tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);
		this.confirmacion = false;
		this.claveActual = "";
		this.claveNueva = "";
		this.confirmacionClaveNueva = "";
		this.opcion = "";
		this.codigoSeguridad = "";
		this.listaRespuestasUsuario = new ArrayList<TwebRespuestasUsuarios>();
		this.listaRespuestasUsuario = this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas()
				.respuestasVigentesUsuarios(this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario());
		parametroGeneral = new TwebParametrosGenerales();
		List<TwebParametrosGenerales> listaParametrosGenerales = this.servicioMantenimientoParametroGeneral
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(this.getHttpSession().getId());
		if (listaParametrosGenerales != null)
			parametroGeneral = listaParametrosGenerales.get(0);
		else
			this.aniadirMensajeError(tituloMensajes, "Parámetros generales");
		this.usuario = this.servicioUsuario.getTwebUsuario();
		this.listaIpsVigentesUsuario = new ArrayList<TwebIpsAutorizadas>();
		this.listaIpsVigentesUsuario = this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas()
				.ipsVigenteUsuario(this.getHttpSession().getId(), this.usuario);
		this.botonCambioClave = false;
		this.botonCambioRespuestas = false;
		this.botonCambioTelefonoCorreo = false;
		this.botonCambioIps = false;

		this.correo = this.usuario.getCorreo();
		this.celular = this.usuario.getCelular();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClaveActual() {
		return claveActual;
	}

	public void setClaveActual(String claveActual) {
		this.claveActual = claveActual;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getConfirmacionClaveNueva() {
		return confirmacionClaveNueva;
	}

	public void setConfirmacionClaveNueva(String confirmacionClaveNueva) {
		this.confirmacionClaveNueva = confirmacionClaveNueva;
	}

	public List<TwebRespuestasUsuarios> getListaRespuestasUsuario() {
		return listaRespuestasUsuario;
	}

	public void setListaRespuestasUsuario(List<TwebRespuestasUsuarios> listaRespuestasUsuario) {
		this.listaRespuestasUsuario = listaRespuestasUsuario;
	}

	public boolean isConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getControlSesion() {
		this.inicializar();
		return controlSesion;
	}

	public void setControlSesion(String controlSesion) {
		this.controlSesion = controlSesion;
	}

	// Comentado para no exponer directamente correo/telefono, por incidente
	// presentado
	// Se permitia escribir un telefono/correo y recibir otps sin validar el cambio
	// debido a que el objeto USUARIO está en un bean de sesion
//	public TwebUsuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(TwebUsuario usuario) {
//		this.usuario = usuario;
//	}

	public List<TwebIpsAutorizadas> getListaIpsVigentesUsuario() {
		return listaIpsVigentesUsuario;
	}

	public void setListaIpsVigentesUsuario(List<TwebIpsAutorizadas> listaIpsVigentesUsuario) {
		this.listaIpsVigentesUsuario = listaIpsVigentesUsuario;
	}

	public boolean isBotonCambioClave() {
		return botonCambioClave;
	}

	public void setBotonCambioClave(boolean botonCambioClave) {
		this.botonCambioClave = botonCambioClave;
	}

	public boolean isBotonCambioRespuestas() {
		return botonCambioRespuestas;
	}

	public void setBotonCambioRespuestas(boolean botonCambioRespuestas) {
		this.botonCambioRespuestas = botonCambioRespuestas;
	}

	public boolean isBotonCambioTelefonoCorreo() {
		return botonCambioTelefonoCorreo;
	}

	public void setBotonCambioTelefonoCorreo(boolean botonCambioTelefonoCorreo) {
		this.botonCambioTelefonoCorreo = botonCambioTelefonoCorreo;
	}

	public boolean isBotonCambioIps() {
		return botonCambioIps;
	}

	public void setBotonCambioIps(boolean botonCambioIps) {
		this.botonCambioIps = botonCambioIps;
	}

	public String getMensajeFormatoClave() {
		return ValidacionClaves.MensajeFormatoClave(this.parametroGeneral.getLongitudMinimaClave(),
				this.parametroGeneral.getMinimoLetrasClave(), this.parametroGeneral.getMinimoMayusculasClave(),
				this.parametroGeneral.getMinimoNumerosClave(),
				this.parametroGeneral.getMinimoCaracteresEspecialesClave());
	}

	public void cambiarClave(ActionEvent evento) {
		this.confirmacion = false;
		this.opcion = "";
		if (this.claveActual.equals("") || this.claveNueva.equals("") || this.confirmacionClaveNueva.equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Debe llenar los campos de la clave");
		} else {
			if (this.claveNueva.equals(this.confirmacionClaveNueva)) {
				try {
					if (!this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas().compruebaClaveUsuario(
							this.getHttpSession().getId(), this.getUsuarioAutenticado().toString(),
							UtilCryptography.encriptarMd5(this.claveActual))) {
						this.aniadirMensajeError(this.tituloMensajes, "Clave incorrecta");
					} else {
						String validacionClave = ValidacionClaves.ValidaClave("La clave", this.claveNueva,
								this.parametroGeneral.getLongitudMinimaClave(),
								this.parametroGeneral.getMinimoLetrasClave(),
								this.parametroGeneral.getMinimoMayusculasClave(),
								this.parametroGeneral.getMinimoNumerosClave(),
								this.parametroGeneral.getMinimoCaracteresEspecialesClave());
						if (!validacionClave.equals("")) {
							this.aniadirMensajeError(tituloMensajes, validacionClave);
						} else {
							String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
									this.getHttpSession().getId(),
									this.servicioUsuario.getTwebUsuario().getCodigoCliente(),
									this.servicioUsuario.getTwebUsuario().getCelular(),
									this.servicioUsuario.getTwebUsuario().getCorreo(), "S", "Cambio de clave");
							if (error == null) {
								this.opcion = "CLAVE";
								this.confirmacion = true;
								this.botonCambioClave = true;
							} else {
								this.aniadirMensajeError(this.tituloMensajes, error);
							}
						}
					}
				} catch (ExcepcionEncriptacion e) {
					e.printStackTrace();
				}
			} else {
				this.aniadirMensajeError(this.tituloMensajes, "Los campos de clave nueva no coinciden");
			}
		}
	}

	public void cambiarRespuestas(ActionEvent evento) {
		this.confirmacion = false;
		this.opcion = "";
		Integer control = 0;
		for (TwebRespuestasUsuarios respuesta : this.listaRespuestasUsuario) {
			if (respuesta.getDescripcion().equals("")) {
				control++;
			}
		}

		if (control > 0) {
			this.aniadirMensajeError(this.tituloMensajes, "Debe contestar todas las preguntas");
		} else {
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario().getCodigoCliente(),
					this.servicioUsuario.getTwebUsuario().getCelular(),
					this.servicioUsuario.getTwebUsuario().getCorreo(), "S", "Cambio de Respuestas");
			if (error == null) {
				this.opcion = "RESPUESTAS";
				this.confirmacion = true;
				this.botonCambioRespuestas = true;
			} else {
				this.aniadirMensajeError(this.tituloMensajes, error);
			}
		}
	}

	public void cambiarTelefonoCorreo(ActionEvent evento) {
		this.confirmacion = false;
		this.opcion = "";
		// if (this.usuario.getCelular().equals("") ||
		// this.usuario.getCorreo().equals("")) {
		if (this.celular.equals("") || this.correo.equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Debe llenar los campos para teléfono y correo");
		} else {
			// MAYER
			Integer compruebaCelular = servicioRegistroUsuario.getIServicioRegistroUsuarioWs()
					.compruebaExistenciaCelular(this.getHttpSession().getId(), this.usuario.getCodigoCliente(),
							this.celular, this.usuario.getUsuario());// this.usuario.getCelular()
			Integer compruebaCorreo = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().compruebaExistenciaCorreo(
					this.getHttpSession().getId(), this.usuario.getCodigoCliente(), this.correo,
					this.usuario.getUsuario());// this.usuario.getCorreo()
			if (compruebaCelular > 0) {
				this.aniadirMensajeError(this.tituloMensajes, "Ya existe un usuario con ese celular");
			} else if (compruebaCelular < 0) {
				this.aniadirMensajeError(this.tituloMensajes, "Celular no registrado");
			} else if (compruebaCorreo > 0) {
				this.aniadirMensajeError(this.tituloMensajes, "Ya existe un usuario con ese correo");
			} else if (compruebaCorreo < 0) {
				this.aniadirMensajeError(this.tituloMensajes, "Correo no registrado");
			} else {
				String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario().getCodigoCliente(),
						this.celular, // this.servicioUsuario.getTwebUsuario().getCelular()
						this.correo, "S", "Cambio de telefono/correo");// this.servicioUsuario.getTwebUsuario().getCorreo()
				if (error == null) {
					this.opcion = "TELEFONOCORREO";
					this.confirmacion = true;
					this.botonCambioTelefonoCorreo = true;
				} else {
					this.aniadirMensajeError(this.tituloMensajes, error);
				}
			}
		}
	}

	public void cambiarIpsAutorizadas(ActionEvent evento) {
		this.confirmacion = false;
		this.opcion = "";
		if (!this.listaIpsVigentesUsuario.isEmpty()) {
			String error = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario().getCodigoCliente(),
					this.servicioUsuario.getTwebUsuario().getCelular(),
					this.servicioUsuario.getTwebUsuario().getCorreo(), "S", "Cambio de computador");
			if (error == null) {
				this.opcion = "IPS";
				this.confirmacion = true;
				this.botonCambioIps = true;
			} else {
				this.aniadirMensajeError(this.tituloMensajes, error);
			}
		}
	}

	public void completarCambios(ActionEvent evento) {
		if (this.codigoSeguridad == null || this.codigoSeguridad.equals("")) {
			this.aniadirMensajeError(this.tituloMensajes, "Código de seguridad no puede estar vacío.");
		} else {
			Integer respuesta = servicioRegistroUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
					this.getHttpSession().getId(), this.servicioUsuario.getTwebUsuario().getCodigoCliente(),
					this.codigoSeguridad, this.codigoSeguridad);
			if (respuesta == 0) {
				this.aniadirMensajeError(this.tituloMensajes, "Código ingresado no existe.");
			} else if (respuesta == 2) {
				this.aniadirMensajeError(this.tituloMensajes,
						"Tiempo máximo de vigencia de su código de seguridad expiro.");
			} else if (respuesta == 1) {
				if (this.opcion.equals("RESPUESTAS")) {
					List<TwebRespuestasUsuarios> listaTemp = new ArrayList<TwebRespuestasUsuarios>();
					for (TwebRespuestasUsuarios res : this.listaRespuestasUsuario) {
						res.setActualizadoPor(this.getUsuarioAutenticado().toString());
						res.setFechaActualizacion(new Date());
						listaTemp.add(res);
					}
					if (!listaTemp.isEmpty()) {
						if (this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas()
								.actualizarRespuestasUsuario(this.getHttpSession().getId(), listaTemp) == 1) {
							this.inicializar();
							this.configuracionesGenerales.mensajeTransaccionExitosa();
						} else {
							this.configuracionesGenerales.mensajeTransaccionError();
						}
					}
				} else if (this.opcion.equals("CLAVE")) {
					try {
						Integer resultadoCambioClave = this.servicioCambioClavePreguntas
								.getIServicioCambioClavePreguntas().cambioClave(this.getHttpSession().getId(),
										this.servicioUsuario.getTwebUsuario(),
										UtilCryptography.encriptarMd5(claveNueva));
						if (resultadoCambioClave == 1) {
							this.inicializar();
							this.configuracionesGenerales.mensajeTransaccionExitosa();
						} else if (resultadoCambioClave == 2) {
							this.aniadirMensajeError(this.tituloMensajes,
									"Esta clave ya fué utilizada con anterioridad");
						} else {
							this.configuracionesGenerales.mensajeTransaccionError();
						}
					} catch (ExcepcionEncriptacion e) {
						e.printStackTrace();
					}
				} else if (this.opcion.equals("TELEFONOCORREO")) {
					// aplica el cambio del telefono y correo ingresado en la pantalla
					this.usuario.setCelular(this.celular);
					this.usuario.setCorreo(this.correo);
					if (this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas()
							.cambioTelefonoCorreo(this.getHttpSession().getId(), this.usuario) == 1) {
						this.inicializar();
						this.configuracionesGenerales.mensajeTransaccionExitosa();
					} else {
						this.configuracionesGenerales.mensajeTransaccionError();
					}
				} else if (this.opcion.equals("IPS")) {
					List<TwebIpsAutorizadas> listaTemp = new ArrayList<TwebIpsAutorizadas>();
					for (TwebIpsAutorizadas res : this.listaIpsVigentesUsuario) {
						res.setActualizadoPor(this.getUsuarioAutenticado().toString());
						res.setFechaActualizacion(new Date());
						listaTemp.add(res);
					}
					if (!listaTemp.isEmpty()) {
						if (this.servicioCambioClavePreguntas.getIServicioCambioClavePreguntas()
								.actualizarIpsVigentes(this.getHttpSession().getId(), listaTemp) == 1) {
							this.inicializar();
							this.configuracionesGenerales.mensajeTransaccionExitosa();
						} else {
							this.configuracionesGenerales.mensajeTransaccionError();
						}
					}
				}
			}
		}
	}
}
