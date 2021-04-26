package Controllers;

import entites.Reclamation;
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
import javafx.scene.input.MouseEvent;
import sample.Main;
import Services.ReclamationService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ControllerAffReclamationFront implements Initializable{


        @FXML
        private TableColumn<Reclamation, Integer> user_id;
        @FXML
        private TableColumn<Reclamation, Integer> evenement_id;
        @FXML
        private TableColumn<Reclamation, Date> date_reclamation;
        @FXML
        private TableColumn<Reclamation, String> descritpiontab;
        @FXML
        private TableColumn<Reclamation, String> typeReclamation;
        @FXML
        private TableColumn<Reclamation, String> etatReclamation;
        @FXML
        private TextField search ;



        @FXML
        private TableView<Reclamation> tableReclamation;

        @Override
        public void initialize(URL url, ResourceBundle rb) {


            user_id.setCellValueFactory(new PropertyValueFactory("user_id"));
            evenement_id.setCellValueFactory(new PropertyValueFactory("evenement_id"));
            descritpiontab.setCellValueFactory(new PropertyValueFactory("description"));
            date_reclamation.setCellValueFactory(new PropertyValueFactory("date_reclamation"));
            typeReclamation.setCellValueFactory(new PropertyValueFactory("type"));
            etatReclamation.setCellValueFactory(new PropertyValueFactory("etat"));


            Preferences preferences = Preferences.userRoot() ;
            int id_user = preferences.getInt("user",0);
            System.out.println(id_user);
            if (id_user!=0)
            {
                ReclamationService reclamationService = new ReclamationService();

                ObservableList<Reclamation> list = reclamationService.getAll_Byid(id_user);



                FilteredList<Reclamation> reclamations = new FilteredList<>(list, b->true);

                search.textProperty().addListener((observable, oldValue, newValue) -> {
                    reclamations.setPredicate(reclamation -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowercase = newValue.toLowerCase();

                        if (reclamation.getDescription().toLowerCase().indexOf(lowercase) != -1) {
                            return true;
                        } else if (reclamation.getType().toLowerCase().indexOf(lowercase) != -1) {
                            return true;
                        } else if (reclamation.getEtat().toLowerCase().indexOf(lowercase) != -1) {
                            return true;
                        }else if (String.valueOf(reclamation.getDate_reclamation()).indexOf(lowercase) != -1)
                        {
                            return true;
                        }else
                            return false;
                    });
                });

                SortedList<Reclamation> sortedreclamation = new SortedList<>(reclamations);
                sortedreclamation.comparatorProperty().bind(tableReclamation.comparatorProperty());
                tableReclamation.setItems(sortedreclamation);
            }

        }




        public void Ajouter(ActionEvent actionEvent) {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Guis/sample.fxml"));
            } catch (Exception e) {
                e.getMessage();
            }
            Main.stage.setTitle("Ajouter Reclamation");
            Main.stage.setScene(new Scene(root, 600, 450));
            Main.stage.show();
        }

        public void Back(ActionEvent actionEvent) {

            retour();

        }
        public void retour()
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Guis/ListeReclamationFront.fxml"));
            } catch (Exception e) {
                e.getMessage();
            }
            Main.stage.setTitle("List Reclamation");
            Main.stage.setScene(new Scene(root, 660, 400));
            Main.stage.show();
        }


        public void Supprimer(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount()==2)
            {


                Reclamation reclamation = tableReclamation.getSelectionModel().getSelectedItem();
                int id = reclamation.getId();
                ReclamationService reclamationService = new ReclamationService();
                reclamationService.delete(id);

                retour();
            }
        }



}

