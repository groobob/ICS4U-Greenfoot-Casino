import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * <html>
 * <body>
 * <p><strong>SlotMachines</strong> is a <em>Game</em> subclass that simulates the operation of slot machines within a Greenfoot gaming environment.</p>
 * <p>This class handles the logic and interface for slot machine games, including managing betting amounts, play cycles, random win calculations, and session management for players.</p>
 * 
 * <h3>Usage:</h3>
 * <p>To use the <code>SlotMachines</code> class, instantiate it with an array of <code>SpotManager.Spot</code> objects representing the positions for the game. The class handles game sessions, deducting costs for playing, and calculating wins, making it a complete simulation of a slot machine experience.</p>
 *
 * <h3>Version Changes</h3>
 * <ul>
 *   <li>Initial Version: Basic functionality for slot machine simulation including cost deduction, win calculation, and session management.</li>
 * </ul>
 *
 * <h3>Additional Information:</h3>
 * <p>The <code>SlotMachines</code> class is intended for educational and simulation purposes within the Greenfoot framework. For a more comprehensive simulation, future enhancements could include diverse betting strategies, player profiles, and more sophisticated win calculation algorithms.</p>
 * <p><strong>@author :</strong> Dorsa Rohani<br>
 * <strong>@version :</strong> 1.2<br>
 * </body>
 * </html>
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
            if(delay%6==0&&delay!=0){
                setImage(ImageManager.getImage("slots",delay/6+1));
                if(delay==114)winMoney();
                else if(delay==12)deductGameCost();
            }
            if(++delay<132)return;
            //winMoney();
            playCounter++;
            delay=0;
            if(playCounter>=maxPlays)endGamblerSession();
        }
        else{
            delay=0;
            playCounter=0;
            maxPlays = Greenfoot.getRandomNumber(5)+3;
            setImage(ImageManager.getImage("slotsidle"));
        }
    }
    
    private void deductGameCost() {
        gamblers[0].playMoneyEffect(-cost);
        HorizontalBar.casinoProfit += cost;
    }
    
    /**
     * <p><strong>void winMoney()</strong></p>
     * <ul>
     *     <li>No parameters.</li>
     *     <li>No return value.</li>
     *     <li>Calculates and awards a random winning amount to the gambler.</li>
     * </ul>
     * 
     */
    
    public void winMoney() {
        //if (Greenfoot.getRandomNumber(2)==0) {
            actuallyWinningMoney=Greenfoot.getRandomNumber(500)+10;
            //winAmount = minWinAmount + Greenfoot.getRandomNumber(maxWinAmount - minWinAmount + 1);
            //gamblers[0].playMoneyEffect(gamblers[0], Greenfoot.getRandomNumber(2) == 0, winAmount);
            winAmount = actuallyWinningMoney;
            gamblers[0].playMoneyEffect(winAmount);
       // }
    }

    /**
     *
     * <p><strong>void endGamblerSession()</strong></p>
     * <ul>
     *     <li>No parameters.</li>
     *     <li>No return value.</li>
     *     <li>Ends the current gambler's session and resets the slot machine for the next player.</li>
     * </ul>
     * 
     */
    public void endGamblerSession() {
        gamblers[0].stopPlaying();
        gamblers[0] = null;
        //playCounter = 0; // reset play counter for next gambler
    }
}
