package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;
import java.util.Date;

/*import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/
public class DatosPagoServicio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer codigoTipoServicio;
	private String numeroIdentificacion;
	private String numeroServicio;
	private Double valorAdeudado;
	private Double valorCancelado;
	private Date fechaCorte;
	private String mes;
	private String anio;
	private String secuenciaEmpresa;
	private String pagado;
	private Double cargoMinimo;
	private boolean estadoPago;
	private String descripcionServicio;
	private String maestroDetalle;
	private Double valorMinimoPago;
	
	  public DatosPagoServicio() { }
	  
	  public DatosPagoServicio(Integer id, Integer codigoTipoServicio, String
	  numeroIdentificacion, String numeroServicio, Double valorAdeudado, Double
	  valorCancelado, Date fechaCorte, String mes, String anio, String
	  secuenciaEmpresa, String pagado, Double cargoMinimo, boolean estadoPago,
	  String descripcionServicio, String maestroDetalle, Double valorMinimoPago) {
	  super(); this.id = id; this.codigoTipoServicio = codigoTipoServicio;
	  this.numeroIdentificacion = numeroIdentificacion; this.numeroServicio =
	  numeroServicio; this.valorAdeudado = valorAdeudado; this.valorCancelado =
	  valorCancelado; this.fechaCorte = fechaCorte; this.mes = mes; this.anio =
	  anio; this.secuenciaEmpresa = secuenciaEmpresa; this.pagado = pagado;
	  this.cargoMinimo = cargoMinimo; this.estadoPago = estadoPago;
	  this.descripcionServicio = descripcionServicio; this.maestroDetalle =
	  maestroDetalle; this.valorMinimoPago = valorMinimoPago; }
	  
	  public Integer getId() { return id; }
	  
	  public void setId(Integer id) { this.id = id; }
	  
	  public Integer getCodigoTipoServicio() { return codigoTipoServicio; }
	  
	  public void setCodigoTipoServicio(Integer codigoTipoServicio) {
	  this.codigoTipoServicio = codigoTipoServicio; }
	  
	  public String getNumeroIdentificacion() { return numeroIdentificacion; }
	  
	  public void setNumeroIdentificacion(String numeroIdentificacion) {
	  this.numeroIdentificacion = numeroIdentificacion; }
	  
	  public String getNumeroServicio() { return numeroServicio; }
	  
	  public void setNumeroServicio(String numeroServicio) { this.numeroServicio =
	  numeroServicio; }
	  
	  public Double getValorAdeudado() { return valorAdeudado; }
	  
	  public void setValorAdeudado(Double valorAdeudado) { this.valorAdeudado =
	  valorAdeudado; }
	  
	  public Double getValorCancelado() { return valorCancelado; }
	  
	  public void setValorCancelado(Double valorCancelado) { this.valorCancelado =
	  valorCancelado; }
	  
	  public Date getFechaCorte() { return fechaCorte; }
	  
	  public void setFechaCorte(Date fechaCorte) { this.fechaCorte = fechaCorte; }
	  
	  public String getMes() { return mes; }
	  
	  public void setMes(String mes) { this.mes = mes; }
	  
	  public String getAnio() { return anio; }
	  
	  public void setAnio(String anio) { this.anio = anio; }
	  
	  public String getSecuenciaEmpresa() { return secuenciaEmpresa; }
	  
	  public void setSecuenciaEmpresa(String secuenciaEmpresa) {
	  this.secuenciaEmpresa = secuenciaEmpresa; }
	  
	  public String getPagado() { return pagado; }
	  
	  public void setPagado(String pagado) { this.pagado = pagado; }
	  
	  public Double getCargoMinimo() { return cargoMinimo; }
	  
	  public void setCargoMinimo(Double cargoMinimo) { this.cargoMinimo =
	  cargoMinimo; }
	  
	  public boolean getEstadoPago() { return estadoPago; }
	  
	  public void setEstadoPago(boolean estadoPago) { this.estadoPago = estadoPago;
	  }
	  
	  public String getDescripcionServicio() { return descripcionServicio; }
	  
	  public void setDescripcionServicio(String descripcionServicio) {
	  this.descripcionServicio = descripcionServicio; }
	  
	  public String getMaestroDetalle() { return maestroDetalle; }
	  
	  public void setMaestroDetalle(String maestroDetalle) { this.maestroDetalle =
	  maestroDetalle; }
	  
	  public Double getValorMinimoPago() { return valorMinimoPago; }
	  
	  public void setValorMinimoPago(Double valorMinimoPago) { this.valorMinimoPago
	  = valorMinimoPago; }
	 

	public String toString() {
		return "DatosPagoServicio [id=" + id + ", codigoTipoServicio="
				+ codigoTipoServicio + ", numeroIdentificacion="
				+ numeroIdentificacion + ", numeroServicio=" + numeroServicio
				+ ", valorAdeudado=" + valorAdeudado + ", valorCancelado="
				+ valorCancelado + ", fechaCorte=" + fechaCorte + ", mes="
				+ mes + ", anio=" + anio + ", secuenciaEmpresa="
				+ secuenciaEmpresa + ", pagado=" + pagado + ", cargoMinimo="
				+ cargoMinimo + ", estadoPago=" + estadoPago
				+ ", descripcionServicio=" + descripcionServicio
				+ ", maestroDetalle=" + maestroDetalle + ", valorMinimoPago="
				+ valorMinimoPago + "]";
	}
}
