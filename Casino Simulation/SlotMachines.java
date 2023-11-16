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
    private Gambler gambler; 
    private boolean clapZSort=false;
    public SlotMachines(CasinoWorld.pos[] cwp) {
        super(cwp);
        numberOfReels = 3;
        jackpot = false;
        moneyWon = Greenfoot.getRandomNumber(99)+1;
        moneyLost = Greenfoot.getRandomNumber(50)+1;
        result = new int[numberOfReels];
    }
       public void addedToWorld(World w){
        if(!clapZSort){//prevent z sort problems
            clapZSort=true;
            CasinoWorld.gs.add(this);
        }
    }
    public void gamblerPay(){
        Gambler.playMoneyEffect(gambler, false, moneyLost);
    }
    public void act(){
        //rn for testing gambler dips immediately
        if(playing[0]){
            bababooey();
            playing[0]=false;
        }
    }
    public void bababooey(){
        g[0].unstop();
        g[0]=null;
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