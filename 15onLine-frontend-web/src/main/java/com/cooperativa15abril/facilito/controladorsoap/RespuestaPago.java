
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTResplyPago;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTResplyReverso;


/**
 * <p>Clase Java para respuestaPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoFlujo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datoPago" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudDataPago" minOccurs="0"/>
 *         &lt;element name="datosFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensajeTransaccional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respuestaFacilito" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_ResplyPago" minOccurs="0"/>
 *         &lt;element name="respuestaReverso" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_ResplyReverso" minOccurs="0"/>
 *         &lt;element name="secuenciaCore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "respuestaPago", propOrder = {
    "codigoFlujo",
    "datoPago",
    "datosFactura",
    "idTransaccion",
    "mensajeTransaccional",
    "respuestaFacilito",
    "respuestaReverso",
    "secuenciaCore",
    "xmlAdd"
})
public class RespuestaPago {

    protected String codigoFlujo;
    protected SolicitudDataPago datoPago;
    protected String datosFactura;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    protected String mensajeTransaccional;
    protected INTResplyPago respuestaFacilito;
    protected INTResplyReverso respuestaReverso;
    protected Long secuenciaCore;
    protected String xmlAdd;

    /**
     * Obtiene el valor de la propiedad codigoFlujo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoFlujo() {
        return codigoFlujo;
    }

    /**
     * Define el valor de la propiedad codigoFlujo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoFlujo(String value) {
        this.codigoFlujo = value;
    }

    /**
     * Obtiene el valor de la propiedad datoPago.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDataPago }
     *     
     */
    public SolicitudDataPago getDatoPago() {
        return datoPago;
    }

    /**
     * Define el valor de la propiedad datoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDataPago }
     *     
     */
    public void setDatoPago(SolicitudDataPago value) {
        this.datoPago = value;
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
     * Obtiene el valor de la propiedad mensajeTransaccional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeTransaccional() {
        return mensajeTransaccional;
    }

    /**
     * Define el valor de la propiedad mensajeTransaccional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeTransaccional(String value) {
        this.mensajeTransaccional = value;
    }

    /**
     * Obtiene el valor de la propiedad respuestaFacilito.
     * 
     * @return
     *     possible object is
     *     {@link INTResplyPago }
     *     
     */
    public INTResplyPago getRespuestaFacilito() {
        return respuestaFacilito;
    }

    /**
     * Define el valor de la propiedad respuestaFacilito.
     * 
     * @param value
     *     allowed object is
     *     {@link INTResplyPago }
     *     
     */
    public void setRespuestaFacilito(INTResplyPago value) {
        this.respuestaFacilito = value;
    }

    /**
     * Obtiene el valor de la propiedad respuestaReverso.
     * 
     * @return
     *     possible object is
     *     {@link INTResplyReverso }
     *     
     */
    public INTResplyReverso getRespuestaReverso() {
        return respuestaReverso;
    }

    /**
     * Define el valor de la propiedad respuestaReverso.
     * 
     * @param value
     *     allowed object is
     *     {@link INTResplyReverso }
     *     
     */
    public void setRespuestaReverso(INTResplyReverso value) {
        this.respuestaReverso = value;
    }

    /**
     * Obtiene el valor de la propiedad secuenciaCore.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecuenciaCore() {
        return secuenciaCore;
    }

    /**
     * Define el valor de la propiedad secuenciaCore.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecuenciaCore(Long value) {
        this.secuenciaCore = value;
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
