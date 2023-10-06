package ec.fin.online15.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.InformacionCliente;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioConvenioWs {

	@WebMethod(operationName = "consultaConvenioWebCliente")
	@WebResult(name = "resultadoconsultaConvenioWebCliente")
	public TwebConvenios consultaConvenioWebCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "cedula") String cedula,
			@WebParam(name = "convenioWeb") String convenioWeb);

	@WebMethod(operationName = "validaDatosCliente")
	@WebResult(name = "resultadovalidaDatosCliente")
	public TwebConvenios validaDatosCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "cedula") String cedula,
			@WebParam(name = "correo") String correo,
			@WebParam(name = "celular") String celular);

	@WebMethod(operationName = "validaIdentidadCliente")
	@WebResult(name = "resultadovalidaIdentidadCliente")
	public TwebConvenios validaIdentidadCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "ip") String ip,
			@WebParam(name = "cedula") String cedula,
			@WebParam(name = "cuenta") String cuenta,
			@WebParam(name = "anioNacimiento") Integer anioNacimiento,
			@WebParam(name = "fechaUltimaTransaccion") Date fechaUltimaTransaccion,
			@WebParam(name = "tieneCredito") Boolean tieneCredito,
			@WebParam(name = "tieneInversion") Boolean tieneInversion,
			@WebParam(name = "recibeTransferencia") Boolean recibeTransferencia,
			@WebParam(name = "listaPreguntasSegundaValidacion") List<Integer> listaPreguntasSegundaValidacion,
			@WebParam(name = "intentosParaBloquear") Integer intentosParaBloquear);

	@WebMethod(operationName = "consultaCliente")
	@WebResult(name = "resultadoconsultaconsultaCliente")
	public InformacionCliente consultaCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "cedula") String cedula);

	@WebMethod(operationName = "registrarConvenio")
	@WebResult(name = "resultadoregistrarConvenio")
	public String registrarConvenio(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "convenio") TwebConvenios convenio);

	@WebMethod(operationName = "listaConveniosVigentes")
	@WebResult(name = "resultadolistaConveniosVigentes")
	public List<TwebConvenios> listaConveniosVigentes(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "actualizarConvenio")
	@WebResult(name = "resultadoactualizarConvenio")
	public void actualizarConvenio(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "convenio") TwebConvenios convenio);

}
