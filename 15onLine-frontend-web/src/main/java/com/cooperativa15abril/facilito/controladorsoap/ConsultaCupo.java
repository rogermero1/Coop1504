
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaCupo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaCupo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudconsultacupo" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudConsultaCupo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaCupo", propOrder = {
    "solicitudconsultacupo"
})
public class ConsultaCupo {

    protected SolicitudConsultaCupo solicitudconsultacupo;

    /**
     * Obtiene el valor de la propiedad solicitudconsultacupo.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudConsultaCupo }
     *     
     */
    public SolicitudConsultaCupo getSolicitudconsultacupo() {
        return solicitudconsultacupo;
    }

    /**
     * Define el valor de la propiedad solicitudconsultacupo.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudConsultaCupo }
     *     
     */
    public void setSolicitudconsultacupo(SolicitudConsultaCupo value) {
        this.solicitudconsultacupo = value;
    }

}
