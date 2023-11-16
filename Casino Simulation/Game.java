import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends Actor
{
    /**
     * Act - do whatever the Game wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Gambler[] g;
    CasinoWorld.pos[] cwp;
    int len;
    public boolean station(){
        int len=g.length, use=-1;
        for(int i = 0; i<len; i++)if(g[i]==null&&(use==-1||(use!=-1&&Greenfoot.getRandomNumber(2)==0)))use=i;
    }
    public Game(CasinoWorld.pos[] cwp){
        this.cwp=cwp;
        len=cwp.length;
        g=new Gambler[len];
    }
    public void act()
    {
        // Add your action code here.
    }
    
    // stationGambler:
    // array for each time of game seating
    // keep count of number of gamblers in the entire game    
    public void stationGambler(){
        //
    }
    public void increaseMoneyEffect(){
        //
    }
    public void decreaseMoneyEffect(){
        //
    }
}
