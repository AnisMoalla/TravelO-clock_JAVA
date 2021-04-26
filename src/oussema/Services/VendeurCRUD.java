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
import oussema.Entities.Vendeur;
import oussema.Interfaces.IVendeur;
import oussema.Utils.MyConnection;

/**
 *
 * @author masso
 */
public class VendeurCRUD implements IVendeur<Vendeur>{

    @Override
    public void ajouterVendeur(Vendeur t) {
        try {
            String requete  = "INSERT INTO `vendeur` ( `id`,`user_id` , `pays`, `ville`, `image`, `tel`,`nom`,`type`,`heure_ouverture`,`heure_fermeture`) VALUES (NULL, '"+t.getUser_id()+"' , '"+t.getPays()+"', '"+t.getVille()+"', '"+t.getImage()+"', '"+t.getTel()+"', '"+t.getNom()+"' , '"+t.getType()+"', '"+t.getHeure_ouverture()+"', '"+t.getHeure_fermeture()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("Vendeur ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerVendeur(Vendeur t) {
        try {
            String requete = "DELETE FROM vendeur WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Vendeur supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierVendeur(Vendeur t) {
        try {
            String requete = " UPDATE vendeur SET user_id=? , pays=?, ville=?, image=?, tel=? , nom=?, type=? ,heure_ouverture=?,heure_fermeture=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,t.getUser_id());
            pst.setString(2,t.getPays());
            pst.setString(3,t.getVille());
            pst.setString(4,t.getImage());
            pst.setInt(5,t.getTel());
            pst.setString(6, t.getNom());
            pst.setString(7,t.getType());
            pst.setTime(8, t.getHeure_ouverture());
            pst.setTime(9, t.getHeure_fermeture());
            pst.setInt(10, t.getId());
            pst.executeUpdate();
            System.out.println("vendeur modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Vendeur> vendeursList() {
        List<Vendeur> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM vendeur";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Vendeur v = new Vendeur();
                v.setUser_id(rs.getInt("user_id"));
                v.setPays(rs.getString("pays"));
                v.setVille(rs.getString("ville"));
                v.setImage(rs.getString("image"));
                v.setTel(rs.getInt("tel"));
                v.setNom(rs.getString("nom"));
                v.setType(rs.getString("type"));
                v.setHeure_ouverture(rs.getTime("heure_ouverture"));
                v.setHeure_fermeture(rs.getTime("heure_fermeture"));
                myList.add(v);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public List<Vendeur> vendeursListTrie() {
        List<Vendeur> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM vendeur ORDER BY nom";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Vendeur v = new Vendeur();
                v.setUser_id(rs.getInt("user_id"));
                v.setPays(rs.getString("pays"));
                v.setVille(rs.getString("ville"));
                v.setImage(rs.getString("image"));
                v.setTel(rs.getInt("tel"));
                v.setNom(rs.getString("nom"));
                v.setType(rs.getString("type"));
                v.setHeure_ouverture(rs.getTime("heure_ouverture"));
                v.setHeure_fermeture(rs.getTime("heure_fermeture"));
                myList.add(v);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
}
