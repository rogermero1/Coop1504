
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudConsultaEntidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudConsultaEntidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoTrx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudConsultaEntidad", propOrder = {
    "tipoTrx"
})
public class SolicitudConsultaEntidad {

    protected String tipoTrx;

    /**
     * Obtiene el valor de la propiedad tipoTrx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTrx() {
        return tipoTrx;
    }

    /**
     * Define el valor de la propiedad tipoTrx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTrx(String value) {
        this.tipoTrx = value;
    }

}
