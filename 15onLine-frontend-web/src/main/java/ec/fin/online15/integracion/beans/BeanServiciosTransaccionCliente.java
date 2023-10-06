package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioTransaccionClienteWs;
import ec.fin.online15.interfaces.IServicioTransaccionClienteWs;

@Named
@RequestScoped
public class BeanServiciosTransaccionCliente {

	public IServicioTransaccionClienteWs getIServicioTransaccionClienteWs() {
		ClienteServicioTransaccionClienteWs servicioTransaccionCliente = new ClienteServicioTransaccionClienteWs();
		return servicioTransaccionCliente.getInterfaz();
	}
}
