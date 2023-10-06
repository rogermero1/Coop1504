
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfINT_ResplyConsulta.INT_DataConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfINT_ResplyConsulta.INT_DataConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INT_ResplyConsulta.INT_DataConsulta" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}INT_ResplyConsulta.INT_DataConsulta" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfINT_ResplyConsulta.INT_DataConsulta", propOrder = {
    "intResplyConsultaINTDataConsulta"
})
public class ArrayOfINTResplyConsultaINTDataConsulta {

    @XmlElement(name = "INT_ResplyConsulta.INT_DataConsulta")
    protected List<INTResplyConsultaINTDataConsulta> intResplyConsultaINTDataConsulta;

    /**
     * Gets the value of the intResplyConsultaINTDataConsulta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intResplyConsultaINTDataConsulta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINTResplyConsultaINTDataConsulta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link INTResplyConsultaINTDataConsulta }
     * 
     * 
     */
    public List<INTResplyConsultaINTDataConsulta> getINTResplyConsultaINTDataConsulta() {
        if (intResplyConsultaINTDataConsulta == null) {
            intResplyConsultaINTDataConsulta = new ArrayList<INTResplyConsultaINTDataConsulta>();
        }
        return this.intResplyConsultaINTDataConsulta;
    }

}
