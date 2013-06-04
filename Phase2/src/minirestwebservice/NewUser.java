package minirestwebservice;

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


public class NewUser {

    public NewUser(Map<String, String> userdata) {

        //neuen User anlegen
        User user = new ObjectFactory().createUser();
        
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

        String url = "http://localhost:4434/users";
        WebResource wrs = Client.create().resource(url);


       ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(user).post(ClientResponse.class);

       System.out.println(cr.getStatus());
    }
}
