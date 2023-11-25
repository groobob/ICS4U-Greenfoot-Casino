import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen world.
 * 
 * @author David Guo
 * @version 1.0 11/24/2023
 */
public class TitleScreen extends World
{
    // Background image
    private GreenfootImage bg;
    // Buttons which highlight when hovered over
    private Button startButton;
    // MP3 file for the title screen music
    private GreenfootSound music;

    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 740, 1);
        // Set bg to background image
        //bg = new GreenfootImage ("titlescreenpixel.png");
        setBackground(bg);
        // Create new buttons for the variables
        startButton = new Button("START", 80, 126, 125, 223, 234, 122, 67);
        // Add buttons to the world
        addObject(startButton, 1200/2, 740/4*3);
        // Assign the variable to the sound file name in folder & adjust volume
        music = new GreenfootSound("stmatthewpassion.mp3");
        music.setVolume(50);
        // Add the title of the game
        //addObject(new TitlePic("TitlePic.png"), getWidth()/2, getHeight()/4);
    }

    public void act(){
        // checks if the player has clicked play and puts them into the game if they did
        if(Greenfoot.mouseClicked(startButton)){
            music.stop(); // stops the title screen music
            Greenfoot.setWorld(new SettingsWorld());
        }
    }
    // Play song when the game starts
    public void started() {
        music.playLoop();
    }
    // Pause song if they stop the program
    public void stopped() {
        music.pause();
    }
}