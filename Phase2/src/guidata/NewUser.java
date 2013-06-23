package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import userlist.LandType;
import userlist.ObjectFactory;
import userlist.User;

/**
 * Erstellt neuen User
 * @author Dario & Ben
 */

public class NewUser {

	/**
	 * Konstruktor eines neuen Nutzers
	 * @param eventID, beitrag
	 */
    public NewUser(Map<String, String> userdata) {

        //neuen User anlegen
        User user = new ObjectFactory().createUser();
        
        //Userdaten gemäß der xsd füllen
        XMLGregorianCalendar xbday = null;
        try {
            xbday = DatatypeFactory.newInstance().newXMLGregorianCalendar(userdata.get("bday"));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.setUsername(userdata.get("name"));
        user.setVorname(userdata.get("fname"));
        user.setName(userdata.get("lname"));
        user.setStadt(userdata.get("city"));
        user.setGender(userdata.get("gender"));
        user.setGeburtsdatum(xbday);
        user.setLand(LandType.fromValue(userdata.get("country")));

        
        String url = "http://localhost:4434/users";			//url der Ressource
        WebResource wrs = Client.create().resource(url);	

        //erstellt user und gibt einen Response zurück. Server übernimmt die genaue Verwaltung
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(user).post(ClientResponse.class);

        System.out.println(cr.getStatus());
    }
}
