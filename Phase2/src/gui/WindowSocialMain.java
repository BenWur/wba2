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
import javafx.stage.Stage;

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

    public void refresh() {
        items = FXCollections.observableArrayList();
        tickerListe.getItems().clear();
        for (int i = 0; i < tickerEvents.eventList().size(); i++) {
            items.add(tickerEvents.eventList().get(i).getEventname());
        }
        tickerListe.setItems(items);
    }

    public void search(String searchText) {
        items = FXCollections.observableArrayList();
        tickerListe.getItems().clear();
        for (int i = 0; i < tickerEvents.searchEvent(searchText).size(); i++) {
            items.add(tickerEvents.searchEvent(searchText).get(i).getEventname());
        }
        tickerListe.setItems(items);
    }

    public int auswahl(String auswahlText) {
        int id = 0;
        for (int i = 0; i < tickerEvents.eventList().size(); i++) {
            if (tickerEvents.eventList().get(i).getEventname().equals(auswahlText)) {
                id = tickerEvents.eventList().get(i).getEventID().intValue();
            }
        }
        return id - 1;
    }

    public void neuerTicker(Event events) {
        for (Tab opentab : tabPane.getTabs()) {
            if (opentab.getText().equals(events.getEventname())) {
                return;
            }
        }

        if (tabPane.getTabs().size() < 7) {  // Maximale Anzahl an offene Tabs (2 + 5)
            final EventTabPanel gejointerTab = new EventTabPanel(events);
            gejointerTab.user = userName;
            Tab tab = new Tab();
            tab.setText(events.getEventname());

            tab.setContent(gejointerTab);
            tabPane.getTabs().add(tab);
            selectTab.select(tab);

            pubSubControl.nodeAbonnieren(events.getEventname(), gejointerTab);
            System.out.println(events.getEventname());

            tab.setOnClosed(new EventHandler<javafx.event.Event>() {
                @Override
                public void handle(javafx.event.Event e) {
                    Tab alt = (Tab) e.getSource();
                    pubSubControl.nodeKuendigen(alt.getText(), gejointerTab);
                    selectTab.select(0);
                }
            });
        }
    }

    @Override
    public void start(final Stage primaryStage) {

        pubSubControl = new PubSubController();

        primaryStage.setResizable(false);
        primaryStage.setTitle("SocialTicker");

        final AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root, 480, 380));

        tabPane.setPrefSize(490, 380);
        selectTab = tabPane.getSelectionModel();
        tabPane.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab,
                    Tab oldTab, Tab newTab) {
                if (newTab.equals(tab1) || newTab.equals(tab2)) {
                    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                } else {
                    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
                }
            }
        });

        final GridPane geoGrid = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        geoGrid.getColumnConstraints().addAll(column1, column2);
        geoGrid.setHgap(5); // Abstand links/rechts
        geoGrid.setVgap(5); // Abstand oben/unten

        final HBox hbox = new HBox(); // hbox für horizontal aligment
        hbox.setSpacing(10);

        final HBox searchbox = new HBox();
        searchbox.setSpacing(10);

        final GridPane geoGrid2 = new GridPane();
        geoGrid2.setHgap(10); // Abstand links/rechts
        geoGrid2.setVgap(10); // Abstand oben/unten

        ColumnConstraints column12 = new ColumnConstraints();
        column12.setPercentWidth(30);
        ColumnConstraints column22 = new ColumnConstraints();
        column22.setPercentWidth(40);
        ColumnConstraints column32 = new ColumnConstraints();
        column32.setPercentWidth(30);
        geoGrid2.getColumnConstraints().addAll(column12, column22, column32);

        userInfo = new UserContent();
        Label profildata = new Label("Ihre Profildaten:");

        Label userinfo = new Label("Username:");
        Label username = new Label(userInfo.userInfo(userName).getUsername());

        Label userfnamet = new Label("Vorname:");
        Label userfname = new Label(userInfo.userInfo(userName).getVorname());

        Label nameText = new Label("Nachname:");
        Label nameTextField = new Label(userInfo.userInfo(userName).getName());

        Label geschlechtText = new Label("Geschlecht:");
        Label geschlecht = new Label(userInfo.userInfo(userName).getGender());

        Label gebText = new Label("Geburtsdatum:");
        Label geb = new Label(userInfo.userInfo(userName).getGeburtsdatum().toString());

        Label landText = new Label("Land:");
        Register register = new Register();
        final ChoiceBox land = new ChoiceBox(FXCollections.observableArrayList(register.countrylist()));
        land.getSelectionModel().select(userInfo.userInfo(userName).getLand().value());

        Label stadtText = new Label("Stadt:");
        final TextField stadt = new TextField(userInfo.userInfo(userName).getStadt());

        Button userBearbeiten = new Button("Bearbeiten");

        geoGrid2.setMaxWidth(350);
        geoGrid2.add(profildata, 1, 1);
        geoGrid2.add(userinfo, 1, 2);
        geoGrid2.add(username, 2, 2);
        geoGrid2.add(userfnamet, 1, 3);
        geoGrid2.add(userfname, 2, 3);
        geoGrid2.add(nameText, 1, 4);
        geoGrid2.add(nameTextField, 2, 4);
        geoGrid2.add(geschlechtText, 1, 5);
        geoGrid2.add(geschlecht, 2, 5);
        geoGrid2.add(gebText, 1, 6);
        geoGrid2.add(geb, 2, 6);
        geoGrid2.add(landText, 1, 7);
        geoGrid2.add(land, 2, 7);
        geoGrid2.add(stadtText, 1, 8);
        geoGrid2.add(stadt, 2, 8);
        geoGrid2.add(userBearbeiten, 1, 9);

        userBearbeiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserContent userChange = new UserContent();
                userChange.userChange(userInfo.userInfo(userName), land.getSelectionModel().getSelectedItem().toString(), stadt.getText());
                userInfo = new UserContent();
            }
        });

        final GridPane geoGrid3 = new GridPane();

        tab1 = new Tab();
        tab1.setText("Live Tickers");

        tab2 = new Tab();
        tab2.setText("My Profile");

        tickerListe = new ListView<String>();
        tickerEvents = new TickerEvents();
        items = FXCollections.observableArrayList();

        refresh();

        final Button joinTicker = new Button("Join");

        final Label welcome = new Label();
        welcome.setText("Hello, " + userName + "!");

        final TextField searchEvent = new TextField();

        Button searchbtn = new Button("Search");
        searchbtn.setDefaultButton(true);
        searchbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String searchText = searchEvent.getText();
                search(searchText);
            }
        });

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

        tickerListe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        joinTicker.fire();
                    }
                    if (mouseEvent.getClickCount() == 1) {
                        eventname.setText("Name:");
                        beschreibung.setText("Description:");
                        eventtyp.setText("Type:");
                        eventadmin.setText("Admin:");
                        eventstart.setText("Start:");
                        eventend.setText("End:");
                        eventscore.setText("Score:");

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

/////////////////////////////////////

        joinTicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String itemName = tickerListe.getSelectionModel().getSelectedItem();
                int id = auswahl(itemName);
                if (tickerListe.getSelectionModel().isEmpty()) {
                    events = tickerEvents.eventList().get(0);
                } else {
                    events = tickerEvents.eventList().get(id);
                }
                neuerTicker(events);
            }
        });

        final Button create = new Button("Create Ticker");
        create.setMinWidth(50);

        final Button refresh = new Button("Refresh");
        refresh.setMinWidth(50);
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refresh();
            }
        });

