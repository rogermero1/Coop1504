
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.sw_wcf_entidades.EntidadesFinancierasCIConsultaEntidades;


/**
 * <p>Clase Java para consultasEntidadesCIResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultasEntidadesCIResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}EntidadesFinancierasCI.ConsultaEntidades" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultasEntidadesCIResponse", propOrder = {
    "_return"
})
public class ConsultasEntidadesCIResponse {

    @XmlElement(name = "return")
    protected EntidadesFinancierasCIConsultaEntidades _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link EntidadesFinancierasCIConsultaEntidades }
     *     
     */
    public EntidadesFinancierasCIConsultaEntidades getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link EntidadesFinancierasCIConsultaEntidades }
     *     
     */
    public void setReturn(EntidadesFinancierasCIConsultaEntidades value) {
        this._return = value;
    }

}
