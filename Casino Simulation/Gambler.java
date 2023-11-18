import greenfoot.*; // imports Actor, World, Greenfoot, GreenfootImage
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Gambler extends Actor {
    private final int varyRange = 40;
    private final int entranceX = 600 + (Greenfoot.getRandomNumber(2) == 0 ? -Greenfoot.getRandomNumber(varyRange) : Greenfoot.getRandomNumber(varyRange));
    private final int sidewalkY = 700 + (Greenfoot.getRandomNumber(2) == 0 ? -Greenfoot.getRandomNumber(varyRange) : Greenfoot.getRandomNumber(varyRange));
    private final int outMapX = (Greenfoot.getRandomNumber(2) == 0 ? 1250 : -50);
    private int speed = Greenfoot.getRandomNumber(3) + 3, tx = entranceX, fx=0, ty=0, yToSeat=0;
    private boolean playing = false;
    private boolean flag = false, toSeat = false;

    public int money;
    private int score = 0;
    private int test;
    private int testValue;
    private int effectCooldown = 0;
    private final int effectDelay = 30;
    private boolean isNew=false;
    public Gambler() {
        money = 10;
    }
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            if(!SeatManager.attemptTarget(this))getWorld().removeObject(this);
        }
    }
    /*
    private boolean attemptFind(){
        boolean found=false;
        int i = 0;
        for(Game ga : CasinoWorld.gs){
            CasinoWorld.posWithID curP=ga.station(this);
            if(curP!=null){
                fx = curP.p.x;
                ty = curP.p.y-curP.p.compensate;
                yToSeat = curP.p.compensate;
                found=true;
                gameID=i;
                stationID=curP.ID;
                break;
            }
            i++;
        }
        return found;
    }
    */
    public void playMoneyEffect(Gambler gambler, boolean won, int moneyAmount) {
        if (effectCooldown > 0) {
            effectCooldown--; // decrease the cooldown timer
        }

        if (effectCooldown <= 0) {
            if (won) {
                gainMoney(gambler, moneyAmount);
                effectCooldown = effectDelay;
            } else {
                loseMoney(gambler, moneyAmount);
                effectCooldown = effectDelay;
            }
        }
    }
    private void gainMoney(Gambler gambler, int amount) {
        score += amount;
        showMoneyEffect(gambler, "+ $" + amount, Color.GREEN);
    }

    private void loseMoney(Gambler gambler, int amount) {
        score -= amount;
        showMoneyEffect(gambler, "- $" + amount, Color.RED);
    }
    private void showMoneyEffect(Gambler gambler, String text, Color color) {
        if (gambler != null && gambler.getWorld() != null) {
            MoneyEffect effect = new MoneyEffect(text, color);
            gambler.getWorld().addObject(effect, gambler.getX(), gambler.getY() - 30);
        }
    }
    public void act() {
        if (!playing) {
            if(Math.abs(tx - getX()) > 5)setLocation(getX() + speed * Integer.signum(tx - getX()), getY());
            else if (Math.abs(ty - getY()) > 5)setLocation(getX(), getY() + speed * Integer.signum(ty - getY()));
            else if (tx != fx)tx = fx;
            else if (tx == 1250 || tx == -50)getWorld().removeObject(this);
            else if(!flag) {
                if (!toSeat)ty += yToSeat;
                else ty -= yToSeat;
                flag = true;
            } 
            else if (!toSeat) {
                playing = true;
                toSeat = true;
                flag = false;
            } 
            else if(SeatManager.attemptTarget(this)) {
                toSeat = false;
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
        if(Math.abs(ty+yToSeat-y)>100)tx=entranceX;
        fx = x;
        ty = y-compensate;
        yToSeat = compensate;
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
}
