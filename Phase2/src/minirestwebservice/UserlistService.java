package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import userlist.ObjectFactory;
import userlist.User;
import userlist.Userlist;

@Path( "/users" )
public class UserlistService
{
	
   @GET 
   @Produces( MediaType.APPLICATION_XML )
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
   @Produces( MediaType.APPLICATION_XML )
   public User getOneUser(@PathParam("userID") int i) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    
	    Userlist users = (Userlist) um.unmarshal(new FileInputStream("XML/userlist.xml"));
	    
	    
      return users.getUser().get(i-1); 
   }
}