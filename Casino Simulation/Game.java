import greenfoot.*;
public abstract class Game extends Actor
{
    protected Gambler[] gamblers;
    private SpotManager.Spot[] spots;
    private int len;
    private boolean isNew=false;
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        len=spots.length;
        gamblers=new Gambler[len];
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            SpotManager.addGame(this);
        }
    }
    // All classes need a way for gamblers to leave the game
    protected void endGamblerSession(int gamblerIndex){
        gamblers[gamblerIndex].stopPlaying();
        gamblers[gamblerIndex] = null;
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
