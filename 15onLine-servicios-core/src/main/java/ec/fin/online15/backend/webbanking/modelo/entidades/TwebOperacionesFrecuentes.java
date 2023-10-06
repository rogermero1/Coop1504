package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebTiposTransaccion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Entity
@Table(name = "TWEB_OPERACION_FRECUENTE")
@XmlRootElement
public class TwebOperacionesFrecuentes extends EntidadBasica<Long> implements
		Serializable {

	// TIPOS DE TRANSACCION
	// 1 Transferencia Cuentas Locales
	// 2 Transferencia Otros Bancos
	// 3 Pago de tarjeta de credito
	// 4 Pago de prestamo
	// 5 Pago servicio

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long cuentaDebito;
	private Long cuentaCredito;
	private String identificacionBeneficiario;
	private String nombreBeneficiario;
	private String financiera;
	private Double monto;
	private String tipoCuentaBeneficiaria;
	private TwebTiposTransaccion tipoTransaccion;
	private TwebUsuario usuario;

	@Id
	@SequenceGenerator(name = "TWEBOPERACIONFRECUENTE_ID_GENERATOR", sequenceName = "SWEB_ID_OPERACIONES_FRECUENTES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBOPERACIONFRECUENTE_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CUENTA_DEBITO")
	public Long getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(Long cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	@Column(name = "CUENTA_CREDITO")
	public Long getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(Long cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	@Column(name = "IDENTIFICACION_BENEFICIARIO", length = 20)
	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}

	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}

	@Column(name = "NOMBRE_BENEFICIARIO", length = 200)
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	@Column(name = "FINANCIERA", length = 10)
	public String getFinanciera() {
		return financiera;
	}

	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}

	@Column(name = "MONTO")
	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Column(name = "TIPO_CUENTA_BENEFICIARIA", length = 2)
	public String getTipoCuentaBeneficiaria() {
		return tipoCuentaBeneficiaria;
	}

	public void setTipoCuentaBeneficiaria(String tipoCuentaBeneficiaria) {
		this.tipoCuentaBeneficiaria = tipoCuentaBeneficiaria;
	}

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_TRANSACCION")
	public TwebTiposTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TwebTiposTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public TwebUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TwebUsuario usuario) {
		this.usuario = usuario;
	}

}
