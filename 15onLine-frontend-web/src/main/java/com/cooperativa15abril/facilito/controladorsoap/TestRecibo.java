package com.cooperativa15abril.facilito.controladorsoap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class TestRecibo {

	public static void main(String[] args) {

		// String reciboFacilito = "<COMPROBANTE><RECIBO><LINEA_2>COOPERATIVA DE AHORRO
		// Y CREDITO 15 DE ABRIL</LINEA_2><LINEA_3>RUC:
		// 1390013678001</LINEA_3><LINEA_4>COMPROBANTE DE RECAUDACIÓN
		// CLARO</LINEA_4><LINEA_5>COMPROBANTE: 00000000435305</LINEA_5><LINEA_7>TIPO
		// RECAUDACION:
		// RECARGA</LINEA_7><LINEA_8>REFERENCIA:0992858575</LINEA_8><LINEA_9>NOMBRE:
		// S/N</LINEA_9><LINEA_10>CEDULA:9999999999</LINEA_10><LINEA_20>-----------------------------------------------------</LINEA_20><LINEA_22>USUARIO:
		// Usuario</LINEA_22><LINEA_23>SEC LOC/SW: 578/0</LINEA_23><LINEA_24>FECHA HORA;
		// 05/01/2022 10:42:44</LINEA_24><LINEA_25>CIUDAD:
		// N/D</LINEA_25><LINEA_26>-----------------------------------------------------</LINEA_26><LINEA_32>Propina</LINEA_32><LINEA_33>VALOR
		// RECAUDADO: 1.00</LINEA_33><LINEA_34>COMISION: 0.00</LINEA_34><LINEA_35>TOTAL:
		// 1.00</LINEA_35><LINEA_36>MENSAJE:</LINEA_36><LINEA_37>OPERADO POR: RED DE
		// SERVICIOS FACILITO</LINEA_37><LINEA_38>FACTURA:
		// 001-029-000039531</LINEA_38><LINEA_39>-----------------------------------------------------</LINEA_39><LINEA_42>Descargue
		// su factura electrónica
		// en</LINEA_42><LINEA_43>https://www.facilito.com.ec/documentoselectronicos/</LINEA_43><LINEA_52>.</LINEA_52></RECIBO></COMPROBANTE>";
		String reciboFacilito = "<COMPROBANTE><RECIBO><LINEA_2>COOPERATIVA DE AHORRO Y CREDITO 15 DE ABRIL</LINEA_2><LINEA_3>RUC: 1390013678001</LINEA_3><LINEA_4>COMPROBANTE DE RECAUDACIÓN CNEL- REGIONAL - MANABI</LINEA_4><LINEA_5>COMPROBANTE: 00000000435189</LINEA_5><LINEA_7>TIPO RECAUDACION: LUZ</LINEA_7><LINEA_8>REFERENCIA:6190417</LINEA_8><LINEA_9>NOMBRE: BURBANO MERO MERCEDES COLOMBIA</LINEA_9><LINEA_10>CEDULA:9999999999</LINEA_10><LINEA_11>-----------------------------------------------------</LINEA_11><LINEA_13>USUARIO: Usuario</LINEA_13><LINEA_14>SEC LOC/SW: 573/176741</LINEA_14><LINEA_15>FECHA HORA; 04/01/2022 11:02:53</LINEA_15><LINEA_16>CIUDAD: N/D</LINEA_16><LINEA_17>-----------------------------------------------------</LINEA_17><LINEA_18>VALOR RECAUDADO: 80.00</LINEA_18><LINEA_19>COMISION: 0.57</LINEA_19><LINEA_20>TOTAL: 80.57</LINEA_20><LINEA_21>MENSAJE: TRANSACCION OK</LINEA_21><LINEA_22>OPERADO POR: RED DE SERVICIOS FACILITO</LINEA_22><LINEA_23>FACTURA:  001-002-012854131</LINEA_23><LINEA_24>-----------------------------------------------------</LINEA_24><LINEA_25>Descargue su factura electrónica en</LINEA_25><LINEA_26>https://www.facilito.com.ec/documentoselectronicos/</LINEA_26><LINEA_35>.</LINEA_35></RECIBO></COMPROBANTE>";

		printXML(reciboFacilito);

	}

	private static void printXML(String xml) {
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			doc.getDocumentElement().normalize();

			String resultado = printXMLnodes((Node) doc.getDocumentElement(), 0);
			System.out.println(resultado+"</table></center>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String printXMLnodes(Node node, int indent) {

		String linea = "";

		// System.out.println(); // salto de linea donde empezamos a escribir el nuevo
		// nodo
		// for (int i = 0; i < indent; i++)
		// System.out.print("\t"); // aplicamos sangría correspondiente

		// System.out.print(node.getNodeName()); // imprimimos nombre del nodo

		// si el nodo tiene atributos, los imprimimos
		if (node.hasAttributes()) {
			NamedNodeMap nnm = node.getAttributes();
			for (int i = 0; i < nnm.getLength(); i++) {
				System.out.print(" (" + nnm.item(i).getNodeName() + ": " + nnm.item(i).getNodeValue() + ")+");
			}
		}

		// System.out.print(": "); // parar separar el nombre del nodo de su contenido

		// por cada hijo de este nodo...
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {

			// aqui filtramos solo los hijos directos, los hijos de hijos seran considerados
			// por recursividad
			if (node.getChildNodes().item(i).getParentNode().isSameNode(node)) {

				// si es de tipo elemento...
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

					// llamada recursiva con dicho hijo, y ampliamos sangria
					linea += printXMLnodes(node.getChildNodes().item(i), indent + 1);
					// o si es de tipo texto...
				} else if (node.getChildNodes().item(i).getNodeType() == Node.TEXT_NODE) {

					// almacenamos su valor en un string por comodidad
					String text = node.getChildNodes().item(i).getNodeValue();

					// El parser considera como TEXT_NODE los saltos de linea y tabulaciones
					// internas
					// que pueda haber en el XML original, asi que aqui filtramos para solo tener en
					// cuenta
					// los strings que realmente tengan caracteres que no sean ni espacios ni '\t',
					// '\n', '\r'
					if (text.trim().length() > 0) {
						if (!text.equals(".")) {
							// para linea 2,3,4,5 se centra
							if (node.getNodeName().equals("LINEA_2") || node.getNodeName().equals("LINEA_3")
									|| node.getNodeName().equals("LINEA_5")) {
								linea += "<center>" + text + "</center>\n";
							} else if (node.getNodeName().equals("LINEA_4")) {
								linea += "<center><b>" + text + "</b></center>\n";
							} else if (node.getNodeName().equals("LINEA_7")) {
								linea += "<center><table style=\"font-size: 10px\"><tr><td>"+text + "</td></tr>\n";
							} else {
								linea += "<tr><td>"+text + "</td></tr>\n";
							}

						}
						// System.out.print(text); // imprimimos el contenido
					}
				}
			}
		}
		return linea;

	}

}
