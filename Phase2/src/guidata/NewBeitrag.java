/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guidata;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.ObjectFactory;
import eventcontentlist.TickerBeitrag;

import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 *
 * @author Dario
 */
public class NewBeitrag {
    
    public NewBeitrag(int eventID, String beitrag) {
    	TickerBeitrag kommi = new ObjectFactory().createTickerBeitrag();
    	kommi.setText(beitrag);
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	String uhrzeit = sdf.format(new Date());
    	XMLGregorianCalendar aktuell=null;
		try {
			aktuell = DatatypeFactory.newInstance().newXMLGregorianCalendar( uhrzeit );
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	kommi.setZeit(aktuell);
    	
    	String url = "http://localhost:4434/events/"+eventID+"/eventcontent/beitrag";
        WebResource wrs = Client.create().resource(url);
        
        
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(kommi).post(ClientResponse.class);
        System.out.println(cr.getStatus());
	}
    
}
