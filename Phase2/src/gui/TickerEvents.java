/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.math.BigDecimal;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import eventlist.ObjectFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eventlist.Event;
import eventlist.Eventlist;

/**
 *
 * @author Dario
 */
public class TickerEvents {
    
	public String[] eventList()
	   {   
	       String url = "http://localhost:4434/events";
	       WebResource wrs = Client.create().resource(url);
	       
	       Eventlist ev = wrs.accept("application/xml").get(Eventlist.class);
	        String[] array = new String[ev.getEvent().size()];
	    for (int i = 0; i < ev.getEvent().size(); i++)
	    {
	     array[i] = ev.getEvent().get(i).getEventname();
	    }  
	        return array;
	   }
	 
	public Event getEventContent(int EventID)
	   {   
	       String url = "http://localhost:4434/events/"+EventID;
	       WebResource wrs = Client.create().resource(url);
	       
	       Event evnt = wrs.accept("application/xml").get(Event.class);
	        
	        
	        return evnt;
	   }
	
	public void newEvent(Map<String, String> eventdata) {
	
	    //neuen User anlegen
	    Event event = new ObjectFactory().createEvent();
	    
	    XMLGregorianCalendar start = null;
	    XMLGregorianCalendar ende = null;
	    try {
			start = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("start"));
			ende = DatatypeFactory.newInstance().newXMLGregorianCalendar(eventdata.get("ende"));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    event.setEventname(eventdata.get("name"));
	    event.setEventbeschreibung(eventdata.get("beschr"));
	    event.setEventtyp(eventdata.get("typ"));
	    event.setEventstart(start);
	    event.setEventdauer(ende);
	    
	    //TODO bisher noch statisch!
	    event.setUserID(BigDecimal.ONE);
	    event.setUsername("DrDare");
	
	    String url = "http://localhost:4434/events";
	    WebResource wrs = Client.create().resource(url);
	
	
	   ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(event).post(ClientResponse.class);
	
	   System.out.println(cr.getStatus());
	}
 
}
