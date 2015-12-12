/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contoller;

import Model.Kingdom;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MuhammedAbdullah
 */
public class RepoServiceImpl implements RepoService {

    static Connection connection = null;
    static String DBurl = "jdbc:oracle:thin:@localhost:1521:XE";
    static String DBusername = "KINGDOM";
    static String DBpassword = "123ewqqwe";

    static {
        connectToDB();
    }

    public static void connectToDB() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
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
            if (query.split(" ")[0].equals("SELECT")) {
                rSet = st.executeQuery(query);
            } else {
                rSet = st.executeUpdate(query);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rSet;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE username='" + username + "' and password='" + password + "'";
        ResultSet rs = (ResultSet) this.executeQuery(query);

        try {
            if (rs.next()) {
                user = new User();
                user.setID(rs.getInt("user_id"));
                user.setUserName(username);
                user.setPassword(password);
                user.setKingdomID(rs.getInt("kingdom_id"));
            }
            return user;

        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean userExists(String username) {
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        ResultSet rs = (ResultSet) this.executeQuery(query);

        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Kingdom getKingdom(int kingdomID) {
        Kingdom kingdom = null;

        String query = "SELECT * FROM kingdoms WHERE kingdom_id=" + kingdomID;
        ResultSet rs = (ResultSet) this.executeQuery(query);

        try {
            if (rs.next()) {
                kingdom = new Kingdom();
                kingdom.setKingdomID(kingdomID);
                kingdom.setKingdomName(rs.getString("kingdom_name"));
                kingdom.setPoint(rs.getInt("point"));
                kingdom.setGold(rs.getInt("gold"));
            }
            return kingdom;

        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Kingdom kingdomExists(String kingdomName) {
        Kingdom kingdom = null;

        String query = "SELECT * FROM kingdoms WHERE kingdom_name='" + kingdomName + "'";
        ResultSet rs = (ResultSet) this.executeQuery(query);

        try {
            if (rs.next()) {
                kingdom = new Kingdom();
                kingdom.setKingdomID(rs.getInt("kingdom_id"));
                kingdom.setKingdomName(kingdomName);
                kingdom.setPoint(rs.getInt("point"));
                kingdom.setGold(rs.getInt("gold"));
            }
            return kingdom;

        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Kingdom registerUser(User user, String kingdomName) {
        String query = "";
        int userID = getLastUserID() + 1;
        int kingdomID = getLastKingdomID() + 1;
        query = "insert into kingdoms VALUES(" + kingdomID + ",'" + kingdomName + "', " + 100 + ", " + 200 + ")";

        if ((int) executeQuery(query) > 0) {
            query = "insert into users (user_id,username,password,kingdom_id,last_login) VALUES(" + userID + ",'" + user.getUserName()
                    + "','" + user.getPassword() + "',"+kingdomID+",(SELECT TO_CHAR"
                    + "(SYSDATE, 'MM-DD-YYYY HH24:MI:SS') FROM DUAL))";
            if ((int) executeQuery(query) > 0) {
                Kingdom kng = new Kingdom();
                kng.setGold(200);
                kng.setPoint(100);
                kng.setKingdomName(kingdomName);
                kng.setKingdomID(kingdomID);
                return kng;
            }
        }
        return null;
    }

    @Override
    public int getLastUserID() {
        String query = "SELECT max(user_id) as user_id FROM users";

        ResultSet rs = (ResultSet) executeQuery(query);
        try {
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getLastKingdomID() {
        String query = "SELECT max(kingdom_id) as kingdom_id FROM kingdoms";

        ResultSet rs = (ResultSet) executeQuery(query);
        try {
            if (rs.next()) {
                return rs.getInt("kingdom_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
