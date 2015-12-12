/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contoller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MuhammedAbdullah
 */
public class RepoServiceImpl implements RepoService {
    
    static Connection connection=null;
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static String username="KINGDOM";
    static String password = "123ewqqwe";
    
    static{
        connectToDB();
    }
    
    public static void connectToDB(){
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver is not found");
        } catch (SQLException ex) {
            System.out.println("Could not be connected to DB");
            ex.printStackTrace();
        }
    }
    
    public Object executeQuery(String query) {
        Statement st = null;
        Object rSet = null;

        try {
            st = connection.createStatement();
            if(query.split(" ")[0].equals("SELECT"))
                rSet=st.executeQuery(query);
            else
            rSet = st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rSet;
    }
    
    
}
