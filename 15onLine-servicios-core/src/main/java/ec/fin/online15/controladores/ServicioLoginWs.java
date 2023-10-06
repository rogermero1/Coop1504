package ec.fin.online15.controladores;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import ec.fin.online15.backend.seguridad.modelo.servicios.ServicioLogin;
import ec.fin.online15.backend.webbanking.modelo.entidades.Canal;
import ec.fin.online15.interfaces.IServicioLoginWs;
import lombok.extern.slf4j.Slf4j;

//import java.util.Map;
//import java.util.Map;
//import javax.annotation.Resource;
//import javax.jws.WebMethod;
//import javax.xml.ws.WebServiceContext;
//import javax.xml.ws.handler.MessageContext;
//import javax.xml.ws.handler.MessageContext;
@Slf4j

@WebService(targetNamespace = "http://servicios.coop15abril.fin.ec")
@HandlerChain(file = "Interceptor.xml")
@Stateless
public class ServicioLoginWs implements IServicioLoginWs {

	// @Resource
	// WebServiceContext ctx;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicioLogin servicioLogin;

	// @WebMethod()
	public String autenticacion(String sesion, String usuario, boolean resultadoLdap, Canal canal) {
		log.info("*** " + usuario + " conectado en canal " + canal.toString());
		log.info("Invocacion web service : ServicioLoginWs-autenticacion");
		log.info("SESION: " + sesion);

		// MessageContext context = ctx.getMessageContext();
		// Map requestHeaders = (Map) context
		// .get(MessageContext.HTTP_REQUEST_HEADERS);
		// System.out.println("PPPPP " + context.values() + "+"
		// + requestHeaders.toString());
		// System.out.println(context.get(MessageContext.HTTP_REQUEST_METHOD));
		// System.out.println(context.get(MessageContext.HTTP_RESPONSE_HEADERS));
		// System.out.println(context
		// .get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS));
		// System.out.println(context
		// .get(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS));
		// System.out.println(context.get(MessageContext.PATH_INFO));
		// System.out.println(context.get(MessageContext.QUERY_STRING));
		// System.out.println((String) ctx.getMessageContext().get(usuario));

		return servicioLogin.autenticacion(usuario, resultadoLdap, canal);
	}
}
