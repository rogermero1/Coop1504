package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioParametrosSolicitudWs;
import ec.fin.online15.interfaces.IServicioParametrosSolicitudWs;

@Named
@RequestScoped
public class BeanServiciosParametrosSolicitud {

	public IServicioParametrosSolicitudWs getIServicioParametrosSolicitudWs() {
		ClienteServicioParametrosSolicitudWs servicio = new ClienteServicioParametrosSolicitudWs();
		return servicio.getInterfaz();
	}
}
