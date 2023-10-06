package ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOComandoJdbcGenerico;

public class PagoServicioSP extends EAOComandoJdbcGenerico{

	private PagoServicio pagoServicio;
	
	public PagoServicioSP(PagoServicio pagoServicio){
		this.pagoServicio = pagoServicio;
	}
	public PagoServicio getPagoServicio() {
		return pagoServicio;
	}


	public void setPagoServicio(PagoServicio pagoServicio) {
		this.pagoServicio = pagoServicio;
	}

	public void execute(Connection connection) throws SQLException {
		try {
			CallableStatement procedimiento = null;

			procedimiento = connection
					.prepareCall("{call As_Prg.Ps_k_Pago_Servicio.Ps_p_Pago_15online(?,?,?,?,?,?,?,?)}");
			procedimiento.setInt(1, this.pagoServicio.getCodigoTipoServicio());
			procedimiento.setString(2, this.pagoServicio.getNumeroServicio());
			procedimiento
			.setString(3, this.pagoServicio.getSecuenciaEmpresa());
			procedimiento.setDouble(4, this.pagoServicio.getValor());
			procedimiento.setLong(5, this.pagoServicio.getNumeroCuenta());
			procedimiento.setString(6, this.pagoServicio.getNumeroIdentificacion());
			procedimiento.registerOutParameter(7, java.sql.Types.VARCHAR);
			procedimiento.setString(8, this.pagoServicio.getCobraComision());
			
			procedimiento.execute();

			this.pagoServicio.setError(procedimiento.getString(7));

		} catch (Exception e) {
			this.pagoServicio.setError("Error Inesperado en pago de servicio");
			this.errorAplicacion = new ExcepcionAplicacion(e);
			e.printStackTrace();
		}
	}

}
