import greenfoot.*;
/**
 * Gambler superclass
 * @Jimmy Zhu
 * @1118
 */
public class Gambler extends Actor {
    private int speed = Greenfoot.getRandomNumber(3)+3,tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)),fx=0, ty=0, yToSpot=0,skill=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50),luck=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50);
    private boolean playing = false, flag = false, toSpot = false, isNew=false;
    protected int money;
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            if(!SpotManager.attemptTarget(this))getWorld().removeObject(this);
        }
    }
    public void playMoneyEffect(int money) {
        this.money+=money;
        getWorld().addObject(new MoneyEffect((Integer.signum(money)==-1?"-$":"+$")+Math.abs(money),(Integer.signum(money)==-1?Color.RED:Color.GREEN)), getX(),getY()-30);
    }
    public void act() {
        if (!playing) {
            if(Math.abs(tx-getX())>2)setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            else if (Math.abs(ty-getY())>5)setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            else if (tx!=fx)tx=fx;
            else if (tx==1250||tx==-50)getWorld().removeObject(this);
            else if(!flag) {
                if(!toSpot)ty+=yToSpot;
                else ty-=yToSpot;
                flag=true;
            } 
            else if(!toSpot) {
                playing=true;
                toSpot=true;
                flag=false;
            } 
            else if(SpotManager.attemptTarget(this)) {
                toSpot=false;
                flag=false;
            } 
            else{
                tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
                ty=700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
                fx=(Greenfoot.getRandomNumber(2)==0?1250:-50);
            }
        }
    }
    public void target(int x, int y, int compensate){
        if(Math.abs(ty+yToSpot-y)>50)tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));;
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
}
