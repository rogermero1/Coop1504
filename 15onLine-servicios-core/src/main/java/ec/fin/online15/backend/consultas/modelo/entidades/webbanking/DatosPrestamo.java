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
public class DatosPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cuotasAtrasadas;
	private Integer cuotasPedientes;
	private Double valorAdeudado;
	private Double saldoCapital;
	private Double saldoTotal;
	/*
	 * public DatosPrestamo() { }
	 * 
	 * public Integer getCuotasAtrasadas() { return cuotasAtrasadas; }
	 * 
	 * public void setCuotasAtrasadas(Integer cuotasAtrasadas) {
	 * this.cuotasAtrasadas = cuotasAtrasadas; }
	 * 
	 * public Integer getCuotasPedientes() { return cuotasPedientes; }
	 * 
	 * public void setCuotasPedientes(Integer cuotasPedientes) {
	 * this.cuotasPedientes = cuotasPedientes; }
	 * 
	 * public Double getValorAdeudado() { return valorAdeudado; }
	 * 
	 * public void setValorAdeudado(Double valorAdeudado) { this.valorAdeudado =
	 * valorAdeudado; }
	 * 
	 * public Double getSaldoCapital() { return saldoCapital; }
	 * 
	 * public void setSaldoCapital(Double saldoCapital) { this.saldoCapital =
	 * saldoCapital; }
	 * 
	 * public Double getSaldoTotal() { return saldoTotal; }
	 * 
	 * public void setSaldoTotal(Double saldoTotal) { this.saldoTotal = saldoTotal;
	 * }
	 */

	public String toString() {
		return "DatosPrestamo [cuotasAtrasadas=" + cuotasAtrasadas
				+ ", cuotasPedientes=" + cuotasPedientes + ", valorAdeudado="
				+ valorAdeudado + ", saldoCapital=" + saldoCapital
				+ ", saldoTotal=" + saldoTotal + "]";
	}
}
