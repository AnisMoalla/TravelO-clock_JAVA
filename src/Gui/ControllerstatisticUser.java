package Gui;

import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerstatisticUser implements Initializable {

    @FXML
    PieChart pieChart ;

    @FXML
    BarChart barChart ;

    @FXML
    CategoryAxis xAxis ;

    @FXML
    NumberAxis yAxis ;

    @FXML
    LineChart lineChart ;

    @FXML
    NumberAxis xAxisLineChart ;

    @FXML
    NumberAxis yAxisLineChart ;

    @FXML
    StackedBarChart stackedBarChart ;

    @FXML
    CategoryAxis  xAxisstackedbarchart ;

    @FXML
    NumberAxis yAxisstackedbarchart ;

    @FXML
    Label caption ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserService userService = new UserService() ;

        // get stat type of users from UserService
        HashMap<String , Integer> stat  = userService.getStatTypeOfUsers() ;

        int numberOfAdmins = stat.get("Admins") ;
        int numberOfServiceProviders = stat.get("ServiceProvider") ;
        int numberOfTourist = stat.get("Tourist") ;

        // PieChart

        PieChart.Data slice1 = new PieChart.Data("Admin", numberOfAdmins);
        PieChart.Data slice2 = new PieChart.Data("Tourist"  , numberOfTourist);
        PieChart.Data slice3 = new PieChart.Data("Service Provider" , numberOfServiceProviders);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                caption.setTranslateX(e.getSceneX()-250);
                caption.setTranslateY(e.getSceneY()-230);

                caption.setText(String.valueOf(data.getPieValue()));
            });

            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
                caption.setText("");
            });
        }

        // barChart

        HashMap<String , Integer> statOfAges = userService.getStatAges() ;
        int statAge_18_25 = statOfAges.get("18-25") ;
        int statAge_25_45 = statOfAges.get("25-45") ;
        int statAge_45_65 = statOfAges.get("45-65") ;
        int statAge_65_100 = statOfAges.get("65-100") ;

        xAxis.setLabel("Number of Users");
        yAxis.setLabel("Age of Users");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data("18-25", statAge_18_25));
        dataSeries1.getData().add(new XYChart.Data("25-45"  , statAge_25_45));
        dataSeries1.getData().add(new XYChart.Data("45-65"  , statAge_45_65));
        dataSeries1.getData().add(new XYChart.Data("65-100"  , statAge_65_100));
        barChart.getData().add(dataSeries1);

        // LineChart

        int[] statMonth = userService.getStatMonths() ;

        xAxisLineChart.setLabel("Months");
        yAxisLineChart.setLabel("Number of new users");

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("2021");

        for(int i = 0 ; i < statMonth.length ; i++)
        {
            dataSeries2.getData().add(new XYChart.Data(i+1, statMonth[i]));
        }

        lineChart.getData().add(dataSeries2);

    }

    public void screenshot(ActionEvent actionEvent) {


    }
}
