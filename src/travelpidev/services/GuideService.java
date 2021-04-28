/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelpidev.entities.Guide;
import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class GuideService implements IServices<Guide>{
 Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void add(Guide entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Guide> getAll() {
         ObservableList<Guide> guides = FXCollections.observableArrayList();
        Statement st = null;
        
        try {
            String req = "SELECT * FROM guide";

            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while(rst.next()){
                Guide g=new Guide(rst.getInt("id"), rst.getString("nom"),rst.getString("prenom"));
                guides.add(g);
            }
        } catch (Exception e) {
        }
        
        
        return  guides;
    }

    @Override
    public void delete(Guide entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Guide entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
