import greenfoot.*;
import java.util.*;
/**
 * Class that manages sound.
 * Recommended to add sounds in world constructor.
 * @author Jimmy Zhu
 * @version 1120
 */
public class SpotManager  
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
    public static class Spot{
        private int x,y,compensate;
        public Spot(int x, int y, int compensate){
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
     *Targets random empty spot
     */
    public static boolean attemptTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return false;//dip when no money or 20% chance
        Spot targetSpot=null;
        int targetGameIndex=-1, targetSpotIndex=-1;
        for(int i = 0; i<numberOfGames; i++){
            Spot curSpot=null;
            Spot[] currentGameSpots=games[i].getSpots();
            Gambler[] currentGameGamblers=games[i].getGamblers();
            int len = currentGameGamblers.length, curSpotIndex=-1;
            for(int j = 0; j<len; j++)if(currentGameGamblers[j]==null&&(curSpot==null||(curSpot!=null&&Greenfoot.getRandomNumber(2)==0))){
                curSpot=currentGameSpots[j];
                curSpotIndex=j;
            }
            if(curSpot==null||(curSpot.getHorizontal()==gb.getTargetX()&&curSpot.getVertical()-curSpot.getCompensate()==gb.getTargetY()))continue;
            if(targetSpot==null||(targetSpot!=null&&Greenfoot.getRandomNumber(2)==0)){
                targetGameIndex=i;
                targetSpotIndex=curSpotIndex;
                targetSpot=curSpot;
            }
        }
        if(targetSpot==null)return false;
        else{
            gb.target(targetSpot.getHorizontal(),targetSpot.getVertical(),targetSpot.getCompensate());
            games[targetGameIndex].placeGambler(gb,targetSpotIndex);
            return true;
        }
    }
}
