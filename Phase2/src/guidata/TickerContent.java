package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import eventcontentlist.Eventcontent;

/**
 * Verwaltung vom Ticker Content
 *
 * @author Dario & Ben
 */
public class TickerContent {

    /**
     * Gibt ein Content zurück
     *
     * @param eventID
     */
    public Eventcontent contentList(int eventID) {
        String url = "http://localhost:4434/events/" + eventID + "/eventcontent";
        WebResource wrs = Client.create().resource(url);
        Eventcontent ev = wrs.accept("application/xml").get(Eventcontent.class);
        return ev;
    }

    /**
     * Erstellt ein Beitrag
     *
     * @param id, beitrag
     */
    public void createBeitrag(int id, String beitrag) {
        new NewBeitrag(id, beitrag);
    }

    /**
     * Erstellt ein Kommentar
     *
     * @param eventID, tickerID, username, beitrag
     */
    public void createKommentar(int eventID, int tickerID, String username, String beitrag) {
        new NewKommentar(eventID, tickerID, username, beitrag);
    }
}
