import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * The blackjack table class.
 * 
 * Players verse the dealer based on who has the highest hand without passing 21.
 * Players will lose their bet if they bust.
 * If they tie, they will go even
 * If they win, they will double their bet.
 * 
 * Players decide when to hit based on their skill level
 * Dealer hits until 17 or above
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
 * @version 1.2 11/24/2023
 */
public class Blackjack extends Game
{
    // Chance to leave after every hand
    private int chanceToLeave;
    // The time in acts between hands played
    private int timeBetweenDeals;
    private int dealTime;
    // Gambler hand values
    private int handValues[];
    // Dealers hand value
    private int dealersHand;
    public Blackjack(SpotManager.Spot[] spots){
        super(spots);
        timeBetweenDeals = 300;
        dealTime = timeBetweenDeals;
        // Chance leave in %
        chanceToLeave = 25;
        // Array to store gambler hand values; initialize and populate so it does not error
        handValues = new int[gamblers.length];
        for(int i = 0; i < handValues.length; i++)handValues[i] = 0;
        // Dealers hand value is decided at the start of each hand
        dealersHand = 0;
    }
    /**
     * Act - do whatever the Blackjack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        dealTime--;
        if(dealTime <= 0)revealCards();
        if(dealTime == timeBetweenDeals/2)dealCards();
    }
    // The dealer is "dealing" cards, making every player at the table put down a bet
    private void dealCards(){
        // Dealers hand
        dealersHand += hit(dealersHand);
        dealersHand += hit(dealersHand);
        for(int i = 0; i < gamblers.length; i++){
            if(gamblers[i] != null && gamblers[i].isPlaying()){
                handValues[i] = playHand(gamblers[i], handValues[i]);
            }
        }
    }
    
    private void revealCards(){
        // Dealer must hit until 17 or above
        while(dealersHand < 17)dealersHand += hit(dealersHand);
        // If dealer busts, their hand is worse than 0
        if(dealersHand > 21)dealersHand = -1;
        for(int i = 0; i < gamblers.length; i++){
            if(gamblers[i] != null && gamblers[i].isPlaying()){
                int gBet = getMoneyBet(gamblers[i]);
                // Did the player lose by busting or having a lower hand?
                if(handValues[i] > 21 || handValues[i] < dealersHand)gamblers[i].playMoneyEffect(-gBet);
                // Did they win?
                else if(handValues[i] > dealersHand)gamblers[i].playMoneyEffect(gBet);
                // If neither, it is a tie. Therefore no money earned/lost
                // Chance for gambler to leave
                int leaveChance = Greenfoot.getRandomNumber(100);
                if(leaveChance < chanceToLeave)endGamblerSession(i);
            }
            // Reset some variables for next round
            handValues[i] = 0;
        }
        // Reset some variables for next round
        dealTime = timeBetweenDeals;
        dealersHand = 0;
    }
    
    public int playHand(Gambler g, int hand){
        int skill = g.getSkill();
        hand += hit(hand);
        // Everyone knows you hit before and at 11
        while(hand <= 11)hand += hit(hand);
        // A bunch of if statements to determine if the player will continue hitting
        if(skill < 10)hand+=hit(hand);
        if(hand <= 13 && dealersHand > 13 && skill > 30)hand += hit(hand);
        if(hand <= 16 && dealersHand > 16 && skill > 45)hand += hit(hand);
        if(hand <= 18 && dealersHand > 18 && skill > 60)hand += hit(hand);
        return hand;
    }
    // An imitation of hitting, aka drawing a card (equal chance for all 13 different cards)
    private int hit(int currHand){
        // Ace = 1, can also double as 11
        int card = Greenfoot.getRandomNumber(13)+1;
        if(card > 10)card = 10;
        if(card == 1 && currHand <= 10)card = 11;
        return card;
    }

    private int getMoneyBet(Gambler g){
        // Gamblers will bet somewhere between 5-25% of their total money
        double randomPercentBet = (double)(Greenfoot.getRandomNumber(20)+5)/100;
        return (int)(g.getMoney()*randomPercentBet);
    }
    
}