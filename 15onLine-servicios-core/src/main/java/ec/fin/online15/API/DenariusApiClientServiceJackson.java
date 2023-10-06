package ec.fin.online15.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DenariusApiClientServiceJackson {
	 private final String BASE_URL = "http://localhost:8080/api-denarius/clientes/api";
	 private final ObjectMapper objectMapper = new ObjectMapper();
	 
	 public ApiResponse consultarAPI(String identificacion) throws Exception {
		 Map<String, Object> dataMap = new HashMap<>();
	        dataMap.put("identificacion", identificacion);
	        dataMap.put("consultarCuentas", true);
	        dataMap.put("consultarCreditos", true);
	        
	        // Convertir dataMap a una cadena JSON
	        String dataJsonString = objectMapper.writeValueAsString(dataMap);

	        Map<String, Object> solicitudMap = new HashMap<>();
	        solicitudMap.put("dataJson", dataJsonString);
	        solicitudMap.put("nombreMetodo", "consultaCliente");

	        String solicitudJson = objectMapper.writeValueAsString(solicitudMap);
	       
	        URL url = new URL(BASE_URL);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json; utf-8");
	        con.setRequestProperty("Accept", "application/json");
	        con.setDoOutput(true);

	        try (java.io.OutputStream os = con.getOutputStream()) {
	            byte[] input = solicitudJson.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        int code = con.getResponseCode();
	        if (code != 200) {
	            throw new RuntimeException("HttpResponseCode: " + code);
	        }

	        try (java.io.InputStream stream = con.getInputStream();
	             java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A")) {
	            String result = s.hasNext() ? s.next() : "";
	            
	            // Deserializar respuesta
	            ApiResponse respuesta = objectMapper.readValue(result, ApiResponse.class);
	            return respuesta;
	        }
	    }   
	    
	     
}
