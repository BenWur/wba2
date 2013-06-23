package gui;

import java.net.MalformedURLException;
import nodepackage.XMPPConnect;
import guidata.Register;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Login-Fenster hiermit wird alles gestartet
 *
 * @author Ben & Dario
 */
public class WindowLogin extends Application {

    /**
     * Hier beginnt alles. Hauptsächlich dafür da, das einloggen zu verwalten
     *
     * @param primaryStage
     */
    @Override
    public void start(final Stage primaryStage) throws MalformedURLException {

        //Logo und Banner einbinden
        primaryStage.getIcons().add(new Image("file:logo/icon.png"));
        Image image1 = new Image("file:logo/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setLayoutX(5);
        iv1.setLayoutY(5);
        iv1.setFitWidth(290);
        iv1.setImage(image1);

        primaryStage.setResizable(false);	//Damit die Größe fix ist

        final Label errmessage = new Label();	//Errormessage falls Login falsch ist
        errmessage.setLayoutX(50);
        errmessage.setLayoutY(141);

        Label name = new Label("Username:");
        name.setLayoutX(50);
        name.setLayoutY(61);

        final TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(77);
        textField.setMinWidth(200);

        Label pass = new Label("Password:");
        pass.setLayoutX(50);
        pass.setLayoutY(103);

        final PasswordField passField1 = new PasswordField();
        passField1.setLayoutX(50);
        passField1.setLayoutY(119);
        passField1.setMinWidth(200);

        Button reg = new Button();
        reg.setLayoutX(160);
        reg.setLayoutY(162);
        reg.setMinWidth(90);
        reg.setText("Register");
        reg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WindowRegister newuser = new WindowRegister();
                newuser.start(primaryStage); 	//oeffnet neue stage
            }
        });

        Button btn = new Button();
        btn.setDefaultButton(true);
        btn.setLayoutX(50);
        btn.setLayoutY(162);
        btn.setMinWidth(90);
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Register register = new Register();	//Register verwaltet Login
                errmessage.setText("");
                if (register.login(textField.getText())) {	//Falls Name in xml existiert
                    WindowSocialMain loggedin = new WindowSocialMain();
                    loggedin.userName = register.loggedInUser;	//username wird uebergeben
                    XMPPConnect connection = XMPPConnect.getInstance();	//connection wird lokal gespeichert
                    if (connection.login(loggedin.userName, passField1.getText())) {	//falls connection klappt
                        loggedin.start(primaryStage);		//Zum Einloggen auf dem XMPP Server
                    } else {
                        errmessage.setText("Your username or password is incorrect!");
                        errmessage.setTextFill(Color.rgb(210, 39, 30));
                    }
                } else {
                    errmessage.setText("Your username is incorrect!");
                    errmessage.setTextFill(Color.rgb(210, 39, 30));
                }
            }
        });

        //Elemente werden hinzugefügt
        AnchorPane root = new AnchorPane();
        root.getChildren().add(iv1);
        root.getChildren().add(btn);
        root.getChildren().add(reg);
        root.getChildren().add(name);
        root.getChildren().add(pass);
        root.getChildren().add(textField);
        root.getChildren().add(passField1);
        root.getChildren().add(errmessage);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("SocialTicker | Login");
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
