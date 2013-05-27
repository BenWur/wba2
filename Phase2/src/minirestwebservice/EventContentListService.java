package minirestwebservice;


import java.io.FileInputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import eventcontentlist.Eventcontent;
import eventcontentlist.Eventcontentlist;

@Path( "/events/{eventID}/eventcontent" )
public class EventContentListService
{
	   
	   @GET 
	   @Produces( MediaType.APPLICATION_XML )
	   public Eventcontent getEventContent(@PathParam("eventID") int i) throws Exception
	   {
		    
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
			//unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    Eventcontentlist eventcontent = (Eventcontentlist) um.unmarshal(new FileInputStream("XML/Eventcontentlist.xml"));
		    
	      return eventcontent.getEventcontent().get(i-1); 
	   }
}