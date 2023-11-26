import greenfoot.*;
/*
 * money effect
 * @author: dorsa
 */

public class Message extends Actor {
    private int lifespan = 50, startingLifespan=50, leviatingSpeed=5;; // increased lifespan for a slower fade-out
    private boolean isNew=false;
    private String value;
    public Message(String text, Color color) {
        GreenfootImage img = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(img);
        value=text;
    }
    public Message(String text, Color color, int lifespan, int leviatingSpeed) {
        GreenfootImage img = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(img);
        value=text;
        startingLifespan=lifespan;
        this.lifespan=lifespan;
        this.leviatingSpeed=leviatingSpeed;
    }
    public Message(String text, Color color, int lifespan, int leviatingSpeed, int size) {
        GreenfootImage img = new GreenfootImage(text, size, color, new Color(0, 0, 0, 0));
        setImage(img);
        value=text;
        startingLifespan=lifespan;
        this.lifespan=lifespan;
        this.leviatingSpeed=leviatingSpeed;
    }
    public void act() {
        update();
    }
    private void update() {
        if (lifespan > 0) {
            // move up more slowly
            setLocation(getX(), getY() - (startingLifespan - lifespan) / (startingLifespan/leviatingSpeed));

            // fade out more gradually
            GreenfootImage img = getImage();
            int transparency = 255 * lifespan / startingLifespan; // adjusted for a more gradual fade
            img.setTransparency(transparency);
            lifespan--;
        } 
        else getWorld().removeObject(this);
    }
}