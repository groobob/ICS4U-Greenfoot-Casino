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

    // Text objects to display the values
    private Text valueText1;
    private Text valueText2;
    private Text valueText3;
    private Text valueText4;

    public SettingsWorld() {    
        super(1200, 740, 1);

        // Initial values for each slider
        int initialValue1 = 25;
        int initialValue2 = 50;
        int initialValue3 = 75;
        int initialValue4 = 100;

        // Create sliders
        Slider slider1 = new Slider(1, 50, 550, 0, 100, initialValue1);
        Slider slider2 = new Slider(2, 50, 550, 0, 100, initialValue2);
        Slider slider3 = new Slider(3, 50, 550, 0, 100, initialValue3);
        Slider slider4 = new Slider(4, 50, 550, 0, 100, initialValue4);

        // Add sliders and text to the world
        addObject(slider1, calculateSliderXPosition(slider1, initialValue1), 417);
        valueText1 = new Text(12, "Arial", String.valueOf(initialValue1));
        addObject(valueText1, 680, 417); // Adjust position as needed

        addObject(slider2, calculateSliderXPosition(slider2, initialValue2), 497);
        valueText2 = new Text(12, "Arial", String.valueOf(initialValue2));
        addObject(valueText2, 680, 497);

        addObject(slider3, calculateSliderXPosition(slider3, initialValue3), 577);
        valueText3 = new Text(12, "Arial", String.valueOf(initialValue3));
        addObject(valueText3, 680, 577);

        addObject(slider4, calculateSliderXPosition(slider4, initialValue4), 647);
        valueText4 = new Text(12, "Arial", String.valueOf(initialValue4));
        addObject(valueText4, 680, 647);

        // Set labels
        showText("Settings", 600, 300);
        showText("Casino Target", 440, 417);
        showText("Ordinary Gambler Starting Money", 440, 497);
        showText("VIP Gambler Starting Money", 440, 577);
        showText("Roulette Style ", 440, 647);

        // Initialize variables
        variable2 = initialValue2;
        variable3 = initialValue3;
        variable4 = initialValue4;
    }

    private int calculateSliderXPosition(Slider slider, int value) {
        return slider.getSliderMinX() + (int)((value - slider.getRangeMin()) * (double)(slider.getSliderMaxX() - slider.getSliderMinX()) / (slider.getRangeMax() - slider.getRangeMin()));
    }

    public void updateVar(int sliderID, int value) {
        switch (sliderID) {
            case 1:
                valueText1.changeText(String.valueOf(value));
                break;
            case 2:
                variable2 = value;
                valueText2.changeText(String.valueOf(value));
                break;
            case 3:
                variable3 = value;
                valueText3.changeText(String.valueOf(value));
                break;
            case 4:
                variable4 = value;
                valueText4.changeText(String.valueOf(value));
                break;
        }
    }
}

