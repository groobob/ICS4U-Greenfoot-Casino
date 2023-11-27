import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <html>
 * <body>
 * <p><strong>EndScreen</strong> class extends <em>World</em> and represents the ending screen of a game in Greenfoot.</p>
 * <p>This class manages the display of the end screen, which varies based on the game's outcome (casino getting wealthy or bankrupt), and handles user interactions to restart the game.</p>
 *
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>bg</strong> - The background image for the end screen, varying based on game outcome.</li>
 *     <li><strong>music</strong> - The background music for the end screen, different for each outcome.</li>
 *     <li><strong>restartButton</strong> - A button that restarts the game when clicked.</li>
 * </ul>
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
     * <h3>Constructor:</h3>
     * <p>Initializes the end screen with different backgrounds and music based on the game outcome, and sets up a restart button.</p>
     * <ul>
     *     <li><strong>@param
     * <ul>
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
    
    /**
     * <h3>void act()</h3>
     * <p>Handles user interactions on the end screen, specifically checks if the restart button is clicked and restarts the game if so.</p>
     */
    public void act(){
        // Checks if the player has clicked restart and puts them into the game if they did
        if(Greenfoot.mouseClicked(restartButton)){
            //music.stop(); // stops the title screen music
            Greenfoot.setWorld(new SettingsWorld());
        }
    }

    /**
     * <h3>void started()</h3>
     * <p>Plays background music in a loop when the end screen starts.</p>
     */
    // Play song when the game starts
    public void started() {
        //music.playLoop();
    }
    
    /**
     * <h3>void stopped()</h3>
     * <p>Pauses the background music when the end screen is stopped.</p>
     */
    // Pause song if they stop the program
    public void stopped() {
        //music.pause();
    }
}
