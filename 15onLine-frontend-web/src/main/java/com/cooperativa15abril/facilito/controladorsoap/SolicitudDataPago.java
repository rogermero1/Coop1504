
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudDataPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudDataPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idRubro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorConComision" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudDataPago", propOrder = {
    "idRubro",
    "valorConComision"
})
public class SolicitudDataPago {

    protected String idRubro;
    protected Double valorConComision;

    /**
     * Obtiene el valor de la propiedad idRubro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRubro() {
        return idRubro;
    }

    /**
     * Define el valor de la propiedad idRubro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRubro(String value) {
        this.idRubro = value;
    }

    /**
     * Obtiene el valor de la propiedad valorConComision.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValorConComision() {
        return valorConComision;
    }

    /**
     * Define el valor de la propiedad valorConComision.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValorConComision(Double value) {
        this.valorConComision = value;
    }

}
