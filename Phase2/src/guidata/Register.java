package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.util.Map;
import userlist.LandType;
import userlist.Userlist;

/**
 * 
 * Dient zur Verwaltung der LoginDaten und zum registrieren
 * 
 * @author Dario & Ben
 *
 */

public class Register
{
   
    public String loggedInUser = null;
    
    public boolean login(String loginuser)
    {
       String url = "http://localhost:4434/users";
       WebResource wrs = Client.create().resource(url);
       Userlist cr = wrs.accept("application/xml").get(Userlist.class);
       loggedInUser = loginuser;
       for (int i = 0; i < cr.getUser().size(); i++){
              if (cr.getUser().get(i).getUsername().equals(loginuser)) {
                  return true;
              }
       }
       return false;
    }

    
   public String[] countrylist() 
   {
	    String[] array = new String[LandType.values().length];
	    for (int i = 0; i < LandType.values().length; i++)
	    {
	    	array[i] = LandType.values()[i].value();
	    }  
	    return array;
   }
   
   
   public void createUser(Map<String, String> userdata) {
       new NewUser(userdata);
   }
}