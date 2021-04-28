/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JFileChooser;
import travelpidev.entities.Plan;
import travelpidev.services.PlanService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class BackPlanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescrip;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Plan> tableView;
    @FXML
    private TableColumn<Plan, Integer> ColId;
    @FXML
    private TableColumn<Plan, String> ColName;
    @FXML
    private TableColumn<Plan, String> ColDescrip;
    @FXML
    private Button pdfprint;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPlans();
        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) {
                txtName.setText(tableView.getSelectionModel().getSelectedItem().getName());
                txtDescrip.setText(tableView.getSelectionModel().getSelectedItem().getDesc());
            }
        });

    }

    @FXML
    public void handlerInsertAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertPlan();
            showPlans();
        }

    }

    @FXML
    public void handlerUpdateAction(ActionEvent event) {
        if (event.getSource() == btnUpdate) {

            Plan plan = new Plan();
            plan.setId(tableView.getSelectionModel().getSelectedItem().getId());
            plan.setDesc(txtDescrip.getText());
            plan.setName(txtName.getText());
            updatePlan(plan);
            showPlans();
        }
    }
@FXML
    void printpdfFunci(ActionEvent event) {
        if(event.getSource()==pdfprint){
            printPDF();
            System.out.println("test");
        }

    }
    @FXML
    public void handlerDeleteAction(ActionEvent event) {
        if (event.getSource() == btnDelete) {
            Plan plan = new Plan();
            plan.setId(tableView.getSelectionModel().getSelectedItem().getId());
            deletePlan(plan);
            showPlans();

        }

    }

    public void insertPlan() {
        System.out.println("add");
        PlanService planService = new PlanService();
        Plan plan = new Plan();
        plan.setName(txtName.getText());
        plan.setDesc(txtDescrip.getText());
        planService.add(plan);

    }

    public void updatePlan(Plan plan) {
        System.out.println("update");
        PlanService planService = new PlanService();

        planService.edit(plan);

    }

    public void deletePlan(Plan plan) {
        PlanService planService = new PlanService();
        planService.delete(plan);

    }

    public void showPlans() {
        PlanService planService = new PlanService();
        ObservableList<Plan> list = planService.getAll();
        ColId.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<Plan, String>("name"));
        ColDescrip.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableView.setItems(list);

    }
    

    private void printPDF() {
        String path = "C:\\Users\\Wofurani\\Desktop/test.pdf";
        

       
        
        try {Document d = new Document();
            PdfWriter.getInstance(d, new FileOutputStream(path));
            System.out.println(path);
            d.open();
            PdfPTable pTable = new PdfPTable(3);
            pTable.addCell("id");
            pTable.addCell("name");
            pTable.addCell("description");
            
                tableView.getItems().forEach((t) -> {
                    pTable.addCell(String.valueOf(t.getId()) );
                    pTable.addCell(t.getName());
                    pTable.addCell(t.getDesc());
                try {
                    d.add(pTable);
                } catch (DocumentException ex) {
                    System.out.println(ex);                }
                });
            d.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            System.out.println(ex);
        }

    }

}
