package guidata;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.ObjectFactory;
import eventcontentlist.TickerBeitrag;

import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Erstellt neuen Beitrag
 *
 * @author Dario & Ben
 */
public class NewBeitrag {

    /**
     * Konstruktor eines neuen Beitrags
     *
     * @param eventID, beitrag
     */
    public NewBeitrag(int eventID, String beitrag) {
        //Beitrag erstellen
        TickerBeitrag kommi = new ObjectFactory().createTickerBeitrag();
        //Beitrag gemäß xsd füllen
        kommi.setText(beitrag);

        //aktuelle Zeit uebergeben
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String uhrzeit = sdf.format(new Date());
        XMLGregorianCalendar aktuell = null;
        try {
            aktuell = DatatypeFactory.newInstance().newXMLGregorianCalendar(uhrzeit);	//aktuelle Uhrzeit
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        kommi.setZeit(aktuell);

        String url = "http://localhost:4434/events/" + eventID + "/eventcontent/beitrag"; //Url des Beitrags
        WebResource wrs = Client.create().resource(url);	//erstellt neuen Beitrag

        //erstellt Beitrag und gibt einen Response zurück. Server übernimmt die genaue Verwaltung
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(kommi).post(ClientResponse.class);	//POST
        System.out.println(cr.getStatus());
    }
}
