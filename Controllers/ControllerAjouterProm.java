package Controllers;

import entites.Promotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Main;
import Services.PromotionService;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAjouterProm implements Initializable {

    @FXML
    private TextField hotel_id;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfpourcentage;
    @FXML
    private DatePicker tfdate_debut;
    @FXML
    private DatePicker tfdate_fin;

    public void AjouterPromotion(ActionEvent actionEvent) {
        Promotion promotion = new Promotion();
        PromotionService promotionService = new PromotionService();

        promotion.setUser_id(1);

        promotion.setNom(tfnom.getText());
        promotion.setHotel_id(2);


        if (tfnom.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING) ;
            alert.setTitle("alert");
            alert.setContentText("Nom is empty");
            alert.show();
            return;
        }

        int pourcentage = Integer.parseInt(tfpourcentage.getText());
        if (pourcentage<0 || pourcentage>100)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("percentage between 0 and 100");
            alert.show();
            return;
        }
        if (tfpourcentage.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("Percentage is empty");
            alert.show();
            return;
        }

        if ((tfdate_debut.getValue() == null) || (tfdate_fin.getValue() == null))
        {

            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("check your date");
            alert.show();
            return;
        }

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = tfdate_debut.getValue();
        Date date_debut = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        promotion.setDate_debut(date_debut);

        ZoneId defaultZoneId1 = ZoneId.systemDefault();
        LocalDate localDate1 = tfdate_fin.getValue();
        Date date_fin = Date.from(localDate1.atStartOfDay(defaultZoneId1).toInstant());
        promotion.setDate_fin(date_fin);
        if (date_debut.compareTo(date_fin) > 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING) ;
            alert.setTitle("alert");
            alert.setContentText("date_debut > date_fin");
            alert.show();
            return;
        }

        promotion.setPourcentage(Integer.parseInt(tfpourcentage.getText()));
        promotionService.add(promotion);

        retour();
    }


    public void retour()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/ListePro.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("List Promotion");
        Main.stage.setScene(new Scene(root, 650, 400));
        Main.stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotel_id.setText("2");
    }
}
