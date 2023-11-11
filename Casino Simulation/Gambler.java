import greenfoot.*;
/**
 * Gambler.
 * @author Jimmy Zhu
 * @version 1111
 * 
 */
//DO NOT ABSTRACT. Not abstracted rn because need this needs to work as a base.
public class Gambler extends Actor
{
    //in: tx:entrance x, fx:game x, ty: game y
    //out: tx: entrace x, fx: out world x, fy: sidewalk y
    private final int varyRange=40;//temp
    private final int entranceX=600+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(varyRange):Greenfoot.getRandomNumber(varyRange));//temp
    private final int sidewalkY=700+(Greenfoot.getRandomNumber(2)==0?-Greenfoot.getRandomNumber(varyRange):Greenfoot.getRandomNumber(varyRange));//temp
    private final int outMapX=(Greenfoot.getRandomNumber(2)==0?1250:-50);//temp
    private int speed=5,tx=entranceX,fx,ty;
    private boolean stopped=false,flag=false,down=false;
    public Gambler(int fx, int ty){
        this.fx=fx;
        this.ty=ty;
    }
    public void act()
    {
        if(!stopped){
            if(Math.abs(tx-getX())>5)setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            else if(Math.abs(ty-getY())>5)setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            else if(tx!=fx)tx=fx;
            else if(tx==1250||tx==-50)getWorld().removeObject(this);
            else if(!flag){
                if(!down)ty-=50;
                else ty+=50;
                flag=true;
            }
            else if(!down){
                stopped=true;
                down=true;
                flag=false;
                //station
                unstop();//temp
            }
            else{
                tx=entranceX;
                ty=sidewalkY;
                fx=outMapX;
            }
        }
    }
    public void unstop(){//temp
        stopped=false;
    }
}
