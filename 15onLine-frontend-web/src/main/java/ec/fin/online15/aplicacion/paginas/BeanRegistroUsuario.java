package ec.fin.online15.aplicacion.paginas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.PreguntaSecreta;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionEncriptacion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebBancoPregunta;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.integracion.beans.BeanServiciosConvenio;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoBancoPreguntas;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.integracion.beans.BeanServiciosUsuario;
import ec.fin.online15.integracion.dto.PreguntasRespuestas;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;
import ec.fin.online15.librerias.utilerias.UtilCryptography;
import ec.fin.online15.librerias.utilerias.ValidacionClaves;

@Named("registroUsuarioMB")
@SessionScoped
public class BeanRegistroUsuario extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosRegistroUsuario servicioMantenimientoUsuario;

	@Inject
	private BeanServiciosUsuario servicioUsuario;

	@Inject
	private BeanServiciosConvenio servicioConvenio;

	@Inject
	private BeanServiciosMantenimientoBancoPreguntas servicioMantenimientoBancoPreguntas;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales servicioMantenimientoParametroGeneral;

	private TwebUsuario usuario;
	private TwebConvenios convenioWebCliente;
	private List<PreguntasRespuestas> listaPreguntasRespuestas;
	private List<TwebBancoPregunta> listaBancoPreguntas;
	private String cedula;
	private String convenioWeb;
	private String claveTemp;
	private String claveTemp2;
	private String nombreCliente;
	private List<String> listaImagen;
	private String otpCorreo;
	private String otpCelular;
	private String controlSesion;
	private Boolean aceptarCondiciones;
	private Boolean segundaValidacion;
	private List<Integer> listaPreguntasSegundaValidacion;

	private String correo;
	private String celular;

	private String cuenta;
	private Integer anioNacimiento;
	private Date fechaUltimaTransaccion;
	private Boolean tieneCredito;
	private Boolean tieneInversion;
	private Boolean recibeTransferencia;

	private PreguntaSecreta preguntaSecreta;
	private String respuestaDesbloqueoOlvido;
	private String otpDesbloqueoOlvido;

	private String tituloMensajes;
	private boolean contestaPreguntaSecreta;
	private TwebParametrosGenerales parametroGeneral;

	@PostConstruct
	private void init() {
		System.out.println(
				/* "IP PUBLICA: " + this.getIpPublica() + */" IP LOCAL INICIO BEAN REGISTRO: " + this.getIpLocal());
		inicilizacion();
	}

	private void inicilizacion() {
		this.cedula = null;
		this.convenioWeb = null;
		this.correo = null;
		this.celular = null;
		this.cuenta = null;
		this.anioNacimiento = null;
		this.fechaUltimaTransaccion = null;
		this.tieneCredito = false;
		this.tieneInversion = false;
		this.recibeTransferencia = false;
		this.contestaPreguntaSecreta = false;
		this.aceptarCondiciones = false;
		this.segundaValidacion = false;

		// System.out.println("CONTROOOOOOOOOOOOOOOOL" + segundaValidacion);
		usuario = new TwebUsuario();
		convenioWebCliente = new TwebConvenios();
		// inicializa lista de imagenes
		listaImagen = new ArrayList<String>();
		for (int i = 1; i <= 15; i++) {
			listaImagen.add(
					ConstantesMemoria.getValorParametro(NombresConstantesMemoria.RUTA_IMAGEN_REGISTRO, String.class) + i
							+ ".png");
		}

		// inicializa lista de preguntas
		listaPreguntasRespuestas = new ArrayList<PreguntasRespuestas>();

		listaBancoPreguntas = new ArrayList<TwebBancoPregunta>();
		listaBancoPreguntas = servicioMantenimientoBancoPreguntas.getIServicioBancoPreguntasWs()
				.listaPreguntasRegistro(this.getHttpSession().getId());
		for (TwebBancoPregunta lista : listaBancoPreguntas) {
			listaPreguntasRespuestas.add(new PreguntasRespuestas(lista.getId(), lista.getDescripcion(), null));
		}

		tituloMensajes = configuracionesGenerales
				.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES);

		parametroGeneral = new TwebParametrosGenerales();
		List<TwebParametrosGenerales> listaParametrosGenerales = this.servicioMantenimientoParametroGeneral
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(this.getHttpSession().getId());
		if (listaParametrosGenerales != null)
			parametroGeneral = listaParametrosGenerales.get(0);
		else
			this.aniadirMensajeError(tituloMensajes, "Parámetros generales");
	}

	public String getControlSesion() {
		// System.out.println("PRUEBAAAAAAAAAAA");
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().getSessionMap()
		// .remove("registroUsuarioMB");
		HttpSession lHttpSession = getHttpServletRequest().getSession();
		if (lHttpSession != null)
			lHttpSession.invalidate();
		this.cedula = this.convenioWeb = this.correo = this.celular = null;
		this.aceptarCondiciones = false;
		this.segundaValidacion = false;
		// this.segundaValidacion = false;
		System.out.println("Sesion anulada en el bean RegistroUsuario");
		return controlSesion;
	}

	public void setControlSesion(String controlSesion) {
		this.controlSesion = controlSesion;
	}

	public String getControlSesion2() {
		System.out.println("Control Sesion opc 2 RegistroUsuario");
		if (this.convenioWebCliente.getConvenioWeb() != null) {
			if (convenioWebCliente.getConvenioWeb()
					.equals(this.parametroGeneral.getIntentosFallidosParaBloquear().toString())) {
				this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_LOGIN_APLICACION,
						"");
			}
		}
		return controlSesion;
	}

	public void nada() {
		System.out.println("Acepta Condiciones  " + this.aceptarCondiciones);
	}

	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getConvenioWeb() {
		return convenioWeb;
	}

	public void setConvenioWeb(String convenioWeb) {
		this.convenioWeb = convenioWeb;
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

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Integer getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(Integer anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public Boolean getTieneCredito() {
		return tieneCredito;
	}

	public void setTieneCredito(Boolean tieneCredito) {
		this.tieneCredito = tieneCredito;
	}

	public Boolean getTieneInversion() {
		return tieneInversion;
	}

	public void setTieneInversion(Boolean tieneInversion) {
		this.tieneInversion = tieneInversion;
	}

	public Boolean getRecibeTransferencia() {
		return recibeTransferencia;
	}

	public void setRecibeTransferencia(Boolean recibeTransferencia) {
		this.recibeTransferencia = recibeTransferencia;
	}

	public List<Integer> getListaPreguntasSegundaValidacion() {
		return listaPreguntasSegundaValidacion;
	}

	public void setListaPreguntasSegundaValidacion(List<Integer> listaPreguntasSegundaValidacion) {
		this.listaPreguntasSegundaValidacion = listaPreguntasSegundaValidacion;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public Boolean getAceptarCondiciones() {
		return aceptarCondiciones;
	}

	public void setAceptarCondiciones(Boolean aceptarCondiciones) {
		this.aceptarCondiciones = aceptarCondiciones;
	}

	public Date getFechaUltimaTransaccion() {
		return fechaUltimaTransaccion;
	}

	public void setFechaUltimaTransaccion(Date fechaUltimaTransaccion) {
		this.fechaUltimaTransaccion = fechaUltimaTransaccion;
	}

	public Boolean getSegundaValidacion() {
		// System.out.println("LEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE "
		// + segundaValidacion);
		return segundaValidacion;
	}

	public void setSegundaValidacion(Boolean segundaValidacion) {
		this.segundaValidacion = segundaValidacion;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getClaveTemp() {
		return claveTemp;
	}

	public void setClaveTemp(String claveTemp) {
		this.claveTemp = claveTemp;
	}

	public String getClaveTemp2() {
		return claveTemp2;
	}

	public void setClaveTemp2(String claveTemp2) {
		this.claveTemp2 = claveTemp2;
	}

	public List<String> getListaImagen() {
		return listaImagen;
	}

	public void setListaImagen(List<String> lista_imagen) {
		this.listaImagen = lista_imagen;
	}

	public String getOtpCorreo() {
		return otpCorreo;
	}

	public void setOtpCorreo(String otpCorreo) {
		this.otpCorreo = otpCorreo;
	}

	public String getOtpCelular() {
		return otpCelular;
	}

	public void setOtpCelular(String otpCelular) {
		this.otpCelular = otpCelular;
	}

	public List<PreguntasRespuestas> getListaPreguntasRespuestas() {
		return listaPreguntasRespuestas;
	}

	public void setListaPreguntasRespuestas(List<PreguntasRespuestas> listaPreguntasRespuestas) {
		this.listaPreguntasRespuestas = listaPreguntasRespuestas;
	}

	public PreguntaSecreta getPreguntaSecreta() {
		return preguntaSecreta;
	}

	public void setPreguntaSecreta(PreguntaSecreta preguntaSecreta) {
		this.preguntaSecreta = preguntaSecreta;
	}

	public String getRespuestaDesbloqueoOlvido() {
		return respuestaDesbloqueoOlvido;
	}

	public void setRespuestaDesbloqueoOlvido(String respuestaDesbloqueoOlvido) {
		this.respuestaDesbloqueoOlvido = respuestaDesbloqueoOlvido;
	}

	public String getOtpDesbloqueoOlvido() {
		return otpDesbloqueoOlvido;
	}

	public void setOtpDesbloqueoOlvido(String otpDesbloqueoOlvido) {
		this.otpDesbloqueoOlvido = otpDesbloqueoOlvido;
	}

	public boolean isContestaPreguntaSecreta() {
		return contestaPreguntaSecreta;
	}

	public void setContestaPreguntaSecreta(boolean contestaPreguntaSecreta) {
		this.contestaPreguntaSecreta = contestaPreguntaSecreta;
	}

	public TwebParametrosGenerales getParametroGeneral() {
		return parametroGeneral;
	}

	public void setParametroGeneral(TwebParametrosGenerales parametroGeneral) {
		this.parametroGeneral = parametroGeneral;
	}

	public String getMensajeFormatoClave() {
		return ValidacionClaves.MensajeFormatoClave(this.parametroGeneral.getLongitudMinimaClave(),
				this.parametroGeneral.getMinimoLetrasClave(), this.parametroGeneral.getMinimoMayusculasClave(),
				this.parametroGeneral.getMinimoNumerosClave(),
				this.parametroGeneral.getMinimoCaracteresEspecialesClave());
	}

	public void validacionConvenio(ActionEvent evento) {
		if (!this.segundaValidacion) {
			System.out.println("Validando convenio OPC 1 ");
			System.out.println("Cedula: " + this.cedula + "\nCorreo: " + this.correo + "\nCelular: " + this.celular);

			this.convenioWebCliente = this.servicioConvenio.getIServicioConvenioWs()
					.validaDatosCliente(this.getHttpSession().getId(), this.cedula, this.correo, this.celular);

			// this.convenioWebCliente = this.servicioConvenio
			// .getIServicioConvenioWs().consultaConvenioWebCliente(
			// this.getHttpSession().getId(), this.cedula,
			// this.convenioWeb);

			System.out.println("Resultado Validacion ");
			System.out.println("CodigoCliente: " + this.convenioWebCliente.getCodigoCliente() + "\nNombre: "
					+ this.convenioWebCliente.getNombreCliente() + "\nControl: "
					+ this.convenioWebCliente.getConvenioWeb());

			if (this.convenioWebCliente.getCodigoCliente() == 0) {
				this.convenioWeb = null;

				String[] parts = this.convenioWebCliente.getConvenioWeb().split(";");
				String existeCliente = parts[0]; // existe cliente
				String tieneUsuario = parts[1]; // tiene usuario
				String intentosRegistro = parts[2]; // cantidad de intentos

				if (existeCliente.equals("N")) {
					this.aniadirMensajeError(tituloMensajes, "No se encuentra cliente");
				} else {
					if (intentosRegistro.equals(this.parametroGeneral.getIntentosFallidosParaBloquear().toString())) {
						this.aniadirMensajeError(tituloMensajes, "Cliente tiene bloqueo para registro");

						PrimeFaces.current().executeScript("PF('dlgBloqueo').show();");

					} else if (tieneUsuario.equals("S")) {
						this.aniadirMensajeError(tituloMensajes, "Cliente tiene usuario activo");
					} else {
						this.aniadirMensajeError(tituloMensajes, "Credenciales Inválidas");

						PrimeFaces.current().executeScript("PF('dlgConfirma').show();");

					}
				}

			} else {
				String[] parts = this.convenioWebCliente.getConvenioWeb().split(";");
				String intentosRegistro = parts[2]; // cantidad de intentos
				if (intentosRegistro.equals(this.parametroGeneral.getIntentosFallidosParaBloquear().toString())) {
					this.aniadirMensajeError(tituloMensajes, "Cliente tiene bloqueo para registro");

					PrimeFaces.current().executeScript("PF('dlgBloqueo').show();");

				} else {
					this.usuario.setCodigoCliente(this.convenioWebCliente.getCodigoCliente());
					this.usuario.setConvenioWeb("000000");
					this.convenioWeb = "000000";// control para el filtro de
												// registro
					this.usuario.setCorreo(correo);
					this.usuario.setCelular(celular);
					this.nombreCliente = this.convenioWebCliente.getNombreCliente();
					this.configuracionesGenerales
							.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO, "2.jsf");
				}
			}
		} else {
			System.out.println("Validando convenio OPC 2 " + this.segundaValidacion);

			this.convenioWebCliente = this.servicioConvenio.getIServicioConvenioWs().validaIdentidadCliente(
					this.getHttpSession().getId(), this.getIpLocal(), this.cedula, this.cuenta, this.anioNacimiento,
					this.fechaUltimaTransaccion, this.tieneCredito, this.tieneInversion, this.recibeTransferencia,
					this.listaPreguntasSegundaValidacion, this.parametroGeneral.getIntentosFallidosParaBloquear());

			if (this.convenioWebCliente != null) {
				if (this.convenioWebCliente.getCodigoCliente() == 0) {

					this.aniadirMensajeError(tituloMensajes,
							"La validación no ha sido exitosa (intento " + convenioWebCliente.getConvenioWeb() + "/"
									+ this.parametroGeneral.getIntentosFallidosParaBloquear() + ")");

					if (convenioWebCliente.getConvenioWeb()
							.equals(this.parametroGeneral.getIntentosFallidosParaBloquear().toString())) {
						// control bloqueo
						// anular sesion
						// this.convenioWeb = null;

						PrimeFaces.current().executeScript("PF('dlgBloqueo').show();");

					}
				} else {
					this.usuario.setCodigoCliente(this.convenioWebCliente.getCodigoCliente());
					this.usuario.setConvenioWeb("000000");
					// this.usuario.setCorreo(correo);
					// this.usuario.setCelular(celular);
					this.nombreCliente = this.convenioWebCliente.getNombreCliente();

					this.configuracionesGenerales
							.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO, "2.jsf");
				}
			} else {
				this.convenioWeb = null;
				this.aniadirMensajeError(tituloMensajes, "Error al procesar transacción");
			}
		}
	}

	public void aceptarSegundaValidacion(ActionEvent evento) {
		this.segundaValidacion = true;

		// List<Integer> listaPreguntas = new
		// ArrayList<Integer>(Arrays.asList(1,
		// 2, 3, 4, 5));

		this.listaPreguntasSegundaValidacion = new ArrayList<Integer>();
		Random random = new Random();

		while (this.listaPreguntasSegundaValidacion.size() < 3) {
			Integer aleatorio = random.nextInt(6);
			if (aleatorio >= 1 && aleatorio <= 5) {
				if (!this.listaPreguntasSegundaValidacion.contains(aleatorio)) {
					this.listaPreguntasSegundaValidacion.add(aleatorio);
				}
			}
		}
		System.out.println("Preguntas seleccionadas para segunda validacion " + this.listaPreguntasSegundaValidacion);
		System.out.println("Acepta proceso segunda validacion");

		this.convenioWeb = "000000";// control para el filtro de registro

		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO,
				"0.jsf");
	}

	public boolean mostrarPregunta(Integer i) {
		// System.out.println("pregunta por " + i + " "
		// + this.listaPreguntasSegundaValidacion);
		return this.listaPreguntasSegundaValidacion == null ? false : this.listaPreguntasSegundaValidacion.contains(i);

	}

	public void validacionImagen(ActionEvent evento) {
		System.out.println("Validando imagenes:");
		System.out.println("Cliente:" + this.nombreCliente + "\nImagen: " + this.usuario.getImagen() + "\nDesImagen:"
				+ this.usuario.getDescripcionImagen());
		if (this.usuario.getCodigoCliente() == null) {
			this.aniadirMensajeError(tituloMensajes, "No se ha validado la credencial");
		} else if (this.usuario.getConvenioWeb() == null) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la credencial");
		} else if (this.usuario.getImagen().isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha seleccionado la imágen");
		} else if (this.usuario.getDescripcionImagen().isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha escrito una descripción para la imágen");
		} else {
			this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO,
					"3.jsf");
		}
	}

	public void validacionPreguntas(ActionEvent evento) {
		System.out.println("Validando preguntas:");
		System.out.println("Cliente: " + this.nombreCliente);
		for (PreguntasRespuestas preguntaRespuesta : this.listaPreguntasRespuestas) {
			System.out.println("idPregunta:" + preguntaRespuesta.getIdPregunta() + " respuesta:"
					+ preguntaRespuesta.getRespuesta());
			if (preguntaRespuesta.getRespuesta().isEmpty()) {
				this.aniadirMensajeError(tituloMensajes, "No se han completado las respuestas");
				return;
			}
		}
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO,
				"4.jsf");
	}

	public void validacionUsuario(ActionEvent evento) {
		System.out.println("Validando datos de usuario");

		System.out.println("Cliente: " + this.usuario.getCodigoCliente() + " " + this.nombreCliente
				+ "\nComprueba existencia de usuario: " + this.usuario.getUsuario() + " Resultado: "
				+ +this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
						.compruebaExistenciaUsuario(this.getHttpSession().getId(), this.usuario.getUsuario()));
		System.out.println("Usuario:" + this.usuario.getUsuario() + "\nCorreo:" + this.usuario.getCorreo()
				+ "\nCelular:" + this.usuario.getCelular() + "\n Clave:" + this.claveTemp);
		if (this.usuario.getUsuario().isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el usuario");
		} else if (this.usuario.getCorreo().isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el correo");
		} else if (this.usuario.getCelular().isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado el celular");
		} else if (this.claveTemp.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la contraseña");
		} else if (this.claveTemp2.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la confirmación de contraseña");
		} else if (this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
				.compruebaExistenciaUsuario(this.getHttpSession().getId(), this.usuario.getUsuario()) > 0) {
			this.aniadirMensajeError(tituloMensajes, "Ya existe un usuario con ese nombre");
		} else if (!this.claveTemp.equals(this.claveTemp2)) {
			this.aniadirMensajeError(tituloMensajes, "Las contraseñas no coinciden");
		} else {
			// MAYER
			// validaciones para correo celular
			Integer compruebaCelular = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
					.compruebaExistenciaCelular(this.getHttpSession().getId(), this.usuario.getCodigoCliente(),
							this.usuario.getCelular(), this.usuario.getUsuario());

			Integer compruebaCorreo = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
					.compruebaExistenciaCorreo(this.getHttpSession().getId(), this.usuario.getCodigoCliente(),
							this.usuario.getCorreo(), this.usuario.getUsuario());
			if (compruebaCelular > 0) {
				this.aniadirMensajeError(tituloMensajes, "Ya existe un usuario con ese celular");
			} else if (compruebaCelular < 0) {
				this.aniadirMensajeError(tituloMensajes, "Celular no registrado");
			} else if (compruebaCorreo > 0) {
				this.aniadirMensajeError(tituloMensajes, "Ya existe un usuario con ese correo");
			} else if (compruebaCorreo < 0) {
				this.aniadirMensajeError(tituloMensajes, "Correo no registrado");
			} else if (!ValidacionClaves.ValidaClave("La clave", this.claveTemp,
					this.parametroGeneral.getLongitudMinimaClave(), this.parametroGeneral.getMinimoLetrasClave(),
					this.parametroGeneral.getMinimoMayusculasClave(), this.parametroGeneral.getMinimoNumerosClave(),
					this.parametroGeneral.getMinimoCaracteresEspecialesClave()).equals("")) {
				this.aniadirMensajeError(tituloMensajes, ValidacionClaves.ValidaClave("La clave", this.claveTemp,
						this.parametroGeneral.getLongitudMinimaClave(), this.parametroGeneral.getMinimoLetrasClave(),
						this.parametroGeneral.getMinimoMayusculasClave(), this.parametroGeneral.getMinimoNumerosClave(),
						this.parametroGeneral.getMinimoCaracteresEspecialesClave()));
			} else if (!ValidacionClaves.ValidaClave("El usuario", this.usuario.getUsuario(),
					this.parametroGeneral.getLongitudMinimaClave(), this.parametroGeneral.getMinimoLetrasClave(),
					this.parametroGeneral.getMinimoMayusculasClave(), this.parametroGeneral.getMinimoNumerosClave(),
					this.parametroGeneral.getMinimoCaracteresEspecialesClave()).equals("")) {
				this.aniadirMensajeError(tituloMensajes, ValidacionClaves.ValidaClave("El usuario",
						this.usuario.getUsuario(), this.parametroGeneral.getLongitudMinimaClave(),
						this.parametroGeneral.getMinimoLetrasClave(), this.parametroGeneral.getMinimoMayusculasClave(),
						this.parametroGeneral.getMinimoNumerosClave(),
						this.parametroGeneral.getMinimoCaracteresEspecialesClave()));
			} else {
				// invocacion para envio de otp
				this.usuario.setObservacion("REGISTRO DE USUARIO");

				String errorEnvioOtp = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.usuario.getCodigoCliente(), this.usuario.getCelular(),
						this.usuario.getCorreo(), "N", "Registro 15onLine");
				System.out.println("Resultado de envioOtp: " + errorEnvioOtp);

				if (errorEnvioOtp == null) {
					this.configuracionesGenerales
							.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_REGISTRO, "5.jsf");
				} else {
					System.out.println("Error en envioOtp: " + errorEnvioOtp);
					this.configuracionesGenerales.mensajeTransaccionError();
				}
			}
		}
	}

	public void creacionUsuario(ActionEvent evento) {
		System.out.println("Creacion de usuario:");
		System.out.println("Cliente: " + this.nombreCliente + "\notpCorreo: " + this.otpCorreo + "\notpCelular: "
				+ this.otpCelular);
		if (this.otpCorreo.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes,
					"Debe ingresar el código enviado al correo: " + this.usuario.getCorreo());
		} else if (this.otpCelular.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes,
					"Debe ingresar el código enviado al teléfono: " + this.usuario.getCelular());
		} else {
			Integer validacionOtp = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
					this.getHttpSession().getId(), this.usuario.getCodigoCliente(), this.otpCorreo, this.otpCelular);
			System.out.println("Validacion OTP: " + validacionOtp);
			if (validacionOtp == 0) {
				this.aniadirMensajeError(tituloMensajes, "Los códigos ingresados no corresponden con los enviados");
			} else if (validacionOtp == 2) {
				this.aniadirMensajeError(tituloMensajes, "Los códigos ingresados han superado su tiempo de vigencia");
			} else {
				// Guarda al usuario
				this.usuario.setEstado("A");
				this.usuario.setCambiaClave("N");
				this.usuario.setBloqueado("N");
				this.usuario.setIntentoFallidoAutenticacion(0);
				this.usuario.setIntentoFallidoRespuestas(0);
				this.usuario.setRegistradoPor(this.usuario.getUsuario());
				this.usuario.setFechaRegistro(new Date());
				this.usuario.setFechaUltimoCambioClave(new Date());

				List<TwebRespuestasUsuarios> listaRespuestasUsuario = new ArrayList<TwebRespuestasUsuarios>();
				for (PreguntasRespuestas lista : listaPreguntasRespuestas) {
					TwebRespuestasUsuarios restemp = new TwebRespuestasUsuarios();
					TwebBancoPregunta bancoPregunta = new TwebBancoPregunta();
					bancoPregunta.setId(lista.getIdPregunta());
					bancoPregunta.setDescripcion(lista.getPregunta());
					restemp.setBancoPregunta(bancoPregunta);
					restemp.setDescripcion(lista.getRespuesta());
					restemp.setEstado("A");
					restemp.setObservacion("CREACION DE USUARIO");
					listaRespuestasUsuario.add(restemp);
				}

				String claveMd5 = null;
				try {
					claveMd5 = UtilCryptography.encriptarMd5(this.claveTemp);
				} catch (ExcepcionEncriptacion e1) {
					e1.printStackTrace();
				}

				int resultado = servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().registrarUsuario(
						this.getHttpSession().getId(), this.usuario, listaRespuestasUsuario, this.nombreCliente,
						claveMd5, Long.valueOf("3"), this.getIpLocal());

				System.out.println("Resultado de creacion de usuario: " + resultado);

				if (resultado == 1) {
					HttpSession lHttpSession = getHttpServletRequest().getSession();
					if (lHttpSession != null)
						lHttpSession.invalidate();
					this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_FIN_REGISTRO,
							"");
					this.configuracionesGenerales.mensajeTransaccionExitosa();
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
				}
			}
		}
	}

	/*
	 * Adicionado por: Rudy Rodriguez Fecha adicion: 10-04-2014 Metodo para buscar
	 * una pregunta aleatoria partiendo del nombre del usuario
	 */
	public void validaUsuarioDesbloqueoOlvido(ActionEvent evento) {
		// TwebUsuario usuarioTemp = new TwebUsuario();
		System.out.println("Envia a buscar usuario para cambio de constraseña " + this.usuario.getUsuario());
		this.usuario = this.servicioUsuario.getIServicioUsuarioWs().obtenerUsuarioBase(this.getHttpSession().getId(),
				this.usuario.getUsuario());
		if (usuario == null) {
			usuario = new TwebUsuario();
			this.aniadirMensajeError(tituloMensajes, "Usuario ingresado no existe.");
		} else {
			Integer conteoRol = 0;
			List<TwebUsuarioRol> rolesUsuario = new ArrayList<TwebUsuarioRol>();
			rolesUsuario = this.servicioUsuario.getIServicioUsuarioWs().rolesUsuario(this.getHttpSession().getId(),
					this.usuario.getId());
			for (TwebUsuarioRol usuarioRol : rolesUsuario) {
				// if (usuarioRol.getRol().getId() == 1
				// || usuarioRol.getRol().getId() == 2) {
				if (usuarioRol.getRol().getId() != 3 && usuarioRol.getRol().getId() != 5) {
					conteoRol += 1;
				}
			}
			System.out.println("Valida usuario, manda a buscar pregunta secreta : " + this.usuario.getUsuario());
			this.preguntaSecreta = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
					.consultaPreguntaSecreta(this.getHttpSession().getId(), this.usuario.getUsuario());
			if (conteoRol == 0) {
				this.contestaPreguntaSecreta = true;
				if (this.preguntaSecreta.getIdPregunta() == 0) {
					this.aniadirMensajeError(tituloMensajes, "No se encuentra pregunta para el usuario");
				}
			} else {
				// para que deje pasar el filtro
				this.preguntaSecreta.setIdPregunta(conteoRol);
				this.contestaPreguntaSecreta = false;
				String otpError = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().envioOtp(
						this.getHttpSession().getId(), this.usuario.getCodigoCliente(), this.usuario.getCelular(),
						this.usuario.getCorreo(), "S", "Desbloqueo/Olvido usuario");
				if (otpError == null) {
					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PROCESO_DESBLOQUEO_OLVIDO, "2.jsf");
				} else {
					this.configuracionesGenerales.mensajeTransaccionError();
					System.out.println(otpError);
				}
			}
		}
		/*
		 * System.out.println("Valida usuario, manda a buscar pregunta secreta : " +
		 * this.usuario.getUsuario()); this.preguntaSecreta =
		 * this.servicioMantenimientoUsuario
		 * .getIServicioRegistroUsuarioWs().consultaPreguntaSecreta(
		 * this.usuario.getUsuario()); if (this.preguntaSecreta.getIdPregunta() == 0) {
		 * this.aniadirMensajeError(tituloMensajes, "Usuario ingresado no existe."); }
		 */
	}

	/*
	 * Adicionado por: Rudy Rodriguez Fecha adicion: 11-04-2014 metodo para validar
	 * respuesta a una pregunta secreta utilizado en el desbloqueo de usario o
	 * olvido de contrasena
	 */
	public void validaRespuestaDesbloqueoOlvido(ActionEvent evento) {
		if (this.respuestaDesbloqueoOlvido.equals(this.preguntaSecreta.getRespuesta())) {
			System.out.println("Todo oks validaRespuestaDesbloqueoOlvido");
			this.usuario.setCodigoCliente(this.preguntaSecreta.getCodigoCliente());
			this.usuario.setCorreo(this.preguntaSecreta.getCorreo());
			this.usuario.setCelular(this.preguntaSecreta.getCelular());
			String otpError = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().envioOtp(
					this.getHttpSession().getId(), this.usuario.getCodigoCliente(), this.usuario.getCelular(),
					this.usuario.getCorreo(), "S", "Desbloqueo/Olvido usuario");
			if (otpError == null) {
				this.configuracionesGenerales
						.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PROCESO_DESBLOQUEO_OLVIDO, "2.jsf");
			} else {
				this.configuracionesGenerales.mensajeTransaccionError();
				System.out.println(otpError);
			}
		} else {
			this.aniadirMensajeError("Aplicación:", "Respuesta es INCORRECTA");
		}
	}

	/**
	 * Añadido por : Rudy Rodriguez Fecha adicion : 14-04-2014 Valida la contraseña
	 * temporal y las claves, manda a actualizar el estado en caso de estar
	 * bloqueado y manda a actualizar la clave del usuario en el LDAP
	 */
	public void desbloqueoOlvidoContrasena(ActionEvent evento) {
		/* validar el otp ante la base de datos */
		// this.usuario.setCodigoCliente(preguntaSecreta.getCodigoCliente());
		this.otpCorreo = this.otpDesbloqueoOlvido;
		this.otpCelular = this.otpDesbloqueoOlvido;

		if (this.claveTemp.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la contraseña");
		} else if (this.claveTemp2.isEmpty()) {
			this.aniadirMensajeError(tituloMensajes, "No se ha ingresado la confirmación de contraseña");
		} else if (!this.claveTemp.equals(this.claveTemp2)) {
			this.aniadirMensajeError(tituloMensajes, "Las contraseñas no coinciden");
		} else if (!ValidacionClaves.ValidaClave("La clave", this.claveTemp,
				this.parametroGeneral.getLongitudMinimaClave(), this.parametroGeneral.getMinimoLetrasClave(),
				this.parametroGeneral.getMinimoMayusculasClave(), this.parametroGeneral.getMinimoNumerosClave(),
				this.parametroGeneral.getMinimoCaracteresEspecialesClave()).equals("")) {
			this.aniadirMensajeError(tituloMensajes, ValidacionClaves.ValidaClave("La clave", this.claveTemp,
					this.parametroGeneral.getLongitudMinimaClave(), this.parametroGeneral.getMinimoLetrasClave(),
					this.parametroGeneral.getMinimoMayusculasClave(), this.parametroGeneral.getMinimoNumerosClave(),
					this.parametroGeneral.getMinimoCaracteresEspecialesClave()));
		} else {
			// System.out.println();
			System.out.println("Validaciones de desbloqueo, olvido de contraseña OK.");

			int validaOtp = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs().confirmacionOtp(
					this.getHttpSession().getId(), this.usuario.getCodigoCliente(), otpCorreo, otpCelular);
			if (validaOtp == 0) {
				this.aniadirMensajeError(tituloMensajes, "Contraseña temporal no es correcta.");
			} else if (validaOtp == 2) {
				this.aniadirMensajeError(tituloMensajes, "Contraseña temporal expirada");
			} else {
				/*
				 * Mandar actualizar fecha de ultimo cambio de clave, el campo desbloqueo en
				 * caso de ser necesario y la contraseña de usuario al LDAP
				 */
				String claveMd5 = null;
				try {
					claveMd5 = UtilCryptography.encriptarMd5(this.claveTemp);
				} catch (ExcepcionEncriptacion e1) {
					e1.printStackTrace();
				}

				Integer resultadoCambioClave = this.servicioMantenimientoUsuario.getIServicioRegistroUsuarioWs()
						.modificaClaveUsuario(this.getHttpSession().getId(), this.usuario.getCodigoCliente(), // preguntaSecreta.getCodigoCliente(),
								this.usuario.getUsuario(), claveMd5, this.usuario.getBloqueado(), // preguntaSecreta.getBloqueado(),
								this.usuario.getUsuario());

				if (resultadoCambioClave == 0) {
					this.configuracionesGenerales.mensajeTransaccionError();
				} else if (resultadoCambioClave == 2) {
					this.aniadirMensajeError(this.tituloMensajes, "Esta clave ya fué utilizada con anterioridad");
				} else {
					this.configuracionesGenerales.redireccionarUrlConstante(
							NombresConstantesMemoria.RUTA_PROCESO_DESBLOQUEO_OLVIDO, "3.jsf");
				}
			}
		}
	}

	public void iniciarSesionDesbloqueo(ActionEvent evento) {
		this.configuracionesGenerales.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_LOGIN_APLICACION, "");
	}

}
