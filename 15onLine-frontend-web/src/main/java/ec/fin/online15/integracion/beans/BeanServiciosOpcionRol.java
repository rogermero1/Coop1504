package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioOpcionRolWs;
import ec.fin.online15.interfaces.IServicioOpcionRolWs;

@Named
@RequestScoped
public class BeanServiciosOpcionRol {
	public IServicioOpcionRolWs getIServicioOpcionRolWs() {
		ClienteServicioOpcionRolWs servicioOpcionRol = new ClienteServicioOpcionRolWs();
		return servicioOpcionRol.getInterfaz();
	}
}
