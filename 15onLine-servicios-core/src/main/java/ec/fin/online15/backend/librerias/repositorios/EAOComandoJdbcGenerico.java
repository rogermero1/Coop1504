package ec.fin.online15.backend.librerias.repositorios;

import org.hibernate.jdbc.Work;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;

public abstract class EAOComandoJdbcGenerico implements Work {
	protected ExcepcionAplicacion errorAplicacion;

	public ExcepcionAplicacion getErrorAplicacion() {
		return errorAplicacion;
	}

}
