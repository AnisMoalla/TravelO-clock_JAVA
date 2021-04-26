package Utils;

import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnection {
    private String url = "jdbc:mysql://127.0.0.1:3306/travel" ;
    private String userName = "root" ;
    private String passWord = "" ;

    private Connection cnx ;
    private static MaConnection instance ;

    private MaConnection()
    {
        try {
            cnx = DriverManager.getConnection(url,userName,passWord) ;
            System.out.println("Connected");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static MaConnection getInstance()
    {
        if(instance == null)
            instance = new MaConnection() ;
        return instance ;
    }

    public Connection getCnx()
    {
        return cnx;
    }
}
