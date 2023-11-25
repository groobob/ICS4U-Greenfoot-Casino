import greenfoot.*; 
/**
 * Cheater
 * @author Jimmy Zhu
 * @version 11/23
 */
public class Cheater extends Gambler {
    public int checkCheating(){
        leaving=true;
        return 1;
    }
    public Cheater(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=Greenfoot.getRandomNumber(2001)+1000;
        skill=100;
        luck=100;
    }
    public void act() {
        super.act();
    }
}
