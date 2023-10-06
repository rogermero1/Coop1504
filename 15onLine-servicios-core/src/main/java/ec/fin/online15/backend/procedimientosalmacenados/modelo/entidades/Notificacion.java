package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class Notificacion extends EAOComandoJdbcGenerico {

	private Integer codigoCliente;
	private String telefono;
	private String correo;
	private Integer plantilla;
	private String parametros;
	private String error;

	public Notificacion(Integer codigoCliente, String telefono, String correo,
			Integer plantilla, String parametros) {
		super();
		this.codigoCliente = codigoCliente;
		this.telefono = telefono;
		this.correo = correo;
		this.plantilla = plantilla;
		this.parametros = parametros;
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

	public Integer getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(Integer plantilla) {
		this.plantilla = plantilla;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
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
					.prepareCall("{call Kweb_Procesos.Pweb_Envio_Notificacion(?,?,?,?,?,?)}");
			procedimiento.setInt(1, this.codigoCliente);
			procedimiento.setString(2, this.telefono);
			procedimiento.setString(3, this.correo);
			procedimiento.setInt(4, this.plantilla);
			procedimiento.setString(5, this.parametros);
			procedimiento.registerOutParameter(6, java.sql.Types.VARCHAR);
			procedimiento.execute();
			this.error = procedimiento.getString(6);
		} catch (Exception e) {
			this.error = "Error invocando EnvioOtp-Kweb_Procesos.Pweb_Envio_Notificacion";
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
