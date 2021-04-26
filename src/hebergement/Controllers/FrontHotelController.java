/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.ServiceHotel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontHotelController implements Initializable {

    @FXML
    private ListView liste;
            
      List<Hotel> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceHotel sp = new ServiceHotel();
        list=sp.getAllHotel();
        System.out.println(list);
        List imageList = new ArrayList();
        
        
        for ( int i =0; i < list.size(); i++){
            
//            Image image = new Image("file:/"+list.get(i).getImage());
//        imageView.setImage(image);
           liste.getItems().add("Statut: "+list.get(i).getNom()+"\n Date : "+list.get(i).getDescription()); 
         
          // File file = new File(list.get(i).getImage());
           
           
           imageList.add(list.get(i).getImage());
           
           
           
//                      liste.getItems().add("Statut: "+list.get(i).getStatut()+"\n Compétence:"+list.get(i).getCompétence()+"\n Biographie: "+list.get(i).getBio()); 

        }
        System.out.println("image 1 "+imageList);
        
        liste.setCellFactory(param -> new ListCell<String>() {
            
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
               
            super.updateItem(name, empty);
            if (empty) {
            setText(null);
            setGraphic(null);
            } else {
               
               try{
                   System.out.println("image 2 "+imageList);
                //   System.out.println("image 2 "+imageList);
                  for ( int i = 0; i< imageList.size();i++ ){
                  imageView.setImage(new Image("img/"+imageList.get(i), 100, 100, false, false));
                 
                          
                    setGraphic(imageView);
                    setText(name);
                  }
               }catch(Exception e){
                   System.err.println(e.getMessage());
               }
                
}
}
});
        System.out.println("list"+list);
        System.out.println(list.get(0).getImage());
    }    
    
}
