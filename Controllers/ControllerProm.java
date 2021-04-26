package Controllers;

import entites.Promotion;

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
import Services.PromotionService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerProm implements Initializable {

    @FXML
    private TextField search ;
    @FXML
    private TableColumn<Promotion, Integer> user_id;
    @FXML
    private TableColumn<Promotion, Integer> hotel_id;
    @FXML
    private TableColumn<Promotion, String> nom;
    @FXML
    private TableColumn<Promotion, Integer> pourcentage;
    @FXML
    private TableColumn<Promotion, Date> date_debut;
    @FXML
    private TableColumn<Promotion, Date> date_fin;
    @FXML
    private TableView<Promotion> tablePromotion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        user_id.setCellValueFactory(new PropertyValueFactory("user_id"));
        hotel_id.setCellValueFactory(new PropertyValueFactory("hotel_id"));
        nom.setCellValueFactory(new PropertyValueFactory("nom"));
        pourcentage.setCellValueFactory(new PropertyValueFactory("pourcentage"));
        date_debut.setCellValueFactory(new PropertyValueFactory("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory("date_fin"));


        PromotionService promotionService = new PromotionService();

        ObservableList<Promotion> list = promotionService.getAll();


        FilteredList<Promotion> promotions = new FilteredList<>(list, b->true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            promotions.setPredicate(promotion -> {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                String lowercase = newValue.toLowerCase();

                if (promotion.getNom().toLowerCase().indexOf(lowercase) != -1)
                {
                    return true;
                }else if (String.valueOf(promotion.getPourcentage()).indexOf(lowercase) != -1)
                {
                    return true;
                }else if (String.valueOf(promotion.getDate_debut()).indexOf(lowercase) != -1)
                {
                    return true;
                }else if (String.valueOf(promotion.getDate_fin()).indexOf(lowercase) != -1)
                {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<Promotion> sortedprom = new SortedList<>(promotions);
        sortedprom.comparatorProperty().bind(tablePromotion.comparatorProperty());
        tablePromotion.setItems(sortedprom);
    }

    public void ConsulterProm(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount()==2)
        {

            FXMLLoader fxmlLoader= null ;

            try {

                fxmlLoader = new FXMLLoader(getClass().getResource("../Guis/ConsulterPromotion.fxml"));
                Main.stage.setScene(new Scene(fxmlLoader.load(), 600, 575));


            } catch (Exception e) {
                e.getMessage();
            }

            Promotion promotion = tablePromotion.getSelectionModel().getSelectedItem();

            ControllerConsulterProm controllerConsulterProm  = fxmlLoader.getController();
            controllerConsulterProm.add(promotion);
            Main.stage.setTitle("Promotion");

            Main.stage.show();

        }
    }

    public void AjouterProm(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/AjouterProm.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("Ajouter Promotion");
        Main.stage.setScene(new Scene(root, 600, 575));
        Main.stage.show();
    }

    public void test(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/statistique.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setScene(new Scene(root, 1200, 750));
        Main.stage.show();
    }
}


