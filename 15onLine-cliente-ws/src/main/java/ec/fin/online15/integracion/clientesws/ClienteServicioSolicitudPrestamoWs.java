package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioSolicitudPrestamoWs;

public class ClienteServicioSolicitudPrestamoWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioSolicitudPrestamoWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioSolicitudPrestamoWsService";

	private IServicioSolicitudPrestamoWs servicioSolicitud;

	public ClienteServicioSolicitudPrestamoWs() {
		try {
			this.servicioSolicitud = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioSolicitudPrestamoWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioSolicitudPrestamoWs getInterfaz() {
		return this.servicioSolicitud;
	}
}
