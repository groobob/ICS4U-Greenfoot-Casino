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
    private int minBet;
    // Integers to count acts between betting phases and the hand
    private int maxDelay;
    private int delay;
    private int maxPhase;
    private int phase;
    // The percentage the casino takes from poker table hands
    private double rake;
    // Array to match the gamblers[] array, boolean to see if the gambler is "in game"
    //private boolean[] inGame;
    public Poker(SpotManager.Spot[] spots){
        super(spots);
        playersAtTable = 0;
        // Players must bet a minimum of this number per hand
        minBet = 10;
        // Rake is a percentage in decimal form. Most commonly from 2%-10
        rake = 0.05;
        // Pot is worth 0 at the start
        pot = 0;
        // Delay between betting phases (actS)
        maxDelay = 100;
        delay = maxDelay;
        // This poker version has 4 betting phases: preflop, flop, turn, river
        maxPhase = 4;
        phase = maxPhase;
        // inGame array initialization
        //inGame = new boolean[gamblers.length];
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
            payout(gamblers[0]); // FIX
        } else if(delay <= 0){
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null && gamblers[i].isPlaying()){
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
        if(moneyBet < minBet){
            g.stopPlaying();
        } else {
            g.playMoneyEffect(-moneyBet);
            pot += moneyBet-rakeProfit;
            HorizontalBar.casinoProfit += rakeProfit;
        }
    }
    
    private void leaveTable(Gambler g){
        g.stopPlaying();
    }
    
    private void payout(Gambler g){
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
            if(gamblers[i] != null && gamblers[i].isPlaying()){
                currPlayers++;
            }
        }
        return currPlayers;
    }
    // Problem: money is too low
    private int getMoneyBet(Gambler g){
        // Gamblers will bet somewhere between 5-25% of their total money
        double randomPercentBet = (double)(Greenfoot.getRandomNumber(20)+5)/100;
        return (int)(g.getMoney()*randomPercentBet);
    }
    
}