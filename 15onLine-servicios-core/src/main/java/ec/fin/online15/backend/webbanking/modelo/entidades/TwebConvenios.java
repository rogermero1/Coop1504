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
@Table(name = "TWEB_CONVENIOS")
@XmlRootElement
public class TwebConvenios extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codigoCliente;
	private String numeroIdentificacion;
	private String convenioWeb;
	private String nombreCliente;

	@Id
	@SequenceGenerator(name = "CONVENIOSTAB_ID_GENERATOR", sequenceName = "SWEB_ID_CONVENIOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONVENIOSTAB_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODIGO_CLIENTE", nullable = false, length = 10)
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Column(name = "NUMERO_IDENTIFICACION", nullable = false, length = 16)
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	@Column(name = "CONVENIO_WEB", nullable = false, length = 6)
	public String getConvenioWeb() {
		return convenioWeb;
	}

	public void setConvenioWeb(String convenioWeb) {
		this.convenioWeb = convenioWeb;
	}

	@Column(name = "NOMBRE_CLIENTE", length = 200)
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
