import greenfoot.*;
/**
 * Game superclass
 * @author Jimmy Zhu
 * @version 1122
 */
public abstract class Game extends Actor
{
    protected Gambler[] gamblers;
    private SpotManager.Spot[] spots;
    private boolean isNew=false;
    protected int actNumber;
    private int len;
    protected int[] reservedSpots;
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        len=spots.length;
        gamblers=new Gambler[len];
        actNumber = 0;
        gamblers=new Gambler[spots.length];
        reservedSpots = new int[len];
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
    public void placeGambler(Gambler g, int spotNumber){
        gamblers[spotNumber]=g;
    }
    protected void endGamblerSession(int i) {
        if(gamblers[i].isVIP())reservedSpots[i]--;
        gamblers[i].stopPlaying();
        if(reservedSpots[i]==0)gamblers[i] = null;
    }
    public void absolutePlaceGambler(Gambler g, int spotNumber){
        if(gamblers[spotNumber]==null)gamblers[spotNumber]=g;
        else{
            endGamblerSession(spotNumber);
            gamblers[spotNumber]=g;
        }
    }
    public boolean isSpotTaken(int spotNumber){
        return gamblers[spotNumber]!=null&&gamblers[spotNumber].isPlaying();
    }
    public void removeReservation(int spotNumber){
        reservedSpots[spotNumber]--;
    }
    public void addReservation(int spotNumber){
        reservedSpots[spotNumber]++;
    }
    public int getReservationCount(int i){
        return reservedSpots[i];
    }
}
