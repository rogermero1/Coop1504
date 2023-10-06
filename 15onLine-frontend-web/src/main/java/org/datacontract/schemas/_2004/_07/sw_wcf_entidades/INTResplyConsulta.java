
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para INT_ResplyConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_ResplyConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataConsulta" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}ArrayOfINT_ResplyConsulta.INT_DataConsulta" minOccurs="0"/>
 *         &lt;element name="FechaHoraTransaccion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Identificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ObjRecuest" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_RequestConsulta" minOccurs="0"/>
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
@XmlType(name = "INT_ResplyConsulta", propOrder = {
    "codigoResultado",
    "dataConsulta",
    "fechaHoraTransaccion",
    "idTransaccion",
    "identificacion",
    "mensaje",
    "nombre",
    "objRecuest",
    "xmlAdd"
})
public class INTResplyConsulta {

    @XmlElement(name = "CodigoResultado")
    protected String codigoResultado;
    @XmlElement(name = "DataConsulta")
    protected ArrayOfINTResplyConsultaINTDataConsulta dataConsulta;
    @XmlElement(name = "FechaHoraTransaccion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraTransaccion;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(name = "Identificacion")
    protected String identificacion;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "Nombre")
    protected String nombre;
    @XmlElement(name = "ObjRecuest")
    protected INTRequestConsulta objRecuest;
    @XmlElement(name = "XmlAdd")
    protected String xmlAdd;

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
     * Obtiene el valor de la propiedad dataConsulta.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfINTResplyConsultaINTDataConsulta }
     *     
     */
    public ArrayOfINTResplyConsultaINTDataConsulta getDataConsulta() {
        return dataConsulta;
    }

    /**
     * Define el valor de la propiedad dataConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfINTResplyConsultaINTDataConsulta }
     *     
     */
    public void setDataConsulta(ArrayOfINTResplyConsultaINTDataConsulta value) {
        this.dataConsulta = value;
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
     * Obtiene el valor de la propiedad identificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Define el valor de la propiedad identificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificacion(String value) {
        this.identificacion = value;
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
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad objRecuest.
     * 
     * @return
     *     possible object is
     *     {@link INTRequestConsulta }
     *     
     */
    public INTRequestConsulta getObjRecuest() {
        return objRecuest;
    }

    /**
     * Define el valor de la propiedad objRecuest.
     * 
     * @param value
     *     allowed object is
     *     {@link INTRequestConsulta }
     *     
     */
    public void setObjRecuest(INTRequestConsulta value) {
        this.objRecuest = value;
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
