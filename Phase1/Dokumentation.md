Dokumentation zur Phase 1
Benedikt Wurth



Aufgabe 1

Erklären Sie kurz die Begriffe Wohlgeformtheit, Validität und Namespaces im Bezug auf XML und XML-Schema.


Wohlgeformtheit

    Wohlgeformtheit im Zusammenhang mit xml-Dateien bedeutet, dass alle Regeln 
    korrekt eingehalten wurden. Am Anfang steht die XML-Deklaration. Es sollte 
    mindestens ein Datenelement vorhanden sein und es gibt ein alles 
    umschließendes Datenelement. Ein xml-Dokument sollte immer wohlgeformt sein.
	 
Validität

    Ein xml-Dokument ist valide, wenn sie der Dokumenttypendefinition (DTD) 
    entspricht, also einer inhaltlichen Struktur entsprechen. Ein xml-Dokument 
    kann valide sein, muss aber wohlgeformt sein.
	 
Namespace

    Der Namespace bezeichnet alle gültigen Namen eines Bereichs. Also alle Namen 
    die für Elemente oder Attribute verwendet werden dürfen. Wichtig ist der 
    Namensraum, da ein Name wie z.B "p" für verschiedenes stehen kann. Er kann zu 
    einem wie bei html als Absatz definiert sein. In einem anderen Namensraum aber 
    für Person gelten. Der Namensraum definiert diesen aber dann eindeutig.
    
    
    
Aufgabe 2

a) Erzeugen Sie ein XML-Dokument, dass die Daten des folgenden Formulars vollständig erfasst:
http://www.gm.fh-koeln.de/~vsch/anmeldung/gruppenanmeldung.html
Füllen Sie das Dokument mit einem Beispieldatensatz. Achten Sie darauf, dass über das Formular mehrere Personen gleichzeitig 
erfasst werden können.
Wichtig: Es sollte nicht die HTML-Struktur der Webseite in der XML-Datei abgebildet werden, sondern die zu übertragenden Daten.


b) Erzeugen Sie ein JSON-Dokument, dass zu ihrem XML-Dokument äquivalent ist.


	Um die Aufgaben zu lösen, habe ich mich sehr stark an dem Tutorial von http://www.w3schools.com/ orientiert
	(http://www.w3schools.com/json/ & http://www.w3schools.com/xml/).
	Die Schwierigkeit war erstmal die Aufgabe richtig zu deuten und dadurch Schlüsse zu ziehen, auf was man achten
	muss. Personen gleichzeitig zu erfassen war dabei das größte Problem, aber nach kurzer Einarbeitung auch lösbar.
	Man hätte es alternativ auch lösen können, indem man nochmal eine extra Gruppierung um die "Teilnehmer" gemacht hätte.


	
Aufgabe 3

a) Gegeben ist folgendes Rezept:

http://www.chefkoch.de/rezepte/24641006006067/Lenchen-s-Schokoladenkuchen.html

Entwickeln Sie ein XML-Dokument, in dem die Daten des Rezeptes abgebildet werden. Achten Sie darauf, dass das Dokument 
semantisch möglichst reichhaltig ist. Bei dieser und den folgenden Aufgaben lassen sie bitte die Daten in der Marginalspalte 
auf der rechten Seite weg.

b) Betrachten Sie nun andere Rezepte auf der Webseite http://www.chefkoch.de. Beschreiben Sie welche Gemeinsamkeiten die 
Rezepte hinsichtlich ihrer Daten haben und worin Sie sich unterscheiden.

c) Arbeiten Sie die Kriterien heraus, die für die Entwicklung einer XML-Schema-Datei beachtet werden müssen. Die Schema-Datei 
soll die Struktur für eine XML-Datei definieren, in der mehrere unterschiedliche Rezepte gespeichert werden können. 
Ziel ist es, dass das XML-Schema möglichst restriktiv ist, so dass in der XML-Datei möglichst semantisch sinnvolle Daten 
bezüglich der Rezepte gespeichert werden können. Ziehen Sie beim Aufstellen der Kriterien u.A. folgende Fragestellungen in Betracht:

Welche Daten müssen in simple und welche in complex-types abgebildet werden?
Für welche Daten ist die Abbildung in Attributen sinnvoller?
Welche Datentypen müssen für die Elemente definiert werden?
Welche Restriktionen müssen definiert werden?

d) Erstellen Sie nun ein XML-Schema auf Basis ihrer zuvor definierten Kriterien. Generieren Sie nun auf Basis des Schemas 
eine XML-Datei und füllen Sie diese mit zwei unterschiedlichen und validen Datensätzen.



