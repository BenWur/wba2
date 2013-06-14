//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.14 um 02:05:42 PM CEST 
//


package userlist;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Username_QNAME = new QName("", "username");
    private final static QName _Geburtsdatum_QNAME = new QName("", "geburtsdatum");
    private final static QName _Vorname_QNAME = new QName("", "vorname");
    private final static QName _AnzEvents_QNAME = new QName("", "anzEvents");
    private final static QName _Stadt_QNAME = new QName("", "stadt");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _Land_QNAME = new QName("", "land");
    private final static QName _Gender_QNAME = new QName("", "gender");
    private final static QName _Beitraege_QNAME = new QName("", "beitraege");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Userlist }
     * 
     */
    public Userlist createUserlist() {
        return new Userlist();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Favoriten }
     * 
     */
    public Favoriten createFavoriten() {
        return new Favoriten();
    }

    /**
     * Create an instance of {@link Folger }
     * 
     */
    public Folger createFolger() {
        return new Folger();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "username")
    public JAXBElement<String> createUsername(String value) {
        return new JAXBElement<String>(_Username_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "geburtsdatum")
    public JAXBElement<XMLGregorianCalendar> createGeburtsdatum(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Geburtsdatum_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vorname")
    public JAXBElement<String> createVorname(String value) {
        return new JAXBElement<String>(_Vorname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "anzEvents")
    public JAXBElement<BigDecimal> createAnzEvents(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AnzEvents_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "stadt")
    public JAXBElement<String> createStadt(String value) {
        return new JAXBElement<String>(_Stadt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "land", defaultValue = "Germany")
    public JAXBElement<String> createLand(String value) {
        return new JAXBElement<String>(_Land_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gender")
    public JAXBElement<String> createGender(String value) {
        return new JAXBElement<String>(_Gender_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "beitraege")
    public JAXBElement<BigDecimal> createBeitraege(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Beitraege_QNAME, BigDecimal.class, null, value);
    }

}
