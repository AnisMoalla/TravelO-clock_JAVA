/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Wofurani
 */
public class TravelPidev extends Application {

public static Stage primaryStage;
    
    
   @Override
    public void start(Stage primaryStage) {
       try {
          this.primaryStage=primaryStage;
           Parent root = FXMLLoader.load(getClass()
                   .getResource("./gui/Calendar.fxml"));
           
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);
           primaryStage.show();
       } catch (IOException ex) {
           System.out.println(ex);
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         System.setProperty("calendarfx.developer", "true");
         
        launch(args);
    }
    
}
