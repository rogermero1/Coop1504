package ec.fin.online15.backend.librerias.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public abstract class EntidadBasica<Id extends Serializable> implements
		EntidadReconocible<Id>, Serializable {

	private static final long serialVersionUID = 1L;

	private String estado;
	private String observacion;
	private String registradoPor;
	private String actualizadoPor;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String auditoria;

	@Column(name = "ESTADO", length = 1)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "OBSERVACION")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REGISTRO")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ACTUALIZACION")
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name = "AUDITORIA")
	public String getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
	}

	@Column(name = "REGISTRADO_POR", length = 20)
	public String getRegistradoPor() {
		return registradoPor;
	}

	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

	@Column(name = "ACTUALIZADO_POR", length = 20)
	public String getActualizadoPor() {
		return actualizadoPor;
	}

	public void setActualizadoPor(String actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntidadBasica)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		EntidadBasica<Id> other = (EntidadBasica<Id>) obj;
		if (this.getId() == null) {
			return false;
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

}
