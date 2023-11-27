import greenfoot.*;
/**
 * Gambler superclass
 * @Jimmy Zhu
 * @1118
 */
public abstract class Gambler extends Actor {
    //ty is target y, tx is target x, fx is final target x. 
    //skill and luck is randomized but in a way it fits the distribution curve(negative quadratic function). 
    protected int speed = Greenfoot.getRandomNumber(3)+3,tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)),fx=0, ty=0, yToSpot=0,animationStep=0,mostRecentDirection=1,money,character,skill=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50),luck=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50);
    protected boolean playing = false, flag = false, toSpot = false, isNew=false, leaving=false, insane=false;
    private SpotManager.DetailedSpot target;
    public abstract int checkBehaviour();
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            //attempts to target, if unable then remove as would do nothing
            target=SpotManager.attemptTarget(this);
            if(target==null)getWorld().removeObject(this);
        }
    }
    public void playMoneyEffect(int money) {
        //if gained is 0 do nothing
        if(money==0)return;
        //money gets incremented
        this.money+=money;
        //casino profit gets incremented
        UIManager.incrementCasinoProfit(-money);
        //spawns pop up.
        getWorld().addObject(new Message((Integer.signum(money)==-1?"-$":"+$")+Math.abs(money),(Integer.signum(money)==-1?Color.RED:Color.GREEN)), getX(),getY()-30);
    }
    public void playDialogue(String text){
        getWorld().addObject(new Message(text,Color.WHITE,200,3),getX(),getY()-30);
    }
    public void playDialogue(String text, int size){
        getWorld().addObject(new Message(text,Color.WHITE,100,3,16),getX(),getY()-30);
    }
    public void act() {
        if(!playing) {
            //5(frame every 5 acts)*9(9 frames)=45
            if(++animationStep==45)animationStep=0;
            if(Math.abs(tx-getX())>2){//if x not at target(wiggle room of 2)
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(tx-getX())==-1?2:4),animationStep/5+1));//animates
                mostRecentDirection=(Integer.signum(tx-getX())==-1?2:4);
                setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            }
            else if (Math.abs(ty-getY())>5){//if y not at target(wiggle room of 5)
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(ty-getY())==-1?1:3),animationStep/5+1));//animates
                mostRecentDirection=(Integer.signum(ty-getY())==-1?1:3);
                setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            }
            else if (tx!=fx)tx=fx;//now target x is final target x. Already when to first target now go to fx.
            else if (tx==1250||tx==-50)getWorld().removeObject(this);//if offscreen remove
            else if(!flag) {
                if(!toSpot)ty+=yToSpot;//if toSpot is positive(already played the game) then ty(target y) is adjusted accordingly and if negative(have not played the game) it is adjusted accordingly
                else ty-=yToSpot;
                flag=true;//to prevent this else if block of code from immediately running again
            } 
            else if(!toSpot) {//at this point gambler is on the game. If toSpot is false it means that this section of code has not been run before, so run it.
                playing=true;
                setImage(ImageManager.getImage("gambler",character,mostRecentDirection,1));
                toSpot=true;
                flag=false;
            } 
            else if(!leaving) {
                target = SpotManager.attemptTarget(this);
                if(target!=null){//has another target
                    toSpot=false;//false so particular section of code can run again
                    flag=false;//false so particular section of code can run again
                }
                else exit();
            } 
            else exit();
        }
        else if(getOneObjectAtOffset(1,1,Insane.class)!=null){//when playing and an insane shows up go somewhere else..
            SpotManager.getGames()[target.getGameIndex()].endGamblerSession(target.getSpotIndex());
            playing=false;
            playDialogue((Greenfoot.getRandomNumber(2)==0?"Get away from me.":"Personal space..."));
        }
    }
    protected void exit(){
        tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        ty=690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        fx=(Greenfoot.getRandomNumber(2)==0?1250:-50);
    }
    public void target(int x, int y, int compensate){
        if(Math.abs(ty+yToSpot-y)>25)tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));//if significant y pos difference then move to middle area
        fx=x;
        ty=y-compensate;
        yToSpot=compensate;
    }
    public int getTargetX(){
        return tx;
    }
    public int getTargetY(){
        return ty;
    }
    public void stopPlaying(){
        playing=false;
    }
    public boolean isPlaying(){
        return playing;
    }
    public int getMoney(){
        return money;
    }
    public int getSkill(){
        return skill;
    }
    public int getLuck(){
        return luck;
    }
    public boolean isInsane(){
        return insane;
    }
}
