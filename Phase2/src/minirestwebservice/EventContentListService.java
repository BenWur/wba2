package minirestwebservice;


import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eventcontentlist.Eventcontent;
import eventcontentlist.Eventcontentlist;
import eventcontentlist.Kommentar;
import eventcontentlist.TickerBeitrag;

@Path( "/events/{eventID}/eventcontent" )
public class EventContentListService
{
	   
	   @GET 
	   @Produces( MediaType.APPLICATION_XML )
	   public Response getEventContent(@PathParam("eventID") int i) throws Exception
	   {
		   DataHandlerEventContent handle = new DataHandlerEventContent();	    
		   return Response.status(200).entity(handle.getEventcontentbyID(i)).build() ;
	   }
	   
	   
	   @POST 
	   @Consumes( MediaType.APPLICATION_XML )
	   public URI postNewEventcontent( Eventcontent eventcontent ,@PathParam("eventID") int i ) throws Exception
	   {
		   	DataHandlerEventContent handle = new DataHandlerEventContent();	    
		    Eventcontentlist events = (Eventcontentlist) handle.getEventcontents();
		    List<Eventcontent> eventliste = events.getEventcontent();
		    
		    
		    BigInteger id = BigInteger.ZERO ;
			for(Eventcontent ev : eventliste ){
				
		    	if(ev.getEventID().compareTo(id)==1){
		    		id = ev.getEventID();
		    	}
		    }
			eventcontent.setEventID(id.add(BigInteger.ONE));
			
		    return handle.writeNewEventcontent(eventcontent);
	   }
	   
	   @POST 
	   @Path( "/beitrag" )
	   @Consumes( MediaType.APPLICATION_XML )
	   public Response postNewBeitrag( @PathParam("eventID") int eventID, TickerBeitrag beitrag ) throws Exception
	   {
		   DataHandlerEventContent handle = new DataHandlerEventContent();
		   System.out.println("4");
	       return Response.created(handle.postNewBeitrag(eventID,beitrag)).build(); 
	   }
	   
	   @POST 
	   @Path( "/beitrag/{tickerBeitragID}")
	   @Consumes( MediaType.APPLICATION_XML )
	   public Response postNewKommentar( 	@PathParam("eventID") int eventID,
			   								@PathParam("tickerBeitragID") int tickerBeitragID, 
			   								Kommentar kommentar ) throws Exception
	   {
		   DataHandlerEventContent handle = new DataHandlerEventContent();	 
	       return Response.created(handle.postNewKommentar(eventID,tickerBeitragID,kommentar)).build() ; 
	   }
	   
	   @DELETE
	   @Path( "/beitrag/{tickerBeitragID}")
	   public Response deleteTickerBeitrag( 	@PathParam("eventID") int eventID,
			   									@PathParam("tickerBeitragID") int tickerBeitragID) throws Exception
	   {
		    DataHandlerEventContent handle = new DataHandlerEventContent();	    
	        return handle.deleteTickerBeitrag(eventID,tickerBeitragID); //Gibt Meldung 204->"ok" zurück
	   }
	   
}