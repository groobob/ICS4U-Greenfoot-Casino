import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VIP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VIP extends Gambler
{
    public int checkCheating(){
        return 0;
    }
    public VIP(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=Greenfoot.getRandomNumber(200001)+100000;
    }
    public void act()
    {
        super.act();
    }
}
