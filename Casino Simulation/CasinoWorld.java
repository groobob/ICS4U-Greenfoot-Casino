import greenfoot.*;// imports Actor, World, Greenfoot, GreenfootImage
import java.util.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CasinoWorld extends World
{
    int delay=1;//temp 
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        System.out.println("---");
        Greenfoot.setSpeed(50);
        SpotManager.resetIndex();
        ImageManager.addImages("slotsidle");
        ImageManager.addImages("slots",22);
        ImageManager.addImages("gambler",14,4,9);
        ImageManager.addImages("betting",12);
        ImageManager.addImages("roulette",12);
        addObject(new Entrance(),600,600);
        prepare();
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
        zSort((ArrayList<Actor>)(getObjects(Actor.class)), this);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(80,250,-20)}),80,225);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(160,250,-20)}),160,225);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(240,250,-20)}),240,225);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(320,250,-20)}),320,225);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(400,250,-20)}),400,225);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(80,350,-20)}),80,325);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(160,350,-20)}),160,325);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(240,350,-20)}),240,325);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(320,350,-20)}),320,325);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(400,350,-20)}),400,325);
        addObject(new Roulette(new SpotManager.Spot[]{new SpotManager.Spot(1152,241,-120), new SpotManager.Spot(1148,290,-80), new SpotManager.Spot(1116,320,-45), new SpotManager.Spot(1068,335,-30), new SpotManager.Spot(1014,330,-40), new SpotManager.Spot(976,302,-60)}),1060,270);
        addObject(new Poker(new SpotManager.Spot[]{new SpotManager.Spot(920,525,0), new SpotManager.Spot(940,440,55), new SpotManager.Spot(1000,435,50), new SpotManager.Spot(1050,435,50), new SpotManager.Spot(1130,460,75)}),1020,500);
        addObject(new Blackjack(new SpotManager.Spot[]{new SpotManager.Spot(80,445,70), new SpotManager.Spot(130,445,70), new SpotManager.Spot(180,445,70), new SpotManager.Spot(230,445,70), new SpotManager.Spot(270,480,0), new SpotManager.Spot(270,520,0)}),155,500);
        addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(740,285,-80), new SpotManager.Spot(798,280,-80), new SpotManager.Spot(877,275,-80), new SpotManager.Spot(758+2*48,255,-80), new SpotManager.Spot(710+48,250,-80), new SpotManager.Spot(710,245,-80), new SpotManager.Spot(678,265,-80)}),780,196);
        addObject(new UIManager(123456),600,60);
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
