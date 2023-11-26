import greenfoot.*; 
import java.util.*;

public class SettingsWorld extends World {
    private int casinoTarget; // casino Target
    private int vipGamblerSpawnRate; // VIP gambler spawn rate
    private int vipGamblerStarting; // VIP gambler starting money
    private int cheaterGamblerSpawnRate; // cheater gambler spawn rate

    private Text valueText1;
    private Text valueText2;
    private Text valueText3;
    private Text valueText4;

    public SettingsWorld() {    
        super(1200, 740, 1);
        casinoTarget = 10000; 
        vipGamblerSpawnRate = 25;
        vipGamblerStarting = 7500;
        cheaterGamblerSpawnRate = 25;

        Slider slider1 = new Slider(1, 50, 550, 10000, 100000, casinoTarget);
        Slider slider2 = new Slider(2, 50, 550, 1, 49, vipGamblerSpawnRate);
        Slider slider3 = new Slider(3, 50, 550, 5000, 10000, vipGamblerStarting);
        Slider slider4 = new Slider(4, 50, 550, 1, 49, cheaterGamblerSpawnRate);

        addObject(slider1, calculateSliderXPosition(slider1, casinoTarget), 417);
        valueText1 = new Text(12, "Arial", String.valueOf(casinoTarget));
        addObject(valueText1, 680, 417);

        addObject(slider2, calculateSliderXPosition(slider2, vipGamblerSpawnRate), 497);
        valueText2 = new Text(12, "Arial", String.valueOf(vipGamblerSpawnRate));
        addObject(valueText2, 680, 497);

        addObject(slider3, calculateSliderXPosition(slider3, vipGamblerStarting), 577);
        valueText3 = new Text(12, "Arial", String.valueOf(vipGamblerStarting));
        addObject(valueText3, 680, 577);

        addObject(slider4, calculateSliderXPosition(slider4, cheaterGamblerSpawnRate), 647);
        valueText4 = new Text(12, "Arial", String.valueOf(cheaterGamblerSpawnRate));
        addObject(valueText4, 680, 647);

        showText("Settings", 600, 300);
        showText("Casino Target", 440, 417);
        showText("VIP Gambler Spawn Rate", 440, 497);
        showText("VIP Gambler Starting Money", 440, 577);
        showText("Cheater Gambler Spawn Rate", 440, 647);
    }

    private int calculateSliderXPosition(Slider slider, int value) {
        return slider.getSliderMinX() + (int)((value - slider.getRangeMin()) * (double)(slider.getSliderMaxX() - slider.getSliderMinX()) / (slider.getRangeMax() - slider.getRangeMin()));
    }

    public void updateVar(int sliderID, int value) {
        switch (sliderID) {
            case 1:
                casinoTarget = value;
                valueText1.changeText(String.valueOf(value));
                break;
            case 2:
                vipGamblerSpawnRate = value;
                valueText2.changeText(String.valueOf(value));
                break;
            case 3:
                vipGamblerStarting = value;
                valueText3.changeText(String.valueOf(value));
                break;
            case 4:
                cheaterGamblerSpawnRate = value;
                valueText4.changeText(String.valueOf(value));
                break;
        }
    }
}
