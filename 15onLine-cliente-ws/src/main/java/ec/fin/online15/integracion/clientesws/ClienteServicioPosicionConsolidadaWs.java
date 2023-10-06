package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioPosicionConsolidadaWs;

public class ClienteServicioPosicionConsolidadaWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioPosicionConsolidadaWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioPosicionConsolidadaWsService";

	private IServicioPosicionConsolidadaWs servicioPosicionConsolidad;

	public ClienteServicioPosicionConsolidadaWs() {
		try {
			this.servicioPosicionConsolidad = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioPosicionConsolidadaWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioPosicionConsolidadaWs getInterfaz() {
		return this.servicioPosicionConsolidad;
	}
}
