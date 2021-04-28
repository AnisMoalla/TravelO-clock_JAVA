/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class PostViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int Idpost;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Platform.runLater(() -> {
             System.out.println(Idpost);
        });
    }    
    
    
    public void setData(int id){
        this.Idpost=id;
    }
    
    
    
    
    
}
