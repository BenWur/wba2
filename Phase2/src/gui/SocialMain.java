package minirestwebservice;


import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class SocialMain extends Application {
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("SocialTicker");
        
        AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root));
        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(480, 320);
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        final Tab tab1 = new Tab();
        tab1.setText("Live Tickers");
        final Tab tab2 = new Tab();
        tab2.setText("My Profile");
        Button create = new Button("heyhp");
        create.setMinWidth(50);
        Button create2 = new Button("heyhp");
        create2.setMinWidth(50);
        Button create3 = new Button("heyhp");
        create3.setMinWidth(50);
        Button create4 = new Button("heyhp");
        create4.setMinWidth(50);
        Label ticker = new Label("Choose the ticker you'd like to follow");
        ListView ticklist = new ListView();
        
        final GridPane geoGrid = new GridPane();
        geoGrid.setHgap(5); // Abstand links/rechts
        geoGrid.setVgap(5); // Abstand oben/unten
        
        
        geoGrid.add(ticker, 1, 1);
        geoGrid.add(ticklist, 1, 2);
         geoGrid.add(create2, 2, 2);
          geoGrid.add(create3, 3, 3);
          geoGrid.add(create4, 4, 4);
        
        tab2.setContent(geoGrid);

        
        //tab2.setContent(create);
        
        
        
        
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
