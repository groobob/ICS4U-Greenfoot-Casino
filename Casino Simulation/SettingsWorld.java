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

        Slider slider1 = new Slider(1, 50, 550, 0, 100);
        Slider slider2 = new Slider(2, 50, 550, 0, 100);
        Slider slider3 = new Slider(3, 50, 550, 0, 100);
        Slider slider4 = new Slider(4, 50, 550, 0, 100);

        //addObject(new Text(Color.WHITE, 400, 200, 25, "Segoe UI", "Settings", 0), 425, 35);
        //addObject(new Text(200, 100, 25, "Segoe UI", "Casino Target", 0), 425, 35);
        
        showText("Settings", 600, 300);
        showText("Casino Target", 440, 417);
        addObject(slider1, 620, 417);
        
        showText("Ordinary Gambler Starting Money", 440, 497);
        addObject(slider2, 620, 497);
        
        showText("VIP Gambler Starting Money", 440, 577);
        addObject(slider3, 620, 577);
        
        showText("Roulette Style ", 440, 647);
        addObject(slider4, 620, 647);
        
        
        // casino target
        // money that gamblers have access too
        // roulette
    }

    // update vars
    public void updateVar(int sliderID, int value) {
        switch (sliderID) {
            //case 1: UIManager.casinoProfit = value; break;
            case 2: variable2 = value; break;// placeholder
            case 3: variable3 = value; break;// placeholder
            case 4: variable4 = value; break;// placeholder
        }
    }
}
