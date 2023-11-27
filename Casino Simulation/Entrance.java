import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The entrance to the casino, has some utility actions which help find coordinates
 * 
 * @author Jimmy Zhu & David Guo
 * @version 1.0 11/18/2023
 */
public class Entrance extends Actor
{
    boolean up=true;
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            //pressing f only prints once before pressing g
            if(Greenfoot.isKeyDown("f")&&up){
                System.out.println(mouse.getX()+","+mouse.getY());
                up=false;
            }
            else if(Greenfoot.isKeyDown("g"))up=true;
        }
    }
}
