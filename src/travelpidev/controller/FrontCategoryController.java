/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import travelpidev.entities.Categorie;
import travelpidev.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class FrontCategoryController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane show;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        showCategory();
    }

    private void showCategory() {
        VBox box = new VBox();
        CategorieService cs = new CategorieService();
        ObservableList<Categorie> list = cs.getAll();

        for (Categorie cat : list) {
            Pane p = new Pane();

            Label l = new Label(cat.getName());
            p.getChildren().add(l);
            p.setPadding(new Insets(50));
            l.setStyle("-fx-font-size:15px;-fx-padding:10px");
            p.setStyle("-fx-align-conten:center; -fx-padding:10px;-fx-start-margin:15px;-fx-end-margin:15px;  -fx-border-color: black ;\n"
                    + "    -fx-border-width: 2 ;  -fx-border-style:solid ; -fx-width:100px");
            p.backgroundProperty().setValue(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
            
               
            p.setOnMouseClicked((event) -> {
                   try {
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(".././gui/FrontPostCategory.fxml")) ;
                       
            travelpidev.TravelPidev.primaryStage.setScene(new Scene(fxmlLoader.load()));
                       
             FrontPostCategoryController categoryController = fxmlLoader.getController() ;
             categoryController.setData(cat.getId());
             
        travelpidev.TravelPidev.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
            });

            box.getChildren().add(p);
            Pane p1 = new Pane();
            p.setPrefWidth(800);
            p1.getChildren().add(new Label(" "));
            box.getChildren().add(p1);
        }

        show.getChildren().add(box);

    }

  



}
