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
    protected boolean[] reserved;
    protected int t=0;//transparency
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        len=spots.length;
        gamblers=new Gambler[len];
        actNumber = 0;
        gamblers=new Gambler[spots.length];
        reserved = new boolean[len];
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            SpotManager.addGame(this);
        }
    }
    public void act(){
        if(t<255){
            t+=3;
            getImage().setTransparency(t);
        }
        else getImage().setTransparency(255);
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
        if(gamblers[i]==null)return;//if no gambler then does nothing
        if(gamblers[i].isInsane())removeReservation(i);//if is insane remove reservation
        gamblers[i].stopPlaying();//stop playing
        gamblers[i] = null;//set current placed info 0
    }
    public void absolutePlaceGambler(Gambler g, int spotNumber){
        if(gamblers[spotNumber]==null)gamblers[spotNumber]=g;
        else{
            endGamblerSession(spotNumber);
            gamblers[spotNumber]=g;
        }
    }
    public boolean isSpotTaken(int spotNumber){
        return gamblers[spotNumber]!=null;
    }
    public boolean isSomeonePlaying(int spotNumber){
        if(gamblers[spotNumber]==null)return false;
        return gamblers[spotNumber].isPlaying();
    }
    public void removeReservation(int spotNumber){
        reserved[spotNumber]=false;
    }
    public void addReservation(int spotNumber){
        reserved[spotNumber]=true;
    }
    public boolean hasReservation(int i){
        return reserved[i];
    }
}
