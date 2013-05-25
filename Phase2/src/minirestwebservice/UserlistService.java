package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import userlist.ObjectFactory;
import userlist.Userlist;

@Path( "/users" )
public class UserlistService
{
	
   @GET 
   @Produces( "application/xml" )
   public Userlist getAllUsers() throws Exception
   {
	   ObjectFactory ob = new ObjectFactory();
	   Userlist users=ob.createUserlist();
	   JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    users = (Userlist) um.unmarshal(new FileInputStream("/Users/Ben/git/wba2/Phase2/XML/userlist.xml"));
	    
	    
      return users; 
   }
   
   @GET 
   @Path( "/{userID}" )
   @Produces( "application/xml" )
   public Userlist getOneUser(@PathParam("userID") int i) throws Exception
   {
	    ObjectFactory ob = new ObjectFactory();
	    Userlist users=ob.createUserlist();
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    users = (Userlist) um.unmarshal(new FileInputStream("/Users/Ben/git/wba2/Phase2/XML/userlist.xml"));
	    
	    Userlist user = ob.createUserlist();
	    user.getUser().add(users.getUser().get(i-1));
	    
      return user; 
   }
}