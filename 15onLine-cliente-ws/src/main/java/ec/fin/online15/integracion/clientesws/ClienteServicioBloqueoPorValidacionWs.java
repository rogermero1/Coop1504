package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioBloqueoPorValidacionWs;

public class ClienteServicioBloqueoPorValidacionWs {
 
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioBloqueoPorValidacionWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioBloqueoPorValidacionWsService";

	private IServicioBloqueoPorValidacionWs servicioBloqueoPorValidacion;

	public ClienteServicioBloqueoPorValidacionWs() {
		try {
			this.servicioBloqueoPorValidacion = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioBloqueoPorValidacionWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioBloqueoPorValidacionWs getInterfaz() {
		return this.servicioBloqueoPorValidacion;
	}

}
