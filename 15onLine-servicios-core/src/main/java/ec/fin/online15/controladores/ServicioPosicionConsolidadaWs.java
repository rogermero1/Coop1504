package ec.fin.online15.controladores;

import java.util.Date;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
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
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioConsultaPosicionConsolidada;
import ec.fin.online15.interfaces.IServicioPosicionConsolidadaWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioPosicionConsolidadaWs implements IServicioPosicionConsolidadaWs {

	@EJB
	private ServicioConsultaPosicionConsolidada servicioPosicionConsolidada;

	public List<PosicionConsolidadaAhorro> consultaPosicionConsolidadaAhorro(String sesion, Integer codigoCliente) {
		log.info("=======================INICIO CONSULTA AHORRO ===========================");
		log.info("1. Invocacion web service -WS : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaAhorro");
		log.info("1. Sesion -WS: "+sesion);
		return servicioPosicionConsolidada.consultaPosicionConsolidadaAhorro(297961);		//temporalmente codigo de jefesito
	}

	public List<PosicionConsolidadaPrestamo> consultaPosicionConsolidadaPrestamo(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaPrestamo");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaPosicioConsolidadaPrestamo(codigoCliente);
	}

	public List<PosicionConsolidadaInversion> consultaPosicionConsolidadaInversion(String sesion,
			Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaInversion");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaPosicioConsolidadaInversion(codigoCliente);
	}

	public List<PosicionConsolidadaSeguro> consultaPosicionConsolidadaSeguro(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaSeguro");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaPosicioConsolidadaSeguro(codigoCliente);
	}

	public List<UltimosMovimientosAhorro> consultaPosicionUltimosMovimientosAhorro(String sesion, String numeroCuenta) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionUltimosMovimientosAhorro");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.ultimosMovimientosAhorro(numeroCuenta);
	}

	public List<CuotasPrestamo> consultaPosicionConsolidadaCuotasPrestamo(String sesion, Long numeroPrestamo) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaCuotasPrestamo");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaCuotasPrestamo(numeroPrestamo);
	}

	public List<CuotasPrestamo> consultaPosicionConsolidadaCuotasGracia(String sesion, Long numeroPrestamo) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaCuotasGracia");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaCuotasGracia(numeroPrestamo);
	}

	public List<DetalleCuotaPrestamo> consultaPosicionConsolidadaDetalleCuotasPrestamo(String sesion,
			Integer numeroCuota, Long numeroPrestamo) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaDetalleCuotasPrestamo");
		log.info("SESION: " + sesion);
		return servicioPosicionConsolidada.consultaDetalleCuotaPrestamo(numeroCuota, numeroPrestamo);
	}

	public List<SaldosBloqueados> consultaPosicionConsolidadaSaldosBloqueados(String sesion, String numeroCuenta) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaSaldosBloqueados");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.consultaSaldosBloqueados(numeroCuenta);
	}

	public List<SaldosReserva> consultaPosicionConsolidadaSaldosReserva(String sesion, String numeroCuenta) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaPosicionConsolidadaSaldosReserva");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.consultaSaldosReserva(numeroCuenta);
	}

	public List<UltimosMovimientosAhorro> consultaHistoricoAhorro(String sesion, String numeroCuenta, Date fechaDesde,
			Date fechaHasta) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-consultaHistoricoAhorro");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.consultaHistoricoAhorro(numeroCuenta, fechaDesde, fechaHasta);
	}

	public List<TwebMovimientos> listadoMovimientos(String sesion, Long idUsuario, Integer codigoTransaccion,
			String fechaDesde, String fechaHasta) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-listadoMovimientos");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.listadoMovimientos(idUsuario, codigoTransaccion, fechaDesde,
				fechaHasta);
	}

	public String nombreFinanciera(String sesion, Integer idTipoFinanciera, Integer codigoFinanciera) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-nombreFinanciera");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.nombreFinanciera(idTipoFinanciera, codigoFinanciera);
	}

	public String nombreServicio(String sesion, Integer codigoTipoServicio) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-nombreServicio");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.nombreServicio(codigoTipoServicio);
	}

	public ProductosPreAprobados productosPreaprobados(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPosicionConsolidadaWs-ProductosPreAprobados");
		log.info("SESION: " + sesion);
		return this.servicioPosicionConsolidada.ProductosPreaprobados(codigoCliente);
	}

}