/////////////////

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                final GridPane geoGridNew = new GridPane(); // Grid fuer new ticker
                geoGridNew.setHgap(5); // Abstand links/rechts
                geoGridNew.setVgap(5); // Abstand oben/unten

                ColumnConstraints column1 = new ColumnConstraints();
                column1.setPercentWidth(30);
                ColumnConstraints column2 = new ColumnConstraints();
                column2.setPercentWidth(40);
                ColumnConstraints column3 = new ColumnConstraints();
                column3.setPercentWidth(30);
                geoGridNew.getColumnConstraints().addAll(column1, column2, column3);

                final Label errmessage = new Label();

                final Label eventname = new Label("Event name:");
                final TextField eventnametextField = new TextField();

                final Label eventbeschreibung = new Label("Event description:");
                final TextField eventbeschreibungField1 = new TextField();

                final Label eventtyp = new Label("Event type:");
                final ChoiceBox typchoice = new ChoiceBox(FXCollections.observableArrayList("Football", "Baseball",
                        "Basketball", "Formula 1"));
                typchoice.getSelectionModel().selectFirst();

                final Label start = new Label("Event start:");
                final TextField startField = new TextField();
                startField.setPromptText("HH:MM");

                final Label ende = new Label("Event end:");
                final TextField endeField = new TextField();
                endeField.setPromptText("HH:MM");

                Button createbtn = new Button();
                createbtn.setText("Create");

                createNewEventTab = new Tab();
                create.setDisable(true);
                createNewEventTab.setText("New Ticker");
                selectTab.select(createNewEventTab);

                createbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TickerEvents make = new TickerEvents();
                        errmessage.setText("");

                        Map<String, String> eventdata = new HashMap<String, String>();
                        eventdata.put("admin", userName);
                        eventdata.put("name", eventnametextField.getText());
                        eventdata.put("beschr",
                                eventbeschreibungField1.getText());
                        eventdata.put("typ", typchoice.getSelectionModel()
                                .getSelectedItem().toString());
                        eventdata.put("start", startField.getText() + ":00");
                        eventdata.put("ende", endeField.getText() + ":00");
                        eventdata.put("bewertung", new Integer(5).toString());

                        make.createEvent(eventdata);

                        refresh();
                        pubSubControl.nodeErstellen(eventnametextField.getText()); // erstellt neue Node
                        tabPane.getTabs().remove(createNewEventTab);
                        create.setDisable(false);
                        events = tickerEvents.eventList().get(tickerListe.getItems().size() - 1);
                        neuerTicker(events);
                    }
                });

                createNewEventTab.setOnClosed(new EventHandler<javafx.event.Event>() {
                    @Override
                    public void handle(javafx.event.Event e) {
                        create.setDisable(false);
                    }
                });

                geoGridNew.add(eventname, 1, 0);
                geoGridNew.add(eventnametextField, 1, 1);
                geoGridNew.add(eventbeschreibung, 1, 2);
                geoGridNew.add(eventbeschreibungField1, 1, 3);
                geoGridNew.add(eventtyp, 1, 4);
                geoGridNew.add(typchoice, 1, 5);
                geoGridNew.add(start, 1, 6);
                geoGridNew.add(startField, 1, 7);
                geoGridNew.add(ende, 1, 8);
                geoGridNew.add(endeField, 1, 9);
                geoGridNew.add(createbtn, 1, 10);

                createNewEventTab.setContent(geoGridNew);
                tabPane.getTabs().add(createNewEventTab);
            }
        });

        Label tickertext = new Label("Choose the ticker you'd like to join");

        hbox.getChildren().addAll(joinTicker, refresh);
        geoGrid.add(tickertext, 0, 0);
        geoGrid.add(tickerListe, 0, 1);
        geoGrid.add(welcome, 1, 0);
        geoGrid.add(create, 1, 2);
        geoGrid.add(hbox, 0, 2);
        geoGrid.add(geoGrid3, 1, 1);

        searchbox.getChildren().addAll(searchEvent, searchbtn);
        geoGrid3.add(searchbox, 0, 1);
        geoGrid3.add(eventname, 0, 3);
        geoGrid3.add(eventnametext, 0, 4);
        geoGrid3.add(beschreibung, 0, 5);
        geoGrid3.add(eventBeschreibung, 0, 6);
        geoGrid3.add(eventtyp, 0, 7);
        geoGrid3.add(eventtyptext, 0, 8);
        geoGrid3.add(eventadmin, 0, 9);
        geoGrid3.add(eventadmintext, 0, 10);
        geoGrid3.add(eventstart, 0, 11);
        geoGrid3.add(eventstarttext, 0, 12);
        geoGrid3.add(eventend, 0, 13);
        geoGrid3.add(eventendtext, 0, 14);
        geoGrid3.add(eventscore, 0, 15);
        geoGrid3.add(eventscoretext, 0, 16);

        tab1.setContent(geoGrid);
        tab2.setContent(geoGrid2);

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