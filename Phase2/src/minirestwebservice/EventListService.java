package minirestwebservice;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import eventlist.Event;
import eventlist.Eventlist;

/**
 * Service für Events
 *
 * @author Ben
 */
@Path("/events")		//Path für die Entity
public class EventListService {

    //GET für alle Events
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllEvents(@QueryParam("name") String name) throws Exception {
        DataHandlerEvent handle = new DataHandlerEvent();
        Eventlist events = (Eventlist) handle.getEvents();
        List<Event> eventliste = events.getEvent();

        if (name != null) {
            for (Iterator<Event> iter = eventliste.iterator(); iter.hasNext();) {
                Event ev = iter.next();
                if (!ev.getEventname().toLowerCase().contains(name.toLowerCase())) {
                    iter.remove();
                }
            }
        }

        return Response.status(200).entity(events).build();
    }

    //GET für ein Event
    @GET
    @Path("/{eventID}")		//Path für die Entity mit PathParam
    @Produces(MediaType.APPLICATION_XML)
    public Response getOneEvent(@PathParam("eventID") int i) throws Exception {
        DataHandlerEvent handle = new DataHandlerEvent();
        return Response.status(200).entity(handle.getEventbyID(i)).build(); //Gibt Meldung 200->"ok" zurück
    }

    //POST für ein neues Event
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response postNewEvent(Event event) throws Exception {

        DataHandlerEvent handle = new DataHandlerEvent();
        Eventlist events = (Eventlist) handle.getEvents();
        List<Event> eventliste = events.getEvent();


        BigInteger id = BigInteger.ZERO;	//Bestimmen der neuen ID
        for (Event ev : eventliste) {
            if (ev.getEventID().compareTo(id) == 1) {
                id = ev.getEventID();
            }
        }
        event.setEventID(id.add(BigInteger.ONE));

        return Response.created(handle.writeNewEvent(event)).build();
    }

    //PUT für ein Event
    @PUT
    @Path("/{eventID}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response changeEvent(@PathParam("eventID") int id, Event event) throws Exception {
        DataHandlerEvent handle = new DataHandlerEvent();
        return Response.created(handle.writeEvent(event, id)).build();
    }

    //DELETE für ein Event
    @DELETE
    @Path("/{eventID}")
    public Response deleteEvent(@PathParam("eventID") int id) throws Exception {
        DataHandlerEvent handle = new DataHandlerEvent();
        handle.delete(id);

        return Response.noContent().build(); //Gibt Meldung 204->"ok" zurück
    }
}