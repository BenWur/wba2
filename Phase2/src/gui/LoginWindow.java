package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginWindow extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setResizable(false);
        
        final Label errmessage = new Label();
        errmessage.setLayoutX(50);
        errmessage.setLayoutY(110);
        
        Label name = new Label("Username:");
        name.setLayoutX(50);
        name.setLayoutY(31);
        
        final TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(47);
        textField.setMinWidth(200);
        
        Label pass = new Label("Password:");
        pass.setLayoutX(50);
        pass.setLayoutY(73);
        
        final PasswordField passField1 = new PasswordField();
        passField1.setLayoutX(50);
        passField1.setLayoutY(89);
        passField1.setMinWidth(200);
        
        Button reg = new Button();
        reg.setLayoutX(122);
        reg.setLayoutY(162);
        reg.setText("Register");
        reg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterNewWindow newuser = new RegisterNewWindow();
                newuser.start(primaryStage); //oeffnet neue stage
            }
        });
        
        Button btn = new Button();
        btn.setLayoutX(122);
        btn.setLayoutY(132);
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Register register = new Register();
               errmessage.setText("");
               passField1.clear();
               if (register.login(textField.getText())) {
                   System.out.println("Eingeloggt!");
               }else{
                  errmessage.setText("Your username or password is incorrect!");
                  errmessage.setTextFill(Color.rgb(210, 39, 30));
               }
            }
        });
       
        AnchorPane root = new AnchorPane();
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