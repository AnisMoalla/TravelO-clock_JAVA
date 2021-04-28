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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelpidev.entities.PostForum;
import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class PostForumService implements IServices<PostForum> {
  Connection cnx = MaConnexion.getInstance().getCnx();

 

    @Override
    public void add(PostForum entity) {

        try {
            String req = "INSERT INTO post_forum(name,content) VALUES(?,?)";
            PreparedStatement ps = cnx.prepareCall(req);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getContenu());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<PostForum> getAll() {

        Statement st = null;
        ObservableList<PostForum> postforms =  FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM post_forum";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                PostForum p = new PostForum();
                p.setTitle(rst.getString("name"));
                p.setContenu(rst.getString("content"));
                p.setId(rst.getInt("id"));
                postforms.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostForumService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostForumService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  postforms;
    }

    @Override
    public void delete(PostForum entity) {

        try {
            String req = "DELETE  FROM post_forum WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void edit(PostForum entity) {
        try {
            String req = "UPDATE  post_forum  SET name=? , content=?  WHERE id =?  ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getContenu());
            ps.setInt(3, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);        }

    }

    
    public ObservableList<PostForum> SelectbyCat(int cat){
        
        
        
       
        ObservableList<PostForum> postforms =  FXCollections.observableArrayList();
        Statement st = null;
        try {

                            String req = "Select * from post_forum where category_id="+cat ;
            Statement statement = cnx.createStatement();
            ResultSet rst = statement.executeQuery(req);
            while (rst.next()) {
                PostForum p = new PostForum();
                p.setTitle(rst.getString("name"));
                p.setContenu(rst.getString("content"));
                p.setId(rst.getInt("id"));
                p.setCatId(rst.getInt("category_id"));
                postforms.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex);        } 

        return  postforms;

        
        
    }
    
    
    
    
    
    public PostForum findPostById(int idP){
        PostForum postForum= new PostForum();
      try {
          
          
          String req="select * from post_form where id=?";
          PreparedStatement ps = cnx.prepareStatement(req);
          ps.setInt(1, idP);
          ResultSet rst=ps.executeQuery();
          while (rst.next()){
              postForum.setId(rst.getInt("id"));
              postForum.setTitle(rst.getString("name"));
              postForum.setContenu(rst.getString("content"));
              postForum.setCatId(rst.getInt("category_id"));
          }
          
          
          
          
          
          
      } catch (SQLException ex) {
          System.out.println(ex);      }
      
          return postForum;
    }
    
}
