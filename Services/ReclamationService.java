package Services;

import entites.Reclamation;
import interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MaConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReclamationService implements IService<Reclamation> {
    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void add(Reclamation entity) {
        try
        {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String currentDateTime = format.format(date);

            String requete = "INSERT INTO reclamation (user_id,evenement_id,description, date_reclamtion, type, etat) "
                    + "VALUES ('"+ entity.getUser_id()+ "','"+ entity.getEvenement_id()+ "','"+ entity.getDescription()+ "','"
                    + currentDateTime+"','"+entity.getType()+"','"+entity.getEtat()+"')";
            Statement st = cnx.createStatement();

            st.executeUpdate(requete);
            System.out.println("Reclamation Ajouteé !! ");
        }catch (Exception e){
            System.out.println(e.getMessage());

        }



    }

    @Override
    public ObservableList getAll() {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        Reclamation reclamation ;
        try {
            String req = "Select * from Reclamation" ;
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next())
            {
                int id = rs.getInt(1);
                int user_id = rs.getInt(2);;
                int evenement_id = rs.getInt(3);;
                String description =rs.getString(4) ;
                Date date_reclamation=rs.getDate(5) ;
                String type = rs.getString(6);
                String etat =rs.getString(7) ;

                reclamation = new Reclamation(description,type,etat,date_reclamation,id,user_id,evenement_id);

                list.add(reclamation);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public void modify(int id , String etat) {
        try {
            String req ="update reclamation set etat = ? where id = ? ";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1,etat);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void modifyProm(Reclamation entity) throws SQLException {

    }


    @Override
    public void delete(int id) {
        try {
            String req = "delete from reclamation where id = ?" ;
            PreparedStatement statement=cnx.prepareStatement(req);
            statement.setInt(1,id);
            int row = statement.executeUpdate();
            System.out.println(row);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }




    @Override
    public Reclamation findByid(int Id) {


        try {
            String req = "Select * from Reclamation where id = "+Id ;
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
                rs.next();
                int id = rs.getInt(1);
                int user_id = rs.getInt(2);;
                int evenement_id = rs.getInt(3);;
                String description =rs.getString(4) ;
                Date date_reclamation=rs.getDate(5) ;
                String type = rs.getString(6);
                String etat =rs.getString(7) ;

                Reclamation reclamation = new Reclamation(description,type,etat,date_reclamation,id,user_id,evenement_id);
                return reclamation;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }



    @Override
    public ObservableList getAll_Byid(int Id) {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        Reclamation reclamation ;
        try {
            String req = "Select * from Reclamation where user_id = "+Id ;
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next())
            {
                int id = rs.getInt(1);
                int user_id = rs.getInt(2);
                int evenement_id = rs.getInt(3);
                String description =rs.getString(4) ;
                Date date_reclamation=rs.getDate(5) ;
                String type = rs.getString(6);
                String etat =rs.getString(7) ;

                reclamation = new Reclamation(description,type,etat,date_reclamation,id,user_id,evenement_id);

                list.add(reclamation);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

}
