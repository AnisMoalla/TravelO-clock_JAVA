package Gui;

import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.* ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModifyUser implements Initializable {

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

    private User user ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(User user)
    {
        nomTextField.setText(user.getNom());
        prenomTextField.setText(user.getPrenom());
        emailTextField.setText(user.getEmail());
        ageTextField.setText(String.valueOf(user.getAge()));
        numeroTextField.setText(String.valueOf(user.getNumero()));
        this.user = user ;
    }

    public void back(ActionEvent actionEvent) {
        back();
    }

    public void back()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/listUsers.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 900, 400));
        Main.primaryStage.show();

    }

    public void Modify(ActionEvent actionEvent) {

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
            user.setId(this.user.getId());
            user.setEmail(email);
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setAge(Integer.parseInt(age));
            user.setNumero(Integer.parseInt(numero));
            user.setPassword(password);
            userService.Modify(user);
            back();
            return;

        }

        Alert alert = new Alert(Alert.AlertType.WARNING) ;
        alert.setTitle("Empty Fields");
        alert.setContentText("Empty Fields");
        alert.show();
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

    public void delete(ActionEvent actionEvent) {
        UserService userService = new UserService() ;
        userService.delete(this.user.getId());
        back();
    }
}
