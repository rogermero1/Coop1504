package ec.fin.online15.API;

import com.fasterxml.jackson.databind.ObjectMapper;

import fin.coop15abril.librerias.dtosdenarius.DatosClienteMS;

public class pruebaAPIJackson {

	public static void main(String[] args) {
		
		DenariusApiClientServiceJackson apiClient=new DenariusApiClientServiceJackson();
		try {
			
			ApiResponse apiResponse=apiClient.consultarAPI("1312598269");
			System.out.println("ESTO ES DE API:"+ apiResponse.getDataJson());			
			ObjectMapper objectMapper = new ObjectMapper();
			DatosClienteMS datos = objectMapper.readValue(apiResponse.getDataJson(),DatosClienteMS.class);
			System.out.println("El nombre: "+datos.getNombre1());
		} catch (Exception e) {
			System.out.println("Error al consultar API: " + e.getMessage());
		}
	}

}
