package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_MOVIMIENTOS")
@XmlRootElement
public class TwebMovimientos extends EntidadBasica<Long> implements Serializable {

	// TIPOS DE TRANSACCION
	// 1 Transferencia Cuentas Locales
	// 2 Transferencia Otros Bancos
	// 3 Pago de tarjeta de credito
	// 4 Pago de prestamo
	// 5 Pago servicio

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long idUsuario;
	private String codigoVerificador;
	private Integer idTipoTransaccion;
	private Date fechaValida;
	private Date fechaHora;
	private String usuario;
	private String ipTransaccion;
	private Double valor;
	private String numeroCuentaOrigen;
	private String numeroCuentaDestino;
	private String referencia;
	private String identificacionBeneficiario;
	private String nombreBeneficiario;
	private String tipoCuenta;
	private String esLocal;
	private Integer idTipoFinanciera;
	private Integer codigoEntidadFinanciera;
	private Long numeroPrestamo;
	private Integer idTipoServicio;
	private String numeroServicio;
	private Canal canal;

	public TwebMovimientos() {
	}

	@Id
	@SequenceGenerator(name = "MOVIMIENTOSTAB_ID_MOVIMIENTO", sequenceName = "SWEB_ID_MOVIMIENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIMIENTOSTAB_ID_MOVIMIENTO")
	@Column(name = "ID_MOVIMIENTO")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ID_USUARIO")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "CODIGO_VERIFICADOR")
	public String getCodigoVerificador() {
		return codigoVerificador;
	}

	public void setCodigoVerificador(String codigoVerificador) {
		this.codigoVerificador = codigoVerificador;
	}

	@Column(name = "ID_TIPO_TRANSACCION")
	public Integer getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	public void setIdTipoTransaccion(Integer idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_VALIDA")
	public Date getFechaValida() {
		return fechaValida;
	}

	public void setFechaValida(Date fechaValida) {
		this.fechaValida = fechaValida;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Column(name = "USUARIO")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "IP_TRANSACCION")
	public String getIpTransaccion() {
		return ipTransaccion;
	}

	public void setIpTransaccion(String ipTransaccion) {
		this.ipTransaccion = ipTransaccion;
	}

	@Column(name = "VALOR")
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "NUMERO_CUENTA_ORIGEN")
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	@Column(name = "NUMERO_CUENTA_DESTINO")
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	@Column(name = "REFERENCIA")
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Column(name = "IDENTIFICACION_BENEFICIARIO")
	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}

	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}

	@Column(name = "NOMBRE_BENEFICIARIO")
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	@Column(name = "TIPO_CUENTA")
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	@Column(name = "ES_LOCAL")
	public String getEsLocal() {
		return esLocal;
	}

	public void setEsLocal(String esLocal) {
		this.esLocal = esLocal;
	}

	@Column(name = "ID_ENTIDAD_FINANCIERA", length = 3)
	public Integer getCodigoEntidadFinanciera() {
		return codigoEntidadFinanciera;
	}

	public void setCodigoEntidadFinanciera(Integer codigoEntidadFinanciera) {
		this.codigoEntidadFinanciera = codigoEntidadFinanciera;
	}

	@Column(name = "NUMERO_PRESTAMO", length = 10)
	public Long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(Long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	@Column(name = "ID_TIPO_SERVICIO", length = 5)
	public Integer getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(Integer idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	@Column(name = "NUMERO_SERVICIO", length = 100)
	public String getNumeroServicio() {
		return numeroServicio;
	}

	public void setNumeroServicio(String numeroServicio) {
		this.numeroServicio = numeroServicio;
	}

	@Column(name = "ID_TIPO_FINANCIERA", length = 2)
	public Integer getIdTipoFinanciera() {
		return idTipoFinanciera;
	}

	public void setIdTipoFinanciera(Integer idTipoFinanciera) {
		this.idTipoFinanciera = idTipoFinanciera;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CANAL", nullable = false, length = 3)
	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

}
