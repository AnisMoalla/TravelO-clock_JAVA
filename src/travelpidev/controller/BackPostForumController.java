/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelpidev.entities.PostForum;
import travelpidev.services.PostForumService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class BackPostForumController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtContent;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<PostForum> tableView;
    @FXML
    private TableColumn<PostForum, Integer> ColId;
    @FXML
    private TableColumn<PostForum, String> ColTitle;
    @FXML
    private TableColumn<PostForum, String> ColContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) {
                txtTitle.setText(tableView.getSelectionModel().getSelectedItem().getTitle());
                txtContent.setText(tableView.getSelectionModel().getSelectedItem().getContenu());

            }
        });
        showPosts();
    }

    @FXML
    public void handlerInsertAction(ActionEvent event) {
        System.out.println("add");
        if (event.getSource() == btnInsert) {
            insertPostForum();
            showPosts();
        }
    }

    @FXML
    public void handlerUpdateAction(ActionEvent event) {
        System.out.println("update" + tableView.getSelectionModel().getSelectedItem().getId());
        if (event.getSource() == btnUpdate) {
            PostForum postForum = new PostForum();
            postForum.setId(tableView.getSelectionModel().getSelectedItem().getId());
            postForum.setTitle(txtTitle.getText());
            postForum.setContenu(txtContent.getText());
            updatePostForum(postForum);
            showPosts();
            
               
        }
    }

    @FXML
    public void handlerDeleteAction(ActionEvent event) {
        
 System.out.println("Delete" + tableView.getSelectionModel().getSelectedItem().getId());
        if (event.getSource() == btnDelete) {
            PostForum postForum = new PostForum();
            postForum.setId(tableView.getSelectionModel().getSelectedItem().getId());
            deletePostForum(postForum);
            showPosts();
            
               
        }
    }

    public void insertPostForum() {
        PostForumService postForumService = new PostForumService();
        PostForum postForum = new PostForum();
        postForum.setTitle(txtTitle.getText());
        postForum.setContenu(txtContent.getText());
        postForumService.add(postForum);

    }

    public void updatePostForum(PostForum postForum) {
        PostForumService postForumService = new PostForumService();
        postForum.setTitle(txtTitle.getText());
        postForum.setContenu(txtContent.getText());
        postForumService.edit(postForum);
    }

    public void deletePostForum(PostForum postForum) {
        PostForumService postForumService = new PostForumService();
        postForumService.delete(postForum);

    }

    public void showPosts() {
        PostForumService postForumService = new PostForumService();
        ObservableList list = postForumService.getAll();
        ColId.setCellValueFactory(new PropertyValueFactory<PostForum, Integer>("id"));
        ColTitle.setCellValueFactory(new PropertyValueFactory<PostForum, String>("title"));
        ColContent.setCellValueFactory(new PropertyValueFactory<PostForum, String>("contenu"));
        tableView.setItems(list);
    }

}
