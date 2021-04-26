/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class Travel extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowHotelClient.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficherFacceuil.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficherHotel.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowFacceuilClient.fxml"));

     //Parent root = FXMLLoader.load(getClass().getResource("/Gui/FrontFacceuil.fxml"));
      
     //Parent root = FXMLLoader.load(getClass().getResource("/Gui/FrontHotel.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
