
package com.cooperativa15abril.facilito.controladorsoap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudConfirmacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudConfirmacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datosFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaPagos" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudDataPago" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "solicitudConfirmacion", propOrder = {
    "datosFactura",
    "idTransaccion",
    "listaPagos",
    "xmlAdd"
})
public class SolicitudConfirmacion {

    protected String datosFactura;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(nillable = true)
    protected List<SolicitudDataPago> listaPagos;
    protected String xmlAdd;

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
     * Gets the value of the listaPagos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPagos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPagos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SolicitudDataPago }
     * 
     * 
     */
    public List<SolicitudDataPago> getListaPagos() {
        if (listaPagos == null) {
            listaPagos = new ArrayList<SolicitudDataPago>();
        }
        return this.listaPagos;
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
