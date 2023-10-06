package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class GeneracionConvenio extends EAOComandoJdbcGenerico {

	private String numeroConvenio;

	public GeneracionConvenio() {
		super();
	}

	public String getNumeroConvenio() {
		return numeroConvenio;
	}

	public void setNumeroConvenio(String numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;
			procedimiento = connection
					.prepareCall("{?=call Kweb_Procesos.Fweb_Genera_Otp()}");
			procedimiento.registerOutParameter(1, java.sql.Types.VARCHAR);
			procedimiento.execute();
			this.numeroConvenio = procedimiento.getString(1);
		} catch (Exception e) {
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
