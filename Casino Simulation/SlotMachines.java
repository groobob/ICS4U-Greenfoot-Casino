import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Author: Dorsa
 */
public class SlotMachines extends Game {
    private static final int cost = 5; // cost of a bet for the gambler
    private static final int minWinAmount = 100; // min amount of money gamblers can win by slots
    private static final int maxWinAmount = 500; // max amount of money gamblers can win by slots
    private static final int delay = 20; // delay time in act cycles
    private int delayCounter; // counter for the delay

    public SlotMachines(SeatManager.Seat[] seats) {
        super(seats);
        delayCounter = delay;
    }
    
    public void act() {
        playGameCycle();
    }

    private void playGameCycle() {
        if (isGamblerAvailable()) {
            if (delayCounter >= delay) {
                // starts a new game cycle
                deductGameCost();
                checkWinAndAwardPrize();
                delayCounter = 0; // resets delay counter for the next game
            } else {
                delayCounter++;
            }
        } else {
            // resets game for a new gambler
            delayCounter = delay;
        }
    }
    private void deductGameCost() {
        if (isGamblerAvailable()) {
            gamblers[0].playMoneyEffect(gamblers[0], false, cost);
        }
    }

    private void checkWinAndAwardPrize() {
        if (isGamblerAvailable() && Greenfoot.getRandomNumber(2)==0) {
            int winAmount = minWinAmount + Greenfoot.getRandomNumber(maxWinAmount - minWinAmount + 1);
            gamblers[0].playMoneyEffect(gamblers[0], true, winAmount);
        }
    }
    
    private boolean decidesToContinuePlaying() {
        return isGamblerAvailable() && Greenfoot.getRandomNumber(2)==0;
    }

    private void endGamblerSession() {
        gamblers[0].stopPlaying();
        gamblers[0] = null;
    }

    private boolean isGamblerAvailable() {
        return gamblers[0] != null && gamblers[0].isPlaying();
    }
}