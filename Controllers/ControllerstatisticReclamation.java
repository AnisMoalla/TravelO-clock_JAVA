package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Main;
import entites.Statistique;
import Services.ReclamationService;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerstatisticReclamation implements Initializable {

    @FXML
    PieChart pieChart ;

    @FXML
    BarChart barChart ;
    @FXML
    BarChart barChart1 ;

    @FXML
    CategoryAxis xAxis ;

    @FXML
    NumberAxis yAxis ;


    @FXML
    Label caption ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ReclamationService reclamationService =new ReclamationService();
        HashMap<String,Integer> map = Statistique.getStatTypeReclamation();


        // PieChart


        PieChart.Data slice1 = new PieChart.Data("Insatisfaction_liée\nau_lieu", map.get("Insatisfaction_liée_au_lieu"));
        PieChart.Data slice2 = new PieChart.Data("horaires_de_début\nnon_respecté"  , map.get("horaires_de_début_non_respecté"));
        PieChart.Data slice3 = new PieChart.Data("Mauvaise_organisation" , map.get("Mauvaise_organisation"));
        PieChart.Data slice4 = new PieChart.Data("other" , map.get("other"));

        pieChart.setTitle("Type de Recalamation");

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);


        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                caption.setTranslateX(e.getSceneX()-260);
                caption.setTranslateY(e.getSceneY()-220);

                caption.setText(String.valueOf(data.getPieValue()));
            });

            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                caption.setText("");
            });
        }

       // barChart
        int[] tab = Statistique.getStatDateReclamation();

        int Summer = tab[6]+tab[7]+tab[8];
        int Autumn  = tab[8]+tab[9]+tab[10];
        int spring  = tab[2]+tab[3]+tab[4];
        int winter  = tab[0]+tab[1]+tab[11];
        xAxis.setLabel("Month");
        yAxis.setLabel("number");
        barChart.setTitle("Number Reclamation/month");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("number reclamation");

        dataSeries1.getData().add(new XYChart.Data("winter", winter));
        dataSeries1.getData().add(new XYChart.Data("spring", spring));
        dataSeries1.getData().add(new XYChart.Data("Summer"  , Summer));
        dataSeries1.getData().add(new XYChart.Data("Autumn"  , Autumn));

        barChart.getData().add(dataSeries1);


        // barChart
        int[] tab1 = Statistique.getStatDatePromotion();

        int Summerpro = tab1[6]+tab1[7]+tab1[8];
        int Autumnpro  = tab1[8]+tab1[9]+tab1[10];
        int springpro  = tab1[2]+tab1[3]+tab1[4];
        int winterpro  = tab1[0]+tab1[1]+tab1[11];
        xAxis.setLabel("Month");
        yAxis.setLabel("number");
        barChart1.setTitle("Number Promotion/month");

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("number reclamation");

        dataSeries2.getData().add(new XYChart.Data("[03-05]"  , springpro));
        dataSeries2.getData().add(new XYChart.Data("[06-08]", Summerpro));
        dataSeries2.getData().add(new XYChart.Data("[09-11]", Autumnpro));
        dataSeries2.getData().add(new XYChart.Data("[12-02]"  , winterpro));

        barChart1.getData().add(dataSeries2);

    }

    public void Back(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/ListReclamation.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("ListReclamation");
        Main.stage.setScene(new Scene(root, 660, 400));
        Main.stage.show();
    }

    public void BackPro(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/ListePro.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("ListPromotion");
        Main.stage.setScene(new Scene(root, 650, 400));
        Main.stage.show();
    }
}
