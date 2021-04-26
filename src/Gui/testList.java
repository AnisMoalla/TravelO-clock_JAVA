package Gui;

import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class testList implements Initializable {

    @FXML
    ScrollPane scrol ;

    int i = 0 ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox root = new VBox();
        UserService userService = new UserService() ;
        List<User> list = userService.getAll() ;

        for (User u: list) {


            Pane pane = new Pane() ;
            Button button = new Button(u.getEmail()) ;
            button.setLayoutX(50);
            button.setLayoutY(100);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(u.getEmail());
                }
            });
            Label label = new Label(String.valueOf(i)) ;
            label.setLayoutX(100);
            label.setLayoutY(100);

            FileInputStream inputstream = null;
            ImageView imageView = new ImageView() ;

            try {
                inputstream = new FileInputStream("/home/saif/Desktop/image/1512471815124718.png");
                Image image = new Image(inputstream) ;
                imageView = new ImageView() ;
                imageView.setImage(image);
                imageView.setLayoutX(300);
                imageView.setLayoutY(100);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            pane.getChildren().addAll(button,label,imageView);
            root.getChildren().addAll(pane);
            i++;
        }

        root.setSpacing(10);
        scrol.setContent(root);
    }
}
