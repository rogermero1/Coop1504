package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UltimosMovimientosAhorro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date hora;
	private Long secuenciaMovimiento;
	private String descripcionMovimiento;
	private String agencia;
	private Double debito;
	private Double credito;
	private Double valor;
	private Double saldo;
	private String debitoCreditoOtro;
	private String detalleTransaccion;
	/*
	 * public UltimosMovimientosAhorro() { }
	 * 
	 * public UltimosMovimientosAhorro(Date hora, Long secuenciaMovimiento, String
	 * descripcionMovimiento, String agencia, Double debito, Double credito, Double
	 * valor, Double saldo, String debitoCreditoOtro, String detalleTransaccion) {
	 * super(); this.hora = hora; this.secuenciaMovimiento = secuenciaMovimiento;
	 * this.descripcionMovimiento = descripcionMovimiento; this.agencia = agencia;
	 * this.debito = debito; this.credito = credito; this.valor = valor; this.saldo
	 * = saldo; this.debitoCreditoOtro = debitoCreditoOtro; this.detalleTransaccion
	 * = detalleTransaccion; }
	 * 
	 * public Date getHora() { return hora; }
	 * 
	 * public void setHora(Date hora) { this.hora = hora; }
	 * 
	 * public Long getSecuenciaMovimiento() { return secuenciaMovimiento; }
	 * 
	 * public void setSecuenciaMovimiento(Long secuenciaMovimiento) {
	 * this.secuenciaMovimiento = secuenciaMovimiento; }
	 * 
	 * public String getDescripcionMovimiento() { return descripcionMovimiento; }
	 * 
	 * public void setDescripcionMovimiento(String descripcionMovimiento) {
	 * this.descripcionMovimiento = descripcionMovimiento; }
	 * 
	 * public String getAgencia() { return agencia; }
	 * 
	 * public void setAgencia(String agencia) { this.agencia = agencia; }
	 * 
	 * public Double getDebito() { return debito; }
	 * 
	 * public void setDebito(Double debito) { this.debito = debito; }
	 * 
	 * public Double getCredito() { return credito; }
	 * 
	 * public void setCredito(Double credito) { this.credito = credito; }
	 * 
	 * public Double getValor() { return valor; }
	 * 
	 * public void setValor(Double valor) { this.valor = valor; }
	 * 
	 * public Double getSaldo() { return saldo; }
	 * 
	 * public void setSaldo(Double saldo) { this.saldo = saldo; }
	 * 
	 * public String getDebitoCreditoOtro() { return debitoCreditoOtro; }
	 * 
	 * public void setDebitoCreditoOtro(String debitoCreditoOtro) {
	 * this.debitoCreditoOtro = debitoCreditoOtro; }
	 * 
	 * public String getDetalleTransaccion() { return detalleTransaccion; }
	 * 
	 * public void setDetalleTransaccion(String detalleTransaccion) {
	 * this.detalleTransaccion = detalleTransaccion; }
	 */

	@Override
	public String toString() {
		return "UltimosMovimientosAhorro [hora=" + hora + ", secuenciaMovimiento=" + secuenciaMovimiento
				+ ", descripcionMovimiento=" + descripcionMovimiento + ", agencia=" + agencia + ", debito=" + debito
				+ ", credito=" + credito + ", valor=" + valor + ", saldo=" + saldo + ", debitoCreditoOtro="
				+ debitoCreditoOtro + ", detalleTransaccion=" + detalleTransaccion + "]";
	}
}
