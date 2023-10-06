package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

public class Certificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private Integer tipoCertificacion;
	private Long numeroCuenta;
	private String correoPrincipal;
	private String correoSecundario;
	private Long secuencia;
	private Canal canal;
	private String codigoError;

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getTipoCertificacion() {
		return tipoCertificacion;
	}

	public void setTipoCertificacion(Integer tipoCertificacion) {
		this.tipoCertificacion = tipoCertificacion;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCorreoPrincipal() {
		return correoPrincipal;
	}

	public void setCorreoPrincipal(String correoPrincipal) {
		this.correoPrincipal = correoPrincipal;
	}

	public String getCorreoSecundario() {
		return correoSecundario;
	}

	public void setCorreoSecundario(String correoSecundario) {
		this.correoSecundario = correoSecundario;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
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

}
