package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioUsuarioWs;
import ec.fin.online15.interfaces.IServicioUsuarioWs;

@Named
@RequestScoped
public class BeanServiciosUsuario {

	/*
	 * ServicioUsuarioService servicio = new ServicioUsuarioService();
	 * ServicioUsuario port = servicio.getServicioUsuarioPort();
	 * 
	 * public ServicioUsuario getIServicioUsuario() { return port; }
	 */

	public IServicioUsuarioWs getIServicioUsuarioWs() {
		ClienteServicioUsuarioWs servicioUsuario = new ClienteServicioUsuarioWs();
		return servicioUsuario.getInterfaz();
	}

}
