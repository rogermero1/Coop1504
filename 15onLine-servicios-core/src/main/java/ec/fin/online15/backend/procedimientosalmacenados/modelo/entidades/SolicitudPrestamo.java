package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

public class SolicitudPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String preAprobado;
	private Double montoSolicitado;
	private Integer plazo;
	private String proposito;
	private Double tasa;
	private Double cuota;
	private Integer tipoAmortizacion;
	private String codigoError;
	private String estadosSituacion;

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getPreAprobado() {
		return preAprobado;
	}

	public void setPreAprobado(String preAprobado) {
		this.preAprobado = preAprobado;
	}

	public Double getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(Double montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	public Integer getTipoAmortizacion() {
		return tipoAmortizacion;
	}

	public void setTipoAmortizacion(Integer tipoAmortizacion) {
		this.tipoAmortizacion = tipoAmortizacion;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getEstadosSituacion() {
		return estadosSituacion;
	}

	public void setEstadosSituacion(String estadosSituacion) {
		this.estadosSituacion = estadosSituacion;
	}

}
