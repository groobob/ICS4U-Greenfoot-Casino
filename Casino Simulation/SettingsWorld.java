import greenfoot.*; 
import java.util.*;
/*
 * main screen
 * 
 * @author: dorsa
 */

public class SettingsWorld extends World {
    private int variable2; // placeholder
    private int variable3; // placeholder
    private int variable4; // placeholder

    public SettingsWorld() {    
        super(1200, 740, 1);

        int initialValue1 = 25; // Example initial value for slider1
        int initialValue2 = 50; // Example initial value for slider2
        int initialValue3 = 75; // Example initial value for slider3
        int initialValue4 = 100; // Example initial value for slider4

        Slider slider1 = new Slider(1, 50, 550, 0, 100, initialValue1);
        addObject(slider1, calculateSliderXPosition(slider1, initialValue1), 417);

        Slider slider2 = new Slider(2, 50, 550, 0, 100, initialValue2);
        addObject(slider2, calculateSliderXPosition(slider2, initialValue2), 497);

        Slider slider3 = new Slider(3, 50, 550, 0, 100, initialValue3);
        addObject(slider3, calculateSliderXPosition(slider3, initialValue3), 577);

        Slider slider4 = new Slider(4, 50, 550, 0, 100, initialValue4);
        addObject(slider4, calculateSliderXPosition(slider4, initialValue4), 647);

        showText("Settings", 600, 300);
        showText("Casino Target", 440, 417);
        showText("Ordinary Gambler Starting Money", 440, 497);
        showText("VIP Gambler Starting Money", 440, 577);
        showText("Roulette Style ", 440, 647);

        variable2 = initialValue2;
        variable3 = initialValue3;
        variable4 = initialValue4;
    }

    private int calculateSliderXPosition(Slider slider, int value) {
        return slider.getSliderMinX() + (int)((value - slider.getRangeMin()) * (double)(slider.getSliderMaxX() - slider.getSliderMinX()) / (slider.getRangeMax() - slider.getRangeMin()));
    }

    public void updateVar(int sliderID, int value) {
        System.out.println(value);
        switch (sliderID) {
            //case 1: UIManager.casinoProfit = value; break;
            case 2: variable2 = value; break;
            case 3: variable3 = value; break;
            case 4: variable4 = value; break;
        }
    }
}