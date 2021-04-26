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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterFacceuilController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField adresse;
    @FXML
    private ImageView img;
    @FXML
    private TextField nbrPlace;

    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/img/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
        
                     UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            nbrPlace.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));

    }    

    @FXML
    private void Ajouter(ActionEvent event) {
         ServiceFacceuil cs = new ServiceFacceuil();
        Facceuil c = new Facceuil();
   
        if( nom.getText().equals("")  || description.getText().equals("") || adresse.getText().equals("")){
                      
                                  
                 Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vérifier votre champs").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }
                  else{
            
        copier(pfile,pDir);

        c.setNom(nom.getText());
        c.setDescription(description.getText());
        c.setAdresse(adresse.getText());
        c.setImage(lien);
        c.setNbrplace(Integer.parseInt(nbrPlace.getText()));
        c.setEtat("disable");
        c.setRate(0);
        c.setVote(0);
        c.setRating(0);
        c.setId_user(1);
        cs.AjoutFacceuil(c);
        
        
         try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/Guis/AfficherFacceuil.fxml"));
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
    
    
    
        
     public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
                
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            img.setImage(image);
    }
        
    }
    
    
}

