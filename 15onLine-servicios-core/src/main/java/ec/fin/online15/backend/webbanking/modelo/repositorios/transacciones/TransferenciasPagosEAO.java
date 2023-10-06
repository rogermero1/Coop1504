package ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ConsolidadoPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ParametroMontoPermitido;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.RubroFacilito;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AbonoPagoPrestamo;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AbonoPagoPrestamoSP;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AutorizaTransaccion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AutorizaTransaccionSP;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.PagoServicio;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.PagoServicioSP;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaLocal;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaLocalSP;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaOtroBanco;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaOtroBancoSP;

@SuppressWarnings("rawtypes")
@Stateless
@LocalBean
public class TransferenciasPagosEAO extends EAOSeed {

	public TransferenciaLocal transferenciaLocalCliente(TransferenciaLocal transferenciaLocal) {
		TransferenciaLocalSP transferencia = new TransferenciaLocalSP(transferenciaLocal);
		this.invocacionJDBC(transferencia);
		return transferencia.getTransferenciaLocal();
	}

	public TransferenciaOtroBanco transferenciaOtroBancoCliente(TransferenciaOtroBanco transferenciaOtroBanco) {
		TransferenciaOtroBancoSP transferencia = new TransferenciaOtroBancoSP(transferenciaOtroBanco);
		this.invocacionJDBC(transferencia);
		return transferencia.getTransferenciaOtroBanco();
	}

	public AbonoPagoPrestamo abonoPagoPrestamoCliente(AbonoPagoPrestamo abonoPagoPrestamo) {
		AbonoPagoPrestamoSP abonoPrestamo = new AbonoPagoPrestamoSP(abonoPagoPrestamo);
		this.invocacionJDBC(abonoPrestamo);
		return abonoPrestamo.getAbonoPagoPrestamo();
	}

	public PagoServicio trxPagoServicio(PagoServicio pagoServicio) {
		PagoServicioSP pagoServicioSp = new PagoServicioSP(pagoServicio);
		this.invocacionJDBC(pagoServicioSp);
		return pagoServicioSp.getPagoServicio();
	}

	public AutorizaTransaccion consultaAutorizacionTransaccion(AutorizaTransaccion autorizaTransaccion) {
		AutorizaTransaccionSP autorizacion = new AutorizaTransaccionSP(autorizaTransaccion);
		this.invocacionJDBC(autorizacion);
		return autorizacion.getAutorizaTransaccion();
	}

	/* Comisiones */

