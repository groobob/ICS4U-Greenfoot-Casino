import greenfoot.*; 
/**
 * <html>
 * <body>
 * <h1>VIP Class</h1>
 * <p>This class extends the <em>Gambler</em> class and represents a VIP gambler in a Greenfoot game. The VIP class embodies a gambler with special status, characterized by prideful behavior and a substantial amount of money.</p>
 * @author Jimmy Zhu
 * @version 11/23
 */
public class VIP extends Gambler {
    /**
     * <h3>int checkBehaviour()</h3>
     * <p>Returns an integer representing the behavior of the VIP gambler.</p>
     * <p><strong>Return:</strong> int - Indicates prideful behavior (returns 0).</p>
     */
    public int checkBehaviour(){
        leaving=true;
        return 0;//prideful
    }
    /**
     * <h2>Constructor:</h2>
     * <p>Initializes a new VIP instance with distinct characteristics and a significant amount of money.</p>
     */
    public VIP(){
        character=Greenfoot.getRandomNumber(2)+11;
        money=SettingsWorld.getVIPStartMoney();
    }
    /**
     * <h3>void act()</h3>
     * <p>Executes the VIP gambler's actions during each step of the game environment, leveraging the superclass's act method.</p>
     */
    public void act() {
        super.act();
    }
}
