/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MuhammedAbdullah
 */
public class User {
    
    private int ID;
    private String userName;
    private String password;
    private int kingdomID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKingdomID() {
        return kingdomID;
    }

    public void setKingdomID(int kingdomID) {
        this.kingdomID = kingdomID;
    }
    
}
