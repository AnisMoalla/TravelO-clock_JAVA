/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelpidev.entities.BadWord;
import travelpidev.services.BadWordService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class BackBadWordsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<BadWord> affiche;

    @FXML
    private TableColumn<BadWord, Integer> IDcol;

    @FXML
    private TableColumn<BadWord, String> wordCol;

    @FXML
    private TextField txtWord;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnedit;

    @FXML
    private Button btnDelete;

    @FXML
    void deletebadword(ActionEvent event) {
if(event.getSource()==btnDelete){
    DeleteBadword();
     ShowBadWord();
}
    }

    @FXML
    void editbadword(ActionEvent event) {
if (event.getSource()==btnedit){
    edit();
     ShowBadWord();
}
    }

    @FXML
    void savebadword(ActionEvent event) {
        if (event.getSource() == btnsave) {
            AjouterBadWord();
            ShowBadWord();
        }

    }

    public void ShowBadWord() {
        ObservableList<BadWord> list = FXCollections.observableArrayList();
        BadWordService badWordService = new BadWordService();
        list = badWordService.getAll();
        System.out.println(badWordService.getAll());
        IDcol.setCellValueFactory(new PropertyValueFactory<BadWord, Integer>("id"));
        wordCol.setCellValueFactory(new PropertyValueFactory<BadWord, String>("word"));
        affiche.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowBadWord();
        affiche.setOnMouseClicked((event) -> {
            txtWord.setText(affiche.getSelectionModel().getSelectedItem().getWord());
        });
    }

    private void AjouterBadWord() {
        BadWordService badWordService = new BadWordService();
        BadWord badWord = new BadWord();
        badWord.setWord(txtWord.getText());
        badWordService.add(badWord);
    }

    private void DeleteBadword() {
        BadWordService badWordService = new BadWordService();
        BadWord badWord = new BadWord();
        badWord.setId(affiche.getSelectionModel().getSelectedItem().getId());
        badWordService.delete(badWord);

    }
    private void edit(){
         BadWord badWord = new BadWord();
         BadWordService badWordService = new BadWordService();
        badWord.setId(affiche.getSelectionModel().getSelectedItem().getId());
        badWord.setWord(txtWord.getText());
        badWordService.edit(badWord);
        
    }

}
