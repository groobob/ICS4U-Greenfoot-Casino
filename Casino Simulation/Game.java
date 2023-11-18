import greenfoot.*;
public class Game extends Actor
{
    protected Gambler[] gamblers;
    private SeatManager.Seat[] seats;
    private int len;
    private boolean isNew=false;
    public Game(SeatManager.Seat[] seats){
        this.seats=seats;
        len=seats.length;
        gamblers=new Gambler[len];
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            SeatManager.addGame(this);
        }
    }
    public SeatManager.Seat station(){
        int use=-1;
        for(int i = 0; i<len; i++)if(gamblers[i]==null&&(use==-1||(use!=-1&&Greenfoot.getRandomNumber(2)==0)))use=i;
        return (use==-1?null:(seats[use]));
    }
    public void placeGambler(Gambler g, int seatNumber){
        gamblers[seatNumber]=g;
    }
    /*
    public boolean openSeats(){
        System.out.println(g[0]==null);
        for(Gambler gt : g)if(gt==null)return true;
        return false;
    }
    */
    /*
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
    */
}
