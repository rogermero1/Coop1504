package ec.fin.online15.integracion.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.fin.online15.integracion.clientesws.ClienteServicioParametrosGeneralesWs;
import ec.fin.online15.interfaces.IServicioParametrosGeneralesWs;

@Named
@RequestScoped
public class BeanServiciosMantenimientoParametrosGenerales {

	/*
	 * ServicioParametrosGeneralesService servicio = new
	 * ServicioParametrosGeneralesService(); ServicioParametrosGenerales port1 =
	 * servicio .getServicioParametrosGeneralesPort();
	 * 
	 * public ServicioParametrosGenerales
	 * getIServicioMantenimientoParametrosGenerales() { return port1; }
	 */

	public IServicioParametrosGeneralesWs getIServicioParametrosGeneralesWs() {
		ClienteServicioParametrosGeneralesWs servicioParametrosGenerales = new ClienteServicioParametrosGeneralesWs();
		return servicioParametrosGenerales.getInterfaz();
	}

}
