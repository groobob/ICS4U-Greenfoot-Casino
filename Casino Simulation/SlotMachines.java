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
    private Gambler gambler; // Reference to the gambler playing this slot machine

    public SlotMachines() {
        numberOfReels = 3;
        jackpot = false;
        moneyWon = 100;
        result = new int[numberOfReels];
    }

    public void assignGambler(Gambler gambler) {
        this.gambler = gambler;
        if (gambler != null) {
            gambler.setPlayingSlot(true); // Set the gambler's state to playing
        }
    }

    public void spinReels() {
        for (int i = 0; i < numberOfReels; i++) {
            result[i] = Greenfoot.getRandomNumber(7); // Random number between 1 and 7
        }

        checkIfWin();
        if (gambler != null) {
            gambler.setPlayingSlot(false); // Reset the gambler's state when done
        }
    }

    public boolean checkIfWin() {
        System.out.println("1222222testttttttt");

        for (int i = 1; i < result.length; i++) {
            if (result[i] != result[i - 1]) {
                jackpot = false;
                if (gambler != null) {
                    Gambler.playMoneyEffect(gambler, true, 100);
                }
            } else {
                jackpot = true;
            }
        }
        return jackpot;
    }
}