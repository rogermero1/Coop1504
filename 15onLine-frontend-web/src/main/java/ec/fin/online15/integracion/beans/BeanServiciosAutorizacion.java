package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioAutorizacionWs;
import ec.fin.online15.interfaces.IServicioAutorizacionWs;

@Named
@RequestScoped
public class BeanServiciosAutorizacion {

	/*
	 * @EJB(lookup =
	 * "java:global/seed-servicio-enterprise-2.0/seed-servicios-implementacion-2.0/ServicioAutorizacion!com.corlasosa.seed.servicios.implementacion.seguridad.IServicioAutorizacion"
	 * ) private IServicioAutorizacion servicioAutorizacion;
	 * 
	 * public IServicioAutorizacion getIServicioAutorizacion() { return
	 * servicioAutorizacion; }
	 */

	/*
	 * ServicioAutorizacionService servicio = new ServicioAutorizacionService();
	 * ServicioAutorizacion port1 = servicio.getServicioAutorizacionPort();
	 * 
	 * public ServicioAutorizacion getIServicioAutorizacion() { return port1; }
	 */

	public IServicioAutorizacionWs getIServicioAutorizacionWs() {
		ClienteServicioAutorizacionWs servicioAutorizacion = new ClienteServicioAutorizacionWs();
		return servicioAutorizacion.getInterfaz();
	}
}
