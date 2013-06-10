//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.10 um 02:55:53 PM CEST 
//


package eventcontentlist;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}text"/>
 *         &lt;element ref="{}kommentar" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="typ" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="zeit" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="tickerBeitragID" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "text",
    "kommentar"
})
@XmlRootElement(name = "tickerBeitrag")
public class TickerBeitrag {

    @XmlElement(required = true)
    protected String text;
    protected List<Kommentar> kommentar;
    @XmlAttribute(name = "typ")
    protected String typ;
    @XmlAttribute(name = "zeit")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar zeit;
    @XmlAttribute(name = "tickerBeitragID")
    protected BigInteger tickerBeitragID;

    /**
     * Ruft den Wert der text-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Legt den Wert der text-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the kommentar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kommentar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKommentar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Kommentar }
     * 
     * 
     */
    public List<Kommentar> getKommentar() {
        if (kommentar == null) {
            kommentar = new ArrayList<Kommentar>();
        }
        return this.kommentar;
    }

    /**
     * Ruft den Wert der typ-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Legt den Wert der typ-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyp(String value) {
        this.typ = value;
    }

    /**
     * Ruft den Wert der zeit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getZeit() {
        return zeit;
    }

    /**
     * Legt den Wert der zeit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setZeit(XMLGregorianCalendar value) {
        this.zeit = value;
    }

    /**
     * Ruft den Wert der tickerBeitragID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTickerBeitragID() {
        return tickerBeitragID;
    }

    /**
     * Legt den Wert der tickerBeitragID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTickerBeitragID(BigInteger value) {
        this.tickerBeitragID = value;
    }

}
