package Controllers;

import entites.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import sample.Main;
import Services.ReclamationService;
import Utils.Mail;
import Utils.SendMail;

import java.util.Date;

public class ControllerAjouter {


    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private TextArea description;


    @FXML
    public void Ajoutereclamation(ActionEvent actionEvent) throws Exception {

        Reclamation r = new Reclamation();
        ReclamationService reclamationService =  new ReclamationService();

        r.setUser_id(1);
        r.setEvenement_id(1);
        r.setDate_reclamation(new Date());
        r.setEtat("attente");

        r.setDescription(description.getText());

        if(rb1.isSelected())
        {
            r.setType(rb1.getText());
        }else if(rb2.isSelected())
        {
            r.setType(rb2.getText());
        }else if(rb3.isSelected())
        {
            r.setType(rb3.getText());
        }else if(rb4.isSelected())
        {
            r.setType(rb4.getText());
            if (description.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING) ;
                alert.setTitle("alert");
                alert.setContentText("Description is empty");
                alert.show();
                return;
            }
        }
        reclamationService.add(r);

        SendMail.sendMail("waelbensalem40@gmail.com" , Mail.templatEmailReclamation(r));

        retour();


    }

    public void Back(ActionEvent actionEvent) {
        retour();
    }
    public void retour()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Guis/ListeReclamationFront.fxml"));
        } catch (Exception e) {
            e.getMessage();
        }
        Main.stage.setTitle("List Reclamation");
        Main.stage.setScene(new Scene(root, 660, 400));
        Main.stage.show();
    }
}
