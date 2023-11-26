import greenfoot.*;
import java.util.*;
/**
 * <html>
 * <body>
 * <p><strong>ImageManager</strong> is a utility class for managing images in Greenfoot applications.</p>
 * <p>It allows for adding and retrieving images with varying complexity, including support for different types, states, and animation frames.</p>
 * 
 * <h3>Usage:</h3>
 * <p>Use ImageManager to add and access images for actors and other elements within the application. Images should be added during the world's construction phase for optimal performance.</p>
 * 
 * <h3>Class Fields:</h3>
 * <ul>
 *     <li><strong>images</strong> - A map that stores multi-dimensional arrays of <code>GreenfootImage</code> objects.</li>
 * </ul>
 * @author Jimmy Zhu
 * @version 11/22
 */public class ImageManager  
{
    private static Map<String,GreenfootImage[][][]> images = new HashMap<String,GreenfootImage[][][]>();
    /**
     * <p><strong>static void addImages(String name, int types, int states, int frames)</strong> - Adds images for scenarios with types, states, and frames. <br>
     *     <strong>@param name</strong> - The base name of the images.<br>
     *     <strong>@param types</strong> - The number of types.<br>
     *     <strong>@param states</strong> - The number of states.<br>
     *     <strong>@param frames</strong> - The number of frames.</p>
     */ 
    public static void addImages(String name, int types, int states, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[types+1][states+1][frames+1];
        for(int i = 1; i<=types; i++)
            for(int j = 1; j<=states; j++)
                try{
                    for(int k = 1; k<=frames; k++)temp[i][j][k]=new GreenfootImage(name+"_"+i+"_"+j+"_"+k+".png");
                }catch(Exception e){}
        images.put(name,temp);
    }
    /**
     * <p><strong>static void addImages(String name, int types, int states, int frames)</strong> - Adds images for scenarios with states and frames. <br>
     *     <strong>@param name</strong> - The base name of the images.<br>
     *     <strong>@param states</strong> - The number of states.<br>
     *     <strong>@param frames</strong> - The number of frames.</p>
     */ 
    public static void addImages(String name, int states, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[1][states+1][frames+1];
        for(int j = 1; j<=states; j++)
            try{
                for(int k = 1; k<=frames; k++)temp[0][j][k]=new GreenfootImage(name+"_"+j+"_"+k+".png");
            }catch(Exception e){}
        images.put(name,temp);
    }
    /**
     * <p><strong>static void addImages(String name, int types, int states, int frames)</strong> - Adds images for scenarios with frames. <br>
     *     <strong>@param name</strong> - The base name of the images.<br>
     *     <strong>@param frames</strong> - The number of frames.</p>
     */ 
    public static void addImages(String name, int frames){
        GreenfootImage temp[][][] = new GreenfootImage[1][1][frames+1];
        try{
            for(int k = 1; k<=frames; k++)temp[0][0][k]=new GreenfootImage(name+"_"+k+".png");
        }catch(Exception e){}
        images.put(name,temp);
    }
    /**
     * <p><strong>static void addImages(String name, int types, int states, int frames)</strong> - Adds image denoted by name. <br>
     *     <strong>@param name</strong> - The base name of the images.<br>
     */ 
    public static void addImages(String name){
        GreenfootImage temp[][][] = new GreenfootImage[1][1][1];
        temp[0][0][0]=new GreenfootImage(name+".png");
        images.put(name,temp);
    }
    /**
     * <p><strong>static GreenfootImage getImage(String name, int type, int state, int frame)</strong> - Retrieves a specific image denoted by name, type, state, and frame. <br>
     * <strong>@param name</strong> - The base name of the image.<br>
     * <strong>@param type</strong> - The type index.<br>
     * <strong>@param state</strong> - The state index.<br>
     * <strong>@param frame</strong> - The frame index.<br>
     * <strong>@return GreenfootImage</strong> - The requested image.</p>
     */
    public static GreenfootImage getImage(String name, int type, int state, int frame){
        return images.get(name)[type][state][frame];
    }
    /**
     * <p><strong>static GreenfootImage getImage(String name, int type, int state, int frame)</strong> - Retrieves a specific image denoted by name, state, and frame. <br>
     * <strong>@param name</strong> - The base name of the image.<br>
     * <strong>@param state</strong> - The state index.<br>
     * <strong>@param frame</strong> - The frame index.<br>
     * <strong>@return GreenfootImage</strong> - The requested image.</p>
     */
    public static GreenfootImage getImage(String name, int state, int frame){
        return images.get(name)[0][state][frame];
    }
    /**
     * <p><strong>static GreenfootImage getImage(String name, int type, int state, int frame)</strong> - Retrieves a specific image denoted by name and frame. <br>
     * <strong>@param name</strong> - The base name of the image.<br>
     * <strong>@param frame</strong> - The frame index.<br>
     * <strong>@return GreenfootImage</strong> - The requested image.</p>
     */
    public static GreenfootImage getImage(String name, int frame){
        return images.get(name)[0][0][frame];
    }
    /**
     * <p><strong>static GreenfootImage getImage(String name, int type, int state, int frame)</strong> - Retrieves a specific image denoted by name. <br>
     * <strong>@param name</strong> - The base name of the image.<br>
     * <strong>@return GreenfootImage</strong> - The requested image.</p>
     */
    public static GreenfootImage getImage(String name){
        return images.get(name)[0][0][0];
    }
}