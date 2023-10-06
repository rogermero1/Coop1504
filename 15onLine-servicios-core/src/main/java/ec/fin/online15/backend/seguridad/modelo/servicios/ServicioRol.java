package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebRolEAO;

@Stateless
public class ServicioRol extends ServicioMantenimientoEntidad<TwebRol, Long>
		{

	@EJB
	private TwebRolEAO rol;

	@Override
	protected EAOGenerico<TwebRol, Long> getEAO() {
		return rol;
	}

	public List<TwebRol> listaRolesVigentes() {
		return rol.listaRolesVigentes();
	}

}
