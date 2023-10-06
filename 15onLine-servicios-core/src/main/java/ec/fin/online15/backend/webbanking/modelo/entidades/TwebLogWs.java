package ec.fin.online15.backend.webbanking.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_LOG_WS")
public class TwebLogWs extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String sesion;
	private String interfaz;
	private String operacion;
	private String wsdl;
	private String xmlRequest;
	private String xmlResponse;
	private Long tiempoRespuesta;

	@Id
	@SequenceGenerator(name = "TWEBLOGWS_ID_GENERATOR", sequenceName = "SWEB_ID_LOG_WS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEBLOGWS_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SESION", length = 500)
	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	@Column(name = "INTERFAZ", length = 500)
	public String getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(String interfaz) {
		this.interfaz = interfaz;
	}

	@Column(name = "OPERACION", length = 500)
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	@Column(name = "WSDL", length = 500)
	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	@Lob
	@Column(name = "XML_REQUEST")
	public String getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(String xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	@Lob
	@Column(name = "XML_RESPONSE")
	public String getXmlResponse() {
		return xmlResponse;
	}

	public void setXmlResponse(String xmlResponse) {
		this.xmlResponse = xmlResponse;
	}

	@Column(name = "TIEMPO_RESPUESTA")
	public Long getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public void setTiempoRespuesta(Long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}

}
