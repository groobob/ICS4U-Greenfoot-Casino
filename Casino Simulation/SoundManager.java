import greenfoot.*;
import java.util.*;
/**
 * Class that manages sound.
 * Recommended to add sounds in world constructor.
 * @author Jimmy Zhu
 * @version 1.0 11/16/23
 * 
 */
public class SoundManager  
{
    private static Map<String,Queue<GreenfootSound>> sounds = new HashMap<String,Queue<GreenfootSound>>();
    private static Map<String,GreenfootSound> currentlyLooping = new HashMap<String,GreenfootSound>();
    /**
     *Plays sound
     */
    //Note: when a sound that is being looped is played with the playSound method, the sound will stop looping. This is intentional.
    public static void playSound(String name){
        sounds.get(name).peek().play();
        sounds.get(name).add(sounds.get(name).poll());
        stopLooped(name);
    }
    /**
     *Looped play
     */
    public static void playLooped(String name){
        currentlyLooping.put(name,sounds.get(name).peek());
        currentlyLooping.get(name).playLoop();
    }
    /**
     *Stops looped sound
     */
    public static void stopLooped(String name){
        if(currentlyLooping.get(name)!=null)currentlyLooping.remove(name).stop();
    }
    /**
     *Add sounds
     */
    public static void addSound(int maxSimultaneousActive, String name, String type){
        sounds.put(name,createFilledQueue(maxSimultaneousActive,name+"."+type));
    }
    //adds n copies of a GreenFootSound denoted by name
    private static Queue<GreenfootSound> createFilledQueue(int n, String name){
        Queue<GreenfootSound> q = new LinkedList<GreenfootSound>();
        for(int i = 0; i<n; i++)q.add(new GreenfootSound(name));
        return q;
    }
}