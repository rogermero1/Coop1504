package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioConvenioWs;
import ec.fin.online15.interfaces.IServicioConvenioWs;

@Named
@RequestScoped
public class BeanServiciosConvenio {

	public IServicioConvenioWs getIServicioConvenioWs() {
		ClienteServicioConvenioWs servicioConvenio = new ClienteServicioConvenioWs();
		return servicioConvenio.getInterfaz();
	}

}
