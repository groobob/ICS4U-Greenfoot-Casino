import greenfoot.*;
/**
 * VIP gambler. Special privileges.
 * @author Jimmy Zhu
 * @version 11/25
 */
public class VIP extends Gambler
{
    private DetailedSpot going;
    private boolean alreadyPlaced=false;
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            going=SpotManager.absoluteTarget(this);
            if(going==null)getWorld().removeObject(this);
            else{
                SpotManager.getGames()[going.getGameIndex()].addReservation(going.getSpotIndex());
                if(!SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex())){
                    alreadyPlaced=true;
                    SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                }
            }
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
    public int checkBehaviour(){
        return 0;//prideful
    }
    public VIP(){
        character=Greenfoot.getRandomNumber(5)+1;
        money=Greenfoot.getRandomNumber(200001)+100000;
    }
    public void act()
    {
        if(!playing) {
            if(++animationStep==45)animationStep=0;
            if(Math.abs(tx-getX())>2){
                setImage(ImageManager.getImage("ordinary",character,(Integer.signum(tx-getX())==-1?2:4),animationStep/5+1));
                mostRecentDirection=(Integer.signum(tx-getX())==-1?2:4);
                setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            }
            else if (Math.abs(ty-getY())>5){
                setImage(ImageManager.getImage("ordinary",character,(Integer.signum(ty-getY())==-1?1:3),animationStep/5+1));
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
                if(!alreadyPlaced){
                    SpotManager.getGames()[going.getGameIndex()].absolutePlaceGambler(this,going.getSpotIndex());
                    if(SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex()))playDialogue((Greenfoot.getRandomNumber(2)==0?"Get out of the way.":"I'm rich. Got a problem?"));
                }
                playing=true;
                setImage(ImageManager.getImage("ordinary",character,mostRecentDirection,1));
                toSpot=true;
                flag=false;
                alreadyPlaced=false;
            } 
            else if(!leaving) {
                going=SpotManager.absoluteTarget(this);
                if(going!=null){
                    SpotManager.getGames()[going.getGameIndex()].addReservation(going.getSpotIndex());
                    if(!SpotManager.getGames()[going.getGameIndex()].isSpotTaken(going.getSpotIndex())){
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
