
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para canal.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="canal">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VEN"/>
 *     &lt;enumeration value="ATM"/>
 *     &lt;enumeration value="WEB"/>
 *     &lt;enumeration value="APP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "canal")
@XmlEnum
public enum Canal {

    VEN,
    ATM,
    WEB,
    APP;

    public String value() {
        return name();
    }

    public static Canal fromValue(String v) {
        return valueOf(v);
    }

}
