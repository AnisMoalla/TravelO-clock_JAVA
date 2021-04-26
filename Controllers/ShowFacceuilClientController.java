/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Facceuil;
import Entities.Hotel;
import Services.ServiceFacceuil;
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
public class ShowFacceuilClientController implements Initializable {

    @FXML
    private ListView<Facceuil> listView;
   
    ObservableList<Facceuil> data;
    
    public static int idFa ;

   
    @FXML
    private Button details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceFacceuil fs = new ServiceFacceuil();
        try {
            data = (ObservableList<Facceuil>) fs.getAllFacceuilsObs();
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowFacceuilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Facceuil> param) -> new ListViewFacceuil());
        
        
        // TODO
    }    


    @FXML
    private void ShowDetail(ActionEvent event) {
        
         
        try {
            ObservableList<Facceuil> e;
            e = listView.getSelectionModel().getSelectedItems();
            
            
            for (Facceuil m : e) {
                idFa=m.getId();
                
            }
            Parent root ;
            
            root = FXMLLoader.load(getClass().getResource("/Gui/DetailsFacceuilClient.fxml"));
            Stage myWindow =  (Stage) details.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowFacceuilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }


    @FXML
    private void handleClose(ActionEvent event) {
    }


}
    

