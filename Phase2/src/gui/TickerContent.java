/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.Eventcontent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import eventcontentlist.ObjectFactory;
import eventcontentlist.Eventcontent;

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
