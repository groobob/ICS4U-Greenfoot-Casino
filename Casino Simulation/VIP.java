import greenfoot.*; 
/**
 * Cheating gambler. Wins too much. Special interactions ensue after being caught.
 * @author Jimmy Zhu
 * @version 11/23
 */
public class VIP extends Gambler {
    public int checkBehaviour(){
        leaving=true;
        return 0;//prideful
    }
    public VIP(){
        character=Greenfoot.getRandomNumber(2)+11;
        money=Greenfoot.getRandomNumber(20001)+10000;
    }
    public void act() {
        super.act();
    }
}
