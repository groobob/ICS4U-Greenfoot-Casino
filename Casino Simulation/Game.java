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
    boolean playing[];
    public CasinoWorld.posWithID station(Gambler gt){
        int len=g.length, use=-1;
        for(int i = 0; i<len; i++)if(g[i]==null&&(use==-1||(use!=-1&&Greenfoot.getRandomNumber(2)==0)))use=i;
        if(use!=-1){
            g[use]=gt;
            //System.out.println((g[use]==null)+"aifjowwfaoiafwioj");
        }
        System.out.println(use);
        return (use==-1?null:(new CasinoWorld.posWithID(cwp[use],use)));
    }
    /*
    public boolean openSeats(){
        System.out.println(g[0]==null);
        for(Gambler gt : g)if(gt==null)return true;
        return false;
    }
    */
    public Game(CasinoWorld.pos[] cwp){
        this.cwp=cwp;
        len=cwp.length;
        g=new Gambler[len];
        playing=new boolean[len];
    }
    public void playing(int i){
        playing[i]=true;
    }
    public void act()
    {
        
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
