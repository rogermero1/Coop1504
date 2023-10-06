package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioParametrosSolicitudWs;

public class ClienteServicioParametrosSolicitudWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioParametrosSolicitudWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioParametrosSolicitudWsService";

	private IServicioParametrosSolicitudWs servicioParametrosSolicitud;

	public ClienteServicioParametrosSolicitudWs() {
		try {
			this.servicioParametrosSolicitud = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioParametrosSolicitudWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioParametrosSolicitudWs getInterfaz() {
		return this.servicioParametrosSolicitud;
	}

}
