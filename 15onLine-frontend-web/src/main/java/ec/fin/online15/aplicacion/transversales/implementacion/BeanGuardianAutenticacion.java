package ec.fin.online15.aplicacion.transversales.implementacion;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutenticacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutenticacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@QualificadorGuardianAutenticacionSimple
@RequestScoped
public class BeanGuardianAutenticacion extends BaseManagedBean implements
		IBeanGuardiaAutenticacion {

	private static final long serialVersionUID = 1L;

	public boolean estaAutenticado() {
		return isAutenticado();
	}

	@Override
	public void logout() {
		try {
			this.getHttpServletRequest().logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void redireccionarMensajeNoAutenticado() {
		try {
			this.getHttpServletResponse().sendError(401);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void invalidarSession() {
		HttpSession lHttpSession = getHttpServletRequest().getSession();
		if (lHttpSession != null)
			lHttpSession.invalidate();
	}

}
