/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import oussema.Entities.Guide;
import oussema.Entities.ReservationGuide;
import oussema.Services.GuideCRUD;
import oussema.Services.ReservationGuideCRUD;
import oussema.Utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AjouterReservationGuideController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_valider;
    @FXML
    private ComboBox<String> in_guide;
    @FXML
    private TextField in_touriste;
    @FXML
    private TextField in_date_reservation;
    private int id_guide;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            String requete = "SELECT prenom FROM guide ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String prenom=rs.getString("prenom");
                in_guide.getItems().add(prenom);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherReservationGuide.fxml"));
            try {
                Parent root = loader.load();
                in_guide.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void valider(ActionEvent event) {
       /* if(in_guide.getValue().isEmpty()|| in_touriste.getText().isEmpty() || in_date_reservation.getText().isEmpty() )
        {
            //JOptionPane.showMessageDialog(null, "vous devez remplir tous les champs !");
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("ERROR");
            tray.setMessage("vous devez remplir tous les champs !");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
             in_touriste.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_touriste).play();
             in_date_reservation.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_date_reservation).play();
        }*/
       if(in_guide.getValue().isEmpty()){
              JOptionPane.showMessageDialog(null, "vous devez choisir un guide a reserver !");
        }
       else if (in_touriste.getText().isEmpty()){
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("ERROR");
            tray.setMessage("vous devez remplir tous les champs !");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        in_touriste.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_touriste).play();
        }
         else if (in_date_reservation.getText().isEmpty()){
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("ERROR");
            tray.setMessage("vous devez remplir tous les champs !");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        in_date_reservation.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(in_date_reservation).play();
        }
        else {
            try {
            String requete = "SELECT id FROM guide where nom='"+in_guide.getValue()+"' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                id_guide=rs.getInt("id");
                
                
            }

            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            ReservationGuideCRUD rgc = new ReservationGuideCRUD();
            ReservationGuide rg = new ReservationGuide( id_guide,Integer.parseInt(in_touriste.getText()), Date.valueOf(in_date_reservation.getText()));
            rgc.ajouterReservationGuide(rg);
            JOptionPane.showMessageDialog(null, "Reservation Guide ajouté!");
            
            sendMail("oussema.belaid@esprit.tn", GuideCRUD.find(id_guide));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherReservationGuide.fxml"));
            try {
                Parent root = loader.load();
                in_guide.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        
    }
    
    private void sendMail(String recepient,String text) {
        System.out.println("preparing to send mail");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "projetpidev992@gmail.com";
        String password = "ozxcgepevofquhfb";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        }
        );
        Message message =  prepareMessage(session,myAccountEmail,recepient,text);
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("message sent succefully!!!");
        
     }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String text) {
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation Guide");
            message.setText(text);
            return message;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    

    
}
