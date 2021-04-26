/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import oussema.Entities.EncapsulationReservationGuide;
import oussema.Entities.ReservationGuide;
import oussema.Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AfficherReservationGuideController implements Initializable {

    @FXML
    private TableView<ReservationGuide> tableGuide;
    @FXML
    private TableColumn<ReservationGuide, String> cl_id;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField in_search;
    @FXML
    private TableColumn<ReservationGuide, String> cl_id_guide;
    @FXML
    private TableColumn<ReservationGuide, String> cl_id_touriste;
    @FXML
    private TableColumn<ReservationGuide, String> cl_date_reservation;
    ObservableList<ReservationGuide> obl = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        search_user();
        tableGuide.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                ReservationGuide maReservationGuide = tableGuide.getSelectionModel().getSelectedItem();
                EncapsulationReservationGuide encapsulationReservationGuide = new EncapsulationReservationGuide(maReservationGuide.getId(), maReservationGuide.getId_guide(), maReservationGuide.getId_touriste(), maReservationGuide.getDate_reservation());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/modifierSupprimerReservationGuide.fxml"));
                try {
                    Parent root = loader.load();
                    in_search.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/acceuil.fxml"));
            try {
                Parent root = loader.load();
                btn_ajouter.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/ajouterReservationGuide.fxml"));
        try {
            Parent root = loader.load();
            in_search.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void search_user() {
        cl_id_guide.setCellValueFactory(new PropertyValueFactory<>("id_guide"));
        cl_id_touriste.setCellValueFactory(new PropertyValueFactory<>("id_touriste"));
        cl_date_reservation.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String requete = "SELECT * FROM reservationguide ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ReservationGuide rg = new ReservationGuide(rs.getInt("id"), rs.getInt("id_guide"), rs.getInt("id_touriste"), rs.getDate("date_reservation"));
                obl.add(rg);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableGuide.setItems(obl);
        FilteredList<ReservationGuide> filteredData = new FilteredList<>(obl, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservationGuideguide -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(reservationGuideguide.getId_touriste()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(reservationGuideguide.getId_guide()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (reservationGuideguide.getDate_reservation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return false; // Does not match.
                }

            });
        });

        tableGuide.setItems(filteredData);
    }

    
    
    
    
}
