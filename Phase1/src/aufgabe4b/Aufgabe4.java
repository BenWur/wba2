package aufgabe4b;
import generated.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



public class Aufgabe4 {

	private static final Scanner in = new Scanner( System.in );
	private static Root datei = null;
	private static Marshaller marshaller = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int i=1;
		while (i==1){
			JAXBContext jc = JAXBContext.newInstance(Root.class);
		    Unmarshaller um = jc.createUnmarshaller();
		    datei = (Root) um.unmarshal(new FileInputStream("aufgabe3d.xml"));
		    marshaller =jc.createMarshaller();
		    
		    System.out.println( "Ihre Auswahl: " );
		    System.out.println( "1 : Kommentar eingeben " );
		    System.out.println( "2 : Rezept anzeigen " );
		    System.out.println( "3 : Beenden " );
		    
		    
		    int eingabe = in.nextInt();
		    
		    switch(eingabe){
		  		case 1: eingabe(); break;
		  		case 2: ausgabe(); break;
		  		case 3: i=0; break;
		    }
		}
        
	}

	public static void eingabe() throws JAXBException, IOException { 
		
		System.out.println( "Welches Rezept wollen Sie kommentieren: " );
	    System.out.println( "1 : Selbstgemachte Butter " );
	    System.out.println( "2 : Schokosoufflee medium " );
	    System.out.println("");
		
		int i = in.nextInt() - 1;
		System.out.println("Geben Sie die Daten ein:");
		
		
		
		//Marshaller erstellen für Hinzufügen der neuen Kommentare zur XML Datei
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		Kommentare kommentar = new ObjectFactory().createKommentare();
		
		XMLGregorianCalendar date = null;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar( new GregorianCalendar() );
		} catch ( DatatypeConfigurationException e ) {
			e.printStackTrace();
		}
		kommentar.setDatum( date );
		
		System.out.println("");

		System.out.print( "Ihr Name: " );
		in.nextLine();
		String name = in.nextLine();
		kommentar.setUser(name);
		

		System.out.println("");

		System.out.print( "Ihr Kommentar: " );
		String text = in.nextLine();
		kommentar.setKommentar(text);

		
		List<Kommentare> kommentare = datei.getRezept().get(i).getKommentarspalte().getKommentare();

		// Add the new comment to the recipe
		kommentare.add( kommentar );

		// Save the new recipe
		schreiben();
		
		
	}
	
	public static void schreiben() throws JAXBException, FileNotFoundException{
		 marshaller.marshal(datei, new File("aufgabe3d.xml"));
	}
        
    public static void ausgabe() throws Exception {
    
	    
	    System.out.println("Rezept: " + "\n");
	        
	    System.out.println( "Welches Rezept wollen Sie ausgeben: " );
	    System.out.println( "1 : Selbstgemachte Butter " );
	    System.out.println( "2 : Schokosoufflee medium " );
	    System.out.println("");
		
		int i = in.nextInt() - 1;
	    
	    
        System.out.println("Titel: " + datei.getRezept().get(i).getRezeptname() + "\n" );
        System.out.println("Untertitel: " + datei.getRezept().get(i).getUntertitel() + "\n" );
        
        if(datei.getRezept().get(i).getBilder().getBild().size()!=0)
        	System.out.println("Bild: ");
        for (int n=0 ;n < datei.getRezept().get(i).getBilder().getBild().size() ; n++ ){
        	System.out.println(datei.getRezept().get(i).getBilder().getBild().get(n).getUrl() + datei.getRezept().get(i).getBilder().getBild().get(n).getBeschreibung() );
        }
        
        System.out.println("\n"+"Portionen: " + datei.getRezept().get(i).getZutaten().getPortionen()  + "\n" );
        System.out.println("Zutaten: ");
        
        for (int n=0 ;n<datei.getRezept().get(i).getZutaten().getZutat().size() ; n++ ){
        	System.out.println( datei.getRezept().get(i).getZutaten().getZutat().get(n).getMenge().getAnzahl() + datei.getRezept().get(i).getZutaten().getZutat().get(n).getMenge().getMengeneinheit() + "  " + datei.getRezept().get(i).getZutaten().getZutat().get(n).getZutatname() );
        }
        
        System.out.println( "\n"+"Arbeitszeit: " + datei.getRezept().get(i).getZubereitung().getArbeitszeit() );
        System.out.println("Schwierigkeitsgrad: " + datei.getRezept().get(i).getZubereitung().getSchwierigkeitsgrad() );
        System.out.println("Brennwert: " + datei.getRezept().get(i).getZubereitung().getBrennwert() );
        System.out.println("Koch-/Backzeit: " + datei.getRezept().get(i).getZubereitung().getKochBackZeit() );
        System.out.println("Ruhezeit: " + datei.getRezept().get(i).getZubereitung().getRuheZeit()  + "\n" );
        System.out.println("Arbeitsschritte: " + datei.getRezept().get(i).getZubereitung().getArbeitsschritte() );
        
        System.out.println("Kommentare: " );
        
        for (int n=0 ;n<datei.getRezept().get(i).getKommentarspalte().getKommentare().size() ; n++ ){
        	System.out.println( datei.getRezept().get(i).getKommentarspalte().getKommentare().get(n).getDatum() + " " + datei.getRezept().get(i).getKommentarspalte().getKommentare().get(n).getUser() );
        	System.out.println( datei.getRezept().get(i).getKommentarspalte().getKommentare().get(n).getKommentar() );
        }
        
        System.out.println( "\n" + "\n" + "\n");

	
	
        }
        
}
