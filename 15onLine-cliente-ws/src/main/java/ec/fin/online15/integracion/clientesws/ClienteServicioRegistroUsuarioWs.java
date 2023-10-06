package ec.fin.online15.integracion.clientesws;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioRegistroUsuarioWs;

public class ClienteServicioRegistroUsuarioWs {

	private static String wsdl = ValoresConstantes.IPWS
			+ ValoresConstantes.lContexto+"/ServicioRegistroUsuarioWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioRegistroUsuarioWsService";

	private IServicioRegistroUsuarioWs servicioRegistroUsuario;

	public ClienteServicioRegistroUsuarioWs() {
		try {
			this.servicioRegistroUsuario = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioRegistroUsuarioWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioRegistroUsuarioWs getInterfaz() {
		return this.servicioRegistroUsuario;
	}

}
