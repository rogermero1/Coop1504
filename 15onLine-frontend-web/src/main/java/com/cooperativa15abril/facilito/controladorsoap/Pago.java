
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudpago" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudPago" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pago", propOrder = {
    "solicitudpago"
})
public class Pago {

    protected SolicitudPago solicitudpago;

    /**
     * Obtiene el valor de la propiedad solicitudpago.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudPago }
     *     
     */
    public SolicitudPago getSolicitudpago() {
        return solicitudpago;
    }

    /**
     * Define el valor de la propiedad solicitudpago.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudPago }
     *     
     */
    public void setSolicitudpago(SolicitudPago value) {
        this.solicitudpago = value;
    }

}
