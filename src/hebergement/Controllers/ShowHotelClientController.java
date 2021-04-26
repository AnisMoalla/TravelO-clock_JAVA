/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Hotel;
import Services.ServiceHotel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author hazar
 */
public class ShowHotelClientController implements Initializable {

    @FXML
    private ListView<Hotel> listView;
   
    ObservableList<Hotel> data;
    
    public static int idE ;

   
    @FXML
    private Button details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceHotel cs = new ServiceHotel();
        try {
            data = (ObservableList<Hotel>) cs.getAllHotelObs();
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowHotelClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Hotel> param) -> new ListViewHotel());
        
        
        // TODO
    }    


    @FXML
    private void ShowDetail(ActionEvent event) {
        
         
        try {
            ObservableList<Hotel> e;
            e = listView.getSelectionModel().getSelectedItems();
            
            
            for (Hotel m : e) {
                idE=m.getId();
                
            }
            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/Guis/DetailsHotelClient.fxml"));
            Stage myWindow =  (Stage) details.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowHotelClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }


    @FXML
    private void handleClose(ActionEvent event) {
    }


}
    

