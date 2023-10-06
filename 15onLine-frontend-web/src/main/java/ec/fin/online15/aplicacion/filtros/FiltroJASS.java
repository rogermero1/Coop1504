package ec.fin.online15.aplicacion.filtros;

import java.io.IOException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutorizacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutorizador;

/**
 * Este filtro debe verificar el usuario en el request, Y tambien debe prmitir
 * la autorizacion de los fuentes
 */
@WebFilter(dispatcherTypes = { DispatcherType.ERROR, DispatcherType.REQUEST,
		DispatcherType.FORWARD, DispatcherType.INCLUDE }, urlPatterns = { "/paginas/*" })
public class FiltroJASS implements Filter {

	@Inject
	private @QualificadorGuardianAutorizacionSimple
	IBeanGuardiaAutorizador beanGuardiaAutorizador;

	@Inject
	private BeanConfiguracionesGenerales beanConfiguracionesGeneral;

	@Inject
	@Default
	private BeanServicioUsuario beanServicioUsuario;

	public FiltroJASS() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest lrequest = (HttpServletRequest) request;
		HttpServletResponse lresponse = (HttpServletResponse) response;
		HttpSession lsession = lrequest.getSession(false);
		// Autenticacion
		System.out.println("FILTRO DE VERIFICACION JASS");
		if (lrequest.getUserPrincipal() == null
				|| !beanServicioUsuario.getAutenticacionCompleta()) {
			System.out
					.println("NO AUTENTICADO, SE DEBE MATAR LA SESSION Y ENVIAR A PANTALLA DE LOGIN");
			if (lsession != null) {
				lsession.invalidate();
			}
			lresponse.sendError(401);
		} else {
			if (lsession == null) {
				System.out
						.println("SESSION FENECIDA, SE REDIRECCIONARA A UN MENSAJE DE SESSION MUERTA");
				lresponse
						.sendRedirect(lrequest.getContextPath()
								+ beanConfiguracionesGeneral
										.getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_SESSION_EXPIRADA));
			} else {
				// Si esta autenticado entonces procedo con la autorizacion
				boolean lURLPermitida = true;
				if (request instanceof HttpServletRequest) {
					//System.out.println("AUTORIZACION");
					HttpServletRequest lHttpServletRequest = ((HttpServletRequest) request);
					String lsUrl = lHttpServletRequest.getRequestURI();
					if (lsUrl != null
							&& (lsUrl.toLowerCase().endsWith("gif")
									|| lsUrl.toLowerCase().endsWith("jpg") || lsUrl
									.toLowerCase().endsWith("png"))) {
						System.out.println("SOLICITUD DE IMAGENES");
					} else {
						//System.out.println("VALIDACION DE URL");
						System.out.println("Validando url: " + lsUrl);
						String lsContextPath = lHttpServletRequest
								.getContextPath();
						lsUrl = lsUrl.replaceFirst(lsContextPath, "");
						// Aqui se debe autorizar la url debo poner un CDI o EJB
						// que consulte a la base de datos
						// La url si esta permitida o no
						lURLPermitida = beanGuardiaAutorizador
								.autorizarUrl(lsUrl);
						System.out.println("Autorizado: " + lURLPermitida);
					}
				}
				if (lURLPermitida)
					chain.doFilter(request, response);
				else
					((HttpServletResponse) response).sendError(403);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
