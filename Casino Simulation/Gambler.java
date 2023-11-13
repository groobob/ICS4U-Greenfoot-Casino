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
    private int speed=5,tx=entranceX,fx,ty,yToStation=50;
    private boolean stopped=false,flag=false,toStation=false;
    public Gambler(int fx, int ty){
        this.fx=fx;
        this.ty=ty;
    }
    public void act()//rn only have go up to station. Implement go down to station and no y change to station. Maybe also make y change amount changeable.
    {
        if(!stopped){
            if(Math.abs(tx-getX())>5)setLocation(getX()+speed*Integer.signum(tx-getX()),getY());
            else if(Math.abs(ty-getY())>5)setLocation(getX(),getY()+speed*Integer.signum(ty-getY()));
            else if(tx!=fx)tx=fx;
            else if(tx==1250||tx==-50)getWorld().removeObject(this);
            else if(!flag){
                if(!toStation)ty-=yToStation;
                else ty+=yToStation;
                flag=true;
            }
            else if(!toStation){
                stopped=true;
                toStation=true;
                flag=false;
                //station
                unstop();//temp
            }
            else{
                if(Greenfoot.getRandomNumber(3)==0){//temp for testing 1/3 chance of leaving casino
                    tx=entranceX;
                    ty=sidewalkY;
                    fx=outMapX;
                }
                else{
                    CasinoWorld.pos p = CasinoWorld.tempPlaces.get(Greenfoot.getRandomNumber(CasinoWorld.tempPlaces.size()));
                    tx=entranceX;
                    ty=p.y+yToStation;
                    fx=p.x;
                    toStation=false;
                    flag=false;
                }
            }
        }
    }
    public void unstop(){//temp
        stopped=false;
    }
}
