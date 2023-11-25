import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The entrance to the casino, has some utility actions which help find coordinates
 * 
 * @author Jimmy Zhu & David Guo
 * @version 1.0 11/18/2023
 */
public class Entrance extends Actor
{
    /**
     * Act - do whatever the Thing2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
