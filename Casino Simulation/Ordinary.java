import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ordinary extends Gambler {
    private int score = 0;
    private int test;
    private int testValue;
    private int effectCooldown = 0; // Ttimer for delay between effects
    private final int effectDelay = 50; // delay time in frames

    public Ordinary(int fx, int ty, int yToStation){
        super(fx, ty, yToStation);
    }

    public void act() {
        playEffect();
    }

    private void playEffect(){
        test = Greenfoot.getRandomNumber(2); // generate a random number 0 or 1
        testValue = Greenfoot.getRandomNumber(100); // gnerate a random number 0 or 1

        if (effectCooldown > 0) {
            effectCooldown--; // decrease the cooldown timer
        }

        if (effectCooldown <= 0) {
            if (test == 0) {
                gainMoney(testValue);
                effectCooldown = effectDelay; 
            } else if (test == 1) {
                loseMoney(testValue);
                effectCooldown = effectDelay; 
            }
        }
    }
    
    private void gainMoney(int amount) {
        score += amount;
        showMoneyEffect("+ $" + amount, Color.GREEN);
    }

    private void loseMoney(int amount) {
        score -= amount;
        showMoneyEffect("- $" + amount, Color.RED);
    }

    private void showMoneyEffect(String text, Color color) {
        MoneyEffect effect = new MoneyEffect(text, color);
        getWorld().addObject(effect, getX(), getY() - 30);
    }
}
