package gui;


import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SocialMain extends Application {
    private Tab tab1;
    private Tab tab2;
    private Tab createNewEventTab;
    private Tab k;
    private Tab newEvent;
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("SocialTicker");
        
        AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root, 480, 380));
        final TabPane tabPane = new TabPane();
        tabPane.setPrefSize(490, 380);
        final SingleSelectionModel<Tab> selectTab = tabPane.getSelectionModel();
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
          @Override public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) {
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
        
        HBox hbox = new HBox(); //hbox für horizontal aligment
        hbox.setSpacing(10);
        
        final GridPane geoGrid2 = new GridPane();
        geoGrid2.setHgap(5); // Abstand links/rechts
        geoGrid2.setVgap(5); // Abstand oben/unten
        
        final GridPane geoGrid3 = new GridPane();
        
        tab1 = new Tab();
        tab1.setText("Live Tickers");
        
        tab2 = new Tab();
        tab2.setText("My Profile");
        
        final ListView<String> ticklist = new ListView<String>();
        final TickerEvents tevents = new TickerEvents();
        ObservableList<String> items = FXCollections.observableArrayList();
         for (int i = 0; i < tevents.eventList().size(); i++)
	    {
         items.add(tevents.eventList().get(i).getEventname());
            }
         ticklist.setItems(items);
        final Button joinTicker = new Button("Join");
        joinTicker.setMinWidth(50);
        
        final Label beschreibung = new Label();
        final Label eventBeschreibung = new Label();
        eventBeschreibung.setWrapText(true);
        
        
        ticklist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        joinTicker.fire();
                    }
                    if (mouseEvent.getClickCount() == 1) {
                            beschreibung.setText("Beschreibung:");
                            int index = ticklist.getSelectionModel().getSelectedIndex();
                         eventBeschreibung.setText(tevents.eventList().get(index).getEventbeschreibung());
                    }
                }
            }
        });
        
        joinTicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Tab opentab:tabPane.getTabs() ) {
                   if (opentab.getText().equals(ticklist.getSelectionModel().getSelectedItem())){
                      return;
                   }
                }
                
                if (tabPane.getTabs().size() < 6 && !ticklist.getSelectionModel().isEmpty()) {
                	int i = tabPane.getTabs().size();
                	k = new Tab();
                	k.setText(ticklist.getSelectionModel().getSelectedItem());
                	tabPane.getTabs().add(i, k);
                        selectTab.select(k);
                	final GridPane geoGridk = new GridPane();
                	geoGridk.setHgap(5); // Abstand links/rechts
                	geoGridk.setVgap(5); // Abstand oben/unten
                	
                	ColumnConstraints column1 = new ColumnConstraints();
                        column1.setPercentWidth(85);
                        ColumnConstraints column2 = new ColumnConstraints();
                        column2.setPercentWidth(15);
                        RowConstraints row1 = new RowConstraints();
                        row1.setPercentHeight(92);
                        RowConstraints row2 = new RowConstraints();
                        row2.setPercentHeight(8);
                        geoGridk.getColumnConstraints().addAll(column1, column2);
                        geoGridk.getRowConstraints().addAll(row1, row2);
                	
                        SplitPane sp = new SplitPane();
                        final StackPane sp1 = new StackPane();
                        
                        
                        final StackPane sp2 = new StackPane();
                        final TextArea comments = new TextArea();
                        sp2.getChildren().add(comments);
                        sp.getItems().addAll(sp1, sp2);
                        
                        
                    final ListView<String> liveticks = new ListView<String>();
                    final TickerContent cevents = new TickerContent();
                    ObservableList<String> items = FXCollections.observableArrayList();
                    final int index2 = ticklist.getSelectionModel().getSelectedIndex() + 1;
                    for (int f = 0; f < cevents.contentList(index2).getTickerBeitrag().size(); f++) {
                        
                        items.add(cevents.contentList(index2).getTickerBeitrag().get(f).getZeit()+": "+cevents.contentList(index2).getTickerBeitrag().get(f).getText());
                        liveticks.setItems(items);
                    
                    liveticks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 1) {
                        final int index3 = liveticks.getSelectionModel().getSelectedIndex();
                        comments.clear();
                        for (int h = 0; h < cevents.contentList(index2).getTickerBeitrag().get(index3).getKommentar().size(); h++) {
                   
                   comments.appendText(cevents.contentList(index2).getTickerBeitrag().get(index3).getKommentar().get(h).getKommentarUser()+"\n");
                   comments.appendText(cevents.contentList(index2).getTickerBeitrag().get(index3).getKommentar().get(h).getKommentarText()+"\n");
                   
                        }
                    }
                    }
                  }
              });
            }      
                sp1.getChildren().add(liveticks);

                    
                        TextField chatText = new TextField();
                        
                        Button sendchat = new Button("Send");
                	sendchat.setMinWidth(50);
                        
                        geoGridk.add(sendchat, 1, 1);
                        geoGridk.add(chatText, 0, 1);
                        
                        geoGridk.add(sp, 0, 0);
                	
                	k.setContent(geoGridk);
                	i++;
               }
            }
        });
        
       
        final Button create = new Button("Create Ticker");
        create.setMinWidth(50);
        
        Button refresh = new Button("Refresh");
        refresh.setMinWidth(50);
        
        create.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
                        createNewEventTab = new Tab();
                        createNewEventTab.setText("New Ticker");
                        selectTab.select(createNewEventTab);
                        create.setDisable(true);
                        
                        createNewEventTab.setOnClosed(new EventHandler<javafx.event.Event>() {
                            
                            public void handle(javafx.event.Event t) {
                                 create.setDisable(false);
                                 selectTab.select(tab1);
                            }
                        });
                                
                	final GridPane geoGridNew = new GridPane(); //Grid fuer new ticker
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
                        
                        Label eventname = new Label("Event name:");
                        final TextField eventnametextField = new TextField();
                        
                        Label eventbeschreibung = new Label("Event description:");
                        final TextField eventbeschreibungField1 = new TextField();
                        
                        Label eventtyp = new Label("Event type:");
                        final ChoiceBox typchoice = new ChoiceBox(FXCollections.observableArrayList("Football", "Baseball", "Basketball", "Formula 1"));
                        typchoice.getSelectionModel().selectFirst();
                        
                        Label start = new Label("Event start:");
                        final TextField startField = new TextField();
                        
                        Label ende = new Label("Event end:");
                        final TextField endeField = new TextField();
                        
                        
        
        Button createbtn = new Button();
        createbtn.setText("Create");
        createbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               TickerEvents make = new TickerEvents();
               errmessage.setText("");
               
                 
                  Map<String, String> eventdata = new HashMap<String, String>();
                eventdata.put("name", eventnametextField.getText());
                eventdata.put("beschr", eventbeschreibungField1.getText());
                eventdata.put("typ", typchoice.getSelectionModel().getSelectedItem().toString());
                eventdata.put("start", startField.getText() + ":00");
                eventdata.put("ende", endeField.getText() + ":00");


                make.createEvent(eventdata);
                
                	createNewEventTab.setText(eventnametextField.getText());
                	//tabPane.getTabs().add(newEvent);
                        //selectTab.select(newEvent);
                	final GridPane geoGridk = new GridPane();
                	geoGridk.setHgap(5); // Abstand links/rechts
                	geoGridk.setVgap(5); // Abstand oben/unten
                	
                	ColumnConstraints column1 = new ColumnConstraints();
                        column1.setPercentWidth(85);
                        ColumnConstraints column2 = new ColumnConstraints();
                        column2.setPercentWidth(15);
                        RowConstraints row1 = new RowConstraints();
                        row1.setPercentHeight(92);
                        RowConstraints row2 = new RowConstraints();
                        row2.setPercentHeight(8);
                        geoGridk.getColumnConstraints().addAll(column1, column2);
                        geoGridk.getRowConstraints().addAll(row1, row2);
                	
                        SplitPane sp = new SplitPane();
                        final StackPane sp1 = new StackPane();
                        final ListView<String> liveticks = new ListView<String>();
                        sp1.getChildren().add(liveticks);
                        final StackPane sp2 = new StackPane();
                        TextArea comments = new TextArea();
                        sp2.getChildren().add(comments);
                        sp.getItems().addAll(sp1, sp2);
                        
                        
                        TextField chatText = new TextField();
                        
                        Button sendchat = new Button("Send");
                	sendchat.setMinWidth(50);
                        
                        geoGridk.add(sendchat, 1, 1);
                        geoGridk.add(chatText, 0, 1);
                        
                        geoGridk.add(sp, 0, 0);
                	
                	createNewEventTab.setContent(geoGridk);
                
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
       
      
        Button test = new Button("Create Ticker");
        
        
        hbox.getChildren().addAll(joinTicker, refresh);
        geoGrid.add(tickertext, 0, 0);
        geoGrid.add(ticklist, 0, 1);
        geoGrid.add(create, 1, 2);
        geoGrid.add(hbox, 0, 2);
        geoGrid3.add(beschreibung, 0, 0);
        geoGrid3.add(eventBeschreibung, 0, 1);
        geoGrid.add(geoGrid3, 1, 1);
        geoGrid2.add(test, 0, 0);
        
   
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
