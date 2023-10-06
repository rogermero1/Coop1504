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
public class SaldosBloqueados implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fecha;
	private String motivo;
	private Double valor;

	/*
	 * public SaldosBloqueados() { }
	 * 
	 * public Date getFecha() { return fecha; }
	 * 
	 * public void setFecha(Date fecha) { this.fecha = fecha; }
	 * 
	 * public String getMotivo() { return motivo; }
	 * 
	 * public void setMotivo(String motivo) { this.motivo = motivo; }
	 * 
	 * public Double getValor() { return valor; }
	 * 
	 * public void setValor(Double valor) { this.valor = valor; }
	 */

}
