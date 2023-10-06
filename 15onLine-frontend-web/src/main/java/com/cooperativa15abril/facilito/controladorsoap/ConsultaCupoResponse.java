
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.INTCUPOResponseCupo;


/**
 * <p>Clase Java para consultaCupoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaCupoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_CUPO.Response_Cupo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaCupoResponse", propOrder = {
    "_return"
})
public class ConsultaCupoResponse {

    @XmlElement(name = "return")
    protected INTCUPOResponseCupo _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link INTCUPOResponseCupo }
     *     
     */
    public INTCUPOResponseCupo getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link INTCUPOResponseCupo }
     *     
     */
    public void setReturn(INTCUPOResponseCupo value) {
        this._return = value;
    }

}
