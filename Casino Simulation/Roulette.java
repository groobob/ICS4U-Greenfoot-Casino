import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * Write a description of class Roulette here.
 * 
 * @author David Guo
 * @version 1.0 11/13/2023
 */
public class Roulette extends Game
{
    // Declare class variables, some of which may be available for customization
    
    // Roulette Table sprite
    GreenfootImage rouletteTable;
    // The number of pockets depends on the type of roulette:
    // 37 pockets in French or European style roulette
    // 38 pockets in American roulette
    // 39 pockets in "Sands Roulette"
    // Note: The number of pockets should be from 37-39!!!!!
    private int numberOfPockets;
    // The pocket number is from 0 to 36 with 37 pockets, -1 to 36 with 38 pockets, 
    // and -2 to 36 with 39 pockets
    private int pocketNum;
    private int maxBet;
    private boolean currentlySpinning;
    private int actsSpinning;
    
    // List of all gamblers currently playing
    private int[] gamblerBets;
    private int[] moneyBets;
    
    // Payout multiplier values
    private final double NUMBER_PAYOUT = 36;
    private final double ODD_EVEN_PAYOUT = 2;
    
    public Roulette() {
        numberOfPockets = 38;
        maxBet = 5000;
        currentlySpinning = false;
        actsSpinning = 0;
        // Set the class to the image
        rouletteTable = new GreenfootImage("TestRoulette.gif");
        rouletteTable.scale(80,60);
        setImage(rouletteTable);
        // Initlize arraylist of gamblers
        gamblersPlaying = new Gambler[6]; // each index is a seat
        gamblerBets = new int[6]; // the number the gambler bets on (-1 is odd, -2 is even ,0 is for 0 and it's variants)
    }
    /**
     * Act - do whatever the Roulette wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actsSpinning++;
        if(actsSpinning > 600){ // 600 acts -> 10 seconds to spin
            actsSpinning = 0;
            currentlySpinning = false;
        } else if (actsSpinning == 0){
            makeBet();
        }
    }
    
    private int spinWheel(){
        currentlySpinning = true;
        int randomPocket = Greenfoot.getRandomNumber(numberOfPockets);
        // Change numbers above 36 to 0
        if(randomPocket == 37 || randomPocket == 38){randomPocket = 0;}
        return randomPocket;
    }
    
    public int calculateEarned(){
        /*
        for(int i = 0; i < gamblersPlaying.length; i++){
            if(gamblerBets[i] == -1 && pocketNum % 2 == 1){
                return getMoneyBet() * EVEN_ODD_PAYOUT;
            } else if(gamblerBets[i] == -2 && pocketNum % 2 == 0){
                return getMoneyBet() * EVEN_ODD_PAYOUT;
            } else if(gamblerBets[i] == pocketNum){
                return getMoneyBet() * NUMBER_PAYOUT;
            }
        }
        */
        return 0;
        
    }
    
    private void clearWheel(){
        // -1 will be used to represent undecided
        pocketNum = -1;
    }
    // When gamblers are ready to make their bet before the wheel starts spinning
    private void makeBet(){
        for(int i = 0; i < gamblersPlaying.length; i++){
            if(gamblersPlaying[i] != null){
                /*
                if(gamblersPlaying[i].getSkill() < 50){
                    gamblerBets[i] = Greenfoot.getRandomNumber(numberOfPockets);
                } else {
                    // -1 means betting on odds, -2 means betting on evens
                    gamblerBets[i] = Greenfoot.getRandomNumber(2)-2;
                }
                */
            }
        }
        spinWheel();
    }
}
