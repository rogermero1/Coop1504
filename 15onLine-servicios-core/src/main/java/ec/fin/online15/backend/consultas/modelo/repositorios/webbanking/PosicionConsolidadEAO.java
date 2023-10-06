package ec.fin.online15.backend.consultas.modelo.repositorios.webbanking;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ec.fin.online15.API.ApiResponse;
import ec.fin.online15.API.DenariusApiClientService;
import ec.fin.online15.API.DenariusApiClientServiceJackson;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.CuotasPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DetalleCuotaPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaAhorro;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaInversion;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.PosicionConsolidadaSeguro;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ProductosPreAprobados;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.SaldosBloqueados;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.SaldosReserva;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.UltimosMovimientosAhorro;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import lombok.extern.slf4j.Slf4j;
import fin.coop15abril.librerias.dtosdenarius.*;
import fin.coop15abril.librerias.dtosdenarius.pojos.*;




@SuppressWarnings("rawtypes")
@Stateless
@LocalBean
@Slf4j
public class PosicionConsolidadEAO extends EAOSeed {

	public List<PosicionConsolidadaAhorro> posicionConsolidadaAhorro(Integer codigoCliente) {
		log.info("3. Preparo Consulta oracle: YA");
		log.info("ESTE CODIGO RECIBO PARA CONSULTAR AHORRO: -> "+codigoCliente);
		
		DenariusApiClientServiceJackson apiClient=new DenariusApiClientServiceJackson();
		try {
			
			ApiResponse apiResponse=apiClient.consultarAPI("1312598269");
			log.info("ESTO ES DE API:"+ apiResponse.getDataJson());			
			ObjectMapper objectMapper = new ObjectMapper();
			DatosClienteMS datos = objectMapper.readValue(apiResponse.getDataJson(),DatosClienteMS.class);
			log.info("El nombre de la cuenta de API es : "+datos.getNombre1());
		} catch (Exception e) {
			log.error("Error al consultar API: " + e.getMessage());
		}
        
		  Query query =
		  getEntityManager().createNativeQuery("SELECT a.Codigo_Aplicacion, " +
		  "       a.Codigo_Subaplicacion, " + "       To_Char(a.Numero_Cuenta), " +
		  "       1 Codigo_Moneda, " + "       Substr(b.Descripcion, 1, 30), " +
		  "       c.Abreviatura, " +
		  "       To_Number(Nvl(a.Saldo_Total_En_Linea, 0)), " +
		  "       To_Number(Nvl(a.Saldo_Reserva_En_Linea, 0)), " +
		  "       To_Number((Nvl(a.Saldo_Total_En_Linea, 0) - " +
		  "                 Nvl(a.Saldo_Embargado_En_Linea, 0) - " +
		  "                 Nvl(a.Saldo_Pignorado_En_Linea, 0) - " +
		  "                 Nvl(a.Saldo_Reserva_En_Linea, 0))) Saldo_Disponible, " +
		  "       To_Number((Nvl(a.Saldo_Reserva_En_Linea, 0) + " +
		  "                 Nvl(a.Saldo_Embargado_En_Linea, 0) + " +
		  "                 Nvl(a.Saldo_Pignorado_En_Linea, 0))) Saldo_Bloqueado, " +
		  "       a.Nombre_Cuenta, " +
		  "       Decode(d.Clase_De_Cliente, 'P', 'TITULAR', 'AUTORIZADO'), " +
		  "       e.Descripcion " +
		  "FROM   Ca_Cuentas_De_Ahorro a, Mg_Sub_Aplicaciones b, Mg_Monedas c , Mg_Cuentas_x_Cliente d, Mg_Clase_Cuentas e "
		  + "WHERE  a.Codigo_Aplicacion = b.Codigo_Aplicacion " +
		  "AND    a.Codigo_Subaplicacion = b.Codigo_Sub_Aplicacion " +
		  "AND    b.Codigo_Moneda = c.Codigo_Moneda " +
		  "AND    a.Numero_Cuenta = d.Codigo_Cuenta " +
		  "AND    a.Clase_De_Cuenta = e.Codigo_Clase_Cuenta " +
		  "AND    a.Situacion_En_Linea != 1 " +
		  "AND    d.Clase_De_Cliente IN ('P', 'S') " +
		  "AND    ((d.Clase_De_Cliente = 'P' AND d.Codigo_Cliente = a.Codigo_Cliente) OR "
		  + "        (d.Clase_De_Cliente = 'S' AND a.Clase_De_Cuenta = 2)) " +
		  "AND    d.Codigo_Cliente = ? " +
		  "ORDER BY d.Clase_De_Cliente, a.Numero_Cuenta");
		  
		  query.setParameter(1, codigoCliente);
		 
		log.info(" EAO - Antes de retornar respuesta");	
		
		
		List<PosicionConsolidadaAhorro> listadoAhorro = new ArrayList<PosicionConsolidadaAhorro>();
		
		if (codigoCliente == null)
			return listadoAhorro;
		
		
		  @SuppressWarnings("unchecked") List<Object[]> listaResultado =
		  query.getResultList(); for (Object[] elementoResultado : listaResultado) {
		  PosicionConsolidadaAhorro posicionConsolidadaAhorro = new PosicionConsolidadaAhorro();
		  posicionConsolidadaAhorro.setCodigoAplicacion((String)elementoResultado[0]);
		  posicionConsolidadaAhorro.setCodigoSubAplicacion(((BigDecimal)elementoResultado[1]).intValue());
		  posicionConsolidadaAhorro.setNumeroCuenta((String) elementoResultado[2]);
		  posicionConsolidadaAhorro.setCodigoMoneda(((BigDecimal)elementoResultado[3]).intValue()); //
		  posicionConsolidadaAhorro.setDescripcionMoneda((String)elementoResultado[4]);
		  posicionConsolidadaAhorro.setAbreviaturaMoneda((String)elementoResultado[5]);
		  posicionConsolidadaAhorro.setSaldoTotalLinea(((BigDecimal)elementoResultado[6]).doubleValue());
		  posicionConsolidadaAhorro.setSaldoReservaLinea(((BigDecimal)elementoResultado[7]).doubleValue());
		  posicionConsolidadaAhorro.setSaldoDisponible(((BigDecimal)elementoResultado[8]).doubleValue());
		  posicionConsolidadaAhorro.setSaldoBloqueado(((BigDecimal)elementoResultado[9]).doubleValue());
		  posicionConsolidadaAhorro.setNombreCuenta((String) elementoResultado[10]);
		  posicionConsolidadaAhorro.setClaseCuenta((String) elementoResultado[12] +" / " + (String) elementoResultado[11]);
		  listadoAhorro.add(posicionConsolidadaAhorro); 
		  }
		 
		log.info("=======================FIN COSULTA AHORRO ============================");
		return listadoAhorro;
	}

