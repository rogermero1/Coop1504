package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class TransferenciaLocalSP extends EAOComandoJdbcGenerico {

	private TransferenciaLocal transferenciaLocal;

	public TransferenciaLocalSP(TransferenciaLocal transferenciaLocal) {
		this.transferenciaLocal = transferenciaLocal;
	}

	public TransferenciaLocal getTransferenciaLocal() {
		return transferenciaLocal;
	}

	public void setTransferenciaLocal(TransferenciaLocal transferenciaLocal) {
		this.transferenciaLocal = transferenciaLocal;
	}

	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection.prepareCall("{call Wb_k_Web_Banking.Wb_p_Trans_Ctas(?,?,?,?,?,?,?,?,?)}");
			procedimiento.setString(1, this.transferenciaLocal.getCodigoAplicacionDebito());
			procedimiento.setLong(2, this.transferenciaLocal.getCuentaDebitar());
			procedimiento.setDouble(3, this.transferenciaLocal.getValorDebito());
			procedimiento.setString(4, this.transferenciaLocal.getDescripcion());
			procedimiento.setString(5, this.transferenciaLocal.getCodigoAplicacionCredito());
			procedimiento.setLong(6, this.transferenciaLocal.getCuentaAcreditar());
			procedimiento.setString(7, this.transferenciaLocal.getCanal().toString());

			procedimiento.registerOutParameter(8, java.sql.Types.INTEGER);
			procedimiento.registerOutParameter(9, java.sql.Types.VARCHAR);
			procedimiento.execute();

			this.transferenciaLocal.setSecuencia(procedimiento.getLong(8));
			this.transferenciaLocal.setCodigoError(procedimiento.getString(9));

		} catch (Exception e) {
			this.transferenciaLocal.setCodigoError("Error Transferencia Local - Wb_k_Web_Banking.Wb_p_Trans_Ctas");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
