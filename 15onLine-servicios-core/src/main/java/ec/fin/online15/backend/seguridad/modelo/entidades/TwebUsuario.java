package ec.fin.online15.backend.seguridad.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConexiones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebIpsAutorizadas;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebRespuestasUsuarios;

@Entity
@Table(name = "TWEB_USUARIOS")
@XmlRootElement
public class TwebUsuario extends EntidadBasica<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TwebUsuarioRol> usuariosRoles;
	private TwebTiposBloqueo tiposBloqueo;

	private String usuario;
	private Long id;
	private Integer codigoCliente;
	private String convenioWeb;
	private String correo;
	private String celular;
	private String bloqueado;
	private Date fechaUltimoBloqueo;
	private Date fechaUltimoDesbloqueo;
	private Date fechaUltimoCambioClave;
	private String imagen;
	private String descripcionImagen;
	private String cambiaClave;
	private Integer intentoFallidoAutenticacion;
	private Integer intentoFallidoRespuestas;
	private String envia_otp_mail;
	private List<TwebRespuestasUsuarios> respuestasUsuarios;
	private List<TwebIpsAutorizadas> ipsAutorizadas;
	private List<TwebConexiones> conexiones;
	private List<TwebHistoricoClaves> claves;
	private List<TwebOperacionesFrecuentes> operacionesFrecuentes;

	@Id
	@SequenceGenerator(name = "objGenIDUsuario", sequenceName = "SWEB_ID_USUARIO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "objGenIDUsuario", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USUARIO")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "CODIGO_CLIENTE")
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Column(name = "CONVENIO_WEB")
	public String getConvenioWeb() {
		return convenioWeb;
	}

	public void setConvenioWeb(String convenioWeb) {
		this.convenioWeb = convenioWeb;
	}

	@Column(name = "CORREO")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "CELULAR")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "BLOQUEADO")
	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMO_BLOQUEO")
	public Date getFechaUltimoBloqueo() {
		return fechaUltimoBloqueo;
	}

	public void setFechaUltimoBloqueo(Date fechaUltimoBloqueo) {
		this.fechaUltimoBloqueo = fechaUltimoBloqueo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMO_DESBLOQUEO")
	public Date getFechaUltimoDesbloqueo() {
		return fechaUltimoDesbloqueo;
	}

	public void setFechaUltimoDesbloqueo(Date fechaUltimoDesbloqueo) {
		this.fechaUltimoDesbloqueo = fechaUltimoDesbloqueo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMO_CAMBIO_CLAVE")
	public Date getFechaUltimoCambioClave() {
		return fechaUltimoCambioClave;
	}

	public void setFechaUltimoCambioClave(Date fechaUltimoCambioClave) {
		this.fechaUltimoCambioClave = fechaUltimoCambioClave;
	}

	@Column(name = "IMAGEN")
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column(name = "DESCRIPCION_IMAGEN")
	public String getDescripcionImagen() {
		return descripcionImagen;
	}

	public void setDescripcionImagen(String descripcionImagen) {
		this.descripcionImagen = descripcionImagen;
	}

	@Column(name = "CAMBIA_CLAVE")
	public String getCambiaClave() {
		return cambiaClave;
	}

	public void setCambiaClave(String cambiaClave) {
		this.cambiaClave = cambiaClave;
	}

	@Column(name = "INTENTOS_FALLIDO_AUTENTICACION")
	public Integer getIntentoFallidoAutenticacion() {
		return intentoFallidoAutenticacion;
	}

	public void setIntentoFallidoAutenticacion(Integer intentoFallidoAutenticacion) {
		this.intentoFallidoAutenticacion = intentoFallidoAutenticacion;
	}

	@Column(name = "INTENTOS_FALLIDO_RESPUESTAS")
	public Integer getIntentoFallidoRespuestas() {
		return intentoFallidoRespuestas;
	}

	public void setIntentoFallidoRespuestas(Integer intentoFallidoRespuestas) {
		this.intentoFallidoRespuestas = intentoFallidoRespuestas;
	}

	@Column(name = "ENVIA_OTP_MAIL")
	public String getEnvia_otp_mail() {
		return envia_otp_mail;
	}

	public void setEnvia_otp_mail(String envia_otp_mail) {
		this.envia_otp_mail = envia_otp_mail;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebUsuarioRol> getUsuariosRoles() {
		return usuariosRoles;
	}

	public void setUsuariosRoles(List<TwebUsuarioRol> usuariosRoles) {
		this.usuariosRoles = usuariosRoles;
	}

	// @XmlTransient
	@ManyToOne
	@JoinColumn(name = "ID_TIPOS_BLOQUEO")
	public TwebTiposBloqueo getTiposBloqueo() {
		return tiposBloqueo;
	}

	public void setTiposBloqueo(TwebTiposBloqueo tiposBloqueo) {
		this.tiposBloqueo = tiposBloqueo;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebRespuestasUsuarios> getRespuestasUsuarios() {
		return respuestasUsuarios;
	}

	public void setRespuestasUsuarios(List<TwebRespuestasUsuarios> twebRespuestasUsuarios) {
		this.respuestasUsuarios = twebRespuestasUsuarios;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebIpsAutorizadas> getIpsAutorizadas() {
		return ipsAutorizadas;
	}

	public void setIpsAutorizadas(List<TwebIpsAutorizadas> ipsAutorizadas) {
		this.ipsAutorizadas = ipsAutorizadas;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebConexiones> getConexiones() {
		return conexiones;
	}

	public void setConexiones(List<TwebConexiones> conexiones) {
		this.conexiones = conexiones;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebHistoricoClaves> getClaves() {
		return claves;
	}

	public void setClaves(List<TwebHistoricoClaves> claves) {
		this.claves = claves;
	}

	@XmlTransient
	@OneToMany(mappedBy = "usuario")
	public List<TwebOperacionesFrecuentes> getOperacionesFrecuentes() {
		return operacionesFrecuentes;
	}

	public void setOperacionesFrecuentes(List<TwebOperacionesFrecuentes> operacionesFrecuentes) {
		this.operacionesFrecuentes = operacionesFrecuentes;
	}

}
