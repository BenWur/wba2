/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.math.BigInteger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import eventlist.Event;
import eventlist.Eventlist;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dario
 */
public class TickerEvents {
    
	public List<Event> eventList()
	   {   
	       String url = "http://localhost:4434/events";
	       WebResource wrs = Client.create().resource(url);
	       
	       Eventlist ev = wrs.accept("application/xml").get(Eventlist.class);
	        
	     return ev.getEvent();
	    }  
	        
        
        public int eventInfo(String EventName) {
                    
             List<Event> events = eventList();
             BigInteger eventId = null;
             
             for(int i=0; i<events.size();i++){
                 if(events.get(i).getEventname().equals(EventName)){
                     eventId = events.get(i).getEventID();
                 }
             }
	        return eventId.intValue()-1;
        }
        

	public Event getEventContent(int EventID)
	   {   
	       String url = "http://localhost:4434/events/"+EventID;
	       WebResource wrs = Client.create().resource(url);
	       
	       Event evnt = wrs.accept("application/xml").get(Event.class);
	        
	        
	        return evnt;
	   }
        public void createEvent(Map<String, String> eventdata) {
       new NewEvent(eventdata);
   }
}
