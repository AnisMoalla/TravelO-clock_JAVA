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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierHotelController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField adresse;
    @FXML
    private TextField nbrChambre;
    @FXML
    private TextField NbreDispo;
    @FXML
    private TextField nbrEtoile;
    @FXML
    private TextField prix;

            ServiceHotel sh = new ServiceHotel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Hotel h = sh.findHotelById(AfficherHotelController.idL);
        nom.setText(h.getNom());
        description.setText(h.getDescription());
        adresse.setText(h.getAdresse());
        nbrChambre.setText(String.valueOf(h.getNbrechambre()));
        NbreDispo.setText(String.valueOf(h.getNbrechambreDispo()));
        nbrEtoile.setText(String.valueOf(h.getNbreEtoile()));
        prix.setText(String.valueOf(h.getPrix()));
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
        
        Hotel c = new Hotel();
        c.setId(AfficherHotelController.idL);
        c.setNom(nom.getText());
        c.setDescription(description.getText());
        c.setAdresse(adresse.getText());
        c.setNbreEtoile(Integer.parseInt(nbrEtoile.getText()));
        c.setNbrechambre(Integer.parseInt(nbrChambre.getText()));
        c.setNbrechambreDispo(Integer.parseInt(NbreDispo.getText()));
        c.setPrix(Float.parseFloat(prix.getText()));
        sh.ModifierHotel(c);
        
           
         try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/Guis/AfficherHotel.fxml"));
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
