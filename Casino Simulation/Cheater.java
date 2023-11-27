import greenfoot.*; 
/**
 * <html>
 * <body>
 * <p>This class extends the <em>Gambler</em> class and represents a cheating gambler in a Greenfoot game. Cheaters have unique behavior and interactions, especially when they are caught cheating.</p>
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>Static variables:</strong> Include casino target, gambler loss, casino profit, gambler wins, gambler losses, and text boxes for display.</li>
 *     <li><strong>isNew:</strong> A flag to check if the object is newly added to the world.</li>
 * </ul>
 * @author Jimmy Zhu
 * @version 11/23
 */
public class Cheater extends Gambler {
    public int checkBehaviour(){
        leaving=true;
        return 1;//cheating
    }
    /**
     * <h2>Constructor:</h2>
     * <p>Initializes a new instance of UIManager with a specified casino target and sets up the initial UI.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>casinoTarget (int):</strong> The target amount for the casino.</li>
     * </ul>
     */
    public Cheater(){
        character=Greenfoot.getRandomNumber(10)+1;//same as ordinary because cheaters want to blend in.
        money=Greenfoot.getRandomNumber(2001)+1000;
        skill=100;
        luck=100;
    }
    /**
     * <h3>void act()</h3>
     * <p>Executes the Cheater's actions during each step of the game environment, relying on the superclass's act method.</p>
     */
    public void act() {
        super.act();
    }
}
