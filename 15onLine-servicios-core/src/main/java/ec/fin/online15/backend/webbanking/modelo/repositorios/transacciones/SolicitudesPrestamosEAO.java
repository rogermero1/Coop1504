package ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.EstadoSituacionCliente;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamo;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.SolicitudPrestamoSP;

@SuppressWarnings("rawtypes")
@Stateless
@LocalBean
public class SolicitudesPrestamosEAO extends EAOSeed {

	public DatosCliente consultaDatosCliente(Integer codigoCliente) {

		DatosCliente resultado = null;

		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT Numero_Identificacion, "
								+ "       Decode(Codigo_Tipo_Identificacion, "
								+ "              2, "
								+ "              Razon_Social, "
								+ "              Primer_Apellido || ' ' || Segundo_Apellido || ' ' || Nombres), "
								+ "       Decode(Estado_Civil, "
								+ "              'S', "
								+ "              'SOLTERO', "
								+ "              'C', "
								+ "              'CASADO', "
								+ "              'D', "
								+ "              'DIVORCIADO', "
								+ "              'U', "
								+ "              'UNION LIBRE', "
								+ "              'V', "
								+ "              'VIUDO'), "
								+ "       Numero_Ident_Conjugue, "
								+ "       Nombre_Conjugue, "
								+ "       To_Char(Fecha_De_Nacimiento, 'DD/MM/RRRR'), "
								+ "       Decode(Nvl(Codigo_Actividad_Economica, '000000005'),'000000001','SI','000000004','SI','000000005','SI','NO') "
								+ "FROM   Mg_Clientes "
								+ "WHERE  Codigo_Cliente = ? ");
		query.setParameter(1, codigoCliente);
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			resultado = new DatosCliente((String) elementoResultado[0],
					(String) elementoResultado[1],
					(String) elementoResultado[2],
					(String) elementoResultado[3],
					(String) elementoResultado[4],
					(String) elementoResultado[5],
					(String) elementoResultado[6]);
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<EstadoSituacionCliente> listaEstadoFinancieroCliente(
			Integer codigoEstado, Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.Codigo_Estado, a.Codigo_Tipo_Estado, b.Descripcion, a.Valor "
								+ "FROM   Mg_Balance_Resultados_x_Clte a, Mg_Tipos_Estados_Situacion b "
								+ "WHERE  a.Codigo_Estado = b.Codigo_Estado "
								+ "AND    a.Codigo_Tipo_Estado = b.Codigo_Tipo_Estado "
								+ "AND    Codigo_Cliente = :cliente "
								+ "AND    a.Codigo_Estado = :estado "
								+ "AND    Anio = (SELECT MAX(Anio) "
								+ "               FROM   Mg_Balance_Resultados_x_Clte "
								+ "               WHERE  Codigo_Cliente = :cliente) "
								+ "ORDER  BY b.Orden");
		query.setParameter("cliente", codigoCliente);
		query.setParameter("estado", codigoEstado);
		List<EstadoSituacionCliente> listaEstados = new ArrayList<EstadoSituacionCliente>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			EstadoSituacionCliente estado = new EstadoSituacionCliente(
					codigoCliente,
					((BigDecimal) elementoResultado[0]).intValue(),
					((BigDecimal) elementoResultado[1]).intValue(),
					(String) elementoResultado[2],
					((BigDecimal) elementoResultado[3]).doubleValue(),
					((BigDecimal) elementoResultado[3]).doubleValue());
			listaEstados.add(estado);
		}
		return listaEstados;

	}

	public SolicitudPrestamo registrarSolicitud(SolicitudPrestamo solicitud) {

		SolicitudPrestamoSP procedimiento = new SolicitudPrestamoSP(solicitud);

		this.invocacionJDBC(procedimiento);
		return procedimiento.getSolicitud();
	}

}
