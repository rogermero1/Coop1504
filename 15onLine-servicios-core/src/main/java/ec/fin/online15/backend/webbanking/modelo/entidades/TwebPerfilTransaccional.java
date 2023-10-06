package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_PERFIL_TRANSACCIONAL")
@XmlRootElement
public class TwebPerfilTransaccional extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer idCanalElectronico;
	private Integer codigoCliente;
	private Integer transaccionDiaria;
	private Double montoDiario;
	private Integer transaccionSemanal;
	private Double montoSemanal;
	private Integer transaccionesMensual;
	private Double montoMensual;
	private Double montoMinimoTransaccion;
	private Double montoMaximoTransaccion;

	@Id
	@SequenceGenerator(name = "ID_PERFIL", sequenceName = "SWEB_ID_PERFIL_TRANSACCIONAL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PERFIL")
	@Column(name = "ID_PEFIL_TRANSACCIONAL", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ID_CANAL_ELECTRONICO")
	public Integer getIdCanalElectronico() {
		return idCanalElectronico;
	}

	public void setIdCanalElectronico(Integer idCanalElectronico) {
		this.idCanalElectronico = idCanalElectronico;
	}

	@Column(name = "CODIGO_CLIENTE")
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Column(name = "TRANSACCION_DIARIA")
	public Integer getTransaccionDiaria() {
		return transaccionDiaria;
	}

	public void setTransaccionDiaria(Integer transaccionDiaria) {
		this.transaccionDiaria = transaccionDiaria;
	}

	@Column(name = "MONTO_DIARIO")
	public Double getMontoDiario() {
		return montoDiario;
	}

	public void setMontoDiario(Double montoDiario) {
		this.montoDiario = montoDiario;
	}

	@Column(name = "TRANSACCION_SEMANAL")
	public Integer getTransaccionSemanal() {
		return transaccionSemanal;
	}

	public void setTransaccionSemanal(Integer transaccionSemanal) {
		this.transaccionSemanal = transaccionSemanal;
	}

	@Column(name = "MONTO_SEMANAL")
	public Double getMontoSemanal() {
		return montoSemanal;
	}

	public void setMontoSemanal(Double montoSemanal) {
		this.montoSemanal = montoSemanal;
	}

	@Column(name = "TRANSACCION_MENSUAL")
	public Integer getTransaccionesMensual() {
		return transaccionesMensual;
	}

	public void setTransaccionesMensual(Integer transaccionesMensual) {
		this.transaccionesMensual = transaccionesMensual;
	}

	@Column(name = "MONTO_MENSUAL")
	public Double getMontoMensual() {
		return montoMensual;
	}

	public void setMontoMensual(Double montoMensual) {
		this.montoMensual = montoMensual;
	}

	@Column(name = "MONTO_MINIMO_TRANSACCION")
	public Double getMontoMinimoTransaccion() {
		return montoMinimoTransaccion;
	}

	public void setMontoMinimoTransaccion(Double montoMinimoTransaccion) {
		this.montoMinimoTransaccion = montoMinimoTransaccion;
	}

	@Column(name = "MONTO_MAXIMO_TRANSACCION")
	public Double getMontoMaximoTransaccion() {
		return montoMaximoTransaccion;
	}

	public void setMontoMaximoTransaccion(Double montoMaximoTransaccion) {
		this.montoMaximoTransaccion = montoMaximoTransaccion;
	}

	public String toString() {
		return "TwebPerfilTransaccional [id=" + id + ", idCanalElectronico="
				+ idCanalElectronico + ", codigoCliente=" + codigoCliente
				+ ", transaccionDiaria=" + transaccionDiaria + ", montoDiario="
				+ montoDiario + ", transaccionSemanal=" + transaccionSemanal
				+ ", montoSemanal=" + montoSemanal + ", transaccionesMensual="
				+ transaccionesMensual + ", montoMensual=" + montoMensual
				+ ", montoMinimoTransaccion=" + montoMinimoTransaccion
				+ ", montoMaximoTransaccion=" + montoMaximoTransaccion + "]";
	}
}
