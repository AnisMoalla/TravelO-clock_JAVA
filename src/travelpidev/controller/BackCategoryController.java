/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelpidev.entities.Categorie;
import travelpidev.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class BackCategoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtName;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Categorie> tabView;
    @FXML
    private TableColumn<Categorie, Integer> colId;
    @FXML
    private TableColumn<Categorie, String> colName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        showCategory();
        tabView.setOnMouseClicked((events) -> {
        if(events.getClickCount()>1){
            
            txtName.setText(tabView.getSelectionModel().getSelectedItem().getName());
            
        }
        
    });

    }

    @FXML
    public void handelInsertAction(ActionEvent event) {
        System.out.println("add");
        if(event.getSource()==btnInsert){
            insertCategory();
            showCategory();
        }
    }

    @FXML
    public void handelDeleteAction(ActionEvent event) {
        if(event.getSource()==btndelete){
             Categorie categorie=new Categorie();
            categorie.setId(tabView.getSelectionModel().getSelectedItem().getId());
             System.out.println(tabView.getSelectionModel().getSelectedItem().getId());
                
            DeleteCategory(categorie);
            showCategory();
        } 
    }

    @FXML
    public void handelUpdateAction(ActionEvent event) {
 System.out.println("update");
     System.out.println(tabView.getSelectionModel().getSelectedItem().getId());
         if(event.getSource()==btnUpdate){
             Categorie categorie=new Categorie();
            categorie.setId(tabView.getSelectionModel().getSelectedItem().getId());
             System.out.println(tabView.getSelectionModel().getSelectedItem().getId());
                categorie.setName(txtName.getText());
            updateCategory(categorie);
            showCategory();
        } 
    }
public void insertCategory(){
            CategorieService categorieService = new CategorieService();
            Categorie categorie = new Categorie();
            categorie.setName(txtName.getText());
            categorieService.add(categorie);
}
public void updateCategory(Categorie categorie){
    CategorieService categorieService = new CategorieService();
    
            
            categorie.setName(txtName.getText());
            categorieService.edit(categorie);
}
public void DeleteCategory(Categorie categorie){
    System.out.println("delete"+categorie.getId());
    CategorieService categorieService=new CategorieService();
   
    categorieService.delete(categorie);
    
    
}
    public void showCategory() {
        CategorieService categorieService = new CategorieService();

        ObservableList<Categorie> categories;
        categories =  categorieService.getAll();
        colId.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Categorie,String>("name"));
        tabView.setItems(categories);

    }
}
