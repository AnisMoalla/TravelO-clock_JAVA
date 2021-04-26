package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.prefs.Preferences;


public class Main extends Application {
    public static Stage stage ;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage ;
        Parent root = FXMLLoader.load(getClass().getResource("../Guis/ListePromotionFront.fxml"));
        primaryStage.setTitle("test");
        Preferences preferences = Preferences.userRoot() ;
        preferences.putInt("user",1);
//        primaryStage.setScene(new Scene(root, 660, 400));
        primaryStage.setScene(new Scene(root, 680, 500));

        primaryStage.show();

    }


    public static void main(String[] args) throws Exception {

       launch(args);

    }
}
