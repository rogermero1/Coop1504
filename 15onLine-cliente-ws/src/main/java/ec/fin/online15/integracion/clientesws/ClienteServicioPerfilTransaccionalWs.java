package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioPerfilTransaccionalWs;

public class ClienteServicioPerfilTransaccionalWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioPerfilTransaccionalWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioPerfilTransaccionalWsService";

	private IServicioPerfilTransaccionalWs servicioPerfilTransaccional;

	public ClienteServicioPerfilTransaccionalWs() {
		try {
			this.servicioPerfilTransaccional = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioPerfilTransaccionalWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioPerfilTransaccionalWs getInterfaz() {
		return this.servicioPerfilTransaccional;
	}

}
