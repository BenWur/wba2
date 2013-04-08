package aufgabe4b;
import generated.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.xml.bind.*;


public class Aufgabe4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
        
        ausgabe();
        
	}

	public static void eingabe() throws Exception { 
		
		
		//Marshaller!!!
		JAXBContext jc2 = JAXBContext.newInstance(Root.class);
		Marshaller ma = jc2.createMarshaller();
		Rezept rezeptNew = new Rezept();
		//datei = ma.marshal(rezeptNew,new FileOutputStream("aufgabe3d.xml"));
		
	}
        
    public static void ausgabe() throws Exception {
    
	    // Unmarshalling from a File:
		JAXBContext jc = JAXBContext.newInstance(Root.class);
	    Unmarshaller um = jc.createUnmarshaller();
	    generated.Root datei = (Root) um.unmarshal(new FileInputStream("aufgabe3d.xml"));
	    
	    System.out.println("Rezepte: " + "\n");
	        for (Rezept rezept:datei.getRezept()){
	            System.out.println("Titel: " + rezept.getRezeptname() + "\n" );
	            System.out.println("Untertitel: " + rezept.getUntertitel() + "\n" );
	            
	            if(rezept.getBilder().getBild().size()!=0)
	            	System.out.println("Bild: ");
	            for (int n=0 ;n < rezept.getBilder().getBild().size() ; n++ ){
	            	System.out.println(rezept.getBilder().getBild().get(n).getUrl() + rezept.getBilder().getBild().get(n).getBeschreibung() );
	            }
	            
	            System.out.println("\n"+"Portionen: " + rezept.getZutaten().getPortionen()  + "\n" );
	            System.out.println("Zutaten: ");
	            
	            for (int n=0 ;n<rezept.getZutaten().getZutat().size() ; n++ ){
	            	System.out.println( rezept.getZutaten().getZutat().get(n).getMenge().getAnzahl() + rezept.getZutaten().getZutat().get(n).getMenge().getMengeneinheit() + "  " + rezept.getZutaten().getZutat().get(n).getZutatname() );
	            }
	            
	            System.out.println( "\n"+"Arbeitszeit: " + rezept.getZubereitung().getArbeitszeit() );
	            System.out.println("Schwierigkeitsgrad: " + rezept.getZubereitung().getSchwierigkeitsgrad() );
	            System.out.println("Brennwert: " + rezept.getZubereitung().getBrennwert() );
	            System.out.println("Koch-/Backzeit: " + rezept.getZubereitung().getKochBackZeit() );
	            System.out.println("Ruhezeit: " + rezept.getZubereitung().getRuheZeit()  + "\n" );
	            System.out.println("Arbeitsschritte: " + rezept.getZubereitung().getArbeitsschritte() );
	            
	            System.out.println("Kommentare: " );
	            
	            for (int n=0 ;n<rezept.getKommentarspalte().getKommentare().size() ; n++ ){
	            	System.out.println( rezept.getKommentarspalte().getKommentare().get(n).getDatum() + " " + rezept.getKommentarspalte().getKommentare().get(n).getUser() );
	            	System.out.println( rezept.getKommentarspalte().getKommentare().get(n).getKommentar() );
	            }
	            
	            System.out.println( "\n" + "\n" + "\n");
	        }

	
	
        }
        
}
