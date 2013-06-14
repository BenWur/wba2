package minirestwebservice;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import eventlist.Event;
import eventlist.Eventlist;

public class DataHandlerEvent {
	private Eventlist events = null;
	private Marshaller marshaller = null;
	private DataHandlerEventContent content = null;

	public DataHandlerEvent() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Eventlist.class);
			Unmarshaller um = jc.createUnmarshaller();
			events = (Eventlist) um.unmarshal(new File("XML/Eventlist.xml"));
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public Eventlist getEvents() {
		return this.events;
	}

	public Event getEventbyID(int id) {
		return this.events.getEvent().get(id - 1);
	}

	public URI writeNewEvent(Event event) {

		List<Event> eventliste = events.getEvent();
		eventliste.add(event);
		this.savePersistent();
		return URI.create("http://localhost:4434/events/" + event.getEventID().toString());

	}

	public URI writeEvent(Event event, int id) {

		List<Event> eventliste = events.getEvent();

		int i = 0;
		for (Event ev : eventliste) {
			if (ev.getEventID().equals(id)) {
				eventliste.set(i, event);
			}
			i++;
		}

		this.savePersistent();

		return URI.create("http://localhost:4434/events/" + id);

	}

	public void delete(int id) {

		List<Event> eventliste = events.getEvent();

		int i = 0;
		for (Event ev : eventliste) {
			if (ev.getEventID().equals(id)) {
				eventliste.remove(i);
			}
			i++;
		}
		
		content.delete(id);

	    this.savePersistent();

	}

	private void savePersistent() {
		try {
			marshaller.marshal(events, new File("XML/Eventlist.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
