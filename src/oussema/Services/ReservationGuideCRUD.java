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
import oussema.Entities.Guide;
import oussema.Entities.ReservationGuide;
import oussema.Interfaces.IGuide;
import oussema.Interfaces.IReservationGuide;
import oussema.Utils.MyConnection;

/**
 *
 * @author masso
 */
public class ReservationGuideCRUD implements IReservationGuide<ReservationGuide>{

    @Override
    public void ajouterReservationGuide(ReservationGuide t) {
        try {
            String requete  = "INSERT INTO `reservationguide` ( `id_guide` , `id_touriste`, `date_reservation`) VALUES ( '"+t.getId_guide()+"' , '"+t.getId_touriste()+"', '"+t.getDate_reservation()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("resvation guide ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReservationGuide(ReservationGuide t) {
        try {
            String requete = "DELETE FROM reservationguide WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Reservation guide supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierReservationGuide(ReservationGuide t) {
        try {
            String requete = " UPDATE reservationguide SET id_guide=? , id_touriste=?, date_reservation=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,t.getId_guide());
            pst.setInt(2,t.getId_touriste());
            pst.setDate(3,t.getDate_reservation());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("Reservation guide modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ReservationGuide> reservationGuidesList() {
        List<ReservationGuide> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM reservationguide";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ReservationGuide rg = new ReservationGuide();
                rg.setId_guide(rs.getInt("id_guide"));
                rg.setId_touriste(rs.getInt("id_touriste"));
                rg.setDate_reservation(rs.getDate("date_reservation"));
                myList.add(rg);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public List<ReservationGuide> reservationGuidesListTrie() {
        List<ReservationGuide> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM reservationguide ORDER BY nom";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ReservationGuide rg = new ReservationGuide();
                rg.setId_guide(rs.getInt("id_guide"));
                rg.setId_touriste(rs.getInt("id_touriste"));
                rg.setDate_reservation(rs.getDate("date_reservation"));
                myList.add(rg);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
}
