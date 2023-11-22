import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HorizontalBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HorizontalBar extends Actor
{
    /**
     * Act - do whatever the HorizontalBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        showText(Game.casinoProfit + "", Color.RED, 1, 2);
    }
    
    private void showText(String text, Color color, int x, int y){
        Text texts = new Text(text, color);
        getWorld().addObject(texts, x, y);
    }
}
