import greenfoot.*;
import java.util.*;
public class CasinoWorld extends World
{
    int delay=1;//temp
    public class pos{//temp
        int x,y;
        pos(int x, int y){
            this.x=x;this.y=y;
        }
    }
    List<pos> tempPlaces = new ArrayList<pos>();//temp
    public CasinoWorld()
    {    
        super(1200, 740, 1, false); 
        Greenfoot.setSpeed(50);
        tempPlaces.add(new pos(450,200));
        tempPlaces.add(new pos(350,200));
        tempPlaces.add(new pos(250,200));
        tempPlaces.add(new pos(150,200));
        tempPlaces.add(new pos(50,200));
        tempPlaces.add(new pos(450,400));
        tempPlaces.add(new pos(350,400));
        tempPlaces.add(new pos(250,400));
        tempPlaces.add(new pos(150,400));
        tempPlaces.add(new pos(50,400));
        //tempPlaces.add(new pos(300,200));
        //tempPlaces.add(new pos(300,200));
        //addObject(new DarkTint(),200,400);
        //addObject(new Player(),600,400);
        addObject(new Entrance(),600,600);
        for(pos p: tempPlaces)addObject(new Thing(),p.x,p.y);
        //addObject(new Gambler(300,200),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        //setPaintOrder(Player.class, Light.class);
        //SoundManager.addSound(50,"A","wav");
    }
    public void act(){
        if(--delay==0){
            delay=Greenfoot.getRandomNumber(60)+30;
            pos p = tempPlaces.get(Greenfoot.getRandomNumber(tempPlaces.size()));
            addObject(new Gambler(p.x,p.y+50),(Greenfoot.getRandomNumber(2)==0?1250:-50),700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(40):Greenfoot.getRandomNumber(40)));
        }
    }
}
