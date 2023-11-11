import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
import java.util.Random;

public class Ordinary extends Gambler {
    private int score = 0;
    private int test;
    private int testValue;
    private int effectCooldown = 0; // Timer for delay between effects
    private final int effectDelay = 50; // Delay time in frames
    private Random random = new Random(); // Create a Random object

    public Ordinary() {
        // 
    }

    public void act() {
        playEffect();
    }

    private void playEffect(){
        test = random.nextInt(2); // Generate a random number 0 or 1
        testValue = random.nextInt(100); // Generate a random number 0 or 1

        if (effectCooldown > 0) {
            effectCooldown--; // Decrease the cooldown timer
        }

        if (effectCooldown <= 0) {
            if (test == 0) {
                gainMoney(testValue);
                effectCooldown = effectDelay; // Reset the cooldown timer
            } else if (test == 1) {
                loseMoney(testValue);
                effectCooldown = effectDelay; // Reset the cooldown timer
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

    // Additional methods and logic for Ordinary class...
}
