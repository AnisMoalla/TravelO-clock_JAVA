/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.awt.datatransfer.DataFlavor;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import travelpidev.entities.EventPlaning;
import travelpidev.entities.Guide;
import travelpidev.entities.Plan;
import travelpidev.services.EventPlaningService;
import travelpidev.services.GuideService;
import travelpidev.services.PlanService;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static travelpidev.TravelPidev.primaryStage;
/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class FrontPlanController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private VBox test;

    @FXML
    private TextField txtTitire;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    private ChoiceBox<Guide> optGuide;

    @FXML
    private Button btnAdd;

    @FXML
    private VBox show;

    @FXML
    void AddPlaningEvent(ActionEvent event) {
        if(event.getSource()==btnAdd){
            addEvent();
            showAllEvent();
        }

    }
    double x;
    double y;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        calenderviewing();
        // TODO
    }

    private void showAllEvent() {
        EventPlaningService eventPlaningService = new EventPlaningService();
        ObservableList<EventPlaning> list = FXCollections.observableArrayList();
        list = eventPlaningService.getAll();
          GuideService gs = new GuideService();
          show.getChildren().clear();
       for (EventPlaning ep : list) {
           
            Pane p = new Pane();
             
            p.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            p.backgroundProperty().setValue(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            p.setStyle("-fx-width:100px;-fx-hight:100px");
            VBox box = new VBox();
            HBox hBox = new HBox();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            
            Label l = new Label("Titre: ");
            l.setPadding(new Insets(0, 0, 10, 10));
            Label l1 = new Label(ep.getTitle());
            hBox.getChildren().addAll(l, l1);
            HBox hBox1 = new HBox();
            Label l2 = new Label("date Debut: ");
            Label l3 = new Label(ep.getDateDebut().toString());
            l2.setPadding(new Insets(0, 0, 10, 10));
            hBox1.getChildren().addAll(l2, l3);
            HBox hBox2 = new HBox();
            Label l4 = new Label("date Fin: ");
            Label l5 = new Label(ep.getDateFin().toString());
            l4.setPadding(new Insets(0, 0, 10, 10));
            hBox2.getChildren().addAll(l4, l5);
            HBox hBox3 = new HBox();
            Label l6 = new Label("Guide: ");
            Label l7 = new Label(eventPlaningService.getnamebyid(ep.getGuideId()));
            l6.setPadding(new Insets(0, 0, 10, 10));
            hBox3.getChildren().addAll(l6, l7);
            box.getChildren().addAll(hBox, hBox1, hBox2, hBox3);
            p.setOnMousePressed((event) -> {
                x = event.getX();
                 y = event.getY();
            });
            
            p.setOnMouseDragged((event) -> {
                Pane pane = (Pane) (Node) event.getSource();
                pane.setTranslateX(event.getX()-x);
                pane.setTranslateY(event.getY()-y);
               
            });
            p.getChildren().addAll(box);
           
            show.backgroundProperty().setValue(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
            show.getChildren().add(p);
            
        }
      

    }
   

    private void addEvent() {
        EventPlaning eventPlaning = new EventPlaning();
        EventPlaningService planingService = new EventPlaningService();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        LocalDate localDate =dateDebut.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        String d=dateFormat.format(Date.from(instant));
        LocalDate localDate1= dateFin.getValue();
        Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
         String d1=dateFormat.format(Date.from(instant1));
        eventPlaning.setDateDebut(localDate);
        eventPlaning.setDateFin(localDate1);
        
        eventPlaning.setTitle(txtTitire.getText());
        eventPlaning.setGuideId(optGuide.getValue().getId());
        System.out.println(eventPlaning);
        planingService.add(eventPlaning);
        
        
        
    }

    private void getAll() {
        ObservableList<Guide> list = FXCollections.observableArrayList();
        GuideService gs = new GuideService();
        list = gs.getAll();
        optGuide.setItems(list);

    }
    public  void calenderviewing(){
         CalendarView calendarView = new CalendarView();

        Calendar katja = new Calendar("Katja");
       

        katja.setShortName("K");
       
       

        katja.setStyle(Style.STYLE1);
        

        CalendarSource familyCalendarSource = new CalendarSource("Family");
        familyCalendarSource.getCalendars().addAll();

        calendarView.getCalendarSources().setAll(familyCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(calendarView); // introPane);

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(1000);
        primaryStage.centerOnScreen();
        primaryStage.show();
        
    }

}
