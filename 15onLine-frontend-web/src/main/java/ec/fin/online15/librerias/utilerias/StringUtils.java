package ec.fin.online15.librerias.utilerias;

public class StringUtils {

	public static String enmascararCadena(String cadena) {
		return cadena.substring(0, 2)
				+ org.apache.commons.lang3.StringUtils.leftPad(
						cadena.substring(cadena.length() - 3, cadena.length()),
						cadena.length() - 2, "x");
	}
 
}
