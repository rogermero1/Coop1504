
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para INT_RequestPago.INT_DataPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="INT_RequestPago.INT_DataPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDRubro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorConComision" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INT_RequestPago.INT_DataPago", propOrder = {
    "idRubro",
    "valorConComision"
})
public class INTRequestPagoINTDataPago {

    @XmlElement(name = "IDRubro")
    protected String idRubro;
    @XmlElement(name = "ValorConComision")
    protected BigDecimal valorConComision;

    /**
     * Obtiene el valor de la propiedad idRubro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDRubro() {
        return idRubro;
    }

    /**
     * Define el valor de la propiedad idRubro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDRubro(String value) {
        this.idRubro = value;
    }

    /**
     * Obtiene el valor de la propiedad valorConComision.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorConComision() {
        return valorConComision;
    }

    /**
     * Define el valor de la propiedad valorConComision.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorConComision(BigDecimal value) {
        this.valorConComision = value;
    }

}
