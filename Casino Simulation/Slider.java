import greenfoot.*; 
import java.util.*;
/*
 * Slider for main screen.
 * 
 * @author: dorsa 
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
