package Controllers;

import entites.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Main;
import Services.ReclamationService;
import Utils.Mail;
import Utils.SendMail;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerConsulter implements Initializable {
    @FXML
    private TextArea description ;
    @FXML
    private TextField id_user ;
    @FXML
    private TextField id_evenement ;
    @FXML
    private TextArea typeRec ;
    @FXML
    private TextField date ;
    @FXML
    private TextField etatrec ;
    private int id ;


    public void RefuserReclamation(ActionEvent actionEvent) {
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.modify(id,"refuser");

        Reclamation reclamation = reclamationService.findByid(id);
        try {
            SendMail.sendMail("waelbensalem40@gmail.com" , Mail.templatEmailConsulter(reclamation,"refuser"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        retour();

    }

    public void AccepterReclamation(ActionEvent actionEvent) {
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.modify(id,"accepter");
        Reclamation reclamation = reclamationService.findByid(id);
        System.out.println(reclamation);

        try {
            SendMail.sendMail("waelbensalem40@gmail.com" , Mail.templatEmailConsulter(reclamation,"accepter"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        retour();
    }

    public void SupprimerReclamation(ActionEvent actionEvent) {
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.delete(id);

        retour();
    }
    public void add(Reclamation reclamation)
    {
        description.setText(reclamation.getDescription());
        id_user.setText(String.valueOf(reclamation.getUser_id()));
        id_evenement.setText(String.valueOf(reclamation.getEvenement_id()));
        etatrec.setText(reclamation.getEtat());
        date.setText(reclamation.getDate_reclamation().toString());
        typeRec.setText(reclamation.getType());
        id = reclamation.getId();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void retour()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Guis/ListReclamation.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("List_Reclamation");
        Main.stage.setScene(new Scene(root, 660, 400));
        Main.stage.show();
    }
}
