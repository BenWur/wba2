package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import userlist.Userlist;

@Path( "/users" )
public class UserlistService
{

	private static Userlist datei = null;
	
   @GET @Produces( "text/html" )
   public Userlist getAllUsers() throws Exception
   {
	   JAXBContext jc = JAXBContext.newInstance(Userlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    datei = (Userlist) um.unmarshal(new FileInputStream("aufgabe3d.xml"));
	    
	    
      return datei; 
   }
}