package minirestwebservice;


import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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
   
   @POST 
   @Consumes( MediaType.APPLICATION_XML )
   public Response postNewEvent( Event event ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Eventlist events = (Eventlist) um.unmarshal(new FileInputStream("XML/Eventlist.xml"));
	    
	    List<Event> eventliste = events.getEvent();
	    
	    event.setEventID( new BigInteger(String.valueOf(eventliste.size() - 1)) );
	    
	 	eventliste.add( event );
	    
	    marshaller.marshal(events, new File("XML/Eventlist.xml"));
	    
	    
      URI location = URI.create( "http://localhost:4434/events/" + event.getEventID().toString() );
      return Response.created(location ).build(); 
   }
   
   @PUT
   @Path( "/{eventID}" )
   @Consumes( MediaType.APPLICATION_XML )
   public Response changeEvent( @PathParam("eventID") int id, Event event  ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Eventlist events = (Eventlist) um.unmarshal(new FileInputStream("XML/Eventlist.xml"));
	    
	    
	    List<Event> eventliste = events.getEvent();
	    
	    int i=0;;
	    for(Event ev : eventliste ){
	    	if(ev.getEventID().equals(id)){
	    		eventliste.set(i, event);
	    	}
	    	i++;
	    }

	    marshaller.marshal(events, new File("XML/Eventlist.xml"));
	    
	    
      URI location = URI.create( "http://localhost:4434/events/" + event.getEventID().toString() );
      return Response.created(location ).build();  
   }
   
   @DELETE
   @Path( "/{eventID}" )
   public Response deleteEvent( @PathParam("eventID") int id  ) throws Exception
   {
	   
	    JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
	    //unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    //marshaller zum schreiben
	    Marshaller marshaller =jc.createMarshaller();
	    
	    Eventlist events = (Eventlist) um.unmarshal(new FileInputStream("XML/Eventlist.xml"));
	    
	    
	    List<Event> eventliste = events.getEvent();
	    
	    int i=0;
	    for(Event ev : eventliste ){
	    	if(ev.getEventID().equals(id)){
	    		eventliste.remove(i);
	    	}
	    	i++;
	    }

	    marshaller.marshal(events, new File("XML/Eventlist.xml"));
	    
	    
      return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
   }
   
}