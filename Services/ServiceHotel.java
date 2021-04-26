/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hotel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.DataSource;

/**
 *
 * @author LENOVO
 */
public class ServiceHotel {
    
    
    private final Connection cnx;
    private static ServiceHotel instance;
    
    public ServiceHotel() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static ServiceHotel getInstance()
    {
        if (instance == null) {
            instance = new ServiceHotel();
        }
        return instance; 
    }
    
    
  public boolean AjoutHotel (Hotel h){
        
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `hotel`(`nbrechambreDispo`, `nom`,`description`, `adresse`,`nbrechambre`,`nbreEtoile`,`image`,`prix`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement res=cnx.prepareStatement(req);
        
        res.setInt(1, h.getNbrechambreDispo());
        res.setString(2, h.getNom());
        res.setString(3, h.getDescription());
        res.setString(4,h.getAdresse());
        res.setInt(5, h.getNbrechambre());
        res.setInt(6, h.getNbreEtoile());
        res.setString(7,h.getImage());
        res.setFloat(8,h.getPrix());

       
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
            
  
      public ObservableList<Hotel> getAllHotelObs() throws SQLDataException {

        
        List<Hotel> list =new ArrayList<Hotel>();
        int count =0;
        String requete="select * from hotel";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Hotel h = new Hotel();
                         h.setId(rs.getInt(1));
               h.setNom(rs.getString(3));
               h.setDescription(rs.getString(4));
                h.setAdresse(rs.getString(5));
                h.setImage(rs.getString(9));
                h.setNbrechambre(rs.getInt(6));
                h.setNbrechambreDispo(rs.getInt(7));
                h.setNbreEtoile(rs.getInt(8));
                h.setPrix(rs.getFloat(10));
                
                list.add(h);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
            
            
 public List<Hotel> getAllHotel(){
        
        List<Hotel> list = new ArrayList<Hotel>();
        int count =0;
        
        String requete="select * from hotel";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
               Hotel h = new Hotel();
               h.setId(rs.getInt(1));
               h.setNom(rs.getString(3));
               h.setDescription(rs.getString(4));
                h.setAdresse(rs.getString(5));
                h.setImage(rs.getString(9));
                h.setNbrechambre(rs.getInt(6));
                h.setNbrechambreDispo(rs.getInt(7));
                h.setNbreEtoile(rs.getInt(8));
                h.setPrix(rs.getFloat(10));
                
                list.add(h);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         
 }
   
         
         
  public Hotel findHotelById(int id)
    {
        Hotel h = new Hotel();
        int count = 0;
           
        String requete="select * from hotel where id="+id;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
               h.setId(rs.getInt(1));
               h.setNom(rs.getString(3));
               h.setDescription(rs.getString(4));
                h.setAdresse(rs.getString(5));
                h.setImage(rs.getString(9));
                h.setNbrechambre(rs.getInt(6));
                h.setNbrechambreDispo(rs.getInt(7));
                h.setNbreEtoile(rs.getInt(8));
                h.setPrix(rs.getFloat(10));
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return h;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
  
              public boolean deleteHotel (int id) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `hotel` WHERE `id` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    }
              
              
   public boolean ModifierHotel   (Hotel r) throws SQLDataException {

               
                String query = "UPDATE `hotel` SET `nom`=?,`description`=?,`adresse`=?,`nbrechambre`=?,`nbrechambreDispo`=?,`nbreEtoile`=?,`prix`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                
                st.setString(1,r.getNom());
                st.setString(2,r.getDescription());
                st.setString(3, r.getAdresse());
                st.setInt(4,r.getNbrechambre());
                st.setInt(5,r.getNbrechambreDispo());
                st.setInt(6,r.getNbreEtoile());
                st.setFloat(7,r.getPrix());
                st.setInt(8,r.getId());
              
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
           
    
}
         
