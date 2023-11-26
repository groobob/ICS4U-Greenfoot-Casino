import greenfoot.*;
import java.util.*;
/**
 * <html>
 * <body>
 * <p><strong>SpotManager</strong> is a class designed to manage gaming spots within a casino environment in Greenfoot.</p>
 * <p>It is responsible for tracking and assigning spots to gamblers in various games, maintaining a list of games, and managing spot assignments.</p>
 * 
 * <h3>Usage:</h3>
 * <p>Use SpotManager to handle the allocation of gamblers to games within a casino environment. It is recommended to set up and manage games through this class.</p>
 * 
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>numberOfGames</strong> - The total number of games in the casino, synchronized with <code>CasinoWorld.numGames</code>.</li>
 *     <li><strong>games</strong> - An array of <code>Game</code> objects representing each game in the casino.</li>
 *     <li><strong>i</strong> - An index used for adding games to the <code>games</code> array.</li>
 * </ul>
 * 
 * @author Jimmy Zhu
 * @version 11/20
 */
//star)_WA)FI_W)FI_)AFWI)_WFI)_AWIF)_IWF)AIWF)_IAF 
//     <li><strong>numberOfGames</strong> - The total number of games in the casino, synchronized with <code>CasinoWorld.numGames</code>.</li>
public class SpotManager  
{
    private static final int numberOfGames = 14;
    private static Game[] games = new Game[numberOfGames];
    private static int i=0;
    /**
     * <p><strong>static void resetIndex()</strong> - Resets the index for adding new games to the <code>games</code> array.</p>
     */
    public static void resetIndex(){
        i=0;
    }
    public static Game[] getGames(){
        return games;
    }
    //constructor when oiawhiofawiohafwhiowafhioawfihofwahioafwhioiafhow
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
    public static class DetailedSpot{
        private SpotManager.Spot spot;
        private int gameIndex, spotIndex;
        public DetailedSpot(SpotManager.Spot spot, int gameIndex, int spotIndex){
            this.spot=spot;
            this.gameIndex=gameIndex;
            this.spotIndex=spotIndex;
        }
        public SpotManager.Spot getSpot(){
            return spot;
        }
        public int getGameIndex(){
            return gameIndex;
        }
        public int getSpotIndex(){
            return spotIndex;
        }
    }
    /**
     * <p><strong>static void addGame(Game g)</strong> - Adds a game to the <code>games</code> array. <br>
     * <strong>@param g</strong> - The <code>Game</code> object to be added.</p>
     */
    public static void addGame(Game g){
        games[i++] = g;
    }
    /**
     * <p><strong>static boolean attemptTarget(Gambler gb)</strong> - Attempts to find an available spot for a <code>Gambler</code> and assign it. <br>
     * <strong>@param gb</strong> - The <code>Gambler</code> object for which a spot is being found for. <br>
     * <strong>@return boolean</strong> - Returns <code>true</code> if a spot is found and assigned, otherwise <code>false</code>.</p>
     */
    //^change return
    public static DetailedSpot attemptTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return null;//leave when no money or 20% chance
        Spot targetSpot=null;
        int targetGameIndex=-1, targetSpotIndex=-1;
        for (int i = 0; i < numberOfGames; i++) {
            Spot curSpot=null;
            Gambler[] currentGameGamblers=games[i].getGamblers();
            int len = currentGameGamblers.length, curSpotIndex=-1;
            //see if the seat being looked at is empty(currentGameGamblers[j]==null). If it is see if gambler is already eyeing a spot:
            //if yes then there is 50% of eyeing the current spot over the previously eyed spot
            //if no then the gambler will eye the current spot
            for(int j = 0; j<len; j++)if(currentGameGamblers[j]==null&&!games[i].hasReservation(j)&&(curSpot==null||(curSpot!=null&&Greenfoot.getRandomNumber(2)==0))){
                curSpot=games[i].getSpots()[j];
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
        if(targetSpot==null)return null;
        else{
            gb.target(targetSpot.getHorizontal(),targetSpot.getVertical(),targetSpot.getCompensate());
            games[targetGameIndex].placeGambler(gb,targetSpotIndex);
            return new DetailedSpot(targetSpot,targetGameIndex,targetSpotIndex);
        }
    }
    public static DetailedSpot absoluteTarget(Gambler gb){
        if(gb.getMoney()<=0||Greenfoot.getRandomNumber(5)==0)return null;//leave when no money or 20% chance
        Spot targetSpot=null;
        int targetGameIndex=-1, targetSpotIndex=-1;
        for (int i = 0; i < 10; i++) {
            Spot curSpot=null;
            Gambler[] currentGameGamblers=games[i].getGamblers();
            int len = currentGameGamblers.length, curSpotIndex=-1;
            //see if gambler is already eyeing a spot:
            //if yes then there is 50% of eyeing the current spot over the previously eyed spot
            //if no then the gambler will eye the current spot
            for(int j = 0; j<len; j++)if(!games[i].hasReservation(j)&&(curSpot==null||(curSpot!=null&&Greenfoot.getRandomNumber(2)==0))){
                curSpot=games[i].getSpots()[j];
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
        if(targetSpot==null)return null;
        //gambler starts moving towards targetted spot
        games[targetGameIndex].addReservation(targetSpotIndex);
        gb.target(targetSpot.getHorizontal(),targetSpot.getVertical(),targetSpot.getCompensate());
        return new DetailedSpot(targetSpot,targetGameIndex,targetSpotIndex);
    }
}
