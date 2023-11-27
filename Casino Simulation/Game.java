import greenfoot.*;
/**
 * <html>
 * <body>
 * <h1>Game Class</h1>
 * <p>This abstract class extends <em>Actor</em> and serves as a superclass for all game types in a Greenfoot game. It provides common functionalities and attributes needed by various game subclasses, particularly in managing gamblers and spots within the game.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>gamblers:</strong> An array of Gamblers participating in the game.</li>
 *     <li><strong>spots:</strong> An array of spots available in the game.</li>
 *     <li><strong>isNew:</strong> A flag to check if the game instance is new.</li>
 *     <li><strong>actNumber:</strong> Tracks the number of action steps taken.</li>
 *     <li><strong>reserved:</strong> Indicates if spots are reserved.</li>
 *     <li><strong>t:</strong> A transparency level for the game's image.</li>
 * </ul>
 * 
 * @author Jimmy Zhu, David Guo
 * @version 1.0 11/22/2023
 */
public abstract class Game extends Actor
{
    protected Gambler[] gamblers;
    private SpotManager.Spot[] spots;
    private boolean isNew=false;
    protected int actNumber;
    private int len;
    protected boolean[] reserved;
    protected int t=0;//transparency
    protected final String[] winDialogues = {
        "Woohoo!",
        "Yippee!",
        "Microchance my &%$!",
        "Too Easy! :)",
        "Bringing home the riches!",
        "No way!",
        ":)"
    };
    protected final String[] loseDialogues = {
        "%$#&!",
        "WHAT THE %$&#!",
        "I am very calm.",
        ":(",
        "This can't be real!",
        "My life savings...",
        "Wow! I can't believe that just happened!",
        "bruh"
    };
    
    /**
     * <h2>Constructor:</h2>
     * <p>Initializes a new Game instance with a set of spots and prepares it for adding gamblers.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *      <li><strong>spots (SpotManager.Spot[]):</strong> The spots available in the game.</li>
     * </ul>
     */
    public Game(SpotManager.Spot[] spots){
        this.spots=spots;
        len=spots.length;
        gamblers=new Gambler[len];
        actNumber = 0;
        gamblers=new Gambler[spots.length];
        reserved = new boolean[len];
    }
    /**
     * <h3>void addedToWorld(World w)</h3>
     * <p>Called when the game is added to the world, integrating it into the SpotManager system.</p>
     */
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            SpotManager.addGame(this);
        }
    }
    /**
     * <p>Handles the game's actions each step, managing image transparency and other behaviors.</p>
     */
    public void act(){
        if(t<255){
            t+=3;
            getImage().setTransparency(t);
        }
        else getImage().setTransparency(255);
    }
    /**
     * <h3>Gambler[] getGamblers()</h3>
     * <p>Returns an array of gamblers currently in the game.</p>
     * <p><strong>Return:</strong> Gambler[] - The array of gamblers.</p>
     */
    public Gambler[] getGamblers(){
        return gamblers;
    }
    /**
     * <h3>SpotManager.Spot[] getSpots()</h3>
     * <p>Returns an array of spots available in the game.</p>
     * <p><strong>Return:</strong> SpotManager.Spot[] - The array of spots.</p>
     */
    public SpotManager.Spot[] getSpots(){
        return spots;
    }
    /**
     * <h3>void placeGambler(Gambler g, int spotNumber)</h3>
     * <p>Places a gambler in a specified spot within the game.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>g (Gambler):</strong> The gambler to place.</li>
     *     <li><strong>spotNumber (int):</strong> The spot number where the gambler will be placed.</li>
     * </ul>
     */
    public void placeGambler(Gambler g, int spotNumber){
        gamblers[spotNumber]=g;
    }
    /**
     * <h3>void endGamblerSession(int i)</h3>
     * <p>Ends the session for a gambler in a specific spot.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>i (int):</strong> The spot number of the gambler whose session is to end.</li>
     * </ul>
     */
    protected void endGamblerSession(int i) {
        if(gamblers[i]==null)return;//if no gambler then does nothing
        if(gamblers[i].isInsane())removeReservation(i);//if is insane remove reservation
        gamblers[i].stopPlaying();//stop playing
        gamblers[i] = null;//set current placed info 0
    }
    /**
     * <h3>void absolutePlaceGambler(Gambler g, int spotNumber)</h3>
     * <p>Places a gambler at a specified spot in the game. If the spot is already occupied,
     * it ends the current gambler's session and replaces them with the new gambler.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>g (Gambler):</strong> The Gambler object to be placed.</li>
     *     <li><strong>spotNumber (int):</strong> The index of the spot where the gambler is to be placed.</li>
     * </ul>
     */
    public void absolutePlaceGambler(Gambler g, int spotNumber){
        if(gamblers[spotNumber]==null)gamblers[spotNumber]=g;
        else{
            endGamblerSession(spotNumber);
            gamblers[spotNumber]=g;
        }
    }
    /**
     * <h3>boolean isSpotTaken(int spotNumber)</h3>
     * <p>Checks if a particular spot in the game is currently occupied by a gambler.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>spotNumber (int):</strong> The index of the spot to check.</li>
     * </ul>
     * <p><strong>Returns:</strong></p>
     * <p>True if the spot is occupied, false otherwise.</p>
     */
    public boolean isSpotTaken(int spotNumber){
        return gamblers[spotNumber]!=null;
    }
    /**
     * <h3>boolean isSomeonePlaying(int spotNumber)</h3>
     * <p>Checks if a gambler is currently playing at a specified spot in the game.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>spotNumber (int):</strong> The index of the spot to check.</li>
     * </ul>
     * <p><strong>Returns:</strong></p>
     * <p>True if a gambler is playing at the spot, false otherwise.</p>
     */
    public boolean isSomeonePlaying(int spotNumber){
        if(gamblers[spotNumber]==null)return false;
        return gamblers[spotNumber].isPlaying();
    }
    /**
     * <h3>void removeReservation(int spotNumber)</h3>
     * <p>Removes a reservation for a spot, indicating that it is no longer reserved.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>spotNumber (int):</strong> The index of the spot to remove the reservation from.</li>
     * </ul>
     */
    public void removeReservation(int spotNumber){
        reserved[spotNumber]=false;
    }
    /**
     * <h3>void addReservation(int spotNumber)</h3>
     * <p>Adds a reservation for a spot, indicating that it is reserved for a specific purpose.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>spotNumber (int):</strong> The index of the spot to add the reservation to.</li>
     * </ul>
     */
    public void addReservation(int spotNumber){
        reserved[spotNumber]=true;
    }
    /**
     * <h3>boolean hasReservation(int i)</h3>
     * <p>Checks if a spot in the game has a reservation.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>i (int):</strong> The index of the spot to check.</li>
     * </ul>
     * <p><strong>Returns:</strong></p>
     * <p>True if the spot has a reservation, false otherwise.</p>
     */
    public boolean hasReservation(int i){
        return reserved[i];
    }
}
