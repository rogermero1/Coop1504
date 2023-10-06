package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioOperacionesFrecuentesWs;
import ec.fin.online15.interfaces.IServicioOperacionesFrecuentesWs;

@Named
@RequestScoped
public class BeanServiciosOperacionesFrecuentes {
	public IServicioOperacionesFrecuentesWs getIServicioOperacionesFrecuentesWs() {
		ClienteServicioOperacionesFrecuentesWs servicioOperacionesFrecuentes = new ClienteServicioOperacionesFrecuentesWs();
		return servicioOperacionesFrecuentes.getInterfaz();
	}
}
