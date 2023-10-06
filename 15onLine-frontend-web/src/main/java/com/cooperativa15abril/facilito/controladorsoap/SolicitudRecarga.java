
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudRecarga complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudRecarga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pnAgencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pnCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvCanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvIdProductoFacilito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvNombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorConComision" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="xmlAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudRecarga", propOrder = {
    "pnAgencia",
    "pnCuenta",
    "pvCanal",
    "pvEmail",
    "pvIdProductoFacilito",
    "pvIdentificacion",
    "pvNombres",
    "pvUsuario",
    "referencia",
    "valorConComision",
    "xmlAdd"
})
public class SolicitudRecarga {

    protected Long pnAgencia;
    protected String pnCuenta;
    protected String pvCanal;
    protected String pvEmail;
    protected String pvIdProductoFacilito;
    protected String pvIdentificacion;
    protected String pvNombres;
    protected String pvUsuario;
    protected String referencia;
    protected Double valorConComision;
    protected String xmlAdd;

    /**
     * Obtiene el valor de la propiedad pnAgencia.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPnAgencia() {
        return pnAgencia;
    }

    /**
     * Define el valor de la propiedad pnAgencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPnAgencia(Long value) {
        this.pnAgencia = value;
    }

    /**
     * Obtiene el valor de la propiedad pnCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnCuenta() {
        return pnCuenta;
    }

    /**
     * Define el valor de la propiedad pnCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnCuenta(String value) {
        this.pnCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad pvCanal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvCanal() {
        return pvCanal;
    }

    /**
     * Define el valor de la propiedad pvCanal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvCanal(String value) {
        this.pvCanal = value;
    }

    /**
     * Obtiene el valor de la propiedad pvEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvEmail() {
        return pvEmail;
    }

    /**
     * Define el valor de la propiedad pvEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvEmail(String value) {
        this.pvEmail = value;
    }

    /**
     * Obtiene el valor de la propiedad pvIdProductoFacilito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvIdProductoFacilito() {
        return pvIdProductoFacilito;
    }

    /**
     * Define el valor de la propiedad pvIdProductoFacilito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvIdProductoFacilito(String value) {
        this.pvIdProductoFacilito = value;
    }

    /**
     * Obtiene el valor de la propiedad pvIdentificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvIdentificacion() {
        return pvIdentificacion;
    }

    /**
     * Define el valor de la propiedad pvIdentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvIdentificacion(String value) {
        this.pvIdentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad pvNombres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvNombres() {
        return pvNombres;
    }

    /**
     * Define el valor de la propiedad pvNombres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvNombres(String value) {
        this.pvNombres = value;
    }

    /**
     * Obtiene el valor de la propiedad pvUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvUsuario() {
        return pvUsuario;
    }

    /**
     * Define el valor de la propiedad pvUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvUsuario(String value) {
        this.pvUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
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

    /**
     * Obtiene el valor de la propiedad xmlAdd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlAdd() {
        return xmlAdd;
    }

    /**
     * Define el valor de la propiedad xmlAdd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlAdd(String value) {
        this.xmlAdd = value;
    }

}
