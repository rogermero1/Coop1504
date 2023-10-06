package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioSolicitudPrestamoWs;
import ec.fin.online15.interfaces.IServicioSolicitudPrestamoWs;

@Named
@RequestScoped
public class BeanServiciosSolicitudPrestamo {

	public IServicioSolicitudPrestamoWs getIServicioSolicitudPrestamoWs() {
		ClienteServicioSolicitudPrestamoWs servicio = new ClienteServicioSolicitudPrestamoWs();
		return servicio.getInterfaz();
	}
}
