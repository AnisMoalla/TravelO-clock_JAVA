package Gui;


import Services.UserService;
import Utils.EmailTemplate;
import Utils.Mailer;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.* ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerForgotPassword implements Initializable {

    @FXML
    TextField emailTextField ;

    @FXML
    ImageView imageView ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        emailTextField.setPromptText("Email");
        emailTextField.setFocusTraversable(false) ;

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/email.png");

            Image image = new Image(inputstream) ;

            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void sendCode(MouseEvent MouseEvent) {

        UserService userService = new UserService();

        // get Email Value
        String email = emailTextField.getText();

        // check email if empty
        if (email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email field is empty");
            alert.setContentText("Email field is empty");
            alert.show();
            return;
        }

        // check format of email (Validator 1.7)
        if (!EmailValidator.getInstance().isValid(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check your email format");
            alert.setContentText("Check your email format");
            alert.show();
            return;
        }

        // check email in the database
        if (!userService.checkEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not exist");
            alert.setContentText("User not exist");
            alert.show();
            return;
        }

        int token = userService.generateResetToken(email) ;
        // send email to user
       Mailer.send(email,"Reset Password", EmailTemplate.getResetPassword(token));
       this.goToCodeResetPassword(email) ;

    }

    public void goToCodeResetPassword(String email)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Gui/codeResetPassword.fxml")) ;
        try {
            Main.primaryStage.setScene(new Scene(fxmlLoader.load(),345,365));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerCodeResetPassword controllerCodeResetPassword = fxmlLoader.getController() ;
        controllerCodeResetPassword.initData(email);
        Main.primaryStage.show();
    }


}
