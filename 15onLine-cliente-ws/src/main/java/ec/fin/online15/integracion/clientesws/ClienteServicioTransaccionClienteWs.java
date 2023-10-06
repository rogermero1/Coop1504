package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioTransaccionClienteWs;

public class ClienteServicioTransaccionClienteWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioTransaccionClienteWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioTransaccionClienteWsService";

	private IServicioTransaccionClienteWs servicioTransaccionCliente;

	public ClienteServicioTransaccionClienteWs() {
		try {
			this.servicioTransaccionCliente = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioTransaccionClienteWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioTransaccionClienteWs getInterfaz() {
		return this.servicioTransaccionCliente;
	}
}
