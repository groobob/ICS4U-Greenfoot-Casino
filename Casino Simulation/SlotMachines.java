import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.Random;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachines extends Game {
    private int numberOfReels;
    private int[] result;
    private boolean jackpot;
    private int moneyWon;
    private int moneyLost;
    private Gambler gambler; 

    private boolean isOccupied = false;
    
    public SlotMachines() {
        numberOfReels = 3;
        jackpot = false;
        moneyWon = Greenfoot.getRandomNumber(99)+1;
        moneyLost = Greenfoot.getRandomNumber(50)+1;
        result = new int[numberOfReels];
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void spinReels() {
        if (!isOccupied) {
            setOccupied(true);
            
            gamblerPay();
            for (int i = 0; i < numberOfReels; i++) {
                result[i] = Greenfoot.getRandomNumber(7); // random num 1-7
            }
    
            checkIfWin();
            if (gambler != null) {
                gambler.setPlayingSlot(false); // reset gambler state
            }
            releaseSlot();
        }
    }
    
    public void gamblerPay(){
        Gambler.playMoneyEffect(gambler, false, moneyLost);
    }
    
    public void releaseSlot() {
        setOccupied(false);
        if (gambler != null) {
            gambler.setPlayingSlot(false);
            Gambler.unstop();
        }
    }
    
    public void assignGambler(Gambler gambler) {
        this.gambler = gambler;
        if (gambler != null) {
            gambler.setPlayingSlot(true); // change gambler state to playing
        }
    }

    public boolean checkIfWin() {
        System.out.println("1222222testttttttt");

        for (int i = 1; i < result.length; i++) {
            if (result[i] != result[i - 1]) {
                jackpot = false;
                if (gambler != null) {
                    Gambler.playMoneyEffect(gambler, true, moneyWon);
                }
            } else {
                jackpot = true;
            }
        }
        return jackpot;
    }
}