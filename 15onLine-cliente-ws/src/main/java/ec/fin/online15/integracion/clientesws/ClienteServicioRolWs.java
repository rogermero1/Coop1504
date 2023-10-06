package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioRolWs;

public class ClienteServicioRolWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioRolWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioRolWsService";

	private IServicioRolWs servicioRol;

	public ClienteServicioRolWs() {
		try {
			this.servicioRol = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioRolWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioRolWs getInterfaz() {
		return this.servicioRol;
	}
}
