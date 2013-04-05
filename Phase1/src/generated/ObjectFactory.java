//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.05 at 12:30:59 PM MESZ 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
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

    private final static QName _Rezeptname_QNAME = new QName("", "rezeptname");
    private final static QName _Arbeitsschritte_QNAME = new QName("", "arbeitsschritte");
    private final static QName _Beschreibung_QNAME = new QName("", "beschreibung");
    private final static QName _Untertitel_QNAME = new QName("", "untertitel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bilder }
     * 
     */
    public Bilder createBilder() {
        return new Bilder();
    }

    /**
     * Create an instance of {@link Zutat }
     * 
     */
    public Zutat createZutat() {
        return new Zutat();
    }

    /**
     * Create an instance of {@link Zubereitung }
     * 
     */
    public Zubereitung createZubereitung() {
        return new Zubereitung();
    }

    /**
     * Create an instance of {@link Menge }
     * 
     */
    public Menge createMenge() {
        return new Menge();
    }

    /**
     * Create an instance of {@link Rezept }
     * 
     */
    public Rezept createRezept() {
        return new Rezept();
    }

    /**
     * Create an instance of {@link Bild }
     * 
     */
    public Bild createBild() {
        return new Bild();
    }

    /**
     * Create an instance of {@link Root }
     * 
     */
    public Root createRoot() {
        return new Root();
    }

    /**
     * Create an instance of {@link Zutaten }
     * 
     */
    public Zutaten createZutaten() {
        return new Zutaten();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rezeptname")
    public JAXBElement<String> createRezeptname(String value) {
        return new JAXBElement<String>(_Rezeptname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arbeitsschritte")
    public JAXBElement<String> createArbeitsschritte(String value) {
        return new JAXBElement<String>(_Arbeitsschritte_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "beschreibung")
    public JAXBElement<String> createBeschreibung(String value) {
        return new JAXBElement<String>(_Beschreibung_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "untertitel")
    public JAXBElement<String> createUntertitel(String value) {
        return new JAXBElement<String>(_Untertitel_QNAME, String.class, null, value);
    }

}