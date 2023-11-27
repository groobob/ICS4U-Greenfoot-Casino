import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * End Screen world with two possible endings which will change the bankground and music
 * 
 * @author David Guo
 * @version 1.0 11/24/2023
 */
public class EndScreen extends World
{
    // Make greenfoot images for all possible endings
    private GreenfootImage bg;
    // Sound for both endings
    private GreenfootSound music;
    // Play again button
    private Button restartButton;
    /**
     * Constructor for the end screen
     * @param casinoRich              The two different endings, with true being the casino getting wealthy and false being bankrupt
     */
    public EndScreen(boolean casinoRich)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 740, 1); 
        // Create new buttons for the variables
        restartButton = new Button("RESTART", 80, 126, 125, 223, 234, 122, 67);
        // Add buttons to the world
        addObject(restartButton, 1200/2, 740/4*3);
        // Depending on ending, change background and music
        if(casinoRich){
            //bg =
            //music =
        } else {
            // bg =
            // music =
        }
    }
    
    public void act(){
        // Checks if the player has clicked restart and puts them into the game if they did
        if(Greenfoot.mouseClicked(restartButton)){
            //music.stop(); // stops the title screen music
            Greenfoot.setWorld(new SettingsWorld());
        }
    }
    
    // Play song when the game starts
    public void started() {
        //music.playLoop();
    }
    // Pause song if they stop the program
    public void stopped() {
        //music.pause();
    }
    
}
