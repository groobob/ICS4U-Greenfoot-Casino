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
        // GAMES
        //slots__________________________________
        // POSITION COORDINATES
        //slots___________________________________
        //addObject(new SlotMachines(new station[]{new station(58,244,-20,0)}),58,244);
        /*
        stationGroupCoords.add(new pos[]{new pos(58,244,-20)});
        stationGroupCoords.add(new pos[]{new pos(141,245,-20)});
        stationGroupCoords.add(new pos[]{new pos(224,244,-20)});
        stationGroupCoords.add(new pos[]{new pos(307,241,-20)});
        stationGroupCoords.add(new pos[]{new pos(386,242,-20)});
        
        stationGroupCoords.add(new pos[]{new pos(57,354,-20)});
        stationGroupCoords.add(new pos[]{new pos(140,353,-20)});
        stationGroupCoords.add(new pos[]{new pos(224,349,-20)});
        stationGroupCoords.add(new pos[]{new pos(304,349,-20)});
        stationGroupCoords.add(new pos[]{new pos(387,347,-20)});
        */
        //tempPlaces.add(new pos(58,244,-20));
        //tempPlaces.add(new pos(141,245,-20));
        //tempPlaces.add(new pos(224,244,-20));
        //tempPlaces.add(new pos(307,241,-20));
        //tempPlaces.add(new pos(386,242,-20));
        
        //tempPlaces.add(new pos(57,354,-20));
        //tempPlaces.add(new pos(140,353,-20));
        //tempPlaces.add(new pos(224,349,-20));
        //tempPlaces.add(new pos(304,349,-20));
        //tempPlaces.add(new pos(387,347,-20));
        //____________________________________________
        addObject(new Entrance(),600,600);//temp
        prepare();
    }
    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(120)+60;
            //pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            //Game tempGame=null;
            //if(emptyGame())
            addObject(new Gambler(),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        }
    }
    /*
    private boolean emptyGame(){
        for(Game gt : gs)if(gt.openSpots())return true;
        return false;
    }
    */
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
    }
}
