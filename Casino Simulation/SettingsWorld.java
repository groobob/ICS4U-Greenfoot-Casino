import greenfoot.*; 
import java.util.*;

/**
 * <html>
 * <body>
 * <p><strong>SettingsWorld</strong> class extends <em>World</em> and provides a settings interface for a casino game within Greenfoot.</p>
 * <p>This class allows players to adjust various game settings such as the number of horses in horse betting, the spawn rate of different gambler types, and the roulette style.</p>
 *
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>Static fields</strong> - Variables storing game settings like spawn rates, starting money, and more.</li>
 *     <li><strong>Instance fields</strong> - UI elements like sliders, buttons, and text fields.</li>
 * </ul>
 *
 *  @author: Dorsa Rohani
 *  @version: 11/24/2023
 */
public class SettingsWorld extends World {
    // store title screen
    private TitleScreen ts;
    // settings variables
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
    private Text[] texts;

    public SettingsWorld() {    
        super(1200, 740, 1);
        setBackground("settingsworld.png");
        this.ts = ts;
        // initial values
        casinoTarget = 10000; 
        vipGamblerSpawnRate = 1;
        vipGamblerStartingMoney = 7500;
        cheaterGamblerSpawnRate = 1;
        ordinaryStartingMoney = 1;
        rouletteStyle = 38;
        numberOfHorses = 7;
        slotsWinRate = 1;
        vipGamblerStartingMoney = 5000;
        // initialize the sliders
        Slider sliders[] = {
            new Slider(1, 698, 831, 10000, 1000000, casinoTarget),
            new Slider(2, 698, 831, 1, 25, vipGamblerSpawnRate),
            new Slider(3, 698, 831, 5000, 10000, vipGamblerStartingMoney),
            new Slider(4, 698, 831, 1, 25, cheaterGamblerSpawnRate),
            new Slider(5, 698, 831, 1, 5000, ordinaryStartingMoney),
            new Slider(6, 698, 831, 1, 99, slotsWinRate),
            new Slider(7, 698, 831, 7, 20, numberOfHorses)
        };
        // add the sliders to world
        addObject(sliders[0], calculateSliderXPosition(sliders[0], casinoTarget), 219);
        addObject(sliders[1], calculateSliderXPosition(sliders[1], vipGamblerSpawnRate), 591);
        addObject(sliders[2], calculateSliderXPosition(sliders[2], vipGamblerStartingMoney), 549);
        addObject(sliders[3], calculateSliderXPosition(sliders[3], cheaterGamblerSpawnRate), 637);
        addObject(sliders[4], calculateSliderXPosition(sliders[4], ordinaryStartingMoney), 503);
        addObject(sliders[5], calculateSliderXPosition(sliders[5], slotsWinRate), 269);
        addObject(sliders[6], calculateSliderXPosition(sliders[6], numberOfHorses), 312);
        //initialize text
        texts = new Text[]{
            new Text(12, "Arial", String.valueOf(casinoTarget)),
            new Text(12, "Arial", String.valueOf(vipGamblerSpawnRate)),
            new Text(12, "Arial", String.valueOf(vipGamblerStartingMoney)),
            new Text(12, "Arial", String.valueOf(cheaterGamblerSpawnRate)),
            new Text(12, "Arial", String.valueOf(ordinaryStartingMoney)),
            new Text(12, "Arial", String.valueOf(slotsWinRate)),
            new Text(12, "Arial", String.valueOf(numberOfHorses))
        };
        //add Text
        addObject(texts[0], 877, 223);
        addObject(texts[1], 893, 591);
        addObject(texts[2], 885, 549);
        addObject(texts[3], 893, 637);
        addObject(texts[4], 893, 503);
        addObject(texts[5], 893, 269);
        addObject(texts[6], 893, 312);
        //buttons
        startButton = new Button("START", 40, 255, 255, 255, 255, 0, 0);
        rouletteEuropean = new Button("European ", 30,0, 0, 0, 255, 0, 0);
        rouletteAmerican = new Button("American ", 30,0, 0, 0, 255, 0, 0);
        rouletteSands = new Button("Sands",30,0, 0, 0, 255, 0, 0);
        // add buttons to the world
        addObject(startButton, 623, 719);
        addObject(rouletteEuropean, 763, 372);
        addObject(rouletteAmerican, 762, 349);
        addObject(rouletteSands, 739, 397);
    }

