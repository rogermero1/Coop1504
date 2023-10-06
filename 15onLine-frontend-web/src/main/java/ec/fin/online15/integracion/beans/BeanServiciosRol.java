package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioRolWs;
import ec.fin.online15.interfaces.IServicioRolWs;

@Named
@RequestScoped
public class BeanServiciosRol {
	public IServicioRolWs getIServicioRolWs() {
		ClienteServicioRolWs servicioRol = new ClienteServicioRolWs();
		return servicioRol.getInterfaz();
	}
}
