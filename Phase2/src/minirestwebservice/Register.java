package minirestwebservice;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Register
{
   private  Marshaller marshaller = null;
   private  userlist.Userlist userlist;
    
   public  Register()
   {
      JAXBContext context;
       try {
           context = JAXBContext.newInstance(userlist.Userlist.class);
           Unmarshaller um = context.createUnmarshaller();
           userlist = (userlist.Userlist) um.unmarshal(new FileReader("C:\\Users\\Dario\\Desktop\\Phase2\\XML\\Userlist.xml"));
       } catch (Exception e) {
       }
   }
   
   public String login() 
   {
   userlist.User user = userlist.getUser().get(1);
   return (user.getUsername());
   }
   
   public boolean registered(String loginuser)
   {
   
    for ( userlist.User user :userlist.getUser()) {
        if (user.getUsername().equals(loginuser))
            return true;
    }
       return false;
   }
   
}