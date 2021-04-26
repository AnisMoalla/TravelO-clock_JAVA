
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facceuil;
import Entities.Hotel;
import Services.ServiceHotel;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javafx.scene.control.Button;
import Utils.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherHotelController implements Initializable {

    @FXML
    private TableColumn<Hotel, String> nom;
    @FXML
    private TableColumn<Hotel, String> Description;
    @FXML
    private TableColumn<Hotel, String> adresse;
    @FXML
    private TableColumn<Hotel, Float> prix;
    @FXML
    private TableColumn<Hotel, Integer> nbrCH;
    @FXML
    private TableColumn<Hotel, Integer> nbrChDis;
    @FXML
    private TableColumn<Hotel, Integer> nbrEtoile;
    
        private ObservableList<Hotel> HotelData = FXCollections.observableArrayList();
        public static int idL = 0 ;
        ServiceHotel cs =  new ServiceHotel();
    @FXML
    private TableView<Hotel> table;
    @FXML
    private TextField recherche;
   
    @FXML
     private Button csv;
    ObservableList<Hotel> obl = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Hotel> listHotel= new ArrayList<Hotel>();
        listHotel = cs.getAllHotel();
        try {

            String requete = "SELECT id, nbrechambre, nbrechambreDispo,nbreEtoile,prix ,nom,description, adresse, image from hotel";
                  
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                System.out.println(rs);
                obl.add(new Hotel(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), 0, rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        HotelData.clear();
        HotelData.addAll(listHotel);
        table.setItems(HotelData);
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        Description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        adresse.setCellValueFactory(
            new PropertyValueFactory<>("adresse")
        );
            prix.setCellValueFactory(
            new PropertyValueFactory<>("prix")
        );
                        nbrCH.setCellValueFactory(
            new PropertyValueFactory<>("nbrechambre")
        );
                                    nbrChDis.setCellValueFactory(
            new PropertyValueFactory<>("nbrechambreDispo")
        );
 nbrEtoile.setCellValueFactory(
            new PropertyValueFactory<>("nbreEtoile")
        );
     
    }    

    @FXML
    private void modifier(ActionEvent event) {
                          Hotel rec = (Hotel) table.getSelectionModel().getSelectedItem();
        
            idL = rec.getId();
                   try {
                           Parent root = FXMLLoader.load(getClass().getResource("/Guis/ModifierHotel.fxml"));
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
    private void supprimer(ActionEvent event) throws SQLDataException {
        
                
        Hotel RecSelec = (Hotel) table.getSelectionModel().getSelectedItem();
        Hotel r = new Hotel();
        r = cs.findHotelById(RecSelec.getId());
 
        cs.deleteHotel(r.getId());
        resetTableData();
        
    }

    
     
      public void resetTableData() throws SQLDataException
    {
        List<Hotel> listCours = new ArrayList<>();
        listCours = cs.getAllHotel();
        ObservableList<Hotel> data = FXCollections.observableArrayList(listCours);
        table.setItems(data);
    }

    @FXML
    private void addHotel(ActionEvent event) {
        
         try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/Gui/AjouterHotel.fxml"));
            Stage myWindow = (Stage)table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  }         

    @FXML
    private void Rechercher(ActionEvent event) {
             nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        Description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        adresse.setCellValueFactory(
            new PropertyValueFactory<>("adresse")
        );
            prix.setCellValueFactory(
            new PropertyValueFactory<>("prix")
        );
                        nbrCH.setCellValueFactory(
            new PropertyValueFactory<>("nbrechambre")
        );
                                    nbrChDis.setCellValueFactory(
            new PropertyValueFactory<>("nbrechambreDispo")
        );
 nbrEtoile.setCellValueFactory(
            new PropertyValueFactory<>("nbreEtoile")
        );
     
            
            
            List<Hotel> list = cs.getAllHotel();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Hotel> filtredData= new FilteredList<>(HotelData, b ->true);
            recherche.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Hotel> Evenement;
                filtredData.setPredicate((Hotel hotel) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(hotel.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (hotel.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Hotel> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    @FXML
    private void csv1(ActionEvent event) {
    
    saveheader("dataRoua.csv");
        for (Hotel r : obl) {
            saveRecord(r, "dataRoua.csv");
        }
    }

    private void saveheader(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Id,Nombre de chambre,Nombre de chambre dispo,Nombre d'etoiles,Nom,Description,adresse,Image,Prix");
            pw.flush();
            pw.close();

           
        } catch (Exception E) {
            System.out.println("Record not saved");
        }
    }

    public static void saveRecord(Hotel hotel, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(hotel.getId() + "," + hotel.getNbrechambre() + "," + hotel.getNbrechambreDispo()+ "," + hotel.getNbreEtoile()+ "," + hotel.getNom()+ "," + hotel.getDescription()+ "," + hotel.getAdresse()+ "," + hotel.getImage()+ "," + hotel.getPrix());
            pw.flush();
            pw.close();

            } catch (Exception E) {
            System.out.println("Record not saved");
        }
    }
    
     
    }

 


   

