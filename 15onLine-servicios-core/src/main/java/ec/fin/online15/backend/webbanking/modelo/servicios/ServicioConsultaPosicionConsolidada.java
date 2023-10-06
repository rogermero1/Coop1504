package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
import ec.fin.online15.backend.consultas.modelo.repositorios.webbanking.PosicionConsolidadEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Stateless
public class ServicioConsultaPosicionConsolidada  {

	@EJB
	private PosicionConsolidadEAO posicionConsolidadaEAO;

	public List<PosicionConsolidadaAhorro> consultaPosicionConsolidadaAhorro(
			Integer codigoCliente) {		
		log.info("2. SERVICIOCONSULTAPOSICIONCONSOLIDAD Invocacion a EAO => posicionConsolidadaEAO-posicionConsolidadaAhorro");
		log.info("2. Codigo de cliente aqui: "+codigoCliente);
		return posicionConsolidadaEAO.posicionConsolidadaAhorro(codigoCliente); //pasar identificacion aqui
	}

	public List<PosicionConsolidadaPrestamo> consultaPosicioConsolidadaPrestamo(
			Integer codigoCliente) {
		return posicionConsolidadaEAO
				.posicionConsolidadaPrestamo(codigoCliente);
	}

	public List<PosicionConsolidadaInversion> consultaPosicioConsolidadaInversion(
			Integer codigoCliente) {
		return posicionConsolidadaEAO
				.posicionConsolidadaInversion(codigoCliente);
	}

	public List<PosicionConsolidadaSeguro> consultaPosicioConsolidadaSeguro(
			Integer codigoCliente) {
		return posicionConsolidadaEAO.posicionConsolidadSeguro(codigoCliente);
	}

	public List<UltimosMovimientosAhorro> ultimosMovimientosAhorro(
			String numeroCuenta) {
		return posicionConsolidadaEAO.ultimosMovientosAhorro(numeroCuenta);
	}

	public List<CuotasPrestamo> consultaCuotasPrestamo(Long numeroPrestamo) {
		return posicionConsolidadaEAO.consultaCuotasPrestamo(numeroPrestamo);
	}

	public List<CuotasPrestamo> consultaCuotasGracia(Long numeroPrestamo) {
		return posicionConsolidadaEAO.consultaCuotasGracia(numeroPrestamo);
	}

	   
	public List<DetalleCuotaPrestamo> consultaDetalleCuotaPrestamo(
			Integer numeroCuota, Long numeroPrestamo) {
		return posicionConsolidadaEAO.consultaDetalleCuotaPrestamo(numeroCuota,
				numeroPrestamo);
	}

	   
	public List<SaldosBloqueados> consultaSaldosBloqueados(String numeroCuenta) {
		return posicionConsolidadaEAO.consultaSaldosBloqueados(numeroCuenta);
	}

	   
	public List<SaldosReserva> consultaSaldosReserva(String numeroCuenta) {
		return posicionConsolidadaEAO.consultaSaldosReserva(numeroCuenta);
	}

	   
	public List<UltimosMovimientosAhorro> consultaHistoricoAhorro(
			String numeroCuenta, Date fechaDesde, Date fechaHasta) {
		return posicionConsolidadaEAO.consultaHistoricoAhorro(numeroCuenta,
				fechaDesde, fechaHasta);
	}

	   
	public List<TwebMovimientos> listadoMovimientos(Long idUsuario,
			Integer codigoTransaccion, String fechaDesde, String fechaHasta) {
		return posicionConsolidadaEAO.listadoMovimientos(idUsuario,
				codigoTransaccion, fechaDesde, fechaHasta);
	}

	   
	public String nombreFinanciera(Integer idTipoFinanciera,
			Integer codigoFinanciera) {
		return posicionConsolidadaEAO.nombreFinanciera(idTipoFinanciera,
				codigoFinanciera);
	}

	   
	public String nombreServicio(Integer codigoTipoServicio) {
		return posicionConsolidadaEAO.nombreServicio(codigoTipoServicio);
	}

	   
	public ProductosPreAprobados ProductosPreaprobados(Integer codigoCliente) {
		return posicionConsolidadaEAO.productosPreaprobados(codigoCliente);
	}

}
