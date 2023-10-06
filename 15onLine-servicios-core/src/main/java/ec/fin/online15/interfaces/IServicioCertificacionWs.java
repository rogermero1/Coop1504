package ec.fin.online15.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.TiposCertificacion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Certificacion;

@Remote
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
public interface IServicioCertificacionWs {

	@WebMethod(operationName = "listaCertificaciones")
	@WebResult(name = "resultadolistaCertificaciones")
	public List<TiposCertificacion> listaCertificaciones(
			@WebParam(name = "sesion") String sesion);

	@WebMethod(operationName = "solicitarCertificacion")
	@WebResult(name = "resultadosolicitarCertificacion")
	public Certificacion solicitarCertificacion(
			@WebParam(name = "sesion") String sesion,
			@WebParam(name = "certificacion") Certificacion certificacion);

}
