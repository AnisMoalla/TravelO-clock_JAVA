/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents.Controllers;

import GestionEvents.Entities.Evenement;
import GestionEvents.Entities.Offre;
import GestionEvents.Entities.Reservation;
import GestionEvents.Services.EventsCRUD;
import GestionEvents.Services.RessCRUD;
import GestionEvents.Utils.EmailTemplate;
import GestionEvents.Guis.EncapsulationOffre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author legion
 */
public class DetailsReservationEventController implements Initializable {

    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonReserver;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelDateD;
    @FXML
    private Label labelDateF;
    @FXML
    private Label labelP;
     EncapsulationOffre eo=new EncapsulationOffre();
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNom.setText(eo.getNom());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        labelDateD.setText(dateFormat.format(eo.getDate_debut()));
        labelDateF.setText(dateFormat.format(eo.getDate_fin()));
        EventsCRUD eventsCRUD=new EventsCRUD();
        double prix=eventsCRUD.recupererPrixEvent(eo.getEvenement_id());
        prix=prix-((prix*eo.getPourcentage())/100);
        labelP.setText(String.valueOf(prix));
        Evenement tempEvent= eventsCRUD.getEventById(eo.getEvenement_id());
        File file = new File("C:\\Users\\legion\\Desktop\\This Year\\Projects\\Third Year\\Web\\Travel\\Travel\\public\\uploads\\images\\"+tempEvent.getImage());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }    

    @FXML
    private void eventRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/ReservationTouriste.fxml"));
        Pane root = loader.load();
        buttonRetour.getScene().setRoot(root);
    }

    @FXML
    private void eventreserver(ActionEvent event) {
        Offre monOffre=new Offre(eo.getId(), eo.getEvenement_id() ,eo.getNom(), eo.getDate_debut(), eo.getDate_fin(), eo.getPourcentage());
                    EventsCRUD eventsCRUD=new EventsCRUD();
                    double prix=eventsCRUD.recupererPrixEvent(monOffre.getEvenement_id());
                    prix=prix-((prix*monOffre.getPourcentage())/100);
                    Reservation reservation= new Reservation();
                    reservation.setTourist_id(1);
                    reservation.setEvenement_id(monOffre.getEvenement_id());
                    reservation.setOffre_id(monOffre.getId());
                    reservation.setDate_reservation(convertToDateViaInstant(LocalDate.now()));
                    reservation.setTarif(prix);
                    Date date=convertToDateViaInstant(LocalDate.now());
                    eventsCRUD.modifierNbrPlace(monOffre.getEvenement_id());
                    RessCRUD ressCRUD=new RessCRUD();
                    ressCRUD.ajouterReservation(reservation);
                    sendMail("anis.moalla@esprit.tn",EmailTemplate.getReservationTouriste(eo.getNom()));
                    TrayNotification tray = new TrayNotification();
                                            AnimationType type = AnimationType.SLIDE;
                                            tray.setAnimationType(type);
                                            tray.setTitle("Réservation Effectuée");
                                            tray.setMessage("Un Mail est envoyé à votre adresse email");
                                            tray.setNotificationType(NotificationType.SUCCESS);//
                                            tray.showAndDismiss(Duration.millis(3000)); 
                        }
    
    private void sendMail(String recepient,String messageToSend) {
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
        Message message =  prepareMessage(session,myAccountEmail,recepient,messageToSend);
        try {
            message.setContent(messageToSend, "text/html; charset=utf-8");
        } catch (MessagingException ex) {
            Logger.getLogger(DetailsReservationEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("message sent succefully!!!");
        
     }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String messageToSend) {
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Mail Réservation");
            message.setText(messageToSend);
            return message;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }    
    
    public Date convertToDateViaInstant(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
       }
    
}
