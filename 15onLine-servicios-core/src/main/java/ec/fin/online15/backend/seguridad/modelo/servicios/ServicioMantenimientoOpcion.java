package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebOpcionesEAO;

@Stateless
public class ServicioMantenimientoOpcion extends
		ServicioMantenimientoEntidad<TwebOpcion, Long>  {

	@EJB
	private TwebOpcionesEAO opcionesEAO;

	public List<TwebOpcion> listaOpcionesViegentes() {
		return opcionesEAO.listaOpcionesVigentes();
	}

	protected EAOGenerico<TwebOpcion, Long> getEAO() {
		return opcionesEAO;
	}

	/*
	 * public void inactivarOpcion(TwebOpcion opcion){
	 * opcionesEAO.inactivarOpcion(opcion); }
	 */

}
