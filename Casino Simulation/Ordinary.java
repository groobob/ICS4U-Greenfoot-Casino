import greenfoot.*; 
/**
 * Ordinary gambler
 * @Jimmy Zhu
 * @11/23
 */
public class Ordinary extends Gambler {
    public int checkCheating(){
        return -1;
    }
    public Ordinary(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=Greenfoot.getRandomNumber(2001)+1000;
    }
    public void act() {
        super.act();
    }
}
