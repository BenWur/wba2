//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.10 um 02:10:18 PM CEST 
//


package eventlist;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{}eventname"/>
 *         &lt;element ref="{}eventbeschreibung" minOccurs="0"/>
 *         &lt;element ref="{}username" minOccurs="0"/>
 *         &lt;element ref="{}eventtyp" minOccurs="0"/>
 *         &lt;element name="eventstart" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="eventdauer" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element ref="{}eventbewertung" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="userID" type="{http://www.w3.org/2001/XMLSchema}decimal" />
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
    "eventname",
    "eventbeschreibung",
    "username",
    "eventtyp",
    "eventstart",
    "eventdauer",
    "eventbewertung"
})
@XmlRootElement(name = "event")
public class Event {

    @XmlElement(required = true)
    protected String eventname;
    protected String eventbeschreibung;
    protected String username;
    @XmlElement(defaultValue = "Football")
    protected String eventtyp;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar eventstart;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar eventdauer;
    protected Byte eventbewertung;
    @XmlAttribute(name = "userID")
    protected BigDecimal userID;
    @XmlAttribute(name = "eventID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger eventID;

    /**
     * Ruft den Wert der eventname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventname() {
        return eventname;
    }

    /**
     * Legt den Wert der eventname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventname(String value) {
        this.eventname = value;
    }

    /**
     * Ruft den Wert der eventbeschreibung-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventbeschreibung() {
        return eventbeschreibung;
    }

    /**
     * Legt den Wert der eventbeschreibung-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventbeschreibung(String value) {
        this.eventbeschreibung = value;
    }

    /**
     * Ruft den Wert der username-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Wert der username-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Ruft den Wert der eventtyp-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventtyp() {
        return eventtyp;
    }

    /**
     * Legt den Wert der eventtyp-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventtyp(String value) {
        this.eventtyp = value;
    }

    /**
     * Ruft den Wert der eventstart-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventstart() {
        return eventstart;
    }

    /**
     * Legt den Wert der eventstart-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventstart(XMLGregorianCalendar value) {
        this.eventstart = value;
    }

    /**
     * Ruft den Wert der eventdauer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventdauer() {
        return eventdauer;
    }

    /**
     * Legt den Wert der eventdauer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventdauer(XMLGregorianCalendar value) {
        this.eventdauer = value;
    }

    /**
     * Ruft den Wert der eventbewertung-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getEventbewertung() {
        return eventbewertung;
    }

    /**
     * Legt den Wert der eventbewertung-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setEventbewertung(Byte value) {
        this.eventbewertung = value;
    }

    /**
     * Ruft den Wert der userID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUserID() {
        return userID;
    }

    /**
     * Legt den Wert der userID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUserID(BigDecimal value) {
        this.userID = value;
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
