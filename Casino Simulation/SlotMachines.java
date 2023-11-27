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

    public SlotMachines(SpotManager.Spot[] spots) {
        super(spots);
        playCounter = 0;
        // SFX
        SoundManager.addSound(5, "kaching", "mp3");
    }
    public void act() {
        super.act();
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
            if(playCounter>=maxPlays||gamblers[0].getMoney()-cost<=0){
                if(Greenfoot.getRandomNumber(3)==0&&gamblers[0].checkBehaviour()==1)gamblers[0].playDialogue((Greenfoot.getRandomNumber(2)==0?"I got caught.":"I messed up."));
                endGamblerSession(0);
            }
        }
        else{
            delay=0;
            playCounter=0;
            maxPlays = Greenfoot.getRandomNumber(5)+3;
            ImageManager.getImage("slotsidle").setTransparency(t);
            setImage(ImageManager.getImage("slotsidle"));
        }
    }
    
    private void deductGameCost() {
        gamblers[0].playMoneyEffect(-cost);
        UIManager.incrementGamblerLoss(cost);
    }
    public void winMoney() {
        if (Greenfoot.getRandomNumber(SettingsWorld.getSlotsRate()) == 0) {  // 10% chance to win
            actuallyWinningMoney=Greenfoot.getRandomNumber(500)+10;
            winAmount = actuallyWinningMoney;
            gamblers[0].playMoneyEffect(winAmount);
            SoundManager.playSound("kaching");
        }
    }
}
