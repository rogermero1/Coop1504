package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;
import java.util.Date;

public class PosicionConsolidadaPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoLineaFinanciera;
	private String descripcionLineaFinanciera;
	private Integer codigoMoneda;
	private String abreviaturaMoneda;
	private Long numeroPrestamo;
	private Double montoInicial;
	private Integer cuotasPendientes;
	private Double saldoCapital;
	private Date fechaApertura;
	private Date fechaVencimiento;
	private Double saldoOtros;
	private Integer plazo;
	private Double tasa;
	private String estado;
	private Double valorCuota;
	private String periodoGracia;

	public PosicionConsolidadaPrestamo() {
	}

	public PosicionConsolidadaPrestamo(String codigoLineaFinanciera,
			String descripcionLineaFinanciera, Integer codigoMoneda,
			String abreviaturaMoneda, Long numeroPrestamo, Double montoInicial,
			Integer cuotasPendientes, Double saldoCapital, Date fechaApertura,
			Date fechaVencimiento, Double saldoOtros, Integer plazo,
			Double tasa, String estado, Double valorCuota) {
		super();
		this.codigoLineaFinanciera = codigoLineaFinanciera;
		this.descripcionLineaFinanciera = descripcionLineaFinanciera;
		this.codigoMoneda = codigoMoneda;
		this.abreviaturaMoneda = abreviaturaMoneda;
		this.numeroPrestamo = numeroPrestamo;
		this.montoInicial = montoInicial;
		this.cuotasPendientes = cuotasPendientes;
		this.saldoCapital = saldoCapital;
		this.fechaApertura = fechaApertura;
		this.fechaVencimiento = fechaVencimiento;
		this.saldoOtros = saldoOtros;
		this.plazo = plazo;
		this.tasa = tasa;
		this.estado = estado;
		this.valorCuota = valorCuota;
	}

	public String getCodigoLineaFinanciera() {
		return codigoLineaFinanciera;
	}

	public void setCodigoLineaFinanciera(String codigoLineaFinanciera) {
		this.codigoLineaFinanciera = codigoLineaFinanciera;
	}

	public String getDescripcionLineaFinanciera() {
		return descripcionLineaFinanciera;
	}

	public void setDescripcionLineaFinanciera(String descripcionLineaFinanciera) {
		this.descripcionLineaFinanciera = descripcionLineaFinanciera;
	}

	public Integer getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(Integer codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getAbreviaturaMoneda() {
		return abreviaturaMoneda;
	}

	public void setAbreviaturaMoneda(String abreviaturaMoneda) {
		this.abreviaturaMoneda = abreviaturaMoneda;
	}

	public Long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(Long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public Double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public Integer getCuotasPendientes() {
		return cuotasPendientes;
	}

	public void setCuotasPendientes(Integer cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	public Double getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(Double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getSaldoOtros() {
		return saldoOtros;
	}

	public void setSaldoOtros(Double saldoOtros) {
		this.saldoOtros = saldoOtros;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(Double valorCuota) {
		this.valorCuota = valorCuota;
	}

	public String getPeriodoGracia() {
		return periodoGracia;
	}

	public void setPeriodoGracia(String periodoGracia) {
		this.periodoGracia = periodoGracia;
	}

}
