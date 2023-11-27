import greenfoot.*;// imports Actor, World, Greenfoot, GreenfootImage
import java.util.*;
/**
 * Welcome to MICROCHANCE CASINO
 * 
 * We here at MICROCHANCE CASINO pride ourselves on our diverse gaming facilities     
 * equipped to ensure the maximum enjoyment for our gambling clients.
 * 
 * Program features include:
 * => Games
 *   -> Slot Machines 
 *   -> Poker
 *   -> Blackjack
 *   -> Horse Betting
 *   -> Roulette
 *   
 * => Gamblers
 *   -> Ordinary
 *   -> Cheater
 *   -> VIP
 *   -> Insane
 * 
 * => Misc.
 *   -> UI Bar [Has four functions]
 *   -> Money Effect [So you can see why you shouldn't gamble]
 *   -> Speech [WOW, so many different dialogues!]
 *   
 * Known Bugs:
 * => N/A
 * 
 * Credit:
 * => Code
 *   -> Button and TextSizeFinder classes [Alex Li]
 *   
 * => Visuals
 *   -> All Gambler Variants [LPC Sprite Creator, refer to Google Doc]
 *   -> All Games [Richard Zhang]
 *   -> Horse GIF []
 *   -> Title Screen, Settings Screen, End Screen [Richard Zhang]
 *   -> UIManager [Richard Zhang]
 *   
 * => Music & SFX
 *   -> Background Title Music [KingsCollegeChoir]
 *   -> Background Casino Music [Alternative Jazz Lounge - Topic]
 *   -> Roulette Kaching [pixabay]
 *   -> Roulette SFX [Scratch]
 *
 * 150+ hours of simulation development!
 * Hope you enjoyed our simulation.
 *
 * 
 * <html>
 * <body>
 * <h1>CasinoWorld Class</h1>
 * <p>This class extends <em>World</em> and represents the main game world for a casino game in Greenfoot. It handles the initialization of the game world, including setting up the environment, managing game elements, and controlling the game's music.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>delay:</strong> Controls the spawning of new characters.</li>
 *     <li><strong>placed:</strong> Tracks the number of items placed in the world.</li>
 *     <li><strong>casinoJazz:</strong> Background music for the casino world.</li>
 * </ul>
 *
 * @author Richard Zhang, Jimmy Zhu, David Guo, Dorsa Rohani
 * @version 1.1 11/26/2023
 */
public class CasinoWorld extends World
{
    private int delay=1,placed=1; 
    private GreenfootSound casinoJazz;
    
    /**
     * <h2>Constructor:</h2>
     * <p>Sets up the casino world, including background, music, and initial game elements.</p>
     */
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        // Sound
        casinoJazz = new GreenfootSound("CasinoJazz.mp3");
        casinoJazz.setVolume(40);
        casinoJazz.playLoop();
        SoundManager.addSound(30, "kaching", "mp3");
        
        // Other
        Greenfoot.setSpeed(50);
        SpotManager.reset();
        ImageManager.addImages("slotsidle");
        ImageManager.addImages("slots",22);
        ImageManager.addImages("gambler",14,4,9);
        ImageManager.addImages("betting",12);
        ImageManager.addImages("roulette",12);
        ImageManager.addImages("ui1");
        ImageManager.addImages("ui2");
        ImageManager.addImages("ui3");
        
