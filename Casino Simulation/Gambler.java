import greenfoot.*;
/**
 * The gambler
 * 
 * @author Jimmy Zhu
 * @version 1118
 */
public class Gambler extends Actor {
    private final int varyRange = 40;
    private final int entranceX = 600 + (Greenfoot.getRandomNumber(2) == 0 ? -Greenfoot.getRandomNumber(varyRange) : Greenfoot.getRandomNumber(varyRange));
    private final int sidewalkY = 700 + (Greenfoot.getRandomNumber(2) == 0 ? -Greenfoot.getRandomNumber(varyRange) : Greenfoot.getRandomNumber(varyRange));
    private final int outMapX = (Greenfoot.getRandomNumber(2) == 0 ? 1250 : -50);
    private int speed = Greenfoot.getRandomNumber(3) + 3, tx = entranceX, fx=0, ty=0, yToSpot=0;
    private boolean playing = false;
    private boolean flag = false, toSpot = false;
    public int money;
    private int score = 0;
    private int test;
    private int testValue;
    private int effectCooldown = 0;
    private final int effectDelay = 30;
    private boolean isNew=false;
    
    public Gambler() {
        money = 1000;
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            if(!SpotManager.attemptTarget(this))getWorld().removeObject(this);
        }
    }
    public void playMoneyEffect(int money) {
        score+=money;
        money+=money;
        getWorld().addObject(new MoneyEffect((Integer.signum(money)==-1?"-$":"+$")+Math.abs(money),(Integer.signum(money)==-1?Color.RED:Color.GREEN)), getX(),getY()-30);
    }
    public void act() {
        if (!playing) {
            if(Math.abs(tx - getX()) > 5)setLocation(getX() + speed * Integer.signum(tx - getX()), getY());
            else if (Math.abs(ty - getY()) > 5)setLocation(getX(), getY() + speed * Integer.signum(ty - getY()));
            else if (tx != fx)tx = fx;
            else if (tx == 1250 || tx == -50)getWorld().removeObject(this);
            else if(!flag) {
                if (!toSpot)ty += yToSpot;
                else ty -= yToSpot;
                flag = true;
            } 
            else if (!toSpot) {
                playing = true;
                toSpot = true;
                flag = false;
            } 
            else if(SpotManager.attemptTarget(this)) {
                toSpot = false;
                flag = false;
            } 
            else{
                tx = entranceX;
                ty = sidewalkY;
                fx = outMapX;
            }
        }
    }
    public void target(int x, int y, int compensate){
        if(Math.abs(ty+yToSpot-y)>100)tx=entranceX;
        fx = x;
        ty = y-compensate;
        yToSpot = compensate;
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
        int hi = Greenfoot.getRandomNumber(100);
        return hi;
    }
}