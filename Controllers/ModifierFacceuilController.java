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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierFacceuilController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField adresse;
    @FXML
    private TextField nbrPlace;

                ServiceFacceuil sh = new ServiceFacceuil();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Facceuil h = sh.findFacceuilById(AfficherFacceuilController.idF);
        nom.setText(h.getNom());
        description.setText(h.getDescription());
        adresse.setText(h.getAdresse());
        nbrPlace.setText(String.valueOf(h.getNbrplace()));
       
    
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLDataException {
   
        Facceuil c = new Facceuil();
        c.setId(AfficherFacceuilController.idF);
        c.setNom(nom.getText());
        c.setDescription(description.getText());
        c.setAdresse(adresse.getText());
        c.setNbrplace(Integer.valueOf(nbrPlace.getText()));
        sh.ModifierFacceuil(c);
        
           
         try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/Gui/AfficherFacceuil.fxml"));
            Stage myWindow = (Stage)nom.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  }         
        
    
    }

    

