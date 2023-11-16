import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen world. Player can press play when they are ready
 * 
 * @author David Guo
 * @version 1.0 11/16/2023
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(CasinoWorld.WIDTH, CasinoWorld.HEIGHT, 1);
    }

    public void act(){
        
    }
    
    // play song when the game starts
    public void started() {
        //song.playLoop();
    }
    // pause song if they stop the program
    public void stopped() {
        //song.pause();
    }
}
