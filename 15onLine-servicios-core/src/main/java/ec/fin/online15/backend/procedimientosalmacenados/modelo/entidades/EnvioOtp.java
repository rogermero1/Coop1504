package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class EnvioOtp extends EAOComandoJdbcGenerico {

	// en el caso de registro de usuario S para
	// que envie una otp diferente al celular y
	// al correo
	private Integer codigoCliente;
	private String telefono;
	private String correo;
	private String otpsIguales;
	private String observacion;
	private String error;

	public EnvioOtp(Integer codigoCliente, String telefono, String correo,
			String otpsIguales, String observacion) {
		super();
		this.codigoCliente = codigoCliente;
		this.telefono = telefono;
		this.correo = correo;
		this.otpsIguales = otpsIguales;
		this.observacion = observacion;
		this.error = null;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getOtpsIguales() {
		return otpsIguales;
	}

	public void setOtpsIguales(String otpsIguales) {
		this.otpsIguales = otpsIguales;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;
			procedimiento = connection
					.prepareCall("{call Kweb_Procesos.Pweb_Envio_Otp(?,?,?,?,?,?)}");
			procedimiento.setInt(1, this.codigoCliente);
			procedimiento.setString(2, this.telefono);
			procedimiento.setString(3, this.correo);
			procedimiento.setString(4, this.otpsIguales);
			procedimiento.setString(5, this.observacion);
			procedimiento.registerOutParameter(6, java.sql.Types.VARCHAR);
			procedimiento.execute();
			this.error = procedimiento.getString(6);
		} catch (Exception e) {
			this.error = "Error invocando EnvioOtp-Kweb_Procesos.Pweb_Envio_Otp";
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
