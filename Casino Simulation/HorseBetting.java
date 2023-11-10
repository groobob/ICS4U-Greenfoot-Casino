import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.Random;
/**
 * Write a description of class HorseBetting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HorseBetting extends Game
{
    private int numberOfHorses; // number of horses involved in the round
    private Random random; // random number generation
    private int moneyWon; // amount of money won
    private int betCost; // cost to participate in the betting
    private boolean win; // tracks if gambler won
    private int winningHorse; // the horse that wins the betting round
    private int horseBet; // the horse that the gambler bets on to win for the round
    
    public HorseBetting(){
        numberOfHorses = 7; // number of horses involved in the round
        betCost = 10; // cost of bet
        win = false; // gambler does not win (at least initially)
    }
    /**
     * Act - do whatever the HorseBetting wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // 
    }

    public void stationGambler(){
        horseBet = random.nextInt(7)+1;
        
        checkIfWin();
    }
    
    // checks if the gambler won the round
    public boolean checkIfWin(){
        winningHorse = random.nextInt(7)+1; // random num between 1 and 7
        
        if(horseBet == winningHorse){
            win = true;
            return true;
        }
        else{
            return false;
        }
    }
}
