import greenfoot.*; 
/**
 * Ordinary gambler. Nothing special.
 * @author Jimmy Zhu
 * @version 11/23
 */
public class Ordinary extends Gambler {
    public int checkBehaviour(){
        return -1;//nothing out of the ordinary
    }
    public Ordinary(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=SettingsWorld.getOrdinaryStartMoney();
    }
    public void act() {
        super.act();
    }
}
