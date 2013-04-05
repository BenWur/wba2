package aufgabe4b;
import generated.*;

import java.io.FileInputStream;
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
		
		//Unmarshalling from an InputStream:
        //InputStream is = new FileInputStream( "aufgabe3d.xml" );
        //JAXBContext jc2 = JAXBContext.newInstance( Root.class );
        //Unmarshaller u = jc2.createUnmarshaller();
        //generated.Root datei = u.unmarshal( is );
		
	}
        
    public static void ausgabe() throws Exception {
    
	    // Unmarshalling from a File:
		JAXBContext jc = JAXBContext.newInstance(Root.class);
	    Unmarshaller um = jc.createUnmarshaller();
	    generated.Root datei = (Root) um.unmarshal(new FileInputStream("aufgabe3d.xml"));
	    
	    System.out.println("Rezepte: " + "\n");
	        for (Rezept rezept:datei.getRezept()){
	            System.out.println("Titel: " + rezept.getRezeptname() + "\n" );
	            System.out.println("Untertitel: " + rezept.getUntertitel()  + "\n" );
	            
	            for (int n=0 ;n < rezept.getBilder().getBild().size() ; n++ ){
	            	System.out.println("Bild: " + rezept.getBilder().getBild().get(n).getUrl() + rezept.getBilder().getBild().get(n).getBeschreibung() );
	            }
	            
	            System.out.println("Portionen: " + rezept.getZutaten().getPortionen()  + "\n" );
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
	            System.out.println( "\n" + "\n" + "\n");
	        }

	
	
        }
        
}
