import greenfoot.*;
/**
 * Gambler superclass
 * @Jimmy Zhu
 * @1118
 */
public abstract class Gambler extends Actor {
    protected int speed = Greenfoot.getRandomNumber(3)+3,tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)),fx=0, ty=0, yToSpot=0,animationStep=0,mostRecentDirection=1;
    protected boolean playing = false, flag = false, toSpot = false, isNew=false;
    protected int money,character,skill=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50),luck=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50);
    protected boolean leaving=false, insane=false;
    private SpotManager.DetailedSpot target;
    public abstract int checkBehaviour();
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            target = SpotManager.attemptTarget(this);
            if(target==null)getWorld().removeObject(this);
        }
    }
    public void playMoneyEffect(int money) {
        if(money==0)return;
        this.money+=money;
        UIManager.incrementCasinoProfit(-money);
        getWorld().addObject(new Message((Integer.signum(money)==-1?"-$":"+$")+Math.abs(money),(Integer.signum(money)==-1?Color.RED:Color.GREEN)), getX(),getY()-30);
    }
    public void playDialogue(String text){
        getWorld().addObject(new Message(text,Color.WHITE,100,3),getX(),getY()-30);
    }
    public void playDialogue(String text, int size){
        getWorld().addObject(new Message(text,Color.WHITE,100,3,16),getX(),getY()-30);
    }
    public void act() {
        if (!playing) {
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
                playing=true;
                setImage(ImageManager.getImage("gambler",character,mostRecentDirection,1));
                toSpot=true;
                flag=false;
            } 
            else if(!leaving) {
                target = SpotManager.attemptTarget(this);
                if(target!=null){
                    toSpot=false;
                    flag=false;
                }
                else exit();
            } 
            else exit();
        }
        else if(getOneObjectAtOffset(1,1,Insane.class)!=null){
            SpotManager.getGames()[target.getGameIndex()].endGamblerSession(target.getSpotIndex());
            playing=false;
            playDialogue((Greenfoot.getRandomNumber(2)==0?"Get away from me.":"Personal space..."),25);
        }
    }
    protected void exit(){
        tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        ty=690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        fx=(Greenfoot.getRandomNumber(2)==0?1250:-50);
    }
    public void target(int x, int y, int compensate){
        if(Math.abs(ty+yToSpot-y)>25)tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
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
