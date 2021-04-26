package Controllers;

import entites.Promotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;
import sample.Main;
import Services.PromotionService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControllerConsulterProm {

    @FXML
    private TextField tfnompro ;
    @FXML
    private DatePicker tfdatedebutpro ;
    @FXML
    private DatePicker tfdatefinpro ;
    @FXML
    private TextField tfpourcentagepro;
    private int id ;

    public void ModifierPromotion(ActionEvent actionEvent) {


        if (tfnompro.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING) ;
            alert.setTitle("alert");
            alert.setContentText("Nom is empty");
            alert.show();
            return;
        }
        if (tfpourcentagepro.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("Percentage is empty");
            alert.show();
            return;
        }
        int pourcentage = Integer.parseInt(tfpourcentagepro.getText());
        if (pourcentage<0 || pourcentage>100)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("percentage between 0 and 100");
            alert.show();
            return;
        }
        if ((tfdatedebutpro.getValue() == null) || (tfdatefinpro.getValue() == null))
        {

            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("alert");
            alert.setContentText("check your date");
            alert.show();
            return;
        }

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = tfdatedebutpro.getValue();
        Date date_debut = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        LocalDate localDate1 = tfdatefinpro.getValue();
        Date date_fin = Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant());

        if (date_debut.compareTo(date_fin) > 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING) ;
            alert.setTitle("alert");
            alert.setContentText("date_debut > date_fin");
            alert.show();
            return;
        }


        Promotion promotion = new Promotion(Integer.parseInt(tfpourcentagepro.getText()),tfnompro.getText(),date_debut,date_fin);
        promotion.setId(id);
        PromotionService promotionService = new PromotionService();

        try {
            promotionService.modifyProm(promotion);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        retourr();
    }

    public void retour(ActionEvent actionEvent) {
        retourr();
    }
    public void retourr()
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


    public void add(Promotion promotion)
    {

        id=promotion.getId();
        tfnompro.setText(promotion.getNom());
        tfpourcentagepro.setText(String.valueOf(promotion.getPourcentage()));
        String year = promotion.getDate_debut().toString().split("-")[0];
        String month = promotion.getDate_debut().toString().split("-")[1];
        String day = promotion.getDate_debut().toString().split("-")[2];

        tfdatedebutpro.setValue(LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)));

        String yearfin = promotion.getDate_fin().toString().split("-")[0];
        String monthfin = promotion.getDate_fin().toString().split("-")[1];
        String dayfin = promotion.getDate_fin().toString().split("-")[2];

        tfdatefinpro.setValue(LocalDate.of(Integer.parseInt(yearfin),Integer.parseInt(monthfin),Integer.parseInt(dayfin)));


    }

    public void Supprimer(ActionEvent actionEvent) {
        PromotionService promotionService = new PromotionService();
        try {
            promotionService.delete(id);

        } catch (Exception e) {
        }
        retourr();
    }
}

