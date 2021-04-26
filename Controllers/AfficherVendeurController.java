/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Comparator;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import oussema.Entities.EncapsulationVendeur;
import oussema.Entities.Guide;
import oussema.Entities.Vendeur;
import oussema.Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AfficherVendeurController implements Initializable {

    @FXML
    private TableColumn<Vendeur, String> cl_user_id;
    @FXML
    private TableColumn<Vendeur, String> cl_pays;
    @FXML
    private TableColumn<Vendeur, String> cl_ville;
    @FXML
    private TableColumn<Vendeur, String> cl_image;
    @FXML
    private TableColumn<Vendeur, String> cl_tel;
    @FXML
    private TableColumn<Vendeur, String> cl_nom;
    @FXML
    private TableColumn<Vendeur, String> cl_id;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField in_search;
    @FXML
    private TableColumn<Vendeur, String> cl_type;
    @FXML
    private TableColumn<Vendeur, String> cl_heure_ouverture;
    @FXML
    private TableView<Vendeur> tableVendeur;
    ObservableList<Vendeur> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Vendeur, String> cl_heure_fermeture;
    @FXML
    private ComboBox<String> TriBox;
    @FXML
    private Button btn_csv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TriBox.getItems().addAll(
                "user_id",
                "pays",
                "date"
        );
        search_user();
        tableVendeur.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Vendeur monVendeur = tableVendeur.getSelectionModel().getSelectedItem();
                EncapsulationVendeur encapsulationVendeur = new EncapsulationVendeur(monVendeur.getId(), monVendeur.getUser_id(), monVendeur.getPays(), monVendeur.getVille(), monVendeur.getImage(), monVendeur.getTel(), monVendeur.getNom(), monVendeur.getType(), monVendeur.getHeure_ouverture(), monVendeur.getHeure_fermeture());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/modifierSupprimerVendeur.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/ajouterVendeur.fxml"));
        try {
            Parent root = loader.load();
            in_search.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void search_user() {
        cl_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        cl_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        cl_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        cl_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        cl_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cl_heure_ouverture.setCellValueFactory(new PropertyValueFactory<>("heure_ouverture"));
        cl_heure_fermeture.setCellValueFactory(new PropertyValueFactory<>("heure_fermeture"));
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String requete = "SELECT * FROM vendeur ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Vendeur v = new Vendeur(rs.getInt("id"), rs.getInt("user_id"), rs.getString("pays"), rs.getString("ville"), rs.getString("image"), rs.getInt("tel"), rs.getString("nom"), rs.getString("type"), rs.getTime("heure_ouverture"), rs.getTime("heure_fermeture"));
                obl.add(v);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableVendeur.setItems(obl);
        FilteredList<Vendeur> filteredData = new FilteredList<>(obl, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vendeur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(vendeur.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (vendeur.getPays().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (vendeur.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (vendeur.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(vendeur.getTel()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (vendeur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (vendeur.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (vendeur.getHeure_ouverture().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (vendeur.getHeure_fermeture().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return false; // Does not match.
                }

            });
        });

        tableVendeur.setItems(filteredData);
    }

    @FXML
    private void csv(ActionEvent event) {
        saveHeader("vendeur.csv");
        try {
            String requete = "SELECT * FROM vendeur ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getInt("user_id"), rs.getString("pays"), rs.getString("ville"), rs.getString("image"), rs.getInt("tel"), rs.getString("nom"), rs.getString("type"), rs.getTime("heure_ouverture"), rs.getTime("heure_fermeture"), "vendeur.csv");
            }
            JOptionPane.showMessageDialog(null, "Record saved");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveHeader(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("ID,user_id,pays,ville,image,tel,nom,type,heure_ouverture,heure_fermeture");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int ID, int user_id, String pays, String ville, String image, int tel, String nom, String type, Time heure_ouverture, Time heure_fermeture, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(ID + "," + user_id + "," + pays + "," + ville + "," + image + "," + tel + "," + nom + "," + type + "," + heure_ouverture + "," + heure_fermeture );
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    @FXML
    private void trier(ActionEvent event) {
        Comparator<Vendeur> comparator;
        if (TriBox.getValue() == "user_id") {
            comparator = Comparator.comparingInt(Vendeur::getUser_id);

        } else if (TriBox.getValue() == "pays") {
            comparator = Comparator.comparing(Vendeur::getPays);

        } else {
            comparator = Comparator.comparing(Vendeur::getId);

        }
        FXCollections.sort(obl, comparator);
        
        tableVendeur.setItems(obl);
    }


}
