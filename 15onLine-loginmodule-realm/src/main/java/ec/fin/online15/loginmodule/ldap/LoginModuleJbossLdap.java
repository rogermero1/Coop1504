package ec.fin.online15.loginmodule.ldap;

import java.security.acl.Group;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionLogin;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.integracion.clientesws.ClienteServicioLoginWs;
import ec.fin.online15.loginmodule.IServiciosLoginModule;

@SuppressWarnings("removal")
public class LoginModuleJbossLdap extends UsernamePasswordLoginModule {
	protected ServiciosLoginModuleLdap serviciosLoginModuleLdap = new ServiciosLoginModuleLdap();

	public IServiciosLoginModule getServiciosLoginModule() {
		return serviciosLoginModuleLdap;
	}

	protected String getUsersPassword() throws LoginException {
		// Aqui debo sacar el usuario del REALM ldap, dataource archivos o lo
		// que sea
		return getServiciosLoginModule().getClaveUsuario(getUsername());
	}

	protected Group[] getRoleSets() throws LoginException {
		// sacar los roles del REALM, ldap, datasource archivos o lo que sea
		List<String> lNombresRol = getServiciosLoginModule().getNombreRoles(getUsername());
		if (lNombresRol != null) {
			Group[] groups = { new SimpleGroup("Roles") };
			for (String lRol : lNombresRol) {
				SimplePrincipal role = new SimplePrincipal(lRol);
				groups[0].addMember(role);
			}
			return groups;
		} else
			return null;
	}

	protected void guadarMensajeErrorRequest(String pMensajaErrorAutenticacion) {
		try {
			HttpServletRequest request = (HttpServletRequest) PolicyContext
					.getContext("javax.servlet.http.HttpServletRequest");
			request.setAttribute("mensajeErrorAutenticacion", pMensajaErrorAutenticacion);
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}
	}

	protected void guadarMensajeErrorRequest(ExcepcionLogin pExcepcionLogin) {
		guadarMensajeErrorRequest(pExcepcionLogin.getMensajeUsuario());
	}

	protected boolean validatePassword(String inputPassword, String expectedPassword) {
		try {// System.out.println("valida pass");
			boolean lbClaveValida = getServiciosLoginModule().validarUsuarioPassword(getUsername(), inputPassword);
			ClienteServicioLoginWs clienteServicioLoginWs = new ClienteServicioLoginWs();
			String mensaje = clienteServicioLoginWs.getInterfaz().autenticacion("", getUsername(), lbClaveValida,
					Canal.WEB);
			lbClaveValida = (mensaje.equals("")) ? true : false;
			if (!lbClaveValida) {
				guadarMensajeErrorRequest(mensaje);
			}
			return lbClaveValida;
		} catch (ExcepcionLogin e) {
			guadarMensajeErrorRequest(e);
			return false;
		}
	}

}
