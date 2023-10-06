package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class TransferenciaOtroBancoSP extends EAOComandoJdbcGenerico {

	private TransferenciaOtroBanco transferenciaOtroBanco;

	public TransferenciaOtroBancoSP(TransferenciaOtroBanco transferenciaOtroBanco) {
		this.transferenciaOtroBanco = transferenciaOtroBanco;
	}

	public TransferenciaOtroBanco getTransferenciaOtroBanco() {
		return transferenciaOtroBanco;
	}

	public void setTransferenciaOtroBanco(TransferenciaOtroBanco transferenciaOtroBanco) {
		this.transferenciaOtroBanco = transferenciaOtroBanco;
	}

	public void execute(Connection connection) throws SQLException {

		try {
			CallableStatement procedimiento = null;

			procedimiento = connection
					.prepareCall("{call Wb_k_Web_Banking.Wb_p_Solicitud_Spi(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");// ?
			procedimiento.setDouble(1, this.transferenciaOtroBanco.getValor());
			procedimiento.setInt(2, this.transferenciaOtroBanco.getTipoTransmision());
			procedimiento.setInt(3, this.transferenciaOtroBanco.getConceptoOpi());
			procedimiento.setString(4, this.transferenciaOtroBanco.getLocalidadOrdenante());
			procedimiento.setInt(5, this.transferenciaOtroBanco.getClienteOrdenante());
			procedimiento.setInt(6, this.transferenciaOtroBanco.getTipoCuentaOrdenante());
			procedimiento.setLong(7, this.transferenciaOtroBanco.getCuentaOrdenante());
			// procedimiento.setInt(8,this.transferenciaOtroBanco.getTipoFinanciera());
			// procedimiento.setInt(9, this.transferenciaOtroBanco.getCodigoFinanciera());
			procedimiento.setString(8, this.transferenciaOtroBanco.getFinancieraBce());
			procedimiento.setString(9, this.transferenciaOtroBanco.getIdentificacionBeneficiario());// 10
			procedimiento.setString(10, this.transferenciaOtroBanco.getNombreBeneficiario());// 11
			procedimiento.setInt(11, this.transferenciaOtroBanco.getTipoCuentaDestino());// 12
			procedimiento.setLong(12, this.transferenciaOtroBanco.getCuentaDestino());// 13
			procedimiento.setString(13, this.transferenciaOtroBanco.getInstruccionEspecial());// 14
			procedimiento.setString(14, this.transferenciaOtroBanco.getUsuario());// 15
			procedimiento.setString(15, this.transferenciaOtroBanco.getCanal().toString());// 15
			procedimiento.registerOutParameter(16, java.sql.Types.LONGNVARCHAR);// 16
			procedimiento.registerOutParameter(17, java.sql.Types.VARCHAR);// 17
			procedimiento.execute();

			this.transferenciaOtroBanco.setSecuencia(procedimiento.getLong(16));// 16
			this.transferenciaOtroBanco.setError(procedimiento.getString(17));// 17

		} catch (Exception e) {
			this.transferenciaOtroBanco.setError("Error Inesperado");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
