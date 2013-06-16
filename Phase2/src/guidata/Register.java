package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.util.Map;
import userlist.LandType;
import userlist.Userlist;

public class Register
{
   
    public String loggedInUser = null;
    
   public boolean login(String loginuser)
   {
       
       if (loginuser.length() < 3 || loginuser.length() > 15)
           return false;
       
       String url = "http://localhost:4434/users?name="+loginuser;
       WebResource wrs = Client.create().resource(url);
       loggedInUser = loginuser;
       Userlist cr = wrs.accept("application/xml").get(Userlist.class);
        for (int i = 0; i < cr.getUser().size(); i++){
              if (cr.getUser().get(i).getUsername().equals(loginuser)) {
                  return true;
              }
        }
       return false;
   }
   
    public boolean registered(String loginuser)
   {
       if (loginuser.length() < 3 || loginuser.length() > 15)
           return true;
       
      String url = "http://localhost:4434/users?name="+loginuser;
       WebResource wrs = Client.create().resource(url);
       
       Userlist cr = wrs.accept("application/xml").get(Userlist.class);
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