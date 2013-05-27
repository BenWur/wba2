package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import eventlist.Event;
import eventlist.Eventlist;

@Path( "/events" )
public class EventListService
{

	
   @GET 
   @Produces( MediaType.APPLICATION_XML )
   public Eventlist getAllEvents() throws Exception
   {
	   JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    Eventlist events = (Eventlist) um.unmarshal(new FileInputStream("XML/Eventlist.xml"));
	    
	    
      return events; 
   }
   
   @GET 
   @Path( "/{eventID}" )
   @Produces( MediaType.APPLICATION_XML )
   public Event getOneEvent(@PathParam("eventID") int i) throws Exception
   {
	    
	    JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    Eventlist event = (Eventlist) um.unmarshal(new FileInputStream("XML/Eventlist.xml"));
	    
      return event.getEvent().get(i-1); 
   }
}