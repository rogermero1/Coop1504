package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosGeneralesEAO;

@Stateless
public class ServicioParametrosGenerales extends
		ServicioMantenimientoEntidad<TwebParametrosGenerales, Long>  {

	@EJB
	private TwebParametrosGeneralesEAO parametrosGeneralesEAO;

	
	protected EAOGenerico<TwebParametrosGenerales, Long> getEAO() {
		return parametrosGeneralesEAO;
	}

	
	public List<TwebParametrosGenerales> listaParametrosGenerales() {
		return parametrosGeneralesEAO.listaParametrosGenerales();
	}

	
	public Date consultaFechaCalendario(String modulo) {
		return parametrosGeneralesEAO.consultaFechaValida(modulo);
	}
}
