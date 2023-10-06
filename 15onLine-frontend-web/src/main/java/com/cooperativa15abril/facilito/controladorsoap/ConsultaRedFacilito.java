
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaRedFacilito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaRedFacilito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudconsulta" type="{http://controladorsoap.facilito.cooperativa15abril.com/}solicitudConsulta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaRedFacilito", propOrder = {
    "solicitudconsulta"
})
public class ConsultaRedFacilito {

    protected SolicitudConsulta solicitudconsulta;

    /**
     * Obtiene el valor de la propiedad solicitudconsulta.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudConsulta }
     *     
     */
    public SolicitudConsulta getSolicitudconsulta() {
        return solicitudconsulta;
    }

    /**
     * Define el valor de la propiedad solicitudconsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudConsulta }
     *     
     */
    public void setSolicitudconsulta(SolicitudConsulta value) {
        this.solicitudconsulta = value;
    }

}
