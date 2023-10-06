package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioBloqueoPorValidacionWs;
import ec.fin.online15.interfaces.IServicioBloqueoPorValidacionWs;

@Named
@RequestScoped
public class BeanServiciosBloqueosPorValidacion {

	public IServicioBloqueoPorValidacionWs getIServicioBloqueoPorValidacionWs() {
		ClienteServicioBloqueoPorValidacionWs servicioBloqueoPorValidacion = new ClienteServicioBloqueoPorValidacionWs();
		return servicioBloqueoPorValidacion.getInterfaz();
	}

}
