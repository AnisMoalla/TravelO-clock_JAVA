package Gui;

import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.* ;
import java.io.IOException;

public class ControllerCheckActivationCode {

    @FXML
    TextField codeTextField ;

    private String email ;


    public void initData(String email)
    {
        this.email = email ;
    }

    public void ActivateAccount(ActionEvent actionEvent) {

        String code = codeTextField.getText() ;
        if (code.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Code field is empty");
            alert.setContentText("Code field is empty");
            alert.show();
            return;
        }

        // check code in db
        UserService userService = new UserService() ;
        if (!userService.checkActivationCode(email,code))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Verify your code");
            alert.setContentText("Verify your code");
            alert.show();
            return;
        }

        // activate Account
        userService.activateAccount(email);

        // go to login page
        backLogin();

    }
    public void backLogin()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.primaryStage.setScene(new Scene(root, 600, 300));
        Main.primaryStage.show();
    }

}
