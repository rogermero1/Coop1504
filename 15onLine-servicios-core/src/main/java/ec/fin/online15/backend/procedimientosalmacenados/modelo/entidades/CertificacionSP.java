package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class CertificacionSP extends EAOComandoJdbcGenerico {

	private Certificacion certificacion;

	public CertificacionSP(Certificacion certificacion) {
		this.certificacion = certificacion;
	}

	public Certificacion getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(Certificacion certificacion) {
		this.certificacion = certificacion;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection
					.prepareCall("{call Wb_k_Web_Banking.Wb_p_Registro_Certificacion(?,?,?,?,?,?,?,?,?)}");
			procedimiento.setInt(1, this.certificacion.getCodigoCliente());
			procedimiento.setDouble(2, this.certificacion.getTipoCertificacion());
			procedimiento.setLong(3, this.certificacion.getNumeroCuenta());
			procedimiento.setString(4, this.certificacion.getCorreoPrincipal());
			procedimiento.setString(5, this.certificacion.getCorreoSecundario());
			procedimiento.setString(6, "S");
			procedimiento.setString(7, this.certificacion.getCanal().toString());
			procedimiento.registerOutParameter(8, java.sql.Types.INTEGER);
			procedimiento.registerOutParameter(9, java.sql.Types.VARCHAR);

			procedimiento.execute();

			this.certificacion.setSecuencia(procedimiento.getLong(8));
			this.certificacion.setCodigoError(procedimiento.getString(9));

		} catch (Exception e) {
			this.certificacion
					.setCodigoError("Error al registrar solicitud - Wb_k_Web_Banking.Wb_p_Registro_Certificacion");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
