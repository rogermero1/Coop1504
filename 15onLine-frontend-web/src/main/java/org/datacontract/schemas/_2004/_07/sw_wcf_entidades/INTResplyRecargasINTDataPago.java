
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para INT_ResplyRecargas.INT_DataPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_ResplyRecargas.INT_DataPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Factura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDRubro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaAdquirente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaSwitch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INT_ResplyRecargas.INT_DataPago", propOrder = {
    "codigoAutorizacion",
    "factura",
    "idRubro",
    "secuenciaAdquirente",
    "secuenciaSwitch"
})
public class INTResplyRecargasINTDataPago {

    @XmlElement(name = "CodigoAutorizacion")
    protected String codigoAutorizacion;
    @XmlElement(name = "Factura")
    protected String factura;
    @XmlElement(name = "IDRubro")
    protected String idRubro;
    @XmlElement(name = "SecuenciaAdquirente")
    protected String secuenciaAdquirente;
    @XmlElement(name = "SecuenciaSwitch")
    protected String secuenciaSwitch;

    /**
     * Obtiene el valor de la propiedad codigoAutorizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    /**
     * Define el valor de la propiedad codigoAutorizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoAutorizacion(String value) {
        this.codigoAutorizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad factura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactura() {
        return factura;
    }

    /**
     * Define el valor de la propiedad factura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactura(String value) {
        this.factura = value;
    }

    /**
     * Obtiene el valor de la propiedad idRubro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDRubro() {
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
    public void setIDRubro(String value) {
        this.idRubro = value;
    }

    /**
     * Obtiene el valor de la propiedad secuenciaAdquirente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuenciaAdquirente() {
        return secuenciaAdquirente;
    }

    /**
     * Define el valor de la propiedad secuenciaAdquirente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuenciaAdquirente(String value) {
        this.secuenciaAdquirente = value;
    }

    /**
     * Obtiene el valor de la propiedad secuenciaSwitch.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuenciaSwitch() {
        return secuenciaSwitch;
    }

    /**
     * Define el valor de la propiedad secuenciaSwitch.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuenciaSwitch(String value) {
        this.secuenciaSwitch = value;
    }

}
