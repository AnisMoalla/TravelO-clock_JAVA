


package Controllers;


import Services.PromotionService;

import entites.Promotion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAffPromFront implements Initializable {

        @FXML
        ScrollPane scrol ;


        int i = 0 ;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            VBox root = new VBox();
            PromotionService promotion = new PromotionService() ;
            List<Promotion> list = promotion.getAll() ;
            root.getStylesheets().add("Styles/layoutstyles.css");
            scrol.setPadding(new Insets(5));

            for (Promotion u: list) {

                Pane pane = new Pane() ;
                pane.setId("pane");

                Label nom = new Label(u.getNom()) ;
                nom.setId("nom");
                nom.setLayoutX(270);
                nom.setLayoutY(10);

                Label lpourcentage = new Label("Pourcentage :") ;
                lpourcentage.setLayoutX(10);
                lpourcentage.setLayoutY(50);
                lpourcentage.setId("titre");
                lpourcentage.setTextFill(Color.color(1, 0, 0));

                Label pourcentage = new Label(String.valueOf(u.getPourcentage())) ;
                pourcentage.setLayoutX(120);
                pourcentage.setLayoutY(50);
                pane.setPrefSize(600, 150);




                pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                //separator.setHalignment(HPos.CENTER);




                pane.getChildren().addAll(nom,lpourcentage,pourcentage);
                root.getChildren().addAll(pane);
                i++;
            }

            root.setSpacing(10);
            scrol.setContent(root);
        }
    }
