package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebMovimientosEAO;
import ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones.TransferenciasPagosEAO;

@Stateless
public class ServicioTransaccionCliente {

	@EJB
	private TransferenciasPagosEAO transferenciaPagoEAO;
	@EJB
	private TwebMovimientosEAO movimientoEAO;

	public TransferenciaLocal tranferenciaLocal(TransferenciaLocal transferenciaLocal) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => tranferenciaLocal");
		return transferenciaPagoEAO.transferenciaLocalCliente(transferenciaLocal);
	}

	public TransferenciaOtroBanco transferenciaOtroBancoCliente(TransferenciaOtroBanco transferenciaOtroBanco) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => transferenciaOtroBancoCliente");
		return transferenciaPagoEAO.transferenciaOtroBancoCliente(transferenciaOtroBanco);
	}

	public AbonoPagoPrestamo abonoPagoPrestamoCliente(AbonoPagoPrestamo abonoPagoPrestamo) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => abonoPagoPrestamoCliente");
		return transferenciaPagoEAO.abonoPagoPrestamoCliente(abonoPagoPrestamo);
	}

	public ParametroMontoPermitido parametroMontosTransaccion(Integer codigoCliente) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => parametroMontosTransaccion");
		return transferenciaPagoEAO.parametroMontosTransaccion(codigoCliente);
	}

	public List<ListadoOpciones> listaCuentasCliente(Integer codigoCliente) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => listaCuentasCliente");
		return transferenciaPagoEAO.listaCuentasCliente(codigoCliente);
	}

	public Double saldoCuentaAhorro(Long numeroCuenta) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => saldoCuentaAhorro");
		return transferenciaPagoEAO.saldoCuentaAhorro(numeroCuenta);
	}

	public String nombreCuentaAhorro(Long numeroCuenta) {
		System.out.println("ServicioTransaccionCliente - IServicioTransaccionCliente => nombreCuentaAhorro");
		return transferenciaPagoEAO.nombreCuentaAhorro(numeroCuenta);
	}

	public void registraMovimiento(TwebMovimientos movimiento) {
		movimientoEAO.crear(movimiento);
	}

	public AutorizaTransaccion consultaAutorizacionTransaccion(AutorizaTransaccion autorizaTransaccion) {
		return transferenciaPagoEAO.consultaAutorizacionTransaccion(autorizaTransaccion);
	}

	public List<ListadoOpciones> listadoEntidadesFinancieras() {
		return transferenciaPagoEAO.listadoEntidadesFinancieras();
	}

	public List<ListadoOpciones> listadoTipoCuenta() {
		return transferenciaPagoEAO.listadoTipoCuenta();
	}

	public ParametroMontoPermitido parametroMontosTransaccionCliente(Integer codigoCliente) {
		return transferenciaPagoEAO.parametroMontosTransaccionCliente(codigoCliente);
	}

	public Double consultaComision(Integer tipoComision) {
		return transferenciaPagoEAO.consultaComision(tipoComision);
	}

	public List<DatosPrestamo> consultaDatosPrestamo(Long numeroPrestamo) {
		return transferenciaPagoEAO.consultaDatosPrestamo(numeroPrestamo);
	}

	public List<ListadoOpciones> listaPrestamoCLiente(Integer codigoCliente) {
		return transferenciaPagoEAO.listaPrestamosCliente(codigoCliente);
	}

	public List<ListadoOpciones> listaEmpresasPagoServicio() {
		return transferenciaPagoEAO.listaEmpresaPagoServicio();
	}

	public List<ListadoOpciones> listaServiciosEmpresa(Integer codigoEmpresa) {
		return transferenciaPagoEAO.listaServiciosEmpresa(codigoEmpresa);
	}

	public PagoServicio pagoServicio(PagoServicio pagoServicio) {
		return transferenciaPagoEAO.trxPagoServicio(pagoServicio);
	}

	public List<DatosPagoServicio> consultaPagoServicio(String numeroIdentificacion, Integer codigoTipoServicio,
			String numeroServicio) {
		return transferenciaPagoEAO.consultaPagoServicio(numeroIdentificacion, codigoTipoServicio, numeroServicio);
	}

	public ConsolidadoPagoServicio consultaConsolidadoServicio(String numeroIdentificacion, Integer codigoTipoServicio,
			String numeroServicio) {
		return transferenciaPagoEAO.consultaConsolidadoServicio(numeroIdentificacion, codigoTipoServicio,
				numeroServicio);
	}

	public List<RubroFacilito> listadoRecargasFacilito() {
		return transferenciaPagoEAO.listadoRecargasFacilito();
	}

	public List<ListadoOpciones> listadoGruposFacilito() {
		return transferenciaPagoEAO.listadoGruposFacilito();
	}
	
	public List<ListadoOpciones> listadoGruposRecaudacionFacilito() {
		return transferenciaPagoEAO.listadoGruposRecaudacionFacilito();
	}

	public List<RubroFacilito> listadoRubrosPorGrupoFacilito(String grupo) {
		return transferenciaPagoEAO.listadoRubrosPorGrupoFacilito(grupo);
	}
	
	

}
