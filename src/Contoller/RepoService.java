/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contoller;

import Model.Kingdom;
import Model.User;

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
}
