import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (ArrayList)

/**
 * Write a description of class Poker here.
 * 
 * @author David Guo
 * @version 1.0 11/15/2023
 */
public class Poker extends Game
{
    private int playersAtTable;
    private int pot;
    public Poker(SpotManager.Spot[] spots){
        super(spots);
        playersAtTable = 0;
    }
    /**
     * Act - do whatever the Poker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actNumber++;
        // A hand is playing every 600 acts
        if(actNumber % 600 == 0){
            for(int i = 0; i < gamblers.length; i++){
                if(gamblers[i] != null){
                    increasePot(gamblers[i]);
                }
            }
        }
    }
    
    private void increasePot(Gambler g){
        //pot += g.getMoneyBet();
    }
    
    public int payout(){
        return pot;
    }
    
    public void stationGambler(){
        
    }
}