	public List<PosicionConsolidadaPrestamo> posicionConsolidadaPrestamo(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery("SELECT a.Codigo_Linea_Financiera, "
				+ "       (SELECT Substr(Descripcion, 1, 30) " + "        FROM   Mg_Linea_Financieras "
				+ "        WHERE  Codigo_Linea_Financiera = a.Codigo_Linea_Financiera "
				+ "        AND    Rownum = 1) Linea_Financiera, " + "       1 Codigo_Moneda, "
				+ "       b.Abreviatura, " + "       a.Numero_Prestamo, " + "       a.Monto_Inicial, "
				+ "       (SELECT COUNT(*) " + "        FROM   Pr_Plan_Pago "
				+ "        WHERE  Numero_Prestamo = a.Numero_Prestamo "
				+ "        AND    Pagado = 'N') Cuotas_Pendientes, " + "       (SELECT SUM(CASE "
				+ "                     WHEN d.Tipo_Abono = 'C' THEN " + "                      Nvl(c.Saldo, 0) "
				+ "                     ELSE " + "                      0 " + "                   END) Saldo_Capital "
				+ "        FROM   Pr_Saldos_Plan_Pago c, Pr_Tipos_Saldo d "
				+ "        WHERE  c.Numero_Prestamo = a.Numero_Prestamo "
				+ "        AND    c.Codigo_Tipo_Saldo = d.Codigo_Tipo_Saldo) Saldo_Capital, "
				+ "       a.Fecha_Apertura, " + "       a.Fecha_Vencimiento, "
				+ "       To_Number(Nvl((SELECT SUM(x.Valor) "
				+ "                     FROM   Pr_Saldos_Prestamo x, Pr_Tipos_Saldo y "
				+ "                     WHERE  x.Codigo_Tipo_Saldo = y.Codigo_Tipo_Saldo "
				+ "                     AND    y.Tipo_Abono != 'C' "
				+ "                     AND    x.Numero_Prestamo = a.Numero_Prestamo), "
				+ "                     0)) Saldo_Otros, " + "       a.Plazo, " + "       a.Tasa_Total, "
				+ "       (SELECT Descripcion " + "        FROM   Pr_Estados_Cartera "
				+ "        WHERE  Codigo_Estado_Cartera = a.Codigo_Estado_Cartera "
				+ "        AND    Codigo_Tipo_Credito = a.Codigo_Tipo_Prestamo) Estado, " + "       a.Valor_Cuota, "
				+ "       nvl((SELECT DISTINCT 'SI' " + "       FROM   Pr_Plan_Dividendos_Gracia "
				+ "       WHERE  Numero_Prestamo = a.numero_prestamo),'NO') " + "FROM   Pr_Prestamos a, Mg_Monedas b "
				+ "WHERE  a.Estado = '0' " + "AND    a.Codigo_Cliente = ? "
				+ "AND    b.Codigo_Moneda = 1 order by a.Numero_Prestamo");
		query.setParameter(1, codigoCliente);
		List<PosicionConsolidadaPrestamo> listadoPrestamo = new ArrayList<PosicionConsolidadaPrestamo>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			PosicionConsolidadaPrestamo posicionConsolidadaPrestamo = new PosicionConsolidadaPrestamo(
					(String) elementoResultado[0], (String) elementoResultado[1],
					((BigDecimal) elementoResultado[2]).intValue(), (String) elementoResultado[3],
					((BigDecimal) elementoResultado[4]).longValue(), ((BigDecimal) elementoResultado[5]).doubleValue(),
					((BigDecimal) elementoResultado[6]).intValue(), ((BigDecimal) elementoResultado[7]).doubleValue(),
					(Date) elementoResultado[8], (Date) elementoResultado[9],
					((BigDecimal) elementoResultado[10]).doubleValue(), ((BigDecimal) elementoResultado[11]).intValue(),
					((BigDecimal) elementoResultado[12]).doubleValue(), (String) elementoResultado[13],
					((BigDecimal) elementoResultado[14]).doubleValue());
			posicionConsolidadaPrestamo.setPeriodoGracia((String) elementoResultado[15]);
			listadoPrestamo.add(posicionConsolidadaPrestamo);
		}
		return listadoPrestamo;
	}

	@SuppressWarnings("unchecked")
	public List<PosicionConsolidadaInversion> posicionConsolidadaInversion(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT Codigo_Aplicacion," + "Codigo_Sub_Aplicacion," + "Numero_Deposito," + "1 Codigo_Moneda,"
						+ "Valor_Ultima_Renovacion, " + "plazo, " + "Decode(Operador_Matematico," + "'+',"
						+ "Valor_Tasa + Valor_Spread," + "Valor_Tasa - Valor_Spread) Valor_Tasa, " + "Fecha_Vigencia, "
						+ "Fecha_Vencimiento, " + "Fecha_Proximo_Pago_Interes, " + "Interes_Causado "
						+ "FROM   Dp_Depositos_Plazos " + "WHERE  Codigo_Propietario_Principal = ? "
						+ "AND    Estado_Vigencia != '2' order by Numero_Deposito");
		query.setParameter(1, codigoCliente);
		List<PosicionConsolidadaInversion> listadoInversion = new ArrayList<PosicionConsolidadaInversion>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			PosicionConsolidadaInversion posicionConsolidadaInversion = new PosicionConsolidadaInversion(
					(String) elementoResultado[0], ((BigDecimal) elementoResultado[1]).intValue(),
					((BigDecimal) elementoResultado[2]).intValue(), ((BigDecimal) elementoResultado[3]).intValue(),
					((BigDecimal) elementoResultado[4]).doubleValue(), ((BigDecimal) elementoResultado[5]).intValue(),
					((BigDecimal) elementoResultado[6]).doubleValue(), (Date) elementoResultado[7],
					(Date) elementoResultado[8], (Date) elementoResultado[9],
					((BigDecimal) elementoResultado[10]).doubleValue());
			listadoInversion.add(posicionConsolidadaInversion);
		}
		return listadoInversion;
	}

	@SuppressWarnings("unchecked")
	public List<PosicionConsolidadaSeguro> posicionConsolidadSeguro(Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT a.Codigo_Tipo_Poliza," + "Substr(b.Descripcion, 1, 30)," + "a.Codigo_Seguro,"
						+ "1 Codigo_Moneda," + "a.Valor_Deposito Valor," + "a.Codigo_Cliente "
						+ "FROM   Sv_Seguro_De_Vida a, Mg_Tipos_Polizas b " + "WHERE  a.Codigo_Cliente = ? "
						+ "AND    a.Codigo_Tipo_Poliza = b.Codigo_Tipos_Polizas " + "AND    a.Estado = 'A'");
		query.setParameter(1, codigoCliente);
		List<PosicionConsolidadaSeguro> listadoSeguro = new ArrayList<PosicionConsolidadaSeguro>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			PosicionConsolidadaSeguro posicionConsolidadaSeguro = new PosicionConsolidadaSeguro(
					(String) elementoResultado[0], (String) elementoResultado[1],
					((BigDecimal) elementoResultado[2]).intValue(), ((BigDecimal) elementoResultado[3]).intValue(),
					((BigDecimal) elementoResultado[4]).doubleValue(), ((BigDecimal) elementoResultado[5]).intValue());
			listadoSeguro.add(posicionConsolidadaSeguro);
		}
		return listadoSeguro;
	}

	/* Consulta ultimos movimientos de ahorro */

	@SuppressWarnings("unchecked")
	public List<UltimosMovimientosAhorro> ultimosMovientosAhorro(String numeroCuenta) {
		System.out.println("Preparando primera Consulta");
		Query query1 = getEntityManager().createNativeQuery("SELECT MAX(Saldo), MIN(Fecha_Saldo) "
				+ "FROM   (SELECT Nvl(x.Saldo_Total, 0) Saldo, Fecha_Saldo " + "        FROM   Ca_Saldos_Diarios x "
				+ "        WHERE  x.Numero_Cuenta = ? " + "        AND    x.Fecha_Saldo = "
				+ "               (SELECT MIN(Fecha_Valida) " + "                 FROM   ((SELECT a.Hora, "
				+ "                                 a.Fecha_Valida, "
				+ "                                 a.Secuencia_Movimiento "
				+ "                          FROM   Ca_Movimientos_Mensuales  a, "
				+ "                                 Mg_Tipos_De_Transacciones b, "
				+ "                                 Mg_Agencias               c "
				+ "                          WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "                          AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion "
				+ "                          AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "                          AND    b.Tipo_Operacion != '4' "
				+ "                          AND    a.Situacion_Movimiento != '5' "
				+ "                          AND    a.Numero_Cuenta = ?) UNION ALL "
				+ "                         (SELECT a.Hora, a.Fecha_Valida, a.Secuencia_Movimiento "
				+ "                          FROM   Ca_Movimientos_Diarios    a, "
				+ "                                 Mg_Tipos_De_Transacciones b, "
				+ "                                 Mg_Agencias               c "
				+ "                          WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "                          AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion "
				+ "                          AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "                          AND    b.Tipo_Operacion != '4' "
				+ "                          AND    a.Situacion_Movimiento != '5' "
				+ "                          AND    a.Numero_Cuenta = ?) ORDER BY Hora DESC, "
				+ "                         Secuencia_Movimiento DESC) " + "                 WHERE  Rownum < 21) "
				+ "        UNION ALL " + "        SELECT 0, To_Date(SYSDATE) " + "        FROM   Dual)");
		System.out.println("Asignacion de parametro");
		query1.setParameter(1, numeroCuenta);
		query1.setParameter(2, numeroCuenta);
		query1.setParameter(3, numeroCuenta);
		System.out.println("Obtencion de resultado");
		Double saldoDiario = 0.00;
		Date fechaDesde = null;
		List<Object[]> resultadoSaldo = query1.getResultList();
		if (!resultadoSaldo.isEmpty()) {
			saldoDiario = Double.parseDouble(resultadoSaldo.get(0)[0].toString());
			fechaDesde = (Date) resultadoSaldo.get(0)[1];
			// System.out.println("FECHA DB -> " + fechaDesde);
		}
		System.out.println("Preparando segunda Consulta");
		Query query2 = getEntityManager().createNativeQuery("SELECT a.Hora," + "a.Secuencia_Movimiento, "
				+ "b.Descripcion, " + "c.Codigo_Agencia || '-' || c.Nombre_Agencia Agencia, "
				+ "Decode(b.Debito_Credito_Otro, 'D', a.Valor, 0) Debito, "
				+ "Decode(b.Debito_Credito_Otro, 'C', a.Valor, 0) Credito, " + "a.Valor, " + "b.Debito_Credito_Otro, "
				+ "nvl(a.Descripcion, b.descripcion) Detalle " + "FROM   Ca_Movimientos_Mensuales  a, "
				+ "      Mg_Tipos_De_Transacciones b, " + "     Mg_Agencias               c "
				+ "WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion " + "AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "AND    a.Fecha_Valida > ?" + "AND    b.Tipo_Operacion != '4' "
				+ "AND    a.Situacion_Movimiento != '5' " + "AND    a.Numero_Cuenta = ? " + "UNION ALL (SELECT a.Hora, "
				+ "                  a.Secuencia_Movimiento, " + "                  b.Descripcion, "
				+ "                  c.Codigo_Agencia || '-' || c.Nombre_Agencia Agencia, "
				+ "                  Decode(b.Debito_Credito_Otro, 'D', a.Valor, 0) Debito, "
				+ "                  Decode(b.Debito_Credito_Otro, 'C', a.Valor, 0) Credito, "
				+ "                  a.Valor, " + "                  b.Debito_Credito_Otro, "
				+ "                  nvl(a.Descripcion, b.descripcion) Detalle "
				+ "           FROM   Ca_Movimientos_Diarios    a, " + "                  Mg_Tipos_De_Transacciones b, "
				+ "                  Mg_Agencias               c "
				+ "           WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "           AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion "
				+ "           AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "           AND    b.Tipo_Operacion != '4' " + "           AND    a.Situacion_Movimiento != '5' "
				+ "           AND    a.Numero_Cuenta = ?) " + "ORDER  BY Hora, Secuencia_Movimiento ");
		System.out.println("Asignacion de parametros");
		query2.setParameter(1, fechaDesde);
		query2.setParameter(2, numeroCuenta);
		query2.setParameter(3, numeroCuenta);
		System.out.println("Declaracion de listas");
		List<UltimosMovimientosAhorro> listadoMovimientos = new ArrayList<UltimosMovimientosAhorro>();
		List<Object[]> listaResultado = query2.getResultList();
		System.out.println("Recorrer listas");
		for (Object[] resultado : listaResultado) {
			if (resultado[7].toString().equals("D")) {
				saldoDiario = saldoDiario - ((BigDecimal) resultado[6]).doubleValue();
			} else if (resultado[7].toString().equals("C")) {
				saldoDiario = saldoDiario + ((BigDecimal) resultado[6]).doubleValue();
			}
			UltimosMovimientosAhorro ultimosMovimientos = new UltimosMovimientosAhorro((Date) resultado[0],
					((BigDecimal) resultado[1]).longValue(), (String) resultado[2], (String) resultado[3],
					((BigDecimal) resultado[4]).doubleValue(), ((BigDecimal) resultado[5]).doubleValue(),
					((BigDecimal) resultado[6]).doubleValue(), saldoDiario, (String) resultado[7],
					(String) resultado[8]);
			listadoMovimientos.add(ultimosMovimientos);
		}
		System.out.println("Retornar lista");
		Collections.reverse(listadoMovimientos);

		List<UltimosMovimientosAhorro> listadoFinal = new ArrayList<UltimosMovimientosAhorro>();
		Integer maxLista = (listadoMovimientos.size() > 10 ? 10 : listadoMovimientos.size());
		if (maxLista > 0) {
			for (Integer j = 1; j <= maxLista; j++) {
				listadoFinal.add(listadoMovimientos.get(j - 1));
			}
		}
		return listadoFinal;// listadoMovimientos;
	}

	@SuppressWarnings("unchecked")
	public List<UltimosMovimientosAhorro> consultaHistoricoAhorro(String numeroCuenta, Date fechaDesde,
			Date fechaHasta) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		Query query1 = getEntityManager()
				.createNativeQuery("SELECT Nvl(x.Saldo_Total, 0), Fecha_Saldo " + "FROM   Ca_Saldos_Diarios x "
						+ "WHERE  x.Numero_Cuenta = ? " + "AND    x.Fecha_Saldo = To_Date(?, 'yyyy-mm-dd') - 1");
		query1.setParameter(1, numeroCuenta);
		query1.setParameter(2, formatoDeFecha.format(fechaDesde));
		Double saldoDiario = 0.00;
		@SuppressWarnings("unused")
		Date fechaInicial = null;
		List<Object[]> resultadoSaldo = query1.getResultList();
		if (!resultadoSaldo.isEmpty()) {
			saldoDiario = Double.parseDouble(resultadoSaldo.get(0)[0].toString());
			fechaInicial = (Date) resultadoSaldo.get(0)[1];
			// System.out.println("QUERY " + saldoDiario + " ** " +
			// fechaInicial);
		}
		Query query2 = getEntityManager().createNativeQuery("SELECT a.Hora, " + "       a.Secuencia_Movimiento, "
				+ "       b.Descripcion, " + "       c.Codigo_Agencia || '-' || c.Nombre_Agencia Agencia, "
				+ "       Decode(b.Debito_Credito_Otro, 'D', a.Valor, 0) Debito, "
				+ "       Decode(b.Debito_Credito_Otro, 'C', a.Valor, 0) Credito, " + "       a.Valor, "
				+ "       b.Debito_Credito_Otro, " + "       nvl(a.Descripcion, b.descripcion) Detalle "
				+ "FROM   Ca_Movimientos_Mensuales  a, " + "       Mg_Tipos_De_Transacciones b, "
				+ "       Mg_Agencias               c "
				+ "WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion " + "AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "AND    a.Fecha_Valida BETWEEN To_Date(?, 'yyyy-mm-dd') AND To_Date(?, 'yyyy-mm-dd') "
				+ "AND    b.Tipo_Operacion != '4' " + "AND    a.Situacion_Movimiento != '5' "
				+ "AND    a.Numero_Cuenta = ? " + "UNION ALL (SELECT a.Hora, "
				+ "                  a.Secuencia_Movimiento, " + "                  b.Descripcion, "
				+ "                  c.Codigo_Agencia || '-' || c.Nombre_Agencia Agencia, "
				+ "                  Decode(b.Debito_Credito_Otro, 'D', a.Valor, 0) Debito, "
				+ "                  Decode(b.Debito_Credito_Otro, 'C', a.Valor, 0) Credito, "
				+ "                  a.Valor, " + "                  b.Debito_Credito_Otro, "
				+ "                  nvl(a.Descripcion, b.descripcion) Detalle "
				+ "           FROM   Ca_Movimientos_Diarios    a, " + "                  Mg_Tipos_De_Transacciones b, "
				+ "                  Mg_Agencias               c "
				+ "           WHERE  a.Codigo_Tipo_Transaccion = b.Codigo_Tipo_Transaccion "
				+ "           AND    a.Codigo_Aplicacion = b.Codigo_Aplicacion "
				+ "           AND    a.Codigo_Agencia = c.Codigo_Agencia "
				+ "           AND    b.Tipo_Operacion != '4' " + "           AND    a.Situacion_Movimiento != '5' "
				+ "           AND    a.Numero_Cuenta = ? "
				+ "           AND    a.Fecha_Valida BETWEEN To_Date(?, 'yyyy-mm-dd') AND To_Date(?, 'yyyy-mm-dd')) "
				+ "ORDER  BY Hora, Secuencia_Movimiento");
		System.out.println("Consulta historico de cuenta " + numeroCuenta + " " + formatoDeFecha.format(fechaDesde)
				+ " " + formatoDeFecha.format(fechaHasta));
		query2.setParameter(1, formatoDeFecha.format(fechaDesde));
		query2.setParameter(2, formatoDeFecha.format(fechaHasta));
		query2.setParameter(3, numeroCuenta);
		query2.setParameter(4, numeroCuenta);
		query2.setParameter(5, formatoDeFecha.format(fechaDesde));
		query2.setParameter(6, formatoDeFecha.format(fechaHasta));
		List<UltimosMovimientosAhorro> listadoMovimientos = new ArrayList<UltimosMovimientosAhorro>();
		List<Object[]> listaResultado = query2.getResultList();
		for (Object[] resultado : listaResultado) {
			if (resultado[7].toString().equals("D")) {
				saldoDiario = saldoDiario - ((BigDecimal) resultado[6]).doubleValue();
			} else if (resultado[7].toString().equals("C")) {
				saldoDiario = saldoDiario + ((BigDecimal) resultado[6]).doubleValue();
			}
			UltimosMovimientosAhorro ultimosMovimientos = new UltimosMovimientosAhorro((Date) resultado[0],
					((BigDecimal) resultado[1]).longValue(), (String) resultado[2], (String) resultado[3],
					((BigDecimal) resultado[4]).doubleValue(), ((BigDecimal) resultado[5]).doubleValue(),
					((BigDecimal) resultado[6]).doubleValue(), saldoDiario, (String) resultado[7],
					(String) resultado[8]);
			listadoMovimientos.add(ultimosMovimientos);
		}
		System.out.println("total " + listadoMovimientos.size() + " movimientos para " + numeroCuenta);
		Collections.reverse(listadoMovimientos);
		return listadoMovimientos;
	}

	@SuppressWarnings("unchecked")
	public List<SaldosBloqueados> consultaSaldosBloqueados(String numeroCuenta) {
		Query query = getEntityManager().createNativeQuery("SELECT Fecha_Inicio, Descripcion, Saldo_Embargado_En_Linea"
				+ " FROM   (SELECT Fecha_Inicio, Descripcion, Saldo_Embargado_En_Linea"
				+ "        FROM   Ca_Saldos_Embargados" + "        WHERE  Numero_Cuenta = ?"
				+ "        AND    Saldo_Embargado_En_Linea > 0" + "        UNION ALL"
				+ "        SELECT Fecha_Inicio, Descripcion, Saldo_Embargado_En_Linea"
				+ "        FROM   Ca_Embargo_Seguro" + "        WHERE  Numero_Cuenta = ?"
				+ "        AND    Saldo_Embargado_En_Linea > 0)" + " ORDER  BY 1, 2");
		query.setParameter(1, numeroCuenta);
		query.setParameter(2, numeroCuenta);
		List<SaldosBloqueados> listaSaldos = new ArrayList<SaldosBloqueados>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			SaldosBloqueados saldoBloqueado = new SaldosBloqueados();
			saldoBloqueado.setFecha((Date) elementoResultado[0]);
			saldoBloqueado.setMotivo(elementoResultado[1].toString());
			saldoBloqueado.setValor(Double.parseDouble(elementoResultado[2].toString()));
			listaSaldos.add(saldoBloqueado);
		}
		return listaSaldos;
	}

	@SuppressWarnings("unchecked")
	public List<SaldosReserva> consultaSaldosReserva(String numeroCuenta) {
		Query query = getEntityManager().createNativeQuery("SELECT a.Numero_Cuenta_Giradora, " + "       b.Nombre, "
				+ "       a.Procedencia, " + "       a.Fecha_Reserva, " + "       c.Dias_Reserva, " + "       a.Valor "
				+ "FROM   Ca_Desglose_Detalle_Reserv a, " + "       Mg_Financieras             b, "
				+ "       Ca_Detalle_De_Reservas     c " + "WHERE  a.Codigo_Financiera = b.Codigo_Financiera "
				+ "AND    a.Numero_Cuenta = c.Numero_Cuenta " + "AND    b.Tipo_Financiera = 1 "
				+ "AND    a.Codigo_Error IS NULL " + "AND    a.Numero_Cuenta = ?");
		query.setParameter(1, numeroCuenta);
		List<SaldosReserva> listaReserva = new ArrayList<SaldosReserva>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			SaldosReserva saldoReserva = new SaldosReserva();
			saldoReserva.setCuentaGiradora(elementoResultado[0].toString());
			saldoReserva.setFinanciera(elementoResultado[1].toString());
			saldoReserva.setProcedencia(elementoResultado[2].toString());
			saldoReserva.setFechaReserva((Date) elementoResultado[3]);
			saldoReserva.setDiasReserva(Integer.parseInt(elementoResultado[4].toString()));
			saldoReserva.setValor(Double.parseDouble(elementoResultado[5].toString()));
			listaReserva.add(saldoReserva);
		}
		return listaReserva;
	}

	@SuppressWarnings("unchecked")
	public List<CuotasPrestamo> consultaCuotasPrestamo(Long numeroPrestamo) {
		Query query = getEntityManager().createNativeQuery("SELECT a.Numero_Cuota, " + "       a.Fecha_Vence, "
				+ "       a.Pagado, " + "       a.Fecha_Pagada, " + "       nvl((SELECT SUM(Valor) "
				+ "        FROM   Pr_Saldos_Plan_Pago " + "        WHERE  Numero_Prestamo = a.Numero_Prestamo "
				+ "        AND    Numero_Cuota = a.Numero_Cuota),0) Valor_Cuota, " + "       nvl((SELECT SUM(Saldo) "
				+ "        FROM   Pr_Saldos_Plan_Pago " + "        WHERE  Numero_Prestamo = a.Numero_Prestamo "
				+ "        AND    Numero_Cuota = a.Numero_Cuota),0) Saldo_Cuota, "
				+ "       Mg_k_Validacion.Mg_f_Fecha_Calendario(1, 'BPR') Fecha_Valida, "
				+ "       Mg_k_Validacion.Mg_f_Fecha_Calendario(1, 'BPR') - Fecha_Vence Dias_Atraso, "
				+ "       nvl((SELECT SUM(Saldo) " + "        FROM   Pr_Saldos_Plan_Pago x, Pr_Tipos_Saldo y  "
				+ "        WHERE  x.Codigo_Tipo_Saldo = y.Codigo_Tipo_Saldo  "
				+ "        AND    Numero_Prestamo = a.Numero_Prestamo  "
				+ "        AND    Numero_Cuota = a.Numero_Cuota  " + "        AND    y.Tipo_Abono = 'C'),0) capital, "
				+ "        CASE " + "         WHEN (SELECT Nvl(Pandemia_Covid19, 'N') "
				+ "               FROM   Pr_Prestamos "
				+ "               WHERE  Numero_Prestamo = a.Numero_Prestamo) = 'S' "
				+ "              AND a.Fecha_Vence BETWEEN To_Date('01-mar-2020', 'dd-mm-rrrr') AND "
				+ "              To_Date('31-may-2020', 'dd-mm-rrrr') THEN "
				+ "          case when a.fecha_vence <= '31-mar-2020' and a.fecha_pagada <= '31-mar-2020' then 'N' else 'S' end "
				+ "        ELSE 'N' END CASE " + "FROM   Pr_Plan_Pago a " + "WHERE  a.Numero_Prestamo = ? "
				+ "ORDER  BY a.Numero_Cuota");
		query.setParameter(1, numeroPrestamo);

		List<CuotasPrestamo> listadoCuotasPrestamo = new ArrayList<CuotasPrestamo>();
		List<Object[]> listaResultado = query.getResultList();

		for (Object[] resultado : listaResultado) {
			CuotasPrestamo cuotaPrestamo = new CuotasPrestamo();
			Calendar fechaVence = Calendar.getInstance();
			Calendar fechaValida = Calendar.getInstance();
			fechaVence.setTime((Date) resultado[1]);
			fechaValida.setTime((Date) resultado[6]);
			// System.out.println("Fecha vence : " + fechaVence.getTime());
			// System.out.println("Fecha Valida : " + fechaValida.getTime());
			// System.out.println("Pagada : " + resultado[2]);
			// System.out.println("Antes " + fechaVence.before(fechaValida));
			// System.out.println("Iguales "
			// + fechasIguales(fechaVence, fechaValida));
			// System.out.println("Despues " + fechaVence.after(fechaValida));
			// if ((resultado[2].equals("N") && fechaVence.before(fechaValida))
			// || (resultado[2].equals("N") && fechasIguales(fechaVence,
			// fechaValida))) {
			if (resultado[2].equals("N") && fechaVence.before(fechaValida)) {
				// long milis1 = fechaValida.getTimeInMillis();
				// long milis2 = fechaVence.getTimeInMillis();
				// cuotaPrestamo.setDiasVencido(milis1 - milis2
				// / (24 * 60 * 60 * 1000));
				cuotaPrestamo.setFechaPago(null);
				// cuotaPrestamo.setDescripcionCuota("VENCIDA");
				cuotaPrestamo
						.setDescripcionCuota(((BigDecimal) resultado[8]).doubleValue() == 0 ? "PENDIENTE" : "VENCIDA");
				// cambio 21/01/2022 si la cuota esta PENDIENTE no muestre dias vencidos
				cuotaPrestamo.setDiasVencido(cuotaPrestamo.getDescripcionCuota().equals("PENDIENTE") ? 0L
						: ((BigDecimal) resultado[7]).longValue());

			} else if (resultado[2].equals("N")
					&& (fechaValida.before(fechaVence) || fechasIguales(fechaVence, fechaValida))) {
				cuotaPrestamo.setDiasVencido(0);
				cuotaPrestamo.setDescripcionCuota("PENDIENTE");
				cuotaPrestamo.setFechaPago(null);
			} else if (resultado[2].equals("S")) {
				cuotaPrestamo.setDiasVencido(0);
				cuotaPrestamo.setDescripcionCuota("PAGADA");
				cuotaPrestamo.setFechaPago((Date) resultado[3]);
			}

			// diferidos por pandemia stado DIFERIDO
			if (resultado[9].toString().equals("S")) {
				// cuotaPrestamo.setDiasVencido(0);
				cuotaPrestamo.setDescripcionCuota("DIFERIDA");
			}

			cuotaPrestamo.setNumeroCuota(((BigDecimal) resultado[0]).intValue());
			cuotaPrestamo.setFechaVence((Date) resultado[1]);
			cuotaPrestamo.setPagada((String) resultado[2]);
			cuotaPrestamo.setValorCuota(((BigDecimal) resultado[4]).doubleValue());
			cuotaPrestamo.setSaldoCuota(((BigDecimal) resultado[5]).doubleValue());
			listadoCuotasPrestamo.add(cuotaPrestamo);
		}
		return listadoCuotasPrestamo;
	}

	@SuppressWarnings("unchecked")
	public List<CuotasPrestamo> consultaCuotasGracia(Long numeroPrestamo) {
		Query query = getEntityManager().createNativeQuery("SELECT Numero_Cuota, " + "       Fecha_Vence, "
				+ "       Capital, " + "       Interes, " + "       Interes_Gracia, "
				+ "       Capital + Interes + Interes_Gracia Total " + "FROM   Pr_Plan_Dividendos_Gracia "
				+ "WHERE  Numero_Prestamo = ? " + "ORDER  BY Numero_Cuota");
		query.setParameter(1, numeroPrestamo);

		List<CuotasPrestamo> listadoCuotasPrestamo = new ArrayList<CuotasPrestamo>();
		List<Object[]> listaResultado = query.getResultList();

		for (Object[] resultado : listaResultado) {
			CuotasPrestamo cuotaPrestamo = new CuotasPrestamo();

			cuotaPrestamo.setNumeroCuota(((BigDecimal) resultado[0]).intValue());
			cuotaPrestamo.setFechaVence((Date) resultado[1]);
			cuotaPrestamo.setCapital(((BigDecimal) resultado[2]).doubleValue());
			cuotaPrestamo.setInteres(((BigDecimal) resultado[3]).doubleValue());
			cuotaPrestamo.setInteresGracia(((BigDecimal) resultado[4]).doubleValue());
			cuotaPrestamo.setValorCuota(((BigDecimal) resultado[5]).doubleValue());
			listadoCuotasPrestamo.add(cuotaPrestamo);
		}
		return listadoCuotasPrestamo;
	}

	private static boolean fechasIguales(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("Las fechas no pueden ser null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	public List<DetalleCuotaPrestamo> consultaDetalleCuotaPrestamo(Integer numeroCuota, Long numeroPrestamo) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT b.Descripcion, a.Valor, a.Saldo " + "FROM   Pr_Saldos_Plan_Pago a, Pr_Tipos_Saldo b "
						+ "WHERE  a.Codigo_Tipo_Saldo = b.Codigo_Tipo_Saldo " + "AND    a.Numero_Prestamo = ? "
						+ "AND    a.Numero_Cuota = ? " + "ORDER  BY 1");
		query.setParameter(1, numeroPrestamo);
		query.setParameter(2, numeroCuota);

		List<DetalleCuotaPrestamo> listadoDetalleCuota = new ArrayList<DetalleCuotaPrestamo>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			DetalleCuotaPrestamo detalleCuotaPrestamo = new DetalleCuotaPrestamo((String) resultado[0],
					((BigDecimal) resultado[1]).doubleValue(), ((BigDecimal) resultado[2]).doubleValue());
			listadoDetalleCuota.add(detalleCuotaPrestamo);
		}
		return listadoDetalleCuota;
	}

	@SuppressWarnings("unchecked")
	public List<TwebMovimientos> listadoMovimientos(Long idUsuario, Integer codigoTransaccion, String fechaDesde,
			String fechaHasta) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * " + "FROM   Tweb_Movimientos " + "WHERE  Id_Usuario = ? " + "AND Id_Tipo_Transaccion = ? "
						+ "AND    Trunc(Fecha_Registro) BETWEEN to_date(?,'YYYYMMDD') AND to_date(?,'YYYYMMDD') "
						+ "ORDER BY Fecha_Registro",
				TwebMovimientos.class);
		query.setParameter(1, idUsuario);
		query.setParameter(2, codigoTransaccion);
		query.setParameter(3, fechaDesde);
		query.setParameter(4, fechaHasta);
		return query.getResultList();
	}

	public String nombreFinanciera(Integer idTipoFinanciera, Integer codigoFinanciera) {
		Query query = getEntityManager().createNativeQuery(
				// "SELECT Nombre FROM Mg_Financieras WHERE Tipo_Financiera = ? AND
				// Codigo_Financiera = ?");
				"SELECT Nombre FROM Mg_Financieras WHERE Cuenta_Bce = ?");
		// query.setParameter(1, idTipoFinanciera);
		query.setParameter(1, codigoFinanciera);

		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			return "";
		} else {
			return listaResultado.get(0).toString();
		}
	}

	public String nombreServicio(Integer codigoTipoServicio) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT Descripcion FROM Ps_Tipo_Servicio WHERE Codigo_Tipo_Servicio = ?");
		query.setParameter(1, codigoTipoServicio);
		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			return "";
		} else {
			return listaResultado.get(0).toString();
		}
	}

	@SuppressWarnings("unchecked")
	public ProductosPreAprobados productosPreaprobados(Integer codigoCliente) {
		ProductosPreAprobados resultado = null;
		Query query = getEntityManager().createNativeQuery("SELECT Nvl((SELECT DISTINCT 'S' "
				+ "           FROM   Mg_Clientes_Aaa " + "           WHERE  (Anio, Mes) IN "
				+ "                  (SELECT y.Anio, MAX(Mes) Mes " + "                  FROM   Mg_Clientes_Aaa x, "
				+ "                         (SELECT MAX(Anio) Anio FROM Mg_Clientes_Aaa) y "
				+ "                   WHERE  x.Anio = y.Anio " + "                   GROUP  BY y.Anio) "
				+ "           AND    Codigo_Cliente = :cliente " + "           AND    Desembolsado = 'N'), "
				+ "           'N') Aaa, " + "       Nvl((SELECT DISTINCT 'S' " + "           FROM   Mg_Clientes_Aa "
				+ "           WHERE  (Anio, Mes) IN " + "                  (SELECT y.Anio, MAX(Mes) Mes "
				+ "                   FROM   Mg_Clientes_Aa x, "
				+ "                          (SELECT MAX(Anio) Anio FROM Mg_Clientes_Aa) y "
				+ "                   WHERE  x.Anio = y.Anio " + "                   GROUP  BY y.Anio) "
				+ "           AND    Codigo_Cliente = :cliente " + "           AND    Desembolsado = 'N'), "
				+ "           'N') Aa, " + "       Nvl((SELECT DISTINCT 'S' " + "           FROM   Mg_Clientes_Aplus "
				+ "           WHERE  (Anio, Mes) IN " + "                  (SELECT y.Anio, MAX(Mes) Mes "
				+ "                   FROM   Mg_Clientes_Aplus x, "
				+ "                          (SELECT MAX(Anio) Anio FROM Mg_Clientes_Aa) y "
				+ "                   WHERE  x.Anio = y.Anio " + "                   GROUP  BY y.Anio) "
				+ "           AND    Codigo_Cliente = :cliente " + "           AND    Desembolsado = 'N'), 'N') Aplus, "
				+ "       (SELECT Mg_k_Clientes_Aaa.f_Cliente_Preferencial(:cliente) FROM Dual) Preferencial, "
				+ "       (SELECT Mg_k_Clientes_Aaa.f_Cliente_Preferencial3(:cliente) FROM   Dual) Preferencial3 "
				+ "FROM   Dual ");
		query.setParameter("cliente", codigoCliente);
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			resultado = new ProductosPreAprobados((String) elementoResultado[0], (String) elementoResultado[1],
					(String) elementoResultado[2], (String) elementoResultado[3], (String) elementoResultado[4]);
		}

		return resultado;

	}
}
