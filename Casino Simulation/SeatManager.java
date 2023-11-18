import greenfoot.*;
import java.util.*;
public class SeatManager  
{
    private static final int numberOfGames=5;//increase when more games are made
    private static Game[] games = new Game[numberOfGames];
    private static int i=0;
    public static void resetIndex(){
        i=0;
    }
    public static class Seat{
        private int i,x,y,compensate;
        public Seat(int i, int x, int y, int compensate){
            this.i=i;
            this.x=x;
            this.y=y;
            this.compensate=compensate;
        }
        public int getIndex(){
            return i;
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
    public static void addGame(Game g){
        games[i++]=g;
    }
    public static boolean attemptTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return false;//dip when no money or 20% chance
        Seat targetSeat=null;
        int targetGameIndex=-1;
        for(int i = 0; i<numberOfGames; i++){
            Seat curS=games[i].station();
            if(curS==null||(curS.getHorizontal()==gb.getTargetX()&&curS.getVertical()-curS.getCompensate()==gb.getTargetY()))continue;
            if(targetSeat==null||(targetSeat!=null&&Greenfoot.getRandomNumber(2)==0)){
                targetGameIndex=i;
                targetSeat=curS;
            }
        }
        if(targetSeat==null)return false;
        else{
            gb.target(targetSeat.getHorizontal(),targetSeat.getVertical(),targetSeat.getCompensate());
            games[targetGameIndex].placeGambler(gb,targetSeat.getIndex());
            return true;
        }
    }
}
