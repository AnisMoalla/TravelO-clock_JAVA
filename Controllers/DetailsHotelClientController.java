/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.ServiceHotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetailsHotelClientController implements Initializable {

    @FXML
    private Label nbrChmbre;
    @FXML
    private Label adresse;
    @FXML
    private Label nom;
    @FXML
    private Label Description;
    @FXML
    private Label nbretaile;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceHotel sh = new ServiceHotel();
        
        Hotel h =  sh.findHotelById(ShowHotelClientController.idE);
        
        
        nom.setText(h.getNom());
        adresse.setText(h.getAdresse());
        Description.setText(h.getDescription());
        nbretaile.setText(String.valueOf(h.getNbreEtoile()));
        nbrChmbre.setText(String.valueOf(h.getNbrechambre()));
        img.setImage(new Image("img/"+h.getImage(), 100, 100, false, false));
        // TODO
    }    
    
}
