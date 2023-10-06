package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioCambioClavePreguntasWs;
 
public class ClienteServicioCambioClavePreguntasWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioCambioClavePreguntasWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioCambioClavePreguntasWsService";

	private IServicioCambioClavePreguntasWs servicioCambioClavePreguntasWs;

	public ClienteServicioCambioClavePreguntasWs() {
		try {
			this.servicioCambioClavePreguntasWs = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioCambioClavePreguntasWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioCambioClavePreguntasWs getInterfaz() {
		return this.servicioCambioClavePreguntasWs;
	}
}
