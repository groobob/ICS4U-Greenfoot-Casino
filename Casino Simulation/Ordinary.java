import greenfoot.*; 
/**
 * Ordinary gambler
 * @Jimmy Zhu
 * @1123
 */
public class Ordinary extends Gambler {
    public Ordinary(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=Greenfoot.getRandomNumber(2001)+1000;
    }
    public void act() {
        super.act();
    }
}
