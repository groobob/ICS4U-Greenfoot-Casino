import greenfoot.*;// imports Actor, World, Greenfoot, GreenfootImage
import java.util.*;
/**
 * BIG COMMENT HERE
 * 
 * @author Richard Zhang, Jimmy Zhu, David Guo, Dorsa Rohani
 * @version 1.1 11/26/2023
 */
public class CasinoWorld extends World
{
    private int delay=1,placed=1; 
    private GreenfootSound casinoJazz;
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        System.out.println("---");
        // Music
        casinoJazz = new GreenfootSound("CasinoJazz.mp3");
        casinoJazz.setVolume(50);
        casinoJazz.playLoop();
        // Other
        Greenfoot.setSpeed(50);
        SpotManager.reset();
        ImageManager.addImages("slotsidle");
        ImageManager.addImages("slots",22);
        ImageManager.addImages("gambler",14,4,9);
        ImageManager.addImages("betting",12);
        ImageManager.addImages("roulette",12);
        addObject(new Entrance(),600,600);
        addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(740,285,-80), new SpotManager.Spot(798,280,-80), new SpotManager.Spot(877,275,-80), new SpotManager.Spot(758+2*48,255,-80), new SpotManager.Spot(710+48,250,-80), new SpotManager.Spot(710,245,-80), new SpotManager.Spot(678,265,-80)}),780,196);
        addObject(new UIManager(SettingsWorld.getCasinoTarget()),600,60);
        setPaintOrder(Message.class,Text.class);
    }
    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(120)+60;
            int random = Greenfoot.getRandomNumber(20);
            if(random>17)addObject(new VIP(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            else if(random>15)addObject(new Insane(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            else if(random>11)addObject(new Cheater(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
            else addObject(new Ordinary(),(Greenfoot.getRandomNumber(2)==0?1250:-50),690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)));
        }
        if(placed!=14&&UIManager.getCasinoProfit()/2>UIManager.getCasinoTarget()*0.05){
            placed++;
            UIManager.incrementCasinoProfit((int)(UIManager.getCasinoTarget()*-0.05));
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
                case 12:addObject(new Roulette(new SpotManager.Spot[]{new SpotManager.Spot(1150,290,-80), new SpotManager.Spot(1115,320,-45), new SpotManager.Spot(1070,335,-30), new SpotManager.Spot(1015,330,-40), new SpotManager.Spot(975,300,-60), new SpotManager.Spot(970,270,-120)}),1060,270);break;
                case 13:addObject(new Poker(new SpotManager.Spot[]{new SpotManager.Spot(920,525,0), new SpotManager.Spot(940,440,55), new SpotManager.Spot(1000,435,50), new SpotManager.Spot(1050,435,50), new SpotManager.Spot(1130,460,75)}),1020,500);break;
                case 14:addObject(new Blackjack(new SpotManager.Spot[]{new SpotManager.Spot(80,445,70), new SpotManager.Spot(130,445,70), new SpotManager.Spot(180,445,70), new SpotManager.Spot(230,445,70), new SpotManager.Spot(270,480,0), new SpotManager.Spot(270,520,0)}),155,500);break;
            }
        }
        zSort((ArrayList<Actor>)(getObjects(Actor.class)), this);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Roulette(new SpotManager.Spot[]{new SpotManager.Spot(1150,290,-80), new SpotManager.Spot(1115,320,-45), new SpotManager.Spot(1070,335,-30), new SpotManager.Spot(1015,330,-40), new SpotManager.Spot(975,300,-60), new SpotManager.Spot(970,270,-120)}),1060,270);
        addObject(new Poker(new SpotManager.Spot[]{new SpotManager.Spot(920,525,0), new SpotManager.Spot(940,440,55), new SpotManager.Spot(1000,435,50), new SpotManager.Spot(1050,435,50), new SpotManager.Spot(1130,460,75)}),1020,500);
        addObject(new Blackjack(new SpotManager.Spot[]{new SpotManager.Spot(80,445,70), new SpotManager.Spot(130,445,70), new SpotManager.Spot(180,445,70), new SpotManager.Spot(230,445,70), new SpotManager.Spot(270,480,0), new SpotManager.Spot(270,520,0)}),155,500);
        //addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(740,285,-80), new SpotManager.Spot(798,280,-80), new SpotManager.Spot(877,275,-80), new SpotManager.Spot(758+2*48,255,-80), new SpotManager.Spot(710+48,250,-80), new SpotManager.Spot(710,245,-80), new SpotManager.Spot(678,265,-80)}),780,196);
        //addObject(new UIManager(123456),600,60);
    }
    // Play song when the game starts
    public void started() {
        casinoJazz.playLoop();
    }
    // Pause song if they stop the program
    public void stopped() {
        casinoJazz.pause();
    }
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
    static class ActorContent implements Comparable <ActorContent> {
        private Actor actor;
        private int xx, yy;
        public ActorContent(Actor actor, int xx, int yy){
            this.actor = actor;
            this.xx = xx;
            this.yy = yy;
        }
    
        public void setLocation (int x, int y){
            xx = x;
            yy = y;
        }
    
        public int getX() {
            return xx;
        }
    
        public int getY() {
            return yy;
        }
    
        public Actor getActor(){
            return actor;
        }
    
        public String toString () {
            return "Actor: " + actor + " at " + xx + ", " + yy;
        }
        public int compareTo (ActorContent a){
            return this.getY() - a.getY();
        }
    }
}
