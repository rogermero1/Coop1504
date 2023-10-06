
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pagoResumenResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pagoResumenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PagoResumen" type="{http://municipio.servicios.coop15abril.fin.ec/}pagoOut" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pagoResumenResponse", propOrder = {
    "pagoResumen"
})
public class PagoResumenResponse {

    @XmlElement(name = "PagoResumen")
    protected PagoOut pagoResumen;

    /**
     * Obtiene el valor de la propiedad pagoResumen.
     * 
     * @return
     *     possible object is
     *     {@link PagoOut }
     *     
     */
    public PagoOut getPagoResumen() {
        return pagoResumen;
    }

    /**
     * Define el valor de la propiedad pagoResumen.
     * 
     * @param value
     *     allowed object is
     *     {@link PagoOut }
     *     
     */
    public void setPagoResumen(PagoOut value) {
        this.pagoResumen = value;
    }

}
