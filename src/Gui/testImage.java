package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.* ;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;

public class testImage implements Initializable {

    @FXML
    ImageView imageView ;

    public void upload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser() ;
        fileChooser.setTitle("open");
        File image = fileChooser.showOpenDialog(Main.primaryStage) ;
        if (image != null)
        {
            int index = (image.toString().lastIndexOf(".")) ;
            if(index > 0) {
                String extension = image.toString().substring(index + 1);
                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING) ;
                    alert.setTitle("invalid image");
                    alert.setContentText("invalid image");
                    alert.show();
                    // return to end this this function
                    return;
                }

                // image name
                Random random = new Random() ;
                int imageName = random.nextInt(30000000) ;

                String pathname = "/home/saif/Desktop/image/"+imageName+String.valueOf(imageName)+"."+extension ;
                System.out.println(pathname);
                File file1 = new File(pathname) ;
                try {
                    Files.copy(image.toPath(),file1.toPath(), StandardCopyOption.REPLACE_EXISTING) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            FileInputStream inputstream = new FileInputStream("/home/saif/Desktop/image/1512471815124718.png");

            Image image = new Image(inputstream) ;
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
