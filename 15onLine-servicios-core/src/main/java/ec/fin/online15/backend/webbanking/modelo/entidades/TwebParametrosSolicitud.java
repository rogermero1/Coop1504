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

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_PARAMETROS_SOLICITUDES")
@XmlRootElement
public class TwebParametrosSolicitud extends EntidadBasica<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String relacionDependencia;
	private String preAprobado;
	private Double montoDesde;
	private Double montoHasta;
	private Double tasa;

	@Id
	@SequenceGenerator(name = "TWEBPARAMETROSSOLICITUD_ID_GENERATOR", sequenceName = "SWEB_ID_PARAMETROS_SOLICITUDES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBPARAMETROSSOLICITUD_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "RELACION_DEPENDENCIA", length = 1, nullable = false)
	public String getRelacionDependencia() {
		return relacionDependencia;
	}

	public void setRelacionDependencia(String relacionDependencia) {
		this.relacionDependencia = relacionDependencia;
	}

	@Column(name = "PREAPROBADO", length = 1, nullable = false)
	public String getPreAprobado() {
		return preAprobado;
	}

	public void setPreAprobado(String preAprobado) {
		this.preAprobado = preAprobado;
	}

	@Column(name = "TASA", nullable = false)
	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	@Column(name = "MONTO_DESDE", nullable = false)
	public Double getMontoDesde() {
		return montoDesde;
	}

	public void setMontoDesde(Double montoDesde) {
		this.montoDesde = montoDesde;
	}

	@Column(name = "MONTO_HASTA", nullable = false)
	public Double getMontoHasta() {
		return montoHasta;
	}

	public void setMontoHasta(Double montoHasta) {
		this.montoHasta = montoHasta;
	}

}
