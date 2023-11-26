import greenfoot.*;
/**
 * VIP gambler. Special privileges.
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
            going=SpotManager.absoluteTarget(this);
            if(going==null)getWorld().removeObject(this);
            else{
                if(!SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex())&&!SpotManager.getGames()[going.getGameIndex()].isSomeonePlaying(going.getSpotIndex())){
                    alreadyPlaced=true;
                    SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                }
            }
        }
    }
    public int checkBehaviour(){
        return 0;//prideful
    }
    public Insane(){
        character=Greenfoot.getRandomNumber(2)+13;
        money=Greenfoot.getRandomNumber(20001)+10000;
        insane=true;
    }
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
