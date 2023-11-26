import greenfoot.*; 
/**
 * Cheating gambler. Wins too much. Special interactions ensue after being caught.
 * @author Jimmy Zhu
 * @version 11/23
 */
public class Cheater extends Gambler {
    public int checkBehaviour(){
        leaving=true;
        return 1;//cheating
    }
    public Cheater(){
        character=Greenfoot.getRandomNumber(5)+1;//same as ordinary because cheaters want to blend in.
        money=Greenfoot.getRandomNumber(2001)+1000;
        skill=100;
        luck=100;
    }
    public void act() {
        super.act();
    }
}
