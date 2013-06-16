package minirestwebservice;

import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import eventcontentlist.Eventcontent;
import eventcontentlist.Eventcontentlist;
import eventcontentlist.Kommentar;
import eventcontentlist.TickerBeitrag;

/**
 * DataHandler zum verwalten der Restabfragen des Eventcontents
 * @author Ben & Dario
 *
 */

public class DataHandlerEventContent {
	private Eventcontentlist eventcontents = null;
	private Marshaller marshaller = null;

	public DataHandlerEventContent() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Eventcontentlist.class);	//Kontext
			Unmarshaller um = jc.createUnmarshaller();		// Unmarshaller erstellen
			eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml")); 	//events beinhaltet alles aus Eventlist.xml
			marshaller = jc.createMarshaller();				// Marshaller erstellen
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);	//damit text formatiert gespeichert wird

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	//gibt jeden Eventcontent zurück	
	public Eventcontentlist getEventcontents() {
		return this.eventcontents;
	}
	//gibt ein bestimmtes Eventcontent zurück
	public Eventcontent getEventcontentbyID(int id) {
		return this.eventcontents.getEventcontent().get(id - 1);
	}
	//erstellt neuen Eventcontent
	public URI writeNewEventcontent(Eventcontent event) {

		List<Eventcontent> Eventcontentliste = eventcontents.getEventcontent();
		Eventcontentliste.add(event);	//fügt neues Event hinzu

		this.savePersistent();	//speichert aktuelle Daten
		
		return URI.create("http://localhost:4434/events/"+event.getEventID().toString()+"/eventcontent");	//erstellt URI

	}
	//erstellt neuen Kommentar
	public URI postNewKommentar(int eventID, int tickerBeitragID, Kommentar kommentar){
	   
	    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
	    int i = 0;	//Zählvariable für den Content
	    int n=0;	//Zählvariable für die Beiträge
	    for(Eventcontent evc : eventcontentliste ){
	    	if(evc.getEventID().intValue()==eventID){	//falls richtige Content
	    		List<TickerBeitrag> beitragliste = evc.getTickerBeitrag();
	    		for(TickerBeitrag tickerc : beitragliste ){
			    	if(tickerc.getTickerBeitragID().intValue()==tickerBeitragID){	//falls richtige Beitrag
			    		eventcontentliste.get(i).getTickerBeitrag().get(n).getKommentar().add(kommentar);	//fügt hinzu
			    	}
			    	n++;
			    }
	    	}
	    	i++;
	    }
	    this.savePersistent();	//speichert
	    
	    return URI.create("http://localhost:4434/events/"+eventID+"/eventcontent/beitrag/"+tickerBeitragID); 
	}
	
	//neuen Beitrag erstellen
	public URI postNewBeitrag(int eventID, TickerBeitrag beitrag){
		   
	    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
	    BigInteger ID = BigInteger.ZERO ;		//für die beitrag ID
	    
	    int i = 0;
	    for(Eventcontent evc : eventcontentliste ){
	    	if(evc.getEventID().intValue()==eventID){
	    		List<TickerBeitrag> beitragliste = evc.getTickerBeitrag();
	    		for(TickerBeitrag tickerc : beitragliste ){
	    			
			    	if(tickerc.getTickerBeitragID().compareTo(ID)==1){
			    		ID = tickerc.getTickerBeitragID();
			    	}
			    }
	    		beitrag.setTickerBeitragID(ID.add(BigInteger.ONE));		//fügt richtige ID hinzu
	    		eventcontentliste.get(i).getTickerBeitrag().add(beitrag);	//fügt beitrag hinzu
	    	}
	    	i++;
	    }
	    
	    this.savePersistent();
	    
	    return URI.create("http://localhost:4434/events/"+eventID+"/eventcontent/beitrag/"+ID.add(BigInteger.ONE)); 
	}
	//löscht Tickerbeitrag
	public Response deleteTickerBeitrag(int eventID, int tickerBeitragID){
		   
		List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
		//Abfrage zum Beitrag löschen
	    for (Iterator<Eventcontent> it = eventcontentliste.iterator(); it.hasNext(); ) {
	    	Eventcontent evc = it.next();
	    	if(evc.getEventID().intValue()==eventID){
	    		List<TickerBeitrag> beitraege = evc.getTickerBeitrag();
	    		for (Iterator<TickerBeitrag> iter = beitraege.iterator(); iter.hasNext(); ) {
			    	TickerBeitrag bei = iter.next();
			    	if(bei.getTickerBeitragID().equals(tickerBeitragID)){
				    	iter.remove();
			    	}
			    }
	    	}
	    }
	    
	    this.savePersistent();
	    
	    return Response.noContent().build(); 
	}
	//löscht Eventcontent
	public void delete(int id) {

		List<Eventcontent> Eventcontentliste = eventcontents.getEventcontent();

		int i = 0;
		for (Eventcontent ev : Eventcontentliste) {
			if (ev.getEventID().intValue()==id) {
				Eventcontentliste.remove(i);
			}
			i++;
		}

	    this.savePersistent();

	}

	private void savePersistent() {
		try {
			System.out.println("save");
			marshaller.marshal(eventcontents, new File("XML/Eventcontentlist.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
