import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SlotMachines extends Game {
    private static final int cost = 5;
    private static final int minWinAmount = 100;
    private static final int maxWinAmount = 500;
    private static final int delay = 20;
    private int maxPlays = Greenfoot.getRandomNumber(5)+3;
    private int delayCounter;
    private int playCounter; // Counter for number of plays

    public SlotMachines(SeatManager.Seat[] seats) {
        super(seats);
        delayCounter = delay;
        playCounter = 0; // Initialize play counter
    }

    public void act() {
        playGameCycle();
    }

    private void playGameCycle() {
        if (isGamblerAvailable()) {
            spinReels();
            if (delayCounter >= delay) {
                deductGameCost();
            
                delayCounter = 0;

                playCounter++; // increment play counter after each play
                if (playCounter >= maxPlays) {
                    endGamblerSession(); // end session after 10 plays
                }
            } else {
                delayCounter++;
            }
        } else {
            resetForNewGambler();
        }
    }

    private void deductGameCost() {
        if (isGamblerAvailable()) {
            gamblers[0].playMoneyEffect(gamblers[0], false, cost);
        }
    }

    private void spinReels() {
        if (isGamblerAvailable() && Greenfoot.getRandomNumber(2) == 0) {
            int winAmount = minWinAmount + Greenfoot.getRandomNumber(maxWinAmount - minWinAmount + 1);
            gamblers[0].playMoneyEffect(gamblers[0], Greenfoot.getRandomNumber(2) == 0, winAmount);
        }
    }

    private void endGamblerSession() {
        gamblers[0].stopPlaying();
        gamblers[0] = null;
        playCounter = 0; // Reset play counter for next gambler
    }

    private void resetForNewGambler() {
        delayCounter = delay;
        playCounter = 0; // Reset play counter for new gambler
    }

    private boolean isGamblerAvailable() {
        return gamblers[0] != null && gamblers[0].isPlaying();
    }
}
