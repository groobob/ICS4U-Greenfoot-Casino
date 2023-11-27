import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <html>
 * <body>
 * <h1>Entrance Class</h1>
 * <p>This class extends <em>Actor</em> and represents the entrance to the casino in a Greenfoot game. It includes utility actions to assist in finding coordinates within the game world.</p>
 *
 * <h2>Class Attributes:</h2>
 * <ul>
 *     <li><strong>up:</strong> A boolean flag to manage key press states.</li>
 * </ul>
 *
 * @author Jimmy Zhu & David Guo
 * @version 1.0 11/18/2023
 */
public class Entrance extends Actor
{
    /**
     * <h3>void act()</h3>
     * <p>Called during each action step in the game environment. Handles key press events for utility purposes.</p>
     */
    boolean up=true;
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            if(Greenfoot.isKeyDown("f")&&up){
                System.out.println(mouse.getX()+","+mouse.getY());
                up=false;
            }
            else if(Greenfoot.isKeyDown("g"))up=true;
        }
    }
}
