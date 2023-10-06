package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;

@Stateless
@LocalBean
public class TwebPerfilTransaccionalEAO extends
		EAOSeed<TwebPerfilTransaccional, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebPerfilTransaccional> consultaPerfilTransaccionalCliente(
			Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Perfil_Transaccional WHERE Estado = 'A' AND Codigo_Cliente = ?",
						TwebPerfilTransaccional.class);
		query.setParameter(1, codigoCliente);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebPerfilTransaccional> consultaPerfilCanalTransaccionalCliente(
			Integer codigoCliente, Integer idCanalElectronico) {
		System.out.println("CodigoCliente: " + codigoCliente + " canal: "
				+ idCanalElectronico);
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM Tweb_Perfil_Transaccional "
						+ "WHERE Estado = 'A' AND Codigo_Cliente = ? "
						+ "AND Id_Canal_Electronico = ?",
				TwebPerfilTransaccional.class);
		query.setParameter(1, codigoCliente);
		query.setParameter(2, idCanalElectronico);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	public List<TwebPerfilTransaccional> consultaCuposTransaccionalInicial(
			Integer codigoCliente) {
		System.out.println("CodigoCliente: " + codigoCliente);
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.Codigo_Tipo_Canal, "
								+ "b.Codigo_Cliente, "
								+ "a.Monto_Minimo, "
								+ "a.Monto_Diario, "
								+ "a.Monto_Semanal, "
								+ "a.Monto_Mensual, "
								+ "a.Trx_Diaria, "
								+ "a.Trx_Semanal, "
								+ "a.Trx_Mensual, "
								+ "a.Monto_Maximo_Trx "
								+ "FROM   Mg_Grupo_Canales_Electronicos a, Mg_Clientes b "
								+ "WHERE  b.Codigo_Cliente = ? "
								+ "AND    a.Codigo_Grupo = b.Grupo_Canal_Electronico");
		query.setParameter(1, codigoCliente);
		List<TwebPerfilTransaccional> listadoCupos = new ArrayList<TwebPerfilTransaccional>();
		@SuppressWarnings("unchecked")
		List<Object[]> resultadoLista = query.getResultList();
		for (Object[] resultado : resultadoLista) {
			TwebPerfilTransaccional perfilTransaccional = new TwebPerfilTransaccional();
			perfilTransaccional
					.setIdCanalElectronico(((BigDecimal) resultado[0])
							.intValue());
			perfilTransaccional.setCodigoCliente(((BigDecimal) resultado[1])
					.intValue());
			perfilTransaccional
					.setMontoMinimoTransaccion(((BigDecimal) resultado[2])
							.doubleValue());
			perfilTransaccional.setMontoDiario(((BigDecimal) resultado[3])
					.doubleValue());
			perfilTransaccional.setMontoSemanal(((BigDecimal) resultado[4])
					.doubleValue());
			perfilTransaccional.setMontoMensual(((BigDecimal) resultado[5])
					.doubleValue());
			perfilTransaccional
					.setTransaccionDiaria(((BigDecimal) resultado[6])
							.intValue());
			perfilTransaccional
					.setTransaccionSemanal(((BigDecimal) resultado[7])
							.intValue());
			perfilTransaccional
					.setTransaccionesMensual(((BigDecimal) resultado[8])
							.intValue());
			perfilTransaccional
					.setMontoMaximoTransaccion(((BigDecimal) resultado[9])
							.doubleValue());
			listadoCupos.add(perfilTransaccional);
		}
		return listadoCupos;
	}

	@SuppressWarnings("unchecked")
	public List<ListadoOpciones> listaCanalesElectronicos() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT Codigo_Canal, Descripcion FROM Mg_Canales_Electronicos WHERE Administrable = 'S'");
		List<ListadoOpciones> listadoCanales = new ArrayList<ListadoOpciones>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones canales = new ListadoOpciones(
					(String) resultado[0].toString(), (String) resultado[1]);
			listadoCanales.add(canales);
		}
		return listadoCanales;
	}

	public List<TwebPerfilTransaccional> consultaPerfilTransaccionesAcumuladas(
			Integer codigoCliente, Integer idTipoCanal) {
		System.out.println("CodigoCliente: " + codigoCliente + " canal: "
				+ idTipoCanal);
		Query query = getEntityManager().createNativeQuery(
				"SELECT a.Codigo_Cliente, " + "a.Codigo_Tipo_Canal, "
						+ "a.Trx_Diarias, " + "a.Trx_Semanales, "
						+ "a.Trx_Mensuales, " + "a.Monto_Acumulado_Diario, "
						+ "a.Monto_Acumulado_Semanal, "
						+ "a.Monto_Acumulado_Mensual "
						+ "FROM   Mg_Acumulado_Trx_Canal a "
						+ "WHERE  Codigo_Cliente = ? "
						+ "AND    Codigo_Tipo_Canal = ?");
		query.setParameter(1, codigoCliente);
		query.setParameter(2, idTipoCanal);
		List<TwebPerfilTransaccional> listadoCupos = new ArrayList<TwebPerfilTransaccional>();
		@SuppressWarnings("unchecked")
		List<Object[]> resultadoLista = query.getResultList();
		for (Object[] resultado : resultadoLista) {
			TwebPerfilTransaccional perfilTransaccional = new TwebPerfilTransaccional();
			perfilTransaccional
					.setIdCanalElectronico(((BigDecimal) resultado[1])
							.intValue());
			perfilTransaccional.setCodigoCliente(((BigDecimal) resultado[0])
					.intValue());
			perfilTransaccional
					.setTransaccionDiaria(((BigDecimal) resultado[2])
							.intValue());
			perfilTransaccional
					.setTransaccionSemanal(((BigDecimal) resultado[3])
							.intValue());
			perfilTransaccional
					.setTransaccionesMensual(((BigDecimal) resultado[4])
							.intValue());
			perfilTransaccional.setMontoDiario(((BigDecimal) resultado[5])
					.doubleValue());
			perfilTransaccional.setMontoSemanal(((BigDecimal) resultado[6])
					.doubleValue());
			perfilTransaccional.setMontoMensual(((BigDecimal) resultado[7])
					.doubleValue());
			listadoCupos.add(perfilTransaccional);
		}
		return listadoCupos;
	}
}
