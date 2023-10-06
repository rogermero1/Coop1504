package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioBancoPreguntasWs;
 
public class ClienteServicioBancoPreguntasWs {
	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioBancoPreguntasWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioBancoPreguntasWsService";

	private IServicioBancoPreguntasWs servicioBancoPreguntas;

	public ClienteServicioBancoPreguntasWs() {
		try {
			this.servicioBancoPreguntas = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioBancoPreguntasWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioBancoPreguntasWs getInterfaz() {
		return this.servicioBancoPreguntas;
	}

}
