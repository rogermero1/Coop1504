package ec.fin.online15.librerias.utilerias;

public class ValidacionClaves {

	public static String ValidaClave(String concepto, String clave,
			Integer longitudMinima, Integer minimoLetras,
			Integer minimoMayusculas, Integer minimoNumeros,
			Integer minimoCaracteresEspeciales) {

		boolean validacion = false;
		Integer letrasMinusculas = 0;
		Integer letrasMayusculas = 0;
		Integer numeros = 0;
		Integer caracterEspecial = 0;

		if (clave.length() < longitudMinima) {
			validacion = false;
		} else {
			for (int i = 0; i < clave.length(); i++) {
				if ((clave.charAt(i) > 64 && clave.charAt(i) < 91))
					letrasMayusculas += 1;
				else if (clave.charAt(i) > 96 && clave.charAt(i) < 123)
					letrasMinusculas += 1;
				else if (clave.charAt(i) >= 48 && clave.charAt(i) <= 57)
					numeros += 1;
				else
					caracterEspecial += 1;
			}
			if (letrasMayusculas >= minimoMayusculas
					&& (letrasMinusculas + letrasMayusculas) >= minimoLetras
					&& numeros >= minimoNumeros
					&& caracterEspecial >= minimoCaracteresEspeciales) {
				validacion = true;
			} else {
				validacion = false;
			}
		}

		if (validacion) {
			return "";
		} else {
			return concepto
					+ " debe tener una longitud mínima de: "
					+ longitudMinima
					+ " caracteres,"
					+ (minimoLetras > 0 ? " al menos " + minimoLetras
							+ " letras" : "")
					+ (minimoMayusculas > 0 ? ", " + minimoMayusculas
							+ " mayúsculas" : "")
					+ (minimoNumeros > 0 ? ", " + minimoNumeros + " números"
							: "")
					+ (minimoCaracteresEspeciales > 0 ? ", "
							+ minimoCaracteresEspeciales
							+ " caracteres especiales" : "");
		}

	}

	public static String MensajeFormatoClave(Integer longitudMinima,
			Integer minimoLetras, Integer minimoMayusculas,
			Integer minimoNumeros, Integer minimoCaracteresEspeciales) {
		return "La clave debe tener una longitud mínima de: "
				+ longitudMinima
				+ " caracteres,"
				+ (minimoLetras > 0 ? " al menos " + minimoLetras + " letras"
						: "")
				+ (minimoMayusculas > 0 ? ", " + minimoMayusculas
						+ " mayúsculas" : "")
				+ (minimoNumeros > 0 ? ", " + minimoNumeros + " números" : "")
				+ (minimoCaracteresEspeciales > 0 ? ", "
						+ minimoCaracteresEspeciales + " caracteres especiales"
						: "");
	}

	// public static boolean ValidaClave(String clave, Integer longitudMinima,
	// Integer minimoLetras, Integer minimoMayusculas,
	// Integer minimoNumeros, Integer minimoCaracteresEspeciales) {
	// Integer letrasMinusculas = 0;
	// Integer letrasMayusculas = 0;
	// Integer numeros = 0;
	// Integer caracterEspecial = 0;
	//
	// if (clave.length() < longitudMinima)
	// return false;
	// for (int i = 0; i < clave.length(); i++) {
	// if ((clave.charAt(i) > 64 && clave.charAt(i) < 91))
	// letrasMayusculas += 1;
	// else if (clave.charAt(i) > 96 && clave.charAt(i) < 123)
	// letrasMinusculas += 1;
	// else if (clave.charAt(i) >= 48 && clave.charAt(i) <= 57)
	// numeros += 1;
	// else
	// caracterEspecial += 1;
	// }
	// if (letrasMayusculas >= minimoMayusculas
	// && (letrasMinusculas + letrasMayusculas) >= minimoLetras
	// && numeros >= minimoNumeros
	// && caracterEspecial >= minimoCaracteresEspeciales) {
	// return true;
	// } else {
	// return false;
	// }
	// }

}
