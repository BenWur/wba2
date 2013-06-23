package gui;

import eventlist.Event;
import guidata.Register;
import guidata.TickerEvents;
import guidata.UserContent;
import java.util.HashMap;
import java.util.Map;
import nodepackage.PubSubController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * In der WindowSocialMain finden alle wichtigen Aktivitäten statt. 
 * Diese Methode ist sozusagen das Hauptmenü
 * @author Ben & Dario
 *
 */

public class WindowSocialMain extends Application {

    public String userName;                         // aktueller Nutzer
    private Tab tab1;                               // Haupt-Tab
    private Tab tab2;                               // Profil-Tab
    private Tab createNewEventTab;                  // Tab zum Erstellen neuer Events
    private PubSubController pubSubControl;         // zum Verwalten der Nodes
    private ListView<String> tickerListe;           // Liste für alle Events in angezeigten Liste
    private TickerEvents tickerEvents;              // Zum verwalten der Events
    private ObservableList<String> items;           // items zum füllen der Liste
    private SingleSelectionModel<Tab> selectTab;    // Ermoeglicht Selektierung einzelner Tabs
    private TabPane tabPane = new TabPane();        // TabPane, enthaelt alle Tabs
    private Event events = null;                    // Ein Event
    private UserContent userInfo;                   // Info eines Users
    private Font bold = Font.font(null, FontWeight.BOLD, 13);	//Um Label BOLD zu machen

    /**
     * Diese Methode aktualisiert alle Events im Ticker
     */
    public void refresh() {
        items = FXCollections.observableArrayList();
        tickerListe.getItems().clear();
        for (int i = 0; i < tickerEvents.eventList().size(); i++) {
            items.add(tickerEvents.eventList().get(i).getEventname());
        }
        tickerListe.setItems(items);
    }

    /**
     * Diese Methode dient zum Suchen eines Events
     * @param searchText den Eventnamen des gesuchten Events
     */
    public void search(String searchText) {
        items = FXCollections.observableArrayList();
        tickerListe.getItems().clear();
        for (int i = 0; i < tickerEvents.searchEvent(searchText).size(); i++) {
            items.add(tickerEvents.searchEvent(searchText).get(i).getEventname());
        }
        tickerListe.setItems(items);
    }

    /**
     * Diese Methode gibt die ID des ausgewählten Events zurück
     * @param auswahlText den Eventnamen des ausgewählten Events
     */
    public int auswahl(String auswahlText) {
        int id = 0;
        for (int i = 0; i < tickerEvents.eventList().size(); i++) {
            if (tickerEvents.eventList().get(i).getEventname().equals(auswahlText)) {
                id = tickerEvents.eventList().get(i).getEventID().intValue();
            }
        }
        return id - 1;
    }

    /**
     * Diese Methode öffnet einen neuen Ticker falls die maximal Zahl noch nicht erreicht wurde
     * @param events 
     */
    public void neuerTicker(Event events) {
        for (Tab opentab : tabPane.getTabs()) {
            if (opentab.getText().equals(events.getEventname())) {
                return;
            }
        }

        if (tabPane.getTabs().size() < 7) {  // Maximale Anzahl an offene Tabs (2 + 5)
            final EventTabPanel gejointerTab = new EventTabPanel(events);	//öffnet neuen Ticker
            gejointerTab.user = userName;									//Der username wird übergeben
            Tab tab = new Tab();
            tab.setText(events.getEventname());

            tab.setContent(gejointerTab);									//übergibt den Content
            tabPane.getTabs().add(tab);										//fügt den neuen Tab der TabPane hinzu
            selectTab.select(tab);											//wählt direkt den geöffneten Tab aus

            pubSubControl.nodeAbonnieren(events.getEventname(), gejointerTab);	//Aboniert die Node mit dem Namen des Events

            tab.setOnClosed(new EventHandler<javafx.event.Event>() {			//Falls der Tab geschlossen wird, muss das Event gekündigt werden
                @Override
                public void handle(javafx.event.Event e) {
                    Tab alt = (Tab) e.getSource();
                    pubSubControl.nodeKuendigen(alt.getText(), gejointerTab);
                    selectTab.select(0);										//wählt Hauptmenü aus
                }
            });
        }
    }

    /**
     * In dieser Methode werden alle Funktionen ausgeführt
     * @param pimaryStage
     */
    
