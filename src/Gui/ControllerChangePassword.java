
package Gui;

import Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChangePassword implements Initializable {

    @FXML
    PasswordField passwordField ;

    @FXML
    PasswordField confirmPasswordField ;

    @FXML
    ImageView imageView ;


    public String email ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false) ;

        confirmPasswordField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setFocusTraversable(false) ;

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/forgotPass.png");

            Image image = new Image(inputstream) ;

            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void initData(String email)
    {
        this.email = email ;
    }

    public void changePassword(MouseEvent MouseEvent) {
        String password = passwordField.getText() ;
        String confirmPassword = confirmPasswordField.getText() ;

        // check fields empty
        if (password.isEmpty() || confirmPassword.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields");
            alert.setContentText("Empty fields");
            alert.show();
            return;
        }

        // check if password is equal to confirm password
        if (password.compareTo(confirmPassword) != 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password must be the same");
            alert.setContentText("Password must be the same");
            alert.show();
            return;
        }


        // update Password

        UserService userService = new UserService() ;
        userService.updatePassword(email , password) ;

        this.goToLoginPage() ;

    }

    public void goToLoginPage()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Gui/login.fxml")) ;
        try {
            Scene scene = new Scene(fxmlLoader.load(),310, 467) ;
            Main.primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("../style/styleLogin.css").toExternalForm());

        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.show();
    }


}
