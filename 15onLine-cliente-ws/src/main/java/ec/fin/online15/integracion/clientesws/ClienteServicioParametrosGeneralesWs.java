package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioParametrosGeneralesWs;

public class ClienteServicioParametrosGeneralesWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioParametrosGeneralesWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioParametrosGeneralesWsService";

	private IServicioParametrosGeneralesWs servicioParametrosGenerales;

	public ClienteServicioParametrosGeneralesWs() {
		try {
			this.servicioParametrosGenerales = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioParametrosGeneralesWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioParametrosGeneralesWs getInterfaz() {
		return this.servicioParametrosGenerales;
	}
}
