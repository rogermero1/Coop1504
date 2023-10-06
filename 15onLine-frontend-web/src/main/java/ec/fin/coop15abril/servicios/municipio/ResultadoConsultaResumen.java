
package ec.fin.coop15abril.servicios.municipio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para resultadoConsultaResumen complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoConsultaResumen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo_respuesta" type="{http://municipio.servicios.coop15abril.fin.ec/}estado" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contribuyente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalComision" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="TotalDeuda" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Detalle" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Rubro" type="{http://municipio.servicios.coop15abril.fin.ec/}Rubro" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoConsultaResumen", propOrder = {
    "codigoRespuesta",
    "mensaje",
    "contribuyente",
    "totalComision",
    "totalDeuda",
    "detalle"
})
public class ResultadoConsultaResumen {

    @XmlElement(name = "Codigo_respuesta")
    @XmlSchemaType(name = "string")
    protected Estado codigoRespuesta;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "Contribuyente")
    protected String contribuyente;
    @XmlElement(name = "TotalComision")
    protected Double totalComision;
    @XmlElement(name = "TotalDeuda")
    protected Double totalDeuda;
    @XmlElement(name = "Detalle")
    protected ResultadoConsultaResumen.Detalle detalle;

    /**
     * Obtiene el valor de la propiedad codigoRespuesta.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Define el valor de la propiedad codigoRespuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setCodigoRespuesta(Estado value) {
        this.codigoRespuesta = value;
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
     * Obtiene el valor de la propiedad totalComision.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalComision() {
        return totalComision;
    }

    /**
     * Define el valor de la propiedad totalComision.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalComision(Double value) {
        this.totalComision = value;
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
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoConsultaResumen.Detalle }
     *     
     */
    public ResultadoConsultaResumen.Detalle getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoConsultaResumen.Detalle }
     *     
     */
    public void setDetalle(ResultadoConsultaResumen.Detalle value) {
        this.detalle = value;
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
     *         &lt;element name="Rubro" type="{http://municipio.servicios.coop15abril.fin.ec/}Rubro" maxOccurs="unbounded" minOccurs="0"/>
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
        "rubro"
    })
    public static class Detalle {

        @XmlElement(name = "Rubro")
        protected List<Rubro> rubro;

        /**
         * Gets the value of the rubro property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rubro property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRubro().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Rubro }
         * 
         * 
         */
        public List<Rubro> getRubro() {
            if (rubro == null) {
                rubro = new ArrayList<Rubro>();
            }
            return this.rubro;
        }

    }

}
