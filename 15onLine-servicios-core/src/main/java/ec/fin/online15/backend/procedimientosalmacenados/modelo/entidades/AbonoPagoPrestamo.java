package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

public class AbonoPagoPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String referencia;
	private Long numeroCuenta;
	private Long numeroPrestamo;
	private Double valorPago;
	private String codigoUsuario;
	private Long secuenciaPago;
	private String codigoError;
	private Canal canal;

	public AbonoPagoPrestamo() {
	}

	public AbonoPagoPrestamo(Integer codigoCliente, String referencia, Long numeroCuenta, Long numeroPrestamo,
			Double valorPago, String codigoUsuario, Long secuenciaPago, Canal canal, String codigoError) {
		super();
		this.codigoCliente = codigoCliente;
		this.referencia = referencia;
		this.numeroCuenta = numeroCuenta;
		this.numeroPrestamo = numeroPrestamo;
		this.valorPago = valorPago;
		this.codigoUsuario = codigoUsuario;
		this.secuenciaPago = secuenciaPago;
		this.codigoError = codigoError;
		this.canal = canal;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(Long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Long getSecuenciaPago() {
		return secuenciaPago;
	}

	public void setSecuenciaPago(Long secuenciaPago) {
		this.secuenciaPago = secuenciaPago;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public String toString() {
		return "AbonoPagoPrestamo [codigoCliente=" + codigoCliente + ", referencia=" + referencia + ", numeroCuenta="
				+ numeroCuenta + ", numeroPrestamo=" + numeroPrestamo + ", valorPago=" + valorPago + ", codigoUsuario="
				+ codigoUsuario + ", secuenciaPago=" + secuenciaPago + ", codigoError=" + codigoError + "]";
	}
}
