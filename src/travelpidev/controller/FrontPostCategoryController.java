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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import travelpidev.entities.PostForum;
import travelpidev.services.PostForumService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class FrontPostCategoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane show;
 @FXML
    private Pane hider;
 
 
 
 
 
    final ScrollBar sc = new ScrollBar();
    public static int catid;

    public void setData(int catid) {
        this.catid = catid;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            showPost();
        });
    }

    public void showPost() {
        hider.backgroundProperty().setValue(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
       
        PostForumService forumService = new PostForumService();
        ObservableList<PostForum> list = forumService.SelectbyCat(catid);
        hider.setStyle("-fx-align-conten:center; -fx-padding:10px;-fx-start-margin:15px;-fx-end-margin:15px;  \n"
                    + "  ;-fx-width:100px");
        VBox box = new VBox();
        
        for (PostForum pf : list) {
            Pane p = new Pane();
            Label l = new Label(pf.getTitle());
               p.setPadding(new Insets(50));
            l.setStyle("-fx-font-size:15px;-fx-padding:10px");
            p.setStyle("-fx-align-conten:center; -fx-padding:10px;-fx-start-margin:15px;-fx-end-margin:15px;  -fx-border-color: black ;\n"
                    + "    -fx-border-width: 2 ;  -fx-border-style:solid ; -fx-width:100px");
            p.backgroundProperty().setValue(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
            
            Label l1 = new Label(pf.getContenu());
            l1.setFont(Font.font("Calibri", FontWeight.LIGHT, 17));
            l1.setMinHeight(70);
            Button b = new Button("Voir plus");
            
            p.getChildren().addAll(l, l1,b);
            b.setOnMouseClicked((event) -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(".././gui/PostView.fxml")) ;
                    travelpidev.TravelPidev.primaryStage.setScene(new Scene(fxmlLoader.load()));
                    PostViewController postViewController = fxmlLoader.getController();
                    postViewController.setData(pf.getId());
                    travelpidev.TravelPidev.primaryStage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(FrontPostCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
                
                
            });

            box.getChildren().add(p);
        }
 sc.setOrientation(Orientation.VERTICAL);
 sc.setPrefHeight(180);
        sc.setMax(360);
        
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                box.setLayoutY(-new_val.doubleValue());
                
            }

        });
        box.setSpacing(30);
        
        show.getChildren().addAll(box,sc);
        
    }

}
