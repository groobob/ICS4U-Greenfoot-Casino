import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * 
 * 
 * <p>=============== Experimental Data ===============</p>
 * 
 * 11/10/2023 <br>
 * Played: ~20 rounds of blackjack (40 chips) with Mr. Cohen <br>
 * Result: Cohen: 40 chips, House: 0 chips <br>
 * <br>
 * 11/10/2023 <br>
 * Played: 3 rounds of blackjack (40 chips) with Liyu <br>
 * Result: Liyu: 0 chips, House: 40 chips <br>
 * <br>
 * =================================================
 * 
 * @author David Guo
 * @version 1.0 11/14/2023
 */
public class Blackjack extends Game
{
    private int trueCount;
    public Blackjack(SpotManager.Spot[] spots){
        super(spots);
    }
    /**
     * Act - do whatever the Blackjack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actNumber++;
    }
    
    // odds of winning the hand calculated based on luck and skill
    private double calculateOdds(Gambler g){
        // The percent chance of winning the hand for this gambler by default
        double tempPercentChance = 48;
        // The chance of them winning is decided by luck, then skill
        //tempPercentChance += gamblersPlaying[i].getLuck()/2; // max 98% chance
        // reduces the chance from 0.01 to 1
        //tempPercentChance *= (double)gamblersPlaying[i].getSkill()/100;
        return tempPercentChance;
    }
    
    public int playHand(Gambler g, int moneyBet){
        int randChance = Greenfoot.getRandomNumber(100);
        double tempPercentChance = calculateOdds(g);
        if(tempPercentChance <= randChance){
            return moneyBet * 2;
        } else{
            return 0;
        }
    }
    
    public void stationGambler(){
        
    }
    
}