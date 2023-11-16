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
    private int speed = Greenfoot.getRandomNumber(3) + 3, tx = entranceX, fx, ty, yToStation;
    private static boolean stopped = false;
    private boolean flag = false, toStation = false;

    public static int money;
    private static int score = 0;
    private int test;
    private static int testValue;
    private static int effectCooldown = 0;
    private static final int effectDelay = 30;
    private int gameID,stationID;
    private boolean clapZSort=false;
    public Gambler() {
        money = 0;//money 0 temp becuz gambler leave when no money and that nice for testing
    }
    public void addedToWorld(World w){
        if(!clapZSort){//prevent z sort problems
            clapZSort=true;
            if(!attemptFind())getWorld().removeObject(this);
        }
    }
    private boolean attemptFind(){
        boolean found=false;
        int i = 0;
        for(Game ga : CasinoWorld.gs){
            CasinoWorld.posWithID curP=ga.station(this);
            if(curP!=null){
                fx = curP.p.x;
                ty = curP.p.y-curP.p.compensate;
                yToStation = curP.p.compensate;
                found=true;
                gameID=i;
                stationID=curP.ID;
                break;
            }
            i++;
        }
        return found;
    }
    public static void playMoneyEffect(Gambler gambler, boolean won, int moneyAmount) {
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
    private static void gainMoney(Gambler gambler, int amount) {
        score += amount;
        showMoneyEffect(gambler, "+ $" + amount, Color.GREEN);
    }

    private static void loseMoney(Gambler gambler, int amount) {
        score -= amount;
        showMoneyEffect(gambler, "- $" + amount, Color.RED);
    }
    private static void showMoneyEffect(Gambler gambler, String text, Color color) {
        if (gambler != null && gambler.getWorld() != null) {
            MoneyEffect effect = new MoneyEffect(text, color);
            gambler.getWorld().addObject(effect, gambler.getX(), gambler.getY() - 30);
        }
    }
    public void act() {
        if (!stopped) {
            if(Math.abs(tx - getX()) > 5)setLocation(getX() + speed * Integer.signum(tx - getX()), getY());
            else if (Math.abs(ty - getY()) > 5)setLocation(getX(), getY() + speed * Integer.signum(ty - getY()));
            else if (tx != fx)tx = fx;
            else if (tx == 1250 || tx == -50)getWorld().removeObject(this);
            else if(!flag) {
                if (!toStation)ty += yToStation;
                else ty -= yToStation;
                flag = true;
            } 
            else if (!toStation) {
                stopped = true;
                toStation = true;
                flag = false;
                CasinoWorld.gs.get(gameID).playing(stationID);
                //unstop(); //temp
            } 
            else {
                if (money==0||Greenfoot.getRandomNumber(5)==0||!attemptFind()) {//dip when no money or 20% chance or no more seats
                    tx = entranceX;
                    ty = sidewalkY;
                    fx = outMapX;
                } 
                else {
                    toStation = false;
                    flag = false;
                }
            }
        }
    }
    private boolean reachedSlotMachine() {
        if (getWorld() == null) {
            return false; 
        }
        return Math.abs(getX() - fx) < 10 && Math.abs(getY() - ty) < 10;
    }
    
    private SlotMachines getIntersectingSlotMachine() {
        if(getWorld() == null){
            return null; // return null if gambler was removed
        }
        java.util.List<SlotMachines> intersectingSlots = getIntersectingObjects(SlotMachines.class);
        if (!intersectingSlots.isEmpty()) {
            return intersectingSlots.get(0); // returns first intersecting slot machine
        }
        return null;
    }
    

    public static void unstop() { //temp
        stopped = false;
    }
}
