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
    public static int war(Kingdom first, Kingdom second){
        
        double loseOfWinner;
        double loseOfLoser;
        
        int deadSoldiersWinner = 0;
        int deadSoldiersLoser = 0;
       
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
           
         
           
            for (Integer value: GameFrame.repoService.getAllSoldiers(first.getKingdomID()).values()) {
               int numberOfSoldiersOfWinner = (int) (value * loseOfLoser); 
               deadSoldiersWinner += (value - numberOfSoldiersOfWinner); //******** ********
               soldierWinnerIntList.add(numberOfSoldiersOfWinner);
               //System.out.println("numberofsoldierofwinner = " + numberOfSoldiersOfWinner+" "+first.getKingdomID());
               
            }
            for (Integer value: GameFrame.repoService.getAllSoldiers(second.getKingdomID()).values()) {
               int numberOfSoldiersOfLoser = (int) (value * loseOfWinner);
               deadSoldiersLoser += (value - numberOfSoldiersOfLoser);
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
            
            //kazanana point eklenecek
            int point = deadSoldiersLoser * 2;//eklenecek olan point 
            first.setPoint(point + first.getPoint());
            //kazanana gold eklenecek
            int gold = 20 * deadSoldiersWinner;//eklenecek olan gold
            first.setGold(gold + first.getGold());
             
            //***** GAME FRAME UPDATE VIEWWWWW c********** 
            MainFrame.gf.startGame(first);
            
            GameFrame.repoService.insertWarLog(first, second, point, gold, deadSoldiersWinner, deadSoldiersLoser);
            return 1;
           
       }
       else {
           
           loseOfWinner = (double)first.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           loseOfLoser = (double)second.getTotalPower()/(double)(first.getTotalPower() + second.getTotalPower());
           
            for (Integer value: GameFrame.repoService.getAllSoldiers(second.getKingdomID()).values()) {
               int numberOfSoldiersOfWinner = (int) (value * loseOfLoser);
               deadSoldiersWinner += (value - numberOfSoldiersOfWinner);
               soldierWinnerIntList.add(numberOfSoldiersOfWinner);
               //System.out.println("numberofsoldierofwinner = " + numberOfSoldiersOfWinner+" "+second.getKingdomID());
               
            }
            for (Integer value: GameFrame.repoService.getAllSoldiers(first.getKingdomID()).values()) {
               int numberOfSoldiersOfLoser = (int) (value * loseOfWinner);
               deadSoldiersLoser += (value - numberOfSoldiersOfLoser);
               soldierLoserIntList.add(numberOfSoldiersOfLoser);
               //System.out.println("numberofsoldierofloser = " + numberOfSoldiersOfLoser+" "+first.getKingdomID());
            }
            for(int i = 0; i < soldierTypes.size(); i++){
               
                newAllSoldiersOfWinner.put(soldierTypes.get(i), soldierWinnerIntList.get(i));
                newAllSoldiersOfLoser.put(soldierTypes.get(i), soldierLoserIntList.get(i));
                
            }
            GameFrame.repoService.updateSoldierNumbers(first, newAllSoldiersOfLoser);
            GameFrame.repoService.updateSoldierNumbers(second, newAllSoldiersOfWinner);
            
            
            int point = deadSoldiersLoser * 2;//eklenecek olan point 
            second.setPoint(point + second.getPoint());
            //kazanana gold eklenecek
            int gold = 20 * deadSoldiersWinner;//eklenecek olan gold
            second.setGold(gold + second.getGold());
             
            //***** GAME FRAME UPDATE VIEWWWWW c********** 
            //MainFrame.gf.startGame(second);
            
            GameFrame.repoService.insertWarLog(second, first, point, gold, deadSoldiersWinner, deadSoldiersLoser);
            return 0;
           
       }
    }
    
}
