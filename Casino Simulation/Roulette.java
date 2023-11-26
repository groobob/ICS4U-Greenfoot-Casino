import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * A roulette table which where gamblers can choose a number to bet their money on.
 * 
 * People with a higher skill value than 50 (very easy to get) will choose to bet
 * on even or odd to actually earn money. People with less skill will blindly pick
 * a number, hoping to win.
 * 
 * The roulette spins every few hundred or so acts.
 * 
 * @author David Guo
 * @version 1.2 11/23/2023
 */
public class Roulette extends Game
{
    // Declare class variables, some of which may be available for customization
    
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
    // Chance of gamblers to leave after every spin
    private int chanceToLeave;
    // List of all gamblers currently playing
    private int[] gamblerBets;
    private int[] moneyBets;
    
    // Payout multiplier values
    private final int NUMBER_PAYOUT = 36;
    private final int ODD_PAYOUT = 2;
    private final int EVEN_PAYOUT = 2;
    
    public Roulette(SpotManager.Spot[] spots) {
        super(spots);
        numberOfPockets = SettingsWorld.getRouletteStyle();
        currentlySpinning = false;
        actsSpinning = 0;
        // Chance to leave in percent
        chanceToLeave = 30;
        // Initlize arraylist of gamblers
        gamblerBets = new int[gamblers.length]; // the number the gambler bets on (-1 is odd, -2 is even ,0 is for 0 and it's variants)
        moneyBets = new int[gamblers.length];
    }
    /**
     * Act - do whatever the Roulette wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        playGame();        
    }
    
    private void playGame(){
        if(actsSpinning > 300){ // 600 acts -> 5 seconds to spin
            actsSpinning = 0;
            currentlySpinning = false;
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null && gamblers[i].isPlaying()){
                    // In order to prevent playMoneyEffect printing +0
                    if(calculateEarned(i)!=0)gamblers[i].playMoneyEffect(calculateEarned(i));
                    int leaveChance = Greenfoot.getRandomNumber(100);
                    if(leaveChance < chanceToLeave)endGamblerSession(i);
                }
            }
        } else if (actsSpinning == 100){
            makeBet();
        }
        actsSpinning++;
    }
    
    private int spinWheel(){
        currentlySpinning = true;
        int randomPocket = Greenfoot.getRandomNumber(numberOfPockets);
        // Change numbers above 36 to 0
        if(randomPocket == 37 || randomPocket == 38)randomPocket = 0;
        return randomPocket;
    }
    
    public int calculateEarned(int gamblerIndex){
        if(gamblerBets[gamblerIndex] == -1 && pocketNum % 2 == 1){
            return moneyBets[gamblerIndex] * ODD_PAYOUT;
        } else if(gamblerBets[gamblerIndex] == -2 && pocketNum % 2 == 0){
            return moneyBets[gamblerIndex] * EVEN_PAYOUT;
        } else if(gamblerBets[gamblerIndex] == pocketNum){
            return moneyBets[gamblerIndex] * NUMBER_PAYOUT;
        }
        return 0;
        
    }
    
    // When gamblers are ready to make their bet before the wheel starts spinning
    private void makeBet(){
        for(int i = 0; i < gamblers.length; i++){
            if(gamblers[i] != null && gamblers[i].isPlaying()){
                if(gamblers[i].getSkill() < 50){
                    gamblerBets[i] = Greenfoot.getRandomNumber(37); // Won't ever change
                } else {
                    // -1 means betting on odds, -2 means betting on evens
                    gamblerBets[i] = Greenfoot.getRandomNumber(2)-2;
                }
                // Give all players a bet
                moneyBets[i] = getMoneyBet(gamblers[i]);
                gamblers[i].playMoneyEffect(-moneyBets[i]);
            }
        }
        pocketNum = spinWheel();
    }
    
    private int getMoneyBet(Gambler g){
        // Gamblers will bet somewhere between 5-25% of their total money
        double randomPercentBet = (double)(Greenfoot.getRandomNumber(20)+5)/100;
        return (int)(g.getMoney()*randomPercentBet);
    }
    
}