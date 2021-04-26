package Gui;

import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ControllerListUsers  implements Initializable {

    @FXML
    Button logoutButton ;

    @FXML
    TableView<User> tableView ;

    @FXML
    TableColumn<User , Integer> idColumn ;

    @FXML
    TableColumn<User,String> nomColumn ;

    @FXML
    TableColumn<User ,String> prenomColumn ;

    @FXML
    TableColumn<User , String> emailColumn ;

    @FXML
    TableColumn<User , Integer> ageColumn ;

    @FXML
    TableColumn<User , Double> numeroColumn ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory("age"));
        numeroColumn.setCellValueFactory(new PropertyValueFactory("numero"));

        UserService userService = new UserService() ;
        List<User> list = userService.getAll() ;
        for(User user : list)
        {
            tableView.getItems().add(user);
        }


    }

    public void Logout(ActionEvent actionEvent) {

        Preferences preferences = Preferences.userRoot() ;
        preferences.remove("user");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 700, 300) ;
        Main.primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../style/styleLogin.css").toExternalForm());

        Main.primaryStage.show();
    }

    public void clicked(MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() == 2)
        {
           User user = tableView.getSelectionModel().getSelectedItem() ;
            FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("../Gui/ModifyUser.fxml")) ;
            try {

                Main.primaryStage.setScene(new Scene(fxmlLoader.load(),800,600));

            } catch (IOException e) {
                e.printStackTrace();
            }
            ControllerModifyUser controllerModifyUser = fxmlLoader.getController() ;
            controllerModifyUser.initData(user);
            Main.primaryStage.show();
        }
    }

    public void stat(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Gui/statisticUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root, 1200, 790));
        Main.primaryStage.show();
    }

    public void excel(ActionEvent actionEvent) {

    }
}
