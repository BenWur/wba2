//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.14 um 02:04:13 PM CEST 
//


package eventcontentlist;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{}aktuellerStand" minOccurs="0"/>
 *         &lt;element ref="{}tickerBeitrag" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="eventID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "aktuellerStand",
    "tickerBeitrag"
})
@XmlRootElement(name = "eventcontent")
public class Eventcontent {

    protected AktuellerStand aktuellerStand;
    protected List<TickerBeitrag> tickerBeitrag;
    @XmlAttribute(name = "eventID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger eventID;

    /**
     * Ruft den Wert der aktuellerStand-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AktuellerStand }
     *     
     */
    public AktuellerStand getAktuellerStand() {
        return aktuellerStand;
    }

    /**
     * Legt den Wert der aktuellerStand-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AktuellerStand }
     *     
     */
    public void setAktuellerStand(AktuellerStand value) {
        this.aktuellerStand = value;
    }

    /**
     * Gets the value of the tickerBeitrag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tickerBeitrag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTickerBeitrag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TickerBeitrag }
     * 
     * 
     */
    public List<TickerBeitrag> getTickerBeitrag() {
        if (tickerBeitrag == null) {
            tickerBeitrag = new ArrayList<TickerBeitrag>();
        }
        return this.tickerBeitrag;
    }

    /**
     * Ruft den Wert der eventID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEventID() {
        return eventID;
    }

    /**
     * Legt den Wert der eventID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEventID(BigInteger value) {
        this.eventID = value;
    }

}
