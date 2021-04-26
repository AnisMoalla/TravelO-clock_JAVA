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
import static Controllers.AfficherHotelController.idL;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherFacceuilController implements Initializable {

    @FXML
    private TableColumn<Facceuil, String> nom;
    @FXML
    private TableColumn<Facceuil, String> adresse;
    @FXML
    private TableColumn<Facceuil, String> etat;
    @FXML
    private TableColumn<Facceuil, Integer> NbrPlace;
    @FXML
    private TableColumn<Facceuil, String> description;
    @FXML
    private TableColumn<Facceuil, Float> rate;
    @FXML
    private TableView<Facceuil> table;
    
        private ObservableList<Facceuil> HotelData = FXCollections.observableArrayList();
        public static int idF = 0 ;
        ServiceFacceuil cs =  new ServiceFacceuil();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Facceuil> listFacceuil= new ArrayList<Facceuil>();
        listFacceuil = cs.getAllFacceuilByIdUser(1);
        HotelData.clear();
        HotelData.addAll(listFacceuil);
        table.setItems(HotelData);
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        adresse.setCellValueFactory(
            new PropertyValueFactory<>("adresse")
        );
            etat.setCellValueFactory(
            new PropertyValueFactory<>("etat")
        );
        NbrPlace.setCellValueFactory(
            new PropertyValueFactory<>("nbrplace")
        );

         rate.setCellValueFactory(
            new PropertyValueFactory<>("rate")
        );

     
       
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
         Facceuil rec = (Facceuil) table.getSelectionModel().getSelectedItem();
        
            idF = rec.getId();
                   try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Guis/ModifierFacceuil.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherHotelController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                   
        Facceuil RecSelec = (Facceuil) table.getSelectionModel().getSelectedItem();
        Facceuil r = new Facceuil();
        r = cs.findFacceuilById(RecSelec.getId());
 
        cs.deleteFacceuil(r.getId());
        resetTableData();
        
    }

    
     
       public void resetTableData() throws SQLDataException
    {
        List<Facceuil> listFacceuil= new ArrayList<>();
        listFacceuil = cs.getAllFacceuilByIdUser(1);
        ObservableList<Facceuil> data = FXCollections.observableArrayList(listFacceuil);
        table.setItems(data);
    
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        
                           try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Guis/AjouterFacceuil.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherFacceuilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
}
