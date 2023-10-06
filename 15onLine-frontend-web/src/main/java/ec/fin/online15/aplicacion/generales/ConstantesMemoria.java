package ec.fin.online15.aplicacion.generales;

import java.util.HashMap;

public class ConstantesMemoria {
	private static HashMap<String, Object> almacenConstante;
	static {
		almacenConstante = new HashMap<String, Object>();
	}

	public static <T extends Object> T getValorParametro(
			String nombreParametro, Class<T> tipo) {
		return tipo.cast(almacenConstante.get(nombreParametro));
	}

	public static void setValorParametro(String nombreParametro,
			Object valorParametro) {
		almacenConstante.put(nombreParametro, valorParametro);
	}

}
