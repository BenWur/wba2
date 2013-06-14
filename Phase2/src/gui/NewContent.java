/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.Eventcontent;
import eventcontentlist.Eventcontentlist;
import eventcontentlist.Kommentar;
import eventcontentlist.TickerBeitrag;
import eventcontentlist.ObjectFactory;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Dario
 */
public class NewContent {
    public NewContent(Map<String, String> contentdata) 
        {
        int contentindex = Integer.parseInt(contentdata.get("contentindex"));
        int eventindex = Integer.parseInt(contentdata.get("eventnr"));
            
        
        /*
        XMLGregorianCalendar xbday = null;
        try {
            xbday = DatatypeFactory.newInstance().newXMLGregorianCalendar(contentdata.get("bday"));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        } */
        Eventcontent event = new ObjectFactory().createEventcontent();
        
        event.getTickerBeitrag().get(contentindex).getKommentar().get(eventindex).setKommentarText(contentdata.get("komment"));
        
        
       
        
        //content.setKommentarText(contentdata.get("komment"));
        /*user.setVorname(userdata.get("fname"));
        user.setName(userdata.get("lname"));
        user.setStadt(userdata.get("city"));
        user.setGender(userdata.get("gender"));
        user.setGeburtsdatum(xbday);
        user.setLand(LandType.fromValue(userdata.get("country")));
        */
        String url = "http://localhost:4434/events/"+eventindex+"/eventcontent";
        WebResource wrs = Client.create().resource(url);


       ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(event).post(ClientResponse.class);

       System.out.println(cr.getStatus());
    }
    
}
