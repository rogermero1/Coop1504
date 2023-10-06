package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.webbanking.modelo.entidades.TwebBancoPregunta;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioBancoPreguntasWs {

	@WebMethod(operationName = "listadoBancoPreguntas")
	@WebResult(name = "resultadolistadoBancoPreguntas")
	public List<TwebBancoPregunta> listadoBancoPreguntas(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "crearPregunta")
	@WebResult(name = "resultadocrearPregunta")
	public void crearPregunta(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "bancoPregunta") TwebBancoPregunta bancoPregunta);

	@WebMethod(operationName = "actualizarPregunta")
	@WebResult(name = "resultadoactualizarPregunta")
	public void actualizarPregunta(@WebParam(name = "sesion") String sesion,
			@WebParam(name = "bancoPregunta") TwebBancoPregunta bancoPregunta);

	@WebMethod(operationName = "listaPreguntasRegistro")
	@WebResult(name = "resultadolistaPreguntasRegistro")
	public List<TwebBancoPregunta> listaPreguntasRegistro(
			@WebParam(name = "sesion") String sesion);

}
