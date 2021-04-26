package Services;

import entites.Promotion;
import entites.Reclamation;
import interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MaConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromotionService implements IService<Promotion> {

    Connection cnx = MaConnection.getInstance().getCnx();

    @Override
    public void add(Promotion entity)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDatedebut = format.format(entity.getDate_debut());
        String currentDatefin = format.format(entity.getDate_fin());

        String requete = "INSERT INTO Promotion (hotel_id,nom,date_debut,date_fin,pourcentage,user_id) "
                + "VALUES (?,?,?,?,?,?)";

        PreparedStatement st = null;
        try {
            st = cnx.prepareStatement(requete);
            st.setInt(1,2);
            st.setString(2,entity.getNom());
            st.setString(3,currentDatedebut);
            st.setString(4,currentDatefin);
            st.setInt(5,entity.getPourcentage());
            st.setInt(6,1);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Promotion Ajouteé !! ");



    }

    @Override
    public ObservableList getAll() {
        ObservableList<Promotion> list = FXCollections.observableArrayList();
        Promotion promotion ;

        String req = "Select * from Promotion" ;
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int hotel_id = resultSet.getInt(2);
                String nom = resultSet.getString(3);
                Date date_debut = resultSet.getDate(4);
                Date date_fin = resultSet.getDate(5);
                int pourcentage  = resultSet.getInt(6);
                int user_id = resultSet.getInt(7);


                promotion = new Promotion(id,hotel_id,user_id,pourcentage,nom,date_debut,date_fin);
                list.add(promotion);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public void modify(int id, String e) {



    }

    @Override
    public void modifyProm(Promotion entity) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String currentDatedebut = format.format(entity.getDate_debut());
            String currentDatefin = format.format(entity.getDate_fin());
            String req ="update promotion set nom = ? , date_debut = ? , date_fin = ? , pourcentage = ?   where id = ? ";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1,entity.getNom());
            preparedStatement.setString(2,currentDatedebut);
            preparedStatement.setString(3,currentDatefin);
            preparedStatement.setInt(4,entity.getPourcentage());
            preparedStatement.setInt(5,entity.getId());
            preparedStatement.executeUpdate();

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void delete(int id)  {
        String req = "delete from promotion where id ="+id ;
        try {
            Statement statement = cnx.createStatement();
            statement.execute(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Reclamation findByid(int id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList getAll_Byid(int Id) {
        return null;
    }
}
