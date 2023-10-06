
package org.datacontract.schemas._2004._07.sw_wcf_entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EntidadesFinancierasCI.ConsultaEntidades complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EntidadesFinancierasCI.ConsultaEntidades">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntidadesCI" type="{http://schemas.datacontract.org/2004/07/SW.WCF.Entidades.Integracion}ArrayOfEntidadesFinancierasCI.INT_DataConsulta" minOccurs="0"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntidadesFinancierasCI.ConsultaEntidades", propOrder = {
    "codResultado",
    "entidadesCI",
    "mensaje"
})
public class EntidadesFinancierasCIConsultaEntidades {

    @XmlElement(name = "CodResultado")
    protected String codResultado;
    @XmlElement(name = "EntidadesCI")
    protected ArrayOfEntidadesFinancierasCIINTDataConsulta entidadesCI;
    @XmlElement(name = "Mensaje")
    protected String mensaje;

    /**
     * Obtiene el valor de la propiedad codResultado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodResultado() {
        return codResultado;
    }

    /**
     * Define el valor de la propiedad codResultado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodResultado(String value) {
        this.codResultado = value;
    }

    /**
     * Obtiene el valor de la propiedad entidadesCI.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEntidadesFinancierasCIINTDataConsulta }
     *     
     */
    public ArrayOfEntidadesFinancierasCIINTDataConsulta getEntidadesCI() {
        return entidadesCI;
    }

    /**
     * Define el valor de la propiedad entidadesCI.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEntidadesFinancierasCIINTDataConsulta }
     *     
     */
    public void setEntidadesCI(ArrayOfEntidadesFinancierasCIINTDataConsulta value) {
        this.entidadesCI = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

}
