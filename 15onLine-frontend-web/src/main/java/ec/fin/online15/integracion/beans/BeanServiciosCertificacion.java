package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioCertificacionWs;
import ec.fin.online15.interfaces.IServicioCertificacionWs;

@Named
@RequestScoped
public class BeanServiciosCertificacion {

	public IServicioCertificacionWs getIServicioCertificacionWs() {
		ClienteServicioCertificacionWs servicio = new ClienteServicioCertificacionWs();
		return servicio.getInterfaz();
	}

}
