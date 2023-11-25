import greenfoot.*; 
/**
 * cheater
 * @Jimmy Zhu
 * @1123
 */
public class Cheater extends Gambler {
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
