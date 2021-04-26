/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facceuil;
import Services.ServiceFacceuil;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;
import Utils.Mail;
import Utils.SendMail;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetailsFacceuilClientController implements Initializable {

    @FXML
    private Label nbrChmbre;
    @FXML
    private Label adresse;
    @FXML
    private Label nom;
    @FXML
    private Label Description;
    @FXML
    private ImageView img;
    @FXML
    private Rating rating;
    
    int ratingNumber = 0;
     ServiceFacceuil sh = new ServiceFacceuil();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        
        Facceuil h =  sh.findFacceuilById(ShowFacceuilClientController.idFa);
        
        
        nom.setText(h.getNom());
        adresse.setText(h.getAdresse());
        Description.setText(h.getDescription());
        nbrChmbre.setText(String.valueOf(h.getNbrplace()));
        img.setImage(new Image("img/"+h.getImage(), 100, 100, false, false));
        
                   rating.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ratingNumber = newValue.intValue();
            }
         });
        
    }    

    @FXML
    private void voter(ActionEvent event) throws SQLDataException, Exception {
        
     Facceuil h =  sh.findFacceuilById(ShowFacceuilClientController.idFa);
     
     int v = h.getVote();
     
     float r = h.getRate();
     
     h.setRate(((r+ratingNumber)/(v+1)));
     h.setVote(v+1);
     sh.Voter(h);
        SendMail.sendMail("roua.maknin@esprit.tn" , Mail.templatEmailReclamation(h));

    }
    
}
