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


public class RegisterNewWindow extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        
        final Label errmessage = new Label();
        errmessage.setLayoutX(50);
        errmessage.setLayoutY(325);
        
        Label name = new Label("Username:");
        name.setLayoutX(50);
        name.setLayoutY(31);
        
        final TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(47);
        textField.setMinWidth(200);
        
        Label fname = new Label("First name:");
        fname.setLayoutX(50);
        fname.setLayoutY(73);
        
        final TextField fnameField1 = new TextField();
        fnameField1.setLayoutX(50);
        fnameField1.setLayoutY(89);
        fnameField1.setMinWidth(200);
        
        Label lname = new Label("Last name:");
        lname.setLayoutX(50);
        lname.setLayoutY(115);
        
        final TextField lnameField1 = new TextField();
        lnameField1.setLayoutX(50);
        lnameField1.setLayoutY(131);
        lnameField1.setMinWidth(200);
        
        Label gname = new Label("Gender:");
        gname.setLayoutX(50);
        gname.setLayoutY(157);
        
        final ToggleGroup ggroup = new ToggleGroup();
        final RadioButton gbm = new RadioButton("Male");
        gbm.setToggleGroup(ggroup);
        gbm.setSelected(true);
        gbm.setLayoutX(70);
        gbm.setLayoutY(173);

        final RadioButton gbf = new RadioButton("Female");
        gbf.setToggleGroup(ggroup);
        gbf.setLayoutX(155);
        gbf.setLayoutY(173);
        
        Label bday = new Label("Birthday:");
        bday.setLayoutX(50);
        bday.setLayoutY(199);
        
        final TextField bdayField = new TextField();
        bdayField.setLayoutX(50);
        bdayField.setLayoutY(215);
        bdayField.setMinWidth(200);
        
        Label country = new Label("Country:");
        country.setLayoutX(50);
        country.setLayoutY(241);
        
        Register register = new Register();
        final ChoiceBox countryc = new ChoiceBox(FXCollections.observableArrayList(register.countrylist()));
        countryc.setLayoutX(122);
        countryc.setLayoutY(257);
        
        Label city = new Label("City:");
        city.setLayoutX(50);
        city.setLayoutY(283);
        
        final TextField cityField = new TextField();
        cityField.setLayoutX(50);
        cityField.setLayoutY(299);
        cityField.setMinWidth(200);
        
        Button backbtn = new Button();
        backbtn.setLayoutX(122);
        backbtn.setLayoutY(371);
        backbtn.setText("Back");
        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginWindow backlogin = new LoginWindow();
                backlogin.start(primaryStage);
            }
        });
        
        Button btn = new Button();
        btn.setLayoutX(122);
        btn.setLayoutY(345);
        btn.setText("Register");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Register register = new Register();
               errmessage.setText("");
               if (register.registered(textField.getText())) {
                   errmessage.setTextFill(Color.rgb(210, 39, 30));
                   errmessage.setText("Sorry, username already taken or invalid!");
               }else{
                  //errmessage.setText("");
                  System.out.println("Registriert!");
                  Map<String, String> userdata = new HashMap<String, String>();
                  userdata.put("name", textField.getText());
                  userdata.put("fname", fnameField1.getText());
                  userdata.put("lname", lnameField1.getText());
                  userdata.put("city", cityField.getText());
                  if (gbm.isSelected()){
                      userdata.put("gender", "Male");
                  } else {
                      userdata.put("gender", "Female");
                  }
                  userdata.put("bday", bdayField.getText());
                  userdata.put("country", countryc.getValue().toString());;
                  register.createUser(userdata);
               }
            }
        });
       
        AnchorPane root = new AnchorPane();
        root.getChildren().add(btn);
        root.getChildren().add(name);
        root.getChildren().add(fname);
        root.getChildren().add(lname);
        root.getChildren().add(bday);
        root.getChildren().add(textField);
        root.getChildren().add(fnameField1);
        root.getChildren().add(lnameField1);
        root.getChildren().add(bdayField);
        root.getChildren().add(errmessage);
        root.getChildren().add(gname);
        root.getChildren().add(gbm);
        root.getChildren().add(gbf);
        root.getChildren().add(country);
        root.getChildren().add(countryc);
        root.getChildren().add(city);
        root.getChildren().add(cityField);
        root.getChildren().add(backbtn);
        
        Scene scene = new Scene(root, 300, 400);
        
        primaryStage.setTitle("SocialTicker | Register");
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