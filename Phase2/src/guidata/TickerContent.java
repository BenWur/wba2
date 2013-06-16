/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
    public void createBeitrag(int ID, String beitrag) {
    	new NewBeitrag(ID, beitrag);
   }
        
    public void createKommentar(int eventID, int tickerID, String username, String beitrag) {
        new NewKommentar(eventID, tickerID, username, beitrag);
    }
}
