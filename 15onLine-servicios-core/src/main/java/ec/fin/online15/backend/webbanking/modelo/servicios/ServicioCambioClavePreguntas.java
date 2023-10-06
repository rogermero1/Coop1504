package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.novell.ldap.LDAPException;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebHistoricoClaves;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;
import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioHistoricoClave;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebIpsAutorizadasEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosGeneralesEAO;
import ec.fin.online15.backend.ldap.modelo.servicios.LdapServicios;

@SuppressWarnings("rawtypes")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioCambioClavePreguntas extends ServicioMantenimientoEntidad
		 {

	@EJB
	private ServicioRespuestasUsuarios servicioRespuestasUsuario;

	@EJB
	private TwebUsuariosEAO twebUsuarioEAO;

	@EJB
	private TwebIpsAutorizadasEAO twebIpsAutorizadasEAO;

	@EJB
	private ServicioHistoricoClave servicioHistoricoClave;

	@EJB
	private TwebParametrosGeneralesEAO twebParametrosGeneralesEAO;

	@Resource
	private SessionContext sessionContext;

	@Override
	protected EAOGenerico getEAO() {
		return null;
	}

	public List<TwebRespuestasUsuarios> respuestasVigentesUsuarios(
			TwebUsuario usuario) {
		return this.servicioRespuestasUsuario.listaRespuestasUsuarios(usuario);
	}

	public Integer actualizarRespuestasUsuario(
			List<TwebRespuestasUsuarios> respuestasUsuario) {
		return this.servicioRespuestasUsuario
				.actualizarRespuestasUsuario(respuestasUsuario);
	}

	public boolean compruebaClaveUsuario(String usuario, String clave) {
		try {
			return LdapServicios.validarCredenciales(usuario, clave);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (LDAPException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Integer cambioClave(TwebUsuario usuario, String claveNueva) {
		UserTransaction cx = sessionContext.getUserTransaction();
		Integer resultado = 0;
		try {
			List<TwebParametrosGenerales> listaParametros = this.twebParametrosGeneralesEAO
					.listaParametrosGenerales();
			TwebParametrosGenerales parametroGeneral;
			if (!listaParametros.isEmpty()) {
				parametroGeneral = listaParametros.get(0);
				if (this.servicioHistoricoClave.verificaHistoricoClave(usuario,
						parametroGeneral.getHistoricoClave(), claveNueva)) {
					resultado = LdapServicios.modificarContrasenia(
							usuario.getUsuario(), claveNueva);
					if (resultado == 1) {
						cx.begin();
						usuario.setFechaActualizacion(new Date());
						usuario.setActualizadoPor(usuario.getUsuario());
						usuario.setCambiaClave("N");
						usuario.setFechaUltimoCambioClave(new Date());
						usuario.setIntentoFallidoAutenticacion(0);
						usuario.setIntentoFallidoRespuestas(0);
						this.twebUsuarioEAO.actualizar(usuario);

						TwebHistoricoClaves historicoClave = new TwebHistoricoClaves();
						historicoClave.setUsuario(usuario);
						historicoClave.setFechaRegistro(new Date());
						historicoClave.setRegistradoPor(usuario.getUsuario());
						historicoClave.setClave(claveNueva);
						this.servicioHistoricoClave.crear(historicoClave);
						cx.commit();
						resultado = 1;
					} else {
						resultado = 0;
					}
				} else {
					resultado = 2;
				}
			} else {
				resultado = 0;
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
			resultado = 0;
			e.printStackTrace();
		}
		return resultado;
	}

	public Integer cambioTelefonoCorreo(TwebUsuario usuario) {
		try {
			// auditar con trigger
			usuario.setFechaActualizacion(new Date());
			usuario.setActualizadoPor(usuario.getUsuario());
			this.twebUsuarioEAO.actualizar(usuario);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<TwebIpsAutorizadas> ipsVigenteUsuario(TwebUsuario usuario) {
		return this.twebIpsAutorizadasEAO.consultaIpsVigentesUsuario(usuario);
	}

	public Integer actualizarIpsVigentes(List<TwebIpsAutorizadas> listaIps) {
		try {
			this.twebIpsAutorizadasEAO.actualizarColeccion(listaIps);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
