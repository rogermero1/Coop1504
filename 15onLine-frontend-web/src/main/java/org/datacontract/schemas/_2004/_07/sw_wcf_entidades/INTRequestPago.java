
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para INT_RequestPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_RequestPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataPago" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}ArrayOfINT_RequestPago.INT_DataPago" minOccurs="0"/>
 *         &lt;element name="DatosFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosSeguridad" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_Seguridad" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "INT_RequestPago", propOrder = {
    "dataPago",
    "datosFactura",
    "datosSeguridad",
    "idTransaccion",
    "xmlAdd"
})
public class INTRequestPago {

    @XmlElement(name = "DataPago")
    protected ArrayOfINTRequestPagoINTDataPago dataPago;
    @XmlElement(name = "DatosFactura")
    protected String datosFactura;
    @XmlElement(name = "DatosSeguridad")
    protected INTSeguridad datosSeguridad;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(name = "XmlAdd")
    protected String xmlAdd;

    /**
     * Obtiene el valor de la propiedad dataPago.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfINTRequestPagoINTDataPago }
     *     
     */
    public ArrayOfINTRequestPagoINTDataPago getDataPago() {
        return dataPago;
    }

    /**
     * Define el valor de la propiedad dataPago.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfINTRequestPagoINTDataPago }
     *     
     */
    public void setDataPago(ArrayOfINTRequestPagoINTDataPago value) {
        this.dataPago = value;
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
     * Obtiene el valor de la propiedad idTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTransaccion() {
        return idTransaccion;
    }

    /**
     * Define el valor de la propiedad idTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTransaccion(String value) {
        this.idTransaccion = value;
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
