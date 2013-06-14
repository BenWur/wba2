/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.Eventcontent;
import eventcontentlist.TickerBeitrag;
import eventlist.Event;
import eventlist.Eventlist;
import java.util.List;

/**
 *
 * @author Dario
 */
public class TickerContent {
    public Eventcontent contentList(int EventID)
	   {   
	       String url = "http://localhost:4434/events/"+EventID+"/eventcontent";
	       WebResource wrs = Client.create().resource(url);
	       
	       Eventcontent ev = wrs.accept("application/xml").get(Eventcontent.class);
	        
	     return ev;
	    }
}
