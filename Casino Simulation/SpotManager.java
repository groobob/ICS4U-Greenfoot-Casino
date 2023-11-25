import greenfoot.*;
import java.util.*;
/**
 * Class that manages spots.
 * <p>
 * Guide gamblers to spots.
 * Allows easy creation of spots.
 * Keeps track of games and therefore their spots.
 * @author Jimmy Zhu
 * @version 11/20
 */
public class SpotManager  
{
    private static final int numberOfGames = 14;
    private static Game[] games = new Game[numberOfGames];
    private static int i=0;
    /**
     *Resets index. Needed because it's static so it doesn't automatically reset when simulation is reset.
     *
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
        games[i++] = g;
    }
    /**
     *Targets random empty spot
     */
    public static boolean attemptTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return false;//leave when no money or 20% chance
        Spot targetSpot=null;
        int targetGameIndex=-1, targetSpotIndex=-1;
        for (int i = 0; i < numberOfGames; i++) {
            //if there game[i] is null then continue to avoid nullpointer
            if (games[i] == null) continue;
            Spot curSpot=null;
            Spot[] currentGameSpots=games[i].getSpots();
            Gambler[] currentGameGamblers=games[i].getGamblers();
            int len = currentGameGamblers.length, curSpotIndex=-1;
            //see if the seat being looked at is empty(currentGameGamblers[j]==null). If it is see if gambler is already eyeing a spot:
            //if yes then there is 50% of eyeing the current spot over the previously eyed spot
            //if no then the gambler will eye the current spot
            for(int j = 0; j<len; j++)if(currentGameGamblers[j]==null&&(curSpot==null||(curSpot!=null&&Greenfoot.getRandomNumber(2)==0))){
                curSpot=currentGameSpots[j];
                curSpotIndex=j;
            }
            //if no spot is found in this particular game(game[i]) or just played in this exact spot then continue trying to find avaliable spot. 
            if(curSpot==null||(curSpot.getHorizontal()==gb.getTargetX()&&curSpot.getVertical()-curSpot.getCompensate()==gb.getTargetY()))continue;
            //if the gambler is not thinking of targetting any game then think about targetting the spot that gambler is eyeing right now
            //otherwise there is a 50% that the gambler chooses to thinking about targetting the spot that gambler is eyeing right now over the spot the gambler was thinking about targetting before
            if(targetSpot==null||(targetSpot!=null&&Greenfoot.getRandomNumber(2)==0)){
                targetGameIndex=i;
                targetSpotIndex=curSpotIndex;
                targetSpot=curSpot;
            }
        }
        //if the gambler is not thinking about targetting anything then returns false which makes gambler leave
        //otherwise the gambler gets the values that will guide him/her to the spot being targetted and immediately start traveling there.
        if(targetSpot==null)return false;
        else{
            gb.target(targetSpot.getHorizontal(),targetSpot.getVertical(),targetSpot.getCompensate());
            games[targetGameIndex].placeGambler(gb,targetSpotIndex);
            return true;
        }
    }
}
