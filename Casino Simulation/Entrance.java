import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Thing2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
                System.out.println("tempPlaces.add(new pos("+mouse.getX()+","+mouse.getY()+"));");
                up=false;
            }
            else if(Greenfoot.isKeyDown("g"))up=true;
        }
    }
}
