package ec.fin.online15.backend.consultas.modelo.entidades.clientes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaSecreta implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idPregunta;
	private Integer codigoCliente;
	private String pregunta;
	private String respuesta;
	private String correo;
	private String celular;
	private String bloqueado;
	/*
	 * public PreguntaSecreta() { }
	 * 
	 * public PreguntaSecreta(Integer idPregunta, Integer codigoCliente, String
	 * pregunta, String respuesta, String correo, String celular, String bloqueado)
	 * { super(); this.idPregunta = idPregunta; this.codigoCliente = codigoCliente;
	 * this.pregunta = pregunta; this.respuesta = respuesta; this.correo = correo;
	 * this.celular = celular; this.bloqueado = bloqueado; }
	 * 
	 * public PreguntaSecreta(Integer codigoCliente, String pregunta, String
	 * respuesta, String correo, String celular, String bloqueado) { super();
	 * this.codigoCliente = codigoCliente; this.pregunta = pregunta; this.respuesta
	 * = respuesta; this.correo = correo; this.celular = celular; this.bloqueado =
	 * bloqueado; }
	 * 
	 * public Integer getIdPregunta() { return idPregunta; }
	 * 
	 * public void setIdPregunta(Integer idPregunta) { this.idPregunta = idPregunta;
	 * }
	 * 
	 * public String getPregunta() { return pregunta; }
	 * 
	 * public void setPregunta(String pregunta) { this.pregunta = pregunta; }
	 * 
	 * public String getRespuesta() { return respuesta; }
	 * 
	 * public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
	 * 
	 * public String getCorreo() { return correo; }
	 * 
	 * public void setCorreo(String correo) { this.correo = correo; }
	 * 
	 * public String getCelular() { return celular; }
	 * 
	 * public void setCelular(String celular) { this.celular = celular; }
	 * 
	 * public Integer getCodigoCliente() { return codigoCliente; }
	 * 
	 * public void setCodigoCliente(Integer codigoCliente) { this.codigoCliente =
	 * codigoCliente; }
	 * 
	 * public String getBloqueado() { return bloqueado; }
	 * 
	 * public void setBloqueado(String bloqueado) { this.bloqueado = bloqueado; }
	 */

	@Override
	public String toString() {
		return "PreguntaSecreta [idPregunta=" + idPregunta + ", codigoCliente="
				+ codigoCliente + ", pregunta=" + pregunta + ", respuesta="
				+ respuesta + ", correo=" + correo + ", celular=" + celular
				+ "]";
	}
}
