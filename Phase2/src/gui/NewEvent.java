/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
 *
 * @author Dario
 */
public class NewEvent {

public NewEvent(Map<String, String> eventdata) {

        //neuen User anlegen
        Event event = new ObjectFactory().createEvent();
        
        XMLGregorianCalendar start = null;
        XMLGregorianCalendar dauer = null;
        try {
            start = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("start"));
            dauer = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("dauer"));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        event.setEventname(eventdata.get("name"));
        event.setEventbeschreibung(eventdata.get("beschr"));
        //event.setEventtyp(eventdata.get("typ"));
        event.setEventstart(start);
        event.setEventdauer(dauer);

        String url = "http://localhost:4434/events";
        WebResource wrs = Client.create().resource(url);


       ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(event).post(ClientResponse.class);

       System.out.println(cr.getStatus());
    }
}
