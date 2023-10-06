package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

public class TransferenciaLocal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cuentaDebitar;
	private Double valorDebito;
	private String descripcion;
	private String codigoAplicacionDebito;
	private String codigoAplicacionCredito;
	private Long cuentaAcreditar;
	private Long secuencia;
	private Canal canal;
	private String codigoError;

	public TransferenciaLocal() {
	}

	public TransferenciaLocal(Long cuentaDebitar, Double valorDebito, String descripcion, String codigoAplicacionDebito,
			String codigoAplicacionCredito, Long cuentaAcreditar, Long secuencia, Canal canal, String codigoError) {
		super();
		this.cuentaDebitar = cuentaDebitar;
		this.valorDebito = valorDebito;
		this.descripcion = descripcion;
		this.codigoAplicacionDebito = codigoAplicacionDebito;
		this.codigoAplicacionCredito = codigoAplicacionCredito;
		this.cuentaAcreditar = cuentaAcreditar;
		this.secuencia = secuencia;
		this.canal = canal;
		this.codigoError = codigoError;
	}

	public Long getCuentaDebitar() {
		return cuentaDebitar;
	}

	public void setCuentaDebitar(Long cuentaDebitar) {
		this.cuentaDebitar = cuentaDebitar;
	}

	public Double getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(Double valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoAplicacionDebito() {
		return codigoAplicacionDebito;
	}

	public void setCodigoAplicacionDebito(String codigoAplicacionDebito) {
		this.codigoAplicacionDebito = codigoAplicacionDebito;
	}

	public String getCodigoAplicacionCredito() {
		return codigoAplicacionCredito;
	}

	public void setCodigoAplicacionCredito(String codigoAplicacionCredito) {
		this.codigoAplicacionCredito = codigoAplicacionCredito;
	}

	public Long getCuentaAcreditar() {
		return cuentaAcreditar;
	}

	public void setCuentaAcreditar(Long cuentaAcreditar) {
		this.cuentaAcreditar = cuentaAcreditar;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
}
