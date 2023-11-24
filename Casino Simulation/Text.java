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
    String font;
    public Text(int w, int h, int s, String font, String text, int type){
        this.w=w;
        this.h=h;
        this.s=s;
        this.font=font;
        setImage(createText(text));
        this.type=type;
    }
    public void act() {
        switch(type){
            case 0:setImage(createText("yeah0"));break;
            case 1:setImage(createText("yeah1"));break;
            case 2:setImage(createText("yeah2"));break;
            case 3:setImage(createText("yeah3"));break;
        }
    }
    private GreenfootImage createText(String text){
        GreenfootImage gfi = new GreenfootImage(w, h);
        gfi.setColor(Color.BLACK);
        gfi.setFont(new Font(font, true, false, s/text.length())); 
        gfi.drawString(text, w/4, h/2);
        return gfi;
    }
}
