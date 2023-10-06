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
public class ConsolidadoPagoServicio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double valorAdeudado;
	private Double valorCancelado;
	private Double valorComision;
	private Double valorMinimoPago;
	private String nombreDeudor;

	/*
	 * public ConsolidadoPagoServicio() { }
	 * 
	 * public ConsolidadoPagoServicio(Double valorAdeudado, Double valorCancelado,
	 * Double valorComision, Double valorMinimoPago, String nombreDeudor) { super();
	 * this.valorAdeudado = valorAdeudado; this.valorCancelado = valorCancelado;
	 * this.valorComision = valorComision; this.valorMinimoPago = valorMinimoPago;
	 * this.nombreDeudor = nombreDeudor; }
	 * 
	 * public Double getValorAdeudado() { return valorAdeudado; }
	 * 
	 * public void setValorAdeudado(Double valorAdeudado) { this.valorAdeudado =
	 * valorAdeudado; }
	 * 
	 * public Double getValorCancelado() { return valorCancelado; }
	 * 
	 * public void setValorCancelado(Double valorCancelado) { this.valorCancelado =
	 * valorCancelado; }
	 * 
	 * public Double getValorComision() { return valorComision; }
	 * 
	 * public void setValorComision(Double valorComision) { this.valorComision =
	 * valorComision; }
	 * 
	 * public String getNombreDeudor() { return nombreDeudor; }
	 * 
	 * public void setNombreDeudor(String nombreDeudor) { this.nombreDeudor =
	 * nombreDeudor; }
	 * 
	 * public Double getValorMinimoPago() { return valorMinimoPago; }
	 * 
	 * public void setValorMinimoPago(Double valorMinimoPago) { this.valorMinimoPago
	 * = valorMinimoPago; }
	 */

	public String toString() {
		return "ConsolidadoPagoServicio [valorAdeudado=" + valorAdeudado
				+ ", valorCancelado=" + valorCancelado + ", valorComision="
				+ valorComision + ", valorMinimoPago=" + valorMinimoPago
				+ ", nombreDeudor=" + nombreDeudor + "]";
	}
}
