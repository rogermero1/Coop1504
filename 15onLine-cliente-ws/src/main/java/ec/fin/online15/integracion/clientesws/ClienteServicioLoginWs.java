package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioLoginWs;

public class ClienteServicioLoginWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioLoginWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioLoginWsService";

	private IServicioLoginWs servicioLogin;

	public ClienteServicioLoginWs() {
		try {
			this.servicioLogin = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioLoginWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioLoginWs getInterfaz() {
		return this.servicioLogin;
	}
}
