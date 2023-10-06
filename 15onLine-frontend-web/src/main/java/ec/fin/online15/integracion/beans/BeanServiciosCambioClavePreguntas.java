package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioCambioClavePreguntasWs;
import ec.fin.online15.interfaces.IServicioCambioClavePreguntasWs;

@Named
@RequestScoped
public class BeanServiciosCambioClavePreguntas {

	public IServicioCambioClavePreguntasWs getIServicioCambioClavePreguntas() {
		ClienteServicioCambioClavePreguntasWs servicioCambioClavePreguntas = new ClienteServicioCambioClavePreguntasWs();
		return servicioCambioClavePreguntas.getInterfaz();
	}
}
