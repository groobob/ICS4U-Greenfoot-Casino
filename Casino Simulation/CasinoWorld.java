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
    public static class pos{//temp
        int x,y,compensate;
        pos(int x, int y, int compensate){
            this.x=x;this.y=y;this.compensate=compensate;
        }
    }
    public static class posWithID{//temp
        pos p; int ID;
        posWithID(pos p, int ID){
            this.p=p;this.ID=ID;
        }
    }
    public static List<Game> gs = new ArrayList<Game>();    
    public static List<pos[]> stationGroupCoords = new ArrayList<pos[]>();
    public static List<pos> tempPlaces = new ArrayList<pos>();//temp
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        System.out.println("___________________________");
        Greenfoot.setSpeed(50);
        stationGroupCoords.clear();
        gs.clear();
        // GAMES
        //slots__________________________________
        // POSITION COORDINATES
        //slots___________________________________
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
        //thing betting
        //tempPlaces.add(new pos(596,312,-20));
        //tempPlaces.add(new pos(596,312,-20));
        //tempPlaces.add(new pos(647,311,-20));
        //tempPlaces.add(new pos(647,311,-20));
        //tempPlaces.add(new pos(687,312,-20));
        //tempPlaces.add(new pos(724,307,-20));
        //tempPlaces.add(new pos(725,307,-20));
        //tempPlaces.add(new pos(763,309,-20));
        //tempPlaces.add(new pos(763,309,-20));
        //tempPlaces.add(new pos(809,309,-20));
        //tempPlaces.add(new pos(809,309,-20));
        //tempPlaces.add(new pos(855,313,-20));
        //tempPlaces.add(new pos(855,313,-20));
        //tempPlaces.add(new pos(896,311,-20));
        //tempPlaces.add(new pos(896,311,-20));
        //____________________________________________
        //yes
        //____________________________________________
        System.out.println(stationGroupCoords);
        addObject(new Entrance(),600,600);
        for(pos[] pa : stationGroupCoords)addObject(new SlotMachines(pa),pa[0].x,pa[0].y);//this supports slots. currently only supporting slots.
        prepare();
    }

    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            //pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            //Game tempGame=null;
            //if(emptyGame())
            addObject(new Gambler(),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        }
    }
    /*
    private boolean emptyGame(){
        for(Game gt : gs)if(gt.openSeats())return true;
        return false;
    }
    */
    public void temp(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            //addObject(new Gambler(p.x,p.y-p.compensate,p.compensate),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    // empty fn
    }
}
