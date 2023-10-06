package ec.fin.online15.aplicacion.paginas;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutenticacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutenticacion;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionEncriptacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;
import ec.fin.online15.librerias.utilerias.UtilCryptography;



@ManagedBean(name = "loginMB")
@RequestScoped
public class BeanLogin extends BaseManagedBean {

	@Inject
	private @QualificadorGuardianAutenticacionSimple
	IBeanGuardiaAutenticacion beanGuardiaAutenticacion;

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String clave;
	
	

	public String getUsuario() {
		System.out.println("*** GET USUARIO " + isAutenticado());
		if (isAutenticado()) {
			beanGuardiaAutenticacion.logout();
			HttpSession lHttpSession = getHttpServletRequest().getSession();
			if (lHttpSession != null)
				lHttpSession.invalidate();
		}
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String autenticar() {
		try {
			if (isAutenticado()) {
				beanGuardiaAutenticacion.logout();
				HttpSession lHttpSession = getHttpServletRequest().getSession();
				if (lHttpSession != null)
					lHttpSession.invalidate();
			}			
			
//System.out.println("antes encriptar "+isAutenticado());
			this.getHttpServletRequest().login(usuario.toUpperCase(),UtilCryptography.encriptarMd5(clave));
//System.out.println("desp ecnt");

			String pagina = ConstantesMemoria.getValorParametro(
					NombresConstantesMemoria.RUTA_VERIFICA_CONEXION,
					String.class);
//System.out.println("pagina "+pagina);			
			return pagina;
			/*
			 * return ConstantesMemoria.getValorParametro(
			 * NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION,
			 * String.class);
			 */
		} catch (ExcepcionEncriptacion error) {
			this.aniadirMensajeError("Error Aplicacion",
					"Error tecnico, revisar los logs");
			error.printStackTrace();
			return "";
		} catch (ServletException e) {
			e.printStackTrace();
			HttpSession lHttpSession = getHttpServletRequest().getSession();
			if (lHttpSession != null)
				lHttpSession.invalidate();

			String mensajeLogin = (String) this.getHttpServletRequest()
					.getAttribute("mensajeErrorAutenticacion");

			this.aniadirMensajeError("Error",
					(mensajeLogin == null) ? "Credenciales inválidas"
							: mensajeLogin);

			if (mensajeLogin.equals("Contraseña vencida")
					|| mensajeLogin.equals("Cambio de contraseña obligatorio")) {
				System.out
						.println("Enviando a cambiar clave por vencimiento / cambio obligatorio");
				return ConstantesMemoria.getValorParametro(
						NombresConstantesMemoria.RUTA_INICIO_DESBLOQUEO,
						String.class);
			} else if (mensajeLogin.equals("Usuario migrado")) {
				System.out.println("Enviando a actualizar usuario migrado");
				return ConstantesMemoria.getValorParametro(
						NombresConstantesMemoria.RUTA_USUARIO_MIGRADO,
						String.class);
			} else {
				return "";
			}
			// this.aniadirMensajeError("Error Aplicación",
			// "Usuario/Clave inválidos");
			// // e.printStackTrace();
			// return "";
		}
	}

	
	
	

}
