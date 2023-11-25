import greenfoot.*;
/**
 * Text
 * 
 * @Jimmy Zhu
 * @1124
 */
public class Text extends Actor
{
    private int type,w,h,s;
    private String font;
    public Text(int w, int h, int s, String font, String text, int type){
        this.w=w;
        this.h=h;
        this.s=s;
        this.font=font;
        changeText(text);
        this.type=type;
        HorizontalBar.attachText(this, type);
    }
    public void changeText(String text){
        GreenfootImage gfi = new GreenfootImage(w,h);
        gfi.setColor(Color.BLACK);
        gfi.setFont(new Font(font, true, false, s/text.length())); 
        gfi.drawString(text, w/4, h/2);
        setImage(gfi);
    }
}
