package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
}
