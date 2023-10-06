
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTResplyRecargas;


/**
 * <p>Clase Java para respuestaRecarga complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaRecarga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoFlujo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensajeTransaccional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respuestaFacilito" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_ResplyRecargas" minOccurs="0"/>
 *         &lt;element name="secuenciaCore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaRecarga", propOrder = {
    "codigoFlujo",
    "mensajeTransaccional",
    "respuestaFacilito",
    "secuenciaCore"
})
public class RespuestaRecarga {

    protected String codigoFlujo;
    protected String mensajeTransaccional;
    protected INTResplyRecargas respuestaFacilito;
    protected Long secuenciaCore;

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
     *     {@link INTResplyRecargas }
     *     
     */
    public INTResplyRecargas getRespuestaFacilito() {
        return respuestaFacilito;
    }

    /**
     * Define el valor de la propiedad respuestaFacilito.
     * 
     * @param value
     *     allowed object is
     *     {@link INTResplyRecargas }
     *     
     */
    public void setRespuestaFacilito(INTResplyRecargas value) {
        this.respuestaFacilito = value;
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

}
