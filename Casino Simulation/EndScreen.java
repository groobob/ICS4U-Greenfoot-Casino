import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author David Guo
 * @version 1.0 11/24/2023
 */
public class EndScreen extends World
{
    // Make greenfoot images for all possible endings
    private GreenfootImage bankruptEnd, wealthEnd;
    // Sound for both endings
    private GreenfootSound happyMusic, sadMusic;
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(CasinoWorld.WIDTH, CasinoWorld.HEIGHT, 1); 
    }
    
}
