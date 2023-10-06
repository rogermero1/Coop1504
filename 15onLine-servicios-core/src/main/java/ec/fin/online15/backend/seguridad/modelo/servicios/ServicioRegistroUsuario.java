package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ec.fin.online15.backend.librerias.generales.ValoresConstantesBackEnd;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebHistoricoClaves;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebRolEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosRolesEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebConveniosEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebIpsAutorizadasEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosGeneralesEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebPerfilTransaccionalEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebRespuestasUsuariosEAO;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.PreguntaSecreta;
import ec.fin.online15.backend.ldap.modelo.servicios.LdapServicios; 

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioRegistroUsuario extends
		ServicioMantenimientoEntidad<TwebUsuario, Long>  {

	@EJB
	private TwebUsuariosEAO twebUsuarioEAO;

	@EJB
	private TwebRolEAO twebRolEAO;

	@EJB
	private TwebUsuariosRolesEAO twebUsuarioRolEAO;

	@EJB
	private TwebRespuestasUsuariosEAO twebRespuestasUsuariosEAO;

	@EJB
	private TwebConveniosEAO twebConvenioEAO;

	@EJB
	private TwebIpsAutorizadasEAO twebIpsAutorizadasEAO;

	@EJB
	private TwebPerfilTransaccionalEAO twebPerfilTransaccionalEAO;

	@EJB
	private TwebParametrosGeneralesEAO twebParametrosGeneralesEAO;

	@EJB
	private ServicioHistoricoClave servicioHistoricoClave;

	@Resource
	private SessionContext sessionContext;

	private LdapServicios ldapServicios;

	protected EAOGenerico<TwebUsuario, Long> getEAO() {
		return twebUsuarioEAO;
	}

	public Integer compruebaExistenciaUsuario(String usuario) {
		return twebUsuarioEAO.compruebaExistenciaUsuario(usuario);
	}

	@SuppressWarnings("static-access")
	public int creacionUsuarioLdap(String nombre, String usuario, String clave,
			String descripcion) {
		try {
			ldapServicios.eliminarPorUsuario(usuario);
		} catch (Throwable e1) {
			e1.printStackTrace();
		} 
		HashMap<String, String> atributos = new HashMap<String, String>();
		atributos.put(LdapServicios.SN, nombre);
		atributos.put(LdapServicios.DESCRIPTION, descripcion);
		atributos.put(LdapServicios.USER, usuario);
		atributos.put(LdapServicios.USERPASSWORD, clave);
		/*
		 * try { atributos.put(LdapServicios.USERPASSWORD,
		 * UtilCryptography.encriptarMd5(clave)); } catch (ExcepcionEncriptacion
		 * e) { e.printStackTrace(); }
		 */

		try {
			return ldapServicios.crearUsuarioLdap(atributos);
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		return 0;
	}

	public String envioOtp(Integer codigoCliente, String telefono,
			String correo, String otpsIguales, String observacion) {
		return this.twebUsuarioEAO.envioOtp(codigoCliente, telefono, correo,
				otpsIguales, observacion);
	}

	public Integer confirmacionOtp(Integer codigoCliente, String otpCorreo,
			String otpCelular) {
		return this.twebUsuarioEAO.confirmacionOtp(codigoCliente, otpCorreo,
				otpCelular);
	}

	public Integer creacionUsuario(TwebUsuario usuario,
			List<TwebRespuestasUsuarios> respuestasUsuario,
			String nombreCliente, String clave, Long rol, String ip) {
		UserTransaction cx = sessionContext.getUserTransaction();
		try {
			cx.begin();
			System.out.println("Creando usuario en el Ldap");
			if (this.creacionUsuarioLdap(nombreCliente, usuario.getUsuario(),
					clave, usuario.getObservacion()) == 1) {
				System.out.println("Registro guardado en el LDAP");
				System.out.println("Registrando usuario...");
				this.twebUsuarioEAO.crear(usuario);
				usuario = this.twebUsuarioEAO.obtenerUsuarioBase(usuario
						.getUsuario());
				System.out.println("Usuario: " + usuario.getUsuario()
						+ " creado con el id: " + usuario.getId());

				System.out.println("Buscando rol " + rol);
				TwebRol rolWeb = new TwebRol();
				rolWeb = this.twebRolEAO.buscarPorId(rol);

				System.out.println("Asignando rol: " + rolWeb.getRol());
				TwebUsuarioRol usuarioRol = new TwebUsuarioRol();
				usuarioRol.setUsuario(usuario);
				usuarioRol.setRol(rolWeb);
				usuarioRol.setRegistradoPor(usuario.getRegistradoPor());
				usuarioRol.setEstado("A");
				usuarioRol.setFechaRegistro(new Date());
				usuarioRol.setObservacion("CREACION DE USUARIO");
				usuarioRol.setDescripcion(rolWeb.getObservacion());
				this.twebUsuarioRolEAO.crear(usuarioRol);

				System.out.println("Guardando respuestas");
				if (respuestasUsuario != null) {
					for (TwebRespuestasUsuarios respuestaUsuario : respuestasUsuario) {
						respuestaUsuario.setUsuario(usuario);
						respuestaUsuario.setFechaRegistro(new Date());
						respuestaUsuario.setRegistradoPor(usuario
								.getRegistradoPor());
						this.twebRespuestasUsuariosEAO.crear(respuestaUsuario);
					}
				}

				if (usuario.getConvenioWeb() != null) {
					if (!usuario.getConvenioWeb().equals("000000")) {
						TwebConvenios convenio = this.twebConvenioEAO
								.obtenerConvenio(usuario.getConvenioWeb(),
										usuario.getCodigoCliente());
						convenio.setEstado("I");
						convenio.setActualizadoPor(usuario.getRegistradoPor());
						convenio.setFechaActualizacion(new Date());
						this.twebConvenioEAO.actualizar(convenio);
					}
				}

				TwebIpsAutorizadas ipAutorizada = new TwebIpsAutorizadas();
				ipAutorizada.setIp(ip);
				ipAutorizada.setUsuario(usuario);
				ipAutorizada.setRegistradoPor(usuario.getRegistradoPor());
				ipAutorizada.setEstado("A");
				ipAutorizada.setFechaRegistro(new Date());
				ipAutorizada.setObservacion("REGISTRO DE USUARIO 15onLine");
				this.twebIpsAutorizadasEAO.crear(ipAutorizada);

				/*
				 * Asignacion automatica de los cupos transaccionales permitidos
				 */
				if (rolWeb.getId() == 3) {
					for (TwebPerfilTransaccional perfil : twebPerfilTransaccionalEAO
							.consultaCuposTransaccionalInicial(usuario
									.getCodigoCliente())) {
						perfil.setRegistradoPor(usuario.getUsuario());
						perfil.setEstado("A");
						perfil.setFechaRegistro(new Date());
						perfil.setObservacion("PARAMETRIZACION INICIAL");
						twebPerfilTransaccionalEAO.crear(perfil);
					}
					List<String> parametros = new ArrayList<String>();
					parametros.add(usuario.getUsuario());
					System.out
							.println("Resultado de notificacion creacion de usuario "
									+ usuario.getUsuario()
									+ " "
									+ this.twebUsuarioEAO.notificacion(
											usuario.getCodigoCliente(),
											usuario.getCelular(),
											usuario.getCorreo(),
											ValoresConstantesBackEnd.PLANTILLANOTIFICACIONREGISTRO,
											parametros));
				}

				TwebHistoricoClaves historicoClave = new TwebHistoricoClaves();
				historicoClave.setUsuario(usuario);
				historicoClave.setFechaRegistro(new Date());
				historicoClave.setRegistradoPor(usuario.getUsuario());
				historicoClave.setClave(clave);
				this.servicioHistoricoClave.crear(historicoClave);

				System.out.println("Creacion de usuario exitosa");
				cx.commit();
				return 1;
			} else {
				cx.rollback();
				return 0;
			}
		} catch (Exception e) {
			try {
				cx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return 0;
		}
	}

	public Integer actualizacionUsuario(TwebUsuario usuario) {
		UserTransaction cx = sessionContext.getUserTransaction();
		try {
			cx.begin();
			System.out.println("Actualizando usuario....");
			this.twebUsuarioEAO.actualizar(usuario);
			if (usuario.getEstado().equals("I")) {
				System.out
						.println("Anulando perfil transaccional del usuario: "
								+ usuario.getUsuario());
				List<TwebPerfilTransaccional> listaPerfilTransaccionalVigente = new ArrayList<TwebPerfilTransaccional>();
				listaPerfilTransaccionalVigente = this.twebPerfilTransaccionalEAO
						.consultaPerfilTransaccionalCliente(usuario
								.getCodigoCliente());
				for (TwebPerfilTransaccional perfil : listaPerfilTransaccionalVigente) {
					perfil.setEstado("I");
					perfil.setActualizadoPor(usuario.getActualizadoPor());
					perfil.setFechaActualizacion(usuario
							.getFechaActualizacion());
				}
				this.twebPerfilTransaccionalEAO
						.actualizarColeccion(listaPerfilTransaccionalVigente);
			}
			cx.commit();
			return 1;
		} catch (Exception e) {
			try {
				cx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * Actualiza clave de usuario, proceso de cambio de clave 0 error ldap 1 ok
	 * 2 error historico de clave
	 */
	@SuppressWarnings("static-access")
	public int actualizaClave(Integer codigoCliente, String usuario,
			String clave, String bloqueado, String actualizadoPor) {
		UserTransaction cx = sessionContext.getUserTransaction();
		int resultado = 0;
		try {
			List<TwebParametrosGenerales> listaParametros = this.twebParametrosGeneralesEAO
					.listaParametrosGenerales();
			TwebParametrosGenerales parametroGeneral;
			if (!listaParametros.isEmpty()) {
				parametroGeneral = listaParametros.get(0);
				TwebUsuario twebUsuario = this.twebUsuarioEAO
						.obtenerUsuarioBase(usuario);
				if (this.servicioHistoricoClave.verificaHistoricoClave(
						twebUsuario, parametroGeneral.getHistoricoClave(),
						clave)) {
					cx.begin();
					resultado = ldapServicios.modificarContrasenia(usuario,
							clave);
					if (resultado == 1) {
						twebUsuario.setFechaActualizacion(new Date());
						twebUsuario.setActualizadoPor(actualizadoPor);
						twebUsuario.setCambiaClave("N");
						twebUsuario.setFechaUltimoCambioClave(new Date());
						twebUsuario.setIntentoFallidoAutenticacion(0);
						twebUsuario.setIntentoFallidoRespuestas(0);

						if (bloqueado.equals("S")) {
							twebUsuario.setBloqueado("N");
							twebUsuario.setFechaUltimoDesbloqueo(new Date());
							// twebUsuarioEAO.desbloqueoUsuario(usuario,
							// codigoCliente);
						}

						this.twebUsuarioEAO.actualizar(twebUsuario);

						TwebHistoricoClaves historicoClave = new TwebHistoricoClaves();
						historicoClave.setUsuario(twebUsuario);
						historicoClave.setFechaRegistro(new Date());
						historicoClave.setRegistradoPor(twebUsuario
								.getUsuario());
						historicoClave.setClave(clave);
						this.servicioHistoricoClave.crear(historicoClave);

						cx.commit();
						return resultado;
					} else {
						return 0;
					}
				} else {
					return 2;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			try {
				cx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return 0;
		}
	}

	public PreguntaSecreta consultaPreguntaSecreta(String nombreUsuario) {
		List<PreguntaSecreta> listaPreguntaSecreta = this.twebUsuarioEAO
				.consultaPreguntaSecreta(nombreUsuario);
		PreguntaSecreta preguntaSecreta;
		if (!listaPreguntaSecreta.isEmpty()) {
			preguntaSecreta = listaPreguntaSecreta.get(0);
			System.out.println("Pregunta encontrada...");
		} else {
			System.out.println("Pregunta no encontrada, usuario no existe...");
			preguntaSecreta = new PreguntaSecreta(0, 0, "NO ENCUENTRA",
					"NO ENCONTRA", "NO ENCONTRO", "NO ENCONTRO",
					"NO ENCONTRADO");
			// System.out.println(preguntaSecreta.toString());
		}
		return preguntaSecreta;
	}

	/* MAYER SE CREA FUNCION PARA COMPROBAR EL CELULAR */
	public Integer compruebaExistenciaCelular(Integer codigoCliente,
			String celular, String usuario) {
		return twebUsuarioEAO.compruebaExistenciaCelular(codigoCliente,
				celular, usuario);
	}

	/* MAYER SE CREA FUNCION PARA COMPROBAR EL CORREO */
	public Integer compruebaExistenciaCorreo(Integer codigoCliente,
			String correo, String usuario) {
		return twebUsuarioEAO.compruebaExistenciaCorreo(codigoCliente, correo,
				usuario);
	}

}
