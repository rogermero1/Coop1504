
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recargas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recargas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudrecarga" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudRecarga" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recargas", propOrder = {
    "solicitudrecarga"
})
public class Recargas {

    protected SolicitudRecarga solicitudrecarga;

    /**
     * Obtiene el valor de la propiedad solicitudrecarga.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudRecarga }
     *     
     */
    public SolicitudRecarga getSolicitudrecarga() {
        return solicitudrecarga;
    }

    /**
     * Define el valor de la propiedad solicitudrecarga.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudRecarga }
     *     
     */
    public void setSolicitudrecarga(SolicitudRecarga value) {
        this.solicitudrecarga = value;
    }

}
