import greenfoot.*;
/**
 * <html>
 * <body>
 * <p><strong>MoneyEffect</strong> is an <em>Actor</em> subclass in Greenfoot used for creating visual effects representing monetary changes.</p>
 * <p>It displays text (such as a change in money amount) in a specified color, moving upward and gradually fading out over its lifespan.</p>
 * 
 * <h3>Usage:</h3>
 * <p>Create an instance of MoneyEffect with the desired text and color. The effect will display the text, move it upwards, and fade it out gradually. For example, <code>new MoneyEffect("+100", Color.GREEN)</code> creates an effect showing "+100" in green.</p>
 *
 * <h3>Constructor</h3>
 * <p><strong>MoneyEffect(String text, Color color)</strong></p>
 * <ul>
 *     <li><strong>@param text</strong> - The text to be displayed.</li>
 *     <li><strong>@param color</strong> - The color of the text.</li>
 * </ul>
 * 
 * <p><strong>@author :</strong> Dorsa Rohani<br>
 * <strong>@version :</strong> 1.2<br>
 * <strong>Date:</strong> 11-24-2023</p>
 */
public class MoneyEffect extends Actor {
    private int lifespan = 50; // increased lifespan for a slower fade-out

    public MoneyEffect(String text, Color color) {
        GreenfootImage img = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(img);
    }

    /**
     * <p><strong>void act()</strong></p>
     * <ul>
     *     <li>Calls the update method to handle the effect's animation.</li>
     * </ul>
     * 
     */
    public void act() {
        update();
    }

    private void update() {
        if (lifespan > 0) {
            // move up more slowly
            setLocation(getX(), getY() - (50 - lifespan) / 10);

            // fade out more gradually
            GreenfootImage img = getImage();
            int transparency = 255 * lifespan / 50; // adjusted for a more gradual fade
            img.setTransparency(transparency);
            lifespan--;
        } else {
            getWorld().removeObject(this);
            
        }
    }
}