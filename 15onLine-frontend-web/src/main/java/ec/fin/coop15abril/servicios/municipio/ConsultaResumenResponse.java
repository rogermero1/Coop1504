
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaResumenResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaResumenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultaResumen" type="{http://municipio.servicios.coop15abril.fin.ec/}resultadoConsultaResumen" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaResumenResponse", propOrder = {
    "consultaResumen"
})
public class ConsultaResumenResponse {

    @XmlElement(name = "ConsultaResumen")
    protected ResultadoConsultaResumen consultaResumen;

    /**
     * Obtiene el valor de la propiedad consultaResumen.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoConsultaResumen }
     *     
     */
    public ResultadoConsultaResumen getConsultaResumen() {
        return consultaResumen;
    }

    /**
     * Define el valor de la propiedad consultaResumen.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoConsultaResumen }
     *     
     */
    public void setConsultaResumen(ResultadoConsultaResumen value) {
        this.consultaResumen = value;
    }

}
