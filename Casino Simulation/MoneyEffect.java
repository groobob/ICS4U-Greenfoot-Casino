import greenfoot.*;

public class MoneyEffect extends Actor {
    private int lifespan = 50; // Increased lifespan for a slower fade-out

    public MoneyEffect(String text, Color color) {
        GreenfootImage img = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(img);
    }

    public void act() {
        update();
    }

    private void update() {
        if (lifespan > 0) {
            // Move up more slowly
            setLocation(getX(), getY() - (50 - lifespan) / 10);

            // Fade out more gradually
            GreenfootImage img = getImage();
            int transparency = 255 * lifespan / 50; // Adjusted for a more gradual fade
            img.setTransparency(transparency);
            lifespan--;
        } else {
            // Remove this effect after it's done
            getWorld().removeObject(this);
        }
    }
}
