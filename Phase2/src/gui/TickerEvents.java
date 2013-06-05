/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jersey.api.client.Client;
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
 
   }
