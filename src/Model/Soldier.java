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
public class Soldier {
    private int soldierID;
    private String soldierName;
    private int power;
    private int value;

    public int getSoldierID() {
        return soldierID;
    }

    public void setSoldierID(int soldierID) {
        this.soldierID = soldierID;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public void setSoldierName(String soldierName) {
        this.soldierName = soldierName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

