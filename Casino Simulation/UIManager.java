import greenfoot.*;

/**
 * Write a description of class HorizontalBar here.
 * 
 * @author Richard
 * @version 11/20
 */
public class UIManager extends Actor
{
    private static int casinoTarget = 0, gamblerLoss = 0, casinoProfit = 0, gamblerWins = 0, gamblerLosses = 0;
    private static Text[] textBoxes = new Text[4];
    private boolean isNew=false;
    public UIManager(int casinoTarget){
        setImage("ui.png");
        this.casinoTarget=casinoTarget;
        casinoProfit=0;
    }
    public void addedToWorld(World w){
        if(!isNew){
            isNew=true;
            getWorld().addObject(new Text(25, "Segoe UI", "0", 0), 420, 42);
            getWorld().addObject(new Text(25, "Segoe UI", "0", 1), 875, 42);
            getWorld().addObject(new Text(25, "Segoe UI", "0", 2), 420, 92);
            getWorld().addObject(new Text(25, "Segoe UI", "0", 3), 875, 92);
            updateText();
        }
    }
    private static void updateText()
    {
        textBoxes[0].changeText(casinoTarget + "");
        textBoxes[1].changeText(gamblerLoss + "");
        textBoxes[2].changeText(casinoProfit + "");
        textBoxes[3].changeText(gamblerWins + "/" + gamblerLosses);
    }
    public static int getCasinoProfit(){
        return casinoProfit;
    }
    public static int getCasinoTarget(){
        return casinoProfit;
    }
    public static void attachText(Text text, int type)
    {
        textBoxes[type] = text;
    }
    public static void incrementGamblerLoss(int num)
    {
        gamblerLoss += num;
        updateText();
    }
    public static void incrementCasinoProfit(int num)
    {
        casinoProfit += num;
        updateText();
    }
    public static void incrementGamblerWL(boolean win)
    {
        if(win) gamblerWins++;
        else gamblerLosses++;
        updateText();
    }
}