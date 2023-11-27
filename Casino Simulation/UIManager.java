import greenfoot.*;
/**
 * <h1>UIManager Class</h1>
 * <p>The <em>UIManager</em> class manages the user interface elements and keeps track of casino and gambler statistics in the game.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>casinoTarget (int):</strong> The target profit that the casino aims to achieve.</li>
 *     <li><strong>gamblerLoss (int):</strong> The total losses by gamblers in the casino.</li>
 *     <li><strong>casinoProfit (int):</strong> The total profit earned by the casino.</li>
 *     <li><strong>gamblerWins (int):</strong> The total number of wins by gamblers.</li>
 *     <li><strong>gamblerLosses (int):</strong> The total number of losses by gamblers.</li>
 *     <li><strong>textBoxes (Text[]):</strong> An array of Text objects for displaying statistics.</li>
 * </ul>
 * 
 * @author Richard Zhang, Jimmy Zhu
 * @version 1.1 11/24/2023
 */
public class UIManager extends Actor
{
    private static int casinoTarget = 0, moneySpent = 0, casinoProfit = 0, gamblerWins = 0, gamblerLosses = 0;
    private static Text[] textBoxes = new Text[4];
    private boolean isNew=false;
    /**
     * <h3>UIManager(int casinoTarget)</h3>
     * <p>Creates a UIManager with the specified casino target.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>casinoTarget (int):</strong> The target profit that the casino aims to achieve.</li>
     * </ul>
     */
    public UIManager(int casinoTarget){
        setImage("ui.png");
        this.casinoTarget=casinoTarget;
        casinoProfit=0;
        moneySpent = 0;
        gamblerLosses = 0;
        gamblerWins = 0;
        
    }
    public void act() {
        if(casinoProfit < 0) setImage(ImageManager.getImage("ui1"));
        else if(casinoProfit < casinoTarget / 2) setImage(ImageManager.getImage("ui2"));
        else setImage(ImageManager.getImage("ui3"));
    }
    /**
     * <h3>addedToWorld(World w)</h3>
     * <p>Initializes the UIManager when added to the world and creates Text objects for displaying statistics.</p>
     */
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
    /**
     * <h3>updateText()</h3>
     * <p>Updates the Text objects with the latest statistics.</p>
     */
    private static void updateText()
    {
        textBoxes[0].changeText(casinoTarget + "");
        textBoxes[1].changeText(moneySpent + "");
        textBoxes[2].changeText(casinoProfit + "");
        textBoxes[3].changeText(gamblerWins + "/" + gamblerLosses);
    }
    /**
     * <h3>getCasinoProfit()</h3>
     * <p>Retrieves the total profit earned by the casino.</p>
     * <p><strong>Returns:</strong></p>
     * <ul>
     *     <li><strong>int:</strong> The total profit earned by the casino.</li>
     * </ul>
     */
    public static int getCasinoProfit(){
        return casinoProfit;
    }
    /**
     * <h3>getCasinoTarget()</h3>
     * <p>Retrieves the casino's profit target.</p>
     * <p><strong>Returns:</strong></p>
     * <ul>
     *     <li><strong>int:</strong> The casino's profit target.</li>
     * </ul>
     */
    public static int getCasinoTarget(){
        return casinoTarget;
    }
    /**
     * <h3>attachText(Text text, int type)</h3>
     * <p>Attaches a Text object to the UIManager for displaying specific statistics.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>text (Text):</strong> The Text object to attach.</li>
     *     <li><strong>type (int):</strong> The type of statistics represented by the Text object.</li>
     * </ul>
     */
    public static void attachText(Text text, int type)
    {
        textBoxes[type] = text;
    }
    /**
     * <h3>incrementGamblerLoss(int num)</h3>
     * <p>Increments the total gambler losses and updates the Text objects.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>num (int):</strong> The number to increment the total gambler losses by.</li>
     * </ul>
     */ 
    public static void incrementMoneySpent(int num)
    {
        moneySpent += num;
        updateText();
    }
    /**
     * <h3>incrementCasinoProfit(int num)</h3>
     * <p>Increments the total casino profit and updates the Text objects.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>num (int):</strong> The number to increment the total casino profit by.</li>
     * </ul>
     */
    public static void incrementCasinoProfit(int num)
    {
        casinoProfit += num;
        updateText();
    }
    /**
     * <h3>incrementGamblerWL(boolean win)</h3>
     * <p>Increments the total gambler wins or losses and updates the Text objects based on the win outcome.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>win (boolean):</strong> A boolean flag indicating whether the gambler won (true) or lost (false).</li>
     * </ul>
     */
    public static void incrementGamblerWL(boolean win)
    {
        if(win) gamblerWins++;
        else gamblerLosses++;
        updateText();
    }
}