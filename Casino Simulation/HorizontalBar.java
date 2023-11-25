import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HorizontalBar here.
 * 
 * @author Richard
 * @version 11/20
 */
public class HorizontalBar extends Actor
{
    private static int casinoTarget = 0, gamblerLoss = 0, casinoProfit = 0, gamblerWins = 0, gamblerLosses = 0;
    private static Text[] textBoxes = new Text[4];
    private boolean isNew=false;
    public void addedToWorld(World w){
        if(!isNew){
            isNew=true;
            getWorld().addObject(new Text(200, 100, 25, "Segoe UI", "0", 0), 425, 35);
            getWorld().addObject(new Text(200, 100, 25, "Segoe UI", "0", 1), 880, 35);
            getWorld().addObject(new Text(200, 100, 25, "Segoe UI", "0", 2), 425, 85);
            getWorld().addObject(new Text(200, 100, 25, "Segoe UI", "0", 3), 880, 85);
            updateText();
        }
    }
    
    private void updateText()
    {
        textBoxes[0].changeText(casinoTarget + "");
        textBoxes[1].changeText(gamblerLoss + "");
        textBoxes[2].changeText(casinoProfit + "");
        textBoxes[3].changeText(gamblerWins + "/" + gamblerLosses);
    }
    
    public static void attachText(Text text, int type)
    {
        textBoxes[type] = text;
    }
    
    public void setCasinoTarget(int num)
    {
        casinoTarget = num;
        updateText();
    }
    
    public void incrementGamblerLoss(int num)
    {
        gamblerLoss += num;
        updateText();
    }
    
    public void incrementcasinoProfit(int num)
    {
        casinoProfit += num;
        updateText();
    }
    
    public void incrementGamblerWL(boolean win)
    {
        if(win) gamblerWins++;
        else gamblerLosses++;
        updateText();
    }
}