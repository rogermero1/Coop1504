
package com.cooperativa15abril.facilito.controladorsoap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaPagos" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudDataPago" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pnAgencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pnCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pnIva" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pnMonto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pnSubtotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pvCanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvIdProductoFacilito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvNombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvNumeroServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pvUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "solicitudPago", propOrder = {
    "idTransaccion",
    "listaPagos",
    "pnAgencia",
    "pnCuenta",
    "pnIva",
    "pnMonto",
    "pnSubtotal",
    "pvCanal",
    "pvEmail",
    "pvIdProductoFacilito",
    "pvIdentificacion",
    "pvNombres",
    "pvNumeroServicio",
    "pvUsuario",
    "xmlAdd"
})
public class SolicitudPago {

    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(nillable = true)
    protected List<SolicitudDataPago> listaPagos;
    protected Long pnAgencia;
    protected String pnCuenta;
    protected Double pnIva;
    protected Double pnMonto;
    protected Double pnSubtotal;
    protected String pvCanal;
    protected String pvEmail;
    protected String pvIdProductoFacilito;
    protected String pvIdentificacion;
    protected String pvNombres;
    protected String pvNumeroServicio;
    protected String pvUsuario;
    protected String xmlAdd;

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
     * Obtiene el valor de la propiedad pnIva.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPnIva() {
        return pnIva;
    }

    /**
     * Define el valor de la propiedad pnIva.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPnIva(Double value) {
        this.pnIva = value;
    }

    /**
     * Obtiene el valor de la propiedad pnMonto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPnMonto() {
        return pnMonto;
    }

    /**
     * Define el valor de la propiedad pnMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPnMonto(Double value) {
        this.pnMonto = value;
    }

    /**
     * Obtiene el valor de la propiedad pnSubtotal.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPnSubtotal() {
        return pnSubtotal;
    }

    /**
     * Define el valor de la propiedad pnSubtotal.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPnSubtotal(Double value) {
        this.pnSubtotal = value;
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
     * Obtiene el valor de la propiedad pvNumeroServicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvNumeroServicio() {
        return pvNumeroServicio;
    }

    /**
     * Define el valor de la propiedad pvNumeroServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvNumeroServicio(String value) {
        this.pvNumeroServicio = value;
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
