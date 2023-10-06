
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudValidacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudValidacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudpagootp" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudPagoOtp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudValidacion", propOrder = {
    "solicitudpagootp"
})
public class SolicitudValidacion {

    protected SolicitudPagoOtp solicitudpagootp;

    /**
     * Obtiene el valor de la propiedad solicitudpagootp.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudPagoOtp }
     *     
     */
    public SolicitudPagoOtp getSolicitudpagootp() {
        return solicitudpagootp;
    }

    /**
     * Define el valor de la propiedad solicitudpagootp.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudPagoOtp }
     *     
     */
    public void setSolicitudpagootp(SolicitudPagoOtp value) {
        this.solicitudpagootp = value;
    }

}
