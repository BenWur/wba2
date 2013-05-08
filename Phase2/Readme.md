#Web-basierte Anwendungen 2: Verteilte Systeme

##Phase 2 Projekt "Social Ticker"

###Projektidee
[TODO: Nochmal durchlesen und überarbeiten]
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
[TODO: Text mit Erklärung]
![Grafik](http://i.imgur.com/xcjO0RS.png)

##Meilenstein 2: 
##Projektspezifisches XML Schema
[TODO: Erläuterung um was es geht + Semantik der blablabla]
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
Die Events sind das Herzstück unseres Dienstes. Es können hier Events (wie zB ein Fußball- oder Handballspiel) erstellt und verwaltet werden. Ein Event benötigt natürlich eine eindeutige Kennung. Der Name wäre hierbei nicht in jedem Fall eindeutig. Deswegen wird eine ID zur einfacheren Verwaltung erstellt. Eventname gibt an um welches Thema der Ticker überhaupt geht. Der Eventadmin ist sofort der User, der das Event erstellt hat. Auch interessant für den User ist es, um welchen Typ es von Event handelt. Also ob es ein Football-, Faustball- oder doch Golfspiel ist.
Zentral bei den Beiträgen des Events ist natürlich das "Soziale". Ein Event kann von anderen Usern bewertet werden. Die Wertung wird abgespeichert und automatisch verrechnet. Andere User können dann zusätzlich noch unter den Beiträgen des Admins Kommentare setzen und diskutieren, ob sie die Geschehnisse genau so interpretieren oder einen anderen Standpunkt besitzen.
 
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
Bei uns entstehen somit 3 Ressourcen - Event, Userprofiles, Eventcontent. Aus diesen Ressourcen ergeben sich folgende Operationen:
####Admin (Ersteller eines Events):
| Operation         | Beschreibung |
| ----------------- | ------------ |
| `PUT /Event`      | Admin erstellt ein Event. |
| `POST /Event/EventContent`      | Erstellt einen Beitrag im Ticker. |
| `DELETE /Event`      | Löscht ein als Admin erstelltes Event. |
| `DELETE /Event/EventContent`      | Löscht Kommentare in seinem Event. |
| `GET /Userprofiles`      | Kann sich eigenes oder fremde Userprofiles anzeigen lassen. |

####User:
| Operation         | Beschreibung |
| ----------------- | ------------ |
| `GET /Event`      | Bekommt alle Informationen eines Events. |
| `POST /Event/EventContent`      | Erstellt einen Kommentar zu einem Beitrag im Ticker. |
| `DELETE /Event/EventContent`      | Löscht ein als User erstellten Kommentar. |
| `GET /Userprofiles`      | Kann sich eigenes oder fremde Userprofiles anzeigen lassen. |
| `POST /Userprofiles`      | Kann sein eigenes Userprofiles bearbeiten. |


