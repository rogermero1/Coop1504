
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfINT_ResplyPago.INT_DataPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfINT_ResplyPago.INT_DataPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INT_ResplyPago.INT_DataPago" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_ResplyPago.INT_DataPago" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfINT_ResplyPago.INT_DataPago", propOrder = {
    "intResplyPagoINTDataPago"
})
public class ArrayOfINTResplyPagoINTDataPago {

    @XmlElement(name = "INT_ResplyPago.INT_DataPago")
    protected List<INTResplyPagoINTDataPago> intResplyPagoINTDataPago;

    /**
     * Gets the value of the intResplyPagoINTDataPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intResplyPagoINTDataPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINTResplyPagoINTDataPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link INTResplyPagoINTDataPago }
     * 
     * 
     */
    public List<INTResplyPagoINTDataPago> getINTResplyPagoINTDataPago() {
        if (intResplyPagoINTDataPago == null) {
            intResplyPagoINTDataPago = new ArrayList<INTResplyPagoINTDataPago>();
        }
        return this.intResplyPagoINTDataPago;
    }

}
