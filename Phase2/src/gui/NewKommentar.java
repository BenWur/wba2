/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import eventcontentlist.Kommentar;
import eventcontentlist.ObjectFactory;

import javax.ws.rs.core.MediaType;
/**
 *
 * @author Dario
 */
public class NewKommentar {
    
    public NewKommentar(int eventID,int beitragID,String username, String kommentar) {
    	Kommentar kommi = new ObjectFactory().createKommentar();
    	kommi.setKommentarText(kommentar);
    	kommi.setKommentarUser(username);
    	
    	String url = "http://localhost:4434/events/"+eventID+"/eventcontent/beitrag/"+beitragID;
    	System.out.println(url);
        WebResource wrs = Client.create().resource(url);
        
        
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(kommi).post(ClientResponse.class);
        System.out.println(cr.getStatus());
	}
    
}
