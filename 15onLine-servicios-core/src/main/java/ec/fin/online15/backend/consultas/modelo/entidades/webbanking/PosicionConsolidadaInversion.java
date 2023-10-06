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
public class PosicionConsolidadaInversion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoAplicacion;
	private Integer codigoSubAplicacion;
	private Integer numeroDeposito;
	private Integer codigoMoneda;
	private Double valorUltimaRenovacion;
	private Integer plazo;
	private Double valorTasa;
	private Date fechaVigencia;
	private Date fechaVencimiento;
	private Date fechaProximoPagoInteres;
	private Double interesCausado;
	/*
	 * public PosicionConsolidadaInversion() { }
	 * 
	 * public PosicionConsolidadaInversion(String codigoAplicacion, Integer
	 * codigoSubAplicacion, Integer numeroDeposito, Integer codigoMoneda, Double
	 * valorUltimaRenovacion, Integer plazo, Double valorTasa, Date fechaVigencia,
	 * Date fechaVencimiento, Date fechaProximoPagoInteres, Double interesCausado) {
	 * super(); this.codigoAplicacion = codigoAplicacion; this.codigoSubAplicacion =
	 * codigoSubAplicacion; this.numeroDeposito = numeroDeposito; this.codigoMoneda
	 * = codigoMoneda; this.valorUltimaRenovacion = valorUltimaRenovacion;
	 * this.plazo = plazo; this.valorTasa = valorTasa; this.fechaVigencia =
	 * fechaVigencia; this.fechaVencimiento = fechaVencimiento;
	 * this.fechaProximoPagoInteres = fechaProximoPagoInteres; this.interesCausado =
	 * interesCausado; }
	 * 
	 * public String getCodigoAplicacion() { return codigoAplicacion; }
	 * 
	 * public void setCodigoAplicacion(String codigoAplicacion) {
	 * this.codigoAplicacion = codigoAplicacion; }
	 * 
	 * public Integer getCodigoSubAplicacion() { return codigoSubAplicacion; }
	 * 
	 * public void setCodigoSubAplicacion(Integer codigoSubAplicacion) {
	 * this.codigoSubAplicacion = codigoSubAplicacion; }
	 * 
	 * public Integer getNumeroDeposito() { return numeroDeposito; }
	 * 
	 * public void setNumeroDeposito(Integer numeroDeposito) { this.numeroDeposito =
	 * numeroDeposito; }
	 * 
	 * public Integer getCodigoMoneda() { return codigoMoneda; }
	 * 
	 * public void setCodigoMoneda(Integer codigoMoneda) { this.codigoMoneda =
	 * codigoMoneda; }
	 * 
	 * public Double getValorUltimaRenovacion() { return valorUltimaRenovacion; }
	 * 
	 * public void setValorUltimaRenovacion(Double valorUltimaRenovacion) {
	 * this.valorUltimaRenovacion = valorUltimaRenovacion; }
	 * 
	 * public Integer getPlazo() { return plazo; }
	 * 
	 * public void setPlazo(Integer plazo) { this.plazo = plazo; }
	 * 
	 * public Double getValorTasa() { return valorTasa; }
	 * 
	 * public void setValorTasa(Double valorTasa) { this.valorTasa = valorTasa; }
	 * 
	 * public Date getFechaVigencia() { return fechaVigencia; }
	 * 
	 * public void setFechaVigencia(Date fechaVigencia) { this.fechaVigencia =
	 * fechaVigencia; }
	 * 
	 * public Date getFechaVencimiento() { return fechaVencimiento; }
	 * 
	 * public void setFechaVencimiento(Date fechaVencimiento) {
	 * this.fechaVencimiento = fechaVencimiento; }
	 * 
	 * public Date getFechaProximoPagoInteres() { return fechaProximoPagoInteres; }
	 * 
	 * public void setFechaProximoPagoInteres(Date fechaProximoPagoInteres) {
	 * this.fechaProximoPagoInteres = fechaProximoPagoInteres; }
	 * 
	 * public Double getInteresCausado() { return interesCausado; }
	 * 
	 * public void setInteresCausado(Double interesCausado) { this.interesCausado =
	 * interesCausado; }
	 */

}
