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
public class TiposCertificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descripcion;
	private Integer codigoTransaccion;
	private Double valor;

	/*
	 * public TiposCertificacion(Integer id, String descripcion, Integer
	 * codigoTransaccion, Double valor) { this.id = id; this.descripcion =
	 * descripcion; this.codigoTransaccion = codigoTransaccion; this.valor = valor;
	 * }
	 * 
	 * public TiposCertificacion() {
	 * 
	 * }
	 * 
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 * 
	 * public String getDescripcion() { return descripcion; }
	 * 
	 * public void setDescripcion(String descripcion) { this.descripcion =
	 * descripcion; }
	 * 
	 * public Integer getCodigoTransaccion() { return codigoTransaccion; }
	 * 
	 * public void setCodigoTransaccion(Integer codigoTransaccion) {
	 * this.codigoTransaccion = codigoTransaccion; }
	 * 
	 * public Double getValor() { return valor; }
	 * 
	 * public void setValor(Double valor) { this.valor = valor; }
	 */

}
