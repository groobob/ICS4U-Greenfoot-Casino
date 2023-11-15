import greenfoot.*;
import java.util.*;
public class CasinoWorld extends World
{
    int delay=1;//temp
    public class pos{//temp
        public int x,y,compensate;
        pos(int x, int y, int compensate){
            this.x=x;
            this.y=y;
            this.compensate=compensate;
        }
        /*
        public int getX2(){
            return x;
        }
        public int getY2(){
            return y;
        }
        public int getCompensate(){
            return compensate;
        }
        */
    }
    public static List<pos> tempPlaces = new ArrayList<pos>();//temp
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        setBackground("casinobg.png");
        System.out.println("___________________________");
        Greenfoot.setSpeed(50);
        //slots___________________________________
        tempPlaces.add(new pos(58,244,-20));
        tempPlaces.add(new pos(141,245,-20));
        tempPlaces.add(new pos(224,244,-20));
        tempPlaces.add(new pos(307,241,-20));
        tempPlaces.add(new pos(386,242,-20));
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
    }

    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            addObject(new Gambler(new pos(p.x,p.y,p.compensate)),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        }
    }
}
