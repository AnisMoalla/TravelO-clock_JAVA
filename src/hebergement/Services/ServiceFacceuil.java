/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Facceuil;
import Entities.Hotel;
import java.sql.Connection;
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
public class ServiceFacceuil {
    
        
    private final Connection cnx;
    private static ServiceFacceuil instance;
    
    public ServiceFacceuil() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static ServiceFacceuil getInstance()
    {
        if (instance == null) {
            instance = new ServiceFacceuil();
        }
        return instance; 
    }
    
    
    public boolean AjoutFacceuil (Facceuil h){
        
        int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `facceuil`(`user_id`, `nom`,`adresse`, `etat`,`nb_place`,`image`,`description`,`rate`,`vote`) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement res=cnx.prepareStatement(req);
        
        res.setInt(1, h.getId_user());
        res.setString(2, h.getNom());
        res.setString(3, h.getAdresse());
        res.setString(4,h.getEtat());
        res.setInt(5, h.getNbrplace());
        res.setString(6, h.getImage());
        res.setString(7,h.getDescription());
        res.setFloat(9,h.getRate());
        res.setInt(8,h.getVote());

       
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
    
 
             
 public List<Facceuil> getAllFacceuil(){
        
        List<Facceuil> list = new ArrayList<Facceuil>();
        int count =0;
        
        String requete="select * from facceuil";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
               Facceuil h = new Facceuil();
                 h.setId(rs.getInt(1));
                 h.setId_user(rs.getInt(2));
                 h.setNom(rs.getString(3));
                 h.setAdresse(rs.getString(4));
                 h.setEtat(rs.getString(5));
                 h.setNbrplace(rs.getInt(6));
                 h.setImage(rs.getString(7));
                 h.setDescription(rs.getString(8));
                 h.setRate(rs.getFloat(9));
                 h.setVote(rs.getInt(10));
         
                
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
   
 
   
      public ObservableList<Facceuil> getAllFacceuilsObs() throws SQLDataException {

        
        List<Facceuil> list =new ArrayList<Facceuil>();
        int count =0;
        String requete="select * from facceuil";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Facceuil h = new Facceuil();

                 h.setId(rs.getInt(1));
                 h.setId_user(rs.getInt(2));
                 h.setNom(rs.getString(3));
                 h.setAdresse(rs.getString(4));
                 h.setEtat(rs.getString(5));
                 h.setNbrplace(rs.getInt(6));
                 h.setImage(rs.getString(7));
                 h.setDescription(rs.getString(8));
                 h.setRate(rs.getFloat(9));
                 h.setVote(rs.getInt(10));
                
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
            Logger.getLogger(ServiceFacceuil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
    
public Facceuil findFacceuilById(int id)
    {
        Facceuil h = new Facceuil();
        int count = 0;
           
        String requete="select * from facceuil where id="+id;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
                 h.setId(rs.getInt(1));
                 h.setId_user(rs.getInt(2));
                 h.setNom(rs.getString(3));
                 h.setAdresse(rs.getString(4));
                 h.setEtat(rs.getString(5));
                 h.setNbrplace(rs.getInt(6));
                 h.setImage(rs.getString(7));
                 h.setDescription(rs.getString(8));
                 h.setRate(rs.getFloat(9));
                 h.setVote(rs.getInt(10));
         
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
  
              public boolean deleteFacceuil (int id) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `facceuil` WHERE `id` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    }
              
              
   public boolean ModifierFacceuil   (Facceuil r) throws SQLDataException {

               
      String query = "UPDATE `facceuil` SET `nom`=?,`description`=?,`adresse`=?,`nb_place`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                
                st.setString(1,r.getNom());
                st.setString(2,r.getDescription());
                st.setString(3, r.getAdresse());
                st.setInt(4,r.getNbrplace());          
                st.setInt(5,r.getId());
              
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
           
   
   
      public boolean Voter   (Facceuil r) throws SQLDataException {

               
      String query = "UPDATE `facceuil` SET `rate`=?,`vote`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                
                st.setFloat(1,r.getRate());
                st.setInt(2,r.getVote());
                st.setInt(3,r.getId());
              
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
           
   
                
 public List<Facceuil> getAllFacceuilByIdUser(int idU){
        
        List<Facceuil> list = new ArrayList<Facceuil>();
        int count =0;
        
        String requete="select * from facceuil WHERE user_id="+idU;
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
               Facceuil h = new Facceuil();
                 h.setId(rs.getInt(1));
                 h.setId_user(rs.getInt(2));
                 h.setNom(rs.getString(3));
                 h.setAdresse(rs.getString(4));
                 h.setEtat(rs.getString(5));
                 h.setNbrplace(rs.getInt(6));
                 h.setImage(rs.getString(7));
                 h.setDescription(rs.getString(8));
                 h.setRate(rs.getFloat(9));
                 h.setVote(rs.getInt(10));
         
                
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
   
      
}
         
