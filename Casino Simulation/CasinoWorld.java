import greenfoot.*;// imports Actor, World, Greenfoot, GreenfootImage
import java.util.*;
/**
 * 
 */
public class CasinoWorld extends World
{
    int delay=1;//temp
    public class pos{//temp
        int x,y,compensate;
        pos(int x, int y, int compensate){
            this.x=x;this.y=y;this.compensate=compensate;
        }
    }
    
    public class slots{
        SlotMachines slot;
        int x,y,compensate;
        slots(int x, int y, int compensate) {
            this.slot = new SlotMachines();
            addObject(slot, x, y);
            this.x = x;
            this.y = y;
            this.compensate = compensate;   
            
            playSlots();
        }
        
        public void playSlots() {
            slot.spinReels();
        }
    }

    public static List<slots> games = new ArrayList<slots>();    
    
    public static List<pos> tempPlaces = new ArrayList<pos>();//temp
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        System.out.println("___________________________");
        Greenfoot.setSpeed(50);
        
        // GAMES
        //slots__________________________________
        games.add(new slots(56,209,-20));
        games.add(new slots(142,210,-20));
        games.add(new slots(224,211,-20));
        games.add(new slots(308,213,-20));
        games.add(new slots(391,213,-20));
        
        // POSITION COORDINATES
        //slots___________________________________
        //tempPlaces.add(new pos(58,244,-20));
        //tempPlaces.add(new pos(141,245,-20));
        //tempPlaces.add(new pos(224,244,-20));
        //tempPlaces.add(new pos(307,241,-20));
        //tempPlaces.add(new pos(386,242,-20));
        tempPlaces.add(new pos(57,354,-20));
        tempPlaces.add(new pos(140,353,-20));
        tempPlaces.add(new pos(224,349,-20));
        tempPlaces.add(new pos(304,349,-20));
        tempPlaces.add(new pos(387,347,-20));
        //____________________________________________
        //thing betting
        tempPlaces.add(new pos(596,312,-20));
        tempPlaces.add(new pos(596,312,-20));
        tempPlaces.add(new pos(647,311,-20));
        tempPlaces.add(new pos(647,311,-20));
        tempPlaces.add(new pos(687,312,-20));
        tempPlaces.add(new pos(724,307,-20));
        tempPlaces.add(new pos(725,307,-20));
        tempPlaces.add(new pos(763,309,-20));
        tempPlaces.add(new pos(763,309,-20));
        tempPlaces.add(new pos(809,309,-20));
        tempPlaces.add(new pos(809,309,-20));
        tempPlaces.add(new pos(855,313,-20));
        tempPlaces.add(new pos(855,313,-20));
        tempPlaces.add(new pos(896,311,-20));
        tempPlaces.add(new pos(896,311,-20));
        //____________________________________________
        //yes
        //____________________________________________
        addObject(new Entrance(),600,600);
        for(pos p: tempPlaces)addObject(new Thing(),p.x,p.y);
        prepare();
    }

    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            slots p = games.get(Greenfoot.getRandomNumber(games.size()));
            addObject(new Gambler(p.x,p.y-p.compensate,p.compensate),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        
        }
    }
    
    public void temp(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            addObject(new Gambler(p.x,p.y-p.compensate,p.compensate),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        SlotMachines slotMachines = new SlotMachines();
        //addObject(slotMachines,56,209);
        SlotMachines slotMachines2 = new SlotMachines();
        //addObject(slotMachines2,142,210);
        SlotMachines slotMachines3 = new SlotMachines();
        //addObject(slotMachines3,224,211);
        SlotMachines slotMachines4 = new SlotMachines();
        //addObject(slotMachines4,308,213);
        SlotMachines slotMachines5 = new SlotMachines();
        //addObject(slotMachines5,391,213);
        SlotMachines slotMachines6 = new SlotMachines();
        addObject(slotMachines6,57,313);
        SlotMachines slotMachines7 = new SlotMachines();
        addObject(slotMachines7,142,315);
        SlotMachines slotMachines8 = new SlotMachines();
        addObject(slotMachines8,222,313);
        SlotMachines slotMachines9 = new SlotMachines();
        addObject(slotMachines9,306,312);
        SlotMachines slotMachines10 = new SlotMachines();
        addObject(slotMachines10,392,315);
    }
}
