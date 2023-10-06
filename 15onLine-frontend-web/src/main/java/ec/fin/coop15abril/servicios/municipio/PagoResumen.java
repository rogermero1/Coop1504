
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pagoResumen complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pagoResumen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfoPago" type="{http://municipio.servicios.coop15abril.fin.ec/}pagoInResumen" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pagoResumen", propOrder = {
    "infoPago"
})
public class PagoResumen {

    @XmlElement(name = "InfoPago")
    protected PagoInResumen infoPago;

    /**
     * Obtiene el valor de la propiedad infoPago.
     * 
     * @return
     *     possible object is
     *     {@link PagoInResumen }
     *     
     */
    public PagoInResumen getInfoPago() {
        return infoPago;
    }

    /**
     * Define el valor de la propiedad infoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link PagoInResumen }
     *     
     */
    public void setInfoPago(PagoInResumen value) {
        this.infoPago = value;
    }

}
