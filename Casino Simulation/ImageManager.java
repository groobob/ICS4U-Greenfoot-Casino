import greenfoot.*;
import java.util.*;
/**
 * Class that manages images.
 * Recommended to add images in world constructor.
 * @author Jimmy Zhu
 * @version 1120
 */
public class ImageManager  
{
    private static Map<String,GreenfootImage[][][]> images = new HashMap<String,GreenfootImage[][][]>();
    /**
     *adds images denoted.
     */
    public static void addImages(String name, int types, int states, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[types+1][states+1][frames+1];
        for(int i = 1; i<=types; i++)
            for(int j = 1; j<=states; j++)
                for(int k = 1; k<=frames; k++)temp[i][j][k]=new GreenfootImage(name+"_"+i+"_"+j+"_"+k+".png");
        images.put(name,temp);
    }
    /**
     *adds images denoted.
     */
    public static void addImages(String name, int states, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[1][states+1][frames+1];
        for(int j = 1; j<=states; j++)
            for(int k = 1; k<=frames; k++)temp[0][j][k]=new GreenfootImage(name+"_"+j+"_"+k+".png");
        images.put(name,temp);
    }
    /**
     *adds images denoted.
     */
    public static void addImages(String name, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[1][1][frames+1];
        for(int k = 1; k<=frames; k++)temp[0][0][k]=new GreenfootImage(name+"_"+k+".png");
        images.put(name,temp);
    }
    /**
     *adds images denoted.
     */
    public static void addImages(String name){
        GreenfootImage temp[][][] = new GreenfootImage[1][1][1];
        temp[0][0][0]=new GreenfootImage(name+".png");
        images.put(name,temp);
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name, int type, int state, int frame){
        return images.get(name)[type][state][frame];
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name, int state, int frame){
        return images.get(name)[0][state][frame];
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name, int frame){
        return images.get(name)[0][0][frame];
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name){
        return images.get(name)[0][0][0];
    }
}
