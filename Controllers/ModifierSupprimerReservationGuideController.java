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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import oussema.Entities.EncapsulationReservationGuide;
import oussema.Entities.ReservationGuide;
import oussema.Services.GuideCRUD;
import oussema.Services.ReservationGuideCRUD;
import oussema.Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class ModifierSupprimerReservationGuideController implements Initializable {

    @FXML
    private ComboBox<String> in_guide;
    @FXML
    private TextField in_touriste;
    @FXML
    private TextField in_date_reservation;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    private int id_guide;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            String requete = "SELECT nom FROM guide where id='"+EncapsulationReservationGuide.getId_guide()+"' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String nom=rs.getString("nom");
                in_guide.setValue(nom);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        in_touriste.setText(Integer.toString(EncapsulationReservationGuide.getId_touriste()));
        in_date_reservation.setText(EncapsulationReservationGuide.getDate_reservation().toString());
        try {
            String requete = "SELECT nom FROM guide ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String nom=rs.getString("nom");
                in_guide.getItems().add(nom);
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
    private void supprimer(ActionEvent event) {
        ReservationGuide rg = new ReservationGuide(EncapsulationReservationGuide.getId());
        ReservationGuideCRUD rgc = new ReservationGuideCRUD();
        rgc.supprimerReservationGuide(rg);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherReservationGuide.fxml"));
        try {
            Parent root = loader.load();
            btn_retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
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
            ReservationGuide rg = new ReservationGuide(EncapsulationReservationGuide.getId(), id_guide, Integer.parseInt(in_touriste.getText()), Date.valueOf(in_date_reservation.getText()));
            ReservationGuideCRUD rgc = new ReservationGuideCRUD();
            rgc.modifierReservationGuide(rg);
            JOptionPane.showMessageDialog(null, "reservation guide modifiée!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/afficherReservationGuide.fxml"));
            try {
                Parent root = loader.load();
                in_guide.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
}


