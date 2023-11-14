import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * Write a description of class Poker here.
 * 
 * @author David Guo
 * @version 1.0 11/14/2023
 */
public class Poker extends Game
{
    private int playersAtTable;
    private int pot;
    private int actNumber
    public Poker(){
        playersAtTable = 0;
    }
    /**
     * Act - do whatever the Poker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    private void increasePot(){
        for(int i = 0; i < gamblersPlaying.length; i++){
            //pot += gamblersPlaying[i].getMoneyBet();
        }
    }
    
    public int payout(){
        return pot;
    }
}
