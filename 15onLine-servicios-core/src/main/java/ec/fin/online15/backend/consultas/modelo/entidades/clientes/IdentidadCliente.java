package ec.fin.online15.backend.consultas.modelo.entidades.clientes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentidadCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String nombre;
	private String respuestaCuenta;
	private String respuestaAnioNacimiento;
	private String respuestaUltimaTransaccion;
	private String respuestaPrestamo;
	private String respuestaInversion;
	private String respuestaTransferencia;

	/*
	 * public IdentidadCliente(Integer codigoCliente, String nombre, String
	 * respuestaCuenta, String respuestaAnioNacimiento, String
	 * respuestaUltimaTransaccion, String respuestaPrestamo, String
	 * respuestaInversion, String respuestaTransferencia) { this.codigoCliente =
	 * codigoCliente; this.nombre = nombre; this.respuestaCuenta = respuestaCuenta;
	 * this.respuestaAnioNacimiento = respuestaAnioNacimiento;
	 * this.respuestaUltimaTransaccion = respuestaUltimaTransaccion;
	 * this.respuestaPrestamo = respuestaPrestamo; this.respuestaInversion =
	 * respuestaInversion; this.respuestaTransferencia = respuestaTransferencia; }
	 * 
	 * public Integer getCodigoCliente() { return codigoCliente; }
	 * 
	 * public void setCodigoCliente(Integer codigoCliente) { this.codigoCliente =
	 * codigoCliente; }
	 * 
	 * public String getNombre() { return nombre; }
	 * 
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 * 
	 * public String getRespuestaCuenta() { return respuestaCuenta; }
	 * 
	 * public void setRespuestaCuenta(String respuestaCuenta) { this.respuestaCuenta
	 * = respuestaCuenta; }
	 * 
	 * public String getRespuestaAnioNacimiento() { return respuestaAnioNacimiento;
	 * }
	 * 
	 * public void setRespuestaAnioNacimiento(String respuestaAnioNacimiento) {
	 * this.respuestaAnioNacimiento = respuestaAnioNacimiento; }
	 * 
	 * public String getRespuestaUltimaTransaccion() { return
	 * respuestaUltimaTransaccion; }
	 * 
	 * public void setRespuestaUltimaTransaccion(String respuestaUltimaTransaccion)
	 * { this.respuestaUltimaTransaccion = respuestaUltimaTransaccion; }
	 * 
	 * public String getRespuestaPrestamo() { return respuestaPrestamo; }
	 * 
	 * public void setRespuestaPrestamo(String respuestaPrestamo) {
	 * this.respuestaPrestamo = respuestaPrestamo; }
	 * 
	 * public String getRespuestaInversion() { return respuestaInversion; }
	 * 
	 * public void setRespuestaInversion(String respuestaInversion) {
	 * this.respuestaInversion = respuestaInversion; }
	 * 
	 * public String getRespuestaTransferencia() { return respuestaTransferencia; }
	 * 
	 * public void setRespuestaTransferencia(String respuestaTransferencia) {
	 * this.respuestaTransferencia = respuestaTransferencia; }
	 */

}
