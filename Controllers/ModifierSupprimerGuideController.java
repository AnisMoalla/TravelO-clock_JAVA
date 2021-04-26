/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import oussema.Entities.EncapsulationGuide;
import oussema.Entities.Guide;
import oussema.Services.GuideCRUD;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class ModifierSupprimerGuideController implements Initializable {

    @FXML
    private TextField in_user_id;
    @FXML
    private TextField in_pays;
    @FXML
    private TextField in_ville;
    @FXML
    private TextField in_tel;
    @FXML
    private TextField in_age;
    @FXML
    private TextField in_sexe;
    @FXML
    private TextField in_nom;
    @FXML
    private TextField in_prenom;
    @FXML
    private TextField in_language;
    @FXML
    private TextField in_vote;
    @FXML
    private TextField in_rate;
    @FXML
    private Label l_user;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button in_image;
    @FXML
    private ImageView in_photo;
    @FXML
    private Button btn_supprimer;
    final FileChooser fileChooser = new FileChooser();
    String imagepath = "";
    String fichierpath = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        in_user_id.setText(Integer.toString(EncapsulationGuide.getUser_id()));
        in_pays.setText(EncapsulationGuide.getPays());
        in_ville.setText(EncapsulationGuide.getVille());
        Image image = new Image(EncapsulationGuide.getImage());
        imagepath = EncapsulationGuide.getImage();
        in_photo.setImage(image);
        in_tel.setText(Integer.toString(EncapsulationGuide.getTel()));
        in_age.setText(Integer.toString(EncapsulationGuide.getAge()));
        in_sexe.setText(EncapsulationGuide.getSexe());
        in_nom.setText(EncapsulationGuide.getNom());
        in_prenom.setText(EncapsulationGuide.getPrenom());
        in_language.setText(EncapsulationGuide.getLanguage());
        in_vote.setText(Integer.toString(EncapsulationGuide.getVote()));
        in_rate.setText(Double.toString(EncapsulationGuide.getRate()));

    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherGuide.fxml"));
        try {
            Parent root = loader.load();
            in_user_id.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        if(in_user_id.getText().isEmpty() )
        {TrayNotification tray = new TrayNotification();
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
            JOptionPane.showMessageDialog(null, "vous devez choisir une image !");
        }
          else if(!telIsValid(in_tel.getText()))
        {
            JOptionPane.showMessageDialog(null, "ce numéro de téléphone est invalide !");
        }
        else if (in_age.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_age.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_age).play();
        }
        else if (in_sexe.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_sexe.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_sexe).play();
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
        else if (in_prenom.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_prenom.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_prenom).play();
        }
        else if (in_language.getText().isEmpty()){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_language.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_language).play();
        }
         else if (in_vote.getText().isEmpty()){
             TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_vote.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_vote).play();
        }
          else if (in_rate.getText().isEmpty()){
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
             tray.setTitle("Modification incompléte");
            tray.setMessage("Vous devez remplir les champs a modifier");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        in_rate.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_rate).play();
        }
       
        else if(!telIsValid(in_tel.getText()))
        {
            JOptionPane.showMessageDialog(null, "ce numéro de téléphone est invalide !");
        }
        else
        {
        Guide g = new Guide(EncapsulationGuide.getId(), Integer.parseInt(in_user_id.getText()), in_pays.getText(), in_ville.getText(), imagepath, Integer.parseInt(in_tel.getText()), Integer.parseInt(in_age.getText()), in_sexe.getText(), in_nom.getText(), in_prenom.getText(), in_language.getText(), Integer.parseInt(in_vote.getText()), Double.parseDouble(in_rate.getText()));
        GuideCRUD gc = new GuideCRUD();
        gc.modifierGuide(g);
        JOptionPane.showMessageDialog(null, "guide modifié!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherGuide.fxml"));
        try {
            Parent root = loader.load();
            in_user_id.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } }

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
        if (!telIsValid(in_tel.getText())) {
            JOptionPane.showMessageDialog(null, "ce numéro de téléphone est invalide !");
        } else {
            Guide g = new Guide(EncapsulationGuide.getId());
            GuideCRUD gc = new GuideCRUD();
            gc.supprimerGuide(g);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherGuide.fxml"));
            try {
                Parent root = loader.load();
                btn_retour.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    
    private boolean telIsValid(String tel){
        Pattern pattern = Pattern.compile("^[234579][0-9]{7}$");
        Matcher matcher = pattern.matcher(tel);
        boolean matchFound = matcher.find();
        return matchFound;
    }

}