    @Override
    public void start(final Stage primaryStage) {							

        pubSubControl = new PubSubController();		//Zur Verwaltung der Nodes
        userInfo = new UserContent();				//Zur Verwaltung der Userdaten
        final GridPane eventInfoFenster = new GridPane(); //Zeigt Infos zu einem Event
        final GridPane userFenster= new GridPane();	// userFenster ist das Fenster der Userdaten und zur Verwaltung dieser
        final GridPane tickerFenster = new GridPane();	//tickerFenster beinhaltet die Auswahl der Events
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("SocialTicker");

        final AnchorPane root = new AnchorPane();	
        primaryStage.setScene(new Scene(root, 480, 380));

        tabPane.setPrefSize(490, 380);
        selectTab = tabPane.getSelectionModel();	//SelectTab dient zur Auswahl von Tabs
        
        // Dient dazu, dass die ersten zwei Tabs nicht geschlossen werden können
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab,Tab oldTab, Tab newTab) {
                if (newTab.equals(tab1) || newTab.equals(tab2)) {
                    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                } 
                else {
                    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
                }
            }
        });

       
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        tickerFenster.getColumnConstraints().addAll(column1, column2);
        tickerFenster.setHgap(5); // Abstand links/rechts
        tickerFenster.setVgap(5); // Abstand oben/unten

        final HBox hbox = new HBox(); // hbox für horizontal aligment
        hbox.setSpacing(10);

        final HBox searchbox = new HBox();
        searchbox.setSpacing(10);

        
        userFenster.setHgap(10); // Abstand links/rechts
        userFenster.setVgap(10); // Abstand oben/unten

        ColumnConstraints column12 = new ColumnConstraints();
        column12.setPercentWidth(30);
        ColumnConstraints column22 = new ColumnConstraints();
        column22.setPercentWidth(40);
        ColumnConstraints column32 = new ColumnConstraints();
        column32.setPercentWidth(30);
        userFenster.getColumnConstraints().addAll(column12, column22, column32);

        //ab hier werden die Userdaten befüllt
        Label profildata = new Label("Ihre Profildaten:");
        profildata.setFont(bold);

        Label userinfo = new Label("Username:");
        userinfo.setFont(bold);
        Label username = new Label(userInfo.userInfo(userName).getUsername());

        Label userfnamet = new Label("Vorname:");
        userfnamet.setFont(bold);
        Label userfname = new Label(userInfo.userInfo(userName).getVorname());

        Label nameText = new Label("Nachname:");
        nameText.setFont(bold);
        Label nameTextField = new Label(userInfo.userInfo(userName).getName());

        Label geschlechtText = new Label("Geschlecht:");
        geschlechtText.setFont(bold);
        Label geschlecht = new Label(userInfo.userInfo(userName).getGender());

        Label gebText = new Label("Geburtsdatum:");
        gebText.setFont(bold);
        Label geb = new Label(userInfo.userInfo(userName).getGeburtsdatum().toString());

        Label landText = new Label("Land:");
        landText.setFont(bold);
        Register register = new Register();
        final ChoiceBox land = new ChoiceBox(FXCollections.observableArrayList(register.countrylist()));
        land.getSelectionModel().select(userInfo.userInfo(userName).getLand().value());

        Label stadtText = new Label("Stadt:");
        stadtText.setFont(bold);
        final TextField stadt = new TextField(userInfo.userInfo(userName).getStadt());

        Button userBearbeiten = new Button("Bearbeiten");

        userFenster.setMaxWidth(350);
        userFenster.add(profildata, 1, 1);
        userFenster.add(userinfo, 1, 2);
        userFenster.add(username, 2, 2);
        userFenster.add(userfnamet, 1, 3);
        userFenster.add(userfname, 2, 3);
        userFenster.add(nameText, 1, 4);
        userFenster.add(nameTextField, 2, 4);
        userFenster.add(geschlechtText, 1, 5);
        userFenster.add(geschlecht, 2, 5);
        userFenster.add(gebText, 1, 6);
        userFenster.add(geb, 2, 6);
        userFenster.add(landText, 1, 7);
        userFenster.add(land, 2, 7);
        userFenster.add(stadtText, 1, 8);
        userFenster.add(stadt, 2, 8);
        userFenster.add(userBearbeiten, 1, 9);

        //Falls der userBearbeiten-Button aktiviert wird
        userBearbeiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserContent userChange = new UserContent();	//neuen User Content
                userChange.userChange(userInfo.userInfo(userName), land.getSelectionModel().getSelectedItem().toString(), stadt.getText());
                userInfo = new UserContent();				//Die neuen Userdaten werden geholt 
            }
        });

        

        tab1 = new Tab();
        tab1.setText("Live Tickers");
        tab2 = new Tab();
        tab2.setText("My Profile");

        tickerListe = new ListView<String>();
        tickerEvents = new TickerEvents();
        items = FXCollections.observableArrayList();

        refresh();		//aktualisiert alle Events

        final Button joinTicker = new Button("Join");	//Zum joinen eines Tickers
        final Label welcome = new Label();				//Anzeige des Usernamen
        welcome.setText("Hello, " + userName + "!");
        welcome.setFont(bold);

        final TextField searchEvent = new TextField();	//Suchfeld
        Button searchbtn = new Button("Search");		//Such-Button	
        searchbtn.setDefaultButton(true);				//Damit er durch Enter aktiviert werden kann
        searchbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String searchText = searchEvent.getText();
                search(searchText);
            }
        });
        
        //ab hier werden die Eventinfos befüllt
        final Label beschreibung = new Label();
        final Label eventBeschreibung = new Label();
        eventBeschreibung.setWrapText(true);

        final Label eventname = new Label();
        final Label eventnametext = new Label();
        eventnametext.setWrapText(true);

        final Label eventtyp = new Label();
        final Label eventtyptext = new Label();
        eventtyptext.setWrapText(true);

        final Label eventadmin = new Label();
        final Label eventadmintext = new Label();
        eventadmintext.setWrapText(true);

        final Label eventstart = new Label();
        final Label eventstarttext = new Label();
        eventstarttext.setWrapText(true);

        final Label eventend = new Label();
        final Label eventendtext = new Label();
        eventendtext.setWrapText(true);

        final Label eventscore = new Label();
        final Label eventscoretext = new Label();

        //Falls man ein Event auswählt, mit Doppelklick kann man joinen
        //Bei einfachem Klick werden die Infos angezeigt
        tickerListe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        joinTicker.fire();		//Joint dem Ticker
                    }
                    if (mouseEvent.getClickCount() == 1) {
                    	//Die Eventinfos werden befüllt
                        eventname.setText("Name:");
                        eventname.setFont(bold);
                        beschreibung.setText("Description:");
                        beschreibung.setFont(bold);
                        eventtyp.setText("Type:");
                        eventtyp.setFont(bold);
                        eventadmin.setText("Admin:");
                        eventadmin.setFont(bold);
                        eventstart.setText("Start:");
                        eventstart.setFont(bold);
                        eventend.setText("End:");
                        eventend.setFont(bold);
                        eventscore.setText("Score:");
                        eventscore.setFont(bold);

                        String itemName = tickerListe.getSelectionModel().getSelectedItem();
                        int id = auswahl(itemName);
                        eventBeschreibung.setText(tickerEvents.eventList().get(id).getEventbeschreibung());
                        eventnametext.setText(tickerEvents.eventList().get(id).getEventname());
                        eventtyptext.setText(tickerEvents.eventList().get(id).getEventtyp());
                        eventadmintext.setText(tickerEvents.eventList().get(id).getUsername());
                        eventstarttext.setText(tickerEvents.eventList().get(id).getEventstart().toXMLFormat());
                        eventendtext.setText(tickerEvents.eventList().get(id).getEventdauer().toXMLFormat());
                        eventscoretext.setText(tickerEvents.eventList().get(id).getEventbewertung().toString());
                    }
                }
            }
        });

        //Falls man einen Ticker Joinen will
        joinTicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String itemName = tickerListe.getSelectionModel().getSelectedItem();
                int id = auswahl(itemName);
                if (tickerListe.getSelectionModel().isEmpty()) {	//Falls man nichts ausgewählt hat tritt man automatisch dem ersten bei
                    events = tickerEvents.eventList().get(0);
                } else {
                    events = tickerEvents.eventList().get(id);		//sonst dem Event mit der bestimmten id
                }
                neuerTicker(events);	//erstellt neuen Ticker Tab
            }
        });

        final Button refresh = new Button("Refresh");			//Button zum aktualisieren der EventListe
        refresh.setMinWidth(50);
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refresh();
            }
        });

        final Button create = new Button("Create Ticker");		//Button zum erstellen von neuen Events
        create.setMinWidth(50);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                final GridPane neuesEventFenster = new GridPane(); // Grid fuer neuen ticker
                neuesEventFenster.setHgap(5); // Abstand links/rechts
                neuesEventFenster.setVgap(5); // Abstand oben/unten
                createNewEventTab = new Tab();

                ColumnConstraints column1 = new ColumnConstraints();
                column1.setPercentWidth(30);
                ColumnConstraints column2 = new ColumnConstraints();
                column2.setPercentWidth(40);
                ColumnConstraints column3 = new ColumnConstraints();
                column3.setPercentWidth(30);
                neuesEventFenster.getColumnConstraints().addAll(column1, column2, column3);

                final Label errmessage = new Label();	
                final Label eventname = new Label("Event name:");
                final TextField eventnametextField = new TextField();
                final Label eventbeschreibung = new Label("Event description:");
                final TextField eventbeschreibungField1 = new TextField();
                final Label eventtyp = new Label("Event type:");
                final ChoiceBox typchoice = new ChoiceBox(FXCollections.observableArrayList("Football","American Football"
                		, "Baseball", "Handball", "Running", "Boxing", "Tennis", "Table Tennis", "Golf", "Volleyball"
                		, "Basketball", "Formula 1", "Others"));
                typchoice.getSelectionModel().selectFirst();
                final Label start = new Label("Event start:");
                final TextField startField = new TextField();
                startField.setPromptText("HH:MM");

                final Label ende = new Label("Event end:");
                final TextField endeField = new TextField();
                endeField.setPromptText("HH:MM");

                Button createbtn = new Button();	//Button zum erstellen
                createbtn.setText("Create");

                
                create.setDisable(true);
                createNewEventTab.setText("New Ticker");
                selectTab.select(createNewEventTab);  //wählt den Tab aus zum erstellen

                //Falls man erstellt werden alle Daten übergeben zum erstellen eines neuen Events
                createbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TickerEvents make = new TickerEvents();
                        errmessage.setText("");

                        Map<String, String> eventdata = new HashMap<String, String>();
                        eventdata.put("admin", userName);
                        eventdata.put("name", eventnametextField.getText());
                        eventdata.put("beschr",eventbeschreibungField1.getText());
                        eventdata.put("typ", typchoice.getSelectionModel().getSelectedItem().toString());
                        eventdata.put("start", startField.getText() + ":00");
                        eventdata.put("ende", endeField.getText() + ":00");
                        eventdata.put("bewertung", new Integer(5).toString());

                        make.createEvent(eventdata);	//übergibt Map damit das Event angelegt wird

                        refresh();						//aktualisiert direkt die Liste
                        
                        pubSubControl.nodeErstellen(eventnametextField.getText()); // erstellt neue Node
                        
                        tabPane.getTabs().remove(createNewEventTab);	//entfernt den Tab
                        create.setDisable(false);
                        events = tickerEvents.eventList().get(tickerListe.getItems().size() - 1);
                        neuerTicker(events);			//joint direkt dem erstellten Event
                    }
                });

                createNewEventTab.setOnClosed(new EventHandler<javafx.event.Event>() {
                    @Override
                    public void handle(javafx.event.Event e) {
                        create.setDisable(false);		//neue Events sind wieder erstellbar falls man createTab schließt
                    }
                });
                
                neuesEventFenster.add(eventname, 1, 0);
                neuesEventFenster.add(eventnametextField, 1, 1);
                neuesEventFenster.add(eventbeschreibung, 1, 2);
                neuesEventFenster.add(eventbeschreibungField1, 1, 3);
                neuesEventFenster.add(eventtyp, 1, 4);
                neuesEventFenster.add(typchoice, 1, 5);
                neuesEventFenster.add(start, 1, 6);
                neuesEventFenster.add(startField, 1, 7);
                neuesEventFenster.add(ende, 1, 8);
                neuesEventFenster.add(endeField, 1, 9);
                neuesEventFenster.add(createbtn, 1, 10);

                createNewEventTab.setContent(neuesEventFenster);
                tabPane.getTabs().add(createNewEventTab);
            }
        });

        Label tickertext = new Label("Choose the ticker you'd like to join");

        //Fenster werden befuellt
        hbox.getChildren().addAll(joinTicker, refresh);
        tickerFenster.add(tickertext, 0, 0);
        tickerFenster.add(tickerListe, 0, 1);
        tickerFenster.add(welcome, 1, 0);
        tickerFenster.add(create, 1, 2);
        tickerFenster.add(hbox, 0, 2);
        tickerFenster.add(eventInfoFenster, 1, 1);

        searchbox.getChildren().addAll(searchEvent, searchbtn);
        eventInfoFenster.add(searchbox, 0, 1);
        eventInfoFenster.add(eventname, 0, 3);
        eventInfoFenster.add(eventnametext, 0, 4);
        eventInfoFenster.add(beschreibung, 0, 5);
        eventInfoFenster.add(eventBeschreibung, 0, 6);
        eventInfoFenster.add(eventtyp, 0, 7);
        eventInfoFenster.add(eventtyptext, 0, 8);
        eventInfoFenster.add(eventadmin, 0, 9);
        eventInfoFenster.add(eventadmintext, 0, 10);
        eventInfoFenster.add(eventstart, 0, 11);
        eventInfoFenster.add(eventstarttext, 0, 12);
        eventInfoFenster.add(eventend, 0, 13);
        eventInfoFenster.add(eventendtext, 0, 14);
        eventInfoFenster.add(eventscore, 0, 15);
        eventInfoFenster.add(eventscoretext, 0, 16);

        tab1.setContent(tickerFenster);
        tab2.setContent(userFenster);

        tabPane.getTabs().addAll(tab1, tab2);
        root.getChildren().add(tabPane);

        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}