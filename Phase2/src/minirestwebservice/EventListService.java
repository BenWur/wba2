package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import eventlist.Eventlist;

@Path( "/eventlist" )
public class EventListService
{

	private static Eventlist datei = null;
	
   @GET @Produces( "text/html" )
   public Eventlist getAllEvents() throws Exception
   {
	   JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    datei = (Eventlist) um.unmarshal(new FileInputStream("Eventlist.xml"));
	    
	    
      return datei; 
   }
}