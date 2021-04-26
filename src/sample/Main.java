package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class Main extends Application {

    public static Stage primaryStage ;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Preferences preferences = Preferences.userRoot() ;
        this.primaryStage = primaryStage ;
        if (preferences.getInt("user",0) == 0)
        {
            this.primaryStage = primaryStage ;
            Parent root = FXMLLoader.load(getClass().getResource("../Gui/login.fxml"));
            Scene scene = new Scene(root, 310, 467) ;
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("../style/styleLogin.css").toExternalForm());
            primaryStage.show();
        } else
        {
            this.primaryStage = primaryStage ;
            Parent root = FXMLLoader.load(getClass().getResource("../Gui/listUsers.fxml"));
            Scene scene = new Scene(root, 1000, 460) ;
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("../style/styleLogin.css").toExternalForm());
            primaryStage.show();
        }



    }


    public static void main(String[] args) {
        launch(args);

    }
}
