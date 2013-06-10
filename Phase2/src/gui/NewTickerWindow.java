package gui;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NewTickerWindow extends Application {
	
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        
        final Label errmessage = new Label();
        errmessage.setLayoutX(50);
        errmessage.setLayoutY(325);
        
        Label eventname = new Label("Eventname:");
        eventname.setLayoutX(50);
        eventname.setLayoutY(31);
        
        final TextField eventnametextField = new TextField();
        eventnametextField.setLayoutX(50);
        eventnametextField.setLayoutY(47);
        eventnametextField.setMinWidth(200);
        
        Label eventbeschreibung = new Label("Eventbeschreibung:");
        eventbeschreibung.setLayoutX(50);
        eventbeschreibung.setLayoutY(73);
        
        final TextField eventbeschreibungField1 = new TextField();
        eventbeschreibungField1.setLayoutX(50);
        eventbeschreibungField1.setLayoutY(89);
        eventbeschreibungField1.setMinWidth(200);
        
        Label eventtyp = new Label("Eventtyp:");
        eventtyp.setLayoutX(50);
        eventtyp.setLayoutY(115);
        
        final TextField eventtypField1 = new TextField();
        eventtypField1.setLayoutX(50);
        eventtypField1.setLayoutY(131);
        eventtypField1.setMinWidth(200);
        
        Label start = new Label("Startuhrzeit:");
        start.setLayoutX(50);
        start.setLayoutY(157);
        
        final TextField startField = new TextField();
        startField.setLayoutX(50);
        startField.setLayoutY(173);
        startField.setMinWidth(200);
        
        Label ende = new Label("Enduhrzeit:");
        ende.setLayoutX(50);
        ende.setLayoutY(199);
        
        final TextField endeField = new TextField();
        endeField.setLayoutX(50);
        endeField.setLayoutY(215);
        endeField.setMinWidth(200);
        
        
        
        Button backbtn = new Button();
        backbtn.setLayoutX(122);
        backbtn.setLayoutY(371);
        backbtn.setText("Back");
        
        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SocialMain main = new SocialMain();
                main.start(primaryStage);
            }
        });
        
        Button btn = new Button();
        btn.setLayoutX(122);
        btn.setLayoutY(345);
        btn.setText("Create");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               TickerEvents create = new TickerEvents();
               errmessage.setText("");
               
                  //errmessage.setText("");
                 
                  Map<String, String> eventdata = new HashMap();
                  eventdata.put("name", eventnametextField.getText());
                  eventdata.put("beschr", eventbeschreibungField1.getText());
                  eventdata.put("typ", eventtypField1.getText());
                  eventdata.put("start", startField.getText());
                  eventdata.put("ende", endeField.getText());
                  
                  System.out.println("Erstellt!");
                  create.newEvent(eventdata);
               
            }
        });
       
        AnchorPane root = new AnchorPane();
        root.getChildren().add(btn);
        root.getChildren().add(errmessage);
        root.getChildren().add(backbtn);
        root.getChildren().add(eventname);
        root.getChildren().add(eventnametextField);
        root.getChildren().add(eventbeschreibung);
        root.getChildren().add(eventbeschreibungField1);
        root.getChildren().add(eventtyp);
        root.getChildren().add(eventtypField1);
        root.getChildren().add(start);
        root.getChildren().add(startField);
        root.getChildren().add(ende);
        root.getChildren().add(endeField);
        
        Scene scene = new Scene(root, 300, 400);
        
        primaryStage.setTitle("SocialTicker | Create New Ticker");
        primaryStage.setScene(scene);
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
