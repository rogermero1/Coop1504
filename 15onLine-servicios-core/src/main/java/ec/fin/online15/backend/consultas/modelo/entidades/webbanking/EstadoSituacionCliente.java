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
public class EstadoSituacionCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private Integer codigoEstado;
	private Integer codigoTipoEstado;
	private String descripcion;
	private Double valor;
	private Double valorNuevo;
	/*
	 * public EstadoSituacionCliente() {
	 * 
	 * }
	 * 
	 * public EstadoSituacionCliente(Integer codigoCliente, Integer codigoEstado,
	 * Integer codigoTipoEstado, String descripcion, Double valor, Double
	 * valorNuevo) {
	 * 
	 * this.codigoCliente = codigoCliente; this.codigoEstado = codigoEstado;
	 * this.codigoTipoEstado = codigoTipoEstado; this.descripcion = descripcion;
	 * this.valor = valor; this.valorNuevo = valorNuevo; }
	 * 
	 * public Integer getCodigoCliente() { return codigoCliente; }
	 * 
	 * public void setCodigoCliente(Integer codigoCliente) { this.codigoCliente =
	 * codigoCliente; }
	 * 
	 * public Integer getCodigoEstado() { return codigoEstado; }
	 * 
	 * public void setCodigoEstado(Integer codigoEstado) { this.codigoEstado =
	 * codigoEstado; }
	 * 
	 * public Integer getCodigoTipoEstado() { return codigoTipoEstado; }
	 * 
	 * public void setCodigoTipoEstado(Integer codigoTipoEstado) {
	 * this.codigoTipoEstado = codigoTipoEstado; }
	 * 
	 * public Double getValor() { return valor; }
	 * 
	 * public void setValor(Double valor) { this.valor = valor; }
	 * 
	 * public Double getValorNuevo() { return valorNuevo; }
	 * 
	 * public void setValorNuevo(Double valorNuevo) { this.valorNuevo = valorNuevo;
	 * }
	 * 
	 * public String getDescripcion() { return descripcion; }
	 * 
	 * public void setDescripcion(String descripcion) { this.descripcion =
	 * descripcion; }
	 */

}
