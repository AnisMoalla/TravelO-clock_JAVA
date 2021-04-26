package Gui;

import Entities.User;
import Services.UserService;
import Utils.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ControllerLogin implements Initializable {

    @FXML
    TextField emailTextField ;

    @FXML
    PasswordField passwordTextField ;

    @FXML
    ImageView imageView ;

    public void signIn(MouseEvent MouseEvent) {

        String password = passwordTextField.getText() ;
        String email = emailTextField.getText() ;

        if (email.isEmpty() || password.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Empty Fields");
            alert.setContentText("Empty Fields");
            alert.show();
            return;
        }

        UserService userService = new UserService() ;
        User user = userService.getUser(email,password) ;
        if (user == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Check your info");
            alert.setContentText("Check your info");
            alert.show();
            return;
        }


        if (user.getRoles().contains("ROLE_ADMIN"))
            goToAdminPage(user.getId()) ;


    }

    public void signUp(MouseEvent MouseEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/typeUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 650, 450));
        Main.primaryStage.show();


    }

    public void goToAdminPage(int id)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/listUsers.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Preferences preferences = Preferences.userRoot() ;
        preferences.putInt("user",id);

        Main.primaryStage.setScene(new Scene(root, 1000, 460));
        Main.primaryStage.show();
    }

    public void goToForgotPasswordPage(MouseEvent MouseEvent)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/forgotPassword.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 340, 365));
        Main.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        emailTextField.setPromptText("Email");
        emailTextField.setFocusTraversable(false) ;

        passwordTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        passwordTextField.setPromptText("*********");
        passwordTextField.setMinWidth(150);
        passwordTextField.setFocusTraversable(false) ;

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/logo.png");

            Image image = new Image(inputstream) ;

            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
