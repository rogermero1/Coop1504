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
public class SaldosReserva implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cuentaGiradora;
	private String financiera;
	private String procedencia;
	private Date fechaReserva;
	private Integer diasReserva;
	private Double valor;

	/*
	 * public String getCuentaGiradora() { return cuentaGiradora; }
	 * 
	 * public void setCuentaGiradora(String cuentaGiradora) { this.cuentaGiradora =
	 * cuentaGiradora; }
	 * 
	 * public String getFinanciera() { return financiera; }
	 * 
	 * public void setFinanciera(String financiera) { this.financiera = financiera;
	 * }
	 * 
	 * public String getProcedencia() { return procedencia; }
	 * 
	 * public void setProcedencia(String procedencia) { this.procedencia =
	 * procedencia; }
	 * 
	 * public Date getFechaReserva() { return fechaReserva; }
	 * 
	 * public void setFechaReserva(Date fechaReserva) { this.fechaReserva =
	 * fechaReserva; }
	 * 
	 * public Integer getDiasReserva() { return diasReserva; }
	 * 
	 * public void setDiasReserva(Integer diasReserva) { this.diasReserva =
	 * diasReserva; }
	 * 
	 * public Double getValor() { return valor; }
	 * 
	 * public void setValor(Double valor) { this.valor = valor; }
	 */

}
