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

import oussema.Entities.EncapsulationVendeur;
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
public class ModifierSupprimerVendeurController implements Initializable {

    @FXML
    private TextField in_user_id;
    @FXML
    private TextField in_pays;
    @FXML
    private TextField in_ville;
    @FXML
    private TextField in_tel;
    @FXML
    private TextField in_type;
    @FXML
    private TextField in_nom;
    @FXML
    private TextField in_heure_ouverture;
    @FXML
    private TextField in_heure_fermeture;
    @FXML
    private Button btn_retour;
    @FXML
    private Button in_image;
    @FXML
    private ImageView in_photo;
    final FileChooser fileChooser = new FileChooser();
    String imagepath = "";
    String fichierpath = "";
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        in_user_id.setText(Integer.toString(EncapsulationVendeur.getUser_id()));
        in_pays.setText(EncapsulationVendeur.getPays());
        in_ville.setText(EncapsulationVendeur.getVille());
        Image image = new Image(EncapsulationVendeur.getImage());
        imagepath = EncapsulationVendeur.getImage();
        in_photo.setImage(image);
        in_tel.setText(Integer.toString(EncapsulationVendeur.getTel()));
        in_type.setText(EncapsulationVendeur.getType());
        in_nom.setText(EncapsulationVendeur.getNom());
        in_heure_ouverture.setText(EncapsulationVendeur.getHeure_ouverture().toString());
        in_heure_fermeture.setText(EncapsulationVendeur.getHeure_fermeture().toString());
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
    private void choosePhoto(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String location = (file.getAbsoluteFile().toURI().toString());
            imagepath = file.toURI().toString();
            Image image = new Image(imagepath);
            in_photo.setImage(image);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Vendeur v = new Vendeur(EncapsulationVendeur.getId());
        VendeurCRUD vc = new VendeurCRUD();
        vc.supprimerVendeur(v);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherVendeur.fxml"));
        try {
            Parent root = loader.load();
            btn_retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
       if (in_user_id.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_user_id.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_user_id).play();
        }
        else if (in_pays.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_pays.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_pays).play();
        }
        else if (in_ville.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_ville.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_ville).play();
        }
        else if(imagepath.equals("null"))
        {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
            JOptionPane.showMessageDialog(null, "vous devez choisir une image !");
        }
        else if (in_type.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_type.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_type).play();
        }
        else if (in_nom.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_nom.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_nom).play();
        }
        else if(!telIsValid(in_tel.getText()))
        {
            JOptionPane.showMessageDialog(null, "ce numéro de téléphone est invalide !");
        }
        else if (in_heure_ouverture.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier(heure ouverture)");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_heure_ouverture.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_heure_ouverture).play();
        }
        else if (in_heure_fermeture.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier (heure fermeture)");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_heure_fermeture.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_heure_fermeture).play();
        
        } else {
            VendeurCRUD vc = new VendeurCRUD();
            Vendeur v = new Vendeur(EncapsulationVendeur.getId(), Integer.parseInt(in_user_id.getText()), in_pays.getText(), in_ville.getText(), imagepath, Integer.parseInt(in_tel.getText()), in_nom.getText(), in_type.getText(), Time.valueOf(in_heure_ouverture.getText()), Time.valueOf(in_heure_fermeture.getText()));
            vc.modifierVendeur(v);
            JOptionPane.showMessageDialog(null, "Vendeur modifié!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherVendeur.fxml"));
            try {
                Parent root = loader.load();
                in_user_id.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private boolean telIsValid(String tel) {
        Pattern pattern = Pattern.compile("^[234579][0-9]{7}$");
        Matcher matcher = pattern.matcher(tel);
        boolean matchFound = matcher.find();
        return matchFound;
    }

}
