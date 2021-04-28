/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import travelpidev.entities.BadWord;
import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class BadWordService implements IServices<BadWord>{
  Connection cnx = MaConnexion.getInstance().getCnx(); 
  
  
    
    @Override
    public void add(BadWord entity) {
      try {
          String req=" insert into badword(word) VALUES(?)";
          PreparedStatement ps=cnx.prepareStatement(req);
          ps.setString(1, entity.getWord());
          ps.execute();
      } catch (SQLException ex) {
          Logger.getLogger(BadWordService.class.getName()).log(Level.SEVERE, null, ex);
      }
        }

    @Override
    public ObservableList<BadWord> getAll() {
        ObservableList<BadWord> badWords=FXCollections.observableArrayList();
          Statement st = null;
         
    try {
            String req = "SELECT * FROM badword";

            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                BadWord c = new BadWord();
                c.setWord(rst.getString("word"));

                c.setId(rst.getInt("id"));
                badWords.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
      return  badWords;
    }

    @Override
    public void delete(BadWord entity) {
      try {
          String req = "Delete from badword where id=?";
          PreparedStatement ps = cnx.prepareStatement(req);
          ps.setInt(1, entity.getId());
          ps.execute();
      } catch (SQLException ex) {
          System.out.println(ex);      }


    }

    @Override
    public void edit(BadWord entity) {
      try {
          String req="Update badword  Set  word=? where id=?";
          PreparedStatement  ps=cnx.prepareStatement(req);
          ps.setString(1, entity.getWord());
          ps.setInt(2, entity.getId());
      } catch (SQLException ex) {
          System.out.println(ex);      }


    }
    
    
    
    
    
    
}