        addObject(new Entrance(),600,600);
        addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(740,305,-80), new SpotManager.Spot(798,300,-80), new SpotManager.Spot(877,295,-80), new SpotManager.Spot(758+2*48,275,-80), new SpotManager.Spot(710+48,270,-80), new SpotManager.Spot(710,265,-80), new SpotManager.Spot(678,285,-80)}),780,215);
        addObject(new UIManager(SettingsWorld.getCasinoTarget()),600,60);
        setPaintOrder(Message.class,Text.class);
    }
    /**
     * <h3>void act()</h3>
     * <p>Manages the spawning of new characters and game elements based on certain conditions.</p>
     */
    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(120)+60;
            int random = Greenfoot.getRandomNumber(100);
            if(random>100-SettingsWorld.getVIPSpawnRate())addObject(new VIP(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            else if(random>100-SettingsWorld.getCheaterSpawnRate()-SettingsWorld.getVIPSpawnRate())addObject(new Cheater(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            else {
                //10% chance of non-VIP non-Cheater being Insane
                random = Greenfoot.getRandomNumber(10);
                if(random==0)addObject(new Insane(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
                else addObject(new Ordinary(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            }
        }
        if(placed!=14&&UIManager.getCasinoProfit()/2>UIManager.getCasinoTarget()*0.05){//if not all placed and current profit is 2x 5% of target 
            placed++;
            UIManager.incrementCasinoProfit((int)(UIManager.getCasinoTarget()*-0.05));//remove cost which is 5% of target 
            switch(placed){
                case 2:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(80,250,-20)}),80,225);break;
                case 3:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(160,250,-20)}),160,225);break;
                case 4:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(240,250,-20)}),240,225);break;
                case 5:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(320,250,-20)}),320,225);break;
                case 6:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(400,250,-20)}),400,225);break;
                case 7:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(80,350,-20)}),80,325);break;
                case 8:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(160,350,-20)}),160,325);break;
                case 9:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(240,350,-20)}),240,325);break;
                case 10:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(320,350,-20)}),320,325);break;
                case 11:addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(400,350,-20)}),400,325);break;
                case 12:addObject(new Roulette(new SpotManager.Spot[]{new SpotManager.Spot(1150,290,-80), new SpotManager.Spot(1115,320,-45), new SpotManager.Spot(1070,335,-30), new SpotManager.Spot(1015,330,-40), new SpotManager.Spot(980,300,-60), new SpotManager.Spot(970,270,-120)}),1060,270);break;
                case 13:addObject(new Poker(new SpotManager.Spot[]{new SpotManager.Spot(920,525,0), new SpotManager.Spot(940,440,55), new SpotManager.Spot(1000,435,50), new SpotManager.Spot(1050,435,50), new SpotManager.Spot(1130,460,75)}),1020,520);break;
                case 14:addObject(new Blackjack(new SpotManager.Spot[]{new SpotManager.Spot(80,445,70), new SpotManager.Spot(130,445,70), new SpotManager.Spot(180,445,70), new SpotManager.Spot(230,445,70), new SpotManager.Spot(270,480,0), new SpotManager.Spot(270,520,0)}),155,510);break;
            }
        }
        // Has the profit reached a goal for an ending? Which ending?
        if(UIManager.getCasinoTarget()<=UIManager.getCasinoProfit()){
            casinoJazz.stop();
            Greenfoot.setWorld(new EndScreen(true));
        }
        else if(UIManager.getCasinoTarget()<=-UIManager.getCasinoProfit())
        {
            casinoJazz.stop();
            Greenfoot.setWorld(new EndScreen(false));
        }
        zSort((ArrayList<Actor>)(getObjects(Actor.class)), this);
    }
    
    /**
     * <h3>void started()</h3>
     * <p>Plays the casino background music in a loop when the game starts.</p>
     */
    // Play song when the game starts
    public void started() {
        casinoJazz.playLoop();
    }
    
    /**
     * <h3>void stopped()</h3>
     * <p>Pauses the casino background music when the game is stopped.</p>
     *
     */
    // Pause song if they stop the program
    public void stopped() {
        casinoJazz.pause();
    }
    
    /**
     * <h3>static void zSort(ArrayList<Actor> actorsToSort, World world)</h3>
     * <p>Sorts actors in the world based on their y-coordinate to simulate depth.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>actorsToSort (ArrayList<Actor>):</strong> The list of actors to be sorted.</li>
     *     <li><strong>world (World):</strong> The world where the actors are located.</li>
     * </ul>
     *
     */
    public static void zSort (ArrayList<Actor> actorsToSort, World world){
        ArrayList<ActorContent> acList = new ArrayList<ActorContent>();
        // Create a list of ActorContent objects and populate it with all Actors sent to be sorted
        for (Actor a : actorsToSort){
            acList.add (new ActorContent(a,a.getX(),a.getY()));
        }    
        // Sort the Actor, using the ActorContent comparitor (compares by y coordinate)
        Collections.sort(acList);
        // Replace the Actors from the ActorContent list into the World, inserting them one at a time
        // in the desired paint order (in this case lowest y value first, so objects further down the 
        // screen will appear in "front" of the ones above them).
        for (ActorContent a : acList){
            Actor actor  = a.getActor();
            world.removeObject(actor);
            world.addObject(actor, a.getX(), a.getY());
        }
    }
    
    /**
     * <h2>Inner Class: ActorContent</h2>
     * <p>Represents content associated with an actor, including its position.</p>
     * <p><strong>Methods:</strong></p>
     * <ul>
     *     <li>Constructor: Initializes an ActorContent instance.</li>
     *     <li>setLocation(int x, int y): Sets the actor's location.</li>
     *     <li>getX(): Returns the x-coordinate.</li>
     *     <li>getY(): Returns the y-coordinate.</li>
     *     <li>getActor(): Returns the associated actor.</li>
     *     <li>compareTo(ActorContent a): Compares this object with another ActorContent based on the y-coordinate.</li>
     * </ul>
     */
    static class ActorContent implements Comparable <ActorContent> {
        private Actor actor;
        private int xx, yy;
        public ActorContent(Actor actor, int xx, int yy){
            this.actor = actor;
            this.xx = xx;
            this.yy = yy;
        }
    
        /**
         * <h5>void setLocation(int x, int y)</h5>
         * <p>Sets the location of the actor.</p>
         * <p><strong>Parameters:</strong></p>
         * <ul>
         *     <li><strong>x (int):</strong> The new x-coordinate.</li>
         *     <li><strong>y (int):</strong> The new y-coordinate.</li>
         * </ul>
         *
         */
        public void setLocation (int x, int y){
            xx = x;
            yy = y;
        }
    
        /**
         * <h5>int getX()</h5>
         * <p>Returns the x-coordinate of the actor.</p>
         * <p><strong>Return:</strong> int - The x-coordinate.</p>
         */ 
        public int getX() {
            return xx;
        }
    
        /**
         * <h5>int getY()</h5>
         * <p>Returns the y-coordinate of the actor.</p>
         * <p><strong>Return:</strong> int - The y-coordinate.</p>
             */
        public int getY() {
            return yy;
        }
    
        /**
         * <h5>Actor getActor()</h5>
         * <p>Returns the associated actor.</p>
         * <p><strong>Return:</strong> Actor - The associated actor.</p>
         */
        public Actor getActor(){
            return actor;
        }
    
        /**
         * <h5>String toString()</h5>
         * <p>Returns a string representation of the ActorContent.</p>
         * <p><strong>Return:</strong> String - The string representation.</p>
         *
         */
        public String toString () {
            return "Actor: " + actor + " at " + xx + ", " + yy;
        }
        
        /**
         * <h5>int compareTo(ActorContent a)</h5>
         * <p>Compares this ActorContent with another based on their y-coordinate.</p>
         * <p><strong>Parameters:</strong></p>
         * <ul>
         *     <li><strong>a (ActorContent):</strong> The ActorContent to be compared with.</li>
         * </ul>
         * <p><strong>Return:</strong> int - The result of the comparison.</p>
         */
        public int compareTo (ActorContent a){
            return this.getY() - a.getY();
        }
    }
}
