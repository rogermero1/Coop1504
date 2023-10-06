package ec.fin.online15.aplicacion.paginas;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("controlPagina")
@SessionScoped
public class BeanPaginas extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pagina;

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public void asignarPagina(String pagina) {
		this.pagina = pagina;
	}

}
