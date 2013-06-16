package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import eventlist.ObjectFactory;
import eventlist.Event;

/**
 * Erstellt neues Event
 * @author Dario & Ben
 */

public class NewEvent {

public NewEvent(Map<String, String> eventdata) {

        //neues Event anlegen
        Event event = new ObjectFactory().createEvent();
        
        //Befüllung gemäß der xsd
        XMLGregorianCalendar start = null;
        XMLGregorianCalendar ende = null;
        try {
            start = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("start"));
            start.setSecond(0);
            ende = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("ende"));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        event.setUsername(eventdata.get("admin"));
        event.setEventname(eventdata.get("name"));
        event.setEventbeschreibung(eventdata.get("beschr"));
        event.setEventtyp(eventdata.get("typ"));
        event.setEventstart(start);
        event.setEventdauer(ende);
        event.setEventbewertung(Integer.parseInt(eventdata.get("bewertung")));

        String url = "http://localhost:4434/events";
        WebResource wrs = Client.create().resource(url);//erstellt neues Event

        //erstellt Event und gibt einen Response zurück. Server übernimmt die genaue Verwaltung
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(event).post(ClientResponse.class);

        System.out.println(cr.getStatus());
    }
}
