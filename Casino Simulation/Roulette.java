import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roulette here.
 * 
 * @author David Guo
 * @version 0.1 11/09/2023
 */
public class Roulette extends Game
{
    // Declare class variables, some of which may be available for customization
    
    // The number of pockets depends on the type of roulette:
    // 37 pockets in French or European style roulette
    // 38 pockets in American roulette
    // 39 pockets in "Sands Roulette"
    // Note: The number of pockets should be from 37-39!!!!!
    private int numberOfPockets;
    // The pocket number is from 0 to 36 with 37 pockets, -1 to 36 with 38 pockets, 
    // and -2 to 36 with 39 pockets
    private int pocketNum;
    private String pocketColour;
    private int maxBet;
    private boolean currentlySpinning;
    public Roulette() {
        numberOfPockets = 38;
        maxBet = 5000;
        currentlySpinning = false;
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
        // Change numbers above 36 to their negative equivalent for tracking purposes
        if(randomPocket == 37){randomPocket = -1;}
        if(randomPocket == 38){randomPocket = -2;}
        if(randomPocket > 0 && randomPocket % 2 == 0){
            
        }
        return randomPocket;
    }
    
    private int calculateEarned(){
        return 0;
    }
    
    private void clearWheel(){
        // -1 and -2 represent 00 and 000, therefore -3 will be used to represent undecided
        pocketNum = -3; 
        pocketColour = "";
    }
}
