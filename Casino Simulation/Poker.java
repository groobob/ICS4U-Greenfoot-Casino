import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * The poker table at the casino
 * 
 * Casinos make money from poker tables by taking in a "rake", or a percentage of the players
 * winnings. Due to this, although the amount that poker tables make is quite little, it is a
 * very safe source of income for the casino.
 * 
 * @author David Guo
 * @version 1.1 11/22/2023
 */
public class Poker extends Game
{
    private int playersAtTable;
    private int pot;
    // Integers to count acts between betting phases and the hand
    private int maxDelay;
    private int delay;
    private int maxPhase;
    private int phase;
    // The percentage the casino takes from poker table hands
    private double rake;
    public Poker(SpotManager.Spot[] spots){
        super(spots);
        playersAtTable = 0;
        // Rake is a percentage in decimal form. Most commonly from 2%-10
        rake = 0.05;
        // Delay between betting phases
        maxDelay = 300;
        delay = maxDelay;
        // This poker version has 4 betting phases: preflop, flop, turn, river
        maxPhase = 4;
        phase = maxPhase;
    }
    /**
     * Act - do whatever the Poker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        playersAtTable = getPlayersAtTable();
        if(playersAtTable > 1){
            playHand();
        }
    }
    
    private void playHand(){
        // Delay between betting phases
        if(delay <= 0 && phase <= 0){
            phase = maxPhase;
            delay = maxDelay;
            payout(gamblers[0]);
        } else if(delay <= 0){
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null){
                    increasePot(gamblers[i]);
                }
            }
            delay = maxDelay;
            phase--;
        }
        delay--;
    }
    
    private void increasePot(Gambler g){
        int moneyBet =  getMoneyBet(g);
        int rakeProfit = (int)(moneyBet*rake);
        g.playMoneyEffect(-moneyBet);
        pot += moneyBet-rakeProfit;
        HorizontalBar.casinoProfit += rakeProfit;
    }
    
    public void payout(Gambler g){
        g.playMoneyEffect(pot);
        pot = 0;
    }
    
    //private void endGamblerSession(int gamblerIndex) {
    //    gamblers[gamblerIndex].stopPlaying();
    //    gamblers[0] = null;
        //playCounter = 0; // reset play counter for next gambler
    //}
    private int getPlayersAtTable(){
        int currPlayers = 0;
        for(int i = 0; i < gamblers.length; i++){
            if(gamblers[i] != null){
                currPlayers++;
            }
        }
        return currPlayers;
    }
    private int getMoneyBet(Gambler g){
        // Gamblers will bet somewhere between 5-25% of their total money
        int randomPercentBet = Greenfoot.getRandomNumber(20)+5;
        return g.getMoney()*randomPercentBet;
    }
}