package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioPerfilTransaccionalWs;
import ec.fin.online15.interfaces.IServicioPerfilTransaccionalWs;

@Named
@RequestScoped
public class BeanServiciosPerfilTransaccional {
	public IServicioPerfilTransaccionalWs getIServicioPerfilTransaccionalWs() {
		ClienteServicioPerfilTransaccionalWs servicioPerfilTransaccional = new ClienteServicioPerfilTransaccionalWs();
		return servicioPerfilTransaccional.getInterfaz();
	}
}
