package ec.fin.online15.interceptores;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
//import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import ec.fin.online15.backend.librerias.generales.ValoresConstantesBackEnd;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebLogWs;
import ec.fin.online15.backend.webbanking.modelo.servicios.ServicioLogWs;

public class InterceptorWS implements SOAPHandler<SOAPMessageContext> {

	@EJB
	private ServicioLogWs servicioLogWs;

	private String sesion = "";
	private String interfaz = "";
	private String operacion = "";
	private String wsdl = "";
	private String xmlRequest = "";
	private String XmlResponse = "";
	private Calendar horaInicio;
	private Calendar horaFin;

	@Override
	public void close(MessageContext arg0) {
		// System.out.println("InterceptorWS->close");
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// System.out.println("InterceptorWS->handleFault");
		logToSystemOut(arg0);
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		// System.out.println("InterceptorWS->handleMessage");
		logToSystemOut(arg0);
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		// System.out.println("InterceptorWS->getHeaders");
		return null;
	}

	private void logToSystemOut(SOAPMessageContext smc) {
		try {
			// Determina el request/response
			Boolean outboundProperty = (Boolean) smc
					.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

			// Obtiene el mensaje
			SOAPMessage message = smc.getMessage();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			message.writeTo(out);
			String soapXml = new String(out.toByteArray());

			if (outboundProperty.booleanValue()) {
				// Response
				this.horaFin = Calendar.getInstance();
				this.XmlResponse = soapXml;
				TwebLogWs log = new TwebLogWs();
				log.setSesion(this.sesion.replace("Bearer ", "")); // correccion en JWT APP
				log.setInterfaz(this.interfaz);
				log.setOperacion(this.operacion);
				log.setWsdl(this.wsdl);
				log.setXmlRequest(this.xmlRequest);
				log.setXmlResponse(this.XmlResponse);
				log.setEstado("A");
				log.setFechaRegistro(new Date());
				log.setRegistradoPor(ValoresConstantesBackEnd.USUARIO);
				log.setTiempoRespuesta(this.horaFin.getTimeInMillis() - this.horaInicio.getTimeInMillis());
				this.servicioLogWs.crear(log);
				this.sesion = this.interfaz = this.operacion = this.wsdl = this.xmlRequest = this.XmlResponse = "";
				this.horaInicio = this.horaFin = null;
				// System.out.println("Response: \n" + this.XmlResponse);
			} else {
				// Request
				this.horaInicio = Calendar.getInstance();
				this.interfaz = smc.get(MessageContext.WSDL_INTERFACE).toString();
				this.operacion = (String) smc.get(MessageContext.WSDL_OPERATION).toString();
				this.wsdl = (String) smc.get(MessageContext.WSDL_DESCRIPTION).toString();
				this.sesion = soapXml.indexOf("<sesion>") > 0 ? soapXml
						.substring(soapXml.indexOf("<sesion>") + 8,
								soapXml.indexOf("</sesion>")) : "";
				this.xmlRequest = soapXml;
				// System.out.println("Request: \nInterfaz: " + this.interfaz
				// + "\nOperacion: " + this.operacion + "\nWsdl: "
				// + this.wsdl + "\nSesion: " + this.sesion + "\n"
				// + this.xmlRequest);
			}
		} catch (Exception e) {
			System.out.println("Exception in handler: " + e);
		}
	}
	// Boolean outboundProperty = (Boolean)
	// smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	//
	// if (outboundProperty.booleanValue()) {
	// System.out.println("\nResponse:");
	// } else {
	// System.out.println("\nRequest:");
	// }
	//
	// SOAPMessage message = smc.getMessage();
	// System.out.println("Interfaz ws"
	// + smc.get(MessageContext.WSDL_INTERFACE));
	// System.out
	// .println("Metodo ws" + smc.get(MessageContext.WSDL_OPERATION));
	// System.out.println("Desc Wsdl"
	// + smc.get(MessageContext.WSDL_DESCRIPTION));
	// System.out.println("SESION " + smc.get("USUARIO"));
	// try {
	// message.writeTo(System.out);
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// message.writeTo(out);
	// String soapXml = new String(out.toByteArray());
	// System.out.println("\nXML->" + soapXml);
	//
	// if (!outboundProperty.booleanValue()) {
	// System.out.println("USUARIO -> "
	// + soapXml.substring(soapXml.indexOf("<usuario>") + 9,
	// soapXml.indexOf("</usuario>")));
	// }
	//
	// } catch (Exception e) {
	// System.out.println("Exception in handler: " + e);
	// }
	/************** PRUEBAS ***************/
	// public Set<QName> getHeaders() {
	// return Collections.emptySet();
	// }
	//
	// public boolean handleMessage(SOAPMessageContext messageContext) {
	// Boolean outboundProperty = (Boolean) messageContext
	// .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	//
	// if (outboundProperty.booleanValue()) {
	// System.out.println("\nOutbound message:");
	// } else {
	// System.out.println("\nInbound message:");
	// }
	// /*
	// * Pero cuando ejecutas no sale nada??? si se va x una exepcion y en el
	// * tuto q encontre solo decia qhe en la clase del ws utilizara la
	// * anotacion handlerchain * reportes dejame revisar el codigo
	// */
	//
	// System.out.println("** Response: "
	// + messageContext.getMessage().toString());
	// return true;
	// }
	//
	// public boolean handleFault(SOAPMessageContext messageContext) {
	// return true;
	// }
	//
	// public void close(MessageContext messageContext) {
	// }
	//
	// @SuppressWarnings("unused")
	// private void logToSystemOut(SOAPMessageContext smc) {
	// System.out.println("METODO logToSystemOut..........");
	// Boolean outboundProperty = (Boolean) smc
	// .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	// if (outboundProperty.booleanValue()) {
	// System.out.println("\nOutbound message:");
	// } else {
	// System.out.println("\nInbound message:");
	// }
	// SOAPMessage message = smc.getMessage();
	// try {
	// message.writeTo(System.out);
	// System.out.println(""); // just to add a newline
	// } catch (Exception e) {
	// System.out.println("Exception in handler: " + e);
	// }
	// }

	// public boolean handleMessage(SOAPMessageContext messageContext) {
	// log(messageContext); return true; }
	//
	// public Set<QName> getHeaders() { return Collections.EMPTY_SET; }
	//
	// public boolean handleFault(SOAPMessageContext messageContext) { return
	// true; }
	//
	// public void close(MessageContext context) { }
	//
	// private void log(SOAPMessageContext messageContext) { SOAPMessage msg =
	// messageContext.getMessage(); // Line 1 try { msg.writeTo(System.out); //
	// Line 3 } catch (SOAPException ex) {
	// Logger.getLogger(InterceptorWS.class.getName()).log(Level.SEVERE, null,
	// ex); } catch (IOException ex) {
	// Logger.getLogger(InterceptorWS.class.getName()).log(Level.SEVERE, null,
	// ex); } }

}