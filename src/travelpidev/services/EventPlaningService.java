/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.services;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelpidev.entities.EventPlaning;

import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class EventPlaningService implements IServices<EventPlaning> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void add(EventPlaning entity) {

        try {
            String req = "INSERT INTO calander(guide_id,title,date_begin,data_fin) VALUES(?,?,?,?) ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getGuideId());
            ps.setString(2, entity.getTitle());
            ps.setDate(3, (java.sql.Date.valueOf(entity.getDateDebut())));
            ps.setDate(4, (java.sql.Date.valueOf(entity.getDateFin())));
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<EventPlaning> getAll() {
         Statement st = null;
        ObservableList<EventPlaning> eventlist =  FXCollections.observableArrayList();
        try {
           String req="Select * from calander";
           st=cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while(rst.next()){
                EventPlaning eventPlaning=new EventPlaning();
                eventPlaning.setId(rst.getInt("id"));
                eventPlaning.setTitle(rst.getString("title"));
                eventPlaning.setGuideId(rst.getInt("guide_id"));
                java.sql.Date sqlDate;
                eventPlaning.setDateDebut(rst.getDate("date_begin").toLocalDate());
                eventPlaning.setDateFin(rst.getDate("data_fin").toLocalDate());
                eventlist.add(eventPlaning);
            }
            
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        
        
        }
        
        return eventlist;
        
    }

    @Override
    public void delete(EventPlaning entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(EventPlaning entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public String getnamebyid(int id){
        Statement st = null;
        
        
        try {
            String req="Select nom,prenom from guide where id=? ";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rstResultSet=ps.executeQuery();
            while(rstResultSet.next()){
                String name=rstResultSet.getString("nom")+" "+rstResultSet.getString("prenom");
                return name;
            }
            
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        return "";
    }
    
    
}
