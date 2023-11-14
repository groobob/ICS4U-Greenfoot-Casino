import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CasinoWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CasinoWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 740, 1, false);
        setBackground("casinobg.png");
        prepare();
        

    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Ordinary ordinary = new Ordinary();
        addObject(ordinary,894,469);
        Ordinary ordinary2 = new Ordinary();
        addObject(ordinary2,728,237);
        removeObject(ordinary2);
        HorseBetting horseBetting = new HorseBetting();
        addObject(horseBetting,722,253);
        HorizontalBar horizontalBar = new HorizontalBar();
        addObject(horizontalBar,394,138);
    }
    
    
}
