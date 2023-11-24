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
 * ============================================
 * 
 * @author David Guo
 * @version 1.1 11/22/2023
 */
public class Blackjack extends Game
{
    // The time in acts between hands played
    private int timeBetweenDeals;
    // A rough imitation of card counting statistics
    private int trueCount;
    public Blackjack(SpotManager.Spot[] spots){
        super(spots);
        timeBetweenDeals = 300;
    }
    /**
     * Act - do whatever the Blackjack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        dealCards();
    }
    // The dealer is "dealing" cards, making every player at the table put down a bet
    private void dealCards(){
        for(int i = 0; i < gamblers.length; i++){
            
        }
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
    // odds of winning the hand calculated based on luck and skill
    private double calculateOdds(Gambler g){
        // The percent chance of winning the hand for this gambler by default
        double tempPercentChance = 48;
        // The chance of them winning is decided by luck, then skill
        //tempPercentChance += gamblersPlaying[i].getLuck()/2; // max 98% chance
        // reduces the chance from 0.01 to 1
        tempPercentChance *= (double)g.getSkill()/100;
        return tempPercentChance;
    }
    
}