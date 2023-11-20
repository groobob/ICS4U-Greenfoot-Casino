import greenfoot.*;
import java.util.*;
/**
 * Class that manages sound.
 * Recommended to add sounds in world constructor.
 * @author Jimmy Zhu
 * @version 1120
 * 
 */
public class SeatManager  
{
    private static final int numberOfGames=5;//increase when more games are made
    private static Game[] games = new Game[numberOfGames];
    private static int i=0;
    /**
     *Resets index
     */
    public static void resetIndex(){
        i=0;
    }
    public static class Seat{
        private int x,y,compensate;
        public Seat(int x, int y, int compensate){
            this.x=x;
            this.y=y;
            this.compensate=compensate;
        }
        public int getHorizontal(){
            return x;
        }
        public int getVertical(){
            return y;
        }
        public int getCompensate(){
            return compensate;
        }
    }
    /**
     *Add game
     */
    public static void addGame(Game g){
        games[i++]=g;
    }
    /**
     *Targets random empty seat
     */
    public static boolean attemptTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return false;//dip when no money or 20% chance
        Seat targetSeat=null;
        int targetGameIndex=-1, targetSeatIndex=-1;
        for(int i = 0; i<numberOfGames; i++){
            Seat curSeat=null;
            Seat[] currentGameSeats=games[i].getSeats();
            Gambler[] currentGameGamblers=games[i].getGamblers();
            int len = currentGameGamblers.length, curSeatIndex=-1;
            for(int j = 0; j<len; j++)if(currentGameGamblers[j]==null&&(curSeat==null||(curSeat!=null&&Greenfoot.getRandomNumber(2)==0))){
                curSeat=currentGameSeats[j];
                curSeatIndex=j;
            }
            if(curSeat==null||(curSeat.getHorizontal()==gb.getTargetX()&&curSeat.getVertical()-curSeat.getCompensate()==gb.getTargetY()))continue;
            if(targetSeat==null||(targetSeat!=null&&Greenfoot.getRandomNumber(2)==0)){
                targetGameIndex=i;
                targetSeatIndex=curSeatIndex;
                targetSeat=curSeat;
            }
        }
        if(targetSeat==null)return false;
        else{
            gb.target(targetSeat.getHorizontal(),targetSeat.getVertical(),targetSeat.getCompensate());
            games[targetGameIndex].placeGambler(gb,targetSeatIndex);
            return true;
        }
    }
}
