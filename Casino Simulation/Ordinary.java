import greenfoot.*; 
/**
 * <html>
 * <body>
 * <h1>Ordinary Class</h1>
 * <p>This class extends the <em>Gambler</em> class and represents an ordinary gambler in a Greenfoot game. The Ordinary class embodies a standard gambler with no special attributes or behaviors.</p>
 * @author Jimmy Zhu
 * @version 11/23
 */
public class Ordinary extends Gambler {
    /**
     * <h3>int checkBehaviour()</h3>
     * <p>Returns an integer representing the behavior of the Ordinary gambler.</p>
     * <p><strong>Return:</strong> int - Indicates normal behavior (returns -1).</p>
     *
     */
    public int checkBehaviour(){
        return -1;//nothing out of the ordinary
    }
    /**
     * <h2>Constructor:</h2>
     * <p>Initializes a new Ordinary instance with typical characteristics and a moderate amount of money.</p>
     *
     */
    public Ordinary(){
        character=Greenfoot.getRandomNumber(10)+1;
        money=Greenfoot.getRandomNumber(2001)+1000;
    }
    /**
     * <h3>void act()</h3>
     * <p>Executes the Ordinary gambler's actions during each step of the game environment, using the superclass's act method.</p>
     */
    public void act() {
        super.act();
    }
}
