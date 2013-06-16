package minirestwebservice;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import eventcontentlist.Eventcontent;
import eventlist.Event;
import eventlist.Eventlist;

/**
 * DataHandler zum verwalten der Restabfragen des Events
 * @author Ben & Dario
 *
 */

public class DataHandlerEvent {
	
	private Eventlist events = null;
	private Marshaller marshaller = null;
	private DataHandlerEventContent content = null;

	public DataHandlerEvent() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Eventlist.class); 	//Kontext
			Unmarshaller um = jc.createUnmarshaller();		// Unmarshaller erstellen
			events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml")); 	//events beinhaltet alles aus Eventlist.xml
			marshaller = jc.createMarshaller();				// Marshaller erstellen
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	//damit text formatiert gespeichert wird

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	//gibt alle Events zurück
	public Eventlist getEvents() {
		return this.events;
	}
	//gibt ein bestimmtes Event zurück
	public Event getEventbyID(int id) {
		return this.events.getEvent().get(id - 1);
	}
	//erstellt neues Event
	public URI writeNewEvent(Event event) {

		List<Event> eventliste = events.getEvent();
		eventliste.add(event);	//fügt neues Event hinzu
		
		this.savePersistent();	//speichert aktuelle Daten
		return URI.create("http://localhost:4434/events/" + event.getEventID().toString());	//erstellt URI

	}
	//ändert ein Event
	public URI writeEvent(Event event, int id) {

		List<Event> eventliste = events.getEvent();

		int i = 0; //Zählvariable
		//bestimmt das zugehörige Event 
		for (Event ev : eventliste) {
			if (ev.getEventID().intValue()==id) {
				eventliste.set(i, event);	//ändert das ausgewählte Event
			}
			i++;
		}

		this.savePersistent();

		return URI.create("http://localhost:4434/events/" + id);

	}
	//löscht Event mit angegebener ID
	public void delete(int id) {

		List<Event> eventliste = events.getEvent();

		int i = 0;
		//fragt Event ab
		for (Event ev : eventliste) {
			if (ev.getEventID().intValue()==id) {
				eventliste.remove(i);	//löscht das Event
			}
			i++;
		}
		
		content.delete(id);

	    this.savePersistent();

	}
	//speichert in Eventlist ab
	private void savePersistent() {
		try {
			marshaller.marshal(events, new File("XML/Eventlist.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
