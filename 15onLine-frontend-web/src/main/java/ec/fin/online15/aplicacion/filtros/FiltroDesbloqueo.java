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


@WebFilter(dispatcherTypes = { DispatcherType.ERROR, DispatcherType.REQUEST,
		DispatcherType.FORWARD, DispatcherType.INCLUDE }, urlPatterns = { "/desbloqueo/*" })
public class FiltroDesbloqueo implements Filter {

	@Inject
	private BeanRegistroUsuario registroUsuario;

	public FiltroDesbloqueo() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse lresponse = (HttpServletResponse) response;
		System.out.println("Filtro para el directorio DESBLOQUEO "
				+ registroUsuario.getPreguntaSecreta().getIdPregunta());
		if (registroUsuario.getPreguntaSecreta().getIdPregunta() == 0
				|| registroUsuario.getPreguntaSecreta() == null) {
			lresponse.sendError(401);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
