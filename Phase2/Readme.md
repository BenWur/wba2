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
*[TODO: Text mit Erklärung]*
![Grafik](http://i.imgur.com/xcjO0RS.png)

##Meilenstein 2:

##Projektspezifisches XML Schema
*[TODO: Erläuterung um was es geht + Semantik der blablabla]*
Für unser Projekt benötigen wir zwei xml Dateien. Eine Datei für die Userprofiles und die andere für die Events, die erstellt werden.


###Userprofiles:
Ein Nutzer des Dienstes muss sich durch ein Profile eindeutig identifizieren, falls er Events oder Kommentare veröffentlichen will. Für sein Userprofile wird also eine eindeutige Kennung benötigt. Hierfür dient der Username.
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



##Ressourcen und die Semantik der HTTP-Operationen
Wir haben uns dafür entschieden unsere Daten auf drei Typen auszulagern. Jeder Ticker selbst wird in einem Event gespeichert. Der zugehörige "Content" also die Beträge des Admins und die Kommentare anderer User werden dabei in eine zweite Datei gespeichert.
Die Profile unserer Nutzer werden in einer dritten Datei gespeichert.
Bei uns entstehen somit drei Ressourcen - Event, Userprofiles, Eventcontent. Aus diesen Ressourcen ergeben sich folgende Operationen:

####Admin (Ersteller eines Events):
Ein Admin hat andere Rechte bzw. andere Funktionen als ein normaler Nutzer, der auf einen Ticker zugreift. Er hat die volle Kontrolle, kann Spielstände aktualisieren, Beiträge löschen und überhaupt den Ticker erst mit wichtigen Informationen - den Beiträgen - füllen.  

| Operation         | Beschreibung |
| ----------------- | ------------ |
| `POST /Events`      | Admin erstellt ein Event. |
| `PUT /Events/$eID`      | Ändern bzw aktualisieren eines Events. |
| `POST /Events/$eID/EventContent`      | Erstellt einen Beitrag im Ticker. |
| `DELETE /Events/$eID`      | Löscht ein als Admin erstelltes Event. |
| `DELETE /Events/$eID/EventContent/$cID`      | Löscht Kommentare in seinem Event. |
| `GET /Userprofiles/$uID`      | Kann sich eigenes oder fremde Userprofiles anzeigen lassen. |

####User:
| Operation         | Beschreibung |
| ----------------- | ------------ |
| `GET /Events`      | Bekommt Events zurück. |
| `GET /Events/$eID`      | Bekommt alle Informationen eines Events. |
| `POST /Events/$eID/EventContent`      | Erstellt einen Kommentar zu einem Beitrag im Ticker. |
| `DELETE /Events/$eID/EventContent/$cID`      | Löscht ein als User erstellten Kommentar. |
| `GET /Userprofiles/$uID`      | Kann sich eigenes oder fremde Userprofiles anzeigen lassen. |
| `GET /Userprofiles`      | Gibt alle User wieder. |
| `PUT /Userprofiles/$uID`      | Kann sein eigenes Userprofiles bearbeiten. |


