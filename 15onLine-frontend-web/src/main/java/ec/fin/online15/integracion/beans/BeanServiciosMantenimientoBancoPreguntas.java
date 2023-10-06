package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioBancoPreguntasWs;
import ec.fin.online15.interfaces.IServicioBancoPreguntasWs;

@Named
@RequestScoped
public class BeanServiciosMantenimientoBancoPreguntas {

	/*
	 * @EJB(lookup =
	 * "java:global/seed-servicio-enterprise-2.0/seed-servicios-implementacion-2.0/ServicioMantenimientoBancoPreguntas!com.corlasosa.seed.servicios.implementacion.webbanking.IServicioMantenimientoBancoPreguntas"
	 * ) private IServicioMantenimientoBancoPreguntas
	 * servicioMantenimientoBancoPreguntas;
	 * 
	 * public IServicioMantenimientoBancoPreguntas
	 * getIServicioMantenimientoBancoPreguntas() { return
	 * this.servicioMantenimientoBancoPreguntas; }
	 */
	/*
	 * ServicioBancoPreguntasService servicio = new
	 * ServicioBancoPreguntasService(); ServicioBancoPreguntas port1 =
	 * servicio.getServicioBancoPreguntasPort();
	 * 
	 * public ServicioBancoPreguntas getIServicioMantenimientoBancoPreguntas() {
	 * return port1; }
	 */

	public IServicioBancoPreguntasWs getIServicioBancoPreguntasWs() {
		ClienteServicioBancoPreguntasWs servicioBancoPreguntas = new ClienteServicioBancoPreguntasWs();
		return servicioBancoPreguntas.getInterfaz();
	}

}
