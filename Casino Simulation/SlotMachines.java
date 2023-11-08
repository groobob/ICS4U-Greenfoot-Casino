import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.Random;
/**
 * The slot machine subclass of the Game class encompasses the functionality and code for the slot machine games. 
 * The slot machine games are played by the majority of the Gamblers that are in the simulation, with jackpots/winning runs that are
 * not determined by skill but by solely luck of the Gambler.
 * 
 * @author Dorsa Rohani
 * @version November 8
 */
public class SlotMachines extends Game
{
    private int numberOfReels;
    private Random random;
    private int[] result;
    private boolean jackpot;
    
    public SlotMachines(){
        numberOfReels = 3;
        jackpot = false;
    }
    
    public void act(){
        if(isGamblerInFront()){
            stationGambler();
        }
    }
    
    private boolean isGamblerInFront(){
        List<Gambler> gamblers = getObjectsInRange(50, Gambler.class); // Adjust range as needed
        return !gamblers.isEmpty();
    }  
    
    
    public void stationGambler(){
        // code here for stationing the gambler in front of the slot machine
        
        spinReels();
    }
    
    // spin reels and determine outcome
    public void spinReels(){
        result = new int[numberOfReels];
        
        // spin each reel
        for (int i = 0; i < numberOfReels; i++) {
            result[i] = random.nextInt(7)+1; //random num between 1 and 7
        }
        
        checkIfWin();
    }

    // calculate payout based on spin outcome
    public boolean checkIfWin(){
        for(int i = 1; i < result.length; i++){
            if(result[i] != result[i-1]){
                jackpot = false;
            }
            else{
                jackpot = true;
            }
        }
        return jackpot;
    }
}