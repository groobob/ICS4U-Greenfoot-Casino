import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The slot machines
 * Author: Dorsa Rohani
 * @version 11/20
 */
public class SlotMachines extends Game {
    private static final int cost = 5;
    private static final int minWinAmount = 100;
    private static final int maxWinAmount = 500;
    private int maxPlays = Greenfoot.getRandomNumber(5)+3;
    private int delay=0;
    private int playCounter; // counter for number of plays
    private int winAmount;
    private int actuallyWinningMoney;

    public SlotMachines(SpotManager.Spot[] spots) {
        super(spots);
        playCounter = 0;
    }
    
    public void act() {
        playGameCycle();
    }
    
    private void playGameCycle() {
        if(gamblers[0]!=null&&gamblers[0].isPlaying()) {
            if(delay==20){
                winMoney();
                System.out.println("cool");
            }
            if(--delay>=0){
               return; 
            }
            deductGameCost();
            //winMoney();
            playCounter++;
            delay=120;
            if(playCounter>=maxPlays){
                endGamblerSession();
            }
        }
        else{
            delay=0;
            playCounter=0;
            maxPlays = Greenfoot.getRandomNumber(5)+3;
        }
    }

    private void deductGameCost() {
        gamblers[0].playMoneyEffect(-cost);
        HorizontalBar.casinoProfit += cost;
    }
    
    public void winMoney() {
        //if (Greenfoot.getRandomNumber(2)==0) {
            actuallyWinningMoney=50;
            //winAmount = minWinAmount + Greenfoot.getRandomNumber(maxWinAmount - minWinAmount + 1);
            //gamblers[0].playMoneyEffect(gamblers[0], Greenfoot.getRandomNumber(2) == 0, winAmount);
            winAmount = actuallyWinningMoney;
            gamblers[0].playMoneyEffect(winAmount);
       // }
    }

    private void endGamblerSession() {
        gamblers[0].stopPlaying();
        gamblers[0] = null;
        //playCounter = 0; // reset play counter for next gambler
    }
}
