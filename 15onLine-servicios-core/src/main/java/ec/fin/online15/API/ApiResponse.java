package ec.fin.online15.API;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	 private String codigo;
	 private String mensaje;
	 private String dataJson;
}
