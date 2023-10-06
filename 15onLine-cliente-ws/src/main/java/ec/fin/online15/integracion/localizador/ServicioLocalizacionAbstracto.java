package ec.fin.online15.integracion.localizador;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionLocalizacionServicio;

public class ServicioLocalizacionAbstracto {

	public static <T extends Object> T lookup(Properties prop, String nombreComponente, Class<T> tipo)
			throws ExcepcionLocalizacionServicio {
		try {
			Context lContext = new InitialContext(prop);
			return tipo.cast(lContext.lookup(nombreComponente));
		} catch (NamingException e) {
			throw new ExcepcionLocalizacionServicio(e);
		}
	}

}
