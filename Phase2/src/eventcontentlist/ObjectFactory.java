//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.27 um 10:50:06 AM CEST 
//


package eventcontentlist;

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

    private final static QName _Text_QNAME = new QName("", "text");
    private final static QName _KommentarText_QNAME = new QName("", "kommentarText");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Eventcontentlist }
     * 
     */
    public Eventcontentlist createEventcontentlist() {
        return new Eventcontentlist();
    }

    /**
     * Create an instance of {@link Eventcontent }
     * 
     */
    public Eventcontent createEventcontent() {
        return new Eventcontent();
    }

    /**
     * Create an instance of {@link AktuellerStand }
     * 
     */
    public AktuellerStand createAktuellerStand() {
        return new AktuellerStand();
    }

    /**
     * Create an instance of {@link TickerBeitrag }
     * 
     */
    public TickerBeitrag createTickerBeitrag() {
        return new TickerBeitrag();
    }

    /**
     * Create an instance of {@link Kommentar }
     * 
     */
    public Kommentar createKommentar() {
        return new Kommentar();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "text")
    public JAXBElement<String> createText(String value) {
        return new JAXBElement<String>(_Text_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "kommentarText")
    public JAXBElement<String> createKommentarText(String value) {
        return new JAXBElement<String>(_KommentarText_QNAME, String.class, null, value);
    }

}
