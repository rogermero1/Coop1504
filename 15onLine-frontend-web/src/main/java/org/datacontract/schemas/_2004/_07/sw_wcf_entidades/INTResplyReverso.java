
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para INT_ResplyReverso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_ResplyReverso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaHoraTransaccion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ObjRecuest" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_RequestReverso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INT_ResplyReverso", propOrder = {
    "codigoAutorizacion",
    "codigoResultado",
    "fechaHoraTransaccion",
    "idTransaccion",
    "mensaje",
    "objRecuest"
})
public class INTResplyReverso {

    @XmlElement(name = "CodigoAutorizacion")
    protected String codigoAutorizacion;
    @XmlElement(name = "CodigoResultado")
    protected String codigoResultado;
    @XmlElement(name = "FechaHoraTransaccion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraTransaccion;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "ObjRecuest")
    protected INTRequestReverso objRecuest;

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
     *     {@link INTRequestReverso }
     *     
     */
    public INTRequestReverso getObjRecuest() {
        return objRecuest;
    }

    /**
     * Define el valor de la propiedad objRecuest.
     * 
     * @param value
     *     allowed object is
     *     {@link INTRequestReverso }
     *     
     */
    public void setObjRecuest(INTRequestReverso value) {
        this.objRecuest = value;
    }

}
