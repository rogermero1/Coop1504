package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class AutorizaTransaccionSP extends EAOComandoJdbcGenerico {
	private AutorizaTransaccion autorizaTransaccion;

	public AutorizaTransaccionSP(AutorizaTransaccion autorizaTransaccion) {
		this.autorizaTransaccion = autorizaTransaccion;
	}

	public AutorizaTransaccion getAutorizaTransaccion() {
		return autorizaTransaccion;
	}

	public void setAutorizaTransaccion(AutorizaTransaccion autorizaTransaccion) {
		this.autorizaTransaccion = autorizaTransaccion;
	}

	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection
					.prepareCall("{call Mg_k_Autorizaciones.Mg_p_Autorizaciones(?,?,?,?,?,?,?)}");
			procedimiento
					.setInt(1, this.autorizaTransaccion.getCodigoCliente());
			procedimiento.setDouble(2, this.autorizaTransaccion.getMonto());
			procedimiento.setInt(3, this.autorizaTransaccion.getIdTipoCanal());
			procedimiento.setString(4, this.autorizaTransaccion.getVerifica());
			procedimiento.setString(5, this.autorizaTransaccion.getActualiza());

			procedimiento.registerOutParameter(6, java.sql.Types.VARCHAR);
			procedimiento.registerOutParameter(7, java.sql.Types.VARCHAR);
			procedimiento.execute();

			this.autorizaTransaccion.setEstado(procedimiento.getString(6));
			this.autorizaTransaccion.setError(procedimiento.getString(7));

		} catch (Exception e) {
			this.autorizaTransaccion.setError("Error Inesperado...");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
