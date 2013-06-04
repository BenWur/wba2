//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.04 um 01:18:12 PM CEST 
//


package userlist;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für null.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType>
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Germany"/>
 *     &lt;enumeration value="Italy"/>
 *     &lt;enumeration value="France"/>
 *     &lt;enumeration value="Japan"/>
 *     &lt;enumeration value="USA"/>
 *     &lt;enumeration value="Brazil"/>
 *     &lt;enumeration value="Albania"/>
 *     &lt;enumeration value="Spain"/>
 *     &lt;enumeration value="Egypt"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "")
@XmlEnum
public enum LandType {

    @XmlEnumValue("Germany")
    GERMANY("Germany"),
    @XmlEnumValue("Italy")
    ITALY("Italy"),
    @XmlEnumValue("France")
    FRANCE("France"),
    @XmlEnumValue("Japan")
    JAPAN("Japan"),
    USA("USA"),
    @XmlEnumValue("Brazil")
    BRAZIL("Brazil"),
    @XmlEnumValue("Albania")
    ALBANIA("Albania"),
    @XmlEnumValue("Spain")
    SPAIN("Spain"),
    @XmlEnumValue("Egypt")
    EGYPT("Egypt");
    private final String value;

    LandType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LandType fromValue(String v) {
        for (LandType c: LandType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
