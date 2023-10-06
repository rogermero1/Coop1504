package ec.fin.online15.backend.webbanking.modelo.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebLogWs;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebLogWsEAO;

@Stateless
public class ServicioLogWs extends
		ServicioMantenimientoEntidad<TwebLogWs, Long>  {

	@EJB
	private TwebLogWsEAO twebLogWs;

	@Override
	protected EAOGenerico<TwebLogWs, Long> getEAO() {
		return twebLogWs;
	}

}
