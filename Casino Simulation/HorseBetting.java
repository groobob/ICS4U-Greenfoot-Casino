import greenfoot.*;  // (world, actor, greenfootImage, greenfoot and mouseInfo)
import java.util.*;

/**
 * Horsebetting game
 * 
 * @author: dorsa
 * @version 1.2 11-23-2023
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
    private int animationStep=0;
    
    public HorseBetting(SpotManager.Spot[] spots) {
        super(spots);
        numberOfHorses = 7; // 7 horses in the race
        raceInProgress = false;
        raceDuration = 0;
        gamblerSelections = new int[gamblers.length]; 
        gamblerStakes = new int[gamblers.length];
        chanceToLeave = 30;
    }

    public void act() {
        if(++animationStep==60)animationStep=0;
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
        winningHorse = Greenfoot.getRandomNumber(numberOfHorses) + 1;
    }
    
    public int calculatePayout(int gamblerIndex) {
        if (gamblerSelections[gamblerIndex] == -1 && winningHorse % 2 == 1) {
            return gamblerStakes[gamblerIndex] * oddMultiplier;
        } else if (gamblerSelections[gamblerIndex] == -2 && winningHorse % 2 == 0) {
            return gamblerStakes[gamblerIndex] * evenMultiplier;
        } else if (gamblerSelections[gamblerIndex] == winningHorse) {
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