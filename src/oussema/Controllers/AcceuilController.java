/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btn_guide;
    @FXML
    private Button btn_vendeur;
    @FXML
    private Button btn_reservation_guide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guide(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherGuide.fxml"));
            try {
                Parent root = loader.load();
                btn_guide.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void vendeur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherVendeur.fxml"));
            try {
                Parent root = loader.load();
                btn_guide.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void reservationGuide(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherReservationGuide.fxml"));
            try {
                Parent root = loader.load();
                btn_guide.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
