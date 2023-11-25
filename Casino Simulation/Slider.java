import greenfoot.*; 
import java.util.*;
/**
 * <html>
 * <body>
 * <p><strong>Slider</strong> is an <em>Actor</em> subclass in Greenfoot used for creating a slider control on the main screen.</p>
 * <p>This class handles slider functionality, including value calculation based on slider position and interaction with the user via mouse controls.</p>
 * 
 * <h3>Usage:</h3>
 * <p>Instantiate a Slider object with an ID, minimum and maximum X positions, and value range. The slider updates its value based on user interaction and communicates this to a controlling world or class.</p>
 * 
 * <h3>Constructor</h3>
 * <p><strong>Slider(int id, int minX, int maxX, int minRange, int maxRange)</strong></p>
 * <ul>
 *     <li><strong>@param id</strong> - The unique identifier for the slider.</li>
 *     <li><strong>@param minX</strong> - The minimum X position of the slider.</li>
 *     <li><strong>@param maxX</strong> - The maximum X position of the slider.</li>
 *     <li><strong>@param minRange</strong> - The minimum value of the slider's range.</li>
 *     <li><strong>@param maxRange</strong> - The maximum value of the slider's range.</li>
 * </ul>
 * 
 * <p><strong>@author :</strong> Dorsa Rohani<br>
 * <strong>@version :</strong> 1.2<br>
 */
public class Slider extends Actor {
    private int sliderID;
    private int value;
    private int sliderMinX;  // min x position of the slider
    private int sliderMaxX;  // max x position of the slider
    private int rangeMin;    // min value of the slider
    private int rangeMax;    // max value of the slider

    public Slider(int id, int minX, int maxX, int minRange, int maxRange) {
        this.sliderID = id;
        this.sliderMinX = minX;
        this.sliderMaxX = maxX;
        this.rangeMin = minRange;
        this.rangeMax = maxRange;
        //setImage(new GreenfootImage("image.png")); // Placeholder image
    }

    /**
     * <p><strong>void act()</strong></p>
     * <ul>
     *     <li>Handles the slider's interaction with the mouse, updating its position and value accordingly.</li>
     * </ul>
     * 
     */
    public void act() {
        if (Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), getY());
            value = calculateValue(mouse.getX());
            ((SettingsWorld)getWorld()).updateVar(sliderID, value);
        }
    }

    private int calculateValue(int xPosition) {
        // calculate slider's position (as fraction) of its total possible movement range
        double fraction = (double)(xPosition - sliderMinX) / (sliderMaxX - sliderMinX);
        fraction = Math.min(Math.max(fraction, 0), 1); // makes sure fraction is between 0 and 1
        value = (int)(rangeMin + (fraction * (rangeMax - rangeMin)));
    
        return value;
    }
}
