/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Contoller.RepoServiceImpl;
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
    public static void war2(Kingdom first, Kingdom second){
        int firstPower = first.getTotalPower();
        int secondPower = second.getTotalPower();
        ArrayList<Soldier> soldierTypes = GameFrame.repoService.getSoldierTypes();
        
        if(firstPower>secondPower){
            double rate = ((double)secondPower)/(firstPower+secondPower);
            rate = Math.pow(rate, 1.5);
            
        }
        
    }
    public static void war(Kingdom first, Kingdom second){
        
        double loseOfWinner;
        double loseOfLoser;
       
        ArrayList<Soldier> soldierTypes = GameFrame.repoService.getSoldierTypes();
        
        ArrayList<Integer> soldierWinnerIntList = new ArrayList<Integer>();
        ArrayList<Integer> soldierLoserIntList = new ArrayList<Integer>();
    
     
        second = GameFrame.repoService.getKingdom(second.getKingdomID());
     //   System.out.println(second.getTotalPower() + "------"+ first.getTotalPower());
        
        HashMap<Soldier, Integer> newAllSoldiersOfWinner = new HashMap<Soldier, Integer>();
        HashMap<Soldier, Integer> newAllSoldiersOfLoser = new HashMap<Soldier, Integer>();
           
        if(first.getTotalPower()>second.getTotalPower()){
           
            loseOfWinner = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
            loseOfLoser = (double)first.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           
           /* System.out.println(loseOfWinner);
            System.out.println(soldierTypes.get(1).getSoldierName());
            System.out.println(first.getAllSoldiers().get(soldierTypes.get(0)));
            System.out.println(GameFrame.repoService.getAllSoldiers(first.getKingdomID()));
            System.out.println(GameFrame.repoService.getSoldierTypes().get(0));*/
           
            for (Integer value: GameFrame.repoService.getAllSoldiers(first.getKingdomID()).values()) {
               int numberOfSoldiersOfWinner = (int) (value * loseOfLoser);
               soldierWinnerIntList.add(numberOfSoldiersOfWinner);
               //System.out.println("numberofsoldierofwinner = " + numberOfSoldiersOfWinner+" "+first.getKingdomID());
               
            }
            for (Integer value: GameFrame.repoService.getAllSoldiers(second.getKingdomID()).values()) {
               int numberOfSoldiersOfLoser = (int) (value * loseOfWinner);
               soldierLoserIntList.add(numberOfSoldiersOfLoser);
               //System.out.println("numberofsoldierofloser = " + numberOfSoldiersOfLoser+" "+second.getKingdomID());
            }
           
           //iki tane for each ile asagidaki for u cevirmis ol. for each donguleri bize integer degerlerini donecek ve islem onlarla yapilacak.
           
            for(int i = 0; i < soldierTypes.size(); i++){
               
                newAllSoldiersOfWinner.put(soldierTypes.get(i), soldierWinnerIntList.get(i));
                newAllSoldiersOfLoser.put(soldierTypes.get(i), soldierLoserIntList.get(i));
                
            }
            //System.out.println("???");
            GameFrame.repoService.updateSoldierNumbers(first, newAllSoldiersOfWinner);
            GameFrame.repoService.updateSoldierNumbers(second, newAllSoldiersOfLoser);
            //System.out.println("***");
           
       }
       else if(second.getTotalPower()>=first.getTotalPower()){
           
           loseOfWinner = (double)first.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           loseOfLoser = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           
            for (Integer value: GameFrame.repoService.getAllSoldiers(second.getKingdomID()).values()) {
               int numberOfSoldiersOfWinner = (int) (value * loseOfLoser);
               soldierWinnerIntList.add(numberOfSoldiersOfWinner);
               //System.out.println("numberofsoldierofwinner = " + numberOfSoldiersOfWinner+" "+second.getKingdomID());
               
            }
            for (Integer value: GameFrame.repoService.getAllSoldiers(first.getKingdomID()).values()) {
               int numberOfSoldiersOfLoser = (int) (value * loseOfWinner);
               soldierLoserIntList.add(numberOfSoldiersOfLoser);
               //System.out.println("numberofsoldierofloser = " + numberOfSoldiersOfLoser+" "+first.getKingdomID());
            }
            for(int i = 0; i < soldierTypes.size(); i++){
               
                newAllSoldiersOfWinner.put(soldierTypes.get(i), soldierWinnerIntList.get(i));
                newAllSoldiersOfLoser.put(soldierTypes.get(i), soldierLoserIntList.get(i));
                
            }
            GameFrame.repoService.updateSoldierNumbers(first, newAllSoldiersOfLoser);
            GameFrame.repoService.updateSoldierNumbers(second, newAllSoldiersOfWinner);
            
           
       }else{
           
       }
    }
}
