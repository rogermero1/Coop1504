
package ec.fin.coop15abril.servicios.municipio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para resultadoConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo_respuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalDeuda" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Contribuyente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Detalle" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Titulo" type="{http://municipio.servicios.coop15abril.fin.ec/}Titulo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comision" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Iva" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoConsulta", propOrder = {
    "codigoRespuesta",
    "totalDeuda",
    "contribuyente",
    "detalle",
    "mensaje",
    "comision",
    "iva"
})
public class ResultadoConsulta {

    @XmlElement(name = "Codigo_respuesta")
    protected String codigoRespuesta;
    @XmlElement(name = "TotalDeuda")
    protected Double totalDeuda;
    @XmlElement(name = "Contribuyente")
    protected String contribuyente;
    @XmlElement(name = "Detalle")
    protected ResultadoConsulta.Detalle detalle;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "Comision")
    protected Double comision;
    @XmlElement(name = "Iva")
    protected Double iva;

    /**
     * Obtiene el valor de la propiedad codigoRespuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Define el valor de la propiedad codigoRespuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRespuesta(String value) {
        this.codigoRespuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad totalDeuda.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalDeuda() {
        return totalDeuda;
    }

    /**
     * Define el valor de la propiedad totalDeuda.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalDeuda(Double value) {
        this.totalDeuda = value;
    }

    /**
     * Obtiene el valor de la propiedad contribuyente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuyente() {
        return contribuyente;
    }

    /**
     * Define el valor de la propiedad contribuyente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuyente(String value) {
        this.contribuyente = value;
    }

    /**
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoConsulta.Detalle }
     *     
     */
    public ResultadoConsulta.Detalle getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoConsulta.Detalle }
     *     
     */
    public void setDetalle(ResultadoConsulta.Detalle value) {
        this.detalle = value;
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
     * Obtiene el valor de la propiedad comision.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getComision() {
        return comision;
    }

    /**
     * Define el valor de la propiedad comision.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setComision(Double value) {
        this.comision = value;
    }

    /**
     * Obtiene el valor de la propiedad iva.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIva() {
        return iva;
    }

    /**
     * Define el valor de la propiedad iva.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIva(Double value) {
        this.iva = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Titulo" type="{http://municipio.servicios.coop15abril.fin.ec/}Titulo" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "titulo"
    })
    public static class Detalle {

        @XmlElement(name = "Titulo")
        protected List<Titulo> titulo;

        /**
         * Gets the value of the titulo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the titulo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTitulo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Titulo }
         * 
         * 
         */
        public List<Titulo> getTitulo() {
            if (titulo == null) {
                titulo = new ArrayList<Titulo>();
            }
            return this.titulo;
        }

    }

}
