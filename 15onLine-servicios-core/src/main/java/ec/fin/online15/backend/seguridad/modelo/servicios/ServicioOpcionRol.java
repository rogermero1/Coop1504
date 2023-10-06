package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcionRol;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebOpcionesRolesEAO;

@Stateless
public class ServicioOpcionRol extends
		ServicioMantenimientoEntidad<TwebOpcionRol, Long>  {

	@EJB
	private TwebOpcionesRolesEAO twebOpcionRolEAO;

	
	protected EAOGenerico<TwebOpcionRol, Long> getEAO() {
		return twebOpcionRolEAO;
	}

	public List<TwebOpcionRol> listaOpcionesRolesVigentes() {
		return twebOpcionRolEAO.listaOpcionesRolesVigentes();
	}

	public List<TwebOpcionRol> listaOpcionesRolesPorRol(Long idRol) {
		return twebOpcionRolEAO.listaOpcionesRolesPorRol(idRol);
	}

}
