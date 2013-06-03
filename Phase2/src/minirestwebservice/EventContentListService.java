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
import eventcontentlist.Kommentar;
import eventcontentlist.TickerBeitrag;

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
		    Eventcontentlist eventcontent = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
		    
	      return eventcontent.getEventcontent().get(i-1); 
	   }
	   
	   
	   @POST 
	   @Consumes( MediaType.APPLICATION_XML )
	   public Response postNewEventcontent( Eventcontent eventcontent ,@PathParam("eventID") int i ) throws Exception
	   {
		   
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
		    //unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    //marshaller zum schreiben
		    Marshaller marshaller =jc.createMarshaller();
		    
		    Eventcontentlist eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
		    
		    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
		    
		    eventcontent.setEventID( new BigInteger(String.valueOf(eventcontentliste.size() - 1)) );
		    
		 	eventcontentliste.add( eventcontent );
		    
		    marshaller.marshal(eventcontents, new File("XML/Eventcontentlist.xml"));
		    
		    
	      URI location = URI.create( "http://localhost:4434/events/" + i +"/eventcontents" );
	      return Response.created(location ).build(); 
	   }
	   
	   @POST 
	   @Path( "/beitrag" )
	   @Consumes( MediaType.APPLICATION_XML )
	   public Response postNewBeitrag( @PathParam("eventID") int eventID, TickerBeitrag beitrag ) throws Exception
	   {
		   
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
		    //unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    //marshaller zum schreiben
		    Marshaller marshaller =jc.createMarshaller();
		    
		    Eventcontentlist eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
		   
		    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
		    BigInteger ID = BigInteger.ZERO ;
		    
		    for(Eventcontent evc : eventcontentliste ){
		    	if(evc.getEventID().equals(eventID)){
		    		evc.getTickerBeitrag().add(beitrag);
		    		
		    		List<TickerBeitrag> beitragliste = evc.getTickerBeitrag();
		    		for(TickerBeitrag tickerc : beitragliste ){
		    			
				    	if(tickerc.getTickerBeitragID().compareTo(ID)==1){
				    		ID = tickerc.getTickerBeitragID();
				    	}
				    }
		    		evc.setEventID(ID.add(BigInteger.ONE));
		    	}
		    }
		    
		    marshaller.marshal(eventcontents, new File("XML/Eventcontentlist.xml"));
		    
	      return Response.noContent().build(); 
	   }
	   
	   @POST 
	   @Path( "/beitrag/{tickerBeitragID}")
	   @Consumes( MediaType.APPLICATION_XML )
	   public Response postNewKommentar( 	@PathParam("eventID") int eventID,
			   								@PathParam("tickerBeitragID") int tickerBeitragID, 
			   								Kommentar kommentar ) throws Exception
	   {
		   
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
		    //unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    //marshaller zum schreiben
		    Marshaller marshaller =jc.createMarshaller();
		    
		    Eventcontentlist eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
		   
		    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
		    
		    
		    for(Eventcontent evc : eventcontentliste ){
		    	if(evc.getEventID().equals(eventID)){
		    		List<TickerBeitrag> beitragliste = evc.getTickerBeitrag();
		    		for(TickerBeitrag tickerc : beitragliste ){
				    	if(tickerc.getTickerBeitragID().equals(tickerBeitragID)){
				    		tickerc.getKommentar().add(kommentar);
				    	}
				    }
		    	}
		    }
		    
		    marshaller.marshal(eventcontents, new File("XML/Eventcontentlist.xml"));
		    
	      return Response.noContent().build(); 
	   }
	   
	   @DELETE
	   @Path( "/beitrag/{tickerBeitragID}")
	   public Response deleteTickerBeitrag( 	@PathParam("eventID") int eventID,
			   									@PathParam("tickerBeitragID") int tickerBeitragID) throws Exception{
		   
		    JAXBContext jc = JAXBContext.newInstance(Eventcontentlist.class);
		    //unmarshaller zum lesen 
		    Unmarshaller um = jc.createUnmarshaller();
		    //marshaller zum schreiben
		    Marshaller marshaller =jc.createMarshaller();
		    
		    Eventcontentlist eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
		    
		    
		    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
		    
		    for (Iterator<Eventcontent> it = eventcontentliste.iterator(); it.hasNext(); ) {
		    	Eventcontent evc = it.next();
		    	if(evc.getEventID().equals(eventID)){
		    		List<TickerBeitrag> beitraege = evc.getTickerBeitrag();
		    		for (Iterator<TickerBeitrag> iter = beitraege.iterator(); iter.hasNext(); ) {
				    	TickerBeitrag bei = iter.next();
				    	if(bei.getTickerBeitragID().equals(tickerBeitragID)){
					    	iter.remove();
				    	}
				    }
		    	}
		    }

		    marshaller.marshal(eventcontents, new File("XML/Eventlist.xml"));
		    
		    
	      return Response.noContent().build() ; //Gibt Meldung 204->"ok" zurück
	   }
	   
}