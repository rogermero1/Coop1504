package ec.fin.online15.controladores;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioParametrosGenerales;
import ec.fin.online15.interfaces.IServicioParametrosGeneralesWs;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioParametrosGeneralesWs implements IServicioParametrosGeneralesWs {

	@EJB
	private ServicioParametrosGenerales servicioParametrosGenerales;

	public List<TwebParametrosGenerales> listaParametrosGenerales(String sesion) {
		log.info("Invocacion web service : ServicioParametrosGeneralesWs-listaParametrosGenerales");
		log.info("SESION : " + sesion);
		//System.out.println("Invocacion web service : ServicioParametrosGeneralesWs-listaParametrosGenerales");
//		System.out.println("SESION: " + sesion);
		return servicioParametrosGenerales.listaParametrosGenerales();
	}

	public void actualizarParametro(String sesion, TwebParametrosGenerales parametroGeneral) {
		log.info("Invocacion web service : ServicioParametrosGeneralesWs-actualizarParametro");
		log.info("SESION : "+sesion);
		//System.out.println("Invocacion web service : ServicioParametrosGeneralesWs-actualizarParametro");
	//	System.out.println("SESION: " + sesion);
		try {
			servicioParametrosGenerales.actualizar(parametroGeneral);
		} catch (ExcepcionAplicacion e) {
			e.printStackTrace();
		}
	}

	public Date consultaFechaValida(String sesion, String modulo) {
		log.info("Invocacion web service : ServicioParametrosGeneralesWs-consultaFechaValida");
		log.info("SESION : " +sesion);
		//System.out.println("Invocacion web service : ServicioParametrosGeneralesWs-consultaFechaValida");
		//System.out.println("SESION: " + sesion);
		return servicioParametrosGenerales.consultaFechaCalendario(modulo);
	}

}
