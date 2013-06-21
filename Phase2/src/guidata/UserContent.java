package guidata;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import userlist.LandType;
import userlist.ObjectFactory;
import userlist.User;
import userlist.Userlist;

/**
 *
 * @author Dario & Ben
 */

public class UserContent {
    
    public User userInfo(String username)
	   {   
	       String url = "http://localhost:4434/users/";
	       WebResource wrs = Client.create().resource(url);
	       
	       Userlist ev = wrs.accept("application/xml").get(Userlist.class);
	       
           for (int i = 0; i < ev.getUser().size(); i++)
           {
        	   	if (ev.getUser().get(i).getUsername().equals(username)){
        	   		return ev.getUser().get(i);
        	   	}
           }
           return null;
        }
    
   public void userChange(User user, String land, String stadt){
	 //neuen User anlegen
       user.setLand(LandType.fromValue(land));
       user.setStadt(stadt);
       
       String url = "http://localhost:4434/users/"+user.getUserID();			//url der Ressource
       WebResource wrs = Client.create().resource(url);	

       //erstellt user und gibt einen Response zurück. Server übernimmt die genaue Verwaltung
       ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(user).put(ClientResponse.class);

       System.out.println(cr.getStatus());
   }
   
}
