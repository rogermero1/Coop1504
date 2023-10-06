package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioOpcionesMenuWs;
import ec.fin.online15.interfaces.IServicioOpcionesMenuWs;

@Named
@RequestScoped
public class BeanServiciosMantenimientoOpcion {

	/*
	 * @EJB(lookup =
	 * "java:global/seed-servicio-enterprise-2.0/seed-servicios-implementacion-2.0/ServicioMantenimientoOpcion!com.corlasosa.seed.servicios.implementacion.seguridad.IServicioMantenimientoOpcion"
	 * ) private IServicioMantenimientoOpcion servicioMantenimientoOpcion;
	 * 
	 * public IServicioMantenimientoOpcion getIServicioMantenimientoOpcion() {
	 * return servicioMantenimientoOpcion; }
	 */

	/*
	 * ServicioOpcionesMenuService servicio = new ServicioOpcionesMenuService();
	 * ServicioOpcionesMenu port1 = servicio.getServicioOpcionesMenuPort();
	 * 
	 * public ServicioOpcionesMenu getIServicioMantenimientoOpcion() { return
	 * port1; }
	 */

	public IServicioOpcionesMenuWs getIServicioOpcionesMenuWs() {
		ClienteServicioOpcionesMenuWs servicioOpcionesMenu = new ClienteServicioOpcionesMenuWs();
		return servicioOpcionesMenu.getInterfaz();
	}
}
