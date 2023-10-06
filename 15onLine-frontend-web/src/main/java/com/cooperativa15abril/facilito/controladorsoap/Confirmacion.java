
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para confirmacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="confirmacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudconfirmacion" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudConfirmacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmacion", propOrder = {
    "solicitudconfirmacion"
})
public class Confirmacion {

    protected SolicitudConfirmacion solicitudconfirmacion;

    /**
     * Obtiene el valor de la propiedad solicitudconfirmacion.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudConfirmacion }
     *     
     */
    public SolicitudConfirmacion getSolicitudconfirmacion() {
        return solicitudconfirmacion;
    }

    /**
     * Define el valor de la propiedad solicitudconfirmacion.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudConfirmacion }
     *     
     */
    public void setSolicitudconfirmacion(SolicitudConfirmacion value) {
        this.solicitudconfirmacion = value;
    }

}
