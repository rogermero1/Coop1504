package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.io.Serializable;

import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;

public class TransferenciaOtroBanco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double valor;
	private Integer tipoTransmision;
	private Integer conceptoOpi;
	private String localidadOrdenante;
	private Integer clienteOrdenante;
	private Integer tipoCuentaOrdenante;
	private Long cuentaOrdenante;
	// private Integer tipoFinanciera;
	// private Integer codigoFinanciera;
	private String financieraBce;
	private String identificacionBeneficiario;
	private String nombreBeneficiario;
	private Integer tipoCuentaDestino;
	private Long cuentaDestino;
	private String instruccionEspecial;
	private String usuario;
	private Long secuencia;
	private Canal canal;
	private String error;
	private String motivo;

	public TransferenciaOtroBanco() {
	}

	public TransferenciaOtroBanco(Double valor, Integer tipoTransmision, Integer conceptoOpi,
			String localidadOredenante, Integer clienteOrdenante, Integer tipoCuentaOrdenante, Long cuentaOrdenante, // Integer
																														// tipoFinanciera,
			// Integer codigoFinanciera,
			String financieraBce, String identificacionBeneficiario, String nombreBeneficiario,
			Integer tipoCuentaDestino, Long cuentaDestino, String instruccionEspecial, String usuario, Long secuencia,
			Canal canal, String error, String motivo) {
		super();
		this.valor = valor;
		this.tipoTransmision = tipoTransmision;
		this.conceptoOpi = conceptoOpi;
		this.localidadOrdenante = localidadOredenante;
		this.clienteOrdenante = clienteOrdenante;
		this.tipoCuentaOrdenante = tipoCuentaOrdenante;
		this.cuentaOrdenante = cuentaOrdenante;
		// this.tipoFinanciera = tipoFinanciera;
		// this.codigoFinanciera = codigoFinanciera;
		this.identificacionBeneficiario = identificacionBeneficiario;
		this.nombreBeneficiario = nombreBeneficiario;
		this.tipoCuentaDestino = tipoCuentaDestino;
		this.cuentaDestino = cuentaDestino;
		this.instruccionEspecial = instruccionEspecial;
		this.usuario = usuario;
		this.secuencia = secuencia;
		this.canal = canal;
		this.error = error;
		this.motivo = motivo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipoTransmision() {
		return tipoTransmision;
	}

	public void setTipoTransmision(Integer tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}

	public Integer getConceptoOpi() {
		return conceptoOpi;
	}

	public void setConceptoOpi(Integer conceptoOpi) {
		this.conceptoOpi = conceptoOpi;
	}

	public String getLocalidadOrdenante() {
		return localidadOrdenante;
	}

	public void setLocalidadOrdenante(String localidadOrdenante) {
		this.localidadOrdenante = localidadOrdenante;
	}

	public Integer getClienteOrdenante() {
		return clienteOrdenante;
	}

	public void setClienteOrdenante(Integer clienteOrdenante) {
		this.clienteOrdenante = clienteOrdenante;
	}

	public Integer getTipoCuentaOrdenante() {
		return tipoCuentaOrdenante;
	}

	public void setTipoCuentaOrdenante(Integer tipoCuentaOrdenante) {
		this.tipoCuentaOrdenante = tipoCuentaOrdenante;
	}

	public Long getCuentaOrdenante() {
		return cuentaOrdenante;
	}

	public void setCuentaOrdenante(Long cuetaOrdenante) {
		this.cuentaOrdenante = cuetaOrdenante;
	}

//	public Integer getTipoFinanciera() {
//		return tipoFinanciera;
//	}
//
//	public void setTipoFinanciera(Integer tipoFinanciera) {
//		this.tipoFinanciera = tipoFinanciera;
//	}
//
//	public Integer getCodigoFinanciera() {
//		return codigoFinanciera;
//	}
//
//	public void setCodigoFinanciera(Integer codigoFinanciera) {
//		this.codigoFinanciera = codigoFinanciera;
//	}

	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}

	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public Integer getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	public void setTipoCuentaDestino(Integer tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	public Long getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Long cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public String getInstruccionEspecial() {
		return instruccionEspecial;
	}

	public void setInstruccionEspecial(String instruccionEspecial) {
		this.instruccionEspecial = instruccionEspecial;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getFinancieraBce() {
		return financieraBce;
	}

	public void setFinancieraBce(String financieraBce) {
		this.financieraBce = financieraBce;
	}

	public String toString() {
		return "TransferenciaOtroBanco [valor=" + valor + ", tipoTransmision=" + tipoTransmision + ", conceptoOpi="
				+ conceptoOpi + ", localidadOrdenante=" + localidadOrdenante + ", clienteOrdenante=" + clienteOrdenante
				+ ", tipoCuentaOrdenante=" + tipoCuentaOrdenante + ", cuetaOrdenante=" + cuentaOrdenante
				// + ", tipoFinanciera=" + tipoFinanciera + ", codigoFinanciera=" +
				// codigoFinanciera
				+ ", financieraBce=" + financieraBce + ", identificacionBeneficiario=" + identificacionBeneficiario
				+ ", nombreBeneficiario=" + nombreBeneficiario + ", tipoCuentaDestino=" + tipoCuentaDestino
				+ ", cuentaDestino=" + cuentaDestino + ", instruccionEspecial=" + instruccionEspecial + ", usuario="
				+ usuario + ", secuencia=" + secuencia + ", error=" + error + ", motivo=" + motivo + "]";
	}
}
