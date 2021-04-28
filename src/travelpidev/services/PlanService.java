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
import travelpidev.entities.Plan;

import travelpidev.interfaces.IServices;
import travelpidev.util.MaConnexion;

/**
 *
 * @author Wofurani
 */
public class PlanService implements IServices<Plan> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void add(Plan entity) {

        try {
            String req = "INSERT INTO plan(name,description) VALUES(?,?)";
            PreparedStatement ps = cnx.prepareCall(req);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDesc());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Plan> getAll() {

        Statement st = null;
        ObservableList<Plan> plans = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM plan";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                Plan plan = new Plan();
                plan.setName(rst.getString("name"));
                plan.setDesc(rst.getString("description"));
                plan.setId(rst.getInt("id"));
                plans.add(plan);
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
        return plans;

    }

    public ObservableList<String> getNames() {
        Statement st = null;
        ObservableList<String> plans = FXCollections.observableArrayList();
        try{
             String req = "SELECT * FROM plan";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while(rst.next()){
                plans.add(rst.getString("name"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return plans;
    }

    @Override
    public void delete(Plan entity) {
        try {
            String req = "DELETE  from  plan WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void edit(Plan entity) {

        try {
            String req = "UPDATE  plan  SET name=? , description=?  WHERE id =?  ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDesc());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
