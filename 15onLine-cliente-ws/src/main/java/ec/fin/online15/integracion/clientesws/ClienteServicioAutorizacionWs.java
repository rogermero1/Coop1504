package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioAutorizacionWs;

public class ClienteServicioAutorizacionWs {
 
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioAutorizacionWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioAutorizacionWsService";

	private IServicioAutorizacionWs servicioAutorizacion;

	public ClienteServicioAutorizacionWs() {
		try {
			this.servicioAutorizacion = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioAutorizacionWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioAutorizacionWs getInterfaz() {
		return this.servicioAutorizacion;
	}

}
