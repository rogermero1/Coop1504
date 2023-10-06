package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioPerfilTransaccionalWs {

	@WebMethod(operationName = "listaPerfilTransaccionalCliente")
	@WebResult(name = "resultadoListaPerfilTransaccionalCliente")
	public List<TwebPerfilTransaccional> listaPerfilTransaccionalCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "actualizaPerfilTransaccionalCliente")
	@WebResult(name = "resultadoActualizaPerfilTransaccionalCliente")
	public void actualizaPerfilTransaccionalCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "perfilCliente") TwebPerfilTransaccional perfilCliente);

	@WebMethod(operationName = "consultaPerfilCanalTransaccionalCliente")
	@WebResult(name = "resultadoConsultaPerfilCanalTransaccionalCliente")
	public List<TwebPerfilTransaccional> consultaPerfilCanalTransaccionalCliente(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "idCanalElectronico") Integer idCanalElectronico);

	@WebMethod(operationName = "consultaCuposTransaccionalInicial")
	@WebResult(name = "resultadoConsultaCuposTransaccionalInicial")
	public List<TwebPerfilTransaccional> consultaCuposTransaccionalInicial(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente);

	@WebMethod(operationName = "consultaListaCanalesElectronicos")
	@WebResult(name = "resultadoListaCanalesElectronicos")
	public List<ListadoOpciones> consultaListaCanalesElectronicos(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "consultaPerfilTransaccionesAcumuladas")
	@WebResult(name = "resultadoconsultaPerfilTransaccionesAcumuladas")
	public List<TwebPerfilTransaccional> consultaPerfilTransaccionesAcumuladas(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "codigoCliente") Integer codigoCliente,
			@WebParam(name = "idTipoCanal") Integer idTipoCanal);
}
