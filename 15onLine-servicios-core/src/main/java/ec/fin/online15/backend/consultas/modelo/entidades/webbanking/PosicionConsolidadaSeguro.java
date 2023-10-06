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
public class PosicionConsolidadaSeguro implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoTipoPoliza;
	private String descripcionPoliza;
	private Integer codigoSeguro;
	private Integer codigoMoneda;
	private Double valorDeposito;
	private Integer codigoCliente;
	/*
	 * public PosicionConsolidadaSeguro() { }
	 * 
	 * public PosicionConsolidadaSeguro(String codigoTipoPoliza, String
	 * descripcionPoliza, Integer codigoSeguro, Integer codigoMoneda, Double
	 * valorDeposito, Integer codigoCliente) { super(); this.codigoTipoPoliza =
	 * codigoTipoPoliza; this.descripcionPoliza = descripcionPoliza;
	 * this.codigoSeguro = codigoSeguro; this.codigoMoneda = codigoMoneda;
	 * this.valorDeposito = valorDeposito; this.codigoCliente = codigoCliente; }
	 * 
	 * public String getCodigoTipoPoliza() { return codigoTipoPoliza; }
	 * 
	 * public void setCodigoTipoPoliza(String codigoTipoPoliza) {
	 * this.codigoTipoPoliza = codigoTipoPoliza; }
	 * 
	 * public String getDescripcionPoliza() { return descripcionPoliza; }
	 * 
	 * public void setDescripcionPoliza(String descripcionPoliza) {
	 * this.descripcionPoliza = descripcionPoliza; }
	 * 
	 * public Integer getCodigoSeguro() { return codigoSeguro; }
	 * 
	 * public void setCodigoSeguro(Integer codigoSeguro) { this.codigoSeguro =
	 * codigoSeguro; }
	 * 
	 * public Integer getCodigoMoneda() { return codigoMoneda; }
	 * 
	 * public void setCodigoMoneda(Integer codigoMoneda) { this.codigoMoneda =
	 * codigoMoneda; }
	 * 
	 * public Double getValorDeposito() { return valorDeposito; }
	 * 
	 * public void setValorDeposito(Double valorDeposito) { this.valorDeposito =
	 * valorDeposito; }
	 * 
	 * public Integer getCodigoCliente() { return codigoCliente; }
	 * 
	 * public void setCodigoCliente(Integer codigoCliente) { this.codigoCliente =
	 * codigoCliente; }
	 */
}
