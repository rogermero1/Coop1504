
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para INT_RequestRecargas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_RequestRecargas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosSeguridad" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_Seguridad" minOccurs="0"/>
 *         &lt;element name="IDAgencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDEntidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorConComision" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="XmlAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INT_RequestRecargas", propOrder = {
    "canal",
    "datosFactura",
    "datosSeguridad",
    "idAgencia",
    "idEntidad",
    "idProducto",
    "referencia",
    "valorConComision",
    "xmlAdd"
})
public class INTRequestRecargas {

    @XmlElement(name = "Canal")
    protected String canal;
    @XmlElement(name = "DatosFactura")
    protected String datosFactura;
    @XmlElement(name = "DatosSeguridad")
    protected INTSeguridad datosSeguridad;
    @XmlElement(name = "IDAgencia")
    protected String idAgencia;
    @XmlElement(name = "IDEntidad")
    protected String idEntidad;
    @XmlElement(name = "IDProducto")
    protected String idProducto;
    @XmlElement(name = "Referencia")
    protected String referencia;
    @XmlElement(name = "ValorConComision")
    protected BigDecimal valorConComision;
    @XmlElement(name = "XmlAdd")
    protected String xmlAdd;

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad datosFactura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatosFactura() {
        return datosFactura;
    }

    /**
     * Define el valor de la propiedad datosFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatosFactura(String value) {
        this.datosFactura = value;
    }

    /**
     * Obtiene el valor de la propiedad datosSeguridad.
     * 
     * @return
     *     possible object is
     *     {@link INTSeguridad }
     *     
     */
    public INTSeguridad getDatosSeguridad() {
        return datosSeguridad;
    }

    /**
     * Define el valor de la propiedad datosSeguridad.
     * 
     * @param value
     *     allowed object is
     *     {@link INTSeguridad }
     *     
     */
    public void setDatosSeguridad(INTSeguridad value) {
        this.datosSeguridad = value;
    }

    /**
     * Obtiene el valor de la propiedad idAgencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDAgencia() {
        return idAgencia;
    }

    /**
     * Define el valor de la propiedad idAgencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDAgencia(String value) {
        this.idAgencia = value;
    }

    /**
     * Obtiene el valor de la propiedad idEntidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDEntidad() {
        return idEntidad;
    }

    /**
     * Define el valor de la propiedad idEntidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDEntidad(String value) {
        this.idEntidad = value;
    }

    /**
     * Obtiene el valor de la propiedad idProducto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDProducto() {
        return idProducto;
    }

    /**
     * Define el valor de la propiedad idProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDProducto(String value) {
        this.idProducto = value;
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
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorConComision() {
        return valorConComision;
    }

    /**
     * Define el valor de la propiedad valorConComision.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorConComision(BigDecimal value) {
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
