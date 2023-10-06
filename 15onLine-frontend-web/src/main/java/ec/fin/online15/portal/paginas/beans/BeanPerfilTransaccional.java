package ec.fin.online15.portal.paginas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;
import ec.fin.online15.integracion.beans.BeanServiciosPerfilTransaccional;
import ec.fin.online15.integracion.beans.BeanServiciosRegistroUsuario;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("perfilTransaccionalMB")
@SessionScoped
public class BeanPerfilTransaccional extends BaseManagedBean {

	private static final long serialVersionUID = 1L;
	private TwebPerfilTransaccional perfilTransaccionalCooperativa;
	private TwebPerfilTransaccional perfilTransaccionalCliente;

	private List<TwebPerfilTransaccional> listadoPerfilTransaccional;
	private List<ListadoOpciones> listadoCanalesElectronicos;
	private ListadoOpciones opcionSeleccionada;
	private Integer opcion = 0;
	private Integer codigoCliente;
	private String telefono;
	private String correo;
	private boolean confirmacion = false;
	private String codigoSeguridad;
	private boolean botonValidar;

	@Inject
	private BeanServicioUsuario servicioUsuario;

	@Inject
	private BeanServiciosPerfilTransaccional servicioPerfilTransaccional;

	@Inject
	private BeanServiciosRegistroUsuario servicioRegistroUsuario;

	@PostConstruct
	private void init() {
		this.inicializa();
	}

	private void inicializa() {
		System.out.println("Inicializando perfil transaccional...");
		this.codigoCliente = this.servicioUsuario.getTwebUsuario()
				.getCodigoCliente();
		this.telefono = this.servicioUsuario.getTwebUsuario().getCelular();
		this.correo = this.servicioUsuario.getTwebUsuario().getCorreo();
		perfilTransaccionalCooperativa = new TwebPerfilTransaccional();
		perfilTransaccionalCliente = new TwebPerfilTransaccional();
		listadoPerfilTransaccional = new ArrayList<TwebPerfilTransaccional>();
		listadoCanalesElectronicos = new ArrayList<ListadoOpciones>();

		this.codigoSeguridad = "";
		System.out.println("Consulta canales electronicos...");
		listadoCanalesElectronicos = servicioPerfilTransaccional
				.getIServicioPerfilTransaccionalWs()
				.consultaListaCanalesElectronicos(this.getHttpSession().getId());
		this.confirmacion = false;
		this.botonValidar = true;
	}

	public void validaActualizaPerfilTransaccional() {
		System.out.println("Canal seleccionado : " + opcion);
		System.out.println("Validacion de datos modificados");
		Boolean control = true;
		if (this.opcion == 0) {
			this.aniadirMensajeError("Debe seleccionar canal", "");
			control = false;
		}
		/* Valida parametrizacion */
		if (this.perfilTransaccionalCliente.getMontoMinimoTransaccion() < perfilTransaccionalCooperativa
				.getMontoMinimoTransaccion()) {
			this.aniadirMensajeError(
					"Monto mínimo por transacción no puede ser menor al permitido",
					"");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getMontoMaximoTransaccion() > this.perfilTransaccionalCooperativa
				.getMontoMaximoTransaccion()) {
			this.aniadirMensajeError(
					"Monto máximo por transacción no puede ser mayor al permitido",
					"");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getMontoDiario() > this.perfilTransaccionalCooperativa
				.getMontoDiario()) {
			this.aniadirMensajeError(
					"Monto máximo diario no puede ser mayor al permitido", "");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getTransaccionDiaria() > this.perfilTransaccionalCooperativa
				.getTransaccionDiaria()) {
			this.aniadirMensajeError(
					"Número de transacciones diarias no puede ser mayor a las permitidas",
					"");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getMontoSemanal() > this.perfilTransaccionalCooperativa
				.getMontoSemanal()) {
			this.aniadirMensajeError(
					"Monto máximo semanal no puede ser mayor al permitido", "");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getTransaccionSemanal() > this.perfilTransaccionalCooperativa
				.getTransaccionSemanal()) {
			this.aniadirMensajeError(
					"Número de transacciones semanales no puede ser mayor a las permitidas",
					"");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getMontoMensual() > this.perfilTransaccionalCooperativa
				.getMontoMensual()) {
			this.aniadirMensajeError(
					"Monto máximo mensual no puede ser mayor al permitido", "");
			control = false;
		}
		if (this.perfilTransaccionalCliente.getTransaccionesMensual() > this.perfilTransaccionalCooperativa
				.getTransaccionesMensual()) {
			this.aniadirMensajeError(
					"Número de transacciones mensuales no puede ser mayor a las permitidas",
					"");
			control = false;
		}
		System.out.println("Control validación : " + control.toString());
		if (control) {
			String error = servicioRegistroUsuario
					.getIServicioRegistroUsuarioWs().envioOtp(
							this.getHttpSession().getId(), this.codigoCliente,
							this.telefono, this.correo, "S", "Modifica perfil");

			System.out.println("Despues de enviar OTP...");
			System.out.println("Error : " + error);

			if (error == null || error.equals("")) {
				this.confirmacion = true;
				this.botonValidar = true;
			} else {
				this.confirmacion = false;
				this.aniadirMensajeError("No se pudo correo : ", error);
			}
			System.out.println(this.confirmacion);

		} else {
			this.aniadirMensajeError("Aplicación", "Problemas de validación");
		}
	}

