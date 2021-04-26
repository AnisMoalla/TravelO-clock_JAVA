/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facceuil;
import Entities.Hotel;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewFacceuil extends ListCell<Facceuil> {
    
    
     @Override
     public void updateItem(Facceuil e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            FacceuilIemController data = new FacceuilIemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
