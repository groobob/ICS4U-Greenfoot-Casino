import greenfoot.*;// imports Actor, World, Greenfoot, GreenfootImage
import java.util.*;
/**
 * Write a description of class Game here.
 * 
 * @author Jimmy Zhu, Dorsa Rohani
 * @version 11/20
 */
public class CasinoWorld extends World
{
    int delay=1;//temp 
    private int progressLevel = 0;
    int casinoTarget = 100;
    public static int numGames = 7; // number of games tracker for spotmanager

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
        
        // simulation progression (games added as casino gets richer)
        if(progressLevel==0 && 10 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(141,245,-20)}),141,245);
            progressLevel++;
        }
        if(progressLevel==1 && 20 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(140,353,-20)}),140,353);
            progressLevel++;
            
            // add horsebetting
            addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(680,312,-20)}),680,312);
            addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(760,312,-20)}),760,312);
            addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(840,312,-20)}),840,312);
            addObject(new HorseBetting(new SpotManager.Spot[]{new SpotManager.Spot(920,312,-20)}),920,312);
        }
        if(progressLevel==2 && 30 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(224,244,-20)}),224,244);
            progressLevel++;
            
            //add blackjack
        }
        if(progressLevel==3 && 40 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(223,353,-20)}),223,353);
            progressLevel++;
            
        }
        if(progressLevel==4 && 50 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(306,245,-20)}),306,245);
            progressLevel++;
            
            // add roulette
        }
        if(progressLevel==5 && 60 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(306,354,-20)}),306,354);
            progressLevel++;
        }
        if(progressLevel==6 && 70 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(399,245,-20)}),399,245); 
            progressLevel++;
            
            // add poker
        }
        if(progressLevel==7 && 80 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(399,354,-20)}),399,354);            
            progressLevel++;
            
        }
        if(progressLevel==8 && 90 == (HorizontalBar.casinoProfit*100)/casinoTarget){
            numGames++;
            addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(399,245,-20)}),399,245);            
            progressLevel++;
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
        addObject(new SlotMachines(new SpotManager.Spot[]{new SpotManager.Spot(57,354,-20)}),57,354);
        
        addObject(new HorizontalBar(),600,60);
    }
    
}
