package ec.fin.online15.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioPerfilTransaccional;
import ec.fin.online15.interfaces.IServicioPerfilTransaccionalWs;
import lombok.extern.slf4j.Slf4j;
@Slf4j

@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioPerfilTransaccionalWs implements IServicioPerfilTransaccionalWs {

	@EJB
	private ServicioPerfilTransaccional servicioPerfilTransaccional;

	public List<TwebPerfilTransaccional> listaPerfilTransaccionalCliente(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-listaPerfilTransaccionalCliente");
		log.info("SESION: " + sesion);
		return servicioPerfilTransaccional.listaPerfilCliente(codigoCliente);
	}

	public void actualizaPerfilTransaccionalCliente(String sesion, TwebPerfilTransaccional perfilCliente) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-actualizaPerfilTransaccionalCliente");
		log.info("SESION: " + sesion);
		servicioPerfilTransaccional.actualizaPerfilCliente(perfilCliente);
	}

	public List<TwebPerfilTransaccional> consultaPerfilCanalTransaccionalCliente(String sesion, Integer codigoCliente,
			Integer idCanalElectronico) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-consultaPerfilCanalTransaccionalCliente");
		log.info("SESION: " + sesion);
		return servicioPerfilTransaccional.consultaPerfilCanalTransaccionalCliente(codigoCliente, idCanalElectronico);
	}

	public List<TwebPerfilTransaccional> consultaCuposTransaccionalInicial(String sesion, Integer codigoCliente) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-consultaCuposTransaccionalInicial");
		log.info("SESION: " + sesion);
		return servicioPerfilTransaccional.consultaCuposTransaccionalInicial(codigoCliente);
	}

	public List<ListadoOpciones> consultaListaCanalesElectronicos(String sesion) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-consultaListaCanalesElectronicos");
		log.info("SESION: " + sesion);
		return servicioPerfilTransaccional.listaCanalesElectronicos();
	}

	public List<TwebPerfilTransaccional> consultaPerfilTransaccionesAcumuladas(String sesion, Integer codigoCliente,
			Integer idTipoCanal) {
		log.info("Invocacion web service : ServicioPerfilTransaccionalWs-consultaPerfilTransaccionesAcumuladas");
		log.info("SESION: " + sesion);
		return servicioPerfilTransaccional.consultaPerfilTransaccionesAcumuladas(codigoCliente, idTipoCanal);
	}

}
