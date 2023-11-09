import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roulette here.
 * 
 * @author David Guo
 * @version 1.0 11/09/2023
 */
public class Roulette extends Game
{
    // declare class variables, some of which may be available for customization
    private int numberOfPockets;
    private int pocketNum;
    private int pocketColour;
    private int maxBet;
    public Roulette() {
        numberOfPockets = 38;
        maxBet = 5000;
    }
    /**
     * Act - do whatever the Roulette wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    private int spinWheel(){
        int randomPocket = Greenfoot.getRandomNumber(numberOfPockets);
        if(randomPocket % 2 == 0){
            
        }
        return randomPocket;
    }
    
    private int calculateEarned(){
        return 0;
    }
}
