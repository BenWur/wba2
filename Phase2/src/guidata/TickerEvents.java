package guidata;

import java.math.BigInteger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import eventlist.Event;
import eventlist.Eventlist;
import java.util.List;
import java.util.Map;

/**
 * Verwaltung vom Ticker Events
 *
 * @author Dario & Ben
 */
public class TickerEvents {

    /**
     * Gibt Liste der Events zurück
     */
    public List<Event> eventList() {
        String url = "http://localhost:4434/events";
        WebResource wrs = Client.create().resource(url);
        Eventlist ev = wrs.accept("application/xml").get(Eventlist.class);

        return ev.getEvent();
    }

    /**
     * Gibt die ID eines Events zurück
     *
     * @param eventName
     */
    public int eventInfo(String eventName) {

        List<Event> events = eventList();
        BigInteger eventId = null;
        //Geht alle Events durch
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventname().equals(eventName)) {
                eventId = events.get(i).getEventID();
            }
        }
        return eventId.intValue() - 1;
    }

    /**
     * Gibt Content eines Events zurück
     *
     * @param eventID
     */
    public Event getEventContent(int eventID) {
        String url = "http://localhost:4434/events/" + eventID;
        WebResource wrs = Client.create().resource(url);
        Event evnt = wrs.accept("application/xml").get(Event.class);
        return evnt;
    }

    /**
     * Filter von Events durch Query Param
     *
     * @param eventName
     */
    public List<Event> searchEvent(String eventName) {
        String url = "http://localhost:4434/events?name=" + eventName;
        WebResource wrs = Client.create().resource(url);
        Eventlist evnt = wrs.accept("application/xml").get(Eventlist.class);
        return evnt.getEvent();
    }

    /**
     * Erstellt neues Event
     *
     * @param eventdata
     */
    public void createEvent(Map<String, String> eventdata) {
        new NewEvent(eventdata);
    }

    /**
     * Erstellt neuen Beitrag
     *
     * @param eventdata
     */
    public void createBeitrag(int ID, String beitrag) {
        new NewBeitrag(ID, beitrag);
    }

    /**
     * Erstellt neuen Kommentar
     *
     * @param eventID, tickerID, username, beitrag
     */
    public void createKommentar(int eventID, int tickerID, String username, String beitrag) {
        new NewKommentar(eventID, tickerID, username, beitrag);
    }
}
