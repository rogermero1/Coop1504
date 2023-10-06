
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reverso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="reverso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudreverso" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudReverso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reverso", propOrder = {
    "solicitudreverso"
})
public class Reverso {

    protected SolicitudReverso solicitudreverso;

    /**
     * Obtiene el valor de la propiedad solicitudreverso.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudReverso }
     *     
     */
    public SolicitudReverso getSolicitudreverso() {
        return solicitudreverso;
    }

    /**
     * Define el valor de la propiedad solicitudreverso.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudReverso }
     *     
     */
    public void setSolicitudreverso(SolicitudReverso value) {
        this.solicitudreverso = value;
    }

}
