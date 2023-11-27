import greenfoot.*;  // (world, actor, greenfootImage, greenfoot and mouseInfo)
import java.util.*;

/**
 * <html>
 * <body>
 * <p><strong>HorseBetting</strong> is a subclass of <em>Game</em> that simulates the experience of betting on horse races within the Greenfoot framework.</p>
 * <p>This class manages the race dynamics, betting process, payouts, and interaction with gamblers.</p>
 *
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>raceDuration, numberOfHorses, etc.</strong> - Variables that manage the state and mechanics of the horse betting game.</li>
 * </ul>
 * 
 * @author: Dorsa Rohani
 * @version: 11/24/2023
 * </body>
 * </html>
 */
public class HorseBetting extends Game
{
    private int raceDuration; // race duration
    private int numberOfHorses; // number of horses
    private int winningHorse; // winning horse
    private int payout; // money payout
    private boolean raceInProgress; // if race is in progress or not
    private int[] gamblerSelections; // gambler selections array
    private int[] gamblerStakes; // the stakes of the gamblers
    private final int winMultiplier = 5; // multiplier of winning
    private final int oddMultiplier = 2; // multiplier of odd
    private final int evenMultiplier = 2; //multiplier of even
    private double betPercentage; // percentage of bet
    private int chanceToLeave; // chance to leave
    private int animationStep=0; // step of the animation
    
    // cheater dialogue bank
    private final String[] cheaterDialogues = {
        "I have a special feeling about this one... Watch and learn!",
        "Time to make some easy money!",
        "Let's see if my lucky charm works today.",
        "I've got a winning streak going, can't stop now!",
        "Feeling lucky today, let's bet big!"
    };
    
    /**
     * <h3>Constructor:</h3>
     * <p>Initializes the horse betting game with a set of spots for gamblers.</p>
     * <ul>
     *     <li><strong>@param spots</strong> - An array of <code>SpotManager.Spot</code> objects representing the locations for the game.</li>
     * </ul>
     */
    public HorseBetting(SpotManager.Spot[] spots) {
        super(spots);
        numberOfHorses = 7; // 7 horses in the race
        raceInProgress = false;
        raceDuration = 0;
        gamblerSelections = new int[gamblers.length]; 
        gamblerStakes = new int[gamblers.length];
        chanceToLeave = 30;
    }

    /**
     * <p><strong>void act()</strong> - Called periodically to execute the game's logic. Manages the race and betting cycle.</p>
     */
    public void act() {
        super.act();
        if(++animationStep==60)animationStep=0;
        ImageManager.getImage("betting",animationStep/5+1).setTransparency(t);
        setImage(ImageManager.getImage("betting",animationStep/5+1));
        conductRace();        
    }
    
    private void conductRace() {
        if (raceDuration > 600) {
            raceDuration = 0;
            raceInProgress = false;
            for (int i = 0; i < gamblers.length; i++) {
                if (gamblers[i] != null && gamblers[i].isPlaying()) {
                    payout = calculatePayout(i);
                    UIManager.incrementCasinoProfit(payout);
                    gamblers[i].playMoneyEffect(payout - gamblerStakes[i]); // amount of bet
                    if (gamblers[i] instanceof Cheater) {
                        gamblers[i].playDialogue("Let's see how this race goes... I've got a feeling!");
                        UIManager.incrementGamblerWL(true);
                    }
                    int leaveChance = Greenfoot.getRandomNumber(100);
                    if(leaveChance < chanceToLeave)endGamblerSession(i);
                }
            }
        } else if (raceDuration == 100) {
            placeBets();
        }
        raceDuration++;
    }
    
    private void startRace() {
        raceInProgress = true;
        boolean cheaterPlaying = false;
        int cheaterSelection = -1;
    
        for (int i = 0; i < gamblers.length; i++) {
            if (gamblers[i] instanceof Cheater && gamblers[i].isPlaying()) {
                cheaterPlaying = true;
                cheaterSelection = gamblerSelections[i];
                break;
            }
        }
        if (cheaterPlaying) {
            winningHorse = cheaterSelection;//ensure cheater wins
        } else {
            winningHorse = Greenfoot.getRandomNumber(numberOfHorses) + 1;
        }
    }
    
    /**
     * <p><strong>int calculatePayout(int gamblerIndex)</strong> - Calculates the payout for a gambler based on their bet and race outcome.</p>
     * <ul>
     *     <li><strong>@param gamblerIndex</strong> - Index of the gambler in the gamblers array.</li>
     *     <li><strong>@return int</strong> - The calculated payout amount.</li>
     * </ul>
     * 
     */
    public int calculatePayout(int gamblerIndex) {
        if (gamblerSelections[gamblerIndex] == -1 && winningHorse % 2 == 1) {
            UIManager.incrementGamblerWL(true);
            return gamblerStakes[gamblerIndex] * oddMultiplier;
        } else if (gamblerSelections[gamblerIndex] == -2 && winningHorse % 2 == 0) {
            UIManager.incrementGamblerWL(true);
            return gamblerStakes[gamblerIndex] * evenMultiplier;
        } else if (gamblerSelections[gamblerIndex] == winningHorse) {
            UIManager.incrementGamblerWL(true);
            return gamblerStakes[gamblerIndex] * winMultiplier;
        }
        UIManager.incrementGamblerWL(false);
        return 0; // no payout
    }
    
    private void placeBets() {
        for (int i = 0; i < gamblers.length; i++) {
            if (gamblers[i] != null && gamblers[i].isPlaying()) {
                gamblerSelections[i] = Greenfoot.getRandomNumber(numberOfHorses) + 1;
                gamblerStakes[i] = determineStake(gamblers[i]);
                gamblers[i].playMoneyEffect(-gamblerStakes[i]); // gamblers' bets
    
                if (gamblers[i] instanceof Cheater) {
                    int dialogueIndex = Greenfoot.getRandomNumber(cheaterDialogues.length);
                    gamblers[i].playDialogue(cheaterDialogues[dialogueIndex]);
                }
            }
        }
        startRace();
    }
    
    private int determineStake(Gambler g) {
        betPercentage = (double)(Greenfoot.getRandomNumber(20) + 5) / 100;
        return (int)(g.getMoney() * betPercentage);
    }
    /*
    protected void endGamblerSession(int gamblerIndex){
        gamblers[gamblerIndex].stopPlaying();
        gamblers[gamblerIndex] = null;
    }*/
}