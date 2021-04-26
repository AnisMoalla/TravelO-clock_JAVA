/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import oussema.Entities.Guide;
import oussema.Entities.Vendeur;
import oussema.Services.GuideCRUD;
import oussema.Services.VendeurCRUD;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AjouterVendeurController implements Initializable {

    @FXML
    private TextField in_user_id;
    @FXML
    private TextField in_pays;
    @FXML
    private TextField in_ville;
    @FXML
    private TextField in_tel;
    @FXML
    private TextField in_nom;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_valider;
    @FXML
    private Button in_image;
    @FXML
    private ImageView in_photo;
    @FXML
    private TextField in_type;
    @FXML
    private TextField in_heure_ouverture;
    @FXML
    private TextField in_heure_fermeture;
    final FileChooser fileChooser = new FileChooser();
    String imagepath = "null";
    String fichierpath = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherVendeur.fxml"));
            try {
                Parent root = loader.load();
                in_user_id.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void valider(ActionEvent event) {
        
       // if(in_user_id.getText().isEmpty() || in_pays.getText().isEmpty() || in_ville.getText().isEmpty() || in_tel.getText().isEmpty() || in_heure_fermeture.getText().isEmpty() || in_heure_ouverture.getText().isEmpty() || in_nom.getText().isEmpty() || in_type.getText().isEmpty() )()
     
       if (in_user_id.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Error");
            tray.setMessage("Vous devez remplir tous les champs");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_user_id.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_user_id).play();
        }
        else if (in_pays.getText().isEmpty()){
        in_pays.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_pays).play();
        }
        else if (in_ville.getText().isEmpty()){
        in_ville.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_ville).play();
        }
        else if(imagepath.equals("null"))
        {
            JOptionPane.showMessageDialog(null, "vous devez choisir une image !");
        }
        else if (in_type.getText().isEmpty()){
        in_type.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_type).play();
        }
        else if (in_nom.getText().isEmpty()){
        in_nom.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_nom).play();
        }
        else if(!telIsValid(in_tel.getText()))
        {
            JOptionPane.showMessageDialog(null, "ce numéro de téléphone est invalide !");
        }
        else if (in_heure_ouverture.getText().isEmpty()){
        in_heure_ouverture.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_heure_ouverture).play();
        }
        else if (in_heure_fermeture.getText().isEmpty()){
        in_heure_fermeture.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_heure_fermeture).play();
        }
        else
        {
            VendeurCRUD vc = new VendeurCRUD();
            Vendeur v = new Vendeur(Integer.parseInt(in_user_id.getText()), in_pays.getText(), in_ville.getText(),imagepath , Integer.parseInt(in_tel.getText()), in_nom.getText(),in_type.getText(),Time.valueOf(in_heure_ouverture.getText()), Time.valueOf(in_heure_fermeture.getText()));
            vc.ajouterVendeur(v);
            JOptionPane.showMessageDialog(null, "Vendeur ajouté!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherVendeur.fxml"));
            try {
                Parent root = loader.load();
                in_user_id.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }

    @FXML
    private void choosePhoto(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagepath = file.toURI().toString();
            //System.out.println(imagepath);
            Image image = new Image(imagepath);
             in_photo.setImage(image); 
        }
    }
    
    private boolean telIsValid(String tel){
        Pattern pattern = Pattern.compile("^[234579][0-9]{7}$");
        Matcher matcher = pattern.matcher(tel);
        boolean matchFound = matcher.find();
        return matchFound;
    }
    
}
