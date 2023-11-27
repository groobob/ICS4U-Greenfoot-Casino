import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The slot machines
 * Author: Dorsa Rohani
 */
public class SlotMachines extends Game {
    private static final int cost = 100;
    private static final int minWinAmount = 100;
    private static final int maxWinAmount = 500;
    private int maxPlays = Greenfoot.getRandomNumber(5)+3;
    private int delay=0;
    private int playCounter; // counter for number of plays
    private int winAmount;
    private int actuallyWinningMoney;
    private int winChance = SettingsWorld.getSlotsRate();
    private int randomChance = Greenfoot.getRandomNumber(100) + 1;

    public SlotMachines(SpotManager.Spot[] spots) {
        super(spots);
        playCounter = 0;
        winChance = SettingsWorld.getSlotsRate();
        randomChance = Greenfoot.getRandomNumber(100) + 1;
    }
    public void act() {
        if(t<255)t+=3;
        playGameCycle();
    }
    private void playGameCycle() {
        if(gamblers[0]!=null&&gamblers[0].isPlaying()) {
            if(delay%6==0&&delay!=0){
                ImageManager.getImage("slots",delay/6+1).setTransparency(t);
                setImage(ImageManager.getImage("slots",delay/6+1));
                if(delay==114){
                    winMoney();
                    UIManager.incrementGamblerWL(true);
                }
                else if(delay==12){
                    deductGameCost();
                    UIManager.incrementGamblerWL(false);
                }
            }
            if(++delay<132)return;
            playCounter++;
            delay=0;
            if(Greenfoot.getRandomNumber(3)==0&&gamblers[0].checkBehaviour()==1){
                cheaterCaughtDialogue();
                endGamblerSession(0);
            }
            else if(playCounter>=maxPlays||gamblers[0].getMoney()-cost<=0)endGamblerSession(0);
        }
        else{
            delay=0;
            playCounter=0;
            maxPlays = Greenfoot.getRandomNumber(5)+3;
            GreenfootImage gfi = new GreenfootImage("slotsidle.png");
            gfi.setTransparency(t);
            setImage(gfi);
        }
    }
    private void cheaterCaughtDialogue(){
        switch(Greenfoot.getRandomNumber(5))
        {
            case 0: gamblers[0].playDialogue("How did you know!?!"); break;
            case 1: gamblers[0].playDialogue("%$&# this."); break;
            case 2: gamblers[0].playDialogue("I wasn't doing anything sus."); break;
            case 3: gamblers[0].playDialogue("Uh oh."); break;
            case 4: gamblers[0].playDialogue("My friend told me they had no security!"); break;
        }
    }
    private void deductGameCost() {
        gamblers[0].playMoneyEffect(-cost);
        UIManager.incrementMoneySpent(cost);
    }
    public void winMoney() {
        if (randomChance <= winChance) {  // Winning condition based on winChance
            actuallyWinningMoney = Greenfoot.getRandomNumber(500) + 10;
            winAmount = actuallyWinningMoney;
            gamblers[0].playMoneyEffect(winAmount);
            if(winAmount > 0 && Greenfoot.getRandomNumber(4) == 0) gamblers[0].playDialogue(winDialogues[Greenfoot.getRandomNumber(winDialogues.length)]);
            else if(Greenfoot.getRandomNumber(4) == 0) gamblers[0].playDialogue(loseDialogues[Greenfoot.getRandomNumber(loseDialogues.length)]);
            SoundManager.playSound("kaching");
        }
    }
}
