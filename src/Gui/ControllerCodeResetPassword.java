package Gui;

import Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.* ;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCodeResetPassword implements Initializable {

    @FXML
    TextField codeTextField ;

    @FXML
    ImageView imageView ;

    public String email ;

    public void checkCode(MouseEvent MouseEvent) {

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
        if (!userService.checkCodeReset(email,code))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Verify your code");
            alert.setContentText("Verify your code");
            alert.show();
            return;
        }

        // go to change Password Page
        this.goToChangePasswordPage() ;

    }

    public void initData(String email)
    {
        this.email = email ;
    }


    public void goToChangePasswordPage()
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Gui/changePassword.fxml")) ;
        try {
            Main.primaryStage.setScene(new Scene(fxmlLoader.load(),310, 425));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerChangePassword controllerCodeResetPassword = fxmlLoader.getController() ;
        controllerCodeResetPassword.initData(email);
        Main.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeTextField.setStyle("-fx-background-color: #2c344b; -fx-text-fill: white; -fx-font-size: 16px;");
        codeTextField.setPromptText("Code");
        codeTextField.setFocusTraversable(false) ;

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/code.png");

            Image image = new Image(inputstream) ;

            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

