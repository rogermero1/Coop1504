package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.UsuarioMigrado;
import ec.fin.online15.backend.librerias.generales.ValoresConstantesBackEnd;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosRolesEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebConveniosEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosGeneralesEAO;

@Stateless
public class ServicioLogin extends ServicioMantenimientoEntidad<TwebUsuario, Long> {

	@EJB
	private TwebUsuariosEAO twebUsuarioEAO;

	@EJB
	private TwebUsuariosRolesEAO twebUsuariosRolesEAO;

	@EJB
	private TwebParametrosGeneralesEAO twebParametrosGeneralesEAO;

	@EJB
	private TwebConveniosEAO twebConveniosEAO;

	protected EAOGenerico<TwebUsuario, Long> getEAO() {
		return twebUsuarioEAO;
	}

	public String autenticacion(String usuario, boolean resultadoLdap, Canal canal) {
		List<TwebParametrosGenerales> listaParametros = this.twebParametrosGeneralesEAO.listaParametrosGenerales();
		TwebParametrosGenerales parametroGeneral;
		String mensaje = "";
		// System.out.println("ENTRAAAAAA " + usuario + resultadoLdap);
		if (!listaParametros.isEmpty()) {
			parametroGeneral = listaParametros.get(0);
			TwebUsuario twebUsuario = this.twebUsuarioEAO.obtenerUsuarioBase(usuario);
			if (resultadoLdap) {
				if (twebUsuario == null) {
					mensaje = "Credenciales inválidas"; // "Usuario no existe";
					// return false;
				} else {
					System.out.println("BLOQUEADO " + twebUsuario.getBloqueado());
					if (twebUsuario.getBloqueado().equals("N")) {
						if (twebUsuario.getIntentoFallidoAutenticacion() > 0
								|| twebUsuario.getIntentoFallidoRespuestas() > 0) {
							twebUsuario.setIntentoFallidoAutenticacion(0);
							twebUsuario.setIntentoFallidoRespuestas(0);
							twebUsuario.setFechaActualizacion(new Date());
							twebUsuario.setActualizadoPor(usuario);
							this.twebUsuarioEAO.actualizar(twebUsuario);
						}

						Calendar calendario = Calendar.getInstance();
						calendario.setTime(twebUsuario.getFechaUltimoCambioClave());
						calendario.add(Calendar.DAY_OF_YEAR, parametroGeneral.getDiasVigenciaClave());
						System.out.println("Ultimo cambio de clave del usuario " + twebUsuario.getUsuario() + " "
								+ twebUsuario.getFechaUltimoCambioClave());
						if (calendario.getTime().compareTo(new Date()) < 0) {
							System.out.println("CAMBIO DE CLAVE OBLIGATORIO");
							mensaje = "Contraseña vencida";
						}

						if (twebUsuario.getCambiaClave().equals("S")) {
							mensaje = "Cambio de contraseña obligatorio";
						}
						// return true;
					} else {
						mensaje = "Usuario bloqueado";
						// return false;
					}
				}
			} else {
				if (twebUsuario != null) {
					mensaje = "Credenciales inválidas";// linea nueva
					if (twebUsuario.getIntentoFallidoAutenticacion()
							+ 1 >= ((parametroGeneral.getIntentosFallidosParaBloquear() == null) ? 0
									: parametroGeneral.getIntentosFallidosParaBloquear())) {
						twebUsuario.setBloqueado("S");
						twebUsuario.setFechaUltimoBloqueo(new Date());
						twebUsuario.setFechaActualizacion(new Date());
						twebUsuario.setActualizadoPor(usuario);
						mensaje = "Usuario bloqueado";
					}
					twebUsuario.setFechaActualizacion(new Date());
					twebUsuario.setActualizadoPor(usuario);
					twebUsuario.setIntentoFallidoAutenticacion(twebUsuario.getIntentoFallidoAutenticacion() + 1);
					twebUsuarioEAO.actualizar(twebUsuario);
					// mensaje += " Contraseña incorrecta";

					List<TwebUsuarioRol> listaUsuarioRol = twebUsuariosRolesEAO.rolesUsuario(twebUsuario.getId());
					Integer conteoRol = 0;
					if (listaUsuarioRol != null)
						for (TwebUsuarioRol usuarioRol : listaUsuarioRol) {
							if (usuarioRol.getRol().getId() == 1 || usuarioRol.getRol().getId() == 2) {
								conteoRol += 1;
							}
						}
					if (conteoRol == 0) {
						Integer plantillaNotificacion = null;
						if (canal == Canal.WEB) {
							plantillaNotificacion = ValoresConstantesBackEnd.PLANTILLANOTIFICACIONAUTENTICACIONFALLIDAWEB;
						} else if (canal == Canal.APP) {
							plantillaNotificacion = ValoresConstantesBackEnd.PLANTILLANOTIFICACIONAUTENTICACIONFALLIDAAPP;
						}
						List<String> parametros = new ArrayList<String>();
						parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((new Date())));
						System.out.println("Resultado de notificacion autenticacion fallida para el usuario "
								+ twebUsuario.getUsuario() + " "
								+ twebUsuarioEAO.notificacion(twebUsuario.getCodigoCliente(), twebUsuario.getCelular(),
										twebUsuario.getCorreo(), plantillaNotificacion, parametros));
					}
				} else {
					UsuarioMigrado usuarioMigrado = this.twebUsuarioEAO.consultaUsuarioMigrado(usuario);
					if (usuarioMigrado == null) {
						mensaje = "Credenciales inválidas";// "Usuario no existe";
					} else {
						List<TwebConvenios> listaConveniosVigentes = this.twebConveniosEAO
								.conveniosVigentesPorCliente(usuarioMigrado.getCodigoCliente());
						for (TwebConvenios convenio : listaConveniosVigentes) {
							convenio.setActualizadoPor(ValoresConstantesBackEnd.USUARIO);
							convenio.setFechaActualizacion(new Date());
							convenio.setEstado("I");
							this.twebConveniosEAO.actualizar(convenio);
						}
						String numeroConvenio = this.twebConveniosEAO.generacionNumeroConvenio();
						if (numeroConvenio != null) {
							TwebConvenios convenio = new TwebConvenios();
							convenio.setRegistradoPor(ValoresConstantesBackEnd.USUARIO);
							convenio.setFechaRegistro(new Date());
							convenio.setEstado("A");
							convenio.setObservacion("MIGRACION");
							convenio.setCodigoCliente(usuarioMigrado.getCodigoCliente());
							convenio.setNumeroIdentificacion(usuarioMigrado.getNumeroIdentificacion());
							convenio.setNombreCliente(usuarioMigrado.getNombres());
							convenio.setConvenioWeb(numeroConvenio);
							this.twebConveniosEAO.crear(convenio);
							List<String> parametros = new ArrayList<String>();
							parametros.add("Actualización de perfil");
							parametros.add(numeroConvenio);
							System.out.println("Resultado de notificacion migracion para el cliente "
									+ usuarioMigrado.getCodigoCliente() + ": "
									+ this.twebUsuarioEAO.notificacion(usuarioMigrado.getCodigoCliente(), null,
											usuarioMigrado.getCorreo(),
											ValoresConstantesBackEnd.PLANTILLANOTIFICACIONUSUARIOMIGRADO, parametros));
						}
						mensaje = "Usuario migrado";
					}
				}
				// return false;
			}
			return mensaje;
		} else {
			return "Parametros de bloqueo";
			// return false;
		}
	}
}
