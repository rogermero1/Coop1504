package ec.fin.online15.integracion.localizador;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;

public class ServicioLocalizacionWebService {
	private ServicioLocalizacionWebService() {
	}

	public static <T extends Object> T getInterfazProxyCliente(String urlServicio, String nombreMetodo, String wsdl,
			Class<T> tipo) throws ExcepcionAplicacion {
		try {
			QName serviceName = new QName(urlServicio, nombreMetodo);
			URL wsdlURL = new URL(wsdl);
			Service service = Service.create(wsdlURL, serviceName);
			return service.getPort(tipo);
		} catch (Throwable error) {
			throw new ExcepcionAplicacion(error);
		}
	}
}
