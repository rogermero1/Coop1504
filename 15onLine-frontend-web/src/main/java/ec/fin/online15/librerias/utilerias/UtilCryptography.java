package ec.fin.online15.librerias.utilerias;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.digest.DigestUtils;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionEncriptacion;


public class UtilCryptography {
	private static byte[] SALT_BYTES = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8,
			(byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };
	private static int ITERATION_COUNT = 19;
	private final static String CLAVE = "seed7moDulo7$eguRidad7jee";

	public static String encriptar(String str) throws ExcepcionEncriptacion {
		return encriptar(CLAVE, str);
	}

	public static String desencriptar(String str) throws ExcepcionEncriptacion {
		return desencriptar(CLAVE, str);
	}

	public static String encriptarMd5(String str) throws ExcepcionEncriptacion {
		return DigestUtils.md5Hex(str).toUpperCase();
	}

	public static String encriptar(String passPhrase, String str)
			throws ExcepcionEncriptacion {
		try {
			Cipher ecipher = null;

			// Crear la key
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(),
					SALT_BYTES, ITERATION_COUNT);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			// Preparar los parametros para el cipher
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES,
					ITERATION_COUNT);
			// Crear el cipher
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

			// Encodear la cadena a bytes usando utf-8
			byte[] utf8 = str.getBytes("UTF8");
			// Encriptar
			byte[] enc = ecipher.doFinal(utf8);
			// Encodear bytes a base64 para obtener cadena
			return java.util.Base64.getEncoder().encode(enc).toString();
		} catch (Exception e) {
			throw new ExcepcionEncriptacion(e);
		}
	}

	public static String desencriptar(String passPhrase, String str)
			throws ExcepcionEncriptacion {
		try {

			Cipher dcipher = null;
			// Crear la key
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(),
					SALT_BYTES, ITERATION_COUNT);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			dcipher = Cipher.getInstance(key.getAlgorithm());
			// Preparar los parametros para el cipher
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT_BYTES,
					ITERATION_COUNT);
			// Crear el cipher
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			// Decodear base64 y obtener bytes
			byte[] dec = java.util.Base64.getDecoder().decode(str);
			// Desencriptar
			byte[] utf8 = dcipher.doFinal(dec);
			// Decodear usando utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			throw new ExcepcionEncriptacion(e);
		}
	}

	public static void main(String arg[]) throws Exception {
		System.out.println(encriptar("12345678"));
		System.out.println(desencriptar(encriptar("12345678")));
	}
}
