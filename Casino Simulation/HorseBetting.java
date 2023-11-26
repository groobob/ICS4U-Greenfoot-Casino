import greenfoot.*;  // (world, actor, greenfootImage, greenfoot and mouseInfo)
import java.util.*;
/**
 * <html>
 * <body>
 * <p><strong>HorseBetting</strong> is a <em>Game</em> subclass that creates and runs the game logic for accurate real-world horse betting.</p>
 * <p>This class is a part of the Greenfoot game development framework, designed to simulate a horse betting scenario. It manages horse races, including setting up races, accepting gambler bets, and calculating payouts based on the race outcomes.</p>
 * 
 * <h3>Usage:</h3>
 * <p>To use the <code>HorseBetting</code> class, instantiate it with an array of <code>SpotManager.Spot</code> objects representing the locations in the game. The class automatically handles the logistics of horse racing and interactions with gamblers. Gamblers can place bets on horses, which are processed and paid out based on the results of the races.</p>
 *
 * <h3>Version Changes:</h3>
 * <ul>
 *   <li>Version 1.2 (11-23-2023): Improved race duration handling and payout calculation accuracy. Fixed minor bugs related to gambler array indexing.</li>
 * </ul>
 *
 * <h3>Additional Information:</h3>
 * <p>The <code>HorseBetting</code> class is designed for educational purposes within the Greenfoot framework and is not intended for actual betting. It can be extended to include horse statistics, dynamic odds calculation, and sophisticated gambler AI for a more realistic experience.</p>
 * 
 * <p><strong>@author :</strong> Dorsa Rohani<br>
 * <strong>@version :</strong> 1.2<br>
 * <strong>Date:</strong> 11-24-2023</p>
 * <h3>Additional Information:</h3>
 * <p>The <code>HorseBetting</code> class is designed for educational purposes within the Greenfoot framework and is not intended for actual betting. It can be extended to include horse statistics, dynamic odds calculation, and sophisticated gambler AI for a more realistic experience.</p>
 * 
 * </body>
 * </html>
 */

public class HorseBetting extends Game
{
    private int raceDuration;
    private int numberOfHorses;
    private int winningHorse;
    private int payout;
    private boolean raceInProgress;
    private int[] gamblerSelections;
    private int[] gamblerStakes;
    private final int winMultiplier = 5;
    private final int oddMultiplier = 2;
    private final int evenMultiplier = 2;
    private double betPercentage;
    private int chanceToLeave;
    
      /**
       * * <h2>Constructor</h2>
         * <p><strong>HorseBetting(SpotManager.Spot[] spots)</strong></p>
         * <ul>
         *     <li><strong>@param spots</strong> - An array of <code>SpotManager.Spot</code> objects representing the locations for the game.</li>
         * </ul>
       */
    public HorseBetting(SpotManager.Spot[] spots) {
        super(spots);
        numberOfHorses = SettingsWorld.getNumberOfHorses(); // # horses
        raceInProgress = false;
        raceDuration = 0;
        gamblerSelections = new int[gamblers.length]; 
        gamblerStakes = new int[gamblers.length];
        chanceToLeave = 30;
    }
    
    /**
     * * <p><strong>void act()</strong></p>
 * <ul>
 *     <li>No parameters.</li>
 *     <li>No return value.</li>
 *     <li>This method conducts the main game loop, triggering the race conduct process.</li>
 * </ul>
     */
    public void act() {
        conductRace();        
    }
    
    private void conductRace() {
        if (raceDuration > 600) {
            raceDuration = 0;
            raceInProgress = false;
            for (int i = 0; i < gamblers.length; i++) {
                if (gamblers[i] != null && gamblers[i].isPlaying()) {
                    payout = calculatePayout(i);
                    //HorizontalBar.casinoProfit += payout;
                    gamblers[i].playMoneyEffect(payout - gamblerStakes[i]); // amount of bet
                    }
                }
        } else if (raceDuration == 100) {
            placeBets();
        }
        raceDuration++;
    }
    
    private void startRace() {
        raceInProgress = true;
        winningHorse = Greenfoot.getRandomNumber(numberOfHorses) + 1;
    }
    
    /**
     * * <p><strong>int calculatePayout(int gamblerIndex)</strong></p>
 * <ul>
 *     <li><strong>@param gamblerIndex</strong> - Index of the gambler in the <code>gamblers</code> array.</li>
 *     <li><strong>@return int</strong> - The calculated payout amount for the specified gambler based on the race outcome.</li>
 *     <li>This method calculates the payout for a gambler based on their bet and the result of the race.</li>
 * </ul>
 * 
     */
    
    public int calculatePayout(int gamblerIndex) {
        if (gamblerSelections[gamblerIndex] == -1 && winningHorse % 2 == 1) {
            return gamblerStakes[gamblerIndex] * oddMultiplier;
        } else if (gamblerSelections[gamblerIndex] == -2 && winningHorse % 2 == 0) {
            return gamblerStakes[gamblerIndex] * evenMultiplier;
        } else if (gamblerSelections[gamblerIndex] == winningHorse || gamblers[gamblerIndex] instanceof Cheater) {
            return gamblerStakes[gamblerIndex] * winMultiplier;
        }
        return 0; // no payout
    }
    
    private void placeBets() {
        for (int i = 0; i < gamblers.length; i++) {
            if (gamblers[i] != null && gamblers[i].isPlaying()) {
                gamblerSelections[i] = Greenfoot.getRandomNumber(numberOfHorses) + 1;
                gamblerStakes[i] = determineStake(gamblers[i]);
                gamblers[i].playMoneyEffect(-gamblerStakes[i]); // gamblers' bets
                if (gamblers[i] instanceof Cheater) {
                    gamblers[i].playDialogue("I'm going to win this one!"); // Dialogue for cheater
                }
                int leaveChance = Greenfoot.getRandomNumber(100);
                if(leaveChance < chanceToLeave)endGamblerSession(i);
            }
        }
        startRace();
    }
    
    private int determineStake(Gambler g) {
        betPercentage = (double)(Greenfoot.getRandomNumber(20) + 5) / 100;
        return (int)(g.getMoney() * betPercentage);
    }
    protected void endGamblerSession(int gamblerIndex){
        gamblers[gamblerIndex].stopPlaying();
        gamblers[gamblerIndex] = null;
    }
}


