package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioRegistroUsuarioWs;
import ec.fin.online15.interfaces.IServicioRegistroUsuarioWs;

@Named
@RequestScoped
public class BeanServiciosRegistroUsuario {
	// TRABAJA CONINYECCION DE DEPENDENCIA
	// @EJB(lookup =
	// "java:global/seed-servicio-enterprise-2.0/seed-servicios-implementacion-2.0/ServicioMantenimientoUsuario!com.corlasosa.seed.servicios.implementacion.seguridad.IServicioMantenimientoUsuario")
	// private IServicioMantenimientoUsuario servicioMantenimientoUsuario;

	// public IServicioMantenimientoUsuario getIServicioMantenimientoUsuario() {
	// return servicioMantenimientoUsuario;
	// }

	// TRABAJA CON WEBSERVICES wizard
	// ServicioRegistroUsuarioService servicio = new
	// ServicioRegistroUsuarioService();
	// ServicioRegistroUsuario port1 =
	// servicio.getServicioRegistroUsuarioPort();
	// public ServicioRegistroUsuario getIServicioMantenimientoUsuario() {
	// return port1;
	// }

	// Trabaja con localizador Ws
	public IServicioRegistroUsuarioWs getIServicioRegistroUsuarioWs() {
		ClienteServicioRegistroUsuarioWs servicioRegistroUsuario = new ClienteServicioRegistroUsuarioWs();
		return servicioRegistroUsuario.getInterfaz();
	}
}
