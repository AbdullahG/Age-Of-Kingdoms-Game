/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Frames.*;
import Model.Kingdom;
import Model.Soldier;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MuhammedAbdullah
 */
public class Calc {
    
    public int getTotalPowerOfKingdom(Kingdom kng){
        int totalPower = 0;
        
        for (Soldier key : kng.getAllSoldiers().keySet()) {
            totalPower+=(key.getPower()*kng.getAllSoldiers().get(key));
        }
        return totalPower;
    }
    
    public static void war(Kingdom first, Kingdom second){
        
        double loseOfWinner;
        double loseOfLoser;
        
        ArrayList<Soldier> soldierTypes = GameFrame.repoService.getSoldierTypes();;
        
        HashMap<Soldier, Integer> newAllSoldiersOfWinner = new HashMap<Soldier, Integer>();
        HashMap<Soldier, Integer> newAllSoldiersOfLoser = new HashMap<Soldier, Integer>();
           
        if(first.getTotalPower()>second.getTotalPower()){
           
           loseOfWinner = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           loseOfLoser = (double)first.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
 
           
           
           for(int i = 0; i < soldierTypes.size(); i++){
               
                int numberOfSoldiersOfWinner = (int)(first.getAllSoldiers().get(soldierTypes.get(i)) * loseOfWinner);
                int numberOfSoldiersOfLoser = (int)(second.getAllSoldiers().get(soldierTypes.get(i)) * loseOfLoser);
          
                newAllSoldiersOfWinner.put(soldierTypes.get(i), numberOfSoldiersOfWinner);
                newAllSoldiersOfLoser.put(soldierTypes.get(i), numberOfSoldiersOfLoser);
           
           }
           
       }
       else if(second.getTotalPower()>first.getTotalPower()){
           
           loseOfWinner = (double)first.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           loseOfLoser = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           
           for(int i = 0; i < soldierTypes.size(); i++){
               
                int numberOfSoldiersOfWinner = (int)(second.getAllSoldiers().get(soldierTypes.get(i)) * loseOfWinner);
                int numberOfSoldiersOfLoser = (int)(first.getAllSoldiers().get(soldierTypes.get(i)) * loseOfLoser);
                
                newAllSoldiersOfWinner.put(soldierTypes.get(i), numberOfSoldiersOfWinner);
                newAllSoldiersOfLoser.put(soldierTypes.get(i), numberOfSoldiersOfLoser);
           }
           
       }else{
           
       }
    }
}
