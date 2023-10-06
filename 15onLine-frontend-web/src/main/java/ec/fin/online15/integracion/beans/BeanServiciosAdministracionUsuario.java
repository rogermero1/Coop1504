package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioAdministracionUsuarioWs;
import ec.fin.online15.interfaces.IServicioAdministracionUsuarioWs;

@Named
@RequestScoped  
public class BeanServiciosAdministracionUsuario {

	public IServicioAdministracionUsuarioWs getIServicioAdministracionUsuarioWs() {
		ClienteServicioAdministracionUsuarioWs servicioAutorizacion = new ClienteServicioAdministracionUsuarioWs();
		return servicioAutorizacion.getInterfaz();
	} 

}
