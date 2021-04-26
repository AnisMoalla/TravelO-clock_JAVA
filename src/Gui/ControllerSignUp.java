package Gui;

import Entities.User;
import Services.UserService;
import Utils.EmailTemplate;
import Utils.Mailer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSignUp implements Initializable {

    @FXML
    TextField nomTextField ;

    @FXML
    TextField prenomTextField ;

    @FXML
    TextField emailTextField ;

    @FXML
    PasswordField passwordField ;

    @FXML
    TextField ageTextField ;

    @FXML
    TextField numeroTextField ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        emailTextField.setPromptText("Email");
        emailTextField.setFocusTraversable(false) ;


        nomTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        nomTextField.setPromptText("First Name");
        nomTextField.setFocusTraversable(false) ;

        prenomTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        prenomTextField.setPromptText("Last Name");
        prenomTextField.setFocusTraversable(false) ;


        ageTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        ageTextField.setPromptText("Age");
        ageTextField.setFocusTraversable(false) ;

        numeroTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        numeroTextField.setPromptText("Number");
        numeroTextField.setFocusTraversable(false) ;

        passwordField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false) ;


    }

    public void signUp(MouseEvent MouseEvent) {

        String email = emailTextField.getText() ;
        String nom = nomTextField.getText() ;
        String prenom = prenomTextField.getText() ;
        String password = passwordField.getText() ;
        String age = ageTextField.getText() ;
        String numero = numeroTextField.getText() ;

        UserService userService = new UserService() ;

        // check if fields are empty
        if (!(email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || password.isEmpty() || age.isEmpty() || numero.isEmpty()))
        {
            // check the email in the database this function return boolean
            if (userService.checkEmail(email))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("Email already exist");
                alert.setContentText("Email already exist");
                alert.show();
                // return to end this this function
                return;
            }

            // check if age is an Integer
            if (!checkInteger(age))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("Age Must be Integer");
                alert.setContentText("Age Must be Integer");
                alert.show();
                // return to end this this function
                return;
            }

            // check if the age is between 10 and 90
            if ( ((Integer.parseInt(age)) < 10) || (Integer.parseInt(age) > 90) )
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("Age Must be between 10 and 90");
                alert.setContentText("Age Must be between 10 and 90");
                alert.show();
                // return to end this this function
                return;
            }


            // check if numero is an Integer
            if (!checkInteger(numero))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("numero Must be Integer");
                alert.setContentText("numero Must be Integer");
                alert.show();
                // return to end this this function
                return;
            }

            // check the length of tlf number
            if ( numero.length() != 8 )
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("Numero must be 8");
                alert.setContentText("Numero must be 8");
                alert.show();
                // return to end this this function
                return;
            }

            // if we are here we are good
            User user = new User() ;
            user.setEmail(email);
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setAge(Integer.parseInt(age));
            user.setNumero(Integer.parseInt(numero));
            user.setPassword(password);
            userService.add(user);

            // get Activate Code
            String code = userService.getActivationCode(user.getEmail()) ;
            System.out.println(code);

           // send ActivateCode to user
            Mailer.send(user.getEmail(),"Activate your account", EmailTemplate.getActivationCode(code));

            goToCheckActivationCodePage(user.getEmail()) ;
            return;

        }

        Alert alert = new Alert(Alert.AlertType.WARNING) ;
        alert.setTitle("Empty Fields");
        alert.setContentText("Empty Fields");
        alert.show();

    }

    public void back(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/typeUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 650, 450));
        Main.primaryStage.show();
    }


    public boolean checkInteger(String value)
    {
        try
        {
            int val = Integer.parseInt(value) ;
            return true ;
        } catch (NumberFormatException e)
        { return false ; }
    }

    public void goToCheckActivationCodePage(String email)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Gui/CheckActivationCode.fxml")) ;
        try {

            Main.primaryStage.setScene(new Scene(fxmlLoader.load(),600,200));

        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerCheckActivationCode controllerCheckActivationCode = fxmlLoader.getController() ;
        controllerCheckActivationCode.initData(email);
        Main.primaryStage.show();
    }


}
