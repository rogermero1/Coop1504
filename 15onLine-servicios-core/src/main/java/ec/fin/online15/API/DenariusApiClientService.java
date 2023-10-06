package ec.fin.online15.API;

import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class DenariusApiClientService {

	private final String BASE_URL = "http://localhost:8080/api-denarius/clientes/api";
	
	public JSONObject consultarAPI(String identificacion) throws Exception {
	       JSONObject solicitudJson = new JSONObject();
	       solicitudJson.put("dataJson", "{\"identificacion\": \"" + identificacion + "\",\"consultarCuentas\": true, \"consultarCreditos\": true}");
	       solicitudJson.put("nombreMetodo", "consultaCliente");
	       
	       URL url = new URL(BASE_URL);
	       HttpURLConnection con = (HttpURLConnection) url.openConnection();
	       con.setRequestMethod("POST");
	       con.setRequestProperty("Content-Type", "application/json; utf-8");
	       con.setRequestProperty("Accept", "application/json");
	       con.setDoOutput(true);

	       try (java.io.OutputStream os = con.getOutputStream()) {
	           byte[] input = solicitudJson.toString().getBytes("utf-8");
	           os.write(input, 0, input.length);
	       }

	       int code = con.getResponseCode();
	       if (code != 200) {
	           throw new RuntimeException("HttpResponseCode: " + code);
	       }

	       java.io.InputStream stream = con.getInputStream();
	       java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
	       String result = s.hasNext() ? s.next() : "";

	       JSONObject respuestaJson = new JSONObject(result);

	       return respuestaJson;
	    }	
}
