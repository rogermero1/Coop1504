package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.generales.ValoresConstantesBackEnd;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebIpsAdministrativas;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebIpsAdministrativasEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosRolesEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConexiones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebConexionesEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebIpsAutorizadasEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosGeneralesEAO;

@Stateless
public class ServicioUsuario extends ServicioMantenimientoEntidad<TwebUsuario, Long> {

	@EJB
	private TwebUsuariosEAO twebUsuarioEAO;

	@EJB
	private TwebParametrosGeneralesEAO twebParametrosGeneralesEAO;

	@EJB
	private TwebConexionesEAO twebConexionEAO;

	@EJB
	private TwebIpsAutorizadasEAO twebIpsAutorizadasEAO;

	@EJB
	private TwebIpsAdministrativasEAO twebIpsAdministrativasEAO;

	@EJB
	private TwebUsuariosRolesEAO twebUsuariosRolesEAO;

	protected EAOGenerico<TwebUsuario, Long> getEAO() {
		return twebUsuarioEAO;
	}

	public TwebUsuario obtenerUsuarioBase(String usuario) {
		TwebUsuario usuarioTemp = new TwebUsuario();
		usuarioTemp = this.twebUsuarioEAO.obtenerUsuarioBase(usuario);
		if (usuarioTemp != null)
			return twebUsuarioEAO.buscarPorId(usuarioTemp.getId());
		else
			return null;
		// return this.twebUsuarioEAO.obtenerUsuarioBase(usuario);
	}

	public String errorRespuestaSecreta(String usuario, String actualizadoPor) {
		List<TwebParametrosGenerales> listaParametros = this.twebParametrosGeneralesEAO.listaParametrosGenerales();
		TwebParametrosGenerales parametroGeneral;
		String mensaje = "";
		if (!listaParametros.isEmpty()) {
			parametroGeneral = listaParametros.get(0);
			TwebUsuario twebUsuario = this.twebUsuarioEAO.obtenerUsuarioBase(usuario);

			if (twebUsuario.getIntentoFallidoRespuestas()
					+ 1 >= ((parametroGeneral.getIntentosFallidosParaBloquear() == null) ? 0
							: parametroGeneral.getIntentosFallidosParaBloquear())) {
				twebUsuario.setBloqueado("S");
				twebUsuario.setFechaUltimoBloqueo(new Date());
				twebUsuario.setFechaActualizacion(new Date());
				twebUsuario.setActualizadoPor(actualizadoPor);
				mensaje = "Usuario bloqueado";
			}

			twebUsuario.setIntentoFallidoRespuestas(twebUsuario.getIntentoFallidoRespuestas() + 1);
			twebUsuarioEAO.actualizar(twebUsuario);

			return mensaje;
		} else {
			mensaje = "Parametros de bloqueo";
		}
		return mensaje;
	}

	public boolean consultaIpConocida(TwebUsuario usuario, String ip) {
		List<TwebIpsAutorizadas> ipsAutorizadas = twebIpsAutorizadasEAO.consultaIpPorUsuario(ip, usuario);
		if (ipsAutorizadas.isEmpty())
			return false;
		else
			return true;
	}

	public boolean validaIpAdministrativa(String ip) {
		List<TwebIpsAdministrativas> ipsAdministrativas = twebIpsAdministrativasEAO.consultaIp(ip);
		if (ipsAdministrativas.isEmpty())
			return false;
		else
			return true;
	}

	public void registrarConexion(String sesion, TwebUsuario usuario, String ip, String observacion, boolean guardarIp,
			String actualizadoPor, boolean usuarioAdministrador, Canal canal) {
		TwebConexiones conexion = new TwebConexiones();
		conexion.setSesion(sesion);
		conexion.setIp(ip);
		conexion.setEstado("A");
		conexion.setFechaRegistro(new Date());
		conexion.setFechaConexion(new Date());
		conexion.setRegistradoPor(actualizadoPor);
		conexion.setUsuario(usuario);
		conexion.setCanal(canal);
		twebConexionEAO.crear(conexion);
		if (guardarIp) {
			TwebIpsAutorizadas ipAutorizada = new TwebIpsAutorizadas();
			ipAutorizada.setIp(ip);
			ipAutorizada.setUsuario(usuario);
			ipAutorizada.setRegistradoPor(actualizadoPor);
			ipAutorizada.setEstado("A");
			ipAutorizada.setFechaRegistro(new Date());
			ipAutorizada.setObservacion(observacion);
			this.twebIpsAutorizadasEAO.crear(ipAutorizada);
		}

		if (!usuarioAdministrador) {
			Integer plantillaNotificacion = null;
			if (canal == Canal.WEB) {
				plantillaNotificacion = ValoresConstantesBackEnd.PLANTILLANOTIFICACIONCONEXIONWEB;
			} else if (canal == Canal.APP) {
				plantillaNotificacion = ValoresConstantesBackEnd.PLANTILLANOTIFICACIONCONEXIONAPP;
			}

			List<String> parametros = new ArrayList<String>();
			parametros.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((new Date())));
			parametros.add(ip);
			System.out.println("Resultado de notificacion al registrar conexion de usuario: " + usuario.getUsuario()
					+ " en canal " + canal.toString() +": "+ this.twebUsuarioEAO.notificacion(usuario.getCodigoCliente(), usuario.getCelular(),
							usuario.getCorreo(), plantillaNotificacion, parametros));
		}
	}

	public String envioNotificacion(Integer codigoCliente, String telefono, String correo, Integer plantilla,
			List<String> parametros) {
		return this.twebUsuarioEAO.notificacion(codigoCliente, telefono, correo, plantilla, parametros);
	}

	public TwebUsuario consultaUsuarioPorNumeroIdentificacion(String identificacion) {
		return this.twebUsuarioEAO.consultaUsuarioPorNumeroIdentificacion(identificacion);
	}

	public String consultaEstadoBatch() {
		return this.twebUsuarioEAO.consultaEstadoBatch();
	}

	public String consultaNombreCliente(Integer codigoCliente) {
		return this.twebUsuarioEAO.consultaNombreCliente(codigoCliente);
	}
}
