/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Kingdom;
import Model.Soldier;

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
    
    public void war(Kingdom first, Kingdom second){
        
       if(first.getTotalPower()>second.getTotalPower()){
           
           double loseOfFirst = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           
           loseOfFirst = first.getTotalPower() * loseOfFirst;
           
           
       }else if(second.getTotalPower()>first.getTotalPower()){
           
       }else{
           
       }
    }
}
