import greenfoot.*;
import java.util.*;
/**
 * Class that manages images.
 * Recommended to add images in world constructor.
 * @author Jimmy Zhu
 * @version 1110
 * 
 */
public class ImageManager  
{
    //using 1D over 2D because according to a StackOverFlow post 2D takes more memory
    private static Map<String,GreenfootImage[]> images = new HashMap<String,GreenfootImage[]>();
    /**
     *adds images denoted.
     */
    //for method to not error make sure the name is in formatted(name+types+"_"+frames+".png").
    public static void addImages(String name, int types, int frames){
        GreenfootImage temp[] = new GreenfootImage[types*frames+1];
        for(int i = 0; i<types; i++){
            for(int j = 1; j<=frames; j++)temp[i*frames+j]=new GreenfootImage(name+types+"_"+frames+".png");
        }
        images.put(name,temp);
    }
    /**
     *adds images denoted.
     */
    public static void addImages(String name, int frames){
        GreenfootImage temp[] = new GreenfootImage[frames+1];
        for(int i = 1; i<=frames; i++)temp[frames+i]=new GreenfootImage(name+frames+".png");
        images.put(name,temp);
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name, int types, int frames){
        return images.get(name)[types*frames];
    }
    /**
     *gets image denoted.
     */
    public static GreenfootImage getImage(String name, int frames){
        return images.get(name)[frames];
    }
}
