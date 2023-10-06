package ec.fin.online15.integracion.clientesws;


import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.general.util.ValoresConstantes;
import ec.fin.online15.integracion.localizador.ServicioLocalizacionWebService;
import ec.fin.online15.interfaces.IServicioAdministracionUsuarioWs;

public class ClienteServicioAdministracionUsuarioWs { 

	private static String wsdl = ValoresConstantes.IPWS + ValoresConstantes.lContexto
			+ "/ServicioAdministracionUsuarioWs?wsdl";
	private static String targetNamedSpace = ValoresConstantes.TARGETNAMESPACE;
	private static String serviceName = "ServicioAdministracionUsuarioWsService";

	private IServicioAdministracionUsuarioWs servicioAdministracionUsuario;

	public ClienteServicioAdministracionUsuarioWs() {
		try {
			this.servicioAdministracionUsuario = ServicioLocalizacionWebService
					.getInterfazProxyCliente(targetNamedSpace, serviceName,
							wsdl, IServicioAdministracionUsuarioWs.class);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public IServicioAdministracionUsuarioWs getInterfaz() {
		return this.servicioAdministracionUsuario;
	}

}
