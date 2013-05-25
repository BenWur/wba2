package minirestwebservice;


import java.io.FileInputStream;
import java.math.BigDecimal;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import eventcontentlist.ObjectFactory;
import eventcontentlist.Eventcontentlist;

@Path( "/events" )
public class EventContentListService
{
	   
	   @GET 
	   @Path( "/{eventID}" )
	   @Produces( "application/xml" )
	   public Eventcontentlist getEventContent(@PathParam("eventID") int i) throws Exception
	   {
		    ObjectFactory ob = new ObjectFactory();
		    Eventcontentlist eventcontent = ob.createEventcontentlist();
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
			//unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    eventcontent = (Eventcontentlist) um.unmarshal(new FileInputStream("/Users/Ben/git/wba2/Phase2/XML/eventcontentlist.xml"));
		    
		    Eventcontentlist content = ob.createEventcontentlist();
		    content.getEventcontent().add(content.getEventcontent().get(i-1));//WIESO KLAPPT DAS NICHT?!?!
		    
	      return content; 
	   }
}