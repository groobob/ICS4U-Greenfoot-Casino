import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * The poker table at the casino
 * 
 * Casinos make money from poker tables by taking in a "rake", or a percentage of the players
 * winnings. Due to this, although the amount that poker tables make is quite little, it is a
 * very safe source of income for the casino.
 * 
 * A minimum of 2 players is required for the game to start
 * Huge price to play, huge payouts -> One winner, a ton of losers
 * Winner of the game is the one with the highest skill stat at the table
 * Poker is known to be one of the most skill-based gambling games since it is played
 * against other players, not the house
 * 
 * @author David Guo
 * @version 1.2 11/23/2023
 */
public class Poker extends Game
{
    private int playersAtTable;
    private int pot;
    private int minBet;
    private int leaveChance;
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
        // Chance for a player to leave the game after a pot is paid out in %
        leaveChance = 20;
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
        super.act();
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
            int highestSkillPlayer = -1; // Will be changed by the for loop
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null && gamblers[i].isPlaying()){
                    // To ensure there is not a null pointer error for the following lines
                    if(highestSkillPlayer == -1)highestSkillPlayer = i;
                    // Player with highest skill gets their index stored in highestSkillPlayer
                    if(gamblers[i].getSkill() >= gamblers[highestSkillPlayer].getSkill()){
                        highestSkillPlayer = i;
                    }
                }
            }
            payout(gamblers[highestSkillPlayer]);
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null && gamblers[i].isPlaying()){
                    int randomChance = Greenfoot.getRandomNumber(100);
                    if(randomChance < leaveChance)endGamblerSession(i);
                }
            }
        } else if(delay <= 0){
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null && gamblers[i].isPlaying()){
                    increasePot(i);
                }
            }
            delay = maxDelay;
            phase--;
        }
        delay--;
    }
    
    private void increasePot(int gamblerIndex){
        int moneyBet =  getMoneyBet(gamblers[gamblerIndex]);
        int rakeProfit = (int)(moneyBet*rake);
        if(moneyBet < minBet){
            endGamblerSession(gamblerIndex);
        } else {
            gamblers[gamblerIndex].playMoneyEffect(-moneyBet, false);
            pot += moneyBet-rakeProfit;
            UIManager.incrementCasinoProfit(rakeProfit);
        }
    }
    
    private void payout(Gambler g){
        // In order to prevent playMoneyEffect printing +0
        if(pot != 0)g.playMoneyEffect(pot, false);
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
    
    private int getMoneyBet(Gambler g){
        // Gamblers will bet somewhere between 5-25% of their total money
        double randomPercentBet = (double)(Greenfoot.getRandomNumber(20)+5)/100;
        return (int)(g.getMoney()*randomPercentBet);
    }
    
}