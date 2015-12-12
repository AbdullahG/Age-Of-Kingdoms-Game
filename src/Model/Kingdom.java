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
public class Kingdom {

    private int kingdomID;
    private String kingdomName;
    private int point;
    private int gold;

    public int getKingdomID() {
        return kingdomID;
    }

    public void setKingdomID(int kingdomID) {
        this.kingdomID = kingdomID;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