	public Double consultaComision(Integer tipoComision) {
		Integer transaccion = 0;
		Double valorComision = 0.00;
		if (tipoComision == 1) {
			transaccion = 454;
		} else if (tipoComision == 2) {
			transaccion = 651;
		}

		Query query = getEntityManager()
				.createNativeQuery("SELECT to_char(Cargo_Minimo) " + "FROM   Mg_Transacciones_Especiales "
						+ "WHERE  Codigo_Aplicacion = 'BCA' " + "AND    Codigo_Tipo_Transaccion = ? "
						+ "AND    EXISTS (SELECT 'x' " + "        FROM   Mg_Tipos_De_Transacciones "
						+ "        WHERE  Codigo_Aplicacion = 'BCA' " + "        AND    Codigo_Tipo_Transaccion = ?)");
		query.setParameter(1, transaccion);
		query.setParameter(2, transaccion);
		@SuppressWarnings("unchecked")
		List<String> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			valorComision = 0.00;
		} else {
			for (String valor : resultado) {
				valorComision = Double.parseDouble(valor.replace(",", "."));
			}
		}
		return valorComision;
	}

	/* Consultas de parametrizacion */
	public ParametroMontoPermitido parametroMontosTransaccion(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery("SELECT b.Monto_Minimo, b.Monto_Diario, b.Monto_Maximo_Trx "
				+ "FROM   Mg_Clientes a, Mg_Grupo_Canales_Electronicos b " + "WHERE  a.Codigo_Cliente = ? "
				+ "AND    a.Grupo_Canal_Electronico = b.Codigo_Grupo " + "AND    b.Codigo_Tipo_Canal = 1");
		query.setParameter(1, codigoCliente);
		ParametroMontoPermitido parametrosPermitidos = new ParametroMontoPermitido();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			parametrosPermitidos.setMontoMaximoDiario(0.00);
			parametrosPermitidos.setMontoMinimoDiario(0.00);
			parametrosPermitidos.setMontoTransaccion(0.00);
		} else {
			for (Object[] resultado : listaResultado) {
				parametrosPermitidos.setMontoMinimoDiario(((BigDecimal) resultado[0]).doubleValue());
				parametrosPermitidos.setMontoMaximoDiario(((BigDecimal) resultado[1]).doubleValue());
				parametrosPermitidos.setMontoTransaccion(((BigDecimal) resultado[2]).doubleValue());
			}
		}
		return parametrosPermitidos;
	}

	/* Consulta montos permitidos transaccionales del cliente */
	public ParametroMontoPermitido parametroMontosTransaccionCliente(Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT Monto_Minimo_Transaccion, Monto_Diario, Monto_Maximo_Transaccion "
						+ "FROM   Tweb_Perfil_Transaccional " + "WHERE  Codigo_Cliente = ? "
						+ "AND    Id_Canal_Electronico = 1 "
						+ "AND    Estado = 'A'");
		query.setParameter(1, codigoCliente);
		ParametroMontoPermitido parametrosPermitidos = new ParametroMontoPermitido();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			parametrosPermitidos.setMontoMaximoDiario(0.00);
			parametrosPermitidos.setMontoMinimoDiario(0.00);
			parametrosPermitidos.setMontoTransaccion(0.00);
		} else {
			for (Object[] resultado : listaResultado) {
				parametrosPermitidos.setMontoMinimoDiario(((BigDecimal) resultado[0]).doubleValue());
				parametrosPermitidos.setMontoMaximoDiario(((BigDecimal) resultado[1]).doubleValue());
				parametrosPermitidos.setMontoTransaccion(((BigDecimal) resultado[2]).doubleValue());
			}
		}
		return parametrosPermitidos;
	}

	/* Consulta datos de un prestamo */
	@SuppressWarnings("unchecked")
	public List<DatosPrestamo> consultaDatosPrestamo(Long numeroPrestamo) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT Pr_k_Saldos.Pr_f_Cuotas_Atrasadas_Calendar(?) Cuotas_Atrasadas, "
						+ "Pr_k_Saldos.Pr_f_Cuotas_Pendientes(?) Cuotas_Pendientes, "
						+ "Pr_k_Saldos.Pr_f_Valor_Adeudado(?) Valor_Adeudado, "
						+ "Pr_k_Saldos.Pr_f_Saldo_Capital(?) Saldo_Capital, "
						+ "Pr_k_Saldos.Pr_f_Saldo_Total(?) Saldo_Total " + "FROM   Dual");
		query.setParameter(1, numeroPrestamo);
		query.setParameter(2, numeroPrestamo);
		query.setParameter(3, numeroPrestamo);
		query.setParameter(4, numeroPrestamo);
		query.setParameter(5, numeroPrestamo);
		List<DatosPrestamo> listaDatosPrestamo = new ArrayList<DatosPrestamo>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			DatosPrestamo datoPrestamo = new DatosPrestamo();
			datoPrestamo.setCuotasAtrasadas(((BigDecimal) resultado[0]).intValue());
			datoPrestamo.setCuotasPedientes(((BigDecimal) resultado[1]).intValue());
			datoPrestamo.setValorAdeudado(((BigDecimal) resultado[2]).doubleValue());
			datoPrestamo.setSaldoCapital(((BigDecimal) resultado[3]).doubleValue());
			datoPrestamo.setSaldoTotal(((BigDecimal) resultado[4]).doubleValue());
			listaDatosPrestamo.add(datoPrestamo);
		}
		return listaDatosPrestamo;
	}

	public List<ListadoOpciones> listaCuentasCliente(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery("SELECT to_char(Numero_Cuenta), "
				+ "Numero_Cuenta || ' Saldo : ' || " + "(Saldo_Total_En_Linea - Saldo_Embargado_En_Linea - "
				+ "Saldo_Pignorado_En_Linea - Saldo_Reserva_En_Linea) Descripcion "
				+ "FROM   Ca_Cuentas_De_Ahorro a, Mg_Cuentas_x_Cliente b " + "WHERE  a.Numero_Cuenta = b.Codigo_Cuenta "
				+ "AND    a.Situacion_En_Linea != 1 " + "AND    a.Clase_De_Cuenta NOT IN (4,6) "
				+ "AND    b.Clase_De_Cliente IN ('P', 'S') "
				// + "AND b.Firmantes = 'S' "
				+ "AND    ((b.Clase_De_Cliente = 'P' AND b.Codigo_Cliente = a.Codigo_Cliente) OR "
				+ "	      (b.Clase_De_Cliente = 'S' AND a.Clase_De_Cuenta = 2)) " + "AND    b.Codigo_Cliente = ? "
				+ "ORDER  BY b.Clase_De_Cliente");
		/*
		 * + "FROM   Ca_Cuentas_De_Ahorro " +
		 * "WHERE  situacion_en_linea != 1 and Codigo_Cliente = ?");
		 */
		query.setParameter(1, codigoCliente);
		List<ListadoOpciones> listadoCuentas = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones cuenta = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoCuentas.add(cuenta);
		}
		return listadoCuentas;
	}

	public List<ListadoOpciones> listaPrestamosCliente(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery("SELECT To_Char(Numero_Prestamo), "
				+ "Numero_Prestamo || ' => ' || " + "TRIM(To_Char(Monto_Inicial, '$999999999.00')) "
				+ "FROM   Pr_Prestamos " + "WHERE  Codigo_Cliente = ? " + "AND estado = '0'");
		query.setParameter(1, codigoCliente);
		List<ListadoOpciones> listadoCuentas = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones cuenta = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoCuentas.add(cuenta);
		}
		return listadoCuentas;
	}

	public Double saldoCuentaAhorro(Long numeroCuenta) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT to_char(Saldo_Total_En_Linea - Saldo_Embargado_En_Linea - "
						+ "Saldo_Pignorado_En_Linea - Saldo_Reserva_En_Linea) Saldo " + "FROM   Ca_Cuentas_De_Ahorro "
						+ "WHERE  Numero_Cuenta = Nvl(?,0)");
		query.setParameter(1, numeroCuenta);
		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			return 0.00;
		} else {
			return Double.valueOf(listaResultado.get(0).toString().replace(",", "."));
		}
	}

	public String nombreCuentaAhorro(Long numeroCuenta) {
		Query query = getEntityManager().createNativeQuery("SELECT Decode(a.Codigo_Tipo_Identificacion, " + "2, "
				+ "a.Razon_Social, " + "a.Primer_Apellido || ' ' || a.Segundo_Apellido || ' ' || "
				+ "a.Nombres) Nombre_Cuenta " + "FROM   Mg_Clientes a, Ca_Cuentas_De_Ahorro b "
				+ "WHERE  a.Codigo_Cliente = b.Codigo_Cliente " + "AND    b.Clase_De_Cuenta NOT IN (4,6) AND b.Situacion_En_Linea != 1 "
				+ "AND    b.Numero_Cuenta = Nvl(?,0)");
		query.setParameter(1, numeroCuenta);
		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			return "";
		} else {
			return listaResultado.get(0).toString();
		}
	}

	public List<ListadoOpciones> listadoEntidadesFinancieras() {
		Query query = getEntityManager().createNativeQuery(
				// "SELECT To_Char(Tipo_Financiera || '-' || Codigo_Financiera), Nombre " +
				// "FROM Mg_Financieras "
				"SELECT Cuenta_Bce, Nombre FROM Mg_Financieras " + "WHERE  Cuenta_Bce IS NOT NULL "
						+ "AND Habilitado_Spi = 'S' " + "ORDER  BY 2");
		List<ListadoOpciones> listadoFinancieras = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones financiera = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoFinancieras.add(financiera);
		}
		return listadoFinancieras;
	}

	public List<ListadoOpciones> listadoTipoCuenta() {
		Query query = getEntityManager().createNativeQuery(
				"SELECT to_char(codigo), tipo_cuenta FROM Gi_Tipos_Cuenta_Spi WHERE Codigo IN (1, 2)");

		List<ListadoOpciones> listadoTipoCuenta = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones tipoCuenta = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoTipoCuenta.add(tipoCuenta);
		}
		return listadoTipoCuenta;
	}

	/* PAGO DE SERVICIOS */
	/* LISTA EMPRESAS */
	@SuppressWarnings("unchecked")
	public List<ListadoOpciones> listaEmpresaPagoServicio() {
		Query query = getEntityManager()
				.createNativeQuery("SELECT To_Char(Codigo_Empresa), Descripcion FROM Ps_Empresa");
		List<ListadoOpciones> listadoEmpresas = new ArrayList<ListadoOpciones>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones empresa = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoEmpresas.add(empresa);
		}
		return listadoEmpresas;
	}

	/* LISTA SERVICIOS */
	@SuppressWarnings("unchecked")
	public List<ListadoOpciones> listaServiciosEmpresa(Integer codigoEmpresa) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT To_Char(Codigo_Tipo_Servicio), Descripcion " + "FROM   Ps_Tipo_Servicio "
						+ "WHERE  Estado = 'A' " + "AND    Codigo_Empresa = ? " + "ORDER  BY Descripcion");
		query.setParameter(1, codigoEmpresa);
		List<ListadoOpciones> listadoServicios = new ArrayList<ListadoOpciones>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ListadoOpciones servicio = new ListadoOpciones((String) resultado[0], (String) resultado[1]);
			listadoServicios.add(servicio);
		}
		return listadoServicios;
	}

	/* CONSULTA DATOS DE PAGO DE SERVICIO */
	@SuppressWarnings("unchecked")
	public List<DatosPagoServicio> consultaPagoServicio(String numeroIdentificacion, Integer codigoTipoServicio,
			String numeroServicio) {
		Query query = getEntityManager().createNativeQuery("SELECT a.Codigo_Tipo_Servicio, "
				+ "to_char(a.Numero_Identificacion), " + "to_char(a.Numero_Servicio), " + "a.Valor, "
				+ "a.Valor_Cancelado, " + "a.Fecha_Corte, " + "To_Char(a.Mes) Mes, " + "To_Char(a.Anio) Anio, "
				+ "To_Char(a.Secuencia_Empresa) secuencia, " + "To_Char('N') Pagado, " + "c.Cargo_Minimo, "
				+ "b.descripcion, " + "b.maestro_detalle, " + "a.Valor * b.Porcentaje_Minimo / 100 Pago_Minimo "
				+ "FROM   Ps_Dato_Externo a, Ps_Tipo_Servicio b, " + "Mg_Transacciones_Especiales c "
				+ "WHERE  trim(nvl(a.Numero_Identificacion,0)) =  "
				+ "      Nvl(trim(?), trim(nvl(a.Numero_Identificacion,0))) "
				+ "AND    a.Codigo_Tipo_Servicio = b.Codigo_Tipo_Servicio " + "AND    b.Estado = 'A' "
				+ "AND    b.Codigo_Trx_Comision = c.Codigo_Tipo_Transaccion " + "AND    c.Codigo_Aplicacion = 'BCA' "
				+ "AND    a.Codigo_Tipo_Servicio = ? "
				+ "AND    trim(To_Number(a.Numero_Servicio)) = Nvl(trim(To_Number(?)), trim(To_Number(a.Numero_Servicio))) "
				+ "AND    a.Valor != a.Valor_Cancelado " + "ORDER  BY Mes, Anio ASC");
		query.setParameter(1, numeroIdentificacion);
		query.setParameter(2, codigoTipoServicio);
		query.setParameter(3, numeroServicio);
		List<DatosPagoServicio> listaDatosPagoServicio = new ArrayList<DatosPagoServicio>();
		List<Object[]> listaResultado = query.getResultList();
		Integer id = 0;
		for (Object[] resultado : listaResultado) {
			id += 1;
			DatosPagoServicio datoServicio = new DatosPagoServicio();
			datoServicio.setId(id);
			datoServicio.setCodigoTipoServicio(((BigDecimal) resultado[0]).intValue());
			datoServicio.setNumeroIdentificacion((String) resultado[1]);
			datoServicio.setNumeroServicio((String) resultado[2]);
			datoServicio.setValorAdeudado(((BigDecimal) resultado[3]).doubleValue());
			datoServicio.setValorCancelado(((BigDecimal) resultado[4]).doubleValue());
			datoServicio.setFechaCorte((Date) resultado[5]);
			datoServicio.setMes((String) resultado[6]);
			datoServicio.setAnio((String) resultado[7]);
			datoServicio.setSecuenciaEmpresa((String) resultado[8]);
			datoServicio.setPagado("N");
			datoServicio.setEstadoPago(true);
			datoServicio.setCargoMinimo(((BigDecimal) resultado[10]).doubleValue());
			datoServicio.setDescripcionServicio((String) resultado[11]);
			datoServicio.setMaestroDetalle((String) resultado[12]);
			datoServicio.setValorMinimoPago(((BigDecimal) resultado[13]).doubleValue());
			listaDatosPagoServicio.add(datoServicio);
		}
		return listaDatosPagoServicio;
	}

	@SuppressWarnings("unchecked")
	public ConsolidadoPagoServicio consultaConsolidadoServicio(String numeroIdentificacion, Integer codigoTipoServicio,
			String numeroServicio) {
		Query query = getEntityManager().createNativeQuery("SELECT SUM(a.Valor) - SUM(a.Valor_Cancelado), "
				+ "SUM(a.Valor_Cancelado), " + "SUM(c.Cargo_Minimo), "
				+ "SUM(a.Valor * b.Porcentaje_Minimo / 100) Pago_Minimo, " + "a.Nombre "
				+ "FROM   Ps_Dato_Externo             a, " + "       Ps_Tipo_Servicio            b, "
				+ "       Mg_Transacciones_Especiales c, " + "       Ps_Tipo_Servicio            d "
				+ "WHERE  TRIM(nvl(a.Numero_Identificacion,0)) = "
				+ "       Nvl(TRIM(?), TRIM(nvl(a.Numero_Identificacion,0))) "
				+ "AND    a.Codigo_Tipo_Servicio = b.Codigo_Tipo_Servicio "
				+ "AND    a.Codigo_Tipo_Servicio = d.Codigo_Tipo_Servicio " + "AND    d.Estado = 'A' "
				+ "AND    b.Codigo_Trx_Comision = c.Codigo_Tipo_Transaccion " + "AND    c.Codigo_Aplicacion = 'BCA' "
				+ "AND    a.Codigo_Tipo_Servicio = ? " + "AND    TRIM(To_Number(a.Numero_Servicio)) = "
				+ "       Nvl(TRIM(To_Number(?)), TRIM(To_Number(a.Numero_Servicio))) "
				+ "AND    a.Valor != a.Valor_Cancelado " + "GROUP  BY a.Nombre");
		query.setParameter(1, numeroIdentificacion);
		query.setParameter(2, codigoTipoServicio);
		query.setParameter(3, numeroServicio);
		List<ConsolidadoPagoServicio> listaConsolidado = new ArrayList<ConsolidadoPagoServicio>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			ConsolidadoPagoServicio consolidado = new ConsolidadoPagoServicio(((BigDecimal) resultado[0]).doubleValue(),
					((BigDecimal) resultado[1]).doubleValue(), ((BigDecimal) resultado[2]).doubleValue(),
					((BigDecimal) resultado[3]).doubleValue(), (String) resultado[4]);
			listaConsolidado.add(consolidado);
		}
		if (listaConsolidado.isEmpty()) {
			ConsolidadoPagoServicio consolidadoPago = new ConsolidadoPagoServicio(0.00, 0.00, 0.00, 0.00, "SN");
			return consolidadoPago;
		} else {
			return listaConsolidado.get(0);
		}
	}

	public List<RubroFacilito> listadoRecargasFacilito() {
		Query query = getEntityManager().createNativeQuery(
				"SELECT Identidad, Producto, Nombre, Tipo_Trx_Pago, Tipo_Pago, Initcap(Titulo_Referencia), Valores, Pago_Especial "
						+ "FROM   Ps_Transacciones_Facilito WHERE  Grupo = 'RECARGAS' AND    Activo_Coop = 'S'");
		List<RubroFacilito> listadoRubros = new ArrayList<RubroFacilito>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			RubroFacilito rubro = new RubroFacilito((String) resultado[0], (String) resultado[1], (String) resultado[2],
					(String) resultado[3], (String) resultado[4], (String) resultado[5], (String) resultado[6],
					(String) resultado[7]);
			listadoRubros.add(rubro);
		}
		return listadoRubros;
	}

	public List<ListadoOpciones> listadoGruposFacilito() {
		Query query = getEntityManager().createNativeQuery("SELECT DISTINCT Grupo FROM   Ps_Transacciones_Facilito "
				+ "WHERE  Activo_Coop = 'S' AND    Grupo != 'RECARGAS' "
				+ "AND    Tipo_Trx_Pago NOT IN ('010030', '010031') ORDER  BY 1");
		List<ListadoOpciones> listadoGrupos = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		for (String resultado : listaResultado) {
			listadoGrupos.add(new ListadoOpciones(resultado, resultado));
		}
		return listadoGrupos;
	}

	public List<ListadoOpciones> listadoGruposRecaudacionFacilito() {
		Query query = getEntityManager().createNativeQuery("SELECT DISTINCT Grupo FROM   Ps_Transacciones_Facilito "
				+ "WHERE  Activo_Coop = 'S' AND    Grupo != 'RECARGAS' "
				+ "AND    Tipo_Trx_Pago IN ('010030', '010031') ORDER  BY 1");
		List<ListadoOpciones> listadoGrupos = new ArrayList<ListadoOpciones>();
		@SuppressWarnings("unchecked")
		List<String> listaResultado = query.getResultList();
		for (String resultado : listaResultado) {
			listadoGrupos.add(new ListadoOpciones(resultado, resultado));
		}
		return listadoGrupos;
	}

	public List<RubroFacilito> listadoRubrosPorGrupoFacilito(String grupo) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT Identidad, Producto, Nombre, Tipo_Trx_Pago, Tipo_Pago, Initcap(Nvl(Titulo_Referencia,'Referencia')), Valores, Pago_Especial "
						+ "FROM   Ps_Transacciones_Facilito WHERE  Grupo = ? AND Activo_Coop = 'S' ORDER  BY Nombre");
		query.setParameter(1, grupo);
		List<RubroFacilito> listadoRubros = new ArrayList<RubroFacilito>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] resultado : listaResultado) {
			RubroFacilito rubro = new RubroFacilito((String) resultado[0], (String) resultado[1], (String) resultado[2],
					(String) resultado[3], (String) resultado[4], (String) resultado[5], (String) resultado[6],
					(String) resultado[7]);
			listadoRubros.add(rubro);
		}
		return listadoRubros;
	}

}
