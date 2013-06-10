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
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SocialMain extends Application {
    private Tab tab1;
    private Tab tab2;
    private Tab k;
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("SocialTicker");
        
        AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root, 450, 380));
        final TabPane tabPane = new TabPane();
        tabPane.setPrefSize(460, 380);
        tabPane.setSide(Side.TOP);
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
        geoGrid.setHgap(5); // Abstand links/rechts
        geoGrid.setVgap(5); // Abstand oben/unten
        final GridPane geoGrid2 = new GridPane();
        geoGrid2.setHgap(5); // Abstand links/rechts
        geoGrid2.setVgap(5); // Abstand oben/unten
        
        tab1 = new Tab();
        tab1.setText("Live Tickers");
        
        tab2 = new Tab();
        tab2.setText("My Profile");
        
        final ListView<String> ticklist = new ListView<String>();
        TickerEvents tevents = new TickerEvents();
        ObservableList<String> items = FXCollections.observableArrayList(tevents.eventList());
        ticklist.setItems(items);
        
        Button joinTicker = new Button("Join");
        joinTicker.setMinWidth(50);
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
                	final GridPane geoGridk = new GridPane();
                	geoGridk.setHgap(5); // Abstand links/rechts
                	geoGridk.setVgap(5); // Abstand oben/unten
                	
                	Button sendchat = new Button("Send");
                	sendchat.setMinWidth(50);
                	sendchat.setOnAction(new EventHandler<ActionEvent>() {
                		@Override
                		public void handle(ActionEvent event) {
                		}
                	});
                 
                	geoGridk.add(sendchat, 12, 12);
                	k.setContent(geoGridk);
                 
                	i++;
               }
            }
        });
        
        Button create = new Button("Create Ticker");
        create.setMinWidth(50);
        create.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
                NewTickerWindow newTicker = new NewTickerWindow();
                newTicker.start(primaryStage); //oeffnet neue stage
            }
        });
        
        Button exitmain = new Button("Exit");
        exitmain.setMinWidth(50);
        Label tickertext = new Label("Choose the ticker you'd like to join");
        
      
        Button test = new Button("Create Ticker");
        create.setMinWidth(50);
        
        
        geoGrid.add(tickertext, 1, 1);
        geoGrid.add(ticklist, 1, 2);
        geoGrid.add(joinTicker, 1, 3);
        geoGrid.add(create, 2, 3);
        geoGrid.add(exitmain, 12, 3);
        geoGrid2.add(test, 1, 1);
        
   
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
