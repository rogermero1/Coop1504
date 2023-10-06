package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListadoOpciones implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String descripcion;
	/*
	 * public ListadoOpciones() { }
	 * 
	 * public ListadoOpciones(String id, String descripcion) { super(); this.id =
	 * id; this.descripcion = descripcion; }
	 * 
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 * 
	 * public String getDescripcion() { return descripcion; }
	 * 
	 * public void setDescripcion(String descripcion) { this.descripcion =
	 * descripcion; }
	 */

}
