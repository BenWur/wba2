//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.08 at 11:16:10 AM MESZ 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}rezeptname"/>
 *         &lt;element ref="{}untertitel" minOccurs="0"/>
 *         &lt;element ref="{}bilder" minOccurs="0"/>
 *         &lt;element ref="{}zutaten"/>
 *         &lt;element ref="{}zubereitung"/>
 *         &lt;element ref="{}kommentarspalte" minOccurs="0"/>
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
    "rezeptname",
    "untertitel",
    "bilder",
    "zutaten",
    "zubereitung",
    "kommentarspalte"
})
@XmlRootElement(name = "rezept")
public class Rezept {

    @XmlElement(required = true)
    protected String rezeptname;
    protected String untertitel;
    protected Bilder bilder;
    @XmlElement(required = true)
    protected Zutaten zutaten;
    @XmlElement(required = true)
    protected Zubereitung zubereitung;
    protected Kommentarspalte kommentarspalte;

    /**
     * Gets the value of the rezeptname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRezeptname() {
        return rezeptname;
    }

    /**
     * Sets the value of the rezeptname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRezeptname(String value) {
        this.rezeptname = value;
    }

    /**
     * Gets the value of the untertitel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUntertitel() {
        return untertitel;
    }

    /**
     * Sets the value of the untertitel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUntertitel(String value) {
        this.untertitel = value;
    }

    /**
     * Gets the value of the bilder property.
     * 
     * @return
     *     possible object is
     *     {@link Bilder }
     *     
     */
    public Bilder getBilder() {
        return bilder;
    }

    /**
     * Sets the value of the bilder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bilder }
     *     
     */
    public void setBilder(Bilder value) {
        this.bilder = value;
    }

    /**
     * Gets the value of the zutaten property.
     * 
     * @return
     *     possible object is
     *     {@link Zutaten }
     *     
     */
    public Zutaten getZutaten() {
        return zutaten;
    }

    /**
     * Sets the value of the zutaten property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zutaten }
     *     
     */
    public void setZutaten(Zutaten value) {
        this.zutaten = value;
    }

    /**
     * Gets the value of the zubereitung property.
     * 
     * @return
     *     possible object is
     *     {@link Zubereitung }
     *     
     */
    public Zubereitung getZubereitung() {
        return zubereitung;
    }

    /**
     * Sets the value of the zubereitung property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zubereitung }
     *     
     */
    public void setZubereitung(Zubereitung value) {
        this.zubereitung = value;
    }

    /**
     * Gets the value of the kommentarspalte property.
     * 
     * @return
     *     possible object is
     *     {@link Kommentarspalte }
     *     
     */
    public Kommentarspalte getKommentarspalte() {
        return kommentarspalte;
    }

    /**
     * Sets the value of the kommentarspalte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kommentarspalte }
     *     
     */
    public void setKommentarspalte(Kommentarspalte value) {
        this.kommentarspalte = value;
    }

}
