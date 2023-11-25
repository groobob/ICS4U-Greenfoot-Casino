import greenfoot.*;
/*
 * money effect
 * @author: dorsa
 */

public class MoneyEffect extends Actor {
    private int lifespan = 50; // increased lifespan for a slower fade-out
    private boolean isNew=false;
    private String value;
    public void addedToWorld(World w){
        if(!isNew){
            isNew=true;
            if(value.equals("$0"))getWorld().removeObject(this);
        }
    }
    public MoneyEffect(String text, Color color) {
        GreenfootImage img = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(img);
        value=text;
    }

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
            //System.out.println("asd");
            getWorld().removeObject(this);
            
        }
    }
}