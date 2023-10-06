package ec.fin.online15.aplicacion.filtros;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import ec.fin.online15.aplicacion.paginas.BeanRegistroUsuario;


/**
 * Este filtro permite el paso al directorio /registro/ cuando se ha validado el
 * convenioWeb
 */

@WebFilter(dispatcherTypes = { DispatcherType.ERROR, DispatcherType.REQUEST,
		DispatcherType.FORWARD, DispatcherType.INCLUDE }, urlPatterns = { "/registro/*" })
public class FiltroRegistro implements Filter {

	@Inject
	private BeanRegistroUsuario registroUsuario;

	public FiltroRegistro() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse lresponse = (HttpServletResponse) response;
		System.out.println("Filtro para el directorio REGISTRO "
				+ registroUsuario.getConvenioWeb());
		if (registroUsuario.getConvenioWeb() == null) {
			lresponse.sendError(401);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
