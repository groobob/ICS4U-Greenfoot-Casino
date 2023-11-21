import greenfoot.*;
public class Game extends Actor
{
    protected Gambler[] gamblers;
    private SpotManager.Spot[] spots;
    private int len;
    private boolean isNew=false;
    protected int actNumber;
    protected Gambler[] gamblersPlaying;
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        len=spots.length;
        gamblers=new Gambler[len];
        actNumber = 0;
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
