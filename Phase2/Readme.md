#Web-basierte Anwendungen 2: Verteilte Systeme

Fachhochschule Köln Campus Gummersbach<br>
Fakultät für Informatik und Ingenieurwissenschaften

##Phase 2 - Projekt "Social Ticker"

##Autoren
* [Dario Vizzaccaro](/dvizzacc) 11085033
* [Benedikt Wurth](/BenWur) 11084022

##Inhaltsverzeichnis

* [Projektidee](#projektidee)
* [Meilenstein 1](#meilenstein-1)
 * [Synchrone Datenübertragung](#synchrone-datenbertragung)
 * [Asynchrone Datenübertragung](#asynchrone-datenbertragung)
 * [Kommunikationsabläufe und Interaktion](#kommunikationsablufe-und-interaktion)
* [Meilenstein 2](#meilenstein-2) 
 * [Projektspezifisches XML Schema](#projektspezifisches-xml-schema)
 * [Userprofiles](#userprofiles)
 * [Events](#events)
 * [Eventcontent](#eventcontent)
 * [Ressourcen und die Semantik der HTTP-Operationen](#ressourcen-und-die-semantik-der-http-operationen)
		* [Admin](#admin)
		* [User](#user)

###Projektidee
*[TODO: Nochmal durchlesen und überarbeiten]*
Unsere Vision ist, dass man seinen eigenen Sportticker erstellen kann und dieser von Freunden oder anderen Nutzern verfolgt und kommentiert werden kann.

Als Grundfunktion wird das Erstellen von Events wichtig sein. Ein Nutzer kann für eine beliebige Sportart einen Liveticker ( Event ) erstellen. Er kommentiert dann dieses Event und aktualisiert den Spielstand und die Ereignisse. Nutzer können dem Event dann beitreten, seine Beiträge verfolgen und zu diesen Kommentare verfassen bzw. diskutieren. Der Ticker kann in Echtzeit bewertet werden, damit weitere Nutzer sehen können, ob der Ticker gut ist.

Der Reiz an dem Erstellen eines Tickers ist, dass ein Nutzer seine eigenen Fans bekommen kann, die zusammen mit ihm Sportereignisse miterleben und Meinungen austauschen. Der Ersteller eines Tickers ist dabei der "Leiter". Er schreibt die wichtigen Ereignisse und seine Beiträge werden Live bewertet.

Die App bietet eine übersichtliche Listung der Ereignisse und unterstützt die Kommunikation zwischen den Usern. Es ist zusätzlich möglich mehrere Ticker gleichzeitig in einem Fenster zu verfolgen. Wenn man z.B. Fan von zwei Teams ist, die an verschiedenen Spielen teilnehmen, will man trotzdem beide gleichzeitig erleben. Auch kann man so mehrere Ticker zum gleichen Spiel folgen und bekommt so mehr Infos und eine vielschichtige Sicht auf Geschehnisse. 

##Meilenstein 1:

###Synchrone Datenübertragung
-	Eventliste 
	* laufenden Events
	* kommende Events
	* Events von Favoriten
-	Zusatzinfos zu den Spielen 
	* Aufstellung
	* Spieler
	* Wetter
	* Zuschauer
-	Bewertungen von Spielern nach dem Spiel
-	Userprofiles	
	* Name
	* Statistiken
	* Follower
	
###Asynchrone Datenübertragung
-	Ticker
-	Kommentare von Nutzern
	* Kommentare sind "Bewertbar"
-	Benachrichtigung 
	* Favorit kommentiert ein Spiel
	* Lieblingsmannschaft spielt

###Kommunikationsabläufe und Interaktion
Im Grunde genommen ist erst einmal jeder Nutzer gleich und ihm liegen die selben Funktionen vor. Jeder Nutzer kann auf sein eigenes Profil zugreifen und dieses auch verändern. Zusätzlich kann er auch andere Profile aufrufen und sich Freunde aufrufen. 
Die Interaktion mit dem Server unterscheidet sich erst wenn man ein Event erstellt bzw. ein Event aufruft. Als Administrator (Ersteller eines Events) hat man einen erweiterten Funktionsumfang. Als allererstes sollte man für sein Event Informationen anlegen ("Event verwalten"). Wenn man alles soweit angelegt hat, kann man Beiträge verfassen und diese dann veröffentlichen. Sie werden chronologisch aufgelistet. Andere Nutzer, die das Event aufrufen können dann die erstellten Beiträge kommentieren. Als Admin ist es möglich Kommentare anderer Nutzer zu löschen.
Als Nutzer (Aufrufer eines Events) kann man sich eine Liste aller Events anzeigen lassen. Dort wählt man dann ein Event auf, welches einen interessiert. In einem Event angekommen, sieht man alle aktuellen Beiträge und Kommentare. Man kann sich nun entscheiden, ob man selbst ein Kommentar verfassen will oder ob man ersteinmal den Ticker verfolgt, bis man seine eigene Meinung veröffentlicht.
![Grafik](http://i.imgur.com/xcjO0RS.png)

##Meilenstein 2:

##Projektspezifisches XML Schema
Um die Informationen zu verwalten benötigen wir xml-Dateien. Diese speichern alle Informationen und gewährleisten die Kommunikation zwischen Client und Server.
Alle Daten werden in drei xml Dateien ausgelagert. Eine Datei enthält alle Daten der Nutzer. Hier werden die wichtigen Daten wie Username, Vorname, Nachname, Geburtsdatum, Anzahl erstellte Events und Anzahl geposteter Beiträge gespeichert. 
Die zweite Datei, ist die Events-Datei. Hier werden alles Grund-Informationen eines Events gespeichert. Eventname, Eventdatum, Eventadmin, Eventtyp, Eventdauer und Bewertung werden hier für alle Events abgelegt. Auch hier vergibt das System zusätzlich noch eine EventID zur eindeutigen Zuweisung.
Die letzte Datei ist für den kompletten Content, also den Beiträgen und Kommentaren zuständig. Man hätte den Content und das Event zwar in ein xml-Dokument zusammenfassen können. Jedoch verändert sich der Content ständig und schnell, wobei die Event-Informationen statisch sind und nach anlegen nicht mehr verändert werden.



###Userprofiles:
Ein Nutzer des Dienstes muss sich durch ein Profile eindeutig identifizieren, falls er Events oder Kommentare veröffentlichen will. Der Nutzer identifiziert sich durch seinen Usernamen. Zusätzlich legt das System automatisch eine UserID für den einfacheren Umgang mit den Usern an. Die UserID wird später benötigt, um die Events und Beiträge eindeutig den Nutzern zuzuordnen. 
Der User kann dann noch weitere Angaben machen, um zB von seinen Freunden erkannt zu werden. Vor- , Nachnamen und Geburtstag sind optionale Angaben, falls der User diese nicht veröffentlichen will. Ein User kann Freunde hinzufügen, um auf dem aktuellsten Stand zu bleiben, was seine Freunde so machen.
die Anzahl erstellter Events und geposteter Beiträge werden automatisch im Profil hinterlegt. Hier kann jeder User sehen, wie aktiv man selbst oder andere im Social Ticker sind.


####complexe-types:
- User (Username, Vorname, Nachname, Geburtsdatum, Freunde, erstellte Events, gepostete Beträge)*
- Freunde (Username)*

####simple-types:
- Username (String)
- Vorname (String)
- Nachname (String)
- Geburtsdatum (Date)
- erstellte Events (Decimal)
- gepostete Beträge (Decimal)

####Restriktionen:
- Username min.3, max.15 Zeichen
- Vorname min.2, max. 20 Zeichen
- Nachname min.2, max. 20 Zeichen


###Events
Die Events sind das Herzstück unseres Dienstes. Es können hier Events (wie zB ein Fußball- oder Handballspiel) erstellt und verwaltet werden. Ein Event benötigt natürlich eine eindeutige Kennung. Der Name wäre hierbei nicht in jedem Fall eindeutig. Deswegen wird eine ID zur einfacheren Verwaltung erstellt. Eventname gibt an um welches Thema der Ticker überhaupt geht. Der Eventadmin ist sofort der User, der das Event erstellt hat. Auch interessant für den User ist es, um welchen Typ es von Event handelt. Also ob es ein Football-, Faustball- oder doch Golfspiel ist. Ein Event kann von anderen Usern bewertet werden. Die Wertung wird abgespeichert und automatisch verrechnet.

 
####complexe-types:
- Event (EventID, Eventname, Eventadmin, Eventtyp, Eventdatum, Eventdauer, Eventbewertung)
- Eventgesamtbewertung (Bewertung*)

####simple-types:
- Eventname (String)
- Eventdatum (Date)
- Eventadmin (Username)
- Eventtyp (String)
- Eventdauer (Date)
- Bewertung (decimal)

####Restriktionen:
- Eventname min. 5, max. 75 Zeichen
- Eventtyp min. 4, max. 40 Zeichen
- Eventdauer > 20 Minuten
- Eventbewertung < 10

###Eventcontent
Zentral bei den Beiträgen des Events ist natürlich das "Soziale". Hierbei sind die Beiträge des Admins der Dreh- und Angelpunkt des Events. Andere User können dann zusätzlich noch unter den Beiträgen des Admins Kommentare setzen und diskutieren, ob sie die Geschehnisse genau so interpretieren oder einen anderen Standpunkt besitzen.

####complexe-types:
- EventContent(TickerBeitrag*)
- TickerBeitrag (Text,Kommentar*)
- Kommentar(Username,KommentarText)

####simple-types:
- TickerBeitrag (String)
- KommentarText (String)

####Restriktionen:
- TickerBeitrag min. 3 Zeichen
- KommentarText min. 3 Zeichen



##Ressourcen ,die Semantik der HTTP-Operationen
Die Daten sollten auf drei Typen ausgelagert werden. Jeder Ticker selbst wird in einem Event gespeichert. Der zugehörige "Content" also die Beträge des Admins und die Kommentare anderer User werden dabei in eine zweite Datei gespeichert. Die Aufteilung von Content und Event ist sinnvoll, da der Content dynamische Daten enthält. Diese werden oft verändert, wobei das Event selbst mit den zugehörigen Informationen meist einmal angelegt und nicht mehr verändert wird.
Die Profile unserer Nutzer werden in einer dritten Datei gespeichert.
Bei uns entstehen somit drei Ressourcen - Event, Userprofiles, Eventcontent. Aus diesen Ressourcen ergeben sich folgende Operationen:

####Admin (Ersteller eines Events):
Ein Admin hat andere Rechte bzw. andere Funktionen als ein normaler Nutzer, der auf einen Ticker zugreift. Er hat die volle Kontrolle, kann Spielstände aktualisieren, Beiträge löschen und überhaupt den Ticker erst mit wichtigen Informationen - den Beiträgen - füllen. Dem Admin stehen zusätzlich die Funktionen der User zur Verfügung.

| Operation         | Beschreibung |
| ----------------- | ------------ |
| `POST /events`      | Admin erstellt ein Event. |
| `PUT /events/$eID`      | Ändern bzw aktualisieren eines Events. |
| `POST /events/$eID/EventContent`      | Erstellt einen Beitrag im Ticker. |
| `DELETE /events/$eID`      | Löscht ein als Admin erstelltes Event. |
| `DELETE /events/$eID/EventContent/beitrag/$tBID`      | Löscht Beitrag in seinem Event. |

####User:
Als User hat man die Standard Funktionen. Die URIs wurden wie folgt gewählt
| Operation         | Beschreibung |
| ----------------- | ------------ |
| `GET /events`      | Bekommt Events zurück. |
| `GET /events/$eID`      | Bekommt alle Informationen eines Events. |
| `GET /events/$eID/eventcontent`      | Bekommt gesammten Content eines Events. |
| `POST /events/$eID/eventcontent/beitrag/$tBID`      | Erstellt einen Kommentar zu einem Beitrag im Ticker. |
| `GET /users/$uID`      | Kann sich eigenes oder fremde Userprofiles anzeigen lassen. |
| `GET /users`      | Gibt alle User wieder. |
| `PUT /users/$uID`      | Kann sein eigenes Userprofiles bearbeiten. |

##RESTful Webservice

####Implementierung der Operationen
Um auf die xml-Daten zuzugreifen wird Marshalling und Unmarshalling verwendet. Marshalling dient dazu aus einer angelegten Datei, xml-Daten auszulesen. Danach können Daten angehängt, verändert oder gelöscht werden und anschließend durchs Unmarshalling wieder abgespeichert werden. 

####PathParams und QueryParams
Mit PathParams kann man auf einen bestimmten Teil der Daten zugreifen. So ist es möglich ein bestimmtes Event bzw einen bestimmten User aufzurufen. Mit QueryParams lassen sich Ergebnisse filtern. Sinnvolle Filterung wäre z.B. User nach Namen oder Land zu filtern. Genau so macht es Sinn die Events nach Namen des Events zu filtern. Als Beispiel wäre ein Nutzer der alle Spiele von Bayern haben möchte und als Filter den Namen Bayern eingibt.

##Konzeption + XMPP Server einrichten
####Leafs (Topics)
Jedes Event ist Abonnierbar. Sobald man das Event joint bekommt man als erstes alle Nachrichten als GET. Wenn es dann aufgerufen ist, werden neue Benachrichtigungen per asynchrone Benachrichtungen verschickt. Ein User kann selbst neue Kommentare schreiben oder von anderen welche zugestellt bekommen.
####Publisher 
Im Grunde benommen ist jeder Publisher, der sich in einem Event befindet. Als User kann man Kommentare erstellen und diese werden asynchron an andere gepusht. Der Admnis selbst ist natürlich auch Publisher. Er erstellt Beiträge zu seinem Event welche alle zugestellt bekommen.
####Subscriber 
Genauso wie jeder Publisher sein kann, ist auch jeder Subscriber der sich in einem Event befindet. Sobald man einem Event beitritt kriegt man alle neuen Beiträge und Kommentare asynchron.
####Zu übertragene Daten
Die übertragbaren Daten sind die Beiträge eines Admins und die Kommentare der User. Im Grunde genommen ist der Ticker nicht viel anders als ein Chat mit besonderer Formatierung und Featuers wie Aktueller Stand und Bewertungen.

##XMPP - Client

##Client - Entwicklung
