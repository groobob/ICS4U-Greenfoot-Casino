import greenfoot.*;
/**
 * <html>
 * <body>
 * <h1>Gambler Class</h1>
 * <p>This abstract class extends <em>Actor</em> and represents a gambler in a casino game in Greenfoot. It includes methods and attributes common to all types of gamblers.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>Various attributes:</strong> Include speed, target coordinates, money, character, skill, luck, and several state flags.</li>
 * </ul>
 * @author: Jimmy Zhu
 * @version: 1118
 */
public abstract class Gambler extends Actor {
    //ty is target y, tx is target x, fx is final target x. 
    //skill and luck is randomized but in a way it fits the distribution curve(negative quadratic function). 
    protected int speed = Greenfoot.getRandomNumber(3)+3,tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20)),fx=0, ty=0, yToSpot=0,animationStep=0,mostRecentDirection=1,money,character,skill=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50),luck=(int)Math.round(Math.pow(((1/13.58)*(Greenfoot.getRandomNumber(100)-49)),3)+50);
    protected boolean playing = false, flag = false, toSpot = false, isNew=false, leaving=false, insane=false;
    private SpotManager.DetailedSpot target;
    public abstract int checkBehaviour();
    
    /**
     * <h3>void addedToWorld(World w)</h3>
     * <p>Called when the gambler is added to the world. Sets initial targeting and checks for a valid target spot.</p>
     */
    public void addedToWorld(World w){
        if(!isNew){//prevent z sort problems
            isNew=true;
            //attempts to target, if unable then remove as would do nothing
            target=SpotManager.attemptTarget(this);
            if(target==null)getWorld().removeObject(this);
        }
    }
    
    /**
     * <h3>void playMoneyEffect(int money)</h3>
     * <p>Handles the money effect when the gambler wins or loses money.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>money (int):</strong> The amount of money won or lost.</li>
     * </ul>
     */
    public void playMoneyEffect(int money) {
        //if gained is 0 do nothing
        if(money==0)return;
        //money gets incremented
        this.money+=money;
        //casino profit gets incremented
        UIManager.incrementCasinoProfit(-money);
        //spawns pop up.
        getWorld().addObject(new Message((Integer.signum(money)==-1?"-$":"+$")+Math.abs(money),(Integer.signum(money)==-1?Color.RED:Color.GREEN)), getX(),getY()-30);
    }
    
    /**
     * <h3>void playDialogue(String text)</h3>
     * <p>Displays a dialogue message above the gambler.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>text (String):</strong> The text of the dialogue.</li>
     * </ul>
     */
    public void playDialogue(String text){
        getWorld().addObject(new Message(text,Color.WHITE,200,3),getX(),getY()-30);
    }
    /**
     * <h3>void playDialogue(String text)</h3>
     * <p>Displays a dialogue message above the gambler.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>text (String):</strong> The text of the dialogue.</li>
     *     <li><strong>size (String):</strong> The size of the dialogue.</li>

     * </ul>
     */
    public void playDialogue(String text, int size){
        getWorld().addObject(new Message(text,Color.WHITE,100,3,16),getX(),getY()-30);
    }
    
    /**
     * <h3>void act()</h3>
     * <p>Called during each action step in the game environment. Manages the gambler's movement and interactions.</p>
     */
    public void act() {
        if(!playing) {
            //5(frame every 5 acts)*9(9 frames)=45
            if(++animationStep==45)animationStep=0;
            if(Math.abs(tx-getX())>2){//if x not at target(wiggle room of 2)
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(tx-getX())==-1?2:4),animationStep/5+1));//animates
                mostRecentDirection=(Integer.signum(tx-getX())==-1?2:4);
                setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            }
            else if (Math.abs(ty-getY())>5){//if y not at target(wiggle room of 5)
                setImage(ImageManager.getImage("gambler",character,(Integer.signum(ty-getY())==-1?1:3),animationStep/5+1));//animates
                mostRecentDirection=(Integer.signum(ty-getY())==-1?1:3);
                setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            }
            else if (tx!=fx)tx=fx;//now target x is final target x. Already when to first target now go to fx.
            else if (tx==1250||tx==-50)getWorld().removeObject(this);//if offscreen remove
            else if(!flag) {
                if(!toSpot)ty+=yToSpot;//if toSpot is positive(already played the game) then ty(target y) is adjusted accordingly and if negative(have not played the game) it is adjusted accordingly
                else ty-=yToSpot;
                flag=true;//to prevent this else if block of code from immediately running again
            } 
            else if(!toSpot) {//at this point gambler is on the game. If toSpot is false it means that this section of code has not been run before, so run it.
                playing=true;
                setImage(ImageManager.getImage("gambler",character,mostRecentDirection,1));
                toSpot=true;
                flag=false;
            } 
            else if(!leaving) {
                target = SpotManager.attemptTarget(this);
                if(target!=null){//has another target
                    toSpot=false;//false so particular section of code can run again
                    flag=false;//false so particular section of code can run again
                }
                else exit();
            } 
            else exit();
        }
        else if(getOneObjectAtOffset(1,1,Insane.class)!=null){//when playing and an insane shows up go somewhere else..
            SpotManager.getGames()[target.getGameIndex()].endGamblerSession(target.getSpotIndex());
            playing=false;
            playDialogue((Greenfoot.getRandomNumber(2)==0?"Get away from me.":"Personal space..."));
        }
    }
    protected void exit(){
        tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        ty=690+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));
        fx=(Greenfoot.getRandomNumber(2)==0?1250:-50);
    }
    /**
     * <h3>void target(int x, int y, int compensate)</h3>
     * <p>Sets the target location for the gambler.</p>
     * <p><strong>Parameters:</strong></p>
     * <ul>
     *     <li><strong>x (int):</strong> The x-coordinate of the target.</li>
     *     <li><strong>y (int):</strong> The y-coordinate of the target.</li>
     *     <li><strong>compensate (int):</strong> The adjustment value for the target y-coordinate.</li>
     * </ul>
     */
    public void target(int x, int y, int compensate){
        if(Math.abs(ty+yToSpot-y)>25)tx=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(20):Greenfoot.getRandomNumber(20));//if significant y pos difference then move to middle area
        fx=x;
        ty=y-compensate;
        yToSpot=compensate;
    }
    /**
     * <h3>int getTargetX()</h3>
     * <p>Returns the x-coordinate of the gambler's target.</p>
     * <p><strong>Return:</strong> int - The target x-coordinate.</p>
     */
    public int getTargetX(){
        return tx;
    }
    /**
     * <h3>int getTargetY()</h3>
     * <p>Returns the y-coordinate of the gambler's target.</p>
     * <p><strong>Return:</strong> int - The target y-coordinate.</p>
     */
    public int getTargetY(){
        return ty;
    }
    
    /**
     * <h3>void stopPlaying()</h3>
     * <p>Sets the playing flag to false, indicating the gambler has stopped playing.</p>
     */
    public void stopPlaying(){
        playing=false;
    }
    
    /**
     * <h3>boolean isPlaying()</h3>
     * <p>Returns whether the gambler is currently playing.</p>
     * <p><strong>Return:</strong> boolean - True if the gambler is playing, false otherwise.</p>
     *
     */
    public boolean isPlaying(){
        return playing;
    }
    
    /**
     * <h3>int getMoney()</h3>
     * <p>Returns the amount of money the gambler has.</p>
     * <p><strong>Return:</strong> int - The amount of money.</p>
     */
    public int getMoney(){
        return money;
    }
    
    /**
     * <h3>int getSkill()</h3>
     * <p>Returns the skill level of the gambler.</p>
     * <p><strong>Return:</strong> int - The skill level.</p>
     */
    public int getSkill(){
        return skill;
    }
    
    /**
     * <h3>int getLuck()</h3>
     * <p>Returns the luck value of the gambler.</p>
     * <p><strong>Return:</strong> int - The luck value.</p>
     */
    public int getLuck(){
        return luck;
    }
    
    /**
     * <h3>boolean isInsane()</h3>
     * <p>Returns whether the gambler is insane.</p>
     * <p><strong>Return:</strong> boolean - True if the gambler is insane, false otherwise.</p>
     */
    public boolean isInsane(){
        return insane;
    }
}
