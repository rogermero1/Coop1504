package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebOpcionesEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosEAO;

@Stateless
public class ServicioAutorizacion  {

	@EJB
	private TwebOpcionesEAO opcionEAO;

	@EJB
	private TwebUsuariosEAO usuarioEAO;

	public List<TwebOpcion> listaOpcionesMenuPadre(String usuario,
			String orientacion) {
		return opcionEAO.listaOpcionesPadreUsuario(usuario, orientacion);
	}

	 
	public TwebUsuario buscarUsuarioAplicacionBase(String usuario) {
		return usuarioEAO.obtenerUsuarioBase(usuario);
	}

	 
	public List<TwebOpcion> listaOpcionesHijas(Long idOpcion, String usuario,
			String orientacion) {
		return opcionEAO.listaOpcionesHijas(idOpcion, usuario, orientacion);
	}

}