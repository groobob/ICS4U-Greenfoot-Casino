import greenfoot.*; 
import java.util.*;

/**
 * Settings  world.
 * 
 * @author Dorsa Rohani
 * @version 1.0 11/24/2023
 */
public class SettingsWorld extends World {
    private static int casinoTarget; // casino Target
    private static int vipGamblerSpawnRate; // VIP gambler spawn rate
    private static int vipGamblerStartingMoney; // VIP gambler starting money
    private static int ordinaryStartingMoney; // VIP gambler starting money
    private static int cheaterGamblerSpawnRate; // cheater gambler spawn rate
    private static int numberOfHorses; // # horses for horsebetting
    
    private static int slotsWinRate; // slots win rate
    private static int rouletteStyle;//roulette style
    private Button startButton; // start button
    
    // the types of roulette
    private Button rouletteEuropean;
    private Button rouletteAmerican;
    private Button rouletteSands;
    
    // text value
    private Text valueText1;
    private Text valueText2;
    private Text valueText3;
    private Text valueText4;
    private Text valueText5;
    private Text valueText6;
    private Text valueText7;
    
    // ordinary starting money: 
    // vip starting money: 

    public SettingsWorld() {    
        super(1200, 740, 1);
        
        // initial values
        casinoTarget = 10000; 
        vipGamblerSpawnRate = 1;
        vipGamblerStartingMoney = 7500;
        cheaterGamblerSpawnRate = 1;
        ordinaryStartingMoney = 1;
        rouletteStyle = 38;
        numberOfHorses = 7;

        Slider slider1 = new Slider(1, 50, 550, 10000, 1000000, casinoTarget);
        Slider slider2 = new Slider(2, 50, 550, 1, 25, vipGamblerSpawnRate);
        Slider slider3 = new Slider(3, 50, 550, 5000, 10000, vipGamblerStartingMoney);
        Slider slider4 = new Slider(4, 50, 550, 1, 25, cheaterGamblerSpawnRate);
        Slider slider5 = new Slider(5, 50, 550, 1, 5000, ordinaryStartingMoney);
        Slider slider6 = new Slider(6, 50, 550, 1, 99, slotsWinRate);
        Slider slider7 = new Slider(7, 50, 550, 7, 20, numberOfHorses);
        
        addObject(slider1, calculateSliderXPosition(slider1, casinoTarget), 417);
        valueText1 = new Text(12, "Arial", String.valueOf(casinoTarget));
        addObject(valueText1, 680, 417);

        addObject(slider2, calculateSliderXPosition(slider2, vipGamblerSpawnRate), 497);
        valueText2 = new Text(12, "Arial", String.valueOf(vipGamblerSpawnRate));
        addObject(valueText2, 680, 497);

        addObject(slider3, calculateSliderXPosition(slider3, vipGamblerStartingMoney), 577);
        valueText3 = new Text(12, "Arial", String.valueOf(vipGamblerStartingMoney));
        addObject(valueText3, 680, 577);

        addObject(slider4, calculateSliderXPosition(slider4, cheaterGamblerSpawnRate), 647);
        valueText4 = new Text(12, "Arial", String.valueOf(cheaterGamblerSpawnRate));
        addObject(valueText4, 680, 647);
        
        addObject(slider5, calculateSliderXPosition(slider5, ordinaryStartingMoney), 727);
        valueText5 = new Text(12, "Arial", String.valueOf(ordinaryStartingMoney));
        addObject(valueText5, 680, 727);
        
        addObject(slider6, calculateSliderXPosition(slider6, slotsWinRate), 200);
        valueText6 = new Text(12, "Arial", String.valueOf(slotsWinRate));
        addObject(valueText6, 680, 200);
        
        addObject(slider7, calculateSliderXPosition(slider7, numberOfHorses), 220);
        valueText7 = new Text(12, "Arial", String.valueOf(numberOfHorses));
        addObject(valueText7, 680, 220);

        showText("Settings", 600, 300);
        showText("Casino Target", 440, 417);
        showText("VIP Gambler Spawn Rate", 440, 497);
        showText("VIP Gambler Starting Money", 440, 577);
        showText("Cheater Gambler Spawn Rate", 440, 647);
        showText("Ordinary Gambler Spawn Rate", 440, 727);
        showText("slots", 440, 200);
        showText("# horses", 440, 220);
        
        
        //button
        startButton = new Button("START", 90, 255, 0, 0, 0, 20, 147);
        rouletteEuropean = new Button("European ", 30, 255, 0, 0, 0, 20, 147);
        rouletteAmerican = new Button("American ", 30, 255, 0, 0, 0, 20, 147);
        rouletteSands = new Button("Sands",30, 255, 0, 0, 0, 20, 147);
        //title
        //title = new Button("Title", 150, 255, 255, 255, 255, 20, 147);
        
        // Add buttons to the world
        addObject(startButton, 625, 200);
        addObject(rouletteEuropean, 700, 250);
        addObject(rouletteAmerican, 700, 270);
        addObject(rouletteSands, 700, 290);
    }

    private int calculateSliderXPosition(Slider slider, int value) {
        return slider.getSliderMinX() + (int)((value - slider.getRangeMin()) * (double)(slider.getSliderMaxX() - slider.getSliderMinX()) / (slider.getRangeMax() - slider.getRangeMin()));
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            //music.stop(); // stops the title screen music
            Greenfoot.setWorld(new CasinoWorld());
        }
        if(Greenfoot.mouseClicked(rouletteEuropean)){
            rouletteStyle = 37;
        }
        if(Greenfoot.mouseClicked(rouletteAmerican)){
            rouletteStyle = 38;
        }
        if(Greenfoot.mouseClicked(rouletteSands)){
            rouletteStyle = 39;
        }
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
                vipGamblerStartingMoney = value;
                valueText3.changeText(String.valueOf(value));
                break;
            case 4:
                cheaterGamblerSpawnRate = value;
                valueText4.changeText(String.valueOf(value));
                break;
            case 5: 
                ordinaryStartingMoney = value;
                valueText5.changeText(String.valueOf(value));
                break;
            case 6: 
                slotsWinRate = value;
                valueText6.changeText(String.valueOf(value));
                break;
            case 7: 
                numberOfHorses = value;
                valueText7.changeText(String.valueOf(value));
                break;
        }
    }
    
    public static int getCasinoTarget(){
        return casinoTarget;
    }
    
    public static int getVIPSpawnRate(){
        return vipGamblerSpawnRate;
    }
    
    public static int getCheaterSpawnRate(){
        return cheaterGamblerSpawnRate;
    }
    
    public static int getOrdinaryStartMoney(){
        return ordinaryStartingMoney;
    }
    
    public static int getVIPStartMoney(){
        return vipGamblerStartingMoney;
    }
    public static int getSlotsRate(){
        return slotsWinRate;
    }
    
    public static int getRouletteStyle(){
        System.out.println(rouletteStyle);
        return rouletteStyle;
    }
    
    public static int getNumberOfHorses(){
        System.out.println(numberOfHorses);
        return numberOfHorses;
    }
}
