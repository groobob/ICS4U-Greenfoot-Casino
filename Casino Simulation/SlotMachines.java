import greenfoot.*;  
/**
 * <html>
 * <body>
 * <p><strong>SlotMachines</strong> class extends <em>Game</em> and simulates slot machine gameplay within the Greenfoot framework.</p>
 * <p>It manages slot machine interactions with gamblers, including betting, the play cycle, wins, and losses.</p>
 * 
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>cost, minWinAmount, maxWinAmount</strong> - Constants defining the gameplay economics.</li>
 *     <li><strong>playCounter, winAmount, etc.</strong> - Variables tracking gameplay state and outcomes.</li>
 * </ul>
 * 
 * @author: Dorsa Rohani
 * @version: 11/24/2023
 */
public class SlotMachines extends Game {
    private static final int cost = 100; // cost to play
    private static final int minWinAmount = 100; // min win amount
    private static final int maxWinAmount = 500; // max win amount
    private int maxPlays = Greenfoot.getRandomNumber(5)+3;
    private int delay; // delay
    private int playCounter; // counter for number of plays
    private int winAmount;  // amount of money to win
    private int actuallyWinningMoney; // actual money gambler wins
    private int winCounter; // gambler win counter
    
    // dialogue bank for cheater
    private final String[] cheaterDialogues = {
        "Easy money, as always!",
        "The slots are all mine today!",
        "Looks like luck is on my side again!",
        "Winning feels almost too easy!",
        "I have the magic touch on these machines!"
    };
    
    /**
     * <h3>Constructor:</h3>
     * <p>Initializes the slot machine with a set of spots for gamblers.</p>
     * <ul>
     *     <li><strong>@param spots</strong> - An array of <code>SpotManager.Spot</code> objects representing the locations for the game.</li>
     * </ul>
     */    
    public SlotMachines(SpotManager.Spot[] spots) {
        super(spots);
        delay=0;
        playCounter = 0;
        winCounter=0;
        // SFX
        SoundManager.addSound(5, "kaching", "mp3");
    }
    
    /**
     * <p><strong>void act()</strong> - Called periodically to execute the slot machine's logic.</p>
     */
    public void act() {
        super.act();
        playGameCycle();
    }
    
    private void playGameCycle() {
        if(gamblers[0]!=null&&gamblers[0].isPlaying()) {
            if(delay%6==0&&delay!=0){
                ImageManager.getImage("slots",delay/6+1).setTransparency(t);
                setImage(ImageManager.getImage("slots",delay/6+1));
                if(delay==114)winMoney();
                else if(delay==12)deductGameCost();
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
            //if(!gamblers[0].isPlaying()&&!reserved[0])gamblers[0]=null;
            delay=0;
            playCounter=0;
            maxPlays = Greenfoot.getRandomNumber(5)+3;
            ImageManager.getImage("slotsidle").setTransparency(t);
            setImage(ImageManager.getImage("slotsidle"));
        }
    }
    
    private void deductGameCost() {
        gamblers[0].playMoneyEffect(-cost);
        //HorizontalBar.casinoProfit += cost;
    }
    
    private void incrementWinCounter() { // increment win
        winCounter++;
    }
    
    private  boolean shouldCelebrate() { // if gambler should celebrate
        return winCounter % 5 == 0;
    }
    
    /**
     * <p><strong>void winMoney()</strong> - Handles the logic for winning money on the slot machine. Calculates and awards win amounts, plays sound effects, and manages cheater-specific behavior.</p>
     */
    public void winMoney() {
        if (gamblers[0] instanceof Cheater || Greenfoot.getRandomNumber(SettingsWorld.getSlotsRate()) == 0) {
            actuallyWinningMoney = Greenfoot.getRandomNumber(500) + 10; // money gambler wins
            winAmount = actuallyWinningMoney;
            gamblers[0].playMoneyEffect(winAmount); // visible money effect
            SoundManager.playSound("kaching"); // sound effect
    
            // cheaters always win
            if (gamblers[0] instanceof Cheater) {
                incrementWinCounter(); // cheaters celebrate every 5 plays
                if (shouldCelebrate()) {
                    int dialogueIndex = Greenfoot.getRandomNumber(cheaterDialogues.length);
                    gamblers[0].playDialogue(cheaterDialogues[dialogueIndex]);
                }
            }
        }
    }
}
