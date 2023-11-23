import greenfoot.*;
/**
 * Game superclass
 * @author Jimmy Zhu
 * @version 1122
 */
public class Game extends Actor
{
    protected Gambler[] gamblers;
    private SpotManager.Spot[] spots;
    private boolean isNew=false;
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        gamblers=new Gambler[spots.length];
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            SpotManager.addGame(this);
        }
    }
    public Gambler[] getGamblers(){
        return gamblers;
    }
    public SpotManager.Spot[] getSpots(){
        return spots;
    }
    public void placeGambler(Gambler g, int SpotNumber){
        gamblers[SpotNumber]=g;
    }
}
