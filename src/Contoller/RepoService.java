/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contoller;

import Model.Kingdom;
import Model.Soldier;
import Model.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MuhammedAbdullah
 */
public interface RepoService {
    public User login(String username, String password);
    public boolean userExists(String username);
    public Kingdom getKingdom(int kingdomID);
    public Kingdom kingdomExists(String kingdomName);
    public Kingdom registerUser(User user, String kingdomName);
    public int getLastUserID();
    public int getLastKingdomID();
    public ArrayList<Soldier> getSoldierTypes();
    public boolean generateSoldier(int kingdomID,int soldierID, int quantity);
    public HashMap<Soldier,Integer> getAllSoldiers(int kingdomID);
    public ArrayList<Kingdom> getAllKingdoms();
    public void updateSoldierNumbers(Kingdom kngdm, HashMap<Soldier, Integer> soldierHashMap);
    public void insertWarLog(Kingdom winner, Kingdom loser, int point, int gold, int winner_soldier_loss, int loser_soldier_loss );
    public Object[][] getWarLog(Kingdom kng);
}
