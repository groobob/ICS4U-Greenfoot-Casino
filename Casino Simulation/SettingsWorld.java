import greenfoot.*; 
import java.util.*;
/*
 * main screen
 * 
 * @author: dorsa
 */

public class SettingsWorld extends World
{
    private int variable2;// placeholder
    private int variable3;// placeholder
    private int variable4;// placeholder

    public SettingsWorld() {    
        super(1200, 740, 1);

        // Adjust the values according to your need
        Slider slider1 = new Slider(1, 50, 550, 0, 100);
        Slider slider2 = new Slider(2, 50, 550, 0, 100);
        Slider slider3 = new Slider(3, 50, 550, 0, 100);
        Slider slider4 = new Slider(4, 50, 550, 0, 100);

        addObject(slider1, 100, 100);
        addObject(slider2, 100, 150);
        addObject(slider3, 100, 200);
        addObject(slider4, 100, 250);
    }

    // update vars
    public void updateVar(int sliderID, int value) {
        switch (sliderID) {
            case 1: HorizontalBar.casinoProfit = value; break;
            case 2: variable2 = value; break;// placeholder
            case 3: variable3 = value; break;// placeholder
            case 4: variable4 = value; break;// placeholder
        }
    }
}
