
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para INT_ResplyPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_ResplyPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTraceTrx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataPago" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}ArrayOfINT_ResplyPago.INT_DataPago" minOccurs="0"/>
 *         &lt;element name="FechaCierre" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaCompensacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaHoraTransaccion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ObjRecuest" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_RequestPago" minOccurs="0"/>
 *         &lt;element name="OperadoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Terminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UrlFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XMLRecibo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "INT_ResplyPago", propOrder = {
    "codigoPago",
    "codigoResultado",
    "codigoTraceTrx",
    "codigoTransaccion",
    "dataPago",
    "fechaCierre",
    "fechaCompensacion",
    "fechaHoraTransaccion",
    "idTransaccion",
    "mensaje",
    "objRecuest",
    "operadoPor",
    "producto",
    "terminal",
    "urlFactura",
    "xmlRecibo",
    "xmlAdd"
})
public class INTResplyPago {

    @XmlElement(name = "CodigoPago")
    protected String codigoPago;
    @XmlElement(name = "CodigoResultado")
    protected String codigoResultado;
    @XmlElement(name = "CodigoTraceTrx")
    protected String codigoTraceTrx;
    @XmlElement(name = "CodigoTransaccion")
    protected String codigoTransaccion;
    @XmlElement(name = "DataPago")
    protected ArrayOfINTResplyPagoINTDataPago dataPago;
    @XmlElement(name = "FechaCierre")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCierre;
    @XmlElement(name = "FechaCompensacion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCompensacion;
    @XmlElement(name = "FechaHoraTransaccion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraTransaccion;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "ObjRecuest")
    protected INTRequestPago objRecuest;
    @XmlElement(name = "OperadoPor")
    protected String operadoPor;
    @XmlElement(name = "Producto")
    protected String producto;
    @XmlElement(name = "Terminal")
    protected String terminal;
    @XmlElement(name = "UrlFactura")
    protected String urlFactura;
    @XmlElement(name = "XMLRecibo")
    protected String xmlRecibo;
    @XmlElement(name = "XmlAdd")
    protected String xmlAdd;

    /**
     * Obtiene el valor de la propiedad codigoPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPago() {
        return codigoPago;
    }

    /**
     * Define el valor de la propiedad codigoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPago(String value) {
        this.codigoPago = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoResultado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }

    /**
     * Define el valor de la propiedad codigoResultado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoResultado(String value) {
        this.codigoResultado = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTraceTrx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTraceTrx() {
        return codigoTraceTrx;
    }

    /**
     * Define el valor de la propiedad codigoTraceTrx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTraceTrx(String value) {
        this.codigoTraceTrx = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    /**
     * Define el valor de la propiedad codigoTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTransaccion(String value) {
        this.codigoTransaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dataPago.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfINTResplyPagoINTDataPago }
     *     
     */
    public ArrayOfINTResplyPagoINTDataPago getDataPago() {
        return dataPago;
    }

    /**
     * Define el valor de la propiedad dataPago.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfINTResplyPagoINTDataPago }
     *     
     */
    public void setDataPago(ArrayOfINTResplyPagoINTDataPago value) {
        this.dataPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCierre.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCierre() {
        return fechaCierre;
    }

    /**
     * Define el valor de la propiedad fechaCierre.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCierre(XMLGregorianCalendar value) {
        this.fechaCierre = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCompensacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCompensacion() {
        return fechaCompensacion;
    }

    /**
     * Define el valor de la propiedad fechaCompensacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCompensacion(XMLGregorianCalendar value) {
        this.fechaCompensacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    /**
     * Define el valor de la propiedad fechaHoraTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraTransaccion(XMLGregorianCalendar value) {
        this.fechaHoraTransaccion = value;
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
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad objRecuest.
     * 
     * @return
     *     possible object is
     *     {@link INTRequestPago }
     *     
     */
    public INTRequestPago getObjRecuest() {
        return objRecuest;
    }

    /**
     * Define el valor de la propiedad objRecuest.
     * 
     * @param value
     *     allowed object is
     *     {@link INTRequestPago }
     *     
     */
    public void setObjRecuest(INTRequestPago value) {
        this.objRecuest = value;
    }

    /**
     * Obtiene el valor de la propiedad operadoPor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperadoPor() {
        return operadoPor;
    }

    /**
     * Define el valor de la propiedad operadoPor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperadoPor(String value) {
        this.operadoPor = value;
    }

    /**
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducto(String value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad terminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Define el valor de la propiedad terminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminal(String value) {
        this.terminal = value;
    }

    /**
     * Obtiene el valor de la propiedad urlFactura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlFactura() {
        return urlFactura;
    }

    /**
     * Define el valor de la propiedad urlFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlFactura(String value) {
        this.urlFactura = value;
    }

    /**
     * Obtiene el valor de la propiedad xmlRecibo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLRecibo() {
        return xmlRecibo;
    }

    /**
     * Define el valor de la propiedad xmlRecibo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLRecibo(String value) {
        this.xmlRecibo = value;
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
