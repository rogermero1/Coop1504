package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

public class AutorizaTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoCliente;
	private Double monto;
	private Integer idTipoCanal;
	private String verifica;
	private String actualiza;
	private String estado;
	private String error;

	public AutorizaTransaccion() {
	}

	public AutorizaTransaccion(Integer codigoCliente, Double monto,
			Integer idTipoCanal, String verifica, String actualiza,
			String estado, String error) {
		super();
		this.codigoCliente = codigoCliente;
		this.monto = monto;
		this.idTipoCanal = idTipoCanal;
		this.verifica = verifica;
		this.actualiza = actualiza;
		this.estado = estado;
		this.error = error;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Integer getIdTipoCanal() {
		return idTipoCanal;
	}

	public void setIdTipoCanal(Integer idTipoCanal) {
		this.idTipoCanal = idTipoCanal;
	}

	public String getVerifica() {
		return verifica;
	}

	public void setVerifica(String verifica) {
		this.verifica = verifica;
	}

	public String getActualiza() {
		return actualiza;
	}

	public void setActualiza(String actualiza) {
		this.actualiza = actualiza;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String toString() {
		return "AutorizaTransaccion [codigoCliente=" + codigoCliente
				+ ", monto=" + monto + ", idTipoCanal=" + idTipoCanal
				+ ", verifica=" + verifica + ", actualiza=" + actualiza
				+ ", estado=" + estado + ", error=" + error + "]";
	}
}