a)

	Diese Aufgabe war recht einfach, aber erst nach den folgenden Aufgaben stellten sich weitere Krieterien heraus.
	Ich war mir allerdings nicht sicher, ob das Doctype zwingend notwendig war oder nicht. Vorsorglich habe ich es 
	aber auch ergänzt. 
	Nachträglich musste ich noch Kommentare einfügen, was dann aber keine große Arbeit war. 
	Auf Attribute wurden bewusst verzichtet, da diese später ausgearbeitet werden sollten.

b)
	
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
	 
d)
	Wie auch bei Aufgabe3a musste ich auch hier nachträglich noch die Kommentare hinzufügen. Die Name sind vielleicht 
	nicht glücklich gewählt, aber für mich nachvollziehbar. Die Restriktionen waren was kniffliger doch auch dort habe
	ich mich an den Tutorials auf w3schools orientiert. Man hätte noch mehr erstellen können aber manche Sachen mussten
	meines Erachtens nach nicht begrenzt werden, wie z.B. der Text für die Zubereitung.

	
	
Aufgabe 4

In dieser Aufgabe entwickeln Sie mit Hilfe des JAXB Frameworks ein Java-Programm, welches die XML-Datei aus der vorigen 
Aufgabe einlesen, modifizieren und ausgeben kann.

a) Erzeugen Sie zunächst aus der Schema-Datei der vorherigen Aufgabe Java-Objekte. Nutzen Sie dazu den XJC-Befehl über das 
Terminal und fügen Sie die generierten Klassen ihrem Java-Projekt hinzu. Alternativ zur Terminal-Eingabe existiert ein JAXB 
Eclipse Plug-In welches hier herunter geladen werden kann: http://sourceforge.net/projects/jaxb-builder.

Dieses kann wie ein normales Plugin in Eclipse eingebunden werden. Zur Nutzung des Plugins klicken Sie mit der rechten Maustaste 
auf die Schema-Datei und wählen Sie aus dem Kontextmenü Generate => JAXB-Classes... und folgen Sie den weiteren Anweisungen 
in dem Dialogfenster.

b) Entwickeln Sie nun das Java-Programm. Es soll die XML-Datei öffnen, einlesen und die enthaltenen Daten über die Konsole 
wieder ausgeben. Benutzen Sie bitte bei der Bearbeitung der Aufgabe die generierten JAXB-Klassen aus der vorherigen Teilaufgabe.

c) Erweitern Sie ihr Programm so, dass es möglich ist, über die Konsole neue Kommentare zu einem Rezept hinzuzufügen. 
Benutzen Sie auch hierfür die generierten JAXB-Klassen. Erstellen Sie ein Menü, dass in der Konsole angezeigt wird. Über 
dieses Menü sollen die Auswahl der Funktionen, zum Ausgeben der Daten und Erstellen neuer Kommentare, möglich sein.


a) 
	Am Anfang wusste ich nicht genau, wie man das Plug-In anwendet bzw. richtig einbindet aber nach einer kurzen Internetrecherche 
	kam ich dann doch zum Ziel.
	
b) 
	Die Aufgabe war sehr schwierig, da ich erst einmal einen guten Anfang brauchte und herausfinden musste, wie man Dateien
	einliest und interpretiert. Das Layout bzw die Menüführung sind zwar nicht sehr umfangreich aber zweckdienlich.
	
c)
	Zusammen mit b) erstellt. Hier war es wichtig die Funktionsweise des Marshallers zu ergreifen und richtig zu implementieren.
	Wenn man ihn aber eingebunden hat, war die Aufgabe selber nicht mehr so schwierig, da sie sich dann doch noch stark an der
	vorangehenden Aufgabe orientiert.


	
Aufgabe 5

Diskutieren Sie, warum es sinnvoll ist Daten in Formaten wie XML oder JSON zu speichern. Stellen Sie außerdem die beiden 
Formate gegenüber und erläutern Sie kurz deren Vor- und Nachteile.	
	


	Diskutieren Sie, warum es sinnvoll ist Daten in Formaten wie XML oder JSON zu speichern. 
	Stellen Sie außerdem die beiden Formate gegenüber und erläutern Sie kurz deren Vor- und Nachteile.
	
	
	
	Warum sind JSON oder XML sinnvoll?
		Es ist sinnvoll JSON oder XML zu nutzen, wenn man später mit den eingegebenen Daten weiter arbeiten möchte.
		XML und JSON strukturieren die Daten und machen sie leichter verwertbar.
		Beide Formate sind plattformunabhängig und durch UNICODE international einsetzbar.
		
	
	
	
	JSON vs. Xml

	JSON: 
		Vorteile
			-sehr kompakt und klein
			-meist auf einen Blick überschaubar
			
		Nachteile
			-Syntax gewöhnungsbedürftig
			-Metadaten oder Kommentare schwieriger nutzbar
			
			
			
			
	XML:
		Vorteile
			-einfache Lesbarkeit
			-über mehrere Jahre schon standard
			
		Nachteile
			-sehr komplex/lang
			-Sturktur muss vorher sehr genau erfasst worden sein
