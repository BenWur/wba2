//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.04 um 01:18:12 PM CEST 
//


package userlist;

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
 *         &lt;element ref="{}username"/>
 *         &lt;element ref="{}vorname"/>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}gender"/>
 *         &lt;element ref="{}geburtsdatum"/>
 *         &lt;element ref="{}land"/>
 *         &lt;element ref="{}stadt"/>
 *         &lt;element ref="{}anzEvents"/>
 *         &lt;element ref="{}beitraege"/>
 *         &lt;element ref="{}favoriten"/>
 *         &lt;element ref="{}folger"/>
 *       &lt;/sequence>
 *       &lt;attribute name="userID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "vorname",
    "name",
    "gender",
    "geburtsdatum",
    "land",
    "stadt",
    "anzEvents",
    "beitraege",
    "favoriten",
    "folger"
})
@XmlRootElement(name = "user")
public class User {

    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String vorname;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String gender;
    @XmlElement(required = true)
    protected XMLGregorianCalendar geburtsdatum;
    @XmlElement(required = true, defaultValue = "Germany")
    protected LandType land;
    @XmlElement(required = true)
    protected String stadt;
    @XmlElement(required = true)
    protected BigDecimal anzEvents;
    @XmlElement(required = true)
    protected BigDecimal beitraege;
    @XmlElement(required = true)
    protected Favoriten favoriten;
    @XmlElement(required = true)
    protected Folger folger;
    @XmlAttribute(name = "userID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger userID;

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
     * Ruft den Wert der vorname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Legt den Wert der vorname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVorname(String value) {
        this.vorname = value;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der gender-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Legt den Wert der gender-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Ruft den Wert der geburtsdatum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Legt den Wert der geburtsdatum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGeburtsdatum(XMLGregorianCalendar value) {
        this.geburtsdatum = value;
    }

    /**
     * Ruft den Wert der land-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LandType }
     *     
     */
    public LandType getLand() {
        return land;
    }

    /**
     * Legt den Wert der land-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LandType }
     *     
     */
    public void setLand(LandType value) {
        this.land = value;
    }

    /**
     * Ruft den Wert der stadt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStadt() {
        return stadt;
    }

    /**
     * Legt den Wert der stadt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStadt(String value) {
        this.stadt = value;
    }

    /**
     * Ruft den Wert der anzEvents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAnzEvents() {
        return anzEvents;
    }

    /**
     * Legt den Wert der anzEvents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAnzEvents(BigDecimal value) {
        this.anzEvents = value;
    }

    /**
     * Ruft den Wert der beitraege-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBeitraege() {
        return beitraege;
    }

    /**
     * Legt den Wert der beitraege-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBeitraege(BigDecimal value) {
        this.beitraege = value;
    }

    /**
     * Ruft den Wert der favoriten-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Favoriten }
     *     
     */
    public Favoriten getFavoriten() {
        return favoriten;
    }

    /**
     * Legt den Wert der favoriten-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Favoriten }
     *     
     */
    public void setFavoriten(Favoriten value) {
        this.favoriten = value;
    }

    /**
     * Ruft den Wert der folger-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Folger }
     *     
     */
    public Folger getFolger() {
        return folger;
    }

    /**
     * Legt den Wert der folger-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Folger }
     *     
     */
    public void setFolger(Folger value) {
        this.folger = value;
    }

    /**
     * Ruft den Wert der userID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUserID() {
        return userID;
    }

    /**
     * Legt den Wert der userID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUserID(BigInteger value) {
        this.userID = value;
    }

}
