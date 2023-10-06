package ec.fin.online15.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

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
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioPosicionConsolidadaWs {

	@WebMethod(operationName = "consultaPosicionConsolidadaAhorro")
	@WebResult(name = "resultadoConsultaPosicionConsolidadAhorro")
	public List<PosicionConsolidadaAhorro> consultaPosicionConsolidadaAhorro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaPosicionConsolidadaPrestamo")
	@WebResult(name = "resultadoConsultaPosicionConsolidadPrestamo")
	public List<PosicionConsolidadaPrestamo> consultaPosicionConsolidadaPrestamo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaPosicionConsolidadaInversion")
	@WebResult(name = "resultadoConsultaPosicionConsolidadInversion")
	public List<PosicionConsolidadaInversion> consultaPosicionConsolidadaInversion(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaPosicionConsolidadaSeguro")
	@WebResult(name = "resultadoConsultaPosicionConsolidadSeguro")
	public List<PosicionConsolidadaSeguro> consultaPosicionConsolidadaSeguro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaPosicionUltimosMovimientosAhorro")
	@WebResult(name = "resultadoConsultaPosicionUltimosMovimientosAhorro")
	public List<UltimosMovimientosAhorro> consultaPosicionUltimosMovimientosAhorro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") String numeroCuenta);

	@WebMethod(operationName = "consultaPosicionConsolidadaCuotasPrestamo")
	@WebResult(name = "resultadoConsultaPosicionConsolidadaCuotasPrestamo")
	public List<CuotasPrestamo> consultaPosicionConsolidadaCuotasPrestamo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroPrestamo") Long numeroPrestamo);

	@WebMethod(operationName = "consultaPosicionConsolidadaCuotasGracia")
	@WebResult(name = "resultadoConsultaPosicionConsolidadaCuotasGracia")
	public List<CuotasPrestamo> consultaPosicionConsolidadaCuotasGracia(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroPrestamo") Long numeroPrestamo);

	@WebMethod(operationName = "consultaPosicionConsolidadaDetalleCuotasPrestamo")
	@WebResult(name = "resultadoConsultaPosicionConsolidadaDetalleCuotasPrestamo")
	public List<DetalleCuotaPrestamo> consultaPosicionConsolidadaDetalleCuotasPrestamo(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuota") Integer numeroCuota,
			@WebParam(name = "numeroPrestamo") Long numeroPrestamo);

	@WebMethod(operationName = "consultaPosicionConsolidadaSaldosBloqueados")
	@WebResult(name = "resultadoconsultaPosicionConsolidadaSaldosBloqueados")
	public List<SaldosBloqueados> consultaPosicionConsolidadaSaldosBloqueados(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") String numeroCuenta);

	@WebMethod(operationName = "consultaPosicionConsolidadaSaldosReserva")
	@WebResult(name = "resultadoconsultaPosicionConsolidadaSaldosBloqueados")
	public List<SaldosReserva> consultaPosicionConsolidadaSaldosReserva(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") String numeroCuenta);

	@WebMethod(operationName = "consultaHistoricoAhorro")
	@WebResult(name = "resultadoconsultaHistoricoAhorro")
	public List<UltimosMovimientosAhorro> consultaHistoricoAhorro(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") String numeroCuenta,
			@WebParam(name = "fechaDesde") Date fechaDesde,
			@WebParam(name = "fechaHasta") Date fechaHasta);

	@WebMethod(operationName = "consultaListadoMovimientos")
	@WebResult(name = "resultadoConsultaListadoMovimientos")
	public List<TwebMovimientos> listadoMovimientos(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "idUsuario") Long idUsuario,
			@WebParam(name = "codigoTransaccion") Integer codigoTransaccion,
			@WebParam(name = "fechaDesde") String fechaDesde,
			@WebParam(name = "fechaHasta") String fechaHasta);

	@WebMethod(operationName = "consultaNombreFinanciera")
	@WebResult(name = "resultadoConsultaNombreFinanciera")
	public String nombreFinanciera(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "idTipoFinanciera") Integer idTipoFinanciera,
			@WebParam(name = "codigoFinanciera") Integer codigoFinanciera);

	@WebMethod(operationName = "consultaNombreServicio")
	@WebResult(name = "resultadoConsultaNombreServicio")
	public String nombreServicio(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoTipoServicio") Integer codigoTipoServicio);

	@WebMethod(operationName = "consultaProductosPreaprobados")
	@WebResult(name = "resultadoConsultaProductosPreaprobados")
	public ProductosPreAprobados productosPreaprobados(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);
}
