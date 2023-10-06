package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class AbonoPagoPrestamoSP extends EAOComandoJdbcGenerico {

	private AbonoPagoPrestamo abonoPagoPrestamo;

	public AbonoPagoPrestamoSP(AbonoPagoPrestamo abonoPagoPrestamo) {
		this.abonoPagoPrestamo = abonoPagoPrestamo;
	}

	public AbonoPagoPrestamo getAbonoPagoPrestamo() {
		return abonoPagoPrestamo;
	}

	public void setAbonoPagoPrestamo(AbonoPagoPrestamo abonoPagoPrestamo) {
		this.abonoPagoPrestamo = abonoPagoPrestamo;
	}

	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection.prepareCall("{call Wb_k_Web_Banking.Wb_p_Abono_Pagos_Prestamos(?,?,?,?,?,?,?)}");
			procedimiento.setLong(1, this.abonoPagoPrestamo.getNumeroCuenta());
			procedimiento.setLong(2, this.abonoPagoPrestamo.getNumeroPrestamo());
			procedimiento.setDouble(3, this.abonoPagoPrestamo.getValorPago());
			procedimiento.setString(4, this.abonoPagoPrestamo.getCodigoUsuario());
			procedimiento.setString(5, this.abonoPagoPrestamo.getCanal().toString());

			procedimiento.registerOutParameter(6, java.sql.Types.LONGNVARCHAR);
			procedimiento.registerOutParameter(7, java.sql.Types.VARCHAR);
			procedimiento.execute();

			this.abonoPagoPrestamo.setSecuenciaPago(procedimiento.getLong(6));
			this.abonoPagoPrestamo.setCodigoError(procedimiento.getString(7));

		} catch (Exception e) {
			this.abonoPagoPrestamo
					.setCodigoError("Error Abono pago prestamo - Wb_k_Web_Banking.Wb_p_Abono_Pagos_Prestamos");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
