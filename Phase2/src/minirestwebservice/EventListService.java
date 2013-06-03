package minirestwebservice;


import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import eventcontentlist.Eventcontent;
import eventcontentlist.Eventcontentlist;
import eventlist.Event;
import eventlist.Eventlist;

@Path( "/events" )
public class EventListService
{

	
   @GET 
   @Produces( MediaType.APPLICATION_XML )
   public Eventlist getAllEvents( @QueryParam("name") String name ) throws Exception
   {
	   JAXBContext jc = JAXBContext.newInstance(Eventlist.class);
		//unmarshaller zum lesen 
	    Unmarshaller um = jc.createUnmarshaller();
	    Eventlist events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
	    List<Event> eventliste = events.getEvent();
	    
	    if(name!=null){
	    	for (Iterator<Event> iter = eventliste.iterator(); iter.hasNext(); ) {
		    	Event ev = iter.next();
		    	if(!ev.getEventname().toLowerCase().contains(name.toLowerCase())){
			    	iter.remove();
		    	}
		    }
	    }
	    
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
	    Eventlist event = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
	    
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
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    Eventlist events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
	    
	    List<Event> eventliste = events.getEvent();
	    
	    BigInteger ID = BigInteger.ZERO ;
		for(Event ev : eventliste ){
			
	    	if(ev.getEventID().compareTo(ID)==1){
	    		ID = ev.getEventID();
	    	}
	    }
		event.setEventID(ID.add(BigInteger.ONE));
	    
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
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    Eventlist events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
	    
	    
	    List<Event> eventliste = events.getEvent();
	    
	    int i=0;
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
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	    Eventlist events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
	    Eventcontentlist eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
	    
	    List<Event> eventliste = events.getEvent();
	    
	    for (Iterator<Event> it = eventliste.iterator(); it.hasNext(); ) {
	    	Event ev = it.next();
	    	if(ev.getEventID().equals(id)){
		    	it.remove();
	    	}
	    }
	    
	    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
	    
	    for (Iterator<Eventcontent> iter = eventcontentliste.iterator(); iter.hasNext(); ) {
	    	Eventcontent evc = iter.next();
	    	if(evc.getEventID().equals(id)){
		    	iter.remove();
	    	}
	    }

	    marshaller.marshal(events, new File("XML/Eventlist.xml"));
	    marshaller.marshal(events, new File("XML/Eventcontentlist.xml"));
	    
      return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
   }
   
}