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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import oussema.Entities.EncapsulationGuide;
import oussema.Entities.Guide;
import oussema.Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author masso
 */
public class AfficherGuideController implements Initializable {

    @FXML
    private TableView<Guide> tableGuide;
    @FXML
    private TableColumn<Guide, String> cl_user_id;
    @FXML
    private TableColumn<Guide, String> cl_pays;
    @FXML
    private TableColumn<Guide, String> cl_ville;
    @FXML
    private TableColumn<Guide, String> cl_image;
    @FXML
    private TableColumn<Guide, String> cl_tel;
    @FXML
    private TableColumn<Guide, String> cl_age;
    @FXML
    private TableColumn<Guide, String> cl_sexe;
    @FXML
    private TableColumn<Guide, String> cl_nom;
    @FXML
    private TableColumn<Guide, String> cl_prenom;
    @FXML
    private TableColumn<Guide, String> cl_language;
    @FXML
    private TableColumn<Guide, String> cl_vote;
    @FXML
    private TableColumn<Guide, String> cl_rate;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajouter;

    ObservableList<Guide> obl = FXCollections.observableArrayList();
    @FXML
    private TextField in_search;
    @FXML
    private TableColumn<Guide, String> cl_id;
    @FXML
    private ComboBox<String> triBox;
    @FXML
    private Button btn_csv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        triBox.getItems().addAll(
                "user_id",
                "pays",
                "date"
        );
        search_user();
        tableGuide.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Guide monGuide = tableGuide.getSelectionModel().getSelectedItem();
                EncapsulationGuide encapsulationGuide = new EncapsulationGuide(monGuide.getId(), monGuide.getUser_id(), monGuide.getPays(), monGuide.getVille(), monGuide.getImage(), monGuide.getTel(), monGuide.getAge(), monGuide.getSexe(), monGuide.getNom(), monGuide.getPrenom(), monGuide.getLanguage(), monGuide.getVote(), monGuide.getRate());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/modifierSupprimerGuide.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Guis/ajouterGuide.fxml"));
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
        cl_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        cl_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cl_language.setCellValueFactory(new PropertyValueFactory<>("language"));
        cl_vote.setCellValueFactory(new PropertyValueFactory<>("vote"));
        cl_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String requete = "SELECT * FROM guide ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Guide g = new Guide(rs.getInt("id"), rs.getInt("user_id"), rs.getString("pays"), rs.getString("ville"), rs.getString("image"), rs.getInt("tel"), rs.getInt("age"), rs.getString("sexe"), rs.getString("nom"), rs.getString("prenom"), rs.getString("language"), rs.getInt("vote"), rs.getDouble("rate"));
                obl.add(g);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableGuide.setItems(obl);
        FilteredList<Guide> filteredData = new FilteredList<>(obl, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(guide -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(guide.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (guide.getPays().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (guide.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (guide.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(guide.getTel()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(guide.getAge()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (guide.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (guide.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (guide.getLanguage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(guide.getVote()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(guide.getRate()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else {
                    return false; // Does not match.
                }

            });
        });

        tableGuide.setItems(filteredData);
    }

    @FXML
    private void trier(ActionEvent event) {
        Comparator<Guide> comparator;
        if (triBox.getValue() == "user_id") {
            comparator = Comparator.comparingInt(Guide::getUser_id);

        } else if (triBox.getValue() == "pays") {
            comparator = Comparator.comparing(Guide::getPays);

        } else {
            comparator = Comparator.comparingInt(Guide::getId);

        }

        FXCollections.sort(obl, comparator);
        tableGuide.setItems(obl);

    }

    @FXML
    private void csv(ActionEvent event) {
        saveHeader("guide.csv");
        try {
            String requete = "SELECT * FROM guide ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getInt("user_id"), rs.getString("pays"), rs.getString("ville"), rs.getString("image"), rs.getInt("tel"), rs.getInt("age"), rs.getString("sexe"), rs.getString("nom"), rs.getString("prenom"), rs.getString("language"), rs.getInt("vote"), rs.getDouble("rate"), "guide.csv");
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

            pw.println("ID,user_id,pays,ville,image,tel,age,sexe,nom,prenom,language,vote,rate");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int ID, int user_id, String pays, String ville, String image, int tel, int age, String sexe, String nom, String prenom, String language, int vote, double rate, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(ID + "," + user_id + "," + pays + "," + ville + "," + image + "," + tel + "," + age + "," + sexe + "," + nom + "," + prenom + "," + language + "," + vote + "," + rate);
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

}
