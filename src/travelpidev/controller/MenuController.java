/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnPlan;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnPostForum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void handlerGoToPlanAction(ActionEvent event) {
        System.out.println(getClass().getResource("BackPlan.fxml").toString());
        if (event.getSource() == btnPlan) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass()
                        .getResource("BackPlan.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    public void handlerGoToCategoryAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource(".././gui/BackCategory.fxml"));

            Scene scene = new Scene(root,800,600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    public void handlerGoToPostForumAction(ActionEvent event) {
        
        try {
            Stage s =(Stage) btnPostForum.getScene().getWindow();
            s.close();
            Parent root = FXMLLoader.load(getClass()
                    .getResource("./gui/BackPostForum.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
