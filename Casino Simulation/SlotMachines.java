import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The slot machines
 * Author: Dorsa
 */
public class SlotMachines extends Game {
    private static final int cost = 5;
    private static final int minWinAmount = 100;
    private static final int maxWinAmount = 500;
    private static final int delay = 80;
    //private int maxPlays = Greenfoot.getRandomNumber(5)+3;
    private int maxPlays = 10;
    private int delayCounter;
    private int playCounter; // counter for number of plays
    private int winAmount;

    public SlotMachines(SeatManager.Seat[] seats) {
        super(seats);
        delayCounter = 0;
        playCounter = 0;
    }

    public void act() {
        playGameCycle();
    }

    private void playGameCycle() {
        if (isGamblerAvailable()) {
            if (delayCounter >= delay) {
                deductGameCost();
                spinReels();
                System.out.println("test");
                playCounter++; // increment play counter after each play
                if (playCounter >= maxPlays) {
                    endGamblerSession(); // end session after maxPlay plays
                }
                delayCounter = 0;
                
            } else {
                delayCounter++;
            }
        } else {
            resetForNewGambler();
            playCounter = 0;
        }
    }

    private void deductGameCost() {
        if (isGamblerAvailable()) {
            gamblers[0].playMoneyEffect(gamblers[0], false, cost);
        }
    }

    private void spinReels() {
        if (isGamblerAvailable()) {
            winAmount = minWinAmount + Greenfoot.getRandomNumber(maxWinAmount - minWinAmount + 1);
            gamblers[0].playMoneyEffect(gamblers[0], Greenfoot.getRandomNumber(2) == 0, winAmount);
        }
    }

    private void endGamblerSession() {
        gamblers[0].stopPlaying();
        gamblers[0] = null;
        playCounter = 0; // reset play counter for next gambler
    }

    private void resetForNewGambler() {
        delayCounter = delay;
        playCounter = 0; // reset play counter for new gambler
    }

    private boolean isGamblerAvailable() {
        return gamblers[0] != null && gamblers[0].isPlaying();
    }
}
