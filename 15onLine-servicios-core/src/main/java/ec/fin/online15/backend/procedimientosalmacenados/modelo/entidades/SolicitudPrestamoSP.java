package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class SolicitudPrestamoSP extends EAOComandoJdbcGenerico {

	private SolicitudPrestamo solicitud;

	public SolicitudPrestamoSP(SolicitudPrestamo solicitud) {
		this.solicitud = solicitud;
	}

	public SolicitudPrestamo getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudPrestamo solicitud) {
		this.solicitud = solicitud;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection
					.prepareCall("{call Wb_k_Web_Banking.Wb_p_Registro_Solicitud(?,?,?,?,?,?,?,?,?)}");
			procedimiento.setInt(1, this.solicitud.getCodigoCliente());
			procedimiento.setDouble(2, this.solicitud.getMontoSolicitado());
			procedimiento.setInt(3, this.solicitud.getPlazo());
			procedimiento.setInt(4, this.solicitud.getTipoAmortizacion());
			procedimiento.setString(5, this.solicitud.getProposito());
			procedimiento.setString(6, this.solicitud.getPreAprobado());
			procedimiento.setDouble(7, this.solicitud.getTasa());
			procedimiento.setString(8, this.solicitud.getEstadosSituacion());
			procedimiento.registerOutParameter(9, java.sql.Types.VARCHAR);

			procedimiento.execute();

			// this.transferenciaLocal.setSecuencia(procedimiento.getLong(7));
			this.solicitud.setCodigoError(procedimiento.getString(9));

		} catch (Exception e) {
			this.solicitud
					.setCodigoError("Error al registrar solicitud - Wb_k_Web_Banking.Wb_p_Registro_Solicitud");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}

	}
}
