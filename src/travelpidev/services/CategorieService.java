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
import travelpidev.entities.Categorie;
import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class CategorieService implements IServices<Categorie> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void add(Categorie entity) {
        try {
            String req = "INSERT INTO category(name) VALUES(?) ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, entity.getName());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public ObservableList<Categorie> getAll() {
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        Statement st = null;
        try {
            String req = "SELECT * FROM category";

            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                Categorie c = new Categorie();
                c.setName(rst.getString("name"));

                c.setId(rst.getInt("id"));
                categories.add(c);
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
        
        return categories;
    }

    @Override
    public void delete(Categorie entity) {

        try {
            String req = "DELETE  FROM  category WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void edit(Categorie entity) {
        try {
            String req = "UPDATE  category  SET name= ?   WHERE id = ?  ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, entity.getName());

            ps.setInt(2, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}


