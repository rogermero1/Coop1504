package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioPosicionConsolidadaWs;
import ec.fin.online15.interfaces.IServicioPosicionConsolidadaWs;

@Named
@RequestScoped
public class BeanServiciosPosicionConsolidada {

	public IServicioPosicionConsolidadaWs getIServicioPosicionConsolidadaWs() {
		ClienteServicioPosicionConsolidadaWs servicioPosicionConsolidada = new ClienteServicioPosicionConsolidadaWs();
		return servicioPosicionConsolidada.getInterfaz();
	}
}
