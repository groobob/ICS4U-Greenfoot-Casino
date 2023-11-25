import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HorizontalBar here.
 * 
 * @author Richard
 * @version 11/20
 */
public class HorizontalBar extends Actor
{
    private static int casinoTarget = 0, gamblerLoss = 0, casinoProfit = 0, gamblerWinRatio = 0;
    private static Text[] textBoxes = new Text[4];
    private boolean isNew=false;
    public void addedToWorld(World w){
        if(!isNew){
            isNew=true;
            getWorld().addObject(new Text(50, 10, 10, "Segoe UI", "0", 0), 200, 100);
            getWorld().addObject(new Text(50, 10, 10, "Segoe UI", "0", 1), 200, 100);
            getWorld().addObject(new Text(50, 10, 10, "Segoe UI", "0", 2), 200, 100);
            getWorld().addObject(new Text(50, 10, 10, "Segoe UI", "0", 3), 200, 100);
            updateText();
        }
    }
    public static void attachText(Text text, int type)
    {
        textBoxes[type] = text;
    }
    private void updateText()
    {
        textBoxes[0].changeText(casinoTarget + "");
        textBoxes[1].changeText(gamblerLoss + "");
        textBoxes[2].changeText(casinoProfit + "");
        textBoxes[3].changeText(gamblerWinRatio + "");
    }
}