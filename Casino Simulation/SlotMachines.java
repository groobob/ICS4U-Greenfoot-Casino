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
    private int tempGamingTime;
    private int numberOfReels;
    private int[] result;
    private boolean jackpot;
    private int moneyWon;
    private int moneyLost;
    public SlotMachines(SpotManager.Spot[] spots) {
        super(spots);
        numberOfReels = 3;
        jackpot = false;
        moneyWon = Greenfoot.getRandomNumber(99)+1;
        moneyLost = Greenfoot.getRandomNumber(50)+1;
        result = new int[numberOfReels];
    }
    public void gamblerPay(){
        gamblers[0].playMoneyEffect(gamblers[0], false, moneyLost);
    }
    public void act(){
        //rn for testing gambler dips immediately
        if(gamblers[0]!=null&&gamblers[0].isPlaying()){
            gamblers[0].stopPlaying();
            gamblers[0]=null;
        }
    }
    public boolean checkIfWin() {
        System.out.println("1222222testttttttt");
        for (int i = 1; i < result.length; i++) {
            if (result[i] != result[i - 1]) {
                jackpot = false;
                if (gamblers[0] != null) {
                    gamblers[0].playMoneyEffect(gamblers[0], true, moneyWon);
                }
            } else {
                jackpot = true;
            }
        }
        return jackpot;
    }
}