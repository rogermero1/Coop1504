package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String identificacion;
	private String nombres;
	private String estadoCivil;
	private String identificacionConyuge;
	private String nombreConyuge;
	private String fechaNacimiento;
	private String relacionDependencia;

	/*
	 * public DatosCliente() { }
	 * 
	 * public DatosCliente(String identificacion, String nombres, String
	 * estadoCivil, String identificacionConyuge, String nombreConyuge, String
	 * fechaNacimiento, String relacionDependencia) { this.identificacion =
	 * identificacion; this.nombres = nombres; this.estadoCivil = estadoCivil;
	 * this.identificacionConyuge = identificacionConyuge; this.nombreConyuge =
	 * nombreConyuge; this.fechaNacimiento = fechaNacimiento;
	 * this.relacionDependencia = relacionDependencia; }
	 * 
	 * public String getFechaNacimiento() { return fechaNacimiento; }
	 * 
	 * public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento
	 * = fechaNacimiento; }
	 * 
	 * public String getIdentificacion() { return identificacion; }
	 * 
	 * public void setIdentificacion(String identificacion) { this.identificacion =
	 * identificacion; }
	 * 
	 * public String getNombres() { return nombres; }
	 * 
	 * public void setNombres(String nombres) { this.nombres = nombres; }
	 * 
	 * public String getEstadoCivil() { return estadoCivil; }
	 * 
	 * public void setEstadoCivil(String estadoCivil) { this.estadoCivil =
	 * estadoCivil; }
	 * 
	 * public String getIdentificacionConyuge() { return identificacionConyuge; }
	 * 
	 * public void setIdentificacionConyuge(String identificacionConyuge) {
	 * this.identificacionConyuge = identificacionConyuge; }
	 * 
	 * public String getNombreConyuge() { return nombreConyuge; }
	 * 
	 * public void setNombreConyuge(String nombreConyuge) { this.nombreConyuge =
	 * nombreConyuge; }
	 * 
	 * public String getRelacionDependencia() { return relacionDependencia; }
	 * 
	 * public void setRelacionDependencia(String relacionDependencia) {
	 * this.relacionDependencia = relacionDependencia; }
	 */

}
