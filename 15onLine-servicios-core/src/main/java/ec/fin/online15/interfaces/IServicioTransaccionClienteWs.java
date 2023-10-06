package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioTransaccionClienteWs {

	@WebMethod(operationName = "transferenciaLocalCliente")
	@WebResult(name = "resultadoTransferenciaLocal")
	public TransferenciaLocal transferenciaLocalCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "transferenciaLocal") TransferenciaLocal transferencia);

	@WebMethod(operationName = "transferenciaOtroBancoCliente")
	@WebResult(name = "resultadoTransferenciaOtroBanco")
	public TransferenciaOtroBanco transferenciaOtroBancoCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "transferenciaOtroBanco") TransferenciaOtroBanco transferenciaOtroBanco);

	@WebMethod(operationName = "abonoPagoPrestamoCliente")
	@WebResult(name = "resultadoAbonoPagoPrestamo")
	public AbonoPagoPrestamo abonoPagoPrestamoCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "abonoPagoPrestamo") AbonoPagoPrestamo abonoPagoPrestamo);

	@WebMethod(operationName = "parametroMontosTransaccion")
	@WebResult(name = "resultadoParametroMontosTransaccion")
	public ParametroMontoPermitido parametroMontosTransaccion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "parametroMontosTransaccionCliente")
	@WebResult(name = "resultadoParametroMontosTransaccionCliente")
	public ParametroMontoPermitido parametroMontosTransaccionCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "listaCuentasCliente")
	@WebResult(name = "resultadoListaCuentasCliente")
	public List<ListadoOpciones> listaCuentasCliente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "saldoCuentaAhorro")
	@WebResult(name = "resultadoSaldoCuentaAhorro")
	public Double saldoCuentaAhorro(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") Long numeroCuenta);

	@WebMethod(operationName = "nombreCuentaAhorro")
	@WebResult(name = "resultadonombreCuentaAhorro")
	public String nombreCuentaAhorro(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroCuenta") Long numeroCuenta);

	@WebMethod(operationName = "registraMovimiento")
	@WebResult(name = "resultadoRegistraMovimiento")
	public void registraMovimiento(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "movimiento") TwebMovimientos movimiento);

	@WebMethod(operationName = "consultaAutorizacionTransaccion")
	@WebResult(name = "resultadoConsultaAutorizacionTransaccion")
	public AutorizaTransaccion consultaAutorizacionTransaccion(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "autorizaTransaccion") AutorizaTransaccion autorizaTransaccion);

	@WebMethod(operationName = "listadoEntidadesFinancieras")
	@WebResult(name = "resultadoListadoEntidadesFinancieras")
	public List<ListadoOpciones> listadoEntidadesFinancieras(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listadoTipoCuenta")
	@WebResult(name = "resultadoListadoTipoCuenta")
	public List<ListadoOpciones> listadoTipoCuenta(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "consultaComision")
	@WebResult(name = "resultadoConsultaComision")
	public Double consultaComision(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "tipoComision") Integer tipoComision);

	@WebMethod(operationName = "consultaDatosPrestamo")
	@WebResult(name = "resultadoConsultaDatosPrestamo")
	public List<DatosPrestamo> consultaDatosPrestamo(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroPrestamo") Long numeroPrestamo);

	@WebMethod(operationName = "listaPrestamoCLiente")
	@WebResult(name = "resultadoListaPrestamoCLiente")
	public List<ListadoOpciones> listaPrestamoCLiente(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "listaEmpresasPagoServicio")
	@WebResult(name = "resultadoListaEmpresasPagoServicio")
	public List<ListadoOpciones> listaEmpresasPagoServicio(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listaServiciosEmpresa")
	@WebResult(name = "resultadoListaServiciosEmpresa")
	public List<ListadoOpciones> listaServiciosEmpresa(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoEmpresa") Integer codigoEmpresa);

	@WebMethod(operationName = "pagoServicioWeb")
	@WebResult(name = "resultadoListapagoServicioWeb")
	public PagoServicio pagoServicioWeb(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "pagoServicio") PagoServicio pagoServicio);

	@WebMethod(operationName = "consultaPagoServicio")
	@WebResult(name = "resultadoConsultaPagoServicio")
	public List<DatosPagoServicio> consultaPagoServicio(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroIdentificacion") String numeroIdentificacion,
			@WebParam(name = "codigoTipoServicio") Integer codigoTipoServicio,
			@WebParam(name = "numeroServicio") String numeroServicio);

	@WebMethod(operationName = "consultaConsolidadoServicio")
	@WebResult(name = "resultadoConsultaConsolidadoServicio")
	public ConsolidadoPagoServicio consultaConsolidadoServicio(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "numeroIdentificacion") String numeroIdentificacion,
			@WebParam(name = "codigoTipoServicio") Integer codigoTipoServicio,
			@WebParam(name = "numeroServicio") String numeroServicio);

	@WebMethod(operationName = "listadoRecargasFacilito")
	@WebResult(name = "resultadoListadoRecargasFacilito")
	public List<RubroFacilito> listadoRecargasFacilito(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listadoGruposFacilito")
	@WebResult(name = "resultadoListadoGruposFacilito")
	public List<ListadoOpciones> listadoGruposFacilito(@WebParam(name = "sesion") String sesion);
	
	@WebMethod(operationName = "listadoGruposRecaudacionFacilito")
	@WebResult(name = "resultadoListadoGruposRecaudacionFacilito")
	public List<ListadoOpciones> listadoGruposRecaudacionFacilito(@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "listadoRubrosPorGrupoFacilito")
	@WebResult(name = "resultadoListadoRubrosPorGrupoFacilito")
	public List<RubroFacilito> listadoRubrosPorGrupoFacilito(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "grupo") String grupo);

}
