package ec.fin.online15.integracion.dto;

import java.io.Serializable;

public class PreguntasRespuestas implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idPregunta;
	private String pregunta;
	private String respuesta;

	public PreguntasRespuestas() {

	}

	public PreguntasRespuestas(Long idPregunta, String pregunta,
			String respuesta) {
		this.idPregunta = idPregunta;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}

	public PreguntasRespuestas(String pregunta, String respuesta) {
		super();
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
