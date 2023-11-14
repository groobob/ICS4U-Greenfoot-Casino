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
    private boolean stopped = false, flag = false, toStation = false;

    public static int money;
    private static int score = 0;
    private int test;
    private static int testValue;
    private static int effectCooldown = 0;
    private static final int effectDelay = 50;
    
    private boolean isPlayingSlot = false; // Flag to track if the gambler is playing

    public Gambler(int fx, int ty, int yToStation) {
        this.fx = fx;
        this.ty = ty;
        this.yToStation = yToStation;
        money = 100;
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
    
    public void setPlayingSlot(boolean isPlaying) {
        this.isPlayingSlot = isPlaying;
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
        if (!stopped  &&!isPlayingSlot) {
            if (Math.abs(tx - getX()) > 5) {
                setLocation(getX() + speed * Integer.signum(tx - getX()), getY());
            } else if (Math.abs(ty - getY()) > 5) {
                setLocation(getX(), getY() + speed * Integer.signum(ty - getY()));
            } else if (tx != fx) {
                tx = fx;
            } else if (tx == 1250 || tx == -50) {
                getWorld().removeObject(this);
            } else if (!flag) {
                if (!toStation) {
                    ty += yToStation;
                } else {
                    ty -= yToStation;
                }
                flag = true;
            } else if (!toStation) {
                stopped = true;
                toStation = true;
                flag = false;
                unstop(); //temp
            } else {
                if (Greenfoot.getRandomNumber(3) == 0) { //temp for testing 1/3 chance of leaving casino
                    tx = entranceX;
                    ty = sidewalkY;
                    fx = outMapX;
                } else {
                    CasinoWorld.pos p = CasinoWorld.tempPlaces.get(Greenfoot.getRandomNumber(CasinoWorld.tempPlaces.size()));
                    tx = entranceX;
                    ty = p.y - yToStation;
                    fx = p.x;
                    toStation = false;
                    flag = false;
                }
            }
        }
    }

    public void unstop() { //temp
        stopped = false;
    }
}
