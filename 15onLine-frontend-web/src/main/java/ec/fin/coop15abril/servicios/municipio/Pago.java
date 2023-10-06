
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfoPago" type="{http://municipio.servicios.coop15abril.fin.ec/}pagoIn" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pago", propOrder = {
    "infoPago"
})
public class Pago {

    @XmlElement(name = "InfoPago")
    protected PagoIn infoPago;

    /**
     * Obtiene el valor de la propiedad infoPago.
     * 
     * @return
     *     possible object is
     *     {@link PagoIn }
     *     
     */
    public PagoIn getInfoPago() {
        return infoPago;
    }

    /**
     * Define el valor de la propiedad infoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link PagoIn }
     *     
     */
    public void setInfoPago(PagoIn value) {
        this.infoPago = value;
    }

}