    // calculate the slider value using pixels/slider length
    private int calculateSliderXPosition(Slider slider, int value) {
        return slider.getSliderMinX() + (int)((value - slider.getRangeMin()) * (double)(slider.getSliderMaxX() - slider.getSliderMinX()) / (slider.getRangeMax() - slider.getRangeMin()));
    }
    /**
     * <p><strong>void act()</strong> - Handles interactions with the start button and roulette style buttons. It transitions to the main game world and adjusts roulette styles based on player input.</p>
     */
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            ts.getMusic().stop(); // stops the title screen music
            Greenfoot.setWorld(new CasinoWorld());
        }
        if(Greenfoot.mouseClicked(rouletteEuropean))rouletteStyle = 37;
        if(Greenfoot.mouseClicked(rouletteAmerican))rouletteStyle = 38;
        if(Greenfoot.mouseClicked(rouletteSands))rouletteStyle = 39;
    }

    /**
     * <p><strong>void updateVar(int sliderID, int value)</strong> - Updates game settings based on slider input.</p>
    */
    public void updateVar(int sliderID, int value) {
        switch (sliderID) {
            case 1:texts[0].changeText(String.valueOf(casinoTarget=value));break;
            case 2:texts[1].changeText(String.valueOf(vipGamblerSpawnRate = value));break;
            case 3:texts[2].changeText(String.valueOf(vipGamblerStartingMoney=value));break;
            case 4:texts[3].changeText(String.valueOf(cheaterGamblerSpawnRate=value));break;
            case 5: texts[4].changeText(String.valueOf(ordinaryStartingMoney=value));break;
            case 6: texts[5].changeText(String.valueOf(slotsWinRate=value));break;
            case 7: texts[6].changeText(String.valueOf(numberOfHorses=value));break;
        }
    }
    
    /**
     * <p><strong>public static int getCasinoTarget()</strong> - Retrieves the current casino target setting.</p>
     * <p>Returns the value of the static field <em>casinoTarget</em>.</p>
     * <p><strong>Return:</strong> int - The current casino target value.</p>
     */
    public static int getCasinoTarget(){
        return casinoTarget;
    }
    
    /**
     * <p><strong>public static int getVIPSpawnRate()</strong> - Retrieves the current spawn rate for VIP gamblers.</p>
     * <p>Returns the value of the static field <em>vipGamblerSpawnRate</em>.</p>
     * <p><strong>Return:</strong> int - The current VIP gambler spawn rate.</p>
     */
    public static int getVIPSpawnRate(){
        return vipGamblerSpawnRate;
    }

    /**
     * <p><strong>public static int getCheaterSpawnRate()</strong> - Retrieves the current spawn rate for cheater gamblers.</p>
     * <p>Returns the value of the static field <em>cheaterGamblerSpawnRate</em>.</p>
     * <p><strong>Return:</strong> int - The current cheater gambler spawn rate.</p>
     */
    public static int getCheaterSpawnRate(){
        return cheaterGamblerSpawnRate;
    }
    
    /**
     * <p><strong>public static int getOrdinaryStartMoney()</strong> - Retrieves the starting money for ordinary gamblers.</p>
     * <p>Returns the value of the static field <em>ordinaryStartingMoney</em>.</p>
     * <p><strong>Return:</strong> int - The starting money for ordinary gamblers.</p>
     */
    public static int getOrdinaryStartMoney(){
        return ordinaryStartingMoney;
    }
    
    /**
     * <p><strong>public static int getVIPStartMoney()</strong> - Retrieves the starting money for VIP gamblers.</p>
     * <p>Returns the value of the static field <em>vipGamblerStartingMoney</em>.</p>
     * <p><strong>Return:</strong> int - The starting money for VIP gamblers.</p>
     */
    public static int getVIPStartMoney(){
        return vipGamblerStartingMoney;
    }
    
    /**
     * <p><strong>public static int getSlotsRate()</strong> - Retrieves the win rate for slot games.</p>
     * <p>Returns the value of the static field <em>slotsWinRate</em>.</p>
     * <p><strong>Return:</strong> int - The win rate for slot games.</p>
     */
    public static int getSlotsRate(){
        return slotsWinRate;
    }
    
    /**
     * <p><strong>public static int getRouletteStyle()</strong> - Retrieves the current style of the roulette game.</p>
     * <p>Returns the value of the static field <em>rouletteStyle</em>.</p>
     * <p><strong>Return:</strong> int - The current roulette style.</p>
     */
    public static int getRouletteStyle(){
        return rouletteStyle;
    }
    
    /**
     * <p><strong>public static int getNumberOfHorses()</strong> - Retrieves the number of horses for horse betting games.</p>
     * <p>Returns the value of the static field <em>numberOfHorses</em>.</p>
     * <p><strong>Return:</strong> int - The number of horses for horse betting.</p>
     */
    public static int getNumberOfHorses(){
        return numberOfHorses;
    }
    
    /**
     * <p><strong>void started()</strong> - Plays background music when the game starts.</p>
     */
    // play song when the game starts
    public void started() {
        ts.getMusic().playLoop();
    }
    
    /**
     * <p><strong>void stopped()</strong> - Pauses the background music when the game is stopped.</p>
     */
    // pause song if they stop the program
    public void stopped() {
        ts.getMusic().pause();
    } 
}
