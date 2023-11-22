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
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(58,244,-20)}),58,244);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(141,245,-20)}),141,245);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(224,244,-20)}),224,244);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(57,354,-20)}),57,354);
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(140,353,-20)}),140,353);
        
        //Spawn Blackjack, Poker, and Roulette games along with their repsective seats
        addObject(new Blackjack(new SpotManager.Spot[]{new SpotManager.Spot(81,445,20), new SpotManager.Spot(127,445,20), new SpotManager.Spot(181,445,20), new SpotManager.Spot(252,445,20), new SpotManager.Spot(305,461,0), new SpotManager.Spot(305,526,0)}),175,500);
        addObject(new Poker(new SpotManager.Spot[]{new SpotManager.Spot(900,517,0), new SpotManager.Spot(900,472,0), new SpotManager.Spot(938,435,0), new SpotManager.Spot(1000,435,0), new SpotManager.Spot(1040,435,0), new SpotManager.Spot(1090,435,0)}),1020,500);
        addObject(new Roulette(new SpotManager.Spot[]{new SpotManager.Spot(1152,241,-100), new SpotManager.Spot(1014,330,-50), new SpotManager.Spot(1148,290,-20), new SpotManager.Spot(1116,320,-20), new SpotManager.Spot(1068,335,-20), new SpotManager.Spot(976,302,0)}),1060,270);
    }
}
