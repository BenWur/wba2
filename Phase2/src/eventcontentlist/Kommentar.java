//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.14 um 03:33:02 PM CEST 
//


package eventcontentlist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kommentarUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}kommentarText"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "kommentarUser",
    "kommentarText"
})
@XmlRootElement(name = "kommentar")
public class Kommentar {

    @XmlElement(required = true)
    protected String kommentarUser;
    @XmlElement(required = true)
    protected String kommentarText;

    /**
     * Ruft den Wert der kommentarUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKommentarUser() {
        return kommentarUser;
    }

    /**
     * Legt den Wert der kommentarUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKommentarUser(String value) {
        this.kommentarUser = value;
    }

    /**
     * Ruft den Wert der kommentarText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKommentarText() {
        return kommentarText;
    }

    /**
     * Legt den Wert der kommentarText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKommentarText(String value) {
        this.kommentarText = value;
    }

}
