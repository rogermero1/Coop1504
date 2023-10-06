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
public class CuotasPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroCuota;
	private Date fechaVence;
	private String pagada;
	private Date fechaPago;
	private Double valorCuota;
	private Double saldoCuota;
	private long diasVencido;
	private String descripcionCuota;

	private Double capital;
	private Double interes;
	private Double interesGracia;
	/*
	 * public CuotasPrestamo() { }
	 * 
	 * public CuotasPrestamo(Integer numeroCuota, Date fechaVence, String pagada,
	 * Date fechaPago, Double valorCuota, Double saldoCuota, Integer diasVencido,
	 * String descripcionCuota) { super(); this.numeroCuota = numeroCuota;
	 * this.fechaVence = fechaVence; this.pagada = pagada; this.fechaPago =
	 * fechaPago; this.valorCuota = valorCuota; this.saldoCuota = saldoCuota;
	 * this.diasVencido = diasVencido; this.descripcionCuota = descripcionCuota; }
	 * 
	 * public CuotasPrestamo(Integer numeroCuota, Date fechaVence, Double capital,
	 * Double interes, Double interesGracia, Double valorCuota) { super();
	 * this.numeroCuota = numeroCuota; this.fechaVence = fechaVence; this.capital =
	 * capital; this.interes = interes; this.interesGracia = interesGracia;
	 * this.valorCuota = valorCuota; ; }
	 * 
	 * public Integer getNumeroCuota() { return numeroCuota; }
	 * 
	 * public void setNumeroCuota(Integer numeroCuota) { this.numeroCuota =
	 * numeroCuota; }
	 * 
	 * public Date getFechaVence() { return fechaVence; }
	 * 
	 * public void setFechaVence(Date fechaVence) { this.fechaVence = fechaVence; }
	 * 
	 * public String getPagada() { return pagada; }
	 * 
	 * public void setPagada(String pagada) { this.pagada = pagada; }
	 * 
	 * public Date getFechaPago() { return fechaPago; }
	 * 
	 * public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
	 * 
	 * public Double getValorCuota() { return valorCuota; }
	 * 
	 * public void setValorCuota(Double valorCuota) { this.valorCuota = valorCuota;
	 * }
	 * 
	 * public Double getSaldoCuota() { return saldoCuota; }
	 * 
	 * public void setSaldoCuota(Double saldoCuota) { this.saldoCuota = saldoCuota;
	 * }
	 * 
	 * public long getDiasVencido() { return diasVencido; }
	 * 
	 * public void setDiasVencido(long diasVencido) { this.diasVencido =
	 * diasVencido; }
	 * 
	 * public String getDescripcionCuota() { return descripcionCuota; }
	 * 
	 * public void setDescripcionCuota(String descripcionCuota) {
	 * this.descripcionCuota = descripcionCuota; }
	 * 
	 * public Double getCapital() { return capital; }
	 * 
	 * public void setCapital(Double capital) { this.capital = capital; }
	 * 
	 * public Double getInteres() { return interes; }
	 * 
	 * public void setInteres(Double interes) { this.interes = interes; }
	 * 
	 * public Double getInteresGracia() { return interesGracia; }
	 * 
	 * public void setInteresGracia(Double interesGracia) { this.interesGracia =
	 * interesGracia; }
	 */

	@Override
	public String toString() {
		return "CuotasPrestamo [numeroCuota=" + numeroCuota + ", fechaVence="
				+ fechaVence + ", pagada=" + pagada + ", fechaPago="
				+ fechaPago + ", valorCuota=" + valorCuota + ", saldoCuota="
				+ saldoCuota + ", diasVencido=" + diasVencido
				+ ", descripcionCuota=" + descripcionCuota + "]";
	}

}
