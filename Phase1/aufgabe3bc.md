
b)

Aufgabe:

Betrachten Sie nun andere Rezepte auf der Webseite http://www.chefkoch.de. 
Beschreiben Sie welche Gemeinsamkeiten die Rezepte hinsichtlich ihrer Daten haben und worin Sie sich unterscheiden.
	
Antwort:

    Gemeinsamkeiten
    
    	- grundsätzlich selbe Gliederung (Rezeptname, Bild, Zutaten, Zubereitung)
    	- Rezeptname, Zutaten, Arbeitszeit, Schwierigkeitsgrad,
          Brennwert (zur Not:"keine Angaben") 
    	- Zubereitungstext sind pflichteingaben.
       
    	
    Unterschiede
    
    	- Nicht immer sind Bilder vorhanden
    	- Manche Rezepte besitzen Untertitel
    	- Manche Zutaten sind kategorisiert ("Für den Belag:","Für den Boden:")
    	- Mengenangaben müssen nicht festgelegt werden
    	- Es gibt weitere Attribute wie z.B.:
    		Koch-/Backzeit: ca. 20 Min.	 
    		Ruhezeit: ca. 16 Std
	
	
c) 
Arbeiten Sie die Kriterien heraus, die für die Entwicklung einer XML-Schema-Datei beachtet werden müssen. 
Die Schema-Datei soll die Struktur für eine XML-Datei definieren, in der mehrere unterschiedliche Rezepte 
gespeichert werden können. 
Ziel ist es, dass das XML-Schema möglichst restriktiv ist, so dass in der XML-Datei möglichst semantisch 
sinnvolle Daten bezüglich der Rezepte gespeichert werden können. Ziehen Sie beim Aufstellen der Kriterien 
u.A. folgende Fragestellungen in Betracht:

-Welche Daten müssen in simple und welche in complex-types abgebildet werden?

-Für welche Daten ist die Abbildung in Attributen sinnvoller?

-Welche Datentypen müssen für die Elemente definiert werden?

-Welche Restriktionen müssen definiert werden?

<br/>
<br/>

Antwort :

Simple-types enthalten keine weiteren Elemente
	
simple-types:

	  -rezeptname 
  	-url
  	-beschreibung 
  	-zutatname 
  	-arbeitszeit 
  	-schwierigkeitsgrad 
  	-brennwert 
  	-arbeitsschritte
  	-portionen
  	-untertitel 
  	
Complexe-types enthalten weiteren Elemente
  	
complex-types:
	
	  -rezept (rezeptname,untertitel,bilder,zutaten,zubereitung)
  	-bilder (bild)*
  	-zutaten (portionen,(zutat)+)
  	-zubereitung (arbeitszeit,schwierigkeitsgrad,brennwert,kochBackZeit?,
       ruheZeit?,arbeitsschritte)
  	-zutat (menge,zutatname)
  	-bild (url,beschreibung)
  	-menge(mengeneinheit,anzahl)
  	-kommentarspalte(kommentare)
  	-kommentare(user, datum, kommentar)*
  	
Abbildung in Attribute:

	-bild kann das feste Attribut URL enthalten
	-Die "menge" kann mit einem Attribut für die Einheit unterteilt werden
	-schwierigkeitsgrad kann als Attribut festgelegt werden, da dieser nur 
	 feste Werte enthält (simpel,normal,pfiffig).
	-Einheit für Brennwert als Attribut
	
Datentypen:

	-rezeptname(String)
	-untertitel(String)
	-url(String)
	-beschreibung(String)
	-menge(decimal)
	-zutatname(String)
	-Arbeitszeit(decimal)
	-brennwert(decimal)
	-arbeitsschritte(String)
	-portionen(decimal)
	
Restriktionen:

	-rezeptname max. 100 Zeichen
	-untertitel max. 200 Zeichen
	-url max. 150 Zeichen	
	-beschreibung max. 50 Zeichen
	-zutatname max. 100 Zeichen	
	-menge, portionen, arbeitszeit und brennwert dürfen nicht negativ sein, 
     deswegen habe ich den Datentyp decimal gewählt.	
	-portionen ist dabei aber auf max. 100 Portionen begrenzt
	-Arbeitsschritte würde ich nicht begrenzen, da manche Rezepte sehr 
     umfangreich sind 
	 (siehe http://www.chefkoch.de/rezepte/1426751247563051/Spareribs-NT-im-Bratschlauch.html )
  	
