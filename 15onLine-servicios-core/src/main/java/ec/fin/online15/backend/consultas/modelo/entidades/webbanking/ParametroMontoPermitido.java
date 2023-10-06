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
public class ParametroMontoPermitido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double montoMinimoDiario;
	private Double montoMaximoDiario;
	private Double montoTransaccion;

	/*
	 * public ParametroMontoPermitido() { }
	 * 
	 * public ParametroMontoPermitido(Double montoMinimoDiario, Double
	 * montoMaximoDiario, Double montoTransaccion) { super(); this.montoMinimoDiario
	 * = montoMinimoDiario; this.montoMaximoDiario = montoMaximoDiario;
	 * this.montoTransaccion = montoTransaccion; }
	 * 
	 * public Double getMontoMinimoDiario() { return montoMinimoDiario; }
	 * 
	 * public void setMontoMinimoDiario(Double montoMinimoDiario) {
	 * this.montoMinimoDiario = montoMinimoDiario; }
	 * 
	 * public Double getMontoMaximoDiario() { return montoMaximoDiario; }
	 * 
	 * public void setMontoMaximoDiario(Double montoMaximoDiario) {
	 * this.montoMaximoDiario = montoMaximoDiario; }
	 * 
	 * public Double getMontoTransaccion() { return montoTransaccion; }
	 * 
	 * public void setMontoTransaccion(Double montoTransaccion) {
	 * this.montoTransaccion = montoTransaccion; }
	 */
}
