package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioOpcionesMenuWs;

public class ClienteServicioOpcionesMenuWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioOpcionesMenuWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioOpcionesMenuWsService";

	private IServicioOpcionesMenuWs servicioOpcionesMenu;

	public ClienteServicioOpcionesMenuWs() {
		try {
			this.servicioOpcionesMenu = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioOpcionesMenuWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioOpcionesMenuWs getInterfaz() {
		return this.servicioOpcionesMenu;
	}

}
