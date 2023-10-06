package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ConsolidadoPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPagoServicio;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DatosPrestamo;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ParametroMontoPermitido;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.RubroFacilito;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AbonoPagoPrestamo;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.AutorizaTransaccion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.PagoServicio;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaLocal;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.TransferenciaOtroBanco;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioTransaccionCliente;
import ec.fin.online15.interfaces.IServicioTransaccionClienteWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioTransaccionClienteWs implements IServicioTransaccionClienteWs {

	@EJB
	private ServicioTransaccionCliente servicioTransaccionCliente;

	public TransferenciaLocal transferenciaLocalCliente(String sesion, TransferenciaLocal transferencia) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-transferenciaLocalCliente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.tranferenciaLocal(transferencia);
	}

	public TransferenciaOtroBanco transferenciaOtroBancoCliente(String sesion,
			TransferenciaOtroBanco transferenciaOtroBanco) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-transferenciaOtroBancoCliente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.transferenciaOtroBancoCliente(transferenciaOtroBanco);
	}

	public AbonoPagoPrestamo abonoPagoPrestamoCliente(String sesion, AbonoPagoPrestamo abonoPagoPrestamo) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-abonoPagoPrestamoCliente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.abonoPagoPrestamoCliente(abonoPagoPrestamo);
	}

	public ParametroMontoPermitido parametroMontosTransaccion(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-parametroMontosTransaccion");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.parametroMontosTransaccion(codigoCliente);
	}

	public List<ListadoOpciones> listaCuentasCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listaCuentasCliente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listaCuentasCliente(codigoCliente);
	}

	public Double saldoCuentaAhorro(String sesion, Long numeroCuenta) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-saldoCuentaAhorro");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.saldoCuentaAhorro(numeroCuenta);
	}

	public String nombreCuentaAhorro(String sesion, Long numeroCuenta) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-nombreCuentaAhorro");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.nombreCuentaAhorro(numeroCuenta);
	}

	public void registraMovimiento(String sesion, TwebMovimientos movimiento) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-registraMovimiento");
		log.info("SESION: " + sesion);
		servicioTransaccionCliente.registraMovimiento(movimiento);
	}

	public AutorizaTransaccion consultaAutorizacionTransaccion(String sesion, AutorizaTransaccion autorizaTransaccion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-consultaAutorizacionTransaccion");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.consultaAutorizacionTransaccion(autorizaTransaccion);
	}

	public List<ListadoOpciones> listadoEntidadesFinancieras(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoEntidadesFinancieras");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoEntidadesFinancieras();
	}

	public List<ListadoOpciones> listadoTipoCuenta(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoTipoCuenta");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoTipoCuenta();
	}

	public ParametroMontoPermitido parametroMontosTransaccionCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-parametroMontosTransaccionCliente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.parametroMontosTransaccionCliente(codigoCliente);
	}

	public Double consultaComision(String sesion, Integer tipoComision) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-consultaComision");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.consultaComision(tipoComision);
	}

	public List<DatosPrestamo> consultaDatosPrestamo(String sesion, Long numeroPrestamo) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-consultaDatosPrestamo");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.consultaDatosPrestamo(numeroPrestamo);
	}

	public List<ListadoOpciones> listaPrestamoCLiente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listaPrestamoCLiente");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listaPrestamoCLiente(codigoCliente);
	}

	public List<ListadoOpciones> listaEmpresasPagoServicio(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listaEmpresasPagoServicio");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listaEmpresasPagoServicio();
	}

	public List<ListadoOpciones> listaServiciosEmpresa(String sesion, Integer codigoEmpresa) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listaServiciosEmpresa");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listaServiciosEmpresa(codigoEmpresa);
	}

	public PagoServicio pagoServicioWeb(String sesion, PagoServicio pagoServicio) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-pagoServicioWeb");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.pagoServicio(pagoServicio);
	}

	public List<DatosPagoServicio> consultaPagoServicio(String sesion, String numeroIdentificacion,
			Integer codigoTipoServicio, String numeroServicio) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-consultaPagoServicio");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.consultaPagoServicio(numeroIdentificacion, codigoTipoServicio,
				numeroServicio);
	}

	public ConsolidadoPagoServicio consultaConsolidadoServicio(String sesion, String numeroIdentificacion,
			Integer codigoTipoServicio, String numeroServicio) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-consultaConsolidadoServicio");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.consultaConsolidadoServicio(numeroIdentificacion, codigoTipoServicio,
				numeroServicio);
	}

	public List<RubroFacilito> listadoRecargasFacilito(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoRecargasFacilito");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoRecargasFacilito();
	}

	public List<ListadoOpciones> listadoGruposFacilito(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoGruposFacilito");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoGruposFacilito();
	}

	public List<ListadoOpciones> listadoGruposRecaudacionFacilito(String sesion) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoGruposRecaudacionFacilito");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoGruposRecaudacionFacilito();
	}

	public List<RubroFacilito> listadoRubrosPorGrupoFacilito(String sesion, String grupo) {
		log.info("Invocacion web service : ServicioTransaccionClienteWs-listadoRubrosPorGrupoFacilito");
		log.info("SESION: " + sesion);
		return servicioTransaccionCliente.listadoRubrosPorGrupoFacilito(grupo);
	}

}
