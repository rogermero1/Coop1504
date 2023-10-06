package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.TiposCertificacion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Certificacion;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioCertificacion;
import ec.fin.online15.interfaces.IServicioCertificacionWs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioCertificacionWs implements IServicioCertificacionWs {

	@EJB
	private ServicioCertificacion servicio;

	public List<TiposCertificacion> listaCertificaciones(String sesion) {
		log.info("Invocacion web service : ServicioCertificacionWs-listaCertificaciones");
		log.info("SESION: " + sesion);
		return servicio.listaCertificaciones();
	}

	public Certificacion solicitarCertificacion(String sesion, Certificacion certificacion) {
		log.info("Invocacion web service : ServicioCertificacionWs-solicitarCertificacion");
		log.info("SESION: " + sesion);
		return servicio.solicitarCertificacion(certificacion);
	}

}
