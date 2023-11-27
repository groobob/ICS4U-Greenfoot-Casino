import greenfoot.*;
/**
 * <html>
 * <body>
 * <h1>Insane Class</h1>
 * <p>This class extends the <em>Gambler</em> class and represents a VIP gambler with special privileges in a Greenfoot game. The Insane class has unique behaviors and interactions within the game world.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>going:</strong> The targeted spot for the Insane gambler.</li>
 *     <li><strong>alreadyPlaced:</strong> A flag indicating whether the gambler has already been placed in a spot.</li>
 * </ul>
 *
 * @author Jimmy Zhu
 * @version 11/25
 */
public class Insane extends Gambler
{
    private SpotManager.DetailedSpot going;
    private boolean alreadyPlaced=false;

    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            //attempts to target, if unable then remove as would do nothing
            going=SpotManager.absoluteTarget(this);
            if(going==null)getWorld().removeObject(this);
            else{
                //see if someone already there if yes let them continue playing until insane(this instance is playing)
                if(!SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex())&&!SpotManager.getGames()[going.getGameIndex()].isSomeonePlaying(going.getSpotIndex())){
                    alreadyPlaced=true;
                    SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                }
            }
        }
    }
    /** 
     * <h3>int checkBehaviour()</h3>
     * <p>Returns an integer representing the behavior of the Insane gambler.</p>
     * <p><strong>Return:</strong> int - Indicates prideful behavior (returns 0).</p>
     */
    public int checkBehaviour(){
        return 0;//prideful
    }
    
    /**
     * <h2>Constructor:</h2>
     * <p>Initializes a new Insane instance with unique characteristics, including a high amount of money.</p>
     */
    public Insane(){
        character=Greenfoot.getRandomNumber(2)+13;
        money=Greenfoot.getRandomNumber(20001)+10000;
        insane=true;
    }
    /**
     *
     * <h3>void act()</h3>
     * <p>Executes the Insane gambler's actions during each step of the game environment. Manages their movement and interaction with the gaming spots.</p>
     */
    public void act()
    {
        if(!playing) {
            if(++animationStep==45)animationStep=0;
            if(Math.abs(tx-getX())>2){
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(tx-getX())==-1?2:4),animationStep/5+1));
                mostRecentDirection=(Integer.signum(tx-getX())==-1?2:4);
                setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            }
            else if (Math.abs(ty-getY())>5){
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(ty-getY())==-1?1:3),animationStep/5+1));
                mostRecentDirection=(Integer.signum(ty-getY())==-1?1:3);
                setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            }
            else if (tx!=fx)tx=fx;
            else if (tx==1250||tx==-50)getWorld().removeObject(this);
            else if(!flag) {
                if(!toSpot)ty+=yToSpot;
                else ty-=yToSpot;
                flag=true;
            } 
            else if(!toSpot) {
                if(!alreadyPlaced)SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                playing=true;
                setImage(ImageManager.getImage("gambler",character,mostRecentDirection,1));
                toSpot=true;
                flag=false;
                alreadyPlaced=false;
            } 
            else if(!leaving) {
                going=SpotManager.absoluteTarget(this);
                if(going!=null){
                    //SpotManager.getGames()[going.getGameIndex()].addReservation(going.getSpotIndex());
                    if(!SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex())&&!SpotManager.getGames()[going.getGameIndex()].isSomeonePlaying(going.getSpotIndex())){
                        alreadyPlaced=true;
                        SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                    }
                    toSpot=false;
                    flag=false;
                }
                else exit();
            } 
            else exit();
        }
    }
}
