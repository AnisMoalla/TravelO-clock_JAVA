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
import travelpidev.entities.Comment;
import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class CommentService implements IServices<Comment> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void add(Comment entity) {
        try {
            String req = "Insert into commentaire(id,post_f_id,content) Values(?,?,?)  ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getId());
            ps.setString(3, entity.getContent());
            ps.setInt(2, entity.getIdPost());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Comment> getAll() {
        ObservableList<Comment> list = FXCollections.observableArrayList();
        Statement s = null;
        try {

            String req = "select * from commentaire";
            s = cnx.createStatement();
            ResultSet rst = s.executeQuery(req);
            while (rst.next()) {
                Comment comment = new Comment();
                comment.setContent(rst.getString("content"));
                comment.setId(rst.getInt("id"));
                comment.setIdPost(rst.getInt("post_f_id"));
                list.add(comment);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    @Override
    public void delete(Comment entity) {
        try {
            String req = "delete from commentaire where id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void edit(Comment entity) {

        try {
            String req = "update commentaire set content=? where id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(2, entity.getId());
            ps.setString(1, entity.getContent());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Comment> getCommentByPost(int idPost) {
        ObservableList<Comment> list = FXCollections.observableArrayList();
        Statement s = null;
        try {

            String req = "select * from commentaire where post_f_id="+idPost;
            s = cnx.createStatement();
           

            ResultSet rst = s.executeQuery(req);
            while (rst.next()) {
                Comment comment = new Comment();
                comment.setContent(rst.getString("content"));
                comment.setId(rst.getInt("id"));
                comment.setIdPost(rst.getInt("post_f_id"));
                list.add(comment);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

}
