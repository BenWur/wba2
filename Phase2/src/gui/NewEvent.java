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
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Dario
 */
public class NewEvent {

public NewEvent(Map<String, String> eventdata) {

        //neuen User anlegen
        Event event = new ObjectFactory().createEvent();
        
    
        XMLGregorianCalendar start = null;
        XMLGregorianCalendar ende = null;
        try {
            start = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("start"));
            start.setSecond(0);
            ende = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("ende"));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NewEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        event.setEventname(eventdata.get("name"));
        event.setEventbeschreibung(eventdata.get("beschr"));
        event.setEventtyp(eventdata.get("typ"));
        event.setEventstart(start);
        event.setEventdauer(ende);

        String url = "http://localhost:4434/events";
        WebResource wrs = Client.create().resource(url);


       ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(event).post(ClientResponse.class);

       System.out.println(cr.getStatus());
    }
}
