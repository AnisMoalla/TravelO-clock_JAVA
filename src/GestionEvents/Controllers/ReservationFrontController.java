/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents.Controllers;

import GestionEvents.Entities.Reservation;
import GestionEvents.Services.RessCRUD;
import GestionEvents.Guis.EncapsulationResrvation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author legion
 */
public class ReservationFrontController implements Initializable {

    @FXML
    private Button boutonAjouterReservation;
    @FXML
    private Button boutonRetour;
    @FXML
    private TableView<Reservation> tableViewReservation;
    @FXML
    private TableColumn<Reservation, String> colonneUsername;
    @FXML
    private TableColumn<Reservation, String> colonneEvenement;
    @FXML
    private TableColumn<Reservation, Date> colonneDateReservation;
    @FXML
    private TableColumn<Reservation, Double> colonneTarif;
    private RessCRUD ressCRUD;
    private List<Reservation> ressList;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ressCRUD = new RessCRUD();
        ressList = ressCRUD.afficherReservation();
        
        colonneUsername.setCellValueFactory(new PropertyValueFactory("username")); //Dans le propertyValueFactory contien le nom
                                                                                   // de l'attribut dans l'entité
        colonneEvenement.setCellValueFactory(new PropertyValueFactory("eventname"));
      
        colonneDateReservation.setCellValueFactory(new PropertyValueFactory("date_reservation"));
 
        colonneTarif.setCellValueFactory(new PropertyValueFactory("tarif"));
      
        for(int i=0;i<ressList.size();i++)
        {
           tableViewReservation.getItems().add(ressList.get(i));
        }
        
        
        
        tableViewReservation.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount()==2) {
                Reservation monReservation=tableViewReservation.getSelectionModel().getSelectedItem();
                EncapsulationResrvation encapsulationreservation = new EncapsulationResrvation(monReservation.getId(),monReservation.getDate_reservation(),monReservation.getTarif());
                FXMLLoader loader = new FXMLLoader(ReservationFrontController.this.getClass().getResource("../Guis/SuppModifReservation.fxml"));
                Pane root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }   boutonAjouterReservation.getScene().setRoot(root);
            }    
        });
    }    

    @FXML
    private void eventAjouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/AjouterResrvation.fxml"));
        Pane root = loader.load();
        boutonAjouterReservation.getScene().setRoot(root);
    }

    @FXML
    private void actionRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/TourRes.fxml"));
        Pane root = loader.load();
        boutonRetour.getScene().setRoot(root);
    }
    
}
