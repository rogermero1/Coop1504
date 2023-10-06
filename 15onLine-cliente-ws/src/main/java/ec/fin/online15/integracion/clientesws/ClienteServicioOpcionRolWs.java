package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioOpcionRolWs;

public class ClienteServicioOpcionRolWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioOpcionRolWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioOpcionRolWsService";

	private IServicioOpcionRolWs servicioOpcionRol;

	public ClienteServicioOpcionRolWs() {
		try {
			this.servicioOpcionRol = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioOpcionRolWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioOpcionRolWs getInterfaz() {
		return this.servicioOpcionRol;
	}
}