	public void confirmaActualizaPerfilTransaccional() {
		Boolean control = true;
		if (this.codigoSeguridad == null || this.codigoSeguridad.equals("")) {
			this.aniadirMensajeError("Aplicación",
					"Código de seguridad no puede estar vacío.");
			control = false;
		}
		if (control) {
			System.out.println("Antes de confirmar => codigo de seguridad");
			Integer respuesta = servicioRegistroUsuario
					.getIServicioRegistroUsuarioWs().confirmacionOtp(
							this.getHttpSession().getId(), this.codigoCliente,
							this.codigoSeguridad, this.codigoSeguridad);
			System.out.println("Depues de confirmar en codigo de seguridad");
			System.out.println("Resultado de confirmacion : " + respuesta);
			if (respuesta == 1) {
				try {
					perfilTransaccionalCliente.setActualizadoPor("HBANKING");
					perfilTransaccionalCliente
							.setFechaActualizacion(new Date());

					servicioPerfilTransaccional
							.getIServicioPerfilTransaccionalWs()
							.actualizaPerfilTransaccionalCliente(
									this.getHttpSession().getId(),
									perfilTransaccionalCliente);
					this.confirmacion = false;
					this.aniadirMensajeInformacion("Información : ",
							"Perfil actualizado correctamente.");
				} catch (Exception e) {
					this.confirmacion = true;
					this.aniadirMensajeError("Aplicación : ",
							"No se pudieron realizar los cambios.");
				}
			} else if (respuesta == 0) {
				this.aniadirMensajeError("Aplicación : ",
						"Código ingresado no existe.");
			} else if (respuesta == 2) {
				this.aniadirMensajeError("Aplicación : ",
						"Tiempo máximo de vigencia de su código de seguridad expiro.");
			}
		} else {
			this.aniadirMensajeError("Aplicación : ",
					"No se puede realizar cambios.");
		}
	}

	public void consultaCupos() {
		this.inicializa();
		// this.aniadirMensajeError("PASO1", "entra");
		/* Perfil inicial de la cooperativa */

		this.botonValidar = false;

		listadoPerfilTransaccional = servicioPerfilTransaccional
				.getIServicioPerfilTransaccionalWs()
				.consultaCuposTransaccionalInicial(
						this.getHttpSession().getId(), codigoCliente);

		if (listadoPerfilTransaccional != null) {
			System.out
					.println("Antes de recorrer la informacion del perfil transaccional general...");
			for (TwebPerfilTransaccional perfil : listadoPerfilTransaccional) {
				// System.out.println("entra al for "
				// + perfil.getIdCanalElectronico() + " " + this.opcion);
				if (perfil.getIdCanalElectronico().toString()
						.equals(this.opcion.toString())) {
					// System.out.println("entra al if " +
					// perfil.getObservacion()
					// + " " + perfil.getId());
					perfilTransaccionalCooperativa = perfil;
				}
			}
		} else {
			this.inicializa();
			this.aniadirMensajeError("Aplicación",
					"No se encontro perfil inicial del cliente");
		}
		/* Perfil transacciona del cliente */
		listadoPerfilTransaccional = servicioPerfilTransaccional
				.getIServicioPerfilTransaccionalWs()
				.consultaPerfilCanalTransaccionalCliente(
						this.getHttpSession().getId(), codigoCliente,
						this.opcion);

		if (listadoPerfilTransaccional != null) {
			System.out
					.println("Antes de recorrer la informacion del perfil transaccional...");
			for (TwebPerfilTransaccional perfil : listadoPerfilTransaccional) {
				// System.out.println("entra al for "
				// + perfil.getIdCanalElectronico() + " " + this.opcion);
				if (perfil.getIdCanalElectronico().toString()
						.equals(this.opcion.toString())) {
					// System.out.println("entra al if " +
					// perfil.getObservacion()
					// + " " + perfil.getId());
					perfilTransaccionalCliente = perfil;
				}
			}
		} else {
			this.inicializa();
			// this.aniadirMensajeError("Aplicación",
			// "No se encontro perfil Transaccional para este canal electronico");
		}
		// this.aniadirMensajeError("PASOFINAL", "sale");
	}

	public TwebPerfilTransaccional getPerfilTransaccionalCooperativa() {
		return perfilTransaccionalCooperativa;
	}

	public void setPerfilTransaccionalCooperativa(
			TwebPerfilTransaccional perfilTransaccionalCooperativa) {
		this.perfilTransaccionalCooperativa = perfilTransaccionalCooperativa;
	}

	public TwebPerfilTransaccional getPerfilTransaccionalCliente() {
		return perfilTransaccionalCliente;
	}

	public void setPerfilTransaccionalCliente(
			TwebPerfilTransaccional perfilTransaccionalCliente) {
		this.perfilTransaccionalCliente = perfilTransaccionalCliente;
	}

	public List<ListadoOpciones> getListadoCanalesElectronicos() {
		return listadoCanalesElectronicos;
	}

	public void setListadoCanalesElectronicos(
			List<ListadoOpciones> listadoCanalesElectronicos) {
		this.listadoCanalesElectronicos = listadoCanalesElectronicos;
	}

	public ListadoOpciones getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpcionSeleccionada(ListadoOpciones opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	public Integer getOpcion() {
		return opcion;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
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

	public boolean isBotonValidar() {
		return botonValidar;
	}

	public void setBotonValidar(boolean botonValidar) {
		this.botonValidar = botonValidar;
	}

}
