/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oussema.Entities.Guide;
import oussema.Interfaces.IGuide;
import oussema.Utils.MyConnection;

/**
 *
 * @author masso
 */
public class GuideCRUD implements IGuide<Guide>{

    @Override
    public  void ajouterGuide(Guide t) {
        try {
            String requete  = "INSERT INTO `guide` ( `user_id` , `pays`, `ville`, `image`, `tel`, `age`, `sexe`,`nom`,`prenom`,`language`,`vote`,`rate`) VALUES ( '"+t.getUser_id()+"' , '"+t.getPays()+"', '"+t.getVille()+"', '"+t.getImage()+"', '"+t.getTel()+"', '"+t.getAge()+"', '"+t.getSexe()+"' , '"+t.getNom()+"' , '"+t.getPrenom()+"', '"+t.getLanguage()+"', '"+t.getVote()+"', '"+t.getRate()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Guide ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerGuide(Guide t) {
        try {
            String requete = "DELETE FROM guide WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("guide supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierGuide(Guide t) {
        try {
            String requete = " UPDATE guide SET user_id=? , pays=?, ville=?, image=?, tel=? , age=?,sexe=? , nom=?, prenom=?,language=?,vote=?,rate=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,t.getUser_id());
            pst.setString(2,t.getPays());
            pst.setString(3,t.getVille());
            pst.setString(4,t.getImage());
            pst.setInt(5,t.getTel());
            pst.setInt(6,t.getAge());
            pst.setString(7, t.getSexe());
            pst.setString(8, t.getNom());
            pst.setString(9,t.getPrenom());
            pst.setString(10,t.getLanguage());
            pst.setInt(11, t.getVote());
            pst.setDouble(12, t.getRate());
            pst.setInt(13, t.getId());
            pst.executeUpdate();
            System.out.println("guide modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Guide> guidesList() {
        List<Guide> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM guide";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Guide g = new Guide();
                g.setUser_id(rs.getInt("user_id"));
                g.setPays(rs.getString("pays"));
                g.setVille(rs.getString("ville"));
                g.setImage(rs.getString("image"));
                g.setTel(rs.getInt("tel"));
                g.setAge(rs.getInt("age"));
                g.setSexe(rs.getString("sexe"));
                g.setNom(rs.getString("nom"));
                g.setPrenom(rs.getString("prenom"));
                g.setLanguage(rs.getString("language"));
                g.setVote(rs.getInt("vote"));
                g.setRate(rs.getDouble("rate"));
                myList.add(g);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public List<Guide> guidesListTrie() {
        List<Guide> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM guide ORDER BY nom";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Guide g = new Guide();
                g.setUser_id(rs.getInt("user_id"));
                g.setPays(rs.getString("pays"));
                g.setVille(rs.getString("ville"));
                g.setImage(rs.getString("image"));
                g.setTel(rs.getInt("tel"));
                g.setAge(rs.getInt("age"));
                g.setSexe(rs.getString("sexe"));
                g.setNom(rs.getString("nom"));
                g.setPrenom(rs.getString("prenom"));
                g.setLanguage(rs.getString("language"));
                g.setVote(rs.getInt("vote"));
                g.setRate(rs.getDouble("rate"));
                myList.add(g);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public static boolean existe(int id)
    {
        boolean resultat=false;
        try {
            String requete = "SELECT EXISTS(SELECT * FROM guide WHERE id='"+id+"')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            int n = 0;
            if ( rs.next() ) {
                n = rs.getInt(1);
            }
            if (n!=0)
            {
                resultat= true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
    
    
    public static String find(int id)
    {
        String resultat="Votre guide est :" ;
        try {
            String requete = "SELECT nom,prenom FROM guide WHERE id='"+id+"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
           while (rs.next())
            {
                resultat+= rs.getString("nom") + "  " + rs.getString("prenom");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
    
    
}
