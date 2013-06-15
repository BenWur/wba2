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

public class DataHandlerEventContent {
	private Eventcontentlist eventcontents = null;
	private Marshaller marshaller = null;

	public DataHandlerEventContent() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Eventcontentlist.class);
			Unmarshaller um = jc.createUnmarshaller();
			eventcontents = (Eventcontentlist) um.unmarshal(new File("XML/Eventcontentlist.xml"));
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public Eventcontentlist getEventcontents() {
		return this.eventcontents;
	}

	public Eventcontent getEventcontentbyID(int id) {
		return this.eventcontents.getEventcontent().get(id - 1);
	}

	public URI writeNewEventcontent(Eventcontent event) {

		List<Eventcontent> Eventcontentliste = eventcontents.getEventcontent();
		Eventcontentliste.add(event);
		
		System.out.println("added");

		this.savePersistent();
		
		System.out.println("nur noch return");
		
		return URI.create("http://localhost:4434/events/"+event.getEventID().toString()+"/eventcontent");

	}
	
	public URI postNewKommentar(int eventID, int tickerBeitragID, Kommentar kommentar){
	   
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
	    this.savePersistent();
	    
	    return URI.create("http://localhost:4434/events/"+eventID+"/eventcontent/beitrag/"); 
	}
	
	public URI postNewBeitrag(int eventID, TickerBeitrag beitrag){
		   
	    List<Eventcontent> eventcontentliste = eventcontents.getEventcontent();
	    BigInteger ID = BigInteger.ZERO ;
	    int i = 0;
	    for(Eventcontent evc : eventcontentliste ){
	    	System.out.println("for"+evc.getEventID());
	    	if(evc.getEventID().intValue()==eventID){
	    		List<TickerBeitrag> beitragliste = evc.getTickerBeitrag();
	    		for(TickerBeitrag tickerc : beitragliste ){
	    			
			    	if(tickerc.getTickerBeitragID().compareTo(ID)==1){
			    		ID = tickerc.getTickerBeitragID();
			    		System.out.println(ID);
			    	}
			    }
	    		beitrag.setTickerBeitragID(ID.add(BigInteger.ONE));
	    		eventcontentliste.get(i).getTickerBeitrag().add(beitrag);
	    	}
	    	i++;
	    }
	    
	    this.savePersistent();
	    
	    return URI.create("http://localhost:4434/events/"+eventID+"/eventcontent/beitrag/"+ID.add(BigInteger.ONE)); 
	}
	
	public Response deleteTickerBeitrag(int eventID, int tickerBeitragID){
		   
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
	    
	    this.savePersistent();
	    
	    return Response.noContent().build(); 
	}

	public void delete(int id) {

		List<Eventcontent> Eventcontentliste = eventcontents.getEventcontent();

		int i = 0;
		for (Eventcontent ev : Eventcontentliste) {
			if (ev.getEventID().equals(id)) {
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
