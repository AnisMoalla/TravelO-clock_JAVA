package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTypeUser implements Initializable {

    @FXML
    ImageView tourist ;

    @FXML
    ImageView serviceProvider ;

    @FXML
    ImageView back ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/tourist.png");

            Image image = new Image(inputstream) ;

            tourist.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/man.png");
            Image image = new Image(inputstream) ;

            serviceProvider.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            inputstream = new FileInputStream("/home/saif/IdeaProjects/Tour/src/style/undo.png");
            Image image = new Image(inputstream) ;

            back.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void serviceProvider(MouseEvent MouseEvent) {

    }

    public void tourist(MouseEvent MouseEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/SignUp.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 650, 445));
        Main.primaryStage.show();
    }


    public void backToLogin(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 310, 467));
        Main.primaryStage.show();
    }
}
