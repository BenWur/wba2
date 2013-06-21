package gui;

import guidata.Register;

import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WindowRegister extends Application {

    @Override
    public void start(final Stage primaryStage) {
    	
    	Image image1 = new Image("file:Logo/Logo.png");
    	// simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setLayoutX(5);
        iv1.setLayoutY(5);
        iv1.setFitWidth(290);
        iv1.setImage(image1);
    	
        primaryStage.setResizable(false);

        final Label errmessage = new Label();
        errmessage.setLayoutX(50);
        errmessage.setLayoutY(355);

        Label name = new Label("Username:");
        name.setLayoutX(50);
        name.setLayoutY(61);

        final TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(77);
        textField.setMinWidth(200);

        Label fname = new Label("First name:");
        fname.setLayoutX(50);
        fname.setLayoutY(103);

        final TextField fnameField1 = new TextField();
        fnameField1.setLayoutX(50);
        fnameField1.setLayoutY(119);
        fnameField1.setMinWidth(200);

        Label lname = new Label("Last name:");
        lname.setLayoutX(50);
        lname.setLayoutY(145);

        final TextField lnameField1 = new TextField();
        lnameField1.setLayoutX(50);
        lnameField1.setLayoutY(161);
        lnameField1.setMinWidth(200);

        Label gname = new Label("Gender:");
        gname.setLayoutX(50);
        gname.setLayoutY(187);

        final ToggleGroup ggroup = new ToggleGroup();
        final RadioButton gbm = new RadioButton("Male");
        gbm.setToggleGroup(ggroup);
        gbm.setSelected(true);
        gbm.setLayoutX(70);
        gbm.setLayoutY(203);

        final RadioButton gbf = new RadioButton("Female");
        gbf.setToggleGroup(ggroup);
        gbf.setLayoutX(155);
        gbf.setLayoutY(203);

        Label bday = new Label("Birthday:");
        bday.setLayoutX(50);
        bday.setLayoutY(229);

        final TextField bdayField = new TextField();
        bdayField.setLayoutX(50);
        bdayField.setLayoutY(245);
        bdayField.setMinWidth(200);
        bdayField.setPromptText("YYYY-MM-DD");

        Label country = new Label("Country:");
        country.setLayoutX(50);
        country.setLayoutY(271);

        Register register = new Register();
        final ChoiceBox countryc = new ChoiceBox(FXCollections.observableArrayList(register.countrylist()));
        countryc.getSelectionModel().selectFirst();
        countryc.setLayoutX(122);
        countryc.setLayoutY(287);

        Label city = new Label("City:");
        city.setLayoutX(50);
        city.setLayoutY(313);

        final TextField cityField = new TextField();
        cityField.setLayoutX(50);
        cityField.setLayoutY(329);
        cityField.setMinWidth(200);

        Button backbtn = new Button();
        backbtn.setLayoutX(122);
        backbtn.setLayoutY(401);
        backbtn.setText("Back");
        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WindowLogin backlogin = new WindowLogin();
                try {
					backlogin.start(primaryStage);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        Button btn = new Button();
        btn.setLayoutX(122);
        btn.setLayoutY(375);
        btn.setText("Register");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Register register = new Register();
                errmessage.setText("");
                if (register.login(textField.getText())) {
                    errmessage.setTextFill(Color.rgb(210, 39, 30));
                    errmessage.setText("Sorry, username already taken or invalid!");
                } else {
                    Map<String, String> userdata = new HashMap<String, String>();
                    userdata.put("name", textField.getText());
                    userdata.put("fname", fnameField1.getText());
                    userdata.put("lname", lnameField1.getText());
                    userdata.put("city", cityField.getText());
                    if (gbm.isSelected()) {
                        userdata.put("gender", "Male");
                    } else {
                        userdata.put("gender", "Female");
                    }
                    userdata.put("bday", bdayField.getText());
                    userdata.put("country", countryc.getValue().toString());;
                    register.createUser(userdata);

                    WindowSocialMain loggedin = new WindowSocialMain();
                    loggedin.user = textField.getText();
                    loggedin.start(primaryStage);
                }
            }
        });

        AnchorPane root = new AnchorPane();
        root.getChildren().add(iv1);
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

        Scene scene = new Scene(root, 300, 430);

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