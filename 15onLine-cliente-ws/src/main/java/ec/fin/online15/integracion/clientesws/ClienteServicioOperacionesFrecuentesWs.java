package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioOperacionesFrecuentesWs;

public class ClienteServicioOperacionesFrecuentesWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioOperacionesFrecuentesWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioOperacionesFrecuentesWsService";

	private IServicioOperacionesFrecuentesWs servicioOperacionesFrecuentes;

	public ClienteServicioOperacionesFrecuentesWs() {
		try {
			this.servicioOperacionesFrecuentes = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioOperacionesFrecuentesWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioOperacionesFrecuentesWs getInterfaz() {
		return this.servicioOperacionesFrecuentes;
	}

}
