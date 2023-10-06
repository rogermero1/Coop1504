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

//import org.hibernate.envers.Audited;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

@Entity
@Table(name = "TWEB_PARAMETROS_GENERALES")
@XmlRootElement
public class TwebParametrosGenerales extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer preguntasObligatorias;
	private Integer diasVigenciaClave;
	private Integer minutosVigenciaOtp;
	private Integer intentosFallidosParaBloquear;
	private Integer longitudMinimaClave;
	private Integer minimoLetrasClave;
	private Integer minimoMayusculasClave;
	private Integer minimoNumerosClave;
	private Integer minimoCaracteresEspecialesClave;
	private Integer historicoClave;
	private Integer historicoAhorros;
	private String encriptarOtp;

	@Id
	@SequenceGenerator(name = "PARAMETROS_GENERALES_ID_GENERATOR", sequenceName = "SWEB_ID_PARAMETROS_GENERALES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETROS_GENERALES_ID_GENERATOR")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PREGUNTAS_OBLIGATORIAS", length = 10)
	public Integer getPreguntasObligatorias() {
		return preguntasObligatorias;
	}

	public void setPreguntasObligatorias(Integer preguntasObligatorias) {
		this.preguntasObligatorias = preguntasObligatorias;
	}

	@Column(name = "DIAS_VIGENCIA_CLAVE", length = 10)
	public Integer getDiasVigenciaClave() {
		return diasVigenciaClave;
	}

	public void setDiasVigenciaClave(Integer diasVigenciaClave) {
		this.diasVigenciaClave = diasVigenciaClave;
	}

	@Column(name = "MINUTOS_VIGENCIA_OTP", length = 10)
	public Integer getMinutosVigenciaOtp() {
		return minutosVigenciaOtp;
	}

	public void setMinutosVigenciaOtp(Integer minutosVigenciaOtp) {
		this.minutosVigenciaOtp = minutosVigenciaOtp;
	}

	@Column(name = "BLOQUEO_INTENTOS_FALLIDOS", length = 10)
	public Integer getIntentosFallidosParaBloquear() {
		return intentosFallidosParaBloquear;
	}

	public void setIntentosFallidosParaBloquear(Integer intentosFallidosParaBloquear) {
		this.intentosFallidosParaBloquear = intentosFallidosParaBloquear;
	}

	@Column(name = "LONGITUD_MINIMA_CLAVE", length = 10)
	public Integer getLongitudMinimaClave() {
		return longitudMinimaClave;
	}

	public void setLongitudMinimaClave(Integer longitudMinimaClave) {
		this.longitudMinimaClave = longitudMinimaClave;
	}

	@Column(name = "LETRAS_MINIMO_CLAVE", length = 10)
	public Integer getMinimoLetrasClave() {
		return minimoLetrasClave;
	}

	public void setMinimoLetrasClave(Integer minimoLetrasClave) {
		this.minimoLetrasClave = minimoLetrasClave;
	}

	@Column(name = "MAYUSCULAS_MINIMO_CLAVE", length = 10)
	public Integer getMinimoMayusculasClave() {
		return minimoMayusculasClave;
	}

	public void setMinimoMayusculasClave(Integer minimoMayusculasClave) {
		this.minimoMayusculasClave = minimoMayusculasClave;
	}

	@Column(name = "NUMEROS_MINIMO_CLAVE", length = 10)
	public Integer getMinimoNumerosClave() {
		return minimoNumerosClave;
	}

	public void setMinimoNumerosClave(Integer minimoNumerosClave) {
		this.minimoNumerosClave = minimoNumerosClave;
	}

	@Column(name = "CARACTERES_ESPECIALES_X_CLAVE", length = 10)
	public Integer getMinimoCaracteresEspecialesClave() {
		return minimoCaracteresEspecialesClave;
	}

	public void setMinimoCaracteresEspecialesClave(Integer minimoCaracteresEspecialesClave) {
		this.minimoCaracteresEspecialesClave = minimoCaracteresEspecialesClave;
	}

	@Column(name = "HISTORICO_CLAVE", length = 10)
	public Integer getHistoricoClave() {
		return historicoClave;
	}

	public void setHistoricoClave(Integer historicoClave) {
		this.historicoClave = historicoClave;
	}

	@Column(name = "HISTORICO_AHORROS", length = 10)
	public Integer getHistoricoAhorros() {
		return historicoAhorros;
	}

	public void setHistoricoAhorros(Integer historicoAhorros) {
		this.historicoAhorros = historicoAhorros;
	}

	@Column(name = "ENCRIPTAR_OTP", length = 1)
	public String getEncriptarOtp() {
		return encriptarOtp;
	}

	public void setEncriptarOtp(String encriptarOtp) {
		this.encriptarOtp = encriptarOtp;
	}

}
