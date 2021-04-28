/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wofurani
 */
public class MaConnexion {
       private String url="jdbc:mysql://127.0.0.1:3306/travel";
    private String userName="root";
    private String password="";
    
    private Connection cnx;
    private static MaConnexion instance;
    
    private MaConnexion(){
        
        try {
            cnx= DriverManager.getConnection(url,userName,password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
           
    }
    
    public static MaConnexion getInstance(){
        if (instance == null)
            instance = new MaConnexion();
        return (instance);
    }

    public Connection getCnx() {
        return cnx;
    }
}
