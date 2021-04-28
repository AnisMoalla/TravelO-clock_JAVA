/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import travelpidev.entities.PostForum;
import travelpidev.services.PostForumService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class FrontAjouterPostForumController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
     @FXML
    private Button btnSave;

    @FXML
    private TextField txtTitre;

    @FXML
    private TextArea txtContent;

    @FXML
    void savePostFront(ActionEvent event) {
        if(event.getSource()==btnSave){
            insertPostForum();
           
        }

    }
    
    
        public void insertPostForum() {
        PostForumService postForumService = new PostForumService();
        PostForum postForum = new PostForum();
        postForum.setTitle(txtTitre.getText());
        postForum.setContenu(txtContent.getText());
        postForumService.add(postForum);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
