package guidata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import eventcontentlist.Kommentar;
import eventcontentlist.ObjectFactory;

import javax.ws.rs.core.MediaType;

/**
 * Erstellt neuen Kommentar
 *
 * @author Dario & Ben
 */
public class NewKommentar {

    /**
     * Konstruktor eines neuen Kommentars
     *
     * @param eventID, beitrag
     */
    public NewKommentar(int eventID, int beitragID, String username, String kommentar) {
        //neuen Kommentar anlegen
        Kommentar kommi = new ObjectFactory().createKommentar();
        //Kommentar gemäß der xsd füllen
        kommi.setKommentarText(kommentar);
        kommi.setKommentarUser(username);

        String url = "http://localhost:4434/events/" + eventID + "/eventcontent/beitrag/" + beitragID;
        WebResource wrs = Client.create().resource(url);//erstellt neuen Kommentar unter der URI

        //erstellt Kommentar und gibt einen Response zurück. Server übernimmt die genaue Verwaltung
        ClientResponse cr = wrs.accept("text/html").type(MediaType.APPLICATION_XML).entity(kommi).post(ClientResponse.class);
        System.out.println(cr.getStatus());
    }
}
