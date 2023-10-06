package ec.fin.online15.aplicacion.filtros;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(dispatcherTypes = { DispatcherType.ERROR, DispatcherType.REQUEST,
		DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC }, urlPatterns = { "/*" })
public class FiltroCacheHTTP implements Filter {

	public FiltroCacheHTTP() {
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

		//System.out.println(" LIMPIANDO CACHE .......");
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		res.setDateHeader("Expires", 0);
		res.setHeader("Pragma", "No-cache");

		// System.out.println("FIN - LIMPIANDO CACHE");
		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
