import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HorizontalBar here.
 * 
 * @author Richard, Dorsa
 * @version 11/20
 */
public class HorizontalBar extends Actor
{
    public static int casinoProfit;
    /**
     * Act - do whatever the HorizontalBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public HorizontalBar(){
        this.setImage("ui.png");
        casinoProfit = 0;
    }
    public void act(){
        showText(casinoProfit + "", Color.RED, 405, 35);
    }
    
    private void showText(String text, Color color, int x, int y){
        Text texts = new Text(text, color);
        getWorld().addObject(texts, x, y);
    }
}
