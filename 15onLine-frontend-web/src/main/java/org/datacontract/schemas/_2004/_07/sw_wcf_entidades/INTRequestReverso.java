
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para INT_RequestReverso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_RequestReverso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosSeguridad" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_Seguridad" minOccurs="0"/>
 *         &lt;element name="IDTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoReverso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INT_RequestReverso", propOrder = {
    "datosSeguridad",
    "idTransaccion",
    "motivo",
    "tipoReverso"
})
public class INTRequestReverso {

    @XmlElement(name = "DatosSeguridad")
    protected INTSeguridad datosSeguridad;
    @XmlElement(name = "IDTransaccion")
    protected String idTransaccion;
    @XmlElement(name = "Motivo")
    protected String motivo;
    @XmlElement(name = "TipoReverso")
    protected String tipoReverso;

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
     * Obtiene el valor de la propiedad motivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Define el valor de la propiedad motivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoReverso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoReverso() {
        return tipoReverso;
    }

    /**
     * Define el valor de la propiedad tipoReverso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoReverso(String value) {
        this.tipoReverso = value;
    }

}
