package ec.fin.online15.aplicacion.paginas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutenticacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutenticacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@ManagedBean(name = "logoutMB")
@RequestScoped
public class BeanLogout extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private @QualificadorGuardianAutenticacionSimple IBeanGuardiaAutenticacion beanGuardiaAutenticacion;

	@Inject
	private BeanConfiguracionesGenerales beanConfiguracones;

	public String logout() {
		beanGuardiaAutenticacion.logout();
		HttpSession lHttpSession = getHttpServletRequest().getSession();
		if (lHttpSession != null)
			lHttpSession.invalidate();
		System.out.print("LOGOUT");
		return beanConfiguracones
				.getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_SESSION_LOGOUT);
	}
}
