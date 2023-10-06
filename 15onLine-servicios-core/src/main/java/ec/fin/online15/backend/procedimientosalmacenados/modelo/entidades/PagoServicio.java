package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

public class PagoServicio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoTipoServicio;
	private String numeroServicio;
	private String secuenciaEmpresa;
	private Double valor;
	private Long numeroCuenta;
	private String numeroIdentificacion;
	private String error;
	private String cobraComision;

	public PagoServicio() {
	}

	public Integer getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	public void setCodigoTipoServicio(Integer codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}

	public String getNumeroServicio() {
		return numeroServicio;
	}

	public void setNumeroServicio(String numeroServicio) {
		this.numeroServicio = numeroServicio;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSecuenciaEmpresa() {
		return secuenciaEmpresa;
	}

	public void setSecuenciaEmpresa(String secuenciaEmpresa) {
		this.secuenciaEmpresa = secuenciaEmpresa;
	}

	public String getCobraComision() {
		return cobraComision;
	}

	public void setCobraComision(String cobraComision) {
		this.cobraComision = cobraComision;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String toString() {
		return "PagoServicio [codigoTipoServicio=" + codigoTipoServicio
				+ ", numeroServicio=" + numeroServicio + ", secuenciaEmpresa="
				+ secuenciaEmpresa + ", valor=" + valor + ", numeroCuenta="
				+ numeroCuenta + ", numeroIdentificacion="
				+ numeroIdentificacion + ", error=" + error
				+ ", cobraComision=" + cobraComision + "]";
	}
}
