
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfINT_RequestPagoOTP.INT_DataPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfINT_RequestPagoOTP.INT_DataPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INT_RequestPagoOTP.INT_DataPago" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_RequestPagoOTP.INT_DataPago" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfINT_RequestPagoOTP.INT_DataPago", propOrder = {
    "intRequestPagoOTPINTDataPago"
})
public class ArrayOfINTRequestPagoOTPINTDataPago {

    @XmlElement(name = "INT_RequestPagoOTP.INT_DataPago")
    protected List<INTRequestPagoOTPINTDataPago> intRequestPagoOTPINTDataPago;

    /**
     * Gets the value of the intRequestPagoOTPINTDataPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intRequestPagoOTPINTDataPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINTRequestPagoOTPINTDataPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link INTRequestPagoOTPINTDataPago }
     * 
     * 
     */
    public List<INTRequestPagoOTPINTDataPago> getINTRequestPagoOTPINTDataPago() {
        if (intRequestPagoOTPINTDataPago == null) {
            intRequestPagoOTPINTDataPago = new ArrayList<INTRequestPagoOTPINTDataPago>();
        }
        return this.intRequestPagoOTPINTDataPago;
    }

}
