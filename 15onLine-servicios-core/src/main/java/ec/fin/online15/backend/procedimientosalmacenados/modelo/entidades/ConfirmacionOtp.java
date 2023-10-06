package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class ConfirmacionOtp extends EAOComandoJdbcGenerico {

	private Integer codigoCliente;
	private String otpCorreo;
	private String otpCelular;
	private Integer resultado;
	private String error;

	public ConfirmacionOtp(Integer codigoCliente, String otpCorreo,
			String otpCelular) {
		super();
		this.codigoCliente = codigoCliente;
		this.otpCorreo = otpCorreo;
		this.otpCelular = otpCelular;
		this.resultado = 0;
		this.error = null;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getOtpCorreo() {
		return otpCorreo;
	}

	public void setOtpCorreo(String otpCorreo) {
		this.otpCorreo = otpCorreo;
	}

	public String getOtpCelular() {
		return otpCelular;
	}

	public void setOtpCelular(String otpCelular) {
		this.otpCelular = otpCelular;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
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
					.prepareCall("{call Kweb_Procesos.Pweb_Confirmacion_Otp(?,?,?,?,?)}");
			procedimiento.setInt(1, this.codigoCliente);
			procedimiento.setString(2, this.otpCelular);
			procedimiento.setString(3, this.otpCorreo);
			procedimiento.registerOutParameter(4, java.sql.Types.INTEGER);
			procedimiento.registerOutParameter(5, java.sql.Types.VARCHAR);
			procedimiento.execute();
			this.resultado = procedimiento.getInt(4);
			this.error = procedimiento.getString(5);
		} catch (Exception e) {
			this.error = "Error invocando EnvioOtp-Kweb_Procesos.Pweb_Envio_Otp";
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}
}
