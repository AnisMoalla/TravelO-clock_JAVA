package Services;

import Entities.User;
import Interfaces.IService;
import Utils.BCrypt;
import Utils.MaConnection;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class UserService implements IService<User> {
    Connection cnx = MaConnection.getInstance().getCnx() ;

    @Override
    public void Modify(User entity) {
        String password = BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt(13));
        password = password.replaceFirst("2a", "2y") ;

        String req = "update user set nom = ? , prenom = ? ,email = ? ,password = ? ,age = ? ," +
                "numero = ? where id = ? " ;

        try {
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1,entity.getNom());
            statement.setString(2,entity.getPrenom());
            statement.setString(3,entity.getEmail());
            statement.setString(4,password);
            statement.setInt(5,entity.getAge());
            statement.setDouble(6,entity.getNumero());
            statement.setInt(7,entity.getId());
            statement.executeUpdate() ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // this function to add tourist
    @Override
    public void add(User entity) {
        String password = BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt(13));
        password = password.replaceFirst("2a", "2y") ;
        String req = "insert into user (nom,prenom,email,password,age,numero,roles,created_at,activationtoken,verified,etat) value (?,?,?,?,?,?,?,?,?,?,?) " ;

        // set the role of the user
        JsonArray jsonArray = new JsonArray() ;
        jsonArray.add(new JsonString().setValue("ROLE_TOURIST"));
        String role = jsonArray.toFormattedString();

        // set activation token
        Random random = new Random() ;
        int token = random.nextInt(30000000) ;

        // not activated yet
        String verified = "false" ;

        String etat = "valid" ;

        // set the current date
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date) ;

        try {
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1,entity.getNom());
            statement.setString(2,entity.getPrenom());
            statement.setString(3,entity.getEmail());
            statement.setString(4,password);
            statement.setInt(5,entity.getAge());
            statement.setDouble(6,entity.getNumero());
            statement.setString(7,role);
            statement.setString(8,currentDateTime);
            statement.setString(9,String.valueOf(token));
            statement.setString(10,verified);
            statement.setString(11,etat);
            statement.executeUpdate() ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public String getActivationCode(String email)
    {
        String activationCode = "" ;
        String req = "select * from user where email = ?" ;
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req) ;
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery() ;
            resultSet.next() ;
            activationCode = resultSet.getString(15) ;
            return activationCode;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activationCode ;

    }

    @Override
    public List<User> getAll() {
        User user ;
        List<User> list = new ArrayList<>() ;
        String req = "select * from user " ;
        try {
            Statement statement = cnx.createStatement() ;
            ResultSet resultSet = statement.executeQuery(req) ;
            while (resultSet.next())
            {
                int id = resultSet.getInt(1) ;
                String nom = resultSet.getString(2) ;
                String prenom = resultSet.getString(3) ;
                String email = resultSet.getString(4) ;
                String role = resultSet.getString(11);
                int age = resultSet.getInt(6) ;
                Date date = resultSet.getDate(13);
                Double numero = resultSet.getDouble(9) ;

                user = new User() ;
                user.setId(id);
                user.setRoles(role);
                user.setEmail(email);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setAge(age);
                user.setCreated_at(date);
                user.setNumero(numero);

                list.add(user) ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public void delete(int id) {
        String req = "delete from user where id = "+id ;
        try {
            Statement statement = cnx.createStatement();
            statement.execute(req) ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public User getEntity(int id) {
        return null;
    }

    public boolean checkEmail(String email)
    {
        String req = "select * from user where email = '"+email+"'" ;
        try {
            Statement statement = cnx.createStatement() ;
            ResultSet resultSet = statement.executeQuery(req) ;
            return resultSet.next() ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false ;
    }


    public User getUser(String email , String password)
    {
        User user = null ;
        String req = "select * from user where email = '"+email+"'" ;
        try {
            Statement statement = cnx.createStatement() ;
            ResultSet resultSet = statement.executeQuery(req) ;
            if (resultSet.next())
            {
             int id = resultSet.getInt(1) ;
             String hashedPasswordDb = resultSet.getString(5) ;
             String role = resultSet.getString(11) ;
             String verified = resultSet.getString(16) ;
             hashedPasswordDb = hashedPasswordDb.replaceFirst("2y", "2a");
             if (BCrypt.checkpw(password,hashedPasswordDb))
             {
                 user = new User() ;
                 user.setId(id);
                 user.setEmail(email);
                 user.setVerified(verified);
                 user.setRoles(role);
             }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user ;
    }

    public HashMap<String , Integer> getStatTypeOfUsers()
    {
        List<User> list = this.getAll() ;
        HashMap<String , Integer> stat = new HashMap<>() ;
        stat.put("Admins",0) ;
        stat.put("ServiceProvider",0) ;
        stat.put("Tourist",0) ;

        for (User user: list) {
            if (user.getRoles().contains("ADMIN"))
                stat.put("Admins",stat.get("Admins")+1);
            else if (user.getRoles().contains("TOURIST"))
                stat.put("Tourist",stat.get("Tourist")+1);
            else
                stat.put("ServiceProvider",stat.get("ServiceProvider")+1) ;
        }
        return stat ;
    }

    public HashMap<String , Integer> getStatAges()
    {
        List<User> list = this.getAll() ;
        HashMap<String , Integer> stat = new HashMap<>() ;
        stat.put("18-25",0) ;
        stat.put("25-45",0) ;
        stat.put("45-65",0) ;
        stat.put("65-100",0) ;

        for (User user: list) {
            int age = user.getAge() ;
            if (age>= 18 && age <25)
                stat.put("18-25",stat.get("18-25")+1) ;
             else if (age >= 25 && age <45)
                stat.put("25-45",stat.get("25-45")+1) ;
             else if (age >= 45 && age < 65)
                 stat.put("45-65" , stat.get("45-65")+1) ;
             else
                 stat.put("65-100",stat.get("65-100")+1) ;
        }

        return stat ;

    }

    public int[] getStatMonths()
    {
        int[] tab = {0,0,0,0,0,0,0,0,0,0,0,0} ;
        List<User> list= this.getAll();
        for (User user : list)
        {
            String date = user.getCreated_at().toString().toString().split("-")[1];;
            int month =Integer.parseInt(date);
            if (month>=10)
                tab[month-1]=tab[month-1]+1;
            else
                tab[Integer.parseInt(String.valueOf(date.charAt(1)))-1]=tab[Integer.parseInt(String.valueOf(date.charAt(1)))-1]+1;
        }
        return tab ;
    }

    public int generateResetToken(String email)
    {

        Random random = new Random() ;
        int token = random.nextInt(30000000) ;

        String req = "update user set resettoken = ? where email = ? " ;

        try {
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1,String.valueOf(token));
            statement.setString(2,email);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return token ;
    }

    public boolean checkCodeReset(String email , String code)
    {
        String req = "select * from user where email = ? and resettoken = ? " ;
        try {
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1,email);
            statement.setString(2,code);
            ResultSet resultSet = statement.executeQuery() ;
            return resultSet.next() ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false ;
    }

    public boolean checkActivationCode(String email , String code)
    {
        String req = "select * from user where email = ? and activationtoken = ? " ;
        try {
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1,email);
            statement.setString(2,code);
            ResultSet resultSet = statement.executeQuery() ;
            return resultSet.next() ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false ;
    }


    public void updatePassword(String email , String password)
    {
        String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt(13));
        hashPassword = hashPassword.replaceFirst("2a", "2y") ;

        String req = "update user set password = ? where email = ?" ;

        try {
            PreparedStatement statement = cnx.prepareStatement(req) ;
            statement.setString(1,hashPassword);
            statement.setString(2,email);
            statement.executeUpdate() ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void activateAccount(String email)
    {
        String verified = "true" ;
        String req = "update user set verified = ? , activationtoken = ? where email = ?" ;

        try {
            PreparedStatement statement = cnx.prepareStatement(req) ;
            statement.setString(1,verified);
            statement.setNull(2,java.sql.Types.NULL);
            statement.setString(3,email);
            statement.executeUpdate() ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
