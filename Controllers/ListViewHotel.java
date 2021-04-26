/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewHotel extends ListCell<Hotel> {
    
    
     @Override
     public void updateItem(Hotel e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            HoteltemController data = new HoteltemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